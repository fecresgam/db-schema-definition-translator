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

package com.fecresgam.dbsd.translator.dto;

import com.fecresgam.dbsd.translator.dto.columns.Column;
import com.fecresgam.dbsd.translator.dto.constraints.Constraint;
import com.fecresgam.dbsd.translator.dto.data.Data;

import java.util.List;


/**
 * The intermediate representation of a Table
 */
public interface Table extends Mappable
{
    /**
     * Retrieves the name.
     *
     * @return such information.
     */
    String getName();

    /**
     * Retrieves the type.
     *
     * @return such information.
     */
    TableType getType();

    /**
     * Retrieves the description.
     *
     * @return such information.
     */
    String getDescription();

    /**
     * Retrieves the columns associated with the given key.
     *
     * @return such information.
     */
    Column getColumn(final String key);

    /**
     * Checks if a table contains a column
     *
     * @param columnName such information.
     * @return true if it contains that column.
     */
    boolean containsColumn(final String columnName);

    /**
     * Retrieves the columns.
     *
     * @return such information.
     */
    List<Column> getColumns();

    /**
     * Retrieves the constraint associated to the given name.
     *
     * @return such information.
     */
    Constraint getConstraint(final String constraintName);

    /**
     * Retrieves the constraints.
     *
     * @return such information.
     */
    List<Constraint> getConstraints();

    /**
     * Checks if a table contains a constraint
     *
     * @param constraintName such information.
     * @return true if it contains that constraint.
     */
    boolean containsConstraint(final String constraintName);

    /**
     * Retrieves the data rows.
     *
     * @return such information.
     */
    public List<List<Data>> getDataRows();


}
