package org.omg.CSIIOP;


/**
 * Generated from IDL struct "CompoundSecMech".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class CompoundSecMechHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(CompoundSecMechHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSIIOP.CompoundSecMechHelper.id(),"CompoundSecMech",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("target_requires", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null),new org.omg.CORBA.StructMember("transport_mech", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.TaggedComponentHelper.id(),"TaggedComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("tag", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ComponentIdHelper.id(), "ComponentId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("component_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null),new org.omg.CORBA.StructMember("as_context_mech", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSIIOP.AS_ContextSecHelper.id(),"AS_ContextSec",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("target_supports", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null),new org.omg.CORBA.StructMember("target_requires", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null),new org.omg.CORBA.StructMember("client_authentication_mech", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.OIDHelper.id(), "OID",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null),new org.omg.CORBA.StructMember("target_name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.GSS_NT_ExportedNameHelper.id(), "GSS_NT_ExportedName",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)}), null),new org.omg.CORBA.StructMember("sas_context_mech", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSIIOP.SAS_ContextSecHelper.id(),"SAS_ContextSec",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("target_supports", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null),new org.omg.CORBA.StructMember("target_requires", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null),new org.omg.CORBA.StructMember("privilege_authorities", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.ServiceConfigurationListHelper.id(), "ServiceConfigurationList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSIIOP.ServiceConfigurationHelper.id(),"ServiceConfiguration",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("syntax", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.ServiceConfigurationSyntaxHelper.id(), "ServiceConfigurationSyntax",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.ServiceSpecificNameHelper.id(), "ServiceSpecificName",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)}))), null),new org.omg.CORBA.StructMember("supported_naming_mechanisms", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.OIDListHelper.id(), "OIDList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CSI.OIDHelper.type())), null),new org.omg.CORBA.StructMember("supported_identity_types", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.IdentityTokenTypeHelper.id(), "IdentityTokenType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CSIIOP.CompoundSecMech s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CSIIOP.CompoundSecMech extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/CSIIOP/CompoundSecMech:1.0";
	}
	public static org.omg.CSIIOP.CompoundSecMech read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CSIIOP.CompoundSecMech result = new org.omg.CSIIOP.CompoundSecMech();
		result.target_requires=in.read_ushort();
		result.transport_mech=org.omg.IOP.TaggedComponentHelper.read(in);
		result.as_context_mech=org.omg.CSIIOP.AS_ContextSecHelper.read(in);
		result.sas_context_mech=org.omg.CSIIOP.SAS_ContextSecHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CSIIOP.CompoundSecMech s)
	{
		out.write_ushort(s.target_requires);
		org.omg.IOP.TaggedComponentHelper.write(out,s.transport_mech);
		org.omg.CSIIOP.AS_ContextSecHelper.write(out,s.as_context_mech);
		org.omg.CSIIOP.SAS_ContextSecHelper.write(out,s.sas_context_mech);
	}
}
