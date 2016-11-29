package org.omg.DynamicAny;


/**
 * Abstract base class for implementations of local interface DynSequence
 * @author JacORB IDL compiler.
 */

public abstract class _DynSequenceLocalBase
	extends org.omg.CORBA.LocalObject
	implements DynSequence
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/DynamicAny/DynSequence:1.0","IDL:omg.org/DynamicAny/DynAny:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
