// $ANTLR 2.7.2: "tcl.g" -> "TCLParser.java"$

package org.jacorb.notification.filter.etcl;

/*
 *        JacORB - a free Java ORB
 *
 *   Copyright (C) 1999-2011 Gerald Brose / The JacORB Team.
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Library General Public
 *   License as published by the Free Software Foundation; either
 *   version 2 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this library; if not, write to the Free
 *   Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */

public interface TCLParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int DOLLAR = 4;
	int EXIST = 5;
	int DOT = 6;
	int AND = 7;
	int OR = 8;
	int NOT = 9;
	int IN = 10;
	int IDENTIFIER = 11;
	int STRING = 12;
	int TRUE = 13;
	int FALSE = 14;
	int PLUS = 15;
	int MINUS = 16;
	int UNARY_PLUS = 17;
	int UNARY_MINUS = 18;
	int MULT = 19;
	int DIV = 20;
	int NUMBER = 21;
	int NUM_FLOAT = 22;
	int SUBSTR = 23;
	int GT = 24;
	int LT = 25;
	int GTE = 26;
	int LTE = 27;
	int EQ = 28;
	int NEQ = 29;
	int ARRAY = 30;
	int ASSOC = 31;
	int UNION_POS = 32;
	int IMPLICIT = 33;
	int DEFAULT = 38;
	int MIN = 39;
	int MAX = 40;
	int WITH = 41;
	int RANDOM = 42;
	int FIRST = 43;
	int TYPE = 44;
	int WS = 45;
	int LPAREN = 46;
	int RPAREN = 47;
	int LBRACKET = 48;
	int RBRACKET = 49;
	int DISCRIM = 50;
	int TYPE_ID = 51;
	int REPO_ID = 52;
	int LENGTH = 53;
	int RUNTIME_VAR = 54;
}
