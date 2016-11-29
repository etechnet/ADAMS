package org.omg.RTCORBA;


/**
 * Abstract base class for implementations of local interface RTORB
 * @author JacORB IDL compiler.
 */

public abstract class _RTORBLocalBase
	extends org.omg.CORBA.LocalObject
	implements RTORB
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/RTCORBA/RTORB:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
