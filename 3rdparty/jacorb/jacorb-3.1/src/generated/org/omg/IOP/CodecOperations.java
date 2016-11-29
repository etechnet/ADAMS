package org.omg.IOP;


/**
 * Generated from IDL interface "Codec".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public interface CodecOperations
{
	/* constants */
	/* operations  */
	byte[] encode(org.omg.CORBA.Any data) throws org.omg.IOP.CodecPackage.InvalidTypeForEncoding;
	org.omg.CORBA.Any decode(byte[] data) throws org.omg.IOP.CodecPackage.FormatMismatch;
	byte[] encode_value(org.omg.CORBA.Any data) throws org.omg.IOP.CodecPackage.InvalidTypeForEncoding;
	org.omg.CORBA.Any decode_value(byte[] data, org.omg.CORBA.TypeCode tc) throws org.omg.IOP.CodecPackage.TypeMismatch,org.omg.IOP.CodecPackage.FormatMismatch;
}
