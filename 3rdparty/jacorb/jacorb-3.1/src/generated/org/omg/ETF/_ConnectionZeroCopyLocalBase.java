package org.omg.ETF;


/**
 * Abstract base class for implementations of local interface ConnectionZeroCopy
 * @author JacORB IDL compiler.
 */

public abstract class _ConnectionZeroCopyLocalBase
	extends org.omg.CORBA.LocalObject
	implements ConnectionZeroCopy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/ETF/ConnectionZeroCopy:1.0","IDL:omg.org/ETF/Connection:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
