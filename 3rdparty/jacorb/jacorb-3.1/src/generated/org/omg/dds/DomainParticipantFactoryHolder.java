package org.omg.dds;

/**
 * Generated from IDL interface "DomainParticipantFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DomainParticipantFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public DomainParticipantFactory value;
	public DomainParticipantFactoryHolder()
	{
	}
	public DomainParticipantFactoryHolder (final DomainParticipantFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DomainParticipantFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DomainParticipantFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DomainParticipantFactoryHelper.write (_out,value);
	}
}
