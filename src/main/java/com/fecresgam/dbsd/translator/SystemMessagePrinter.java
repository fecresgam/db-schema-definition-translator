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

package com.fecresgam.dbsd.translator;


import java.text.MessageFormat;
import java.util.ResourceBundle;


/**
 * The System Message Printer
 */
public class SystemMessagePrinter
{

    /**
     * The message bundle name
     */
    private static final String BUNDLE_NAME = "system-messages";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    private static final String GENERAL_HELP_INFORMATION = "general.help.information";
    // Error messages
    static final String SEMANTIC_VALIDATION_ERROR_DBS = "semantic.validation.error.dbs";
    static final String SEMANTIC_VALIDATION_ERROR_FOREIGNKEY_DIFFERENTSIZE = "semantic.validation.error.foreignkey.differentsize";
    static final String SEMANTIC_VALIDATION_ERROR_FOREIGNKEY_REFERENCETABLE_NOTEXISTS = "semantic.validation.error.foreignkey.referencetable.notexists";
    static final String SEMANTIC_VALIDATION_ERROR_CONSTRAINT_COLUMNNOTEXISTS = "semantic.validation.error.constraint.columnnotexists";
    static final String SEMANTIC_VALIDATION_ERROR_CONSTRAINT_INDEXNOTEXISTS = "semantic.validation.error.constraint.indexnotexists";
    static final String SEMANTIC_VALIDATION_ERROR_INDEX_COLUMNNOTEXISTS = "semantic.validation.error.index.columnnotexists";
    static final String SEMANTIC_VALIDATION_ERROR_DATAROW_DIFFERENTSIZE = "semantic.validation.error.datarow.differentsize";
    static final String SEMANTIC_VALIDATION_ERROR_ISATABLE_NOTEXISTS = "semantic.validation.error.isatable.notexists";
    static final String SEMANTIC_VALIDATION_ERROR_STATICTABLE_NOTEXISTS = "semantic.validation.error.statictable.notexists";
    static final String ERROR_WRITING_FILE = "file.writing.error";



    /**
     * Prints the help information
     */
    public static void printHelpInformation()
    {
        printSystemMessage(GENERAL_HELP_INFORMATION);
    }

    /**
     * Print a system message.
     *
     * @param messageKey the message key.
     * @param args the message arguments.
     */
    public static void printSystemMessage(
            final String messageKey,
            final Object ... args)
    {
        final String message = resourceBundle.getString(messageKey);
        final String format = MessageFormat.format(message, args);

        System.out.println(format);
    }

}
