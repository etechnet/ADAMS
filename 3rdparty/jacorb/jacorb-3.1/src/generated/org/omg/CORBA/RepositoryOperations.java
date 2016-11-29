package org.omg.CORBA;


/**
 * Generated from IDL interface "Repository".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface RepositoryOperations
	extends org.omg.CORBA.ContainerOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.Contained lookup_id(java.lang.String search_id);
	org.omg.CORBA.TypeCode get_canonical_typecode(org.omg.CORBA.TypeCode tc);
	org.omg.CORBA.PrimitiveDef get_primitive(org.omg.CORBA.PrimitiveKind kind);
	org.omg.CORBA.StringDef create_string(int bound);
	org.omg.CORBA.WstringDef create_wstring(int bound);
	org.omg.CORBA.SequenceDef create_sequence(int bound, org.omg.CORBA.IDLType element_type);
	org.omg.CORBA.ArrayDef create_array(int length, org.omg.CORBA.IDLType element_type);
	org.omg.CORBA.FixedDef create_fixed(short digits, short scale);
}
