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
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

import java.io.IOException;

/**
 * The Dbsd Reader
 */
public class DbsdReader
{

    /**
     * Retrieves the dbs represented by the given input file.
     *
     * @param inputFileName such information.
     * @return such information.
     * @throws IOException
     */
    public DataBaseSchema compile(final String inputFileName) throws IOException
    {
        DataBaseSchema dataBaseSchema = null;

        final ANTLRFileStream antlrFileStream = new ANTLRFileStream(inputFileName);

        try {

            dataBaseSchema = retrieveDataBaseSchema(antlrFileStream);

        } catch (RecognitionException e) {
            throw new IllegalStateException("Recognition exception is never thrown, only declared.");
        }

        return dataBaseSchema;
    }

    /**
     * Retrieves the dbs for the given input.
     *
     * @return such information.
     */
    private DataBaseSchema retrieveDataBaseSchema(final CharStream inputStream) throws RecognitionException
    {
        final TokenStream tokens =
                new CommonTokenStream(new com.ventura24.dbsd.translator.DbsdLexer(inputStream));

        //parser generates abstract syntax tree
        final com.ventura24.dbsd.translator.DbsdParser parser =
                new com.ventura24.dbsd.translator.DbsdParser(tokens);
                parser.db_schema_definition();

        return parser.dbsd;
    }


}
