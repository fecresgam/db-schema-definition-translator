package com.ventura24.dbsd.translator.dto;

import com.ventura24.dbsd.translator.utils.CollectionUtils;

import java.util.List;
import java.util.Map;

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

/**
 * The intermediate representation of a Common Data Base Schema
 */
public class CommonDataBaseSchema implements DataBaseSchema
{

    /**
     * The DB owner name
     */
    private String owner;
    private String user;
    private String userRole;
    private String defaultTableTablespace;
    private String defaultIndexTablespace;
    private DBMSType targetDBMS;

    private Map<String, Table> tables;
    private List<Table> orderedTables;

    private List<Sequence> sequences;

    private String sqlLiteralCode;


    /**
     * Creates a new Common Data Base Schema.
     *
     * @param owner such information.
     * @param user such information.
     * @param userRole such information.
     * @param defaultTableTablespace such information.
     * @param defaultIndexTablespace such information.
     * @param targetDBMS such information.
     * @param tables such information.
     * @param sequences such information.
     * @param sqlLiteralCode such information.
     */
    public CommonDataBaseSchema(
            String owner,
            String user,
            String userRole,
            String defaultTableTablespace,
            String defaultIndexTablespace,
            DBMSType targetDBMS,
            List<Table> tables,
            List<Sequence> sequences,
            String sqlLiteralCode)
    {
        final CollectionUtils collectionUtils =
                CollectionUtils.getInstance();

        this.owner = owner;
        this.user = user;
        this.userRole = userRole;
        this.defaultTableTablespace = defaultTableTablespace;
        this.defaultIndexTablespace = defaultIndexTablespace;
        this.targetDBMS = targetDBMS;
        this.orderedTables = tables;
        this.sequences = sequences;
        this.sqlLiteralCode = sqlLiteralCode;

        // Make maps
        this.tables = collectionUtils.putIntoMap(tables);
    }

    @Override
    public String getSqlLiteralCode()
    {
        return sqlLiteralCode;
    }


    @Override
    public Table getTable(String key)
    {
        return tables.get(key);
    }

    @Override
    public boolean containsTable(String tableName)
    {
        return tables.containsKey(tableName);
    }

    @Override
    public List<Table> getTables()
    {
        return orderedTables;
    }

    @Override
    public List<Sequence> getSequences()
    {
        return sequences;
    }

    @Override
    public String getOwner()
    {
        return owner;
    }

    @Override
    public String getUser()
    {
        return user;
    }

    @Override
    public String getUserRole()
    {
        return userRole;
    }

    @Override
    public String getDefaultTableTablespace()
    {
        return defaultTableTablespace;
    }

    @Override
    public String getDefaultIndexTablespace()
    {
        return defaultIndexTablespace;
    }

    @Override
    public DBMSType getTargetDBMS()
    {
        return targetDBMS;
    }
}
