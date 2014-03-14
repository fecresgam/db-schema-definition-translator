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

package com.fecresgam.dbsd.translator.dto.data;

/**
 * The intermediate representation of a Data Type
 */
public enum DataType
{
    TEXT, NUMBER, DATE, BOOLEAN;

    /**
     * Check if the column type is TEXT.
     *
     * @return such information.
     */
    public boolean isText()
    {
        return this == TEXT;
    }

    /**
     * Check if the column type is NUMBER.
     *
     * @return such information.
     */
    public boolean isNumber()
    {
        return this == NUMBER;
    }

    /**
     * Check if the column type is DATE.
     *
     * @return such information.
     */
    public boolean isDate()
    {
        return this == DATE;
    }


    /**
     * Check if the column type is BOOLEAN.
     *
     * @return such information.
     */
    public boolean isBoolean()
    {
        return this == BOOLEAN;
    }

}
