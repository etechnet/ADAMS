package org.omg.CosTrading;


/**
 * Generated from IDL interface "ImportAttributes".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class _ImportAttributesStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.omg.CosTrading.ImportAttributes
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:omg.org/CosTrading/ImportAttributes:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.omg.CosTrading.ImportAttributesOperations.class;
	public int def_hop_count()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_def_hop_count",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_def_hop_count", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.def_hop_count();
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

	public int def_match_card()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_def_match_card",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_def_match_card", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.def_match_card();
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

	public int max_return_card()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_max_return_card",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_max_return_card", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.max_return_card();
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

	public org.omg.CosTrading.FollowOption max_follow_policy()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_max_follow_policy",true);
					_is = _invoke(_os);
					return org.omg.CosTrading.FollowOptionHelper.read(_is);
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_max_follow_policy", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				org.omg.CosTrading.FollowOption _result;
				try
				{
					_result = _localServant.max_follow_policy();
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

	public int max_list()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_max_list",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_max_list", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.max_list();
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

	public org.omg.CosTrading.FollowOption def_follow_policy()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_def_follow_policy",true);
					_is = _invoke(_os);
					return org.omg.CosTrading.FollowOptionHelper.read(_is);
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_def_follow_policy", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				org.omg.CosTrading.FollowOption _result;
				try
				{
					_result = _localServant.def_follow_policy();
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

	public int max_hop_count()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_max_hop_count",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_max_hop_count", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.max_hop_count();
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

	public int max_match_card()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_max_match_card",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_max_match_card", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.max_match_card();
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

	public int def_search_card()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_def_search_card",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_def_search_card", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.def_search_card();
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

	public int max_search_card()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_max_search_card",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_max_search_card", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.max_search_card();
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

	public int def_return_card()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_def_return_card",true);
					_is = _invoke(_os);
					return _is.read_ulong();
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
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_def_return_card", _opsClass);
				if( _so == null )
					continue;
				ImportAttributesOperations _localServant = (ImportAttributesOperations)_so.servant;
				int _result;
				try
				{
					_result = _localServant.def_return_card();
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
