package org.jacorb.util.tracing;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TracingService".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public class TracingServicePOATie
	extends TracingServicePOA
{
	private TracingServiceOperations _delegate;

	private POA _poa;
	public TracingServicePOATie(TracingServiceOperations delegate)
	{
		_delegate = delegate;
	}
	public TracingServicePOATie(TracingServiceOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public TracingServiceOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TracingServiceOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public int get_id()
	{
		return _delegate.get_id();
	}

	public void logTraceAtPoint(org.jacorb.util.tracing.Request origin, java.lang.String operation, long client_time, long server_time) throws org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestId
	{
_delegate.logTraceAtPoint(origin,operation,client_time,server_time);
	}

	public org.jacorb.util.tracing.TraceData getTrace(org.jacorb.util.tracing.Request source) throws org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestId
	{
		return _delegate.getTrace(source);
	}

	public void registerSubTrace(org.jacorb.util.tracing.Request original, org.jacorb.util.tracing.Request nested) throws org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestId
	{
_delegate.registerSubTrace(original,nested);
	}

}
