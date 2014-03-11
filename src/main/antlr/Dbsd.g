// **********************************************************************
//   This file is part of DB Schema Definition Translator.              *
//                                                                      *
//   DB Schema Definition Translator is free software: you can          *
//   redistribute it and/or modify it under the terms of the GNU        *
//   General Public License as published by the Free Software           *
//   Foundation, either version 2 of the License, or any later version. *
//                                                                      *
//   Foobar is distributed in the hope that it will be useful,          *
//   but WITHOUT ANY WARRANTY; without even the implied warranty of     *
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the      *
//   GNU General Public License for more details.                       *
//                                                                      *
//   You should have received a copy of the GNU General Public License  *
//   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.    *
//                                                                      *
//   Author: Felipe Crespo Gambade                                      *
//                                                                      *
//***********************************************************************

grammar Dbsd;

options
{
  // antlr will generate java lexer and parser
  language = Java;
  // generated parser should create abstract syntax tree
}


@lexer::header {
package com.ryoppei.dbsd.translator;
}

@parser::header {
package com.ryoppei.dbsd.translator;

import com.ryoppei.dbsd.translator.dto.columns.Column;
import com.ryoppei.dbsd.translator.dto.columns.ColumnOption;
import com.ryoppei.dbsd.translator.dto.columns.CommonColumnOption;
import com.ryoppei.dbsd.translator.dto.columns.DefaultValueColumnOption;
import com.ryoppei.dbsd.translator.dto.columns.CommonColumn;
import com.ryoppei.dbsd.translator.dto.columns.NumberColumn;
import com.ryoppei.dbsd.translator.dto.columns.TextColumn;

import com.ryoppei.dbsd.translator.dto.data.CommonData;
import com.ryoppei.dbsd.translator.dto.data.Data;
import com.ryoppei.dbsd.translator.dto.data.DataType;
import com.ryoppei.dbsd.translator.dto.data.NumberData;
import com.ryoppei.dbsd.translator.dto.data.TextData;
import com.ryoppei.dbsd.translator.dto.data.DateData;
import com.ryoppei.dbsd.translator.dto.data.BooleanData;

import com.ryoppei.dbsd.translator.dto.constraints.CommonConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.Constraint;
import com.ryoppei.dbsd.translator.dto.constraints.CheckConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.ForeignKeyConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.IndexConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.PrimaryKeyConstraint;
import com.ryoppei.dbsd.translator.dto.constraints.UniqueKeyConstraint;

import com.ryoppei.dbsd.translator.dto.CommonDataBaseSchema;
import com.ryoppei.dbsd.translator.dto.CommonTable;
import com.ryoppei.dbsd.translator.dto.DBMSType;
import com.ryoppei.dbsd.translator.dto.Sequence;
import com.ryoppei.dbsd.translator.dto.Table;
import com.ryoppei.dbsd.translator.dto.IsaTable;
import com.ryoppei.dbsd.translator.dto.StaticTable;
import com.ryoppei.dbsd.translator.dto.TableType;

import com.ryoppei.dbsd.translator.utils.ParserUtils;

import java.util.ArrayList;
import java.util.List;



}

@parser::members
{
    public CommonDataBaseSchema dbsd;
}

// ***************** lexer rules:

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
     ;

LITERAL_CODE
    :  '{' ( ESC_SEQ | ~('\\'|'}') )* '}'
    ;

STRING
    :  '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\''
    ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

TABLE_HEADER_START : '<<-' '-'+ ;
TABLE_HEADER_END   :       '-'+ '->>';
CONSTRAINTS_HEADER : '##'  ' '+ '##' ;
TABLE_DATA_HEADER  : '++'  ' '+ '++' ;
TABLE_FOOTER       : '<<-' '-'+ '->>';


OWNER     : 'OWNER';
USER      : 'USER';
USER_ROLE : 'USER_ROLE';
DEFAULT_TABLE_TS : 'DEFAULT_TABLE_TABLESPACE';
DEFAULT_INDEX_TS : 'DEFAULT_INDEX_TABLESPACE';
TARGET_DBMS : 'TARGET_DBMS';

