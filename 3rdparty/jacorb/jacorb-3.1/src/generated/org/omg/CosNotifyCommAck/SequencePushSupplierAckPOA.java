package org.omg.CosNotifyCommAck;


/**
 * Generated from IDL interface "SequencePushSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public abstract class SequencePushSupplierAckPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosNotifyCommAck.SequencePushSupplierAckOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "subscription_change", Integer.valueOf(0));
		m_opsHash.put ( "acknowledge", Integer.valueOf(1));
		m_opsHash.put ( "disconnect_sequence_push_supplier", Integer.valueOf(2));
	}
	private String[] ids = {"IDL:omg.org/CosNotifyCommAck/SequencePushSupplierAck:1.0","IDL:omg.org/CosNotifyComm/SequencePushSupplier:1.0","IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0"};
	public org.omg.CosNotifyCommAck.SequencePushSupplierAck _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyCommAck.SequencePushSupplierAck __r = org.omg.CosNotifyCommAck.SequencePushSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyCommAck.SequencePushSupplierAck _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyCommAck.SequencePushSupplierAck __r = org.omg.CosNotifyCommAck.SequencePushSupplierAckHelper.narrow(__o);
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
			case 0: // subscription_change
			{
			try
			{
				org.omg.CosNotification.EventType[] _arg0=org.omg.CosNotification.EventTypeSeqHelper.read(_input);
				org.omg.CosNotification.EventType[] _arg1=org.omg.CosNotification.EventTypeSeqHelper.read(_input);
				_out = handler.createReply();
				subscription_change(_arg0,_arg1);
			}
			catch(org.omg.CosNotifyComm.InvalidEventType _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyComm.InvalidEventTypeHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // acknowledge
			{
				int[] _arg0=org.omg.CosNotifyCommAck.SequenceNumbersHelper.read(_input);
				_out = handler.createReply();
				acknowledge(_arg0);
				break;
			}
			case 2: // disconnect_sequence_push_supplier
			{
				_out = handler.createReply();
				disconnect_sequence_push_supplier();
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
