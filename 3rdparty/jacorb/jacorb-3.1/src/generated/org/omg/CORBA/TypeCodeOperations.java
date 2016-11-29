package org.omg.CORBA;


/**
 * Generated from IDL interface "TypeCode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface TypeCodeOperations
{
	/* constants */
	/* operations  */
	boolean equal(org.omg.CORBA.TypeCode tc);
	boolean equivalent(org.omg.CORBA.TypeCode tc);
	org.omg.CORBA.TypeCode get_compact_typecode();
	org.omg.CORBA.TCKind kind();
	java.lang.String id() throws org.omg.CORBA.TypeCodePackage.BadKind;
	java.lang.String name() throws org.omg.CORBA.TypeCodePackage.BadKind;
	int member_count() throws org.omg.CORBA.TypeCodePackage.BadKind;
	java.lang.String member_name(int index) throws org.omg.CORBA.TypeCodePackage.Bounds,org.omg.CORBA.TypeCodePackage.BadKind;
	org.omg.CORBA.TypeCode member_type(int index) throws org.omg.CORBA.TypeCodePackage.Bounds,org.omg.CORBA.TypeCodePackage.BadKind;
	org.omg.CORBA.Any member_label(int index) throws org.omg.CORBA.TypeCodePackage.Bounds,org.omg.CORBA.TypeCodePackage.BadKind;
	org.omg.CORBA.TypeCode discriminator_type() throws org.omg.CORBA.TypeCodePackage.BadKind;
	int default_index() throws org.omg.CORBA.TypeCodePackage.BadKind;
	int length() throws org.omg.CORBA.TypeCodePackage.BadKind;
	org.omg.CORBA.TypeCode content_type() throws org.omg.CORBA.TypeCodePackage.BadKind;
	short fixed_digits() throws org.omg.CORBA.TypeCodePackage.BadKind;
	short fixed_scale() throws org.omg.CORBA.TypeCodePackage.BadKind;
	short member_visibility(int index) throws org.omg.CORBA.TypeCodePackage.Bounds,org.omg.CORBA.TypeCodePackage.BadKind;
	short type_modifier() throws org.omg.CORBA.TypeCodePackage.BadKind;
	org.omg.CORBA.TypeCode concrete_base_type() throws org.omg.CORBA.TypeCodePackage.BadKind;
}
