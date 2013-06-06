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

package com.ryoppei.dbsd.translator;

import com.ryoppei.dbsd.translator.dto.CommonDataBaseSchema;
import com.ryoppei.dbsd.translator.dto.CommonTable;
import com.ryoppei.dbsd.translator.dto.DBMSType;
import com.ryoppei.dbsd.translator.dto.DataBaseSchema;
import com.ryoppei.dbsd.translator.dto.IsaTable;
import com.ryoppei.dbsd.translator.dto.StaticTable;
import com.ryoppei.dbsd.translator.dto.Table;
import com.ryoppei.dbsd.translator.dto.TableType;
import com.ryoppei.dbsd.translator.dto.columns.Column;
import com.ryoppei.dbsd.translator.dto.columns.NumberColumn;
import com.ryoppei.dbsd.translator.dto.columns.TextColumn;
import com.ryoppei.dbsd.translator.dto.constraints.CheckConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.Constraint;
import com.ryoppei.dbsd.translator.dto.constraints.ForeignKeyConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.IndexConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.PrimaryKeyConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.UniqueKeyConstraint;
import com.ryoppei.dbsd.translator.dto.data.Data;
import com.ryoppei.dbsd.translator.dto.data.NumberData;
import com.ryoppei.dbsd.translator.dto.data.TextData;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Tests {@link com.ryoppei.dbsd.translator.DbsdSemanticValidatorTest}.
 *
 * @author fcres
 *
 */
public class DbsdSemanticValidatorTest
{

    @Test
    public void testValidDbs()
            throws IOException
    {
        final DataBaseSchema dbs =
                DbsdTestUtils.getInstance().generateValidTestDbs();

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(dbs);

        assertEquals(true, semanticValidator.isValid());
    }

