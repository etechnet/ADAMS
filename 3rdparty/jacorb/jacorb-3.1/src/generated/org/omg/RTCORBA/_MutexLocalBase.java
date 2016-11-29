package org.omg.RTCORBA;


/**
 * Abstract base class for implementations of local interface Mutex
 * @author JacORB IDL compiler.
 */

public abstract class _MutexLocalBase
	extends org.omg.CORBA.LocalObject
	implements Mutex
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/RTCORBA/Mutex:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
