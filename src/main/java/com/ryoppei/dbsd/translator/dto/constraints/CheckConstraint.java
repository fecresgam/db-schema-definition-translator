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

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * The intermediate representation of a Check Constraint
 */
public class CheckConstraint extends CommonConstraint
{
    private String column;
    private String condition;

    /**
     * Creates a new Check Constraint.
     *
     * @param name such information.
     * @param column such information.
     * @param condition such information.
     */
    public CheckConstraint(
            final String name,
            final String column,
            final String condition)
    {
        super(name, ConstraintType.CHECK);
        this.column = column;
        this.condition = StringEscapeUtils.unescapeJava(condition);
    }

    /**
     * Retrieves the columns.
     *
     * @return such information.
     */
    public String getColumn()
    {
        return column;
    }

    /**
     * Retrieves the condition to check.
     *
     * @return such information.
     */
    public String getCondition()
    {
        return condition;
    }
}
