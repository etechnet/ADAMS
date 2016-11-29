/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

/***************************************************************************
                          tree.cpp  -  description
                             -------------------
    begin                : Fri Nov 3 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************
  
   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include "tree.h"
#include "store.h"

#include <math.h>
#include <qmessagebox.h>

BinNode::~BinNode ()
{
    delete _pLeft;
    delete _pRight;
}

double NumNode::Calc () const
{
    return _num;
}

double VarNode::Calc () const
{
	double x = 0.0;
    if (_store.IsInit (_id ))
        x = _store.Value (_id);
	else
		 MathParser::errorPipe("Use of uninitialized variable");
    return x;
}

void VarNode::Assign (double val)
{
    _store.SetValue (_id, val);
}

bool VarNode::IsLvalue () const
{
    return true;
}

double UMinusNode::Calc () const
{
    return - _pNode->Calc();
}

double FunNode::Calc () const
{
    double val = 0;
    assert (_pFun != 0);
    val = (*_pFun)(_pNode->Calc());
    return val;
}

MultiNode::~MultiNode ()
{
    for (int i = 0; i < _iCur; ++i)
        delete _aChild [i];
}

double AssignNode::Calc () const
{
    double x = _pRight->Calc ();
    _pLeft->Assign (x);
    return x;
}

double SumNode::Calc () const
{
    if (_isError)
    {
        MathParser::errorPipe("Error: too many terms");
        return 0.0;
    }

    double sum = 0.0;
    for (int i = 0; i < _iCur; ++i)
    {
        double val = _aChild [i]->Calc ();
        if (_aIsPositive [i])
            sum += val;
        else
            sum -= val;
    }
    return sum;
}

// Notice: the calculation is left associative
double ProductNode::Calc () const
{
    if (_isError)
    {
        MathParser::errorPipe("Error: too many terms");
        return 0.0;
    }

    double prod = 1.0;
    for (int i = 0; i < _iCur; ++i)
    {
        double val = _aChild [i]->Calc ();
        if (_aIsPositive [i])
            prod *= val;
        else if (val != 0.0)
        {
            prod /= val;
        }
        else
        {
            MathParser::errorPipe("Error: division by zero");
            return HUGE_VAL;
        }
    }
    return prod;
}
