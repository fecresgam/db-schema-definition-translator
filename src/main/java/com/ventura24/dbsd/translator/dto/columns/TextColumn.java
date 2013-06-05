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

package com.ventura24.dbsd.translator.dto.columns;


import com.ventura24.dbsd.translator.dto.data.DataType;

import java.util.List;

/**
 * The intermediate representation of a Common Column
 */
public class TextColumn extends CommonColumn
{
    private Integer size;

    /**
     * Creates a new Text column.
     *
     * @param name such information.
     * @param size such information.
     * @param options such information.
     * @param description such information.
     */
    public TextColumn(
            final String name,
            final Integer size,
            final List<ColumnOption> options,
            final String description)
    {
        super(name, DataType.TEXT, options, description);
        this.size = size;
    }

    /**
     * Retrieves the size.
     *
     * @return such information.
     */
    public Integer getSize()
    {
        return size;
    }
}
