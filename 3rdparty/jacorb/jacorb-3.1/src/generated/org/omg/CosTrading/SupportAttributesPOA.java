package org.omg.CosTrading;


/**
 * Generated from IDL interface "SupportAttributes".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class SupportAttributesPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTrading.SupportAttributesOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "_get_supports_proxy_offers", Integer.valueOf(0));
		m_opsHash.put ( "_get_type_repos", Integer.valueOf(1));
		m_opsHash.put ( "_get_supports_dynamic_properties", Integer.valueOf(2));
		m_opsHash.put ( "_get_supports_modifiable_properties", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:omg.org/CosTrading/SupportAttributes:1.0"};
	public org.omg.CosTrading.SupportAttributes _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTrading.SupportAttributes __r = org.omg.CosTrading.SupportAttributesHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTrading.SupportAttributes _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTrading.SupportAttributes __r = org.omg.CosTrading.SupportAttributesHelper.narrow(__o);
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
			case 0: // _get_supports_proxy_offers
			{
			_out = handler.createReply();
			_out.write_boolean(supports_proxy_offers());
				break;
			}
			case 1: // _get_type_repos
			{
			_out = handler.createReply();
			_out.write_Object(type_repos());
				break;
			}
			case 2: // _get_supports_dynamic_properties
			{
			_out = handler.createReply();
			_out.write_boolean(supports_dynamic_properties());
				break;
			}
			case 3: // _get_supports_modifiable_properties
			{
			_out = handler.createReply();
			_out.write_boolean(supports_modifiable_properties());
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
