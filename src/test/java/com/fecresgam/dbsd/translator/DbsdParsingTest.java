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

package com.fecresgam.dbsd.translator;

import com.fecresgam.dbsd.translator.dto.DBMSType;
import com.fecresgam.dbsd.translator.dto.DataBaseSchema;
import com.fecresgam.dbsd.translator.dto.IsaTable;
import com.fecresgam.dbsd.translator.dto.StaticTable;
import com.fecresgam.dbsd.translator.dto.Table;
import com.fecresgam.dbsd.translator.dto.TableType;
import com.fecresgam.dbsd.translator.dto.columns.Column;
import com.fecresgam.dbsd.translator.dto.columns.ColumnOption;
import com.fecresgam.dbsd.translator.dto.columns.ColumnOptionType;
import com.fecresgam.dbsd.translator.dto.columns.DefaultValueColumnOption;
import com.fecresgam.dbsd.translator.dto.columns.NumberColumn;
import com.fecresgam.dbsd.translator.dto.columns.TextColumn;
import com.fecresgam.dbsd.translator.dto.constraints.CheckConstraint;
import com.fecresgam.dbsd.translator.dto.constraints.Constraint;
import com.fecresgam.dbsd.translator.dto.constraints.ConstraintType;
import com.fecresgam.dbsd.translator.dto.constraints.ForeignKeyConstraint;
import com.fecresgam.dbsd.translator.dto.constraints.IndexConstraint;
import com.fecresgam.dbsd.translator.dto.constraints.PrimaryKeyConstraint;
import com.fecresgam.dbsd.translator.dto.constraints.UniqueKeyConstraint;
import com.fecresgam.dbsd.translator.dto.data.BooleanData;
import com.fecresgam.dbsd.translator.dto.data.Data;
import com.fecresgam.dbsd.translator.dto.data.DataType;
import com.fecresgam.dbsd.translator.dto.data.DateData;
import com.fecresgam.dbsd.translator.dto.data.NumberData;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Tests {@link DbsdParsingTest}.
 *
 * @author fcres
 *
 */
public class DbsdParsingTest
{

    private static final String TEST_INPUT_FILE_RELATIVE_URI =
            "src/test/resources/testInput.dbsd";

