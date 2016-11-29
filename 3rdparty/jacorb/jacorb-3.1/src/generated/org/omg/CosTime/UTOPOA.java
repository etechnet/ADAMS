package org.omg.CosTime;


/**
 * Generated from IDL interface "UTO".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class UTOPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTime.UTOOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "absolute_time", Integer.valueOf(0));
		m_opsHash.put ( "_get_utc_time", Integer.valueOf(1));
		m_opsHash.put ( "_get_tdf", Integer.valueOf(2));
		m_opsHash.put ( "_get_inaccuracy", Integer.valueOf(3));
		m_opsHash.put ( "time_to_interval", Integer.valueOf(4));
		m_opsHash.put ( "compare_time", Integer.valueOf(5));
		m_opsHash.put ( "interval", Integer.valueOf(6));
		m_opsHash.put ( "_get_time", Integer.valueOf(7));
	}
	private String[] ids = {"IDL:omg.org/CosTime/UTO:1.0"};
	public org.omg.CosTime.UTO _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTime.UTO __r = org.omg.CosTime.UTOHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTime.UTO _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTime.UTO __r = org.omg.CosTime.UTOHelper.narrow(__o);
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
			case 0: // absolute_time
			{
				_out = handler.createReply();
				org.omg.CosTime.UTOHelper.write(_out,absolute_time());
				break;
			}
			case 1: // _get_utc_time
			{
			_out = handler.createReply();
			org.omg.TimeBase.UtcTHelper.write(_out,utc_time());
				break;
			}
			case 2: // _get_tdf
			{
			_out = handler.createReply();
			_out.write_short(tdf());
				break;
			}
			case 3: // _get_inaccuracy
			{
			_out = handler.createReply();
			_out.write_ulonglong(inaccuracy());
				break;
			}
			case 4: // time_to_interval
			{
				org.omg.CosTime.UTO _arg0=org.omg.CosTime.UTOHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosTime.TIOHelper.write(_out,time_to_interval(_arg0));
				break;
			}
			case 5: // compare_time
			{
				org.omg.CosTime.ComparisonType _arg0=org.omg.CosTime.ComparisonTypeHelper.read(_input);
				org.omg.CosTime.UTO _arg1=org.omg.CosTime.UTOHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosTime.TimeComparisonHelper.write(_out,compare_time(_arg0,_arg1));
				break;
			}
			case 6: // interval
			{
				_out = handler.createReply();
				org.omg.CosTime.TIOHelper.write(_out,interval());
				break;
			}
			case 7: // _get_time
			{
			_out = handler.createReply();
			_out.write_ulonglong(time());
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
