package org.omg.CORBA;


/**
 * Abstract base class for implementations of local interface PollableSet
 * @author JacORB IDL compiler.
 */

public abstract class _PollableSetLocalBase
	extends org.omg.CORBA.LocalObject
	implements PollableSet
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/CORBA/PollableSet:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
