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

import com.iona.corba.IT_WorkQueue.*;


/**
 * Class that associated a label (name) with an Orbix work queue.
 */
public class LabelledOrbixWorkQueue
{
    /**
     * Create a LabelledOrbixWorkQueue with the specified label and work queue
     *
     * @param label		The label.
     * @param wq		The work queue.
     */
    public LabelledOrbixWorkQueue(String label, WorkQueue wq)
    {
    	m_label = label;
	m_wq    = wq;
    }





    /**
     * Accessor for the label.
     *
     * @return 		The label.
     */
    public String       label()    { return m_label; }





    /**
     * Accessor for the work queue.
     *
     * @return 		The work queue.
     */
    public WorkQueue    wq()       { return m_wq; }





    /**
     * The label
     */
    private String		m_label;





    /**
     * The work queue
     */
    private WorkQueue		m_wq;
}
