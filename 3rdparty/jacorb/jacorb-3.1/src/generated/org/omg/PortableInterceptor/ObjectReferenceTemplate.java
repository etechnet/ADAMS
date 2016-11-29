package org.omg.PortableInterceptor;

/**
 * Generated from IDL abstract value type "ObjectReferenceTemplate".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */


public interface ObjectReferenceTemplate
	extends org.omg.CORBA.portable.ValueBase , org.omg.PortableInterceptor.ObjectReferenceFactory
{
	/* operations  */
	java.lang.String server_id();
	java.lang.String orb_id();
	java.lang.String[] adapter_name();
}
