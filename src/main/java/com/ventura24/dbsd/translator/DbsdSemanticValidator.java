/* **********************************************************************
 *   This file is part of DB Schema Definition Translator.              *
 *                                                                      *
 *   DB Schema Definition Translator is free software: you can          *
 *   redistribute it and/or modify it under the terms of the GNU        *
 *   General Public License as published by the Free Software           *
 *   Foundation, either version 2 of the License, or any later version. *
 *                                                                      *
 *   Foobar is distributed in the hope that it will be useful,          *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of     *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the      *
 *   GNU General Public License for more details.                       *
 *                                                                      *
 *   You should have received a copy of the GNU General Public License  *
 *   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.    *
 *                                                                      *
 *   Author: Felipe Crespo Gambade                                      *
 *                                                                      *
 ************************************************************************/

package com.ventura24.dbsd.translator;


import com.ventura24.dbsd.translator.dto.DataBaseSchema;
import com.ventura24.dbsd.translator.dto.IsaTable;
import com.ventura24.dbsd.translator.dto.StaticTable;
import com.ventura24.dbsd.translator.dto.Table;
import com.ventura24.dbsd.translator.dto.data.Data;
import com.ventura24.dbsd.translator.dto.constraints.CheckConstraint;
import com.ventura24.dbsd.translator.dto.constraints.Constraint;
import com.ventura24.dbsd.translator.dto.constraints.ForeignKeyConstraint;
import com.ventura24.dbsd.translator.dto.constraints.IndexConstraint;
import com.ventura24.dbsd.translator.dto.constraints.PrimaryKeyConstraint;
import com.ventura24.dbsd.translator.dto.constraints.UniqueKeyConstraint;

import java.util.List;

/**
 * The Dbsd Semantic Validator
 */
public class DbsdSemanticValidator
{

    private final DataBaseSchema dbs;

    /**
     * Creates a new semantic validator.
     *
     * @param dbs a Data Base Schema
     */
    public DbsdSemanticValidator(final DataBaseSchema dbs)
    {
        this.dbs = dbs;
    }

    /**
     * Validate the Data Base Schema given in the constructor method.
     *
     * @return true if is valid.
     */
    public boolean isValid()
    {
        final boolean isValidDbs = areValidTables(dbs.getTables());

        if(!isValidDbs)
        {
            SystemMessagePrinter.printSystemMessage(SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_DBS);
        }
        return isValidDbs;
    }

    /**
     * Validate a Table List.
     *
     * @param tables such information.
     * @return true if is valid.
     */
    private boolean areValidTables(final Iterable<Table> tables)
    {
        boolean result = true;

            for (final Table table : tables)
            {
                result &= isValid(table);
            }

        return result;
    }

    /**
     * Validate a Table.
     *
     * @param table such information.
     * @return true if is valid.
     */
    private boolean isValid(final Table table)
    {
        boolean result;
        final Iterable<Constraint> constraints = table.getConstraints();

        switch (table.getType())
        {
            case ISA:
                result = isValidIsaReference((IsaTable) table);
                result &= areValidConstraints(constraints, table);
                break;
            case STATIC:
                final StaticTable staticTable = (StaticTable) table;
                result = isValidStaticReference(staticTable);
                result &= areValidConstraints(constraints, table);
                result &= areValidDataRows(staticTable.getDataRows(), table);
                break;
            default:
                result = areValidConstraints(constraints, table);
        }

        return result;
    }

    /**
     * Validate a ISA table reference.
     *
     * @param isaTable such information.
     * @return true if is valid.
     */
    private boolean isValidIsaReference(final IsaTable isaTable)
    {
        boolean result = true;
        final String referenceTable = isaTable.getReferenceTable();

        if (!dbs.containsTable(referenceTable))
        {
            result = false;
            SystemMessagePrinter.printSystemMessage(
                    SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_ISATABLE_NOTEXISTS,
                    referenceTable,
                    isaTable.getName());
        }


        return result;
    }

    /**
     * Validate a Static table.
     *
     * @param staticTable such information.
     * @return true if is valid.
     */
    private boolean isValidStaticReference(final StaticTable staticTable)
    {
        boolean result = true;
        final String nameColumn = staticTable.getNameColumn();

        if (!staticTable.containsColumn(nameColumn))
        {
            result = false;
            SystemMessagePrinter.printSystemMessage(
                    SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_STATICTABLE_NOTEXISTS,
                    nameColumn,
                    staticTable.getName());
        }

        return result;
    }

    /**
     * Validate a group of constraints.
     *
     * @param constraints such information.
     * @param ownerTable the owner table.
     * @return true if all the constraints are valid.
     */
    private boolean areValidConstraints(
            final Iterable<Constraint> constraints,
            final Table ownerTable)
    {
        boolean result = true;

        for (final Constraint constraint : constraints)
        {
            result &= isValid(constraint, ownerTable);
        }

        return result;
    }

    /**
     * Validate a constraint.
     *
     * @param constraint such information.
     * @param ownerTable the owner table.
     * @return true if is valid.
     */
    private boolean isValid(
            final Constraint constraint,
            final Table ownerTable)
    {
        boolean result = false;

        switch (constraint.getType())
        {
            case INDEX:
                result = isValid(
                        (IndexConstraint) constraint,
                        ownerTable);
                break;
            case CHECK:
                result = isValid(
                        (CheckConstraint) constraint,
                        ownerTable);
                break;
            case PRIMARY_KEY:
                result = isValid(
                        (PrimaryKeyConstraint) constraint,
                        ownerTable);
                break;
            case UNIQUE_KEY:
                result = isValid(
                        (UniqueKeyConstraint) constraint,
                        ownerTable);
                break;
            case FOREIGN_KEY:
                result = isValid(
                        (ForeignKeyConstraint) constraint,
                        ownerTable);
                break;
        }

        return result;
    }

