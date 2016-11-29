package org.omg.DynamicAny;


/**
 * Abstract base class for implementations of local interface DynStruct
 * @author JacORB IDL compiler.
 */

public abstract class _DynStructLocalBase
	extends org.omg.CORBA.LocalObject
	implements DynStruct
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/DynamicAny/DynStruct:1.0","IDL:omg.org/DynamicAny/DynAny:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
