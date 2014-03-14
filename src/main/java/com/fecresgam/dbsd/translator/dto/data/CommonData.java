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
 * The intermediate representation of an Abstract Data
 */
public class CommonData implements Data
{

    private final String content;

    static public final Data NULL = new CommonData("NULL");

    /**
     * Creates a new Abstract Data.
     *
     * @param content such information.
     */
    protected CommonData(String content)
    {
        this.content = content;
    }

    @Override
    public DataType getDataType()
    {
        return null;
    }

    @Override
    public String getContent()
    {
        return content;
    }
}
