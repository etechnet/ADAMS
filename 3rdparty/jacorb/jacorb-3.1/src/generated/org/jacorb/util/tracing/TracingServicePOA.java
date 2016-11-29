package org.jacorb.util.tracing;


/**
 * Generated from IDL interface "TracingService".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public abstract class TracingServicePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.jacorb.util.tracing.TracingServiceOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_id", Integer.valueOf(0));
		m_opsHash.put ( "logTraceAtPoint", Integer.valueOf(1));
		m_opsHash.put ( "getTrace", Integer.valueOf(2));
		m_opsHash.put ( "registerSubTrace", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:org/jacorb/util/tracing/TracingService:1.0"};
	public org.jacorb.util.tracing.TracingService _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.util.tracing.TracingService __r = org.jacorb.util.tracing.TracingServiceHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.util.tracing.TracingService _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.util.tracing.TracingService __r = org.jacorb.util.tracing.TracingServiceHelper.narrow(__o);
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
			case 0: // get_id
			{
				_out = handler.createReply();
				_out.write_long(get_id());
				break;
			}
			case 1: // logTraceAtPoint
			{
			try
			{
				org.jacorb.util.tracing.Request _arg0=org.jacorb.util.tracing.RequestHelper.read(_input);
				java.lang.String _arg1=_input.read_string();
				long _arg2=_input.read_longlong();
				long _arg3=_input.read_longlong();
				_out = handler.createReply();
				logTraceAtPoint(_arg0,_arg1,_arg2,_arg3);
			}
			catch(org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestId _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestIdHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // getTrace
			{
			try
			{
				org.jacorb.util.tracing.Request _arg0=org.jacorb.util.tracing.RequestHelper.read(_input);
				_out = handler.createReply();
				org.jacorb.util.tracing.TraceDataHelper.write(_out,getTrace(_arg0));
			}
			catch(org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestId _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestIdHelper.write(_out, _ex0);
			}
				break;
			}
			case 3: // registerSubTrace
			{
			try
			{
				org.jacorb.util.tracing.Request _arg0=org.jacorb.util.tracing.RequestHelper.read(_input);
				org.jacorb.util.tracing.Request _arg1=org.jacorb.util.tracing.RequestHelper.read(_input);
				_out = handler.createReply();
				registerSubTrace(_arg0,_arg1);
			}
			catch(org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestId _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestIdHelper.write(_out, _ex0);
			}
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
