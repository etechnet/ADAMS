package org.omg.CORBA;


/**
 * Generated from IDL interface "InterfaceAttrExtension".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class _InterfaceAttrExtensionStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.omg.CORBA.InterfaceAttrExtension
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:omg.org/CORBA/InterfaceAttrExtension:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.omg.CORBA.InterfaceAttrExtensionOperations.class;
	public org.omg.CORBA.InterfaceAttrExtensionPackage.ExtFullInterfaceDescription describe_ext_interface()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "describe_ext_interface", true);
					_is = _invoke(_os);
					org.omg.CORBA.InterfaceAttrExtensionPackage.ExtFullInterfaceDescription _result = org.omg.CORBA.InterfaceAttrExtensionPackage.ExtFullInterfaceDescriptionHelper.read(_is);
					return _result;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "describe_ext_interface", _opsClass );
			if( _so == null )
				continue;
			InterfaceAttrExtensionOperations _localServant = (InterfaceAttrExtensionOperations)_so.servant;
			org.omg.CORBA.InterfaceAttrExtensionPackage.ExtFullInterfaceDescription _result;
			try
			{
				_result = _localServant.describe_ext_interface();
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

	public org.omg.CORBA.ExtAttributeDef create_ext_attribute(java.lang.String id, java.lang.String name, java.lang.String version, org.omg.CORBA.IDLType type, org.omg.CORBA.AttributeMode mode, org.omg.CORBA.ExceptionDef[] get_exceptions, org.omg.CORBA.ExceptionDef[] set_exceptions)
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "create_ext_attribute", true);
					java.lang.String tmpResult491 = id;
_os.write_string( tmpResult491 );
					java.lang.String tmpResult492 = name;
_os.write_string( tmpResult492 );
					java.lang.String tmpResult493 = version;
_os.write_string( tmpResult493 );
					org.omg.CORBA.IDLTypeHelper.write(_os,type);
					org.omg.CORBA.AttributeModeHelper.write(_os,mode);
					org.omg.CORBA.ExceptionDefSeqHelper.write(_os,get_exceptions);
					org.omg.CORBA.ExceptionDefSeqHelper.write(_os,set_exceptions);
					_is = _invoke(_os);
					org.omg.CORBA.ExtAttributeDef _result = org.omg.CORBA.ExtAttributeDefHelper.read(_is);
					return _result;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "create_ext_attribute", _opsClass );
			if( _so == null )
				continue;
			InterfaceAttrExtensionOperations _localServant = (InterfaceAttrExtensionOperations)_so.servant;
			org.omg.CORBA.ExtAttributeDef _result;
			try
			{
				_result = _localServant.create_ext_attribute(id,name,version,type,mode,get_exceptions,set_exceptions);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

}
