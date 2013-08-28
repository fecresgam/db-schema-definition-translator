DB Schema Definition Translator user manual
===========================================

DB Schema definition translator is a tool that helps in the DB schema changes managing.
It allows to translate between different representations of a DB schema.
In this version these are the available formats:

* Input formats
  * DB Schema Definition (.dbsd)
* Output formats
  * DB Creation PL/SQL group of scripts (.sql)
  * Graphviz diagram (.dot)


Command line usage
------------------

The tool could be use via the command-line ?convert-dbsd?:

  convert-dbsd mode input_file output_folder output_file_prefix
     Where the mode could be:
        -h: prints a help message.
        -o FORMAT: defines the output format.
           sql: to obtain the DB creation PL/SQL scripts.
           img: to obtain a visual representation.


The ?DB Schema Definition? format grammar
-----------------------------------------

The next screen-shots shows an example usage using all the possibilities:


As you can see this is the format:
General Settings.
OWNER(name): Defines the DB owner.
USER(name): Defines the DB user.
USER_ROLE(name): Defines the role of the DB user.
DEFAULT_TABLE_TABLESPACE(name): Defines the tables default tablespace.
DEFAULT_INDEX_TABLESPACE(name): Defines the indexes default tablespace.
TARGET_DBMS(dbms): Defines the target DBMS. Currently the only valid value is 'ORACLE'
Tables
?<<-- name optional_type 'description' --> ?: Defines the table name and description and optionally its type.
Where types could be
STATIC(column_name): Define a static table.
ISA(table_name): Defines a table with an ?IS A? relation with another table.
Columns
?name | col_type | col_options | 'description' ;? : Defines a column.
Where 'col_type' could be:
NUM(precision,scale) or NUM(precision): Number type.
TXT(size): Text type.
BOOL: Boolean type.
DATE_TIME: Date & time type.
DATE: Date only type.
TIME: Time only type.
Where 'col_options' could be:
NULL: Defines a nullable column.
NOT_NULL: Defines a non-nullable column.
RO: Defines a read only column.
DEF(value): Defines the default value of a column.
?##     ##?: The constraints header
Constraints
?name | const_definition | options ;? : Defines a constraint.
Where ?const_definition? could be:
IND(column): Defines an index.
PK(column_list): Defines a Primary Key constraint.
UK(column_list): Defines a Unique constraint.
CHK(column 'condition'): Defines a Check constraint.
FK(column_list)->table.(column_list): Defines a Foreign Key constraint.
Where ?options? could be:
USE_IND(name): Define a index used by a PK (only in PK constraints).
?++     ++?: The table data rows header (only in STATIC tables).
Table data rows.
?( data, data ?.);? : Defines a data row (only in STATIC tables).
Sequences
SEQUENCE(name): Defines a sequence.
Literal SQL Code: All the code between '{' and '}' will be copied unchanged to the output files.
