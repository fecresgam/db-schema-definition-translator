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

/**
 * The intermediate representation of an Index Constraint
 */
public class IndexConstraint extends CommonConstraint
{
    private String column;
    private OrderType orderType;

    /**
     * Creates a new Index
     *
     * @param name such information.
     * @param column such information.
     * @param orderType such information.
     */
    public IndexConstraint(
            final String name,
            final String column,
            final OrderType orderType)
    {
        super(name, ConstraintType.INDEX);
        this.column = column;
        this.orderType = orderType;
    }

    /**
     * Retrieves the column to index.
     *
     * @return such information.
     */
    public String getColumn()
    {
        return column;
    }

    /**
     * Retrieves the order type.
     *
     * @return such information.
     */
    public OrderType getOrderType()
    {
        return orderType;
    }

    public static enum OrderType
    {
        ASC, DESC
    }
}
