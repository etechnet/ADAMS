package org.omg.CORBA;

/**
 * Generated from IDL abstract value type "CustomMarshal".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */


public interface CustomMarshal
	extends org.omg.CORBA.portable.ValueBase 
{
	/* operations  */
	void marshal(org.omg.CORBA.DataOutputStream os);
	void unmarshal(org.omg.CORBA.DataInputStream is);
}
