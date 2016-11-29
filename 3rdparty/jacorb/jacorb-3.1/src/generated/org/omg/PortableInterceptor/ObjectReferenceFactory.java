package org.omg.PortableInterceptor;

/**
 * Generated from IDL abstract value type "ObjectReferenceFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */


public interface ObjectReferenceFactory
	extends org.omg.CORBA.portable.ValueBase 
{
	/* operations  */
	org.omg.CORBA.Object make_object(java.lang.String repository_id, byte[] id);
}
