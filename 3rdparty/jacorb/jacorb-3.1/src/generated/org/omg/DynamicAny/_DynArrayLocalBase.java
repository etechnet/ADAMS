package org.omg.DynamicAny;


/**
 * Abstract base class for implementations of local interface DynArray
 * @author JacORB IDL compiler.
 */

public abstract class _DynArrayLocalBase
	extends org.omg.CORBA.LocalObject
	implements DynArray
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/DynamicAny/DynArray:1.0","IDL:omg.org/DynamicAny/DynAny:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
