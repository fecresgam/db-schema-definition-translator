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

package com.fecresgam.dbsd.translator.utils;


import org.apache.commons.lang3.StringEscapeUtils;

/**
 * The Parser Utils
 */
public class ParserUtils
{
    /**
     * Clean a the start and character from a given string.
     *
     * @param input such information.
     * @return such information.
     */
    public static String cleanFakeString(final String input)
    {
        String result = null;

        if (input != null && input.length()>1)
        {
            result = input.substring(1,input.length()-1);
        }
        return StringEscapeUtils.unescapeJava(result);
    }
}
