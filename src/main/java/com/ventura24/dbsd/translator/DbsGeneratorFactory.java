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

package com.ventura24.dbsd.translator;

import java.lang.ref.WeakReference;


/**
 * Builds instance of DbsGenerator
 */
public class DbsGeneratorFactory
{
        /**
         * Singleton implemented as a weak reference.
         */
        private static WeakReference<DbsGeneratorFactory> singleton;

        /**
         * Specifies a new weak reference.
         * @param manager the manager instance to use.
         */
        protected static void setReference(
                DbsGeneratorFactory manager)
        {
            singleton = new WeakReference<DbsGeneratorFactory>(manager);
        }

        /**
         * Retrieves the weak reference.
         * @return such reference.
         */
        protected static WeakReference<DbsGeneratorFactory> getReference()
        {
            return singleton;
        }

        /**
         * Retrieves a  DbsGeneratorFactory instance.
         * @return such instance.
         */
        public static DbsGeneratorFactory getInstance()
        {
            DbsGeneratorFactory result = null;

            WeakReference<DbsGeneratorFactory> reference = getReference();

            if  (reference != null)
            {
                result = reference.get();
            }

            if  (result == null)
            {
                result = new DbsGeneratorFactory() {};

                setReference(result);
            }

            return result;
        }

        /**
         * Creates an instance
         * @param outputFormat the output format
         * @return the instance
         */
        public  DbsGenerator createDbsGenerator(
                final OutputFormat outputFormat)
        {
            DbsGenerator result = null;

            switch (outputFormat)
            {
                case DIAGRAM:
                    result = new DbsDiagramGenerator();
                    break;
                case CREATION_SQL:
                    result = new DbsCreationSqlGenerator();
                    break;
                default:
                    throw new
                            RuntimeException("There is not an DbsGenerator associated to " + outputFormat);
            }

            return result;
        }

}
