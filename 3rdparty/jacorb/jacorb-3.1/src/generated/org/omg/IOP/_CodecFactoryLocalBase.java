package org.omg.IOP;


/**
 * Abstract base class for implementations of local interface CodecFactory
 * @author JacORB IDL compiler.
 */

public abstract class _CodecFactoryLocalBase
	extends org.omg.CORBA.LocalObject
	implements CodecFactory
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/IOP/CodecFactory:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
