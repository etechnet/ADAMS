package org.omg.SecurityLevel2;


/**
 * Generated from IDL interface "InvocationCredentialsPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class InvocationCredentialsPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvocationCredentialsPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/SecurityLevel2/InvocationCredentialsPolicy:1.0", "InvocationCredentialsPolicy");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.SecurityLevel2.InvocationCredentialsPolicy s)
	{
			any.insert_Object(s);
	}
	public static org.omg.SecurityLevel2.InvocationCredentialsPolicy extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/SecurityLevel2/InvocationCredentialsPolicy:1.0";
	}
	public static InvocationCredentialsPolicy read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.SecurityLevel2.InvocationCredentialsPolicy s)
	{
		_out.write_Object(s);
	}
	public static org.omg.SecurityLevel2.InvocationCredentialsPolicy narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.SecurityLevel2.InvocationCredentialsPolicy)
		{
			return (org.omg.SecurityLevel2.InvocationCredentialsPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.SecurityLevel2.InvocationCredentialsPolicy unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.SecurityLevel2.InvocationCredentialsPolicy)
		{
			return (org.omg.SecurityLevel2.InvocationCredentialsPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
