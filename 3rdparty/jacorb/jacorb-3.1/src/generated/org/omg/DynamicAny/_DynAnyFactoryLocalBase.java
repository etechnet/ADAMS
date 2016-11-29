package org.omg.DynamicAny;


/**
 * Abstract base class for implementations of local interface DynAnyFactory
 * @author JacORB IDL compiler.
 */

public abstract class _DynAnyFactoryLocalBase
	extends org.omg.CORBA.LocalObject
	implements DynAnyFactory
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/DynamicAny/DynAnyFactory:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
