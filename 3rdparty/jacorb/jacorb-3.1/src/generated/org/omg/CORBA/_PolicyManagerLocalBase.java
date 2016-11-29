package org.omg.CORBA;


/**
 * Abstract base class for implementations of local interface PolicyManager
 * @author JacORB IDL compiler.
 */

public abstract class _PolicyManagerLocalBase
	extends org.omg.CORBA.LocalObject
	implements PolicyManager
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/CORBA/PolicyManager:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
