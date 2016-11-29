package org.omg.PortableInterceptor;

/**
 * Generated from IDL alias "ObjectReferenceTemplateSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ObjectReferenceTemplateSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.PortableInterceptor.ObjectReferenceTemplate[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.PortableInterceptor.ObjectReferenceTemplate[] extract (final org.omg.CORBA.Any any)
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
			synchronized(ObjectReferenceTemplateSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableInterceptor.ObjectReferenceTemplateSeqHelper.id(), "ObjectReferenceTemplateSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_value_tc("IDL:omg.org/PortableInterceptor/ObjectReferenceTemplate:1.0", "ObjectReferenceTemplate", org.omg.CORBA.VM_ABSTRACT.value , null, null)));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/PortableInterceptor/ObjectReferenceTemplateSeq:1.0";
	}
	public static org.omg.PortableInterceptor.ObjectReferenceTemplate[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.PortableInterceptor.ObjectReferenceTemplate[] _result;
		int _l_result65 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result65 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result65);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.PortableInterceptor.ObjectReferenceTemplate[_l_result65];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=(org.omg.PortableInterceptor.ObjectReferenceTemplate)((org.omg.CORBA_2_3.portable.InputStream)_in).read_value ("IDL:omg.org/PortableInterceptor/ObjectReferenceTemplate:1.0");
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.PortableInterceptor.ObjectReferenceTemplate[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			((org.omg.CORBA_2_3.portable.OutputStream)_out).write_value (_s[i]);
		}

	}
}
