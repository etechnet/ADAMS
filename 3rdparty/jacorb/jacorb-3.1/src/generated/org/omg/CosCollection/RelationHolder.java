package org.omg.CosCollection;

/**
 * Generated from IDL interface "Relation".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class RelationHolder	implements org.omg.CORBA.portable.Streamable{
	 public Relation value;
	public RelationHolder()
	{
	}
	public RelationHolder (final Relation initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RelationHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RelationHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RelationHelper.write (_out,value);
	}
}
