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
import com.ryoppei.dbsd.translator.dto.Sequence;
import com.ryoppei.dbsd.translator.dto.StaticTable;
import com.ryoppei.dbsd.translator.dto.Table;
import com.ryoppei.dbsd.translator.dto.TableType;
import com.ryoppei.dbsd.translator.dto.columns.Column;
import com.ryoppei.dbsd.translator.dto.columns.ColumnOption;
import com.ryoppei.dbsd.translator.dto.columns.CommonColumn;
import com.ryoppei.dbsd.translator.dto.columns.CommonColumnOption;
import com.ryoppei.dbsd.translator.dto.columns.DefaultValueColumnOption;
import com.ryoppei.dbsd.translator.dto.columns.NumberColumn;
import com.ryoppei.dbsd.translator.dto.columns.TextColumn;
import com.ryoppei.dbsd.translator.dto.constraints.CheckConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.Constraint;
import com.ryoppei.dbsd.translator.dto.constraints.ForeignKeyConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.IndexConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.PrimaryKeyConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.UniqueKeyConstraint;
import com.ryoppei.dbsd.translator.dto.data.BooleanData;
import com.ryoppei.dbsd.translator.dto.data.Data;
import com.ryoppei.dbsd.translator.dto.data.DataType;
import com.ryoppei.dbsd.translator.dto.data.DateData;
import com.ryoppei.dbsd.translator.dto.data.NumberData;
import com.ryoppei.dbsd.translator.dto.data.TextData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbsdTestUtils
{
    // Dbsd Constants
    static final String OWNER = "T_OWNER";
    static final String USER = "T_USER";
    static final String USER_ROLE = "ROLE";
    static final String DEFAULT_TABLE_TBSPC = "DATA";
    static final String DEFAULT_INDEX_TBLSPC = "INDX";
    // Table 1 constants
    static final String TABLE1_NAME = "tipos_empleado";
    static final String TABLE1_DESC = "Los tipos de empleados.";
    static final String COL11_NAME = "id_tipo_empleado";
    static final String COL12_NAME = "nombre";
    static final String COL11_DESC = "Identificador unico de empleado";
    static final String COL12_DESC = "El nombre de tipo de empleado";
    static final String CONS11_NAME = "pk_tipo_empleado";
    static final String TABLE_DATA112 = "Secretario";
    static final String TABLE_DATA122 = "Programador";
    static final String TABLE_DATA132 = "Directivo";
    // Table 2 constants
    static final String TABLE2_NAME = "personas";
    static final String TABLE2_DESC = "Las personas";
    static final String COL21_NAME = "id_persona";
    static final String COL22_NAME = "nombre_completo";
    static final String COL23_NAME = "altura_en_metros";
    static final String COL24_NAME = "edad";
    static final String COL21_DESC = "Identificador unico de persona";
    static final String COL22_DESC = "El nombe y los dos apellidos";
    static final String COL23_DESC = "La altura de la persona expresada en metros, debe ser menor de 2,5 metros para que entre en la oficina";
    static final String COL24_DESC = "La edad. No se permite dos personas con la misma edad por supersticion";
    static final String CONS21_NAME = "in_id_persona";
    static final String CONS22_NAME = "pk_persona";
    static final String CONS23_NAME = "chk_altura";
    static final String CONS24_NAME = "uk_edad";
    // Table 3 constants
    static final String TABLE3_NAME = "empleados";
    static final String TABLE3_DESC = "Los empleados.";
    static final String COL31_NAME = "id_persona";
    static final String COL31_DESC = "Identificador unico de persona";
    static final String COL32_NAME = "id_tipo_empleado";
    static final String COL32_DESC = "Identificador unico de tipo de empleado";
    static final String COL13_NAME = "es_jefe";
    static final String COL13_DESC = "Define si tiene alguien a su cargo";
    static final String COL33_NAME = "fecha_inicio_ss";
    static final String COL33_DESC = "La fecha en la que se le dio de alta en la SS";
    static final String COL34_NAME = "fecha_fin_ss";
    static final String COL34_DESC = "La fecha en la que se le dio de baja en la SS";
    static final String COL35_NAME = "hora_entrada";
    static final String COL35_DESC = "La hora en la que inicia la jornada laboral";
    static final String COL36_NAME = "hora_salida";
    static final String COL36_DESC = "La hora en la que finaliza la jornada laboral";
    static final String COL37_NAME = "ultima_modificacion";
    static final String COL37_DESC = "Fecha y hora de la ultima modificacion de este registro";
    static final String CONS31_NAME = "pk_empleado";
    static final String CONS32_NAME = "fk_empleado_persona";
    static final String CONS33_NAME = "fk_tipo_empleado";
    // Sequence constants
    static final String SEQ_1 = "seq_id_persona";
    static final String SEQ_2 = "seq_id_empleado";
    // Literal code constants
    static final String SQL_LITERAL_CODE = "\n" +
            "CREATE OR REPLACE TRIGGER tr_empleados_fecha_modificacion before\n" +
            "  UPDATE ON empleados FOR EACH row BEGIN :new.fecha_modificacion:=SYSDATE;\n" +
            "END tr_empleados_fecha_modificacion;\n";


    // Singleton implementation.
    /**
     * Singleton implemented to avoid the double-checked locking idiom.
     */
    private static final class DbsdTestUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        static final DbsdTestUtils SINGLETON = new DbsdTestUtils();
    }

    /**
     * Retrieves a EmailUtils instance.
     *
     * @return such instance.
     */
    public static DbsdTestUtils getInstance()
    {

        return DbsdTestUtilsSingletonContainer.SINGLETON;
    }
    /**
     * Generates the DataBaseSchema to be user in test.
     *
     * @return such information.
     */
    public DataBaseSchema generateValidTestDbs()
    {
        List<Table> tables = new ArrayList<Table>();
        tables.add(getTable1());
        tables.add(getTable2());
        tables.add(getTable3());

        List<Sequence> sequences = new ArrayList<Sequence>();
        sequences.add(new Sequence(SEQ_1));
        sequences.add(new Sequence(SEQ_2));

        return new CommonDataBaseSchema(
                OWNER,
                USER,
                USER_ROLE,
                DEFAULT_TABLE_TBSPC,
                DEFAULT_INDEX_TBLSPC,
                DBMSType.ORACLE,
                tables,
                sequences,
                SQL_LITERAL_CODE);
    }

    private Table getTable1()
    {
        List<Column> columns = new ArrayList<Column>();
        final NumberColumn col11 = new NumberColumn(COL11_NAME, 2, 0, getNotNullOptionAsList(), COL11_DESC);
        final TextColumn col12 = new TextColumn(COL12_NAME, 50, getNotNullOptionAsList(), COL12_DESC);
        final CommonColumn col13 = new CommonColumn(COL13_NAME, DataType.BOOLEAN, getNotNullOptionAsList(), COL13_DESC);

        columns.add(col11);
        columns.add(col12);
        columns.add(col13);

        List<String> col_cons_111 = new ArrayList<String>();
        col_cons_111.add(COL11_NAME);
        final PrimaryKeyConstraint cons11 = new PrimaryKeyConstraint(CONS11_NAME, col_cons_111, null);
        List<Constraint> constraints = new ArrayList<Constraint>();
        constraints.add(cons11);

        List<List<Data>> dataRows = new ArrayList<List<Data>>();
        List<Data> dataRow1 = new ArrayList<Data>();
        dataRow1.add(new NumberData("1"));
        dataRow1.add(new TextData(TABLE_DATA112));
        dataRow1.add(BooleanData.FALSE);
        List<Data> dataRow2 = new ArrayList<Data>();
        dataRow2.add(new NumberData("2"));
        dataRow2.add(new TextData(TABLE_DATA122));
        dataRow2.add(BooleanData.FALSE);
        List<Data> dataRow3 = new ArrayList<Data>();
        dataRow3.add(new NumberData("3"));
        dataRow3.add(new TextData(TABLE_DATA132));
        dataRow3.add(BooleanData.TRUE);
        dataRows.add(dataRow1);
        dataRows.add(dataRow2);
        dataRows.add(dataRow3);

        return new StaticTable(
                TABLE1_NAME,
                COL12_NAME,
                TABLE1_DESC,
                columns,
                constraints,
                dataRows);
    }

    @SuppressWarnings("unchecked")
    private Table getTable2()
    {
        List<Column> columns = new ArrayList<Column>();
        final NumberColumn col21 = new NumberColumn(COL21_NAME, 4, 0, getNotNullOptionAsList(), COL21_DESC);
        final TextColumn   col22 = new TextColumn(COL22_NAME, 100, getNullOptionAsList(), COL22_DESC);
        final NumberColumn col23 = new NumberColumn(COL23_NAME, 3, 2, getNotNullOptionAsList(), COL23_DESC);
        final NumberColumn col24 = new NumberColumn(COL24_NAME, 2, 0, getNotNullOptionAsList(), COL24_DESC);
        columns.add(col21);
        columns.add(col22);
        columns.add(col23);
        columns.add(col24);


        final IndexConstraint cons21 = new IndexConstraint(CONS21_NAME, COL21_NAME);

        List<String> col_cons_221 = new ArrayList<String>();
        col_cons_221.add(COL22_NAME);
        final PrimaryKeyConstraint cons22 = new PrimaryKeyConstraint(CONS22_NAME, col_cons_221, CONS21_NAME);

        final CheckConstraint cons23 = new CheckConstraint(CONS23_NAME, COL23_NAME, "< 2.5");
        List<String> col_cons_241 = new ArrayList<String>();
        col_cons_241.add(COL24_NAME);
        final UniqueKeyConstraint cons24 = new UniqueKeyConstraint(CONS24_NAME, col_cons_241);


        List<Constraint> constraints = new ArrayList<Constraint>();
        constraints.add(cons21);
        constraints.add(cons22);
        constraints.add(cons23);
        constraints.add(cons24);


        return new CommonTable(
                TABLE2_NAME,
                TableType.COMMON,
                TABLE2_DESC,
                columns,
                constraints,
                Collections.EMPTY_LIST);
    }

    @SuppressWarnings("unchecked")
    public Table getTable3()
    {

        final ArrayList<ColumnOption> column38Options = new ArrayList<ColumnOption>();
        column38Options.add(CommonColumnOption.NOT_NULL);
        column38Options.add(CommonColumnOption.READ_ONLY);
        column38Options.add(new DefaultValueColumnOption(DateData.NOW));

        List<Column> columns = new ArrayList<Column>();
        final NumberColumn col31 = new NumberColumn(COL31_NAME, 4, 0, getNotNullOptionAsList(), COL31_DESC);
        final NumberColumn col32 = new NumberColumn(COL32_NAME, 1, 0, getNotNullOptionAsList(), COL32_DESC);
        final CommonColumn col34 = new CommonColumn(COL33_NAME, DataType.DATE, getNotNullOptionAsList(), COL33_DESC);
        final CommonColumn col35 = new CommonColumn(COL34_NAME, DataType.DATE, getNullOptionAsList(), COL34_DESC);
        final CommonColumn col36 = new CommonColumn(COL35_NAME, DataType.TIME, getNotNullOptionAsList(), COL35_DESC);
        final CommonColumn col37 = new CommonColumn(COL36_NAME, DataType.TIME, getNotNullOptionAsList(), COL36_DESC);
        final CommonColumn col38 = new CommonColumn(COL37_NAME, DataType.DATE_AND_TIME, column38Options, COL37_DESC);
        columns.add(col31);
        columns.add(col32);
        columns.add(col34);
        columns.add(col35);
        columns.add(col36);
        columns.add(col37);
        columns.add(col38);


        List<String> col_cons_311 = new ArrayList<String>();
        col_cons_311.add(COL31_NAME);
        final PrimaryKeyConstraint cons31 = new PrimaryKeyConstraint(CONS31_NAME, col_cons_311, null);

        List<String> col_cons_321 = new ArrayList<String>();
        col_cons_321.add(COL31_NAME);
        List<String> col_cons_322 = new ArrayList<String>();
        col_cons_322.add(COL21_NAME);
        final ForeignKeyConstraint cons32 = new ForeignKeyConstraint(CONS32_NAME, col_cons_321, TABLE2_NAME,col_cons_322);

        List<String> col_cons_331 = new ArrayList<String>();
        col_cons_331.add(COL32_NAME);
        List<String> col_cons_332 = new ArrayList<String>();
        col_cons_332.add(COL11_NAME);
        final ForeignKeyConstraint cons33 = new ForeignKeyConstraint(CONS32_NAME, col_cons_331, TABLE1_NAME,col_cons_332);

        List<Constraint> constraints = new ArrayList<Constraint>();
        constraints.add(cons31);
        constraints.add(cons32);
        constraints.add(cons33);

        return new IsaTable(
                TABLE3_NAME,
                TABLE2_NAME,
                TABLE3_DESC,
                columns,
                constraints,
                Collections.EMPTY_LIST);
    }

    private List<ColumnOption> getNullOptionAsList()
    {
        final ArrayList<ColumnOption> columnOptions = new ArrayList<ColumnOption>();
        columnOptions.add(CommonColumnOption.NULL);

        return columnOptions;
    }

    private List<ColumnOption> getNotNullOptionAsList()
    {
        final ArrayList<ColumnOption> columnOptions = new ArrayList<ColumnOption>();
        columnOptions.add(CommonColumnOption.NOT_NULL);

        return columnOptions;
    }


}