package org.omg.RTCORBA;


/**
 * Abstract base class for implementations of local interface ProtocolProperties
 * @author JacORB IDL compiler.
 */

public abstract class _ProtocolPropertiesLocalBase
	extends org.omg.CORBA.LocalObject
	implements ProtocolProperties
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/RTCORBA/ProtocolProperties:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
