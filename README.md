DB Schema Definition Translator
===============================

DB Schema definition translator is a tool that helps in the DB schema changes managing.
It allows to translate between different representations of a DB schema.
In this version these are the available formats:

* Input formats
  * DB Schema Definition (.dbsd)
* Output formats
  * DB Creation PL/SQL group of scripts (.sql)
  * Graphviz diagram (.dot)


Usage
-----

It could be used via command line but it's recommended to be used via it's [maven plugin][1].


"DB Schema Definition" grammar format
-----------------------------------------
It's recommended to take a look to the [.dbsd file example][2] that uses almost all the DBSD grammar possibilities.
The whole grammar is represented in the [DBSD Antlr 3 Grammar definition][3].

As you can see this is the format:

* General Settings.
  * __OWNER(name):__ Defines the DB owner.
  * __USER(name):__ Defines the DB user.
  * __USER_ROLE(name):__ Defines the role of the DB user.
  * __DEFAULT_TABLE_TABLESPACE(name):__ Defines the tables default tablespace.
  * __DEFAULT_INDEX_TABLESPACE(name):__ Defines the indexes default tablespace.
  * __TARGET_DBMS(dbms):__ Defines the target DBMS. Currently the only valid value is 'ORACLE'
* Tables
  * __"<<-- name optional_type 'description' --> ":__ Defines the table name and description and optionally its type.
    * Where types could be
      * __STATIC(column_name):__ Define a static table using "column_name" data as constants names.
      * __ISA(table_name):__ Defines a table with an "IS A" relation with another table "table_name".
  * Columns: __"name | col_type | col_options | 'description' ;" :__ Defines a column.
      * Where 'col_type' could be:
         * __NUM(precision,scale), NUM(precision) or NUM:__ Number type.
         * __TXT(size) or TXT:__ Text type.
         * __BOOL:__ Boolean type.
         * __DATE:__ Date and time type.
      * Where 'col_options' could be:
         * __NULL:__ Defines a nullable column.
         * __NOT_NULL:__ Defines a non-nullable column.
         * __RO:__ Defines a read only column.
         * __DEF(value):__ Defines the default value of a column.
  * __"##     ##":__ The constraints header
  * Constraints: __"name | const_definition | options ;" :__ Defines a constraint.
      * Where "const_definition" could be:
         * __IND(column):__ Defines an index".
         * __PK(column_list):__ Defines a Primary Key constraint.
         * __UK(column_list):__ Defines a Unique constraint.
         * __CHK(column 'condition'):__ Defines a Check constraint.
         * __FK(column_list)->other_table.(column_list):__ Defines a Foreign Key constraint.
      * Where "options" could be:__
         * __USE_IND(name):__ Define a index used by a PK (only in PK constraints).
  * __"++     ++":__ The table data rows header. (Mandatory in Static tables).
  * Table data rows.
    * __"( data, data ...);" :__ Defines a data row (Mandatory in Static tables).
* Sequences
  * __SEQUENCE(name):__ Defines a sequence.
* Literal SQL Code: All the code between '{' and '}' will be copied unchanged to the output files.

* __Comments:__ Any place in the code (expect inside the "Literal SQL Code block") comments could be inserted
  * __"//":__  One line comment
  * __"/* */":__ Multiline comment

### Constant Values ###
Some constant values are defined to use as column default value or in the "Table data rows" section.
  * __NULL:__ to be used with all data types.
  * __TRUE:__ to be used with boolean data type.
  * __FALSE:__ to be used with boolean data type.
  * __NOW:__ to be used with date data type. Similar to SYSDATE in ORACLE.



[1]: https://github.com/fecresgam/db-schema-definition-translator-maven-plugin  "DB Schema Definition Translator Maven Plugin"
[2]: src/test/resources/testInput.dbsd ".dbsd File Example"
[3]: src/main/antlr/Dbsd.g "DBSD Antlr 3 Grammar"