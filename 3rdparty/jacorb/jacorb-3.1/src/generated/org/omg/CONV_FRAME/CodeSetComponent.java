package org.omg.CONV_FRAME;

/**
 * Generated from IDL struct "CodeSetComponent".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CodeSetComponent
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public CodeSetComponent(){}
	public int native_code_set;
	public int[] conversion_code_sets;
	public CodeSetComponent(int native_code_set, int[] conversion_code_sets)
	{
		this.native_code_set = native_code_set;
		this.conversion_code_sets = conversion_code_sets;
	}
}
