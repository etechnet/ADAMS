package org.omg.dds;

/**
 * Generated from IDL interface "DomainParticipant".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DomainParticipantHolder	implements org.omg.CORBA.portable.Streamable{
	 public DomainParticipant value;
	public DomainParticipantHolder()
	{
	}
	public DomainParticipantHolder (final DomainParticipant initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DomainParticipantHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DomainParticipantHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DomainParticipantHelper.write (_out,value);
	}
}
