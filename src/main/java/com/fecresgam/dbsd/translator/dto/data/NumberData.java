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

package com.fecresgam.dbsd.translator.dto.data;


/**
 * The intermediate representation of a Number Data
 */
public class NumberData extends CommonData
{
    private int precision;
    private int scale;

    /**
     * Creates a ne number data.
     *
     * @param content such information.
     */
    public NumberData(final String content)
    {
        super(content);

        final String unsignedNumber = removeSign(content);
        int length = unsignedNumber.length();
        int indexOfDot = unsignedNumber.indexOf('.');

        this.precision =
                (indexOfDot != -1) ? length -1 : length;
        this.scale =
                (indexOfDot != -1) ? length - indexOfDot - 1: 0;
    }

    /**
     * Removes the sign from a number.
     *
     * @param content the number.
     * @return unsigned number.
     */
    private static String removeSign(final String content)
    {
        return (content.startsWith("+") || content.startsWith("-")) ?
                content.substring(1) :
                content;
    }

    @Override
    public DataType getDataType()
    {
        return DataType.NUMBER;
    }

    /**
     * Retrieves the precission.
     *
     * @return such information.
     */
    @SuppressWarnings("unused")
    public Integer getPrecision()
    {
        return  this.precision;
    }

    /**
     * Retrieves the scale.
     *
     * @return such information.
     */
    @SuppressWarnings("unused")
    public Integer getScale()
    {
        return this.scale;
    }



}
