package org.omg.RTCORBA;


/**
 * Abstract base class for implementations of local interface TCPProtocolProperties
 * @author JacORB IDL compiler.
 */

public abstract class _TCPProtocolPropertiesLocalBase
	extends org.omg.CORBA.LocalObject
	implements TCPProtocolProperties
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/RTCORBA/TCPProtocolProperties:1.0","IDL:omg.org/RTCORBA/ProtocolProperties:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
