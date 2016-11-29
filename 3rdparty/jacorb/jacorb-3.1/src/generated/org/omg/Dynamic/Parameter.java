package org.omg.Dynamic;

/**
 * Generated from IDL struct "Parameter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class Parameter
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Parameter(){}
	public org.omg.CORBA.Any argument;
	public org.omg.CORBA.ParameterMode mode;
	public Parameter(org.omg.CORBA.Any argument, org.omg.CORBA.ParameterMode mode)
	{
		this.argument = argument;
		this.mode = mode;
	}
}
