package org.omg.CORBA;


/**
 * Abstract base class for implementations of local interface PolicyCurrent
 * @author JacORB IDL compiler.
 */

public abstract class _PolicyCurrentLocalBase
	extends org.omg.CORBA.LocalObject
	implements PolicyCurrent
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/CORBA/PolicyCurrent:1.0","IDL:omg.org/CORBA/PolicyManager:1.0","IDL:omg.org/CORBA/Current:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
