package org.omg.CSI;

/**
 * Generated from IDL struct "EstablishContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class EstablishContextHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSI.EstablishContext value;

	public EstablishContextHolder ()
	{
	}
	public EstablishContextHolder(final org.omg.CSI.EstablishContext initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CSI.EstablishContextHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CSI.EstablishContextHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CSI.EstablishContextHelper.write(_out, value);
	}
}
