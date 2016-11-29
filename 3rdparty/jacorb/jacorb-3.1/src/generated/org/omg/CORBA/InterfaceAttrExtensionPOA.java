package org.omg.CORBA;


/**
 * Generated from IDL interface "InterfaceAttrExtension".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public abstract class InterfaceAttrExtensionPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CORBA.InterfaceAttrExtensionOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "describe_ext_interface", Integer.valueOf(0));
		m_opsHash.put ( "create_ext_attribute", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:omg.org/CORBA/InterfaceAttrExtension:1.0"};
	public org.omg.CORBA.InterfaceAttrExtension _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.InterfaceAttrExtension __r = org.omg.CORBA.InterfaceAttrExtensionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.InterfaceAttrExtension _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.InterfaceAttrExtension __r = org.omg.CORBA.InterfaceAttrExtensionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // describe_ext_interface
			{
				_out = handler.createReply();
				org.omg.CORBA.InterfaceAttrExtensionPackage.ExtFullInterfaceDescriptionHelper.write(_out,describe_ext_interface());
				break;
			}
			case 1: // create_ext_attribute
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				org.omg.CORBA.AttributeMode _arg4=org.omg.CORBA.AttributeModeHelper.read(_input);
				org.omg.CORBA.ExceptionDef[] _arg5=org.omg.CORBA.ExceptionDefSeqHelper.read(_input);
				org.omg.CORBA.ExceptionDef[] _arg6=org.omg.CORBA.ExceptionDefSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.ExtAttributeDefHelper.write(_out,create_ext_attribute(_arg0,_arg1,_arg2,_arg3,_arg4,_arg5,_arg6));
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
