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

package com.fecresgam.dbsd.translator.dto.columns;

/**
 * The intermediate representation of a Column Option
 */
public interface ColumnOption
{
    /**
     * Retrieves the type.
     *
     * @return such information.
     */
    public ColumnOptionType getType();

    /**
     * Retrieves true if it's read only .
     *
     * @return such information.
     */
    public boolean isReadonly();

    /**
     * Retrieves the true if it's not nullable.
     *
     * @return such information.
     */
    public boolean isNotNull();

    /**
     * Retrieves true if it's nullable.
     *
     * @return such information.
     */
    public boolean isNull();

    /**
     * Retrieves true if it's a default value.
     *
     * @return such information.
     */
    public boolean isDefaultValue();

}
