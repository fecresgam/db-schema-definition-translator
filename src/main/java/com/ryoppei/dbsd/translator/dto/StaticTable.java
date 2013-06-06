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

package com.ryoppei.dbsd.translator.dto;


import com.ryoppei.dbsd.translator.dto.columns.Column;
import com.ryoppei.dbsd.translator.dto.constraints.Constraint;
import com.ryoppei.dbsd.translator.dto.data.Data;

import java.util.List;

/**
 * The intermediate representation of a Static Table
 */
public class StaticTable extends CommonTable
{


    private final String nameColumn;


    /**
     * Creates a new static table.
     *
     * @param name such information.
     * @param nameColumn such information.
     * @param description such information.
     * @param columns such information.
     * @param constraints such information.
     * @param dataRows such information.
     */
    public StaticTable(
            final String name,
            final String nameColumn,
            final String description,
            final List<Column> columns,
            final List<Constraint> constraints,
            final List<List<Data>> dataRows)
    {
        super(
                name,
                TableType.STATIC,
                description,
                columns,
                constraints,
                dataRows);
        this.nameColumn = nameColumn;

    }

    /**
     * Retrieves the column which contains the name of the constants contained in the table.
     *
     * @return such information.
     */
    public String getNameColumn()
    {
        return nameColumn;
    }

}
