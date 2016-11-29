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
import java.io.*;


/**
 *
 * Example of how to implement the ImportExportAlgorithm interface.
 * To use this implementation, specify
 * "java_class#com.iona.corbautil.ImportExportExampleAlgorithm"
 * as the instructions: this class will be dynamically loaded and used.
 * <p>
 * This class imports object reference by reading them from standard
 * input. It exports object reference by writing them to standard output.
 */

public class ImportExportExampleAlgorithm
	implements ImportExportAlgorithm
{
    /**
     * Writes the IOR and the instructions to the console.
     *
     * @param orb The orb.
     * @param obj The object
     * @param instructions The instructions
     *
     */
    public void exportObjRef(
	ORB				orb,
	org.omg.CORBA.Object		obj,
	String				instructions)
		throws ImportExportException
    {
	String				strIOR = null;
	try {
		strIOR = orb.object_to_string(obj);
	} catch (Exception ex) {
		throw new ImportExportException("export failed for "
			+ "instructions '" + instructions
			+ "': object_to_string() failed: " + ex);
	}
	System.out.println("instructions = '" + instructions + "'");
	System.out.println("IOR = " + strIOR);
    }

    /**
     * Writes the instructions to the console; then waits for a
     * stringified object references to be typed in. This is
     * unstringified and returned.
     *
     * @param orb The orb.
     * @param instructions The instructions
     *
     * @return The object.
     */
    public org.omg.CORBA.Object importObjRef(
	ORB				orb,
	String				instructions)
		throws ImportExportException
    {
	System.out.println("instructions:   " + instructions);
	System.out.println("Please type in a stringified object reference: ");
	try {
		BufferedReader	stdin = new BufferedReader(
					     new InputStreamReader(System.in));
		String strIOR = stdin.readLine();
		return orb.string_to_object(strIOR);
	} catch (Exception ex) {
		throw new ImportExportException("import failed for "
			+ "instructions '" + instructions
			+ "': error importing stringified object "
			+ "reference from the console: " + ex);
	}
    }
}
