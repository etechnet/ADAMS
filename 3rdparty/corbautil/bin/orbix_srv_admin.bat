@echo off
REM --------------------------------------------------------------------
REM Copyright 2008 Ciaran McHale.
REM
REM Permission is hereby granted, free of charge, to any person obtaining a
REM copy of this software and associated documentation files (the
REM "Software"), to deal in the Software without restriction, including
REM without limitation the rights to use, copy, modify, merge, publish,
REM distribute, sublicense, and/or sell copies of the Software, and to
REM permit persons to whom the Software is furnished to do so, subject to
REM the following conditions:
REM
REM 	The above copyright notice and this permission notice shall be
REM 	included in all copies or substantial portions of the Software.  
REM
REM 	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
REM 	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
REM 	OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
REM 	NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
REM 	HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
REM 	WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
REM 	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
REM 	OTHER DEALINGS IN THE SOFTWARE.
REM --------------------------------------------------------------------
set TCL_SCRIPT=C:/corbautil/bin/orbix_srv_admin.tcl
itadmin %TCL_SCRIPT% -exe orbix_srv_admin %*
