package org.omg.CSI;

/**
 * Generated from IDL alias "X509CertificateChain".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class X509CertificateChainHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public X509CertificateChainHolder ()
	{
	}
	public X509CertificateChainHolder (final byte[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return X509CertificateChainHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = X509CertificateChainHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		X509CertificateChainHelper.write (out,value);
	}
}
