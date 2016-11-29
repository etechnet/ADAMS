package org.omg.CosTime;


/**
 * Generated from IDL interface "TIO".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class TIOPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTime.TIOOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "overlaps", Integer.valueOf(0));
		m_opsHash.put ( "spans", Integer.valueOf(1));
		m_opsHash.put ( "_get_time_interval", Integer.valueOf(2));
		m_opsHash.put ( "time", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:omg.org/CosTime/TIO:1.0"};
	public org.omg.CosTime.TIO _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTime.TIO __r = org.omg.CosTime.TIOHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTime.TIO _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTime.TIO __r = org.omg.CosTime.TIOHelper.narrow(__o);
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
			case 0: // overlaps
			{
				org.omg.CosTime.TIO _arg0=org.omg.CosTime.TIOHelper.read(_input);
				org.omg.CosTime.TIOHolder _arg1= new org.omg.CosTime.TIOHolder();
				_out = handler.createReply();
				org.omg.CosTime.OverlapTypeHelper.write(_out,overlaps(_arg0,_arg1));
				org.omg.CosTime.TIOHelper.write(_out,_arg1.value);
				break;
			}
			case 1: // spans
			{
				org.omg.CosTime.UTO _arg0=org.omg.CosTime.UTOHelper.read(_input);
				org.omg.CosTime.TIOHolder _arg1= new org.omg.CosTime.TIOHolder();
				_out = handler.createReply();
				org.omg.CosTime.OverlapTypeHelper.write(_out,spans(_arg0,_arg1));
				org.omg.CosTime.TIOHelper.write(_out,_arg1.value);
				break;
			}
			case 2: // _get_time_interval
			{
			_out = handler.createReply();
			org.omg.TimeBase.IntervalTHelper.write(_out,time_interval());
				break;
			}
			case 3: // time
			{
				_out = handler.createReply();
				org.omg.CosTime.UTOHelper.write(_out,time());
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
