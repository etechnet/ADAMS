package org.omg.CosTime;


/**
 * Generated from IDL interface "TimeService".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class TimeServicePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTime.TimeServiceOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "uto_from_utc", Integer.valueOf(0));
		m_opsHash.put ( "universal_time", Integer.valueOf(1));
		m_opsHash.put ( "secure_universal_time", Integer.valueOf(2));
		m_opsHash.put ( "new_universal_time", Integer.valueOf(3));
		m_opsHash.put ( "new_interval", Integer.valueOf(4));
	}
	private String[] ids = {"IDL:omg.org/CosTime/TimeService:1.0"};
	public org.omg.CosTime.TimeService _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTime.TimeService __r = org.omg.CosTime.TimeServiceHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTime.TimeService _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTime.TimeService __r = org.omg.CosTime.TimeServiceHelper.narrow(__o);
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
			case 0: // uto_from_utc
			{
				org.omg.TimeBase.UtcT _arg0=org.omg.TimeBase.UtcTHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosTime.UTOHelper.write(_out,uto_from_utc(_arg0));
				break;
			}
			case 1: // universal_time
			{
			try
			{
				_out = handler.createReply();
				org.omg.CosTime.UTOHelper.write(_out,universal_time());
			}
			catch(org.omg.CosTime.TimeUnavailable _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTime.TimeUnavailableHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // secure_universal_time
			{
			try
			{
				_out = handler.createReply();
				org.omg.CosTime.UTOHelper.write(_out,secure_universal_time());
			}
			catch(org.omg.CosTime.TimeUnavailable _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTime.TimeUnavailableHelper.write(_out, _ex0);
			}
				break;
			}
			case 3: // new_universal_time
			{
				long _arg0=_input.read_ulonglong();
				long _arg1=_input.read_ulonglong();
				short _arg2=_input.read_short();
				_out = handler.createReply();
				org.omg.CosTime.UTOHelper.write(_out,new_universal_time(_arg0,_arg1,_arg2));
				break;
			}
			case 4: // new_interval
			{
				long _arg0=_input.read_ulonglong();
				long _arg1=_input.read_ulonglong();
				_out = handler.createReply();
				org.omg.CosTime.TIOHelper.write(_out,new_interval(_arg0,_arg1));
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
