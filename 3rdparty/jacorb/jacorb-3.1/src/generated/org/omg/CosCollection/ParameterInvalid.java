package org.omg.CosCollection;

/**
 * Generated from IDL exception "ParameterInvalid".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ParameterInvalid
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ParameterInvalid()
	{
		super(org.omg.CosCollection.ParameterInvalidHelper.id());
	}

	public int which;
	public java.lang.String why;
	public ParameterInvalid(java.lang.String _reason,int which, java.lang.String why)
	{
		super(_reason);
		this.which = which;
		this.why = why;
	}
	public ParameterInvalid(int which, java.lang.String why)
	{
		super(org.omg.CosCollection.ParameterInvalidHelper.id());
		this.which = which;
		this.why = why;
	}
}
