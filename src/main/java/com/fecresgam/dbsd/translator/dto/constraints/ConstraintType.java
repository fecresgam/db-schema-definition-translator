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
 * The intermediate representation of a Constraint Type
 */
public enum ConstraintType
{
    INDEX, PRIMARY_KEY, FOREIGN_KEY, UNIQUE_KEY, CHECK;

    /**
     * Check if the constraint type is INDEX.
     *
     * @return such information.
     */
    public boolean isIndex()
    {
        return this == INDEX;
    }

    /**
     * Check if the constraint type is PRIMARY_KEY.
     *
     * @return such information.
     */
    public boolean isPrimaryKey()
    {
        return this == PRIMARY_KEY;
    }

    /**
     * Check if the constraint type is FOREIGN_KEY.
     *
     * @return such information.
     */
    public boolean isForeignKey()
    {
        return this == FOREIGN_KEY;
    }

    /**
     * Check if the constraint type is UNIQUE_KEY.
     *
     * @return such information.
     */
    public boolean isUniqueKey()
    {
        return this == UNIQUE_KEY;
    }

    /**
     * Check if the constraint type is CHECK.
     *
     * @return such information.
     */
    public boolean isCheck()
    {
        return this == CHECK;
    }



}
