package org.omg.SecurityLevel2;

/**
 * Generated from IDL alias "CredentialsList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class CredentialsListHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.SecurityLevel2.Credentials[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.SecurityLevel2.Credentials[] extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(CredentialsListHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.SecurityLevel2.CredentialsListHelper.id(), "CredentialsList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/SecurityLevel2/Credentials:1.0", "Credentials")));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/SecurityLevel2/CredentialsList:1.0";
	}
	public static org.omg.SecurityLevel2.Credentials[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.SecurityLevel2.Credentials[] _result;
		int _l_result164 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result164 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result164);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.SecurityLevel2.Credentials[_l_result164];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.SecurityLevel2.CredentialsHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.SecurityLevel2.Credentials[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.SecurityLevel2.CredentialsHelper.write(_out,_s[i]);
		}

	}
}
