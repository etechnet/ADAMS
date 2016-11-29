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

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

import antlr.TokenStreamSelector;
import java.io.StringReader;
import org.jacorb.notification.filter.ParseException;

public class TCLParser extends antlr.LLkParser       implements TCLParserTokenTypes
 {

public static AbstractTCLNode parse( String data )
		throws ParseException
{
   try
   {
      final TokenStreamSelector _selector = new TokenStreamSelector();

      // set up two Lexers
      final TCLLexer _tclLexer = new TCLLexer( new StringReader( data ) );
      _tclLexer.setTokenStreamSelector( _selector );

      final ComponentLexer _compLexer =
         new ComponentLexer( _tclLexer.getInputState() );

      _compLexer.setTokenStreamSelector( _selector );

      _selector.addInputStream( _tclLexer,
                                TCLLexer.LEXER_NAME );

      _selector.addInputStream( _compLexer,
                                ComponentLexer.LEXER_NAME );

      _selector.select( TCLLexer.LEXER_NAME );

      // connect the Parser with the two Lexers
      final TCLParser _parser = new TCLParser( _selector );

      // begin parse
      _parser.startRule();

      // return AST tree
      return ( AbstractTCLNode ) _parser.getAST();
   }
   catch (TokenStreamException e)
   {
      throw new ParseException(data, e);
   }
   catch (RecognitionException e)
   {
      throw new ParseException(data, e);
   }
}


protected TCLParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public TCLParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected TCLParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public TCLParser(TokenStream lexer) {
  this(lexer,2);
}

public TCLParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void startRule() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST startRule_AST = null;
		
		constraint();
		astFactory.addASTChild(currentAST, returnAST);
		startRule_AST = (AST)currentAST.root;
		returnAST = startRule_AST;
	}
	
	public final void constraint() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constraint_AST = null;
		
		switch ( LA(1)) {
		case EOF:
		{
			constraint_AST = (AST)currentAST.root;
			break;
		}
		case DOLLAR:
		case EXIST:
		case NOT:
		case IDENTIFIER:
		case STRING:
		case TRUE:
		case FALSE:
		case PLUS:
		case MINUS:
		case NUMBER:
		case NUM_FLOAT:
		case DEFAULT:
		case LPAREN:
		{
			bool();
			astFactory.addASTChild(currentAST, returnAST);
			constraint_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = constraint_AST;
	}
	
	public final void bool() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bool_AST = null;
		
		bool_or();
		astFactory.addASTChild(currentAST, returnAST);
		bool_AST = (AST)currentAST.root;
		returnAST = bool_AST;
	}
	
	public final void preference() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST preference_AST = null;
		
		switch ( LA(1)) {
		case EOF:
		{
			preference_AST = (AST)currentAST.root;
			break;
		}
		case MIN:
		{
			AST tmp1_AST = null;
			tmp1_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp1_AST);
			match(MIN);
			bool();
			astFactory.addASTChild(currentAST, returnAST);
			preference_AST = (AST)currentAST.root;
			break;
		}
		case MAX:
		{
			AST tmp2_AST = null;
			tmp2_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp2_AST);
			match(MAX);
			bool();
			astFactory.addASTChild(currentAST, returnAST);
			preference_AST = (AST)currentAST.root;
			break;
		}
		case WITH:
		{
			AST tmp3_AST = null;
			tmp3_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp3_AST);
			match(WITH);
			bool();
			astFactory.addASTChild(currentAST, returnAST);
			preference_AST = (AST)currentAST.root;
			break;
		}
		case RANDOM:
		{
			AST tmp4_AST = null;
			tmp4_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp4_AST);
			match(RANDOM);
			preference_AST = (AST)currentAST.root;
			break;
		}
		case FIRST:
		{
			AST tmp5_AST = null;
			tmp5_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp5_AST);
			match(FIRST);
			preference_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = preference_AST;
	}
	
	public final void bool_or() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bool_or_AST = null;
		
		bool_and();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop7:
		do {
			if ((LA(1)==OR)) {
				org.jacorb.notification.filter.etcl.OrOperator tmp6_AST = null;
				tmp6_AST = (org.jacorb.notification.filter.etcl.OrOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.OrOperator");
				astFactory.makeASTRoot(currentAST, tmp6_AST);
				match(OR);
				bool_and();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop7;
			}
			
		} while (true);
		}
		bool_or_AST = (AST)currentAST.root;
		returnAST = bool_or_AST;
	}
	
	public final void bool_and() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bool_and_AST = null;
		
		bool_compare();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop10:
		do {
			if ((LA(1)==AND)) {
				org.jacorb.notification.filter.etcl.AndOperator tmp7_AST = null;
				tmp7_AST = (org.jacorb.notification.filter.etcl.AndOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.AndOperator");
				astFactory.makeASTRoot(currentAST, tmp7_AST);
				match(AND);
				bool_compare();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop10;
			}
			
		} while (true);
		}
		bool_and_AST = (AST)currentAST.root;
		returnAST = bool_and_AST;
	}
	
	public final void bool_compare() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bool_compare_AST = null;
		
		expr_in();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop14:
		do {
			if (((LA(1) >= GT && LA(1) <= NEQ))) {
				{
				switch ( LA(1)) {
				case EQ:
				{
					org.jacorb.notification.filter.etcl.EqOperator tmp8_AST = null;
					tmp8_AST = (org.jacorb.notification.filter.etcl.EqOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.EqOperator");
					astFactory.makeASTRoot(currentAST, tmp8_AST);
					match(EQ);
					break;
				}
				case NEQ:
				{
					org.jacorb.notification.filter.etcl.NeqOperator tmp9_AST = null;
					tmp9_AST = (org.jacorb.notification.filter.etcl.NeqOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.NeqOperator");
					astFactory.makeASTRoot(currentAST, tmp9_AST);
					match(NEQ);
					break;
				}
				case LT:
				{
					org.jacorb.notification.filter.etcl.LtOperator tmp10_AST = null;
					tmp10_AST = (org.jacorb.notification.filter.etcl.LtOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.LtOperator");
					astFactory.makeASTRoot(currentAST, tmp10_AST);
					match(LT);
					break;
				}
				case LTE:
				{
					org.jacorb.notification.filter.etcl.LteOperator tmp11_AST = null;
					tmp11_AST = (org.jacorb.notification.filter.etcl.LteOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.LteOperator");
					astFactory.makeASTRoot(currentAST, tmp11_AST);
					match(LTE);
					break;
				}
				case GT:
				{
					org.jacorb.notification.filter.etcl.GtOperator tmp12_AST = null;
					tmp12_AST = (org.jacorb.notification.filter.etcl.GtOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.GtOperator");
					astFactory.makeASTRoot(currentAST, tmp12_AST);
					match(GT);
					break;
				}
				case GTE:
				{
					org.jacorb.notification.filter.etcl.GteOperator tmp13_AST = null;
					tmp13_AST = (org.jacorb.notification.filter.etcl.GteOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.GteOperator");
					astFactory.makeASTRoot(currentAST, tmp13_AST);
					match(GTE);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expr_in();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop14;
			}
			
		} while (true);
		}
		bool_compare_AST = (AST)currentAST.root;
		returnAST = bool_compare_AST;
	}
	
	public final void expr_in() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_in_AST = null;
		
		expr_twiddle();
		astFactory.addASTChild(currentAST, returnAST);
		{
		if ((LA(1)==IN) && (LA(2)==IDENTIFIER)) {
			{
			org.jacorb.notification.filter.etcl.InOperator tmp14_AST = null;
			tmp14_AST = (org.jacorb.notification.filter.etcl.InOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.InOperator");
			astFactory.makeASTRoot(currentAST, tmp14_AST);
			match(IN);
			org.jacorb.notification.filter.etcl.IdentValue tmp15_AST = null;
			tmp15_AST = (org.jacorb.notification.filter.etcl.IdentValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.IdentValue");
			astFactory.addASTChild(currentAST, tmp15_AST);
			match(IDENTIFIER);
			}
		}
		else if ((LA(1)==IN) && (LA(2)==DOLLAR)) {
			{
			org.jacorb.notification.filter.etcl.InOperator tmp16_AST = null;
			tmp16_AST = (org.jacorb.notification.filter.etcl.InOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.InOperator");
			astFactory.makeASTRoot(currentAST, tmp16_AST);
			match(IN);
			dollarComponent();
			astFactory.addASTChild(currentAST, returnAST);
			}
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		expr_in_AST = (AST)currentAST.root;
		returnAST = expr_in_AST;
	}
	
	public final void expr_twiddle() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_twiddle_AST = null;
		
		expr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop22:
		do {
			if ((LA(1)==SUBSTR)) {
				{
				org.jacorb.notification.filter.etcl.SubstrOperator tmp17_AST = null;
				tmp17_AST = (org.jacorb.notification.filter.etcl.SubstrOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.SubstrOperator");
				astFactory.makeASTRoot(currentAST, tmp17_AST);
				match(SUBSTR);
				}
				expr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop22;
			}
			
		} while (true);
		}
		expr_twiddle_AST = (AST)currentAST.root;
		returnAST = expr_twiddle_AST;
	}
	
	public final void dollarComponent() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dollarComponent_AST = null;
		
		org.jacorb.notification.filter.etcl.ETCLComponentName tmp18_AST = null;
		tmp18_AST = (org.jacorb.notification.filter.etcl.ETCLComponentName)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.ETCLComponentName");
		astFactory.makeASTRoot(currentAST, tmp18_AST);
		match(DOLLAR);
		component();
		astFactory.addASTChild(currentAST, returnAST);
		dollarComponent_AST = (AST)currentAST.root;
		returnAST = dollarComponent_AST;
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_AST = null;
		
		term();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop26:
		do {
			if ((LA(1)==PLUS||LA(1)==MINUS)) {
				{
				switch ( LA(1)) {
				case PLUS:
				{
					org.jacorb.notification.filter.etcl.PlusOperator tmp19_AST = null;
					tmp19_AST = (org.jacorb.notification.filter.etcl.PlusOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.PlusOperator");
					astFactory.makeASTRoot(currentAST, tmp19_AST);
					match(PLUS);
					break;
				}
				case MINUS:
				{
					org.jacorb.notification.filter.etcl.MinusOperator tmp20_AST = null;
					tmp20_AST = (org.jacorb.notification.filter.etcl.MinusOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.MinusOperator");
					astFactory.makeASTRoot(currentAST, tmp20_AST);
					match(MINUS);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				term();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop26;
			}
			
		} while (true);
		}
		expr_AST = (AST)currentAST.root;
		returnAST = expr_AST;
	}
	
	public final void term() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST term_AST = null;
		
		factor_not();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop30:
		do {
			if ((LA(1)==MULT||LA(1)==DIV)) {
				{
				switch ( LA(1)) {
				case MULT:
				{
					org.jacorb.notification.filter.etcl.MultOperator tmp21_AST = null;
					tmp21_AST = (org.jacorb.notification.filter.etcl.MultOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.MultOperator");
					astFactory.makeASTRoot(currentAST, tmp21_AST);
					match(MULT);
					break;
				}
				case DIV:
				{
					org.jacorb.notification.filter.etcl.DivOperator tmp22_AST = null;
					tmp22_AST = (org.jacorb.notification.filter.etcl.DivOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.DivOperator");
					astFactory.makeASTRoot(currentAST, tmp22_AST);
					match(DIV);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				factor_not();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop30;
			}
			
		} while (true);
		}
		term_AST = (AST)currentAST.root;
		returnAST = term_AST;
	}
	
	public final void factor_not() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST factor_not_AST = null;
		
		{
		switch ( LA(1)) {
		case NOT:
		{
			org.jacorb.notification.filter.etcl.NotOperator tmp23_AST = null;
			tmp23_AST = (org.jacorb.notification.filter.etcl.NotOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.NotOperator");
			astFactory.makeASTRoot(currentAST, tmp23_AST);
			match(NOT);
			break;
		}
		case DOLLAR:
		case EXIST:
		case IDENTIFIER:
		case STRING:
		case TRUE:
		case FALSE:
		case PLUS:
		case MINUS:
		case NUMBER:
		case NUM_FLOAT:
		case DEFAULT:
		case LPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		factor();
		astFactory.addASTChild(currentAST, returnAST);
		factor_not_AST = (AST)currentAST.root;
		returnAST = factor_not_AST;
	}
	
	public final void factor() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST factor_AST = null;
		
		switch ( LA(1)) {
		case LPAREN:
		{
			match(LPAREN);
			bool_or();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case DOLLAR:
		{
			dollarComponent();
			astFactory.addASTChild(currentAST, returnAST);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case DEFAULT:
		{
			org.jacorb.notification.filter.etcl.DefaultOperator tmp26_AST = null;
			tmp26_AST = (org.jacorb.notification.filter.etcl.DefaultOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.DefaultOperator");
			astFactory.makeASTRoot(currentAST, tmp26_AST);
			match(DEFAULT);
			dollarComponent();
			astFactory.addASTChild(currentAST, returnAST);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case IDENTIFIER:
		{
			org.jacorb.notification.filter.etcl.IdentValue tmp27_AST = null;
			tmp27_AST = (org.jacorb.notification.filter.etcl.IdentValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.IdentValue");
			astFactory.addASTChild(currentAST, tmp27_AST);
			match(IDENTIFIER);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case NUMBER:
		case NUM_FLOAT:
		{
			number();
			astFactory.addASTChild(currentAST, returnAST);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case PLUS:
		{
			org.jacorb.notification.filter.etcl.PlusOperator tmp28_AST = null;
			tmp28_AST = (org.jacorb.notification.filter.etcl.PlusOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.PlusOperator");
			astFactory.makeASTRoot(currentAST, tmp28_AST);
			match(PLUS);
			number();
			astFactory.addASTChild(currentAST, returnAST);
			tmp28_AST.setType(UNARY_PLUS);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case MINUS:
		{
			org.jacorb.notification.filter.etcl.MinusOperator tmp29_AST = null;
			tmp29_AST = (org.jacorb.notification.filter.etcl.MinusOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.MinusOperator");
			astFactory.makeASTRoot(currentAST, tmp29_AST);
			match(MINUS);
			number();
			astFactory.addASTChild(currentAST, returnAST);
			tmp29_AST.setType(UNARY_MINUS);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case STRING:
		{
			org.jacorb.notification.filter.etcl.StringValue tmp30_AST = null;
			tmp30_AST = (org.jacorb.notification.filter.etcl.StringValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.StringValue");
			astFactory.addASTChild(currentAST, tmp30_AST);
			match(STRING);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case TRUE:
		{
			org.jacorb.notification.filter.etcl.BoolValue tmp31_AST = null;
			tmp31_AST = (org.jacorb.notification.filter.etcl.BoolValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.BoolValue");
			astFactory.addASTChild(currentAST, tmp31_AST);
			match(TRUE);
			factor_AST = (AST)currentAST.root;
			break;
		}
		case FALSE:
		{
			org.jacorb.notification.filter.etcl.BoolValue tmp32_AST = null;
			tmp32_AST = (org.jacorb.notification.filter.etcl.BoolValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.BoolValue");
			astFactory.addASTChild(currentAST, tmp32_AST);
			match(FALSE);
			factor_AST = (AST)currentAST.root;
			break;
		}
		default:
			if ((LA(1)==EXIST) && (LA(2)==IDENTIFIER)) {
				org.jacorb.notification.filter.etcl.ExistOperator tmp33_AST = null;
				tmp33_AST = (org.jacorb.notification.filter.etcl.ExistOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.ExistOperator");
				astFactory.makeASTRoot(currentAST, tmp33_AST);
				match(EXIST);
				org.jacorb.notification.filter.etcl.IdentValue tmp34_AST = null;
				tmp34_AST = (org.jacorb.notification.filter.etcl.IdentValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.IdentValue");
				astFactory.addASTChild(currentAST, tmp34_AST);
				match(IDENTIFIER);
				factor_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==EXIST) && (LA(2)==DOLLAR)) {
				org.jacorb.notification.filter.etcl.ExistOperator tmp35_AST = null;
				tmp35_AST = (org.jacorb.notification.filter.etcl.ExistOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.ExistOperator");
				astFactory.makeASTRoot(currentAST, tmp35_AST);
				match(EXIST);
				dollarComponent();
				astFactory.addASTChild(currentAST, returnAST);
				factor_AST = (AST)currentAST.root;
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = factor_AST;
	}
	
	public final void number() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST number_AST = null;
		
		switch ( LA(1)) {
		case NUMBER:
		{
			org.jacorb.notification.filter.etcl.NumberValue tmp36_AST = null;
			tmp36_AST = (org.jacorb.notification.filter.etcl.NumberValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.NumberValue");
			astFactory.addASTChild(currentAST, tmp36_AST);
			match(NUMBER);
			number_AST = (AST)currentAST.root;
			break;
		}
		case NUM_FLOAT:
		{
			org.jacorb.notification.filter.etcl.NumberValue tmp37_AST = null;
			tmp37_AST = (org.jacorb.notification.filter.etcl.NumberValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.NumberValue");
			astFactory.addASTChild(currentAST, tmp37_AST);
			match(NUM_FLOAT);
			number_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = number_AST;
	}
	
	public final void component() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST component_AST = null;
		
		switch ( LA(1)) {
		case EOF:
		case AND:
		case OR:
		case IN:
		case PLUS:
		case MINUS:
		case MULT:
		case DIV:
		case SUBSTR:
		case GT:
		case LT:
		case GTE:
		case LTE:
		case EQ:
		case NEQ:
		case RPAREN:
		{
			component_AST = (AST)currentAST.root;
			break;
		}
		case DOT:
		{
			org.jacorb.notification.filter.etcl.DotOperator tmp38_AST = null;
			tmp38_AST = (org.jacorb.notification.filter.etcl.DotOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.DotOperator");
			astFactory.addASTChild(currentAST, tmp38_AST);
			match(DOT);
			compDot();
			astFactory.addASTChild(currentAST, returnAST);
			component_AST = (AST)currentAST.root;
			break;
		}
		case LBRACKET:
		{
			compArray();
			astFactory.addASTChild(currentAST, returnAST);
			component_AST = (AST)currentAST.root;
			break;
		}
		case LPAREN:
		{
			compAssoc();
			astFactory.addASTChild(currentAST, returnAST);
			component_AST = (AST)currentAST.root;
			break;
		}
		case IDENTIFIER:
		{
			org.jacorb.notification.filter.etcl.RuntimeVariableNode tmp39_AST = null;
			tmp39_AST = (org.jacorb.notification.filter.etcl.RuntimeVariableNode)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.RuntimeVariableNode");
			astFactory.addASTChild(currentAST, tmp39_AST);
			match(IDENTIFIER);
			tmp39_AST.setType(RUNTIME_VAR);
			compExt();
			astFactory.addASTChild(currentAST, returnAST);
			component_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = component_AST;
	}
	
	public final void compDot() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST compDot_AST = null;
		
		switch ( LA(1)) {
		case IDENTIFIER:
		{
			org.jacorb.notification.filter.etcl.IdentValue tmp40_AST = null;
			tmp40_AST = (org.jacorb.notification.filter.etcl.IdentValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.IdentValue");
			astFactory.addASTChild(currentAST, tmp40_AST);
			match(IDENTIFIER);
			compExt();
			astFactory.addASTChild(currentAST, returnAST);
			compDot_AST = (AST)currentAST.root;
			break;
		}
		case NUMBER:
		{
			compPos();
			astFactory.addASTChild(currentAST, returnAST);
			compDot_AST = (AST)currentAST.root;
			break;
		}
		case LPAREN:
		{
			unionPos();
			astFactory.addASTChild(currentAST, returnAST);
			compDot_AST = (AST)currentAST.root;
			break;
		}
		case LENGTH:
		{
			org.jacorb.notification.filter.etcl.ImplicitOperatorNode tmp41_AST = null;
			tmp41_AST = (org.jacorb.notification.filter.etcl.ImplicitOperatorNode)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.ImplicitOperatorNode");
			astFactory.addASTChild(currentAST, tmp41_AST);
			match(LENGTH);
			tmp41_AST.setType(IMPLICIT);
			compDot_AST = (AST)currentAST.root;
			break;
		}
		case DISCRIM:
		{
			org.jacorb.notification.filter.etcl.ImplicitOperatorNode tmp42_AST = null;
			tmp42_AST = (org.jacorb.notification.filter.etcl.ImplicitOperatorNode)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.ImplicitOperatorNode");
			astFactory.addASTChild(currentAST, tmp42_AST);
			match(DISCRIM);
			tmp42_AST.setType(IMPLICIT);
			compDot_AST = (AST)currentAST.root;
			break;
		}
		case TYPE_ID:
		{
			org.jacorb.notification.filter.etcl.ImplicitOperatorNode tmp43_AST = null;
			tmp43_AST = (org.jacorb.notification.filter.etcl.ImplicitOperatorNode)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.ImplicitOperatorNode");
			astFactory.addASTChild(currentAST, tmp43_AST);
			match(TYPE_ID);
			tmp43_AST.setType(IMPLICIT);
			compDot_AST = (AST)currentAST.root;
			break;
		}
		case REPO_ID:
		{
			org.jacorb.notification.filter.etcl.ImplicitOperatorNode tmp44_AST = null;
			tmp44_AST = (org.jacorb.notification.filter.etcl.ImplicitOperatorNode)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.ImplicitOperatorNode");
			astFactory.addASTChild(currentAST, tmp44_AST);
			match(REPO_ID);
			tmp44_AST.setType(IMPLICIT);
			compDot_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = compDot_AST;
	}
	
	public final void compArray() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST compArray_AST = null;
		
		match(LBRACKET);
		org.jacorb.notification.filter.etcl.ArrayOperator tmp46_AST = null;
		tmp46_AST = (org.jacorb.notification.filter.etcl.ArrayOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.ArrayOperator");
		astFactory.addASTChild(currentAST, tmp46_AST);
		match(NUMBER);
		tmp46_AST.setType(ARRAY);
		match(RBRACKET);
		compExt();
		astFactory.addASTChild(currentAST, returnAST);
		compArray_AST = (AST)currentAST.root;
		returnAST = compArray_AST;
	}
	
	public final void compAssoc() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST compAssoc_AST = null;
		
		match(LPAREN);
		org.jacorb.notification.filter.etcl.AssocOperator tmp49_AST = null;
		tmp49_AST = (org.jacorb.notification.filter.etcl.AssocOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.AssocOperator");
		astFactory.addASTChild(currentAST, tmp49_AST);
		match(IDENTIFIER);
		tmp49_AST.setType(ASSOC);
		match(RPAREN);
		compExt();
		astFactory.addASTChild(currentAST, returnAST);
		compAssoc_AST = (AST)currentAST.root;
		returnAST = compAssoc_AST;
	}
	
	public final void compExt() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST compExt_AST = null;
		
		switch ( LA(1)) {
		case EOF:
		case AND:
		case OR:
		case IN:
		case PLUS:
		case MINUS:
		case MULT:
		case DIV:
		case SUBSTR:
		case GT:
		case LT:
		case GTE:
		case LTE:
		case EQ:
		case NEQ:
		case RPAREN:
		{
			compExt_AST = (AST)currentAST.root;
			break;
		}
		case DOT:
		{
			org.jacorb.notification.filter.etcl.DotOperator tmp51_AST = null;
			tmp51_AST = (org.jacorb.notification.filter.etcl.DotOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.DotOperator");
			astFactory.addASTChild(currentAST, tmp51_AST);
			match(DOT);
			compDot();
			astFactory.addASTChild(currentAST, returnAST);
			compExt_AST = (AST)currentAST.root;
			break;
		}
		case LBRACKET:
		{
			compArray();
			astFactory.addASTChild(currentAST, returnAST);
			compExt_AST = (AST)currentAST.root;
			break;
		}
		case LPAREN:
		{
			compAssoc();
			astFactory.addASTChild(currentAST, returnAST);
			compExt_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = compExt_AST;
	}
	
	public final void compPos() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST compPos_AST = null;
		
		org.jacorb.notification.filter.etcl.NumberValue tmp52_AST = null;
		tmp52_AST = (org.jacorb.notification.filter.etcl.NumberValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.NumberValue");
		astFactory.addASTChild(currentAST, tmp52_AST);
		match(NUMBER);
		compExt();
		astFactory.addASTChild(currentAST, returnAST);
		compPos_AST = (AST)currentAST.root;
		returnAST = compPos_AST;
	}
	
	public final void unionPos() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unionPos_AST = null;
		
		org.jacorb.notification.filter.etcl.UnionPositionOperator tmp53_AST = null;
		tmp53_AST = (org.jacorb.notification.filter.etcl.UnionPositionOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.UnionPositionOperator");
		astFactory.addASTChild(currentAST, tmp53_AST);
		match(LPAREN);
		tmp53_AST.setType(UNION_POS);
		unionVal();
		astFactory.addASTChild(currentAST, returnAST);
		match(RPAREN);
		compExt();
		astFactory.addASTChild(currentAST, returnAST);
		unionPos_AST = (AST)currentAST.root;
		returnAST = unionPos_AST;
	}
	
	public final void unionVal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unionVal_AST = null;
		
		switch ( LA(1)) {
		case RPAREN:
		{
			unionVal_AST = (AST)currentAST.root;
			break;
		}
		case NUMBER:
		{
			org.jacorb.notification.filter.etcl.NumberValue tmp55_AST = null;
			tmp55_AST = (org.jacorb.notification.filter.etcl.NumberValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.NumberValue");
			astFactory.addASTChild(currentAST, tmp55_AST);
			match(NUMBER);
			unionVal_AST = (AST)currentAST.root;
			break;
		}
		case PLUS:
		{
			org.jacorb.notification.filter.etcl.PlusOperator tmp56_AST = null;
			tmp56_AST = (org.jacorb.notification.filter.etcl.PlusOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.PlusOperator");
			astFactory.makeASTRoot(currentAST, tmp56_AST);
			match(PLUS);
			org.jacorb.notification.filter.etcl.NumberValue tmp57_AST = null;
			tmp57_AST = (org.jacorb.notification.filter.etcl.NumberValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.NumberValue");
			astFactory.addASTChild(currentAST, tmp57_AST);
			match(NUMBER);
			unionVal_AST = (AST)currentAST.root;
			break;
		}
		case MINUS:
		{
			org.jacorb.notification.filter.etcl.MinusOperator tmp58_AST = null;
			tmp58_AST = (org.jacorb.notification.filter.etcl.MinusOperator)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.MinusOperator");
			astFactory.makeASTRoot(currentAST, tmp58_AST);
			match(MINUS);
			org.jacorb.notification.filter.etcl.NumberValue tmp59_AST = null;
			tmp59_AST = (org.jacorb.notification.filter.etcl.NumberValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.NumberValue");
			astFactory.addASTChild(currentAST, tmp59_AST);
			match(NUMBER);
			unionVal_AST = (AST)currentAST.root;
			break;
		}
		case STRING:
		{
			org.jacorb.notification.filter.etcl.StringValue tmp60_AST = null;
			tmp60_AST = (org.jacorb.notification.filter.etcl.StringValue)astFactory.create(LT(1),"org.jacorb.notification.filter.etcl.StringValue");
			astFactory.addASTChild(currentAST, tmp60_AST);
			match(STRING);
			unionVal_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = unionVal_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"DOLLAR",
		"EXIST",
		"DOT",
		"AND",
		"OR",
		"NOT",
		"IN",
		"IDENTIFIER",
		"STRING",
		"TRUE",
		"FALSE",
		"PLUS",
		"MINUS",
		"UNARY_PLUS",
		"UNARY_MINUS",
		"MULT",
		"DIV",
		"NUMBER",
		"NUM_FLOAT",
		"SUBSTR",
		"GT",
		"LT",
		"GTE",
		"LTE",
		"EQ",
		"NEQ",
		"ARRAY",
		"ASSOC",
		"UNION_POS",
		"IMPLICIT",
		"<34>",
		"<35>",
		"<36>",
		"<37>",
		"DEFAULT",
		"MIN",
		"MAX",
		"WITH",
		"RANDOM",
		"FIRST",
		"TYPE",
		"WS",
		"LPAREN",
		"RPAREN",
		"LBRACKET",
		"RBRACKET",
		"DISCRIM",
		"TYPE_ID",
		"REPO_ID",
		"LENGTH",
		"RUNTIME_VAR"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap = new Hashtable();
		tokenTypeToASTClassMap.put(new Integer(DOLLAR), org.jacorb.notification.filter.etcl.ETCLComponentName.class);
		tokenTypeToASTClassMap.put(new Integer(EXIST), org.jacorb.notification.filter.etcl.ExistOperator.class);
		tokenTypeToASTClassMap.put(new Integer(DOT), org.jacorb.notification.filter.etcl.DotOperator.class);
		tokenTypeToASTClassMap.put(new Integer(AND), org.jacorb.notification.filter.etcl.AndOperator.class);
		tokenTypeToASTClassMap.put(new Integer(OR), org.jacorb.notification.filter.etcl.OrOperator.class);
		tokenTypeToASTClassMap.put(new Integer(NOT), org.jacorb.notification.filter.etcl.NotOperator.class);
		tokenTypeToASTClassMap.put(new Integer(IN), org.jacorb.notification.filter.etcl.InOperator.class);
		tokenTypeToASTClassMap.put(new Integer(IDENTIFIER), org.jacorb.notification.filter.etcl.IdentValue.class);
		tokenTypeToASTClassMap.put(new Integer(STRING), org.jacorb.notification.filter.etcl.StringValue.class);
		tokenTypeToASTClassMap.put(new Integer(TRUE), org.jacorb.notification.filter.etcl.BoolValue.class);
		tokenTypeToASTClassMap.put(new Integer(FALSE), org.jacorb.notification.filter.etcl.BoolValue.class);
		tokenTypeToASTClassMap.put(new Integer(PLUS), org.jacorb.notification.filter.etcl.PlusOperator.class);
		tokenTypeToASTClassMap.put(new Integer(MINUS), org.jacorb.notification.filter.etcl.MinusOperator.class);
		tokenTypeToASTClassMap.put(new Integer(MULT), org.jacorb.notification.filter.etcl.MultOperator.class);
		tokenTypeToASTClassMap.put(new Integer(DIV), org.jacorb.notification.filter.etcl.DivOperator.class);
		tokenTypeToASTClassMap.put(new Integer(NUMBER), org.jacorb.notification.filter.etcl.NumberValue.class);
		tokenTypeToASTClassMap.put(new Integer(NUM_FLOAT), org.jacorb.notification.filter.etcl.NumberValue.class);
		tokenTypeToASTClassMap.put(new Integer(SUBSTR), org.jacorb.notification.filter.etcl.SubstrOperator.class);
		tokenTypeToASTClassMap.put(new Integer(GT), org.jacorb.notification.filter.etcl.GtOperator.class);
		tokenTypeToASTClassMap.put(new Integer(LT), org.jacorb.notification.filter.etcl.LtOperator.class);
		tokenTypeToASTClassMap.put(new Integer(GTE), org.jacorb.notification.filter.etcl.GteOperator.class);
		tokenTypeToASTClassMap.put(new Integer(LTE), org.jacorb.notification.filter.etcl.LteOperator.class);
		tokenTypeToASTClassMap.put(new Integer(EQ), org.jacorb.notification.filter.etcl.EqOperator.class);
		tokenTypeToASTClassMap.put(new Integer(NEQ), org.jacorb.notification.filter.etcl.NeqOperator.class);
		tokenTypeToASTClassMap.put(new Integer(DEFAULT), org.jacorb.notification.filter.etcl.DefaultOperator.class);
		tokenTypeToASTClassMap.put(new Integer(DISCRIM), org.jacorb.notification.filter.etcl.ImplicitOperatorNode.class);
		tokenTypeToASTClassMap.put(new Integer(TYPE_ID), org.jacorb.notification.filter.etcl.ImplicitOperatorNode.class);
		tokenTypeToASTClassMap.put(new Integer(REPO_ID), org.jacorb.notification.filter.etcl.ImplicitOperatorNode.class);
		tokenTypeToASTClassMap.put(new Integer(LENGTH), org.jacorb.notification.filter.etcl.ImplicitOperatorNode.class);
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 140738545320322L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	
	}
