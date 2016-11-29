package org.omg.CosCollection;


/**
 * Generated from IDL interface "CollectionFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class CollectionFactoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosCollection.CollectionFactoryOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "generic_create", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:omg.org/CosCollection/CollectionFactory:1.0"};
	public org.omg.CosCollection.CollectionFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.CollectionFactory __r = org.omg.CosCollection.CollectionFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.CollectionFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.CollectionFactory __r = org.omg.CosCollection.CollectionFactoryHelper.narrow(__o);
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
			case 0: // generic_create
			{
			try
			{
				org.omg.CosCollection.NVPair[] _arg0=org.omg.CosCollection.ParameterListHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosCollection.CollectionHelper.write(_out,generic_create(_arg0));
			}
			catch(org.omg.CosCollection.ParameterInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ParameterInvalidHelper.write(_out, _ex0);
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
