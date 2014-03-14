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

/**
 * The intermediate representation of a Common Constraint
 */
public class CommonConstraint implements Constraint
{

    private String name;
    private ConstraintType constraintType;

    /**
     * Creates a new Common Constraint.
     * @param name such information.
     * @param constraintType such information.
     */
    public CommonConstraint(
            final String name,
            final ConstraintType constraintType)
    {
        this.name = name;
        this.constraintType = constraintType;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public ConstraintType getType()
    {
        return constraintType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof CommonConstraint))
        {
            return false;
        }

        CommonConstraint that = (CommonConstraint) o;

        if (!name.equals(that.name))
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
}