SEQUENCE : 'SEQUENCE';

OP : '(';
CP : ')';
I  : '|';
SC : ';';
CM : ',';
DT : '.';
AA : '->';

PK  : 'PK';
UK  : 'UK';
FK  : 'FK';
CHK : 'CHK';
IND : 'IND';

TXT       : 'TXT';
NUM       : 'NUM';
BOOL      : 'BOOL';
DATE      : 'DATE';

NOT_NULL : 'NOT_NULL';
NULL     : 'NULL';
RO       : 'RO';
DEF      : 'DEF';
USE_IND  : 'USE_IND';

NOW   : 'NOW';
TRUE  : 'TRUE';
FALSE : 'FALSE';

ISA : 'ISA';
STATIC : 'STATIC';

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT
  : '0'..'9'+
  ;

DECIMAL
  : ('0'..'9')+ '.' ('0'..'9')+
  ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;


// ***************** parser rules:

db_schema_definition returns [CommonDataBaseSchema result]
     :  o=owner u=user r=role dt=default_table_ts di=default_index_ts dbms=target_dbms t=tables (s=sequences)? l=LITERAL_CODE
                             { dbsd = new CommonDataBaseSchema(o, u, r, dt, di, dbms, t, s, ParserUtils.cleanFakeString($l.text));
                               result=dbsd;}
     ;

fragment owner returns [String result]
  : OWNER OP i=ID CP { result = $i.text;}
  ;

fragment user returns [String result]
  : USER OP i=ID CP { result = $i.text;}
  ;

fragment role returns [String result]
  : USER_ROLE OP i=ID CP { result = $i.text;}
  ;

fragment default_table_ts returns [String result]
  : DEFAULT_TABLE_TS OP i=ID CP { result = $i.text;}
  ;

fragment default_index_ts returns [String result]
  : DEFAULT_INDEX_TS OP i=ID CP { result = $i.text;}
  ;

fragment target_dbms returns [DBMSType result]
  : TARGET_DBMS OP i=ID CP { result = DBMSType.valueOf($i.text);}
  ;


// ------------------------------ Tables ---------------------------------------

fragment tables returns [List<Table> result]
@init { result = new ArrayList<Table>(); }
  : (t=table { result.add(t); })+
  ;

fragment table  returns [Table result]
@init { result = null; }
	:	TABLE_HEADER_START n=ID                   d=string TABLE_HEADER_END col=columns con=constraints (TABLE_DATA_HEADER tds=t_data_rows_list)? TABLE_FOOTER  { result = new CommonTable($n.text, TableType.COMMON, d, col, con, tds);}
	|	TABLE_HEADER_START n=ID ISA    OP r=ID CP d=string TABLE_HEADER_END col=columns con=constraints (TABLE_DATA_HEADER tds=t_data_rows_list)? TABLE_FOOTER  { result = new IsaTable($n.text, $r.text, d, col, con, tds);}
	|	TABLE_HEADER_START n=ID STATIC OP r=ID CP d=string TABLE_HEADER_END col=columns con=constraints  TABLE_DATA_HEADER tds=t_data_rows_list   TABLE_FOOTER  { result = new StaticTable($n.text, $r.text, d, col, con, tds);}
	;


// ---------------- Columns ---------------------
fragment columns returns [List<Column> result]
@init { result = new ArrayList<Column>(); }
  : (c=column  {result.add(c);})+
  ;

