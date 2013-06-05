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


import com.ryoppei.dbsd.translator.dto.data.DataType;

import java.util.List;

/**
 * The intermediate representation of a Number Column
 */
public class NumberColumn extends CommonColumn
{
    private Integer precision;

    private Integer scale;

    /**
     * Creates a new Number Column.
     * @param name such information.
     * @param precision such information.
     * @param scale such information.
     * @param options such information.
     * @param description such information.
     */
    public NumberColumn(
            final String name,
            final Integer precision,
            final Integer scale,
            final List<ColumnOption> options,
            final String description)
    {
        super(name, DataType.NUMBER, options, description);
        this.precision = precision;
        this.scale = scale;
    }

    /**
     * Retrieves the precission.
     *
     * @return such information.
     */
    public Integer getPrecision()
    {
        return precision;
    }

    /**
     * Retrieves the scale.
     *
     * @return such information.
     */
    public Integer getScale()
    {
        return scale;
    }
}
