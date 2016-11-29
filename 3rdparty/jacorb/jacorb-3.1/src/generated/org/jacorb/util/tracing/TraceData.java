package org.jacorb.util.tracing;

/**
 * Generated from IDL struct "TraceData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class TraceData
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TraceData(){}
	public org.jacorb.util.tracing.TraceData[] subtrace;
	public int tracer_id;
	public java.lang.String operation = "";
	public long client_time;
	public long trace_system_time;
	public TraceData(org.jacorb.util.tracing.TraceData[] subtrace, int tracer_id, java.lang.String operation, long client_time, long trace_system_time)
	{
		this.subtrace = subtrace;
		this.tracer_id = tracer_id;
		this.operation = operation;
		this.client_time = client_time;
		this.trace_system_time = trace_system_time;
	}
}
