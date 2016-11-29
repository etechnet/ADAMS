package org.omg.CORBA;


/**
 * Generated from IDL interface "InterfaceDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface InterfaceDefOperations
	extends org.omg.CORBA.ContainerOperations , org.omg.CORBA.ContainedOperations , org.omg.CORBA.IDLTypeOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.InterfaceDef[] base_interfaces();
	void base_interfaces(org.omg.CORBA.InterfaceDef[] arg);
	boolean is_a(java.lang.String interface_id);
	org.omg.CORBA.InterfaceDefPackage.FullInterfaceDescription describe_interface();
	org.omg.CORBA.AttributeDef create_attribute(java.lang.String id, java.lang.String name, java.lang.String version, org.omg.CORBA.IDLType type, org.omg.CORBA.AttributeMode mode);
	org.omg.CORBA.OperationDef create_operation(java.lang.String id, java.lang.String name, java.lang.String version, org.omg.CORBA.IDLType result, org.omg.CORBA.OperationMode mode, org.omg.CORBA.ParameterDescription[] params, org.omg.CORBA.ExceptionDef[] exceptions, java.lang.String[] contexts);
}
