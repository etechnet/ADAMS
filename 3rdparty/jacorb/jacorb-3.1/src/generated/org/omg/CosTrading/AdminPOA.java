package org.omg.CosTrading;


/**
 * Generated from IDL interface "Admin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class AdminPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTrading.AdminOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "set_max_match_card", Integer.valueOf(0));
		m_opsHash.put ( "_get_supports_proxy_offers", Integer.valueOf(1));
		m_opsHash.put ( "_get_proxy_if", Integer.valueOf(2));
		m_opsHash.put ( "_get_register_if", Integer.valueOf(3));
		m_opsHash.put ( "_get_max_hop_count", Integer.valueOf(4));
		m_opsHash.put ( "set_max_link_follow_policy", Integer.valueOf(5));
		m_opsHash.put ( "_get_admin_if", Integer.valueOf(6));
		m_opsHash.put ( "_get_max_match_card", Integer.valueOf(7));
		m_opsHash.put ( "set_max_list", Integer.valueOf(8));
		m_opsHash.put ( "_get_request_id_stem", Integer.valueOf(9));
		m_opsHash.put ( "set_def_search_card", Integer.valueOf(10));
		m_opsHash.put ( "set_def_hop_count", Integer.valueOf(11));
		m_opsHash.put ( "_get_max_follow_policy", Integer.valueOf(12));
		m_opsHash.put ( "set_supports_dynamic_properties", Integer.valueOf(13));
		m_opsHash.put ( "set_max_hop_count", Integer.valueOf(14));
		m_opsHash.put ( "set_def_match_card", Integer.valueOf(15));
		m_opsHash.put ( "set_supports_modifiable_properties", Integer.valueOf(16));
		m_opsHash.put ( "_get_def_search_card", Integer.valueOf(17));
		m_opsHash.put ( "set_def_follow_policy", Integer.valueOf(18));
		m_opsHash.put ( "set_max_follow_policy", Integer.valueOf(19));
		m_opsHash.put ( "_get_def_return_card", Integer.valueOf(20));
		m_opsHash.put ( "set_max_return_card", Integer.valueOf(21));
		m_opsHash.put ( "_get_link_if", Integer.valueOf(22));
		m_opsHash.put ( "_get_max_link_follow_policy", Integer.valueOf(23));
		m_opsHash.put ( "set_type_repos", Integer.valueOf(24));
		m_opsHash.put ( "_get_type_repos", Integer.valueOf(25));
		m_opsHash.put ( "_get_max_search_card", Integer.valueOf(26));
		m_opsHash.put ( "set_request_id_stem", Integer.valueOf(27));
		m_opsHash.put ( "list_proxies", Integer.valueOf(28));
		m_opsHash.put ( "_get_def_follow_policy", Integer.valueOf(29));
		m_opsHash.put ( "_get_lookup_if", Integer.valueOf(30));
		m_opsHash.put ( "list_offers", Integer.valueOf(31));
		m_opsHash.put ( "_get_supports_dynamic_properties", Integer.valueOf(32));
		m_opsHash.put ( "_get_max_return_card", Integer.valueOf(33));
		m_opsHash.put ( "set_max_search_card", Integer.valueOf(34));
		m_opsHash.put ( "set_supports_proxy_offers", Integer.valueOf(35));
		m_opsHash.put ( "_get_supports_modifiable_properties", Integer.valueOf(36));
		m_opsHash.put ( "_get_def_match_card", Integer.valueOf(37));
		m_opsHash.put ( "_get_max_list", Integer.valueOf(38));
		m_opsHash.put ( "_get_def_hop_count", Integer.valueOf(39));
		m_opsHash.put ( "set_def_return_card", Integer.valueOf(40));
	}
	private String[] ids = {"IDL:omg.org/CosTrading/Admin:1.0","IDL:omg.org/CosTrading/SupportAttributes:1.0","IDL:omg.org/CosTrading/LinkAttributes:1.0","IDL:omg.org/CosTrading/ImportAttributes:1.0","IDL:omg.org/CosTrading/TraderComponents:1.0"};
	public org.omg.CosTrading.Admin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTrading.Admin __r = org.omg.CosTrading.AdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTrading.Admin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTrading.Admin __r = org.omg.CosTrading.AdminHelper.narrow(__o);
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
			case 0: // set_max_match_card
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_max_match_card(_arg0));
				break;
			}
			case 1: // _get_supports_proxy_offers
			{
			_out = handler.createReply();
			_out.write_boolean(supports_proxy_offers());
				break;
			}
			case 2: // _get_proxy_if
			{
			_out = handler.createReply();
			org.omg.CosTrading.ProxyHelper.write(_out,proxy_if());
				break;
			}
			case 3: // _get_register_if
			{
			_out = handler.createReply();
			org.omg.CosTrading.RegisterHelper.write(_out,register_if());
				break;
			}
			case 4: // _get_max_hop_count
			{
			_out = handler.createReply();
			_out.write_ulong(max_hop_count());
				break;
			}
			case 5: // set_max_link_follow_policy
			{
				org.omg.CosTrading.FollowOption _arg0=org.omg.CosTrading.FollowOptionHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosTrading.FollowOptionHelper.write(_out,set_max_link_follow_policy(_arg0));
				break;
			}
			case 6: // _get_admin_if
			{
			_out = handler.createReply();
			org.omg.CosTrading.AdminHelper.write(_out,admin_if());
				break;
			}
			case 7: // _get_max_match_card
			{
			_out = handler.createReply();
			_out.write_ulong(max_match_card());
				break;
			}
			case 8: // set_max_list
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_max_list(_arg0));
				break;
			}
			case 9: // _get_request_id_stem
			{
			_out = handler.createReply();
			org.omg.CosTrading.AdminPackage.OctetSeqHelper.write(_out,request_id_stem());
				break;
			}
			case 10: // set_def_search_card
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_def_search_card(_arg0));
				break;
			}
			case 11: // set_def_hop_count
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_def_hop_count(_arg0));
				break;
			}
			case 12: // _get_max_follow_policy
			{
			_out = handler.createReply();
			org.omg.CosTrading.FollowOptionHelper.write(_out,max_follow_policy());
				break;
			}
			case 13: // set_supports_dynamic_properties
			{
				boolean _arg0=_input.read_boolean();
				_out = handler.createReply();
				_out.write_boolean(set_supports_dynamic_properties(_arg0));
				break;
			}
			case 14: // set_max_hop_count
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_max_hop_count(_arg0));
				break;
			}
			case 15: // set_def_match_card
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_def_match_card(_arg0));
				break;
			}
			case 16: // set_supports_modifiable_properties
			{
				boolean _arg0=_input.read_boolean();
				_out = handler.createReply();
				_out.write_boolean(set_supports_modifiable_properties(_arg0));
				break;
			}
			case 17: // _get_def_search_card
			{
			_out = handler.createReply();
			_out.write_ulong(def_search_card());
				break;
			}
			case 18: // set_def_follow_policy
			{
				org.omg.CosTrading.FollowOption _arg0=org.omg.CosTrading.FollowOptionHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosTrading.FollowOptionHelper.write(_out,set_def_follow_policy(_arg0));
				break;
			}
			case 19: // set_max_follow_policy
			{
				org.omg.CosTrading.FollowOption _arg0=org.omg.CosTrading.FollowOptionHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosTrading.FollowOptionHelper.write(_out,set_max_follow_policy(_arg0));
				break;
			}
			case 20: // _get_def_return_card
			{
			_out = handler.createReply();
			_out.write_ulong(def_return_card());
				break;
			}
			case 21: // set_max_return_card
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_max_return_card(_arg0));
				break;
			}
			case 22: // _get_link_if
			{
			_out = handler.createReply();
			org.omg.CosTrading.LinkHelper.write(_out,link_if());
				break;
			}
			case 23: // _get_max_link_follow_policy
			{
			_out = handler.createReply();
			org.omg.CosTrading.FollowOptionHelper.write(_out,max_link_follow_policy());
				break;
			}
			case 24: // set_type_repos
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				_out = handler.createReply();
				_out.write_Object(set_type_repos(_arg0));
				break;
			}
			case 25: // _get_type_repos
			{
			_out = handler.createReply();
			_out.write_Object(type_repos());
				break;
			}
			case 26: // _get_max_search_card
			{
			_out = handler.createReply();
			_out.write_ulong(max_search_card());
				break;
			}
			case 27: // set_request_id_stem
			{
				byte[] _arg0=org.omg.CosTrading.AdminPackage.OctetSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosTrading.AdminPackage.OctetSeqHelper.write(_out,set_request_id_stem(_arg0));
				break;
			}
			case 28: // list_proxies
			{
			try
			{
				int _arg0=_input.read_ulong();
				org.omg.CosTrading.OfferIdSeqHolder _arg1= new org.omg.CosTrading.OfferIdSeqHolder();
				org.omg.CosTrading.OfferIdIteratorHolder _arg2= new org.omg.CosTrading.OfferIdIteratorHolder();
				_out = handler.createReply();
				list_proxies(_arg0,_arg1,_arg2);
				org.omg.CosTrading.OfferIdSeqHelper.write(_out,_arg1.value);
				org.omg.CosTrading.OfferIdIteratorHelper.write(_out,_arg2.value);
			}
			catch(org.omg.CosTrading.NotImplemented _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTrading.NotImplementedHelper.write(_out, _ex0);
			}
				break;
			}
			case 29: // _get_def_follow_policy
			{
			_out = handler.createReply();
			org.omg.CosTrading.FollowOptionHelper.write(_out,def_follow_policy());
				break;
			}
			case 30: // _get_lookup_if
			{
			_out = handler.createReply();
			org.omg.CosTrading.LookupHelper.write(_out,lookup_if());
				break;
			}
			case 31: // list_offers
			{
			try
			{
				int _arg0=_input.read_ulong();
				org.omg.CosTrading.OfferIdSeqHolder _arg1= new org.omg.CosTrading.OfferIdSeqHolder();
				org.omg.CosTrading.OfferIdIteratorHolder _arg2= new org.omg.CosTrading.OfferIdIteratorHolder();
				_out = handler.createReply();
				list_offers(_arg0,_arg1,_arg2);
				org.omg.CosTrading.OfferIdSeqHelper.write(_out,_arg1.value);
				org.omg.CosTrading.OfferIdIteratorHelper.write(_out,_arg2.value);
			}
			catch(org.omg.CosTrading.NotImplemented _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTrading.NotImplementedHelper.write(_out, _ex0);
			}
				break;
			}
			case 32: // _get_supports_dynamic_properties
			{
			_out = handler.createReply();
			_out.write_boolean(supports_dynamic_properties());
				break;
			}
			case 33: // _get_max_return_card
			{
			_out = handler.createReply();
			_out.write_ulong(max_return_card());
				break;
			}
			case 34: // set_max_search_card
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_max_search_card(_arg0));
				break;
			}
			case 35: // set_supports_proxy_offers
			{
				boolean _arg0=_input.read_boolean();
				_out = handler.createReply();
				_out.write_boolean(set_supports_proxy_offers(_arg0));
				break;
			}
			case 36: // _get_supports_modifiable_properties
			{
			_out = handler.createReply();
			_out.write_boolean(supports_modifiable_properties());
				break;
			}
			case 37: // _get_def_match_card
			{
			_out = handler.createReply();
			_out.write_ulong(def_match_card());
				break;
			}
			case 38: // _get_max_list
			{
			_out = handler.createReply();
			_out.write_ulong(max_list());
				break;
			}
			case 39: // _get_def_hop_count
			{
			_out = handler.createReply();
			_out.write_ulong(def_hop_count());
				break;
			}
			case 40: // set_def_return_card
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(set_def_return_card(_arg0));
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
