package org.omg.CosNotifyFilter;


/**
 * Generated from IDL interface "MappingFilter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class _MappingFilterStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.omg.CosNotifyFilter.MappingFilter
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:omg.org/CosNotifyFilter/MappingFilter:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.omg.CosNotifyFilter.MappingFilterOperations.class;
	public org.omg.CosNotifyFilter.MappingConstraintInfo[] add_mapping_constraints(org.omg.CosNotifyFilter.MappingConstraintPair[] pair_list) throws org.omg.CosNotifyFilter.InvalidValue,org.omg.CosNotifyFilter.InvalidConstraint
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "add_mapping_constraints", true);
					org.omg.CosNotifyFilter.MappingConstraintPairSeqHelper.write(_os,pair_list);
					_is = _invoke(_os);
					org.omg.CosNotifyFilter.MappingConstraintInfo[] _result = org.omg.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(_is);
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
						if( _id.equals("IDL:omg.org/CosNotifyFilter/InvalidValue:1.0"))
						{
							throw org.omg.CosNotifyFilter.InvalidValueHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/CosNotifyFilter/InvalidConstraint:1.0"))
						{
							throw org.omg.CosNotifyFilter.InvalidConstraintHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "add_mapping_constraints", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			org.omg.CosNotifyFilter.MappingConstraintInfo[] _result;
			try
			{
				_result = _localServant.add_mapping_constraints(pair_list);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CosNotifyFilter.InvalidValue ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.CosNotifyFilter.InvalidConstraint ex) 
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

	public boolean match_typed(org.omg.CosNotification.Property[] filterable_data, org.omg.CORBA.AnyHolder result_to_set) throws org.omg.CosNotifyFilter.UnsupportedFilterableData
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "match_typed", true);
					org.omg.CosNotification.PropertySeqHelper.write(_os,filterable_data);
					_is = _invoke(_os);
					boolean _result = _is.read_boolean();
					result_to_set.value = _is.read_any();
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
						if( _id.equals("IDL:omg.org/CosNotifyFilter/UnsupportedFilterableData:1.0"))
						{
							throw org.omg.CosNotifyFilter.UnsupportedFilterableDataHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "match_typed", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			boolean _result;
			try
			{
				_result = _localServant.match_typed(filterable_data,result_to_set);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CosNotifyFilter.UnsupportedFilterableData ex) 
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

	public java.lang.String constraint_grammar()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_constraint_grammar",true);
					_is = _invoke(_os);
					return _is.read_string();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_constraint_grammar", _opsClass);
				if( _so == null )
					continue;
				MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
				java.lang.String _result;
				try
				{
					_result = _localServant.constraint_grammar();
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

	public void modify_mapping_constraints(int[] del_list, org.omg.CosNotifyFilter.MappingConstraintInfo[] modify_list) throws org.omg.CosNotifyFilter.ConstraintNotFound,org.omg.CosNotifyFilter.InvalidValue,org.omg.CosNotifyFilter.InvalidConstraint
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "modify_mapping_constraints", true);
					org.omg.CosNotifyFilter.ConstraintIDSeqHelper.write(_os,del_list);
					org.omg.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(_os,modify_list);
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
						if( _id.equals("IDL:omg.org/CosNotifyFilter/ConstraintNotFound:1.0"))
						{
							throw org.omg.CosNotifyFilter.ConstraintNotFoundHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/CosNotifyFilter/InvalidValue:1.0"))
						{
							throw org.omg.CosNotifyFilter.InvalidValueHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/CosNotifyFilter/InvalidConstraint:1.0"))
						{
							throw org.omg.CosNotifyFilter.InvalidConstraintHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "modify_mapping_constraints", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			try
			{
				_localServant.modify_mapping_constraints(del_list,modify_list);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
			}
			catch (org.omg.CosNotifyFilter.ConstraintNotFound ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.CosNotifyFilter.InvalidValue ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.CosNotifyFilter.InvalidConstraint ex) 
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

	public org.omg.CORBA.TypeCode value_type()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_value_type",true);
					_is = _invoke(_os);
					return _is.read_TypeCode();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_value_type", _opsClass);
				if( _so == null )
					continue;
				MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
				org.omg.CORBA.TypeCode _result;
				try
				{
					_result = _localServant.value_type();
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

	public org.omg.CORBA.Any default_value()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_default_value",true);
					_is = _invoke(_os);
					return _is.read_any();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_default_value", _opsClass);
				if( _so == null )
					continue;
				MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
				org.omg.CORBA.Any _result;
				try
				{
					_result = _localServant.default_value();
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

	public void remove_all_mapping_constraints()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "remove_all_mapping_constraints", true);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "remove_all_mapping_constraints", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			try
			{
				_localServant.remove_all_mapping_constraints();
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
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

	public void destroy()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "destroy", true);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "destroy", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			try
			{
				_localServant.destroy();
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
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

	public org.omg.CosNotifyFilter.MappingConstraintInfo[] get_mapping_constraints(int[] id_list) throws org.omg.CosNotifyFilter.ConstraintNotFound
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "get_mapping_constraints", true);
					org.omg.CosNotifyFilter.ConstraintIDSeqHelper.write(_os,id_list);
					_is = _invoke(_os);
					org.omg.CosNotifyFilter.MappingConstraintInfo[] _result = org.omg.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(_is);
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
						if( _id.equals("IDL:omg.org/CosNotifyFilter/ConstraintNotFound:1.0"))
						{
							throw org.omg.CosNotifyFilter.ConstraintNotFoundHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "get_mapping_constraints", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			org.omg.CosNotifyFilter.MappingConstraintInfo[] _result;
			try
			{
				_result = _localServant.get_mapping_constraints(id_list);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CosNotifyFilter.ConstraintNotFound ex) 
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

	public boolean match_structured(org.omg.CosNotification.StructuredEvent filterable_data, org.omg.CORBA.AnyHolder result_to_set) throws org.omg.CosNotifyFilter.UnsupportedFilterableData
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "match_structured", true);
					org.omg.CosNotification.StructuredEventHelper.write(_os,filterable_data);
					_is = _invoke(_os);
					boolean _result = _is.read_boolean();
					result_to_set.value = _is.read_any();
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
						if( _id.equals("IDL:omg.org/CosNotifyFilter/UnsupportedFilterableData:1.0"))
						{
							throw org.omg.CosNotifyFilter.UnsupportedFilterableDataHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "match_structured", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			boolean _result;
			try
			{
				_result = _localServant.match_structured(filterable_data,result_to_set);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CosNotifyFilter.UnsupportedFilterableData ex) 
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

	public org.omg.CosNotifyFilter.MappingConstraintInfo[] get_all_mapping_constraints()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "get_all_mapping_constraints", true);
					_is = _invoke(_os);
					org.omg.CosNotifyFilter.MappingConstraintInfo[] _result = org.omg.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(_is);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "get_all_mapping_constraints", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			org.omg.CosNotifyFilter.MappingConstraintInfo[] _result;
			try
			{
				_result = _localServant.get_all_mapping_constraints();
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

	public boolean match(org.omg.CORBA.Any filterable_data, org.omg.CORBA.AnyHolder result_to_set) throws org.omg.CosNotifyFilter.UnsupportedFilterableData
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "match", true);
					_os.write_any(filterable_data);
					_is = _invoke(_os);
					boolean _result = _is.read_boolean();
					result_to_set.value = _is.read_any();
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
						if( _id.equals("IDL:omg.org/CosNotifyFilter/UnsupportedFilterableData:1.0"))
						{
							throw org.omg.CosNotifyFilter.UnsupportedFilterableDataHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "match", _opsClass );
			if( _so == null )
				continue;
			MappingFilterOperations _localServant = (MappingFilterOperations)_so.servant;
			boolean _result;
			try
			{
				_result = _localServant.match(filterable_data,result_to_set);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CosNotifyFilter.UnsupportedFilterableData ex) 
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
