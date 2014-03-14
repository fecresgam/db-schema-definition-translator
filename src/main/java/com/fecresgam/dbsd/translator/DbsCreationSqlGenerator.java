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

import java.util.ArrayList;
import java.util.List;

/**
 * The Dbs Creation-Sql Generator
 */
public class DbsCreationSqlGenerator extends AbstractDbsTemplateBasedGenerator
{
    private static final String DBS_SQL_CREATION_TEMPLATE_FILE_NAME_50 =
            "50-schemaCreate";
    private static final String DBS_SQL_CREATION_TEMPLATE_FILE_NAME_60 =
            "60-specialPostStatements";
    private static final String DBS_SQL_CREATION_TEMPLATE_FILE_NAME_80 =
            "80-tableInserts";
    private static final String DBS_SQL_CREATION_TEMPLATE_FILE_NAME_90 =
            "90-constraintsCreate";
    private static final String DBS_SQL_CREATION_TEMPLATE_FILE_NAME_100 =
            "100-grants";
    private static final String DBS_SQL_CREATION_TEMPLATE_FILE_NAME_110 =
            "110-synonyms";

    private static final String TEMPLATES_FOLDER =
            "/sql_creation_template/";
    private static final String FILE_EXTENSION =
            ".sql";


    private static List<String> fileNameList;

    static {
        fileNameList = new ArrayList<String>();
        fileNameList.add(DBS_SQL_CREATION_TEMPLATE_FILE_NAME_50);
        fileNameList.add(DBS_SQL_CREATION_TEMPLATE_FILE_NAME_60);
        fileNameList.add(DBS_SQL_CREATION_TEMPLATE_FILE_NAME_80);
        fileNameList.add(DBS_SQL_CREATION_TEMPLATE_FILE_NAME_90);
        fileNameList.add(DBS_SQL_CREATION_TEMPLATE_FILE_NAME_100);
        fileNameList.add(DBS_SQL_CREATION_TEMPLATE_FILE_NAME_110);
    }

    @Override
    protected Iterable<String> getBaseFileNames()
    {
        return fileNameList;
    }

    @Override
    protected String getOutputFileExtension()
    {
        return FILE_EXTENSION;
    }

    @Override
    protected String getTemplatesFolder()
    {
        return TEMPLATES_FOLDER;
    }
}
