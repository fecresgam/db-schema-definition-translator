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

package com.ryoppei.dbsd.translator.dto.columns;

import com.ryoppei.dbsd.translator.dto.Mappable;
import com.ryoppei.dbsd.translator.dto.data.Data;
import com.ryoppei.dbsd.translator.dto.data.DataType;

import java.util.List;


/**
 * The intermediate representation of a Column
 */
public interface Column extends Mappable
{

    /**
     * Retrieves the name.
     * @return such information.
     */
    public String getName();

    /**
     * Retrieves the data type.
     * @return such information.
     */
    public DataType getDataType();

    /**
     * Retrieves the options.
     * @return such information.
     */
    public List<ColumnOption> getOptions();

    /**
     * Retrieves the description.
     * @return such information.
     */
    public String getDescription();

    /**
     * Retrieves true if it's nullable.
     * @return such information.
     */
    public boolean isNullable();

    /**
     * Retrieves true if it's not nullable
     * @return such information.
     */
    public boolean isNotNullable();

    /**
     * Retrieves tre if it's read only.
     * @return such information
     */
    public boolean isReadOnly();

    /**
     * Retrieves the default value.
     *
     * @return such information.
     */
    public Data getDefaultValue();
}
