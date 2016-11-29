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
//----------------------------------------------------------------------

package com.iona.corbautil;
import org.omg.CORBA.*;

/**
 * Users can implement this interface to develop their own algorithms
 * for importing and exporting object references. For example, implement
 * this interface with a class called <code>com.acme.MyAlgorithm</code>.
 * Then, if you specify <code>"java_class#com.acme.MyAlgorithm"</code> as
 * the instructions, your implementation class will be dynamically loaded
 * and <code>importObjRef()</code> and <code>exportObjRef()</code> will be
 * invoked upon it.
 */

public interface ImportExportAlgorithm {

    /**
     * Exports the object reference using the specified instructions.
     * 
     * @param orb The orb.
     * @param obj The object
     * @param instructions The instructions
     */ 
    public void exportObjRef(
	ORB				orb,
	org.omg.CORBA.Object		obj,
	String				instructions)
		throws ImportExportException;

    /**
     * Imports the object reference using the specified instructions.
     * 
     * @param orb The orb.
     * @param instructions The instructions
     *
     * @return The object.
     */ 
    public org.omg.CORBA.Object importObjRef(
	ORB				orb,
	String				instructions)
		throws ImportExportException;

}
