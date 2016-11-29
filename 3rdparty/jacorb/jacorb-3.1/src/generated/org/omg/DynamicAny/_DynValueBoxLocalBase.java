package org.omg.DynamicAny;


/**
 * Abstract base class for implementations of local interface DynValueBox
 * @author JacORB IDL compiler.
 */

public abstract class _DynValueBoxLocalBase
	extends org.omg.CORBA.LocalObject
	implements DynValueBox
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/DynamicAny/DynValueBox:1.0","IDL:omg.org/DynamicAny/DynValueCommon:1.0","IDL:omg.org/DynamicAny/DynAny:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
