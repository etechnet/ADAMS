//----------------------------------------------------------------------
// Copyright 2008 Ciaran McHale.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// 	The above copyright notice and this permission notice shall be
// 	included in all copies or substantial portions of the Software.  
//
// 	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// 	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// 	OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// 	NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// 	HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// 	WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// 	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// 	OTHER DEALINGS IN THE SOFTWARE.
//
// File:	p_omniorb_fix.h
//
// omniORB 4.0.x does not provide iostream streaming operators for CORBA
// exceptions or strings. Support for this iostreaming is planned for
// omniORB 4.1.
// 
// We provide the missing streaming operators here and ensure that this
// file is #include'd by the other "p_*.h" files in the P_USE_OMNIORB
// section.
//
// Note: the reason why omniORB does not provide these streaming operators
// is that some applications use <iostream> and other applicaions use
// <iostream.h>. The stream classes defined in these files (often called
// "standard iostreams" and "classic iostreams", respectively) are
// incompatible with each other on many platforms. Some C++ CORBA products
// force users to use a #define symbol to specify if they are using
// classic or standard iostreams. The omniORB approach to this problem is
// to not provide any streaming operators at all. This is unfortunate
// because the IDL-to-C++ mapping specification states that they should be
// provided.
//----------------------------------------------------------------------

#ifndef P_OMNIORB_FIX_H
#define P_OMNIORB_FIX_H

#if P_OMNIORB_VERSION < 410





#include "p_iostream.h"





//--------
// Output streaming operator for CORBA::Exception
//--------
inline ostream & operator << (ostream & out, const CORBA::Exception & ex)
{
	out << ex._rep_id();
	return out;
}


//--------
// Output streaming operators for string types
//--------
inline ostream & operator << (ostream & out, const CORBA::String_var & str)
{
	return out << str.in();
}
inline ostream & operator << (ostream & out, CORBA::String_out & str)
{
	return out << str.ptr();
}
inline ostream & operator << (ostream & out, const _CORBA_String_element & str)
{
	return out << str.in();
}
inline ostream & operator << (ostream & out, const _CORBA_String_member & str)
{
	return out << str.in();
}


//--------
// Input streaming operators for string types
//--------
inline istream & operator >> (istream & in, CORBA::String_var & str)
{
	return in >> str.inout();
}
inline istream & operator >> (istream & in, CORBA::String_out & str)
{
	return in >> str.ptr();
}
inline istream & operator >> (istream & in, _CORBA_String_element & str)
{
	return in >> str.inout();
}
inline istream & operator >> (istream & in, _CORBA_String_member & str)
{
	return in >> str.inout();
}


#endif

#endif
