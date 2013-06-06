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
 * The intermediate representation of a Isa Table
 */
public class IsaTable extends CommonTable
{


    private final String referenceTable;

    /**
     * Creates a new ISA table.
     *
     * @param name such information.
     * @param referenceTable such information.
     * @param description such information.
     * @param columns such information.
     * @param constraints such information.
     * @param dataRows such information.
     */
    public IsaTable(
            final String name,
            final String referenceTable,
            final String description,
            final List<Column> columns,
            final List<Constraint> constraints,
            final List<List<Data>> dataRows)
    {
        super(
                name,
                TableType.ISA,
                description,
                columns,
                constraints,
                dataRows
        );
        this.referenceTable = referenceTable;

    }

    /**
     * Retrieves the reference table.
     *
     * @return such information.
     */
    public String getReferenceTable()
    {
        return referenceTable;
    }
}
