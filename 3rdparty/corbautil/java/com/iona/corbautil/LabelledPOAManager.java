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

import org.omg.PortableServer.*;


/**
 * Class that associated a label (name) with a POA Manager.
 * The reason for doing this is that some CORBA products provide
 * proprietary capabilities that allow a POA Manager to listen on
 * a fixed port. Such capabilities typically require that the POA
 * manager has a label (name).
 */
public class LabelledPOAManager
{
    /**
     * Create a LabelledPOAManager with the specified label and POA Manager
     *
     * @param label		The label.
     * @param mgr		The POA Manager.
     */
    public LabelledPOAManager(String label, POAManager mgr)
    {
    	m_label = label;
	m_mgr   = mgr;
    }





    /**
     * Accessor for the label.
     *
     * @return 		The label.
     */
    public String        label()    { return m_label; }





    /**
     * Accessor for the POA Manager.
     *
     * @return 		The POA Manager.
     */
    public POAManager    mgr()      { return m_mgr; }





    /**
     * The label
     */
    private String		m_label;





    /**
     * The POA Manager
     */
    private POAManager		m_mgr;
}
