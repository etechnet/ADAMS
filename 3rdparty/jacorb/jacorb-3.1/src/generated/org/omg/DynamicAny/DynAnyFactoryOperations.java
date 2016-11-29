package org.omg.DynamicAny;


/**
 * Generated from IDL interface "DynAnyFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface DynAnyFactoryOperations
{
	/* constants */
	/* operations  */
	org.omg.DynamicAny.DynAny create_dyn_any(org.omg.CORBA.Any value) throws org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode;
	org.omg.DynamicAny.DynAny create_dyn_any_from_type_code(org.omg.CORBA.TypeCode type) throws org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode;
	org.omg.DynamicAny.DynAny create_dyn_any_without_truncation(org.omg.CORBA.Any value) throws org.omg.DynamicAny.MustTruncate,org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode;
	org.omg.DynamicAny.DynAny[] create_multiple_dyn_anys(org.omg.CORBA.Any[] values, boolean allow_truncate) throws org.omg.DynamicAny.MustTruncate,org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode;
	org.omg.CORBA.Any[] create_multiple_anys(org.omg.DynamicAny.DynAny[] values);
}
