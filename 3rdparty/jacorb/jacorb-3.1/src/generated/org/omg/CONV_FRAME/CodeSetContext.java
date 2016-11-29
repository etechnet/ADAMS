package org.omg.CONV_FRAME;

/**
 * Generated from IDL struct "CodeSetContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CodeSetContext
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public CodeSetContext(){}
	public int char_data;
	public int wchar_data;
	public CodeSetContext(int char_data, int wchar_data)
	{
		this.char_data = char_data;
		this.wchar_data = wchar_data;
	}
}
