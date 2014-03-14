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

import org.junit.Test;

import java.io.IOException;


/**
 * Tests {@link com.fecresgam.dbsd.translator.DbsCreationSqlGenerationTest}.
 *
 * @author fcres
 *
 */
public class DbsCreationSqlGenerationTest
{


    /**
     * Tests the creation-SQL generation.
     *
     * @throws IOException
     */
    @Test
    public void testDbsCreationSqlGeneration()
            throws IOException
    {
        final DbsdTestUtils testUtils = DbsdTestUtils.getInstance();

        final DbsGeneratorFactory dbsGeneratorFactory =
                DbsGeneratorFactory.getInstance();

        final DbsGenerator dbsGenerator =
                dbsGeneratorFactory.createDbsGenerator(OutputFormat.CREATION_SQL);

        dbsGenerator.generateOutput(testUtils.generateValidTestDbs(), "target", "test");
    }

}
