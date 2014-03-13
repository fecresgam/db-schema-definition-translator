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
import com.ryoppei.dbsd.translator.dto.data.DataType;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

/**
 * The intermediate representation of a Common Column
 */
public class CommonColumn implements Column
{

    private String name;
    private DataType type;
    private List<ColumnOption> options;
    private String description;

    public CommonColumn(
            final String name,
            final DataType type,
            final List<ColumnOption> options,
            final String description)
    {
        this.name = name;
        this.type = type;
        this.options = options;
        this.description = StringEscapeUtils.unescapeJava(description);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public DataType getDataType()
    {
        return type;
    }

    @Override
    public List<ColumnOption> getOptions()
    {
        return options;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public boolean isNullable()
    {
        return optionContains(ColumnOptionType.NULL);
    }

    @Override
    public boolean isNotNullable()
    {
        return optionContains(ColumnOptionType.NOT_NULL);
    }

    @Override
    public boolean isReadOnly()
    {
        return optionContains(ColumnOptionType.READ_ONLY);
    }

    @Override
    public Data getDefaultValue()
    {
        Data result = null;

        for (final ColumnOption option : options)
        {
            if (ColumnOptionType.DEFAULT_VALUE == option.getType())
            {
                result = ((DefaultValueColumnOption) option).getValue();
                break;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof CommonColumn))
        {
            return false;
        }

        CommonColumn commonColumn = (CommonColumn) o;

        if (!name.equals(commonColumn.name))
        {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public String getMapKey()
    {
        return getName();
    }

    /**
     * Retrieves the true if the option list contains the give option type.
     *
     * @param type the option type.
     * @return such information.
     */
    private boolean optionContains(final ColumnOptionType type)
    {
        boolean result = false;

        for (final ColumnOption option : options)
        {
            if (type == option.getType())
            {
                result = true;
                break;
            }
        }

        return result;
    }
}
