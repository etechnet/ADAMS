package org.omg.CosCollection;

/**
 * Generated from IDL interface "SortedRelationFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SortedRelationFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public SortedRelationFactory value;
	public SortedRelationFactoryHolder()
	{
	}
	public SortedRelationFactoryHolder (final SortedRelationFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SortedRelationFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SortedRelationFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SortedRelationFactoryHelper.write (_out,value);
	}
}
