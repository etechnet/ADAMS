package org.omg.CORBA;


/**
 * Generated from IDL interface "Contained".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface ContainedOperations
	extends org.omg.CORBA.IRObjectOperations
{
	/* constants */
	/* operations  */
	java.lang.String id();
	void id(java.lang.String arg);
	java.lang.String name();
	void name(java.lang.String arg);
	java.lang.String version();
	void version(java.lang.String arg);
	org.omg.CORBA.Container defined_in();
	java.lang.String absolute_name();
	org.omg.CORBA.Repository containing_repository();
	org.omg.CORBA.ContainedPackage.Description describe();
	void move(org.omg.CORBA.Container new_container, java.lang.String new_name, java.lang.String new_version);
}
