package org.omg.dds;


/**
 * Generated from IDL interface "WaitSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class WaitSetPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.WaitSetOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "detach_condition", Integer.valueOf(0));
		m_opsHash.put ( "wait", Integer.valueOf(1));
		m_opsHash.put ( "get_conditions", Integer.valueOf(2));
		m_opsHash.put ( "attach_condition", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:omg.org/dds/WaitSet:1.0"};
	public org.omg.dds.WaitSet _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.WaitSet __r = org.omg.dds.WaitSetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.WaitSet _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.WaitSet __r = org.omg.dds.WaitSetHelper.narrow(__o);
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
			case 0: // detach_condition
			{
				org.omg.dds.Condition _arg0=org.omg.dds.ConditionHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(detach_condition(_arg0));
				break;
			}
			case 1: // wait
			{
				org.omg.dds.ConditionSeqHolder _arg0= new org.omg.dds.ConditionSeqHolder();
				org.omg.dds.Duration_t _arg1=org.omg.dds.Duration_tHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(_wait(_arg0,_arg1));
				org.omg.dds.ConditionSeqHelper.write(_out,_arg0.value);
				break;
			}
			case 2: // get_conditions
			{
				org.omg.dds.ConditionSeqHolder _arg0= new org.omg.dds.ConditionSeqHolder();
				_out = handler.createReply();
				_out.write_long(get_conditions(_arg0));
				org.omg.dds.ConditionSeqHelper.write(_out,_arg0.value);
				break;
			}
			case 3: // attach_condition
			{
				org.omg.dds.Condition _arg0=org.omg.dds.ConditionHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(attach_condition(_arg0));
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