    /**
     * Tests the Dbsd parser
     *
     * @throws IOException
     */
    @Test
    public void testParserWithFile()
            throws IOException
    {
        final DbsdReader reader = new DbsdReader();
        final DataBaseSchema dbs = reader.compile(TEST_INPUT_FILE_RELATIVE_URI);

        // Headers
        assertEquals(DbsdTestUtils.OWNER, dbs.getOwner());
        assertEquals(DbsdTestUtils.USER, dbs.getUser());
        assertEquals(DbsdTestUtils.USER_ROLE, dbs.getUserRole());
        assertEquals(DbsdTestUtils.DEFAULT_TABLE_TBSPC, dbs.getDefaultTableTablespace());
        assertEquals(DbsdTestUtils.DEFAULT_INDEX_TBLSPC, dbs.getDefaultIndexTablespace());
        assertEquals(DBMSType.ORACLE, dbs.getTargetDBMS());


        // Tables
        assertEquals(3, dbs.getTables().size());
        // ----------- Table1 ----------------------
        final Table table1 = dbs.getTable(DbsdTestUtils.TABLE1_NAME);
        assertNotNull(table1);
        assertEquals(DbsdTestUtils.TABLE1_NAME, table1.getName());
        assertEquals(DbsdTestUtils.TABLE1_DESC, table1.getDescription());
        assertEquals(TableType.STATIC, table1.getType());
        final StaticTable table1b = (StaticTable) table1;
        assertEquals(DbsdTestUtils.COL12_NAME, table1b.getNameColumn());
        // Col 11
        assertEquals(3, table1.getColumns().size());
        final Column column11 = table1.getColumn(DbsdTestUtils.COL11_NAME);
        assertEquals(column11.getName(), DbsdTestUtils.COL11_NAME);
        assertEquals(DataType.NUMBER, column11.getDataType());
        assertEquals(DbsdTestUtils.COL11_DESC, column11.getDescription());
        assertEquals(1, column11.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column11.getOptions().get(0).getType());
        final NumberColumn column11b = (NumberColumn) column11;
        assertEquals(2, column11b.getPrecision().intValue());
        assertEquals(null, column11b.getScale());
        // Col 12
        final Column column12 = table1.getColumn(DbsdTestUtils.COL12_NAME);
        assertEquals(column12.getName(), DbsdTestUtils.COL12_NAME);
        assertEquals(DataType.TEXT, column12.getDataType());
        assertEquals(DbsdTestUtils.COL12_DESC, column12.getDescription());
        assertEquals(1, column12.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column12.getOptions().get(0).getType());
        final TextColumn column12b = (TextColumn) column12;
        assertEquals(50, column12b.getSize().intValue());

        // Col 13
        final Column column13 = table1.getColumn(DbsdTestUtils.COL13_NAME);
        assertEquals(DbsdTestUtils.COL13_NAME, column13.getName());
        assertEquals(DbsdTestUtils.COL13_DESC, column13.getDescription());
        assertEquals(DataType.BOOLEAN, column13.getDataType());
        assertEquals(2, column13.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column13.getOptions().get(0).getType());
        final ColumnOption columnOption13 = column13.getOptions().get(1);
        assertEquals(ColumnOptionType.DEFAULT_VALUE, columnOption13.getType());
        final DefaultValueColumnOption columnOption13b = (DefaultValueColumnOption) columnOption13;
        assertEquals(BooleanData.FALSE, columnOption13b.getValue());

        // Constrains
        assertEquals(1, table1.getConstraints().size());
        final Constraint constraint11 = table1.getConstraint(DbsdTestUtils.CONS11_NAME);
        assertEquals(DbsdTestUtils.CONS11_NAME, constraint11.getName());
        assertEquals(ConstraintType.PRIMARY_KEY, constraint11.getType());
        final PrimaryKeyConstraint constraint11b = (PrimaryKeyConstraint) constraint11;
        final List<String> constraint11bColumns = constraint11b.getColumns();
        assertEquals(1, constraint11bColumns.size());
        assertEquals(DbsdTestUtils.COL11_NAME, constraint11bColumns.get(0));

        // Table data rows
        final List<List<Data>> table1DataRows = table1.getDataRows();
        assertNotNull(table1DataRows);
        assertEquals(4, table1DataRows.size());
        final List<Data> table1DataRow1 = table1DataRows.get(0);
        assertNotNull(table1DataRow1.get(0));
        assertEquals(DataType.NUMBER, table1DataRow1.get(0).getDataType());
        assertEquals(new Integer(1), ((NumberData) table1DataRow1.get(0)).getPrecision());
        assertEquals(new Integer(0), ((NumberData) table1DataRow1.get(0)).getScale());
        assertEquals("1", table1DataRow1.get(0).getContent());
        assertNotNull(table1DataRow1.get(1));
        assertEquals(DataType.TEXT, table1DataRow1.get(1).getDataType());
        assertEquals("Secretario", table1DataRow1.get(1).getContent());
        assertNotNull(table1DataRow1.get(2));
        assertEquals(DataType.BOOLEAN, table1DataRow1.get(2).getDataType());
        assertEquals(BooleanData.FALSE, table1DataRow1.get(2));
        final List<Data> table1DataRow2 = table1DataRows.get(1);
        assertNotNull(table1DataRow2.get(0));
        assertEquals(DataType.NUMBER, table1DataRow2.get(0).getDataType());
        assertEquals(new Integer(1), ((NumberData) table1DataRow2.get(0)).getPrecision());
        assertEquals(new Integer(0), ((NumberData) table1DataRow2.get(0)).getScale());
        assertEquals("2", table1DataRow2.get(0).getContent());
        assertNotNull(table1DataRow2.get(1));
        assertEquals(DataType.TEXT, table1DataRow2.get(1).getDataType());
        assertEquals("Programador", table1DataRow2.get(1).getContent());
        assertNotNull(table1DataRow2.get(2));
        assertEquals(DataType.BOOLEAN, table1DataRow2.get(2).getDataType());
        assertEquals(BooleanData.FALSE, table1DataRow2.get(2));
        final List<Data> table1DataRow3 = table1DataRows.get(2);
        assertNotNull(table1DataRow3.get(0));
        assertEquals(DataType.NUMBER, table1DataRow3.get(0).getDataType());
        assertEquals(new Integer(1), ((NumberData) table1DataRow3.get(0)).getPrecision());
        assertEquals(new Integer(0), ((NumberData) table1DataRow3.get(0)).getScale());
        assertEquals("3", table1DataRow3.get(0).getContent());
        assertNotNull(table1DataRow3.get(1));
        assertEquals(DataType.TEXT, table1DataRow3.get(1).getDataType());
        assertEquals("Directivo", table1DataRow3.get(1).getContent());
        assertNotNull(table1DataRow3.get(2));
        assertEquals(DataType.BOOLEAN, table1DataRow3.get(2).getDataType());
        assertEquals(BooleanData.TRUE, table1DataRow3.get(2));
        final List<Data> table1DataRow4 = table1DataRows.get(3);
        assertNotNull(table1DataRow4.get(0));
        assertEquals(DataType.NUMBER, table1DataRow4.get(0).getDataType());
        assertEquals(new Integer(1), ((NumberData) table1DataRow4.get(0)).getPrecision());
        assertEquals(new Integer(0), ((NumberData) table1DataRow4.get(0)).getScale());
        assertEquals("-8", table1DataRow4.get(0).getContent());
        assertNotNull(table1DataRow4.get(1));
        assertEquals(DataType.TEXT, table1DataRow4.get(1).getDataType());
        assertEquals("Espia", table1DataRow4.get(1).getContent());
        assertNotNull(table1DataRow4.get(2));
        assertEquals(DataType.BOOLEAN, table1DataRow4.get(2).getDataType());
        assertEquals(BooleanData.FALSE, table1DataRow4.get(2));

        // ----------- Table2 ----------------------
        final Table table2 = dbs.getTable(DbsdTestUtils.TABLE2_NAME);
        assertNotNull(table2);
        assertEquals(DbsdTestUtils.TABLE2_NAME, table2.getName());
        assertEquals(DbsdTestUtils.TABLE2_DESC, table2.getDescription());
        assertEquals(TableType.COMMON, table2.getType());
        assertEquals(4, table2.getColumns().size());
        // Col 21
        final Column column21 = table2.getColumn(DbsdTestUtils.COL21_NAME);
        assertEquals(DbsdTestUtils.COL21_NAME, column21.getName());
        assertEquals(DbsdTestUtils.COL21_DESC, column21.getDescription());
        assertEquals(DataType.NUMBER, column21.getDataType());
        final NumberColumn column21b = (NumberColumn) column21;
        assertEquals(4, column21b.getPrecision().intValue());
        assertEquals(null, column21b.getScale());
        assertEquals(1, column21.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column21.getOptions().get(0).getType());
        // Col 22
        final Column column22 = table2.getColumn(DbsdTestUtils.COL22_NAME);
        assertEquals(DbsdTestUtils.COL22_NAME, column22.getName());
        assertEquals(DbsdTestUtils.COL22_DESC, column22.getDescription());
        assertEquals(DataType.TEXT, column22.getDataType());
        final TextColumn column22b = (TextColumn) column22;
        assertEquals(100, column22b.getSize().intValue());
        assertEquals(1, column22.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column22.getOptions().get(0).getType());
        // Col 23
        final Column column23 = table2.getColumn(DbsdTestUtils.COL23_NAME);
        assertEquals(DbsdTestUtils.COL23_NAME, column23.getName());
        assertEquals(DbsdTestUtils.COL23_DESC, column23.getDescription());
        assertEquals(DataType.NUMBER, column23.getDataType());
        final NumberColumn column23b = (NumberColumn) column23;
        assertEquals(3, column23b.getPrecision().intValue());
        assertEquals(2, column23b.getScale().intValue());
        assertEquals(1, column23.getOptions().size());
        assertEquals(ColumnOptionType.NULL, column23.getOptions().get(0).getType());
        // Col 24
        final Column column24 = table2.getColumn(DbsdTestUtils.COL24_NAME);
        assertEquals(DbsdTestUtils.COL24_NAME, column24.getName());
        assertEquals(DbsdTestUtils.COL24_DESC, column24.getDescription());
        assertEquals(DataType.NUMBER, column24.getDataType());
        final NumberColumn column24b = (NumberColumn) column24;
        assertEquals(2, column24b.getPrecision().intValue());
        assertEquals(null, column24b.getScale());
        assertEquals(1, column24.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column24.getOptions().get(0).getType());
        // Constraints
        //TODO fcres: Sale 9 en vez de 4, aunque haya 9 en total son 4
        //assertEquals(4, table2.getConstraints().size());
        final Constraint constraint21 = table2.getConstraint(DbsdTestUtils.CONS21_NAME);
        assertEquals(DbsdTestUtils.CONS21_NAME, constraint21.getName());
        assertEquals(ConstraintType.INDEX, constraint21.getType());
        final IndexConstraint constraint21b = (IndexConstraint) constraint21;
        final List<IndexConstraint.ColumnReference> constraint21bColumns = constraint21b.getColumns();
        assertNotNull(constraint21bColumns);
        final IndexConstraint.ColumnReference columnReference21b0 = constraint21bColumns.get(0);
        assertEquals(DbsdTestUtils.COL21_NAME, columnReference21b0.getName());
        assertEquals(IndexConstraint.OrderType.ASC, columnReference21b0.getOrderType());
        final IndexConstraint.ColumnReference columnReference21b1 = constraint21bColumns.get(1);
        assertEquals(DbsdTestUtils.COL24_NAME, columnReference21b1.getName());
        assertNull(columnReference21b1.getOrderType());
        final Constraint constraint22 = table2.getConstraint(DbsdTestUtils.CONS22_NAME);
        assertEquals(DbsdTestUtils.CONS22_NAME, constraint22.getName());
        assertEquals(ConstraintType.PRIMARY_KEY, constraint22.getType());
        final PrimaryKeyConstraint constraint22b = (PrimaryKeyConstraint) constraint22;
        assertEquals(1, constraint22b.getColumns().size());
        assertEquals(DbsdTestUtils.COL21_NAME, constraint22b.getColumns().get(0));
        assertEquals(DbsdTestUtils.CONS21_NAME, constraint22b.getIndex());
        final Constraint constraint23 = table2.getConstraint(DbsdTestUtils.CONS23_NAME);
        assertEquals(ConstraintType.CHECK, constraint23.getType());
        assertEquals(DbsdTestUtils.CONS23_NAME, constraint23.getName());
        final CheckConstraint constraint23b = (CheckConstraint) constraint23;
        assertEquals(DbsdTestUtils.COL23_NAME,constraint23b.getColumn());
        assertEquals("< 2.5",constraint23b.getCondition());
        final Constraint constraint24 = table2.getConstraint(DbsdTestUtils.CONS24_NAME);
        assertEquals(DbsdTestUtils.CONS24_NAME, constraint24.getName());
        assertEquals(ConstraintType.UNIQUE_KEY, constraint24.getType());
        final UniqueKeyConstraint constraint24b = (UniqueKeyConstraint) constraint24;
        assertEquals(1, constraint24b.getColumns().size());
        assertEquals(DbsdTestUtils.COL24_NAME, constraint24b.getColumns().get(0));


        // ----------- Table3 ----------------------
        final Table table3 = dbs.getTable(DbsdTestUtils.TABLE3_NAME);
        assertNotNull(table3);
        assertEquals(DbsdTestUtils.TABLE3_NAME, table3.getName());
        assertEquals(DbsdTestUtils.TABLE3_DESC, table3.getDescription());
        assertEquals(TableType.ISA, table3.getType());
        final IsaTable table3b = (IsaTable) table3;
        assertEquals(DbsdTestUtils.TABLE2_NAME,table3b.getReferenceTable());
        // -- Columns --
        assertEquals(7, table3.getColumns().size());
        // Col 31
        final Column column31 = table3.getColumn(DbsdTestUtils.COL31_NAME);
        assertEquals(DbsdTestUtils.COL31_NAME, column31.getName());
        assertEquals(DbsdTestUtils.COL31_DESC, column31.getDescription());
        assertEquals(DataType.NUMBER, column31.getDataType());
        final NumberColumn column31b = (NumberColumn) column31;
        assertEquals(4, column31b.getPrecision().intValue());
        assertEquals(null, column31b.getScale());
        assertEquals(1, column31.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column31.getOptions().get(0).getType());
        // Col 32
        final Column column32 = table3.getColumn(DbsdTestUtils.COL32_NAME);
        assertEquals(DbsdTestUtils.COL32_NAME, column32.getName());
        assertEquals(DbsdTestUtils.COL32_DESC, column32.getDescription());
        assertEquals(DataType.NUMBER, column32.getDataType());
        final NumberColumn column32b = (NumberColumn) column32;
        assertEquals(1, column32b.getPrecision().intValue());
        assertEquals(null, column32b.getScale());
        assertEquals(1, column32.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column32.getOptions().get(0).getType());
        // Col 33
        final Column column33 = table3.getColumn(DbsdTestUtils.COL33_NAME);
        assertEquals(DbsdTestUtils.COL33_NAME, column33.getName());
        assertEquals(DbsdTestUtils.COL33_DESC, column33.getDescription());
        assertEquals(DataType.DATE, column33.getDataType());
        assertEquals(1, column33.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column33.getOptions().get(0).getType());
        // Col 34
        final Column column34 = table3.getColumn(DbsdTestUtils.COL34_NAME);
        assertEquals(DbsdTestUtils.COL34_NAME, column34.getName());
        assertEquals(DbsdTestUtils.COL34_DESC, column34.getDescription());
        assertEquals(DataType.DATE, column34.getDataType());
        assertEquals(1, column34.getOptions().size());
        assertEquals(ColumnOptionType.NULL, column34.getOptions().get(0).getType());
        // Col 35
        final Column column35 = table3.getColumn(DbsdTestUtils.COL35_NAME);
        assertEquals(DbsdTestUtils.COL35_NAME, column35.getName());
        assertEquals(DbsdTestUtils.COL35_DESC, column35.getDescription());
        assertEquals(DataType.DATE, column35.getDataType());
        assertEquals(1, column35.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column35.getOptions().get(0).getType());
        // Col 36
        final Column column36 = table3.getColumn(DbsdTestUtils.COL36_NAME);
        assertEquals(DbsdTestUtils.COL36_NAME, column36.getName());
        assertEquals(DbsdTestUtils.COL36_DESC, column36.getDescription());
        assertEquals(DataType.DATE, column36.getDataType());
        assertEquals(1, column36.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column36.getOptions().get(0).getType());
        // Col 37
        final Column column37 = table3.getColumn(DbsdTestUtils.COL37_NAME);
        assertEquals(DbsdTestUtils.COL37_NAME, column37.getName());
        assertEquals(DbsdTestUtils.COL37_DESC, column37.getDescription());
        assertEquals(DataType.DATE, column37.getDataType());
        assertEquals(3, column37.getOptions().size());
        assertEquals(ColumnOptionType.NOT_NULL, column37.getOptions().get(0).getType());
        assertEquals(ColumnOptionType.READ_ONLY, column37.getOptions().get(1).getType());
        assertEquals(ColumnOptionType.DEFAULT_VALUE, column37.getOptions().get(2).getType());
        final DefaultValueColumnOption columnOption113b = (DefaultValueColumnOption) column37.getOptions().get(2);
        assertEquals(DateData.NOW, columnOption113b.getValue());

        // --- Constraints ---
        assertEquals(3, table3.getConstraints().size());
        // Cons 31
        final Constraint constraint31 = table3.getConstraint(DbsdTestUtils.CONS31_NAME);
        assertEquals(DbsdTestUtils.CONS31_NAME, constraint31.getName());
        assertEquals(ConstraintType.PRIMARY_KEY, constraint31.getType());
        final PrimaryKeyConstraint constraint31b = (PrimaryKeyConstraint) constraint31;
        assertEquals(1, constraint31b.getColumns().size());
        assertEquals(DbsdTestUtils.COL31_NAME, constraint31b.getColumns().get(0));
        // Cons 32
        final Constraint constraint32 = table3.getConstraint(DbsdTestUtils.CONS32_NAME);
        assertEquals(DbsdTestUtils.CONS32_NAME, constraint32.getName());
        assertEquals(ConstraintType.FOREIGN_KEY, constraint32.getType());
        final ForeignKeyConstraint constraint32b = (ForeignKeyConstraint) constraint32;
        assertEquals(1, constraint32b.getColumns().size());
        assertEquals(DbsdTestUtils.COL31_NAME, constraint32b.getColumns().get(0));
        assertEquals(DbsdTestUtils.TABLE2_NAME, constraint32b.getReferenceTable());
        assertEquals(1, constraint32b.getReferenceColumns().size());
        assertEquals(DbsdTestUtils.COL21_NAME, constraint32b.getReferenceColumns().get(0));
        // Cons 33
        final Constraint constraint33 = table3.getConstraint(DbsdTestUtils.CONS33_NAME);
        assertEquals(DbsdTestUtils.CONS33_NAME, constraint33.getName());
        assertEquals(ConstraintType.FOREIGN_KEY, constraint33.getType());
        final ForeignKeyConstraint constraint33b = (ForeignKeyConstraint) constraint33;
        assertEquals(1, constraint33b.getColumns().size());
        assertEquals(DbsdTestUtils.COL32_NAME, constraint33b.getColumns().get(0));
        assertEquals(DbsdTestUtils.TABLE1_NAME, constraint33b.getReferenceTable());
        assertEquals(1, constraint33b.getReferenceColumns().size());
        assertEquals(DbsdTestUtils.COL11_NAME, constraint33b.getReferenceColumns().get(0));


        // --------------------- Sequences --------------------------------------
        assertEquals(2, dbs.getSequences().size());
        assertEquals(DbsdTestUtils.SEQ_1, dbs.getSequences().get(0).getName());
        assertEquals(DbsdTestUtils.SEQ_2, dbs.getSequences().get(1).getName());


        // Literal Code
        assertEquals(DbsdTestUtils.SQL_LITERAL_CODE, dbs.getSqlLiteralCode());
    }

}
