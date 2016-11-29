package org.omg.CORBA;
public final class WStringValueHelper
	implements org.omg.CORBA.portable.BoxedValueHelper
{
	private static org.omg.CORBA.TypeCode _type = org.omg.CORBA.ORB.init().create_value_box_tc(org.omg.CORBA.WStringValueHelper.id(),"WStringValue",org.omg.CORBA.ORB.init().create_wstring_tc(0));
	public static org.omg.CORBA.TypeCode type()
	{
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final java.lang.String s)
	{
		any.insert_Value(s, type());
	}

	public static java.lang.String extract (final org.omg.CORBA.Any any)
	{
		return (java.lang.String) any.extract_Value();
	}

	public static String id()
	{
		return "IDL:omg.org/CORBA/WStringValue:1.0";
	}
	public static java.lang.String read (final org.omg.CORBA.portable.InputStream in)
	{
		java.lang.String result;
		result=in.read_wstring();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final java.lang.String s)
	{
		java.lang.String tmpResult979 = s;
out.write_wstring( tmpResult979 );
	}
	public java.io.Serializable read_value (final org.omg.CORBA.portable.InputStream is)
	{
		return org.omg.CORBA.WStringValueHelper.read (is);
	}
	public void write_value (final org.omg.CORBA.portable.OutputStream os, final java.io.Serializable value)
	{
		org.omg.CORBA.WStringValueHelper.write (os, (java.lang.String)value);
	}
	public java.lang.String get_id()
	{
		return org.omg.CORBA.WStringValueHelper.id();
	}
}
