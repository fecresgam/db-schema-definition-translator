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

import java.util.ArrayList;
import java.util.List;

/**
 * The Dbs Diagram Generator
 */
public class DbsDiagramGenerator extends AbstractDbsTemplateBasedGenerator
{
    private static final String DBS_DIAGRAM_TEMPLATE_FILE_NAME = "db-schema";
    private static final String DBS_DIAGRAM_MINIMUM_TEMPLATE_FILE_NAME = "db-schema-minimum";
    private static final String TEMPLATES_FOLDER = "/diagram_templates/";
    private static final String OUTPUT_EXTENSION = ".dot";

    private static List<String> fileNameList;

    static {
        fileNameList = new ArrayList<String>();
        fileNameList.add(DBS_DIAGRAM_TEMPLATE_FILE_NAME);
        fileNameList.add(DBS_DIAGRAM_MINIMUM_TEMPLATE_FILE_NAME);
    }


    @Override
    protected Iterable<String> getBaseFileNames()
    {
        return fileNameList;
    }

    @Override
    protected String getOutputFileExtension()
    {
        return OUTPUT_EXTENSION;
    }

    @Override
    protected String getTemplatesFolder()
    {
        return TEMPLATES_FOLDER;
    }
}
