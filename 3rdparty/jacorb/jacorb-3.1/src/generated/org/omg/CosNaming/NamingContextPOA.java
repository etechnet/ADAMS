package org.omg.CosNaming;


/**
 * Generated from IDL interface "NamingContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class NamingContextPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosNaming.NamingContextOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "list", Integer.valueOf(0));
		m_opsHash.put ( "bind", Integer.valueOf(1));
		m_opsHash.put ( "bind_new_context", Integer.valueOf(2));
		m_opsHash.put ( "rebind_context", Integer.valueOf(3));
		m_opsHash.put ( "bind_context", Integer.valueOf(4));
		m_opsHash.put ( "unbind", Integer.valueOf(5));
		m_opsHash.put ( "new_context", Integer.valueOf(6));
		m_opsHash.put ( "destroy", Integer.valueOf(7));
		m_opsHash.put ( "rebind", Integer.valueOf(8));
		m_opsHash.put ( "resolve", Integer.valueOf(9));
	}
	private String[] ids = {"IDL:omg.org/CosNaming/NamingContext:1.0"};
	public org.omg.CosNaming.NamingContext _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNaming.NamingContext __r = org.omg.CosNaming.NamingContextHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNaming.NamingContext _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNaming.NamingContext __r = org.omg.CosNaming.NamingContextHelper.narrow(__o);
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
			case 0: // list
			{
				int _arg0=_input.read_ulong();
				org.omg.CosNaming.BindingListHolder _arg1= new org.omg.CosNaming.BindingListHolder();
				org.omg.CosNaming.BindingIteratorHolder _arg2= new org.omg.CosNaming.BindingIteratorHolder();
				_out = handler.createReply();
				list(_arg0,_arg1,_arg2);
				org.omg.CosNaming.BindingListHelper.write(_out,_arg1.value);
				org.omg.CosNaming.BindingIteratorHelper.write(_out,_arg2.value);
				break;
			}
			case 1: // bind
			{
			try
			{
				org.omg.CosNaming.NameComponent[] _arg0=org.omg.CosNaming.NameHelper.read(_input);
				org.omg.CORBA.Object _arg1=_input.read_Object();
				_out = handler.createReply();
				bind(_arg0,_arg1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.NotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNaming.NamingContextPackage.AlreadyBound _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.write(_out, _ex1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.CannotProceed _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write(_out, _ex2);
			}
			catch(org.omg.CosNaming.NamingContextPackage.InvalidName _ex3)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write(_out, _ex3);
			}
				break;
			}
			case 2: // bind_new_context
			{
			try
			{
				org.omg.CosNaming.NameComponent[] _arg0=org.omg.CosNaming.NameHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosNaming.NamingContextHelper.write(_out,bind_new_context(_arg0));
			}
			catch(org.omg.CosNaming.NamingContextPackage.NotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNaming.NamingContextPackage.AlreadyBound _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.write(_out, _ex1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.CannotProceed _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write(_out, _ex2);
			}
			catch(org.omg.CosNaming.NamingContextPackage.InvalidName _ex3)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write(_out, _ex3);
			}
				break;
			}
			case 3: // rebind_context
			{
			try
			{
				org.omg.CosNaming.NameComponent[] _arg0=org.omg.CosNaming.NameHelper.read(_input);
				org.omg.CosNaming.NamingContext _arg1=org.omg.CosNaming.NamingContextHelper.read(_input);
				_out = handler.createReply();
				rebind_context(_arg0,_arg1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.NotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNaming.NamingContextPackage.CannotProceed _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write(_out, _ex1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.InvalidName _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write(_out, _ex2);
			}
				break;
			}
			case 4: // bind_context
			{
			try
			{
				org.omg.CosNaming.NameComponent[] _arg0=org.omg.CosNaming.NameHelper.read(_input);
				org.omg.CosNaming.NamingContext _arg1=org.omg.CosNaming.NamingContextHelper.read(_input);
				_out = handler.createReply();
				bind_context(_arg0,_arg1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.NotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNaming.NamingContextPackage.AlreadyBound _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.write(_out, _ex1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.CannotProceed _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write(_out, _ex2);
			}
			catch(org.omg.CosNaming.NamingContextPackage.InvalidName _ex3)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write(_out, _ex3);
			}
				break;
			}
			case 5: // unbind
			{
			try
			{
				org.omg.CosNaming.NameComponent[] _arg0=org.omg.CosNaming.NameHelper.read(_input);
				_out = handler.createReply();
				unbind(_arg0);
			}
			catch(org.omg.CosNaming.NamingContextPackage.NotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNaming.NamingContextPackage.CannotProceed _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write(_out, _ex1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.InvalidName _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write(_out, _ex2);
			}
				break;
			}
			case 6: // new_context
			{
				_out = handler.createReply();
				org.omg.CosNaming.NamingContextHelper.write(_out,new_context());
				break;
			}
			case 7: // destroy
			{
			try
			{
				_out = handler.createReply();
				destroy();
			}
			catch(org.omg.CosNaming.NamingContextPackage.NotEmpty _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.NotEmptyHelper.write(_out, _ex0);
			}
				break;
			}
			case 8: // rebind
			{
			try
			{
				org.omg.CosNaming.NameComponent[] _arg0=org.omg.CosNaming.NameHelper.read(_input);
				org.omg.CORBA.Object _arg1=_input.read_Object();
				_out = handler.createReply();
				rebind(_arg0,_arg1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.NotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNaming.NamingContextPackage.CannotProceed _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write(_out, _ex1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.InvalidName _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write(_out, _ex2);
			}
				break;
			}
			case 9: // resolve
			{
			try
			{
				org.omg.CosNaming.NameComponent[] _arg0=org.omg.CosNaming.NameHelper.read(_input);
				_out = handler.createReply();
				_out.write_Object(resolve(_arg0));
			}
			catch(org.omg.CosNaming.NamingContextPackage.NotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNaming.NamingContextPackage.CannotProceed _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write(_out, _ex1);
			}
			catch(org.omg.CosNaming.NamingContextPackage.InvalidName _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write(_out, _ex2);
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