fragment column returns [Column result]
  : i=ID I TXT                        I co=column_options? I d=string SC  {result = new TextColumn  ($i.text, null, co, d);}
  | i=ID I TXT  OP n=INT CP           I co=column_options? I d=string SC  {result = new TextColumn  ($i.text, Integer.valueOf($n.text), co, d);}
  | i=ID I NUM                        I co=column_options? I d=string SC  {result = new NumberColumn($i.text, null,  null, co, d);}
  | i=ID I NUM  OP n=INT CP           I co=column_options? I d=string SC  {result = new NumberColumn($i.text, Integer.valueOf($n.text),  null, co, d);}
  | i=ID I NUM  OP n=INT CM n2=INT CP I co=column_options? I d=string SC  {result = new NumberColumn($i.text, Integer.valueOf($n.text), Integer.valueOf($n2.text), co, d);}
  | i=ID I BOOL                       I co=column_options? I d=string SC  {result = new CommonColumn($i.text, DataType.BOOLEAN , co, d);}
  | i=ID I DATE                       I co=column_options? I d=string SC  {result = new CommonColumn($i.text, DataType.DATE    , co, d);}
  ;

fragment column_options returns [List<ColumnOption> result]
@init { result = new ArrayList<ColumnOption>();}
  : co=column_option {result.add(co);} ( CM co2=column_option {result.add(co2);})*
  ;

fragment column_option returns [ColumnOption result]
  : NOT_NULL              { result = CommonColumnOption.NOT_NULL;}
  | NULL                  { result = CommonColumnOption.NULL;}
  | RO                    { result = CommonColumnOption.READ_ONLY;}
  | DEF OP d=t_data_row CP  { result = new DefaultValueColumnOption(d);}
  ;


// ---------------- Constraints ---------------------
fragment constraints returns [List<Constraint> result]
@init { result = new ArrayList<Constraint>(); }
  : CONSTRAINTS_HEADER (c=constraint  {result.add(c);})+
  ;

fragment constraint returns [Constraint result]
  : i=ID I IND OP c=ID CP I SC  {result = new IndexConstraint($i.text, $c.text);}
  | i=ID I PK  OP cs=ids CP I ui=uses_index? SC  {result = new PrimaryKeyConstraint($i.text, cs, ui);}
  | i=ID I UK  OP cs=ids CP I SC  {result = new UniqueKeyConstraint($i.text, cs);}
  | i=ID I CHK OP c=ID cd=string CP I SC  {result = new CheckConstraint($i.text, $c.text, cd);}
  | i=ID I FK  OP cs=ids CP AA rt=ID DT OP rtc=ids CP I SC  {result = new ForeignKeyConstraint($i.text, cs, $rt.text, rtc);}
  ;

fragment uses_index returns [String result]
  : USE_IND OP i=ID CP { result = $i.text;}
  ;


// --------- Table Data Rows ----------


fragment t_data_rows_list returns [List<List<Data>> result]
@init { result = new ArrayList<List<Data>>(); }
  : ( d=t_data_rows {result.add(d);})+
  ;



fragment t_data_rows returns [List<Data> result]
@init { result = new ArrayList<Data>(); }
  : OP d=t_data_row {result.add(d);} ( CM d2=t_data_row {result.add(d2);})* CP SC
  ;

fragment t_data_row returns [Data result]
  : t=DECIMAL {result = new NumberData($t.text);}
  | t=INT     {result = new NumberData($t.text);}
  | s=string  {result = new TextData(s);}
  | NOW       {result = DateData.NOW;}
  | TRUE      {result = BooleanData.TRUE;}
  | FALSE     {result = BooleanData.FALSE;}
  | NULL      {result = CommonData.NULL;}

  ;

// ------------------------------End Tables ------------------------------------

fragment sequences returns [List<Sequence> result]
@init { result = new ArrayList<Sequence>(); }
  : (s=sequence { result.add(s); })+
  ;

fragment sequence returns [Sequence result]
  : SEQUENCE OP i=ID CP { result = new Sequence($i.text);}
  ;

// ------Generic rules --------

fragment ids returns [List<String> result]
@init { result = new ArrayList<String>(); }
  : i1=ID {result.add($i1.text);} (CM in=ID {result.add($in.text);})*
  ;

fragment string returns [String result]
  : s=STRING { result = ParserUtils.cleanFakeString($s.text);}
  ;
