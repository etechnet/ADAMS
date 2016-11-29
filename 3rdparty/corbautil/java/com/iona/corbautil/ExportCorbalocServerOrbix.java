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
import com.iona.corba.IT_PlainTextKey.Forwarder;
import com.iona.corba.IT_PlainTextKey.ForwarderHelper;

/**
 * An interface for exporting object references via Orbix-proprietary
 * corbaloc-server functionality.
 */

public class ExportCorbalocServerOrbix
	implements ExportCorbalocServer
{

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
		throws ImportExportException
    {
	org.omg.CORBA.Object		tmpObj;
	Forwarder			forwarder;
	String				name;

	name = instructions.substring("corbaloc_server#".length());
	try {
		tmpObj = orb.resolve_initial_references(
					"IT_PlainTextKeyForwarder");
		forwarder = ForwarderHelper.narrow(tmpObj);
		forwarder.add_plain_text_key(name, obj);
	} catch(Exception ex) {
		throw new ImportExportException("export failed for "
			+ "instructions '" + instructions
			+ "': " + ex);
	}
    }

}
