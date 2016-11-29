package org.omg.Security;

/**
 * Generated from IDL struct "OptionsDirectionPair".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class OptionsDirectionPair
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public OptionsDirectionPair(){}
	public short options;
	public org.omg.Security.CommunicationDirection direction;
	public OptionsDirectionPair(short options, org.omg.Security.CommunicationDirection direction)
	{
		this.options = options;
		this.direction = direction;
	}
}
