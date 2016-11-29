package org.omg.dds;


/**
 * Generated from IDL interface "DomainParticipantFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class DomainParticipantFactoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.DomainParticipantFactoryOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "lookup_participant", Integer.valueOf(0));
		m_opsHash.put ( "create_participant", Integer.valueOf(1));
		m_opsHash.put ( "get_default_participant_qos", Integer.valueOf(2));
		m_opsHash.put ( "delete_participant", Integer.valueOf(3));
		m_opsHash.put ( "set_default_participant_qos", Integer.valueOf(4));
	}
	private String[] ids = {"IDL:omg.org/dds/DomainParticipantFactory:1.0"};
	public org.omg.dds.DomainParticipantFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.DomainParticipantFactory __r = org.omg.dds.DomainParticipantFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.DomainParticipantFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.DomainParticipantFactory __r = org.omg.dds.DomainParticipantFactoryHelper.narrow(__o);
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
			case 0: // lookup_participant
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				org.omg.dds.DomainParticipantHelper.write(_out,lookup_participant(_arg0));
				break;
			}
			case 1: // create_participant
			{
				int _arg0=_input.read_long();
				org.omg.dds.DomainParticipantQos _arg1=org.omg.dds.DomainParticipantQosHelper.read(_input);
				org.omg.dds.DomainParticipantListener _arg2=org.omg.dds.DomainParticipantListenerHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.DomainParticipantHelper.write(_out,create_participant(_arg0,_arg1,_arg2));
				break;
			}
			case 2: // get_default_participant_qos
			{
				org.omg.dds.DomainParticipantQosHolder _arg0= new org.omg.dds.DomainParticipantQosHolder();
				_out = handler.createReply();
				get_default_participant_qos(_arg0);
				org.omg.dds.DomainParticipantQosHelper.write(_out,_arg0.value);
				break;
			}
			case 3: // delete_participant
			{
				org.omg.dds.DomainParticipant _arg0=org.omg.dds.DomainParticipantHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(delete_participant(_arg0));
				break;
			}
			case 4: // set_default_participant_qos
			{
				org.omg.dds.DomainParticipantQos _arg0=org.omg.dds.DomainParticipantQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_default_participant_qos(_arg0));
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