    @Test
    public void testISAReferenceTableNotExists()
            throws IOException
    {
        @SuppressWarnings("unchecked")
        final Table table = new IsaTable(
                DbsdTestUtils.TABLE3_NAME,
                DbsdTestUtils.TABLE2_NAME,
                DbsdTestUtils.TABLE3_DESC,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testStaticReferenceColumnNotExists()
            throws IOException
    {
        @SuppressWarnings("unchecked")
        final Table table =  new StaticTable(
                DbsdTestUtils.TABLE1_NAME,
                DbsdTestUtils.COL12_NAME,
                DbsdTestUtils.TABLE1_DESC,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testIndexColumnNotExists()
            throws IOException
    {
        final IndexConstraint cons21 = new IndexConstraint(DbsdTestUtils.CONS21_NAME, DbsdTestUtils.COL21_NAME);
        List<Constraint> constraints = new ArrayList<Constraint>();
        constraints.add(cons21);

        @SuppressWarnings("unchecked")
        final Table table =  new CommonTable(
                DbsdTestUtils.TABLE2_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE2_DESC,
                Collections.EMPTY_LIST,
                constraints,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testCheckConstraintColumnNotExists()
            throws IOException
    {
        final CheckConstraint cons21 = new CheckConstraint(DbsdTestUtils.CONS23_NAME, DbsdTestUtils.COL23_NAME, "< 2");
        List<Constraint> constraints = new ArrayList<Constraint>();
        constraints.add(cons21);

        @SuppressWarnings("unchecked")
        final Table table =  new CommonTable(
                DbsdTestUtils.TABLE2_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE2_DESC,
                Collections.EMPTY_LIST,
                constraints,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testPKColumnsNotExists()
            throws IOException
    {
        List<String> col_cons_111 = new ArrayList<String>();
        col_cons_111.add(DbsdTestUtils.COL11_NAME);
        final PrimaryKeyConstraint cons11 = new PrimaryKeyConstraint(DbsdTestUtils.CONS11_NAME, col_cons_111, null);
        List<Constraint> constraints = new ArrayList<Constraint>();
        constraints.add(cons11);

        @SuppressWarnings("unchecked")
        final Table table = new CommonTable(
                DbsdTestUtils.TABLE1_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE1_DESC,
                Collections.EMPTY_LIST,
                constraints,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testPKIndexNotExists()
            throws IOException
    {
        List<Column> columns = new ArrayList<Column>();
        final NumberColumn col11 = new NumberColumn(DbsdTestUtils.COL11_NAME, 2, 0, null, DbsdTestUtils.COL11_DESC);
        columns.add(col11);
        List<String> col_cons_111 = new ArrayList<String>();
        col_cons_111.add(DbsdTestUtils.COL11_NAME);
        final PrimaryKeyConstraint cons11 =
                new PrimaryKeyConstraint(DbsdTestUtils.CONS11_NAME, col_cons_111, DbsdTestUtils.CONS21_NAME);
        List<Constraint> constraints = new ArrayList<Constraint>();
        constraints.add(cons11);

        @SuppressWarnings("unchecked")
        final Table table = new CommonTable(
                DbsdTestUtils.TABLE1_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE1_DESC,
                columns,
                constraints,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testUKColumnsNotExists()
            throws IOException
    {
        List<String> col_cons_241 = new ArrayList<String>();
        col_cons_241.add(DbsdTestUtils.COL24_NAME);
        final UniqueKeyConstraint cons24 = new UniqueKeyConstraint(DbsdTestUtils.CONS24_NAME, col_cons_241);
        List<Constraint> constraints = new ArrayList<Constraint>();
        constraints.add(cons24);

        @SuppressWarnings("unchecked")
        final Table table =  new CommonTable(
                DbsdTestUtils.TABLE2_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE2_DESC,
                Collections.EMPTY_LIST,
                constraints,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testFKColumnsNotExists()
            throws IOException
    {
        // Fake table 3
        List<String> col_cons_331 = new ArrayList<String>();
        col_cons_331.add(DbsdTestUtils.COL32_NAME);
        List<String> col_cons_332 = new ArrayList<String>();
        col_cons_332.add(DbsdTestUtils.COL11_NAME);
        final ForeignKeyConstraint cons33 = new ForeignKeyConstraint(DbsdTestUtils.CONS32_NAME, col_cons_331, DbsdTestUtils.TABLE1_NAME,col_cons_332);

        List<Constraint> constraints3 = new ArrayList<Constraint>();
        constraints3.add(cons33);

        @SuppressWarnings("unchecked")
        Table table3 = new CommonTable(
                DbsdTestUtils.TABLE3_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE3_DESC,
                Collections.EMPTY_LIST,
                constraints3,
                Collections.EMPTY_LIST);

        // Fake table 1
        List<Column> columns1 = new ArrayList<Column>();
        final NumberColumn col11 = new NumberColumn(DbsdTestUtils.COL11_NAME, 2, 0, null, DbsdTestUtils.COL11_DESC);
        columns1.add(col11);

        @SuppressWarnings("unchecked")
        final Table table1 =  new CommonTable(
                DbsdTestUtils.TABLE1_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE1_DESC,
                columns1,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table1, table3));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testFKReferenceTableNotExists()
            throws IOException
    {
        // Fake table 3
        List<Column> columns3 = new ArrayList<Column>();
        final NumberColumn col32 = new NumberColumn(DbsdTestUtils.COL32_NAME, 1, 0, null, DbsdTestUtils.COL32_DESC);
        columns3.add(col32);
        List<String> col_cons_331 = new ArrayList<String>();
        col_cons_331.add(DbsdTestUtils.COL32_NAME);
        List<String> col_cons_332 = new ArrayList<String>();
        col_cons_332.add(DbsdTestUtils.COL11_NAME);
        final ForeignKeyConstraint cons33 = new ForeignKeyConstraint(DbsdTestUtils.CONS32_NAME, col_cons_331, DbsdTestUtils.TABLE1_NAME,col_cons_332);

        List<Constraint> constraints3 = new ArrayList<Constraint>();
        constraints3.add(cons33);

        @SuppressWarnings("unchecked")
        Table table3 = new CommonTable(
                DbsdTestUtils.TABLE3_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE3_DESC,
                columns3,
                constraints3,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table3));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testFKReferenceColumnsNotExists()
            throws IOException
    {
        // Fake table 3
        List<Column> columns3 = new ArrayList<Column>();
        final NumberColumn col32 = new NumberColumn(DbsdTestUtils.COL32_NAME, 1, 0, null, DbsdTestUtils.COL32_DESC);
        columns3.add(col32);
        List<String> col_cons_331 = new ArrayList<String>();
        col_cons_331.add(DbsdTestUtils.COL32_NAME);
        List<String> col_cons_332 = new ArrayList<String>();
        col_cons_332.add(DbsdTestUtils.COL11_NAME);
        final ForeignKeyConstraint cons33 = new ForeignKeyConstraint(DbsdTestUtils.CONS32_NAME, col_cons_331, DbsdTestUtils.TABLE1_NAME,col_cons_332);

        List<Constraint> constraints3 = new ArrayList<Constraint>();
        constraints3.add(cons33);

        @SuppressWarnings("unchecked")
        Table table3 = new CommonTable(
                DbsdTestUtils.TABLE3_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE3_DESC,
                columns3,
                constraints3,
                Collections.EMPTY_LIST);

        // Fake table 1
        @SuppressWarnings("unchecked")
        final Table table1 =  new CommonTable(
                DbsdTestUtils.TABLE1_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE1_DESC,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table1, table3));

        assertEquals(false, semanticValidator.isValid());
    }

    @Test
    public void testFKColumnsDifferentSizeThanReferenceColumns()
            throws IOException
    {
        // Fake table 3
        List<Column> columns3 = new ArrayList<Column>();
        final NumberColumn col31 = new NumberColumn(DbsdTestUtils.COL31_NAME, 4, 0, null, DbsdTestUtils.COL31_DESC);
        final NumberColumn col32 = new NumberColumn(DbsdTestUtils.COL32_NAME, 1, 0, null, DbsdTestUtils.COL32_DESC);
        columns3.add(col31);
        columns3.add(col32);
        List<String> col_cons_331 = new ArrayList<String>();
        col_cons_331.add(DbsdTestUtils.COL31_NAME);
        col_cons_331.add(DbsdTestUtils.COL32_NAME);
        List<String> col_cons_332 = new ArrayList<String>();
        col_cons_332.add(DbsdTestUtils.COL11_NAME);
        final ForeignKeyConstraint cons33 = new ForeignKeyConstraint(DbsdTestUtils.CONS32_NAME, col_cons_331, DbsdTestUtils.TABLE1_NAME,col_cons_332);

        List<Constraint> constraints3 = new ArrayList<Constraint>();
        constraints3.add(cons33);

        @SuppressWarnings("unchecked")
        Table table3 = new CommonTable(
                DbsdTestUtils.TABLE3_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE3_DESC,
                columns3,
                constraints3,
                Collections.EMPTY_LIST);

        // Fake table 1
        List<Column> columns1 = new ArrayList<Column>();
        final NumberColumn col11 = new NumberColumn(DbsdTestUtils.COL11_NAME, 2, 0, null, DbsdTestUtils.COL11_DESC);
        columns1.add(col11);

        @SuppressWarnings("unchecked")
        final Table table1 =  new CommonTable(
                DbsdTestUtils.TABLE1_NAME,
                TableType.COMMON,
                DbsdTestUtils.TABLE1_DESC,
                columns1,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table1, table3));

        assertEquals(false, semanticValidator.isValid());
    }


    @Test
    public void testDataRowsSizeDifferentSizeThanTableColumnsSize()
            throws IOException
    {



        List<Column> columns = new ArrayList<Column>();
        final NumberColumn col11 = new NumberColumn(DbsdTestUtils.COL11_NAME, 2, 0, null, DbsdTestUtils.COL11_DESC);
        final TextColumn col12 = new TextColumn(DbsdTestUtils.COL12_NAME, 50, null, DbsdTestUtils.COL12_DESC);
        columns.add(col11);
        columns.add(col12);

        List<List<Data>> dataRows = new ArrayList<List<Data>>();
        List<Data> dataRow1 = new ArrayList<Data>();
        dataRow1.add(new NumberData("1"));
        dataRow1.add(new TextData(DbsdTestUtils.TABLE_DATA112));
        dataRow1.add(new TextData("anotherFakeData"));
        dataRows.add(dataRow1);

        @SuppressWarnings("unchecked")
        Table table = new StaticTable(
                DbsdTestUtils.TABLE1_NAME,
                DbsdTestUtils.COL12_NAME,
                DbsdTestUtils.TABLE1_DESC,
                columns,
                Collections.EMPTY_LIST,
                dataRows);

        final DbsdSemanticValidator semanticValidator =
                new DbsdSemanticValidator(createMinimumDbs(table));

        assertEquals(false, semanticValidator.isValid());
    }

    private CommonDataBaseSchema createMinimumDbs(final Table... rawTables)
    {

        return new CommonDataBaseSchema(
                DbsdTestUtils.OWNER,
                DbsdTestUtils.USER,
                DbsdTestUtils.USER_ROLE,
                DbsdTestUtils.DEFAULT_TABLE_TBSPC,
                DbsdTestUtils.DEFAULT_INDEX_TBLSPC,
                DBMSType.ORACLE,
                Arrays.asList(rawTables),
                null,
                null);
    }





}
