package org.omg.dds;

/**
 * Generated from IDL interface "Entity".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class EntityHolder	implements org.omg.CORBA.portable.Streamable{
	 public Entity value;
	public EntityHolder()
	{
	}
	public EntityHolder (final Entity initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EntityHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EntityHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EntityHelper.write (_out,value);
	}
}
