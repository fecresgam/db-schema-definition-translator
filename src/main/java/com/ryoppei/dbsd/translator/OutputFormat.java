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


/**
 * The Output Format
 */
public enum OutputFormat
{
    CREATION_SQL, DIAGRAM;

    /**
     * Retrieves the constant that represent the given value.
     *
     * @return such information.
     */
    public static OutputFormat getEnum(final String value)
    {
        OutputFormat result = null;

        try
        {
            result = OutputFormat.valueOf(value);
        }
        catch (IllegalArgumentException e)
        {
            // Using Plan-B
            if ("sql".equals(value))
            {
                result = CREATION_SQL;
            }
            else if ("img".equals(value))
            {
                result = DIAGRAM;
            }

        }

        return  result;
    }
}
