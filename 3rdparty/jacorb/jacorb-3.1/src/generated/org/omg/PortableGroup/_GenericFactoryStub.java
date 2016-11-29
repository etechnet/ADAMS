package org.omg.PortableGroup;


/**
 * Generated from IDL interface "GenericFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class _GenericFactoryStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.omg.PortableGroup.GenericFactory
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:omg.org/PortableGroup/GenericFactory:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.omg.PortableGroup.GenericFactoryOperations.class;
	public org.omg.CORBA.Object create_object(java.lang.String type_id, org.omg.PortableGroup.Property[] the_criteria, org.omg.CORBA.AnyHolder factory_creation_id) throws org.omg.PortableGroup.InvalidProperty,org.omg.PortableGroup.ObjectNotCreated,org.omg.PortableGroup.InvalidCriteria,org.omg.PortableGroup.CannotMeetCriteria,org.omg.PortableGroup.NoFactory
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "create_object", true);
					java.lang.String tmpResult1004 = type_id;
_os.write_string( tmpResult1004 );
					org.omg.PortableGroup.PropertiesHelper.write(_os,the_criteria);
					_is = _invoke(_os);
					org.omg.CORBA.Object _result = _is.read_Object();
					factory_creation_id.value = _is.read_any();
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
						if( _id.equals("IDL:omg.org/PortableGroup/InvalidProperty:1.0"))
						{
							throw org.omg.PortableGroup.InvalidPropertyHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectNotCreated:1.0"))
						{
							throw org.omg.PortableGroup.ObjectNotCreatedHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/InvalidCriteria:1.0"))
						{
							throw org.omg.PortableGroup.InvalidCriteriaHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/CannotMeetCriteria:1.0"))
						{
							throw org.omg.PortableGroup.CannotMeetCriteriaHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/NoFactory:1.0"))
						{
							throw org.omg.PortableGroup.NoFactoryHelper.read(_ax.getInputStream());
						}
						else 
						{
							throw new RuntimeException("Unexpected exception " + _id );
						}
					}
					finally
					{
						try
						{
							_ax.getInputStream().close();
						}
						catch (java.io.IOException e)
						{
							throw new RuntimeException("Unexpected exception " + e.toString() );
						}
					}
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "create_object", _opsClass );
			if( _so == null )
				continue;
			GenericFactoryOperations _localServant = (GenericFactoryOperations)_so.servant;
			org.omg.CORBA.Object _result;
			try
			{
				_result = _localServant.create_object(type_id,the_criteria,factory_creation_id);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.PortableGroup.InvalidProperty ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.ObjectNotCreated ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.InvalidCriteria ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.CannotMeetCriteria ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.NoFactory ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
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

	public void delete_object(org.omg.CORBA.Any factory_creation_id) throws org.omg.PortableGroup.ObjectNotFound
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "delete_object", true);
					_os.write_any(factory_creation_id);
					_is = _invoke(_os);
					return;
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
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectNotFound:1.0"))
						{
							throw org.omg.PortableGroup.ObjectNotFoundHelper.read(_ax.getInputStream());
						}
						else 
						{
							throw new RuntimeException("Unexpected exception " + _id );
						}
					}
					finally
					{
						try
						{
							_ax.getInputStream().close();
						}
						catch (java.io.IOException e)
						{
							throw new RuntimeException("Unexpected exception " + e.toString() );
						}
					}
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "delete_object", _opsClass );
			if( _so == null )
				continue;
			GenericFactoryOperations _localServant = (GenericFactoryOperations)_so.servant;
			try
			{
				_localServant.delete_object(factory_creation_id);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
			}
			catch (org.omg.PortableGroup.ObjectNotFound ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
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
