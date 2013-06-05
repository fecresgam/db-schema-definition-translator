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

package com.ryoppei.dbsd.translator.dto;


import com.ryoppei.dbsd.translator.dto.columns.Column;
import com.ryoppei.dbsd.translator.dto.constraints.Constraint;
import com.ryoppei.dbsd.translator.utils.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * The intermediate representation of a Common Table
 */
public class CommonTable implements Table
{
    private String name;
    private TableType type;
    private String description;

    private Map<String, Column> columns;
    private Map<String, Constraint> constraints;

    private List<Column> orderedColumns;
    private List<Constraint> orderedConstraints;

    /**
     * Creates a new Common Table.
     *
     * @param name such information.
     * @param type such information.
     * @param description such information.
     * @param columns such information.
     * @param constraints such information.
     */
    public CommonTable(
            final String name,
            final TableType type,
            final String description,
            final List<Column> columns,
            final List<Constraint> constraints)
    {
        final CollectionUtils collectionUtils =
                CollectionUtils.getInstance();

        this.name = name;
        this.type = type;
        this.description = description;
        this.orderedColumns = columns;
        this.orderedConstraints = constraints;

        // put intos maps
        this.columns = collectionUtils.putIntoMap(columns);
        this.constraints = collectionUtils.putIntoMap(constraints);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public TableType getType()
    {
        return type;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public Column getColumn(final String key)
    {
        return columns.get(key);
    }

    @Override
    public boolean containsColumn(String columnName)
    {
        return columns.containsKey(columnName);
    }

    @Override
    public List<Column> getColumns()
    {
        return orderedColumns;
    }

    @Override
    public Constraint getConstraint(final String key)
    {
        return constraints.get(key);
    }

    @Override
    public List<Constraint> getConstraints()
    {
        return orderedConstraints;
    }

    @Override
    public boolean containsConstraint(final String constraintName)
    {
        return constraints.containsKey(constraintName);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof CommonTable))
        {
            return false;
        }

        CommonTable that = (CommonTable) o;

        if (!name.equals(that.name))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public String getMapKey()
    {
        return getName();
    }
}