    /**
     * Validate a Foreign Key Constraint.
     *
     * @param constraint such information.
     * @param ownerTable the owner table.
     * @return true if is valid.
     */
    private boolean isValid(
            final ForeignKeyConstraint constraint,
            final Table ownerTable)
    {
        boolean result = true;

        final List<String> columns = constraint.getColumns();
        final String referenceTableName = constraint.getReferenceTable();
        final List<String> referenceColumns = constraint.getReferenceColumns();

        if (columns.size() != referenceColumns.size())
        {
            result = false;
            SystemMessagePrinter.printSystemMessage(
                    SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_FOREIGNKEY_DIFFERENTSIZE,
                    constraint.getName(),
                    columns.size(),
                    referenceColumns.size());
        }

        result &= checkTableContainsColumns(ownerTable, columns, constraint.getName());

        if (dbs.containsTable(referenceTableName))
        {
            final Table referenceTable = dbs.getTable(referenceTableName);
            result &= checkTableContainsColumns(referenceTable, referenceColumns, constraint.getName());
        }
        else
        {
            result = false;
            SystemMessagePrinter.printSystemMessage(
                    SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_FOREIGNKEY_REFERENCETABLE_NOTEXISTS,
                    referenceTableName,
                    constraint.getName());
        }

        return result;
    }

    /**
     * Validate a Unique Key Constraint.
     *
     * @param constraint such information.
     * @param ownerTable the owner table.
     * @return true if is valid.
     */
    private boolean isValid(
            final UniqueKeyConstraint constraint,
            final Table ownerTable)
    {
        return checkTableContainsColumns(ownerTable, constraint.getColumns(), constraint.getName());
    }

    /**
     * Validate a Primary Key Constraint.
     *
     * @param constraint such information.
     * @param ownerTable the owner table.
     * @return true if is valid.
     */
    private boolean isValid(
            final PrimaryKeyConstraint constraint,
            final Table ownerTable)
    {
        final String constraintName = constraint.getName();
        boolean result = checkTableContainsColumns(ownerTable, constraint.getColumns(), constraintName);

        if (constraint.isUsingIndex())
        {
            final String index = constraint.getIndex();

            if (!ownerTable.containsConstraint(index))
            {
                result = false;
                SystemMessagePrinter.printSystemMessage(
                        SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_CONSTRAINT_INDEXNOTEXISTS,
                        index,
                        constraintName,
                        ownerTable.getName());
            }
        }

        return result;
    }

    /**
     * Validate a Check Constraint.
     *
     * @param constraint such information.
     * @param ownerTable the owner table.
     * @return true if is valid.
     */
    private boolean isValid(
            final CheckConstraint constraint,
            final Table ownerTable)
    {
        return checkTableContainsColumn(ownerTable, constraint.getColumn(), constraint.getName());
    }

    /**
     * Validate a Index.
     *
     * @param constraint such information.
     * @param ownerTable the owner table.
     * @return true if is valid.
     */
    private boolean isValid(
            final IndexConstraint constraint,
            final Table ownerTable)
    {
        boolean result = true;
        final String columnName = constraint.getColumn();

        if (!ownerTable.containsColumn(columnName))
        {
            result = false;
            SystemMessagePrinter.printSystemMessage(
                    SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_INDEX_COLUMNNOTEXISTS,
                    columnName,
                    constraint.getName(),
                    ownerTable.getName());
        }

        return result;
    }


    /**
     * Check if a data rows group are valid
     *
     * @param dataRows the data rows.
     * @param ownerTable the owner table.
     *
     * @return true if all the data rows are valid.
     */
    private boolean areValidDataRows(
            final List<List<Data>> dataRows,
            final Table ownerTable)
    {
        boolean result = true;
        for (final List<Data> dataRow : dataRows)
        {
            result &= isValid(dataRow, ownerTable);
        }
        return result;
    }

    /**
     * Check if a data row is valid
     *
     * @param dataRow the data row.
     * @param ownerTable the owner table.
     *
     * @return true if the data row is valid.
     */
    private boolean isValid(
            final List<Data> dataRow,
            final Table ownerTable)
    {
        boolean result = true;

        final int tableColumnNumber = ownerTable.getColumns().size();
        if (dataRow.size() != tableColumnNumber)
        {
            result = false;
            SystemMessagePrinter.printSystemMessage(
                    SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_DATAROW_DIFFERENTSIZE,
                    dataRow.get(0).getContent(),
                    ownerTable.getName(),
                    dataRow.size(),
                    tableColumnNumber);
        }

        return result;
    }

    /**
     * Check if a table contains a group of columns.
     * @param table the table.
     * @param columns the columns.
     * @param constraintName the constraint name.
     * @return true if the table contains all the columns.
     */
    private boolean checkTableContainsColumns(
            final Table table,
            final Iterable<String> columns,
            final String constraintName)
    {
        boolean result = true;

        for (final String column : columns)
        {
            result &= checkTableContainsColumn(table, column, constraintName);
        }
        return result;
    }

    private boolean checkTableContainsColumn(
            final Table table,
            final String column,
            final String constraintName)
    {
        boolean result = true;

        if (!table.containsColumn(column))
        {
            result = false;
            SystemMessagePrinter.printSystemMessage(
                    SystemMessagePrinter.SEMANTIC_VALIDATION_ERROR_CONSTRAINT_COLUMNNOTEXISTS,
                    column,
                    constraintName,
                    table.getName());
        }
        return result;
    }
}
