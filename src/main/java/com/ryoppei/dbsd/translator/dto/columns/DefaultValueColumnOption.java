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

package com.ryoppei.dbsd.translator.dto.columns;


import com.ryoppei.dbsd.translator.dto.data.Data;

/**
 * The intermediate representation of a Default Value Column Option
 */
public class DefaultValueColumnOption implements ColumnOption
{

    private final Data defaultValue;

    /**
     * Creates a new Default Value Column Option
     * @param defaultValue such information.
     */
    public DefaultValueColumnOption(final Data defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public ColumnOptionType getType()
    {
        return ColumnOptionType.DEFAULT_VALUE;
    }

    @Override
    public boolean isReadonly()
    {
        return false;
    }

    @Override
    public boolean isNotNull()
    {
        return false;
    }

    @Override
    public boolean isNull()
    {
        return false;
    }

    /**
     * Retrieves the defaut value.
     *
     * @return such information.
     */
    public Data getValue()
    {
        return defaultValue;
    }

    @Override
    public boolean isDefaultValue()
    {
        return true;
    }

}
