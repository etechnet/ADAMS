package org.jacorb.sasPolicy;


/**
 * Generated from IDL interface "ATLASPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ATLASPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ATLASPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_local_interface_tc("IDL:org/jacorb/sasPolicy/ATLASPolicy:1.0", "ATLASPolicy");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.sasPolicy.ATLASPolicy s)
	{
			any.insert_Object(s);
	}
	public static org.jacorb.sasPolicy.ATLASPolicy extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:org/jacorb/sasPolicy/ATLASPolicy:1.0";
	}
	public static ATLASPolicy read(final org.omg.CORBA.portable.InputStream in)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.jacorb.sasPolicy.ATLASPolicy s)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static org.jacorb.sasPolicy.ATLASPolicy narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.sasPolicy.ATLASPolicy)
		{
			return (org.jacorb.sasPolicy.ATLASPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.jacorb.sasPolicy.ATLASPolicy unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.sasPolicy.ATLASPolicy)
		{
			return (org.jacorb.sasPolicy.ATLASPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
