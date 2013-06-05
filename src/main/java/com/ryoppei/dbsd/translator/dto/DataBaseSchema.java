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

package com.ryoppei.dbsd.translator.dto;

import java.util.List;

/**
 * The intermediate representation of a Data Base Schema
 */
public interface DataBaseSchema
{
    /**
     * Retrieves the dbs owner.
     *
     * @return such information.
     */
    String getOwner();

    /**
     * Retrieves the dbs user.
     *
     * @return such information.
     */
    String getUser();

    /**
     * Retrieves the debs user role.
     *
     * @return such information.
     */
    String getUserRole();

    /**
     * Retrieves the default tablespace for tables.
     *
     * @return such information.
     */
    String getDefaultTableTablespace();

    /**
     * Retrieves the default tablespace for indexes.
     *
     * @return such information.
     */
    String getDefaultIndexTablespace();

    /**
     * Retrieves the target DBMS.
     *
     * @return such information.
     */
    DBMSType getTargetDBMS();

    /**
     * Retrieves the table associated with the given key..
     *
     * @param key such information
     * @return such information.
     */
    Table getTable(final String key);

    /**
     * Check if a Data Base Schema contains a table
     * @param tableName such information.
     *
     * @return true if it contains that table.
     */
    boolean containsTable(final String tableName);

    /**
     * Retrieves the tables.
     *
     * @return such information.
     */
    List<Table> getTables();

    /**
     * Retrieves the sequences.
     *
     * @return such information.
     */
    List<Sequence> getSequences();

    /**
     * Retrieves the SQL Literal Code.
     *
     * @return such information.
     */
    String getSqlLiteralCode();
}
