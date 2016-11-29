package org.omg.CosNaming;


/**
 * Generated from IDL interface "BindingIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class BindingIteratorPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosNaming.BindingIteratorOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "next_one", Integer.valueOf(0));
		m_opsHash.put ( "next_n", Integer.valueOf(1));
		m_opsHash.put ( "destroy", Integer.valueOf(2));
	}
	private String[] ids = {"IDL:omg.org/CosNaming/BindingIterator:1.0"};
	public org.omg.CosNaming.BindingIterator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNaming.BindingIterator __r = org.omg.CosNaming.BindingIteratorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNaming.BindingIterator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNaming.BindingIterator __r = org.omg.CosNaming.BindingIteratorHelper.narrow(__o);
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
			case 0: // next_one
			{
				org.omg.CosNaming.BindingHolder _arg0= new org.omg.CosNaming.BindingHolder();
				_out = handler.createReply();
				_out.write_boolean(next_one(_arg0));
				org.omg.CosNaming.BindingHelper.write(_out,_arg0.value);
				break;
			}
			case 1: // next_n
			{
				int _arg0=_input.read_ulong();
				org.omg.CosNaming.BindingListHolder _arg1= new org.omg.CosNaming.BindingListHolder();
				_out = handler.createReply();
				_out.write_boolean(next_n(_arg0,_arg1));
				org.omg.CosNaming.BindingListHelper.write(_out,_arg1.value);
				break;
			}
			case 2: // destroy
			{
				_out = handler.createReply();
				destroy();
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
