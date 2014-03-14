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

package com.fecresgam.dbsd.translator.dto.constraints;

import java.util.List;


/**
 * The intermediate representation of a Primary Key Constraint
 */
public class PrimaryKeyConstraint extends CommonConstraint
{
    private List<String> columns;
    private String index;

    /**
     * Creates a new Primary Key Constraint.
     *
     * @param name such information.
     * @param columns such information.
     * @param index such information.
     */
    public PrimaryKeyConstraint(
            final String name,
            final List<String> columns,
            final String index)
    {
        super(name, ConstraintType.PRIMARY_KEY);
        this.columns = columns;
        this.index = index;
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

    /**
     * Retrieves the index that the primary key uses.
     *
     * @return such information.
     */
    public String getIndex()
    {
        return index;
    }

    /**
     * Retrieves true if the primary key uses an index.
     *
     * @return such information.
     */
    public boolean isUsingIndex()
    {
        return (index != null);
    }
}
