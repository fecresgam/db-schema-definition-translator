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
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * The Abstract Dbs Template-Based Generator
 */
abstract public class AbstractDbsTemplateBasedGenerator implements DbsGenerator
{

    private static final String DBS_TEMPLATE_ATTRIBUTE = "dbs";
    private static final String DBS_ROOT_RULE = "dbs_root_rule";
    private static final String TEMPLATE_FILE_EXTENSION = ".stg";


    @Override
    public void generateOutput(
            final DataBaseSchema dbs,
            final String outputFolder,
            final String outputFilePrefix)
    {

        for (final String baseFileName : getBaseFileNames())
        {

            final String fileContent =
                    processTemplate(dbs, getTemplateFileName(baseFileName));

            saveToFile(
                    fileContent,
                    getoutputFileName(
                            outputFilePrefix,
                            baseFileName,
                            outputFolder));
        }
    }

    /**
     * Process the given file template ith the given dbs and writes it to the file system.
     *
     * @param dbs such information.
     * @param templateFileName such information.
     * @return such information.
     */
    private String processTemplate(
            final DataBaseSchema dbs,
            final String templateFileName)
    {
        final InputStream resourceAsStream =
                getClass().getResourceAsStream(templateFileName);

        final InputStreamReader inputStreamReader =
                new InputStreamReader(
                        resourceAsStream,
                        Charset.defaultCharset());

        final StringTemplateGroup group =
                new StringTemplateGroup(
                        inputStreamReader,
                        AngleBracketTemplateLexer.class);

        final StringTemplate dbsTemplate =
                group.getInstanceOf(DBS_ROOT_RULE);


        dbsTemplate.setAttribute(DBS_TEMPLATE_ATTRIBUTE, dbs);

        return dbsTemplate.toString();
    }

    /**
     * Save a file in the file system.
     *
     * @param fileContent such information.
     * @param fileUri such information.
     */
    private static void saveToFile(
            final String fileContent,
            final String fileUri) {

        try
        {
            final FileWriter fileWriter = new FileWriter(fileUri);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(fileContent);
            bw.close();
        } catch (IOException e)
        {
            SystemMessagePrinter.printSystemMessage(SystemMessagePrinter.ERROR_WRITING_FILE, fileUri);
        }

    }


    /**
     * Retrieves the template file name.
     *
     * @param baseFileName such information.
     * @return such information.
     */
    private String getTemplateFileName(final String baseFileName)
    {
        return getTemplatesFolder() +
                baseFileName +
                TEMPLATE_FILE_EXTENSION;
    }


    /**
     * Retrieves the output file name.
     *
     * @param outputFilePrefix such information.
     * @param baseFileName such information.
     * @param outputFolder such information.
     * @return such information.
     */
    private String getoutputFileName(
            final String outputFilePrefix,
            final String baseFileName,
            final String outputFolder)
    {
        return outputFolder +
                "/" +
                outputFilePrefix +
                "-" +
                baseFileName +
                getOutputFileExtension();
    }

    /**
     * Retrieves the base file names.
     *
     * @return such information.
     */
    abstract protected Iterable<String> getBaseFileNames();

    /**
     * Retrieves the output file extension.
     *
     * @return such information.
     */
    abstract protected String getOutputFileExtension();

    /**
     * Retrieves the templates folder.
     *
     * @return such information.
     */
    abstract protected String getTemplatesFolder();
}
