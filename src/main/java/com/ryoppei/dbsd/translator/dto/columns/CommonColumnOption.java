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

/**
 * The intermediate representation of a Common Column Option
 */
public class CommonColumnOption implements ColumnOption
{
    private ColumnOptionType type;

    public static final CommonColumnOption READ_ONLY = new CommonColumnOption(ColumnOptionType.READ_ONLY);
    public static final CommonColumnOption NOT_NULL = new CommonColumnOption(ColumnOptionType.NOT_NULL);
    public static final CommonColumnOption NULL = new CommonColumnOption(ColumnOptionType.NULL);

    private CommonColumnOption(ColumnOptionType type)
    {
        this.type = type;
    }

    @java.lang.Override
    public ColumnOptionType getType()
    {
        return type;
    }

    @Override
    public boolean isReadonly()
    {
        return this == READ_ONLY;
    }

    @Override
    public boolean isNotNull()
    {
        return this == NOT_NULL;
    }

    @Override
    public boolean isNull()
    {
        return this == NULL;
    }

    @Override
    public boolean isDefaultValue()
    {
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof CommonColumnOption))
        {
            return false;
        }

        CommonColumnOption that = (CommonColumnOption) o;

        if (type != that.type)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return type.hashCode();
    }
}
