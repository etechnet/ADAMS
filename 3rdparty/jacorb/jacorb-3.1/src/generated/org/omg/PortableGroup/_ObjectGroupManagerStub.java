package org.omg.PortableGroup;


/**
 * Generated from IDL interface "ObjectGroupManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class _ObjectGroupManagerStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.omg.PortableGroup.ObjectGroupManager
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:omg.org/PortableGroup/ObjectGroupManager:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.omg.PortableGroup.ObjectGroupManagerOperations.class;
	public org.omg.CORBA.Object create_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location, java.lang.String type_id, org.omg.PortableGroup.Property[] the_criteria) throws org.omg.PortableGroup.ObjectNotCreated,org.omg.PortableGroup.MemberAlreadyPresent,org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.InvalidCriteria,org.omg.PortableGroup.CannotMeetCriteria,org.omg.PortableGroup.NoFactory
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "create_member", true);
					_os.write_Object(object_group);
					org.omg.CosNaming.NameHelper.write(_os,the_location);
					java.lang.String tmpResult1003 = type_id;
_os.write_string( tmpResult1003 );
					org.omg.PortableGroup.PropertiesHelper.write(_os,the_criteria);
					_is = _invoke(_os);
					org.omg.CORBA.Object _result = _is.read_Object();
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
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectNotCreated:1.0"))
						{
							throw org.omg.PortableGroup.ObjectNotCreatedHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/MemberAlreadyPresent:1.0"))
						{
							throw org.omg.PortableGroup.MemberAlreadyPresentHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectGroupNotFound:1.0"))
						{
							throw org.omg.PortableGroup.ObjectGroupNotFoundHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "create_member", _opsClass );
			if( _so == null )
				continue;
			ObjectGroupManagerOperations _localServant = (ObjectGroupManagerOperations)_so.servant;
			org.omg.CORBA.Object _result;
			try
			{
				_result = _localServant.create_member(object_group,the_location,type_id,the_criteria);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.PortableGroup.ObjectNotCreated ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.MemberAlreadyPresent ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.ObjectGroupNotFound ex) 
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

	public org.omg.CosNaming.NameComponent[][] locations_of_members(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "locations_of_members", true);
					_os.write_Object(object_group);
					_is = _invoke(_os);
					org.omg.CosNaming.NameComponent[][] _result = org.omg.PortableGroup.LocationsHelper.read(_is);
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
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectGroupNotFound:1.0"))
						{
							throw org.omg.PortableGroup.ObjectGroupNotFoundHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "locations_of_members", _opsClass );
			if( _so == null )
				continue;
			ObjectGroupManagerOperations _localServant = (ObjectGroupManagerOperations)_so.servant;
			org.omg.CosNaming.NameComponent[][] _result;
			try
			{
				_result = _localServant.locations_of_members(object_group);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.PortableGroup.ObjectGroupNotFound ex) 
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

	public org.omg.CORBA.Object get_member_ref(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] loc) throws org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.MemberNotFound
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "get_member_ref", true);
					_os.write_Object(object_group);
					org.omg.CosNaming.NameHelper.write(_os,loc);
					_is = _invoke(_os);
					org.omg.CORBA.Object _result = _is.read_Object();
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
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectGroupNotFound:1.0"))
						{
							throw org.omg.PortableGroup.ObjectGroupNotFoundHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/MemberNotFound:1.0"))
						{
							throw org.omg.PortableGroup.MemberNotFoundHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "get_member_ref", _opsClass );
			if( _so == null )
				continue;
			ObjectGroupManagerOperations _localServant = (ObjectGroupManagerOperations)_so.servant;
			org.omg.CORBA.Object _result;
			try
			{
				_result = _localServant.get_member_ref(object_group,loc);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.PortableGroup.ObjectGroupNotFound ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.MemberNotFound ex) 
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

	public long get_object_group_id(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "get_object_group_id", true);
					_os.write_Object(object_group);
					_is = _invoke(_os);
					long _result = _is.read_ulonglong();
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
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectGroupNotFound:1.0"))
						{
							throw org.omg.PortableGroup.ObjectGroupNotFoundHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "get_object_group_id", _opsClass );
			if( _so == null )
				continue;
			ObjectGroupManagerOperations _localServant = (ObjectGroupManagerOperations)_so.servant;
			long _result;
			try
			{
				_result = _localServant.get_object_group_id(object_group);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.PortableGroup.ObjectGroupNotFound ex) 
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

	public org.omg.CORBA.Object get_object_group_ref(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "get_object_group_ref", true);
					_os.write_Object(object_group);
					_is = _invoke(_os);
					org.omg.CORBA.Object _result = _is.read_Object();
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
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectGroupNotFound:1.0"))
						{
							throw org.omg.PortableGroup.ObjectGroupNotFoundHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "get_object_group_ref", _opsClass );
			if( _so == null )
				continue;
			ObjectGroupManagerOperations _localServant = (ObjectGroupManagerOperations)_so.servant;
			org.omg.CORBA.Object _result;
			try
			{
				_result = _localServant.get_object_group_ref(object_group);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.PortableGroup.ObjectGroupNotFound ex) 
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

	public org.omg.CORBA.Object remove_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location) throws org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.MemberNotFound
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "remove_member", true);
					_os.write_Object(object_group);
					org.omg.CosNaming.NameHelper.write(_os,the_location);
					_is = _invoke(_os);
					org.omg.CORBA.Object _result = _is.read_Object();
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
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectGroupNotFound:1.0"))
						{
							throw org.omg.PortableGroup.ObjectGroupNotFoundHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/MemberNotFound:1.0"))
						{
							throw org.omg.PortableGroup.MemberNotFoundHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "remove_member", _opsClass );
			if( _so == null )
				continue;
			ObjectGroupManagerOperations _localServant = (ObjectGroupManagerOperations)_so.servant;
			org.omg.CORBA.Object _result;
			try
			{
				_result = _localServant.remove_member(object_group,the_location);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.PortableGroup.ObjectGroupNotFound ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.MemberNotFound ex) 
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

	public org.omg.CORBA.Object add_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location, org.omg.CORBA.Object member) throws org.omg.CORBA.INV_OBJREF,org.omg.PortableGroup.MemberAlreadyPresent,org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.ObjectNotAdded
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "add_member", true);
					_os.write_Object(object_group);
					org.omg.CosNaming.NameHelper.write(_os,the_location);
					_os.write_Object(member);
					_is = _invoke(_os);
					org.omg.CORBA.Object _result = _is.read_Object();
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
						if( _id.equals("IDL:omg.org/CORBA/INV_OBJREF:1.0"))
						{
							throw org.omg.CORBA.INV_OBJREFHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/MemberAlreadyPresent:1.0"))
						{
							throw org.omg.PortableGroup.MemberAlreadyPresentHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectGroupNotFound:1.0"))
						{
							throw org.omg.PortableGroup.ObjectGroupNotFoundHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/PortableGroup/ObjectNotAdded:1.0"))
						{
							throw org.omg.PortableGroup.ObjectNotAddedHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "add_member", _opsClass );
			if( _so == null )
				continue;
			ObjectGroupManagerOperations _localServant = (ObjectGroupManagerOperations)_so.servant;
			org.omg.CORBA.Object _result;
			try
			{
				_result = _localServant.add_member(object_group,the_location,member);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CORBA.INV_OBJREF ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.MemberAlreadyPresent ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.ObjectGroupNotFound ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.PortableGroup.ObjectNotAdded ex) 
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
