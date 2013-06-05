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

package com.ryoppei.dbsd.translator.dto.constraints;

import java.util.List;


/**
 * The intermediate representation of a Unique Key Constraint
 */
public class UniqueKeyConstraint extends CommonConstraint
{
    private List<String> columns;

    /**
     * Creates a new Unique Key Constraint.
     *
     * @param name such information.
     * @param columns such information.
     */
    public UniqueKeyConstraint(
            final String name,
            final List<String> columns)
    {
        super(name, ConstraintType.UNIQUE_KEY);
        this.columns = columns;
    }

    /**
     * Retrieves the columns.
     *
     * @return such information.
     */
    public List<String> getColumns()
    {
        return columns;
    }
}
