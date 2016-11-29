package org.omg.CORBA;
/**
 * Generated from IDL enum "DefinitionKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class DefinitionKindHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DefinitionKindHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CORBA.DefinitionKindHelper.id(),"DefinitionKind",new String[]{"dk_none","dk_all","dk_Attribute","dk_Constant","dk_Exception","dk_Interface","dk_Module","dk_Operation","dk_Typedef","dk_Alias","dk_Struct","dk_Union","dk_Enum","dk_Primitive","dk_String","dk_Sequence","dk_Array","dk_Repository","dk_Wstring","dk_Fixed","dk_Value","dk_ValueBox","dk_ValueMember","dk_Native","dk_AbstractInterface","dk_LocalInterface","dk_Component","dk_Home","dk_Factory","dk_Finder","dk_Emits","dk_Publishes","dk_Consumes","dk_Provides","dk_Uses","dk_Event"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.DefinitionKind s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CORBA.DefinitionKind extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/CORBA/DefinitionKind:1.0";
	}
	public static DefinitionKind read (final org.omg.CORBA.portable.InputStream in)
	{
		return DefinitionKind.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final DefinitionKind s)
	{
		out.write_long(s.value());
	}
}
