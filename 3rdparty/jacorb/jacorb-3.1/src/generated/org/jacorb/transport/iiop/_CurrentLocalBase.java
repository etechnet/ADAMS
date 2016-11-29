package org.jacorb.transport.iiop;


/**
 * Abstract base class for implementations of local interface Current
 * @author JacORB IDL compiler.
 */

public abstract class _CurrentLocalBase
	extends org.omg.CORBA.LocalObject
	implements Current
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:jacorb.org/org/jacorb/transport/iiop/Current:1.0","IDL:jacorb.org/org/jacorb/transport/Current:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
