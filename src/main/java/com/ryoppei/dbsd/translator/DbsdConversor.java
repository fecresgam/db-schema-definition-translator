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

import com.ryoppei.dbsd.translator.dto.DataBaseSchema;

import java.io.IOException;

/**
 * The Dbsd Conversor
 */
public class DbsdConversor
{


    private static final String HELP_COMMANDLINE_ARGUMENT = "-h";
    private static final String OUTPUT_FORMAT_COMMANDLINE_ARGUMENT = "-o";

    /**
     * The main entry point.
     *
     * @param args the args.
     */
    public static void main(String[] args)
    {
        OutputFormat outputFormat;
        String inputFileName;
        String outputFolder;
        String outputFilePrefix;

        if ((args == null) ||
                (args.length == 0) ||
                (HELP_COMMANDLINE_ARGUMENT.equals(args[0])))
        {
            SystemMessagePrinter.printHelpInformation();
        }
        else
        {
            if ((args.length == 5) &&
                    OUTPUT_FORMAT_COMMANDLINE_ARGUMENT.equals(args[0]))
            {
                outputFormat = OutputFormat.getEnum(args[1]);
                inputFileName = args[2];
                outputFolder = args[3];
                outputFilePrefix = args[4];

                if (outputFormat != null &&
                        inputFileName != null &&
                        outputFolder != null &&
                        outputFilePrefix != null)
                {
                    final DbsdConversor dbsdConversor = new DbsdConversor();
                    dbsdConversor.convert(outputFormat,
                                          inputFileName,
                                          outputFolder,
                                          outputFilePrefix);
                }
            }


        }
    }

    /**
     * Converts the given file to the output format ad write it to a group
     * of files in the given folder.
     *
     * @param outputFormat such information.
     * @param inputFileName such information.
     * @param outputFolder such information.
     * @param outputFilePrefix such information.
     */
    public void convert(
            final OutputFormat outputFormat,
            final String inputFileName,
            final String outputFolder,
            final String outputFilePrefix)
    {
        DataBaseSchema dbs = null;

        //Read the file
        final DbsdReader reader = new DbsdReader();

        try
        {
            dbs = reader.compile(inputFileName);
        } catch (IOException e)
        {
            System.out.println("Error reading input file: '" + inputFileName + "'");
        }


        //Validate & convert
        if (dbs != null)
        {
            final DbsdSemanticValidator semanticValidator = new DbsdSemanticValidator(dbs);

            if (semanticValidator.isValid())
            {
                final DbsGeneratorFactory dbsGeneratorFactory =
                        DbsGeneratorFactory.getInstance();

                final DbsGenerator dbsGenerator =
                        dbsGeneratorFactory.createDbsGenerator(outputFormat);

                dbsGenerator.generateOutput(dbs, outputFolder, outputFilePrefix);
            }
        }
    }
}
