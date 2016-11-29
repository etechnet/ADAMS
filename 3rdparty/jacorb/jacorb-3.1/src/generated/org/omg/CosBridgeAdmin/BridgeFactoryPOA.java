package org.omg.CosBridgeAdmin;


/**
 * Generated from IDL interface "BridgeFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public abstract class BridgeFactoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosBridgeAdmin.BridgeFactoryOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "create_bridge", Integer.valueOf(0));
		m_opsHash.put ( "get_bridge_with_id", Integer.valueOf(1));
		m_opsHash.put ( "get_all_bridges", Integer.valueOf(2));
	}
	private String[] ids = {"IDL:omg.org/CosBridgeAdmin/BridgeFactory:1.0"};
	public org.omg.CosBridgeAdmin.BridgeFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosBridgeAdmin.BridgeFactory __r = org.omg.CosBridgeAdmin.BridgeFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosBridgeAdmin.BridgeFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosBridgeAdmin.BridgeFactory __r = org.omg.CosBridgeAdmin.BridgeFactoryHelper.narrow(__o);
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
			case 0: // create_bridge
			{
			try
			{
				org.omg.CosBridgeAdmin.ExternalEndpoint _arg0=org.omg.CosBridgeAdmin.ExternalEndpointHelper.read(_input);
				org.omg.CosBridgeAdmin.ExternalEndpoint _arg1=org.omg.CosBridgeAdmin.ExternalEndpointHelper.read(_input);
				org.omg.CORBA.IntHolder _arg2= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				org.omg.CosBridgeAdmin.BridgeHelper.write(_out,create_bridge(_arg0,_arg1,_arg2));
				_out.write_long(_arg2.value);
			}
			catch(org.omg.CosBridgeAdmin.InvalidExternalEndPoints _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosBridgeAdmin.InvalidExternalEndPointsHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // get_bridge_with_id
			{
			try
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				org.omg.CosBridgeAdmin.BridgeHelper.write(_out,get_bridge_with_id(_arg0));
			}
			catch(org.omg.CosBridgeAdmin.BridgeNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosBridgeAdmin.BridgeNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // get_all_bridges
			{
				_out = handler.createReply();
				org.omg.CosBridgeAdmin.BridgeIDSeqHelper.write(_out,get_all_bridges());
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
