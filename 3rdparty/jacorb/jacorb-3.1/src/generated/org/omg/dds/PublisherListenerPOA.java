package org.omg.dds;


/**
 * Generated from IDL interface "PublisherListener".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class PublisherListenerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.PublisherListenerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "on_offered_deadline_missed", Integer.valueOf(0));
		m_opsHash.put ( "on_liveliness_lost", Integer.valueOf(1));
		m_opsHash.put ( "on_offered_incompatible_qos", Integer.valueOf(2));
		m_opsHash.put ( "on_publication_match", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:omg.org/dds/PublisherListener:1.0","IDL:omg.org/dds/DataWriterListener:1.0","IDL:omg.org/dds/Listener:1.0"};
	public org.omg.dds.PublisherListener _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.PublisherListener __r = org.omg.dds.PublisherListenerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.PublisherListener _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.PublisherListener __r = org.omg.dds.PublisherListenerHelper.narrow(__o);
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
			case 0: // on_offered_deadline_missed
			{
				org.omg.dds.DataWriter _arg0=org.omg.dds.DataWriterHelper.read(_input);
				org.omg.dds.OfferedDeadlineMissedStatus _arg1=org.omg.dds.OfferedDeadlineMissedStatusHelper.read(_input);
				_out = handler.createReply();
				on_offered_deadline_missed(_arg0,_arg1);
				break;
			}
			case 1: // on_liveliness_lost
			{
				org.omg.dds.DataWriter _arg0=org.omg.dds.DataWriterHelper.read(_input);
				org.omg.dds.LivelinessLostStatus _arg1=org.omg.dds.LivelinessLostStatusHelper.read(_input);
				_out = handler.createReply();
				on_liveliness_lost(_arg0,_arg1);
				break;
			}
			case 2: // on_offered_incompatible_qos
			{
				org.omg.dds.DataWriter _arg0=org.omg.dds.DataWriterHelper.read(_input);
				org.omg.dds.OfferedIncompatibleQosStatus _arg1=org.omg.dds.OfferedIncompatibleQosStatusHelper.read(_input);
				_out = handler.createReply();
				on_offered_incompatible_qos(_arg0,_arg1);
				break;
			}
			case 3: // on_publication_match
			{
				org.omg.dds.DataWriter _arg0=org.omg.dds.DataWriterHelper.read(_input);
				org.omg.dds.PublicationMatchStatus _arg1=org.omg.dds.PublicationMatchStatusHelper.read(_input);
				_out = handler.createReply();
				on_publication_match(_arg0,_arg1);
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
