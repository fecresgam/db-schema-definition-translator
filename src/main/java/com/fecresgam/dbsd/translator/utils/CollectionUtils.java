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

package com.fecresgam.dbsd.translator.utils;


import com.fecresgam.dbsd.translator.dto.Mappable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The Collection utils
 */
public class CollectionUtils
{

    // Singleton implementation.
    /**
     * Singleton implemented to avoid the double-checked locking idiom.
     */
    private static final class CollectionUtilsSingletonContainer
    {

        /**
         * The actual singleton.
         */
        static final CollectionUtils SINGLETON = new CollectionUtils();
    }

    /**
     * Retrieves a EmailUtils instance.
     *
     * @return such instance.
     */
    public static CollectionUtils getInstance()
    {

        return CollectionUtilsSingletonContainer.SINGLETON;
    }


    /**
     * Put a group of mappable objects into a map.
     *
     * @param mappables such information.
     * @return the map.
     */
    public <T extends Mappable> Map<String, T> putIntoMap (final Iterable<T> mappables)
    {
        Map<String, T> result = Collections.emptyMap();

        if (mappables!=null)
        {
            result = new HashMap<String, T>();

            for (final T mappable : mappables)
            {
                if (mappable!=null){
                    result.put(mappable.getMapKey(), mappable);
                }
            }
        }

        return result;
    }
}
