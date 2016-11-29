package org.omg.CONV_FRAME;

/**
 * Generated from IDL struct "CodeSetComponentInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CodeSetComponentInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public CodeSetComponentInfo(){}
	public org.omg.CONV_FRAME.CodeSetComponent ForCharData;
	public org.omg.CONV_FRAME.CodeSetComponent ForWcharData;
	public CodeSetComponentInfo(org.omg.CONV_FRAME.CodeSetComponent ForCharData, org.omg.CONV_FRAME.CodeSetComponent ForWcharData)
	{
		this.ForCharData = ForCharData;
		this.ForWcharData = ForWcharData;
	}
}
