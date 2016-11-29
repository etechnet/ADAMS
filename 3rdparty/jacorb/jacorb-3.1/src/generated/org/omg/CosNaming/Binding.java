package org.omg.CosNaming;

/**
 * Generated from IDL struct "Binding".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class Binding
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Binding(){}
	public org.omg.CosNaming.NameComponent[] binding_name;
	public org.omg.CosNaming.BindingType binding_type;
	public Binding(org.omg.CosNaming.NameComponent[] binding_name, org.omg.CosNaming.BindingType binding_type)
	{
		this.binding_name = binding_name;
		this.binding_type = binding_type;
	}
}
