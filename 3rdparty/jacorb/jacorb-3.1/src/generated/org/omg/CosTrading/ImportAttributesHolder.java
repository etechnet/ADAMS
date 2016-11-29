package org.omg.CosTrading;

/**
 * Generated from IDL interface "ImportAttributes".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ImportAttributesHolder	implements org.omg.CORBA.portable.Streamable{
	 public ImportAttributes value;
	public ImportAttributesHolder()
	{
	}
	public ImportAttributesHolder (final ImportAttributes initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ImportAttributesHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ImportAttributesHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ImportAttributesHelper.write (_out,value);
	}
}
