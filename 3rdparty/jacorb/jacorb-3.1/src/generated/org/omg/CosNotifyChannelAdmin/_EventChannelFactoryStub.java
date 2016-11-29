package org.omg.CosNotifyChannelAdmin;


/**
 * Generated from IDL interface "EventChannelFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class _EventChannelFactoryStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.omg.CosNotifyChannelAdmin.EventChannelFactory
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:omg.org/CosNotifyChannelAdmin/EventChannelFactory:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.omg.CosNotifyChannelAdmin.EventChannelFactoryOperations.class;
	public org.omg.CosNotifyChannelAdmin.EventChannel create_channel(org.omg.CosNotification.Property[] initial_qos, org.omg.CosNotification.Property[] initial_admin, org.omg.CORBA.IntHolder id) throws org.omg.CosNotification.UnsupportedAdmin,org.omg.CosNotification.UnsupportedQoS
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "create_channel", true);
					org.omg.CosNotification.PropertySeqHelper.write(_os,initial_qos);
					org.omg.CosNotification.PropertySeqHelper.write(_os,initial_admin);
					_is = _invoke(_os);
					org.omg.CosNotifyChannelAdmin.EventChannel _result = org.omg.CosNotifyChannelAdmin.EventChannelHelper.read(_is);
					id.value = _is.read_long();
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
						if( _id.equals("IDL:omg.org/CosNotification/UnsupportedAdmin:1.0"))
						{
							throw org.omg.CosNotification.UnsupportedAdminHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/CosNotification/UnsupportedQoS:1.0"))
						{
							throw org.omg.CosNotification.UnsupportedQoSHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "create_channel", _opsClass );
			if( _so == null )
				continue;
			EventChannelFactoryOperations _localServant = (EventChannelFactoryOperations)_so.servant;
			org.omg.CosNotifyChannelAdmin.EventChannel _result;
			try
			{
				_result = _localServant.create_channel(initial_qos,initial_admin,id);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CosNotification.UnsupportedAdmin ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.CosNotification.UnsupportedQoS ex) 
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

	public org.omg.CosNotifyChannelAdmin.EventChannel get_event_channel(int id) throws org.omg.CosNotifyChannelAdmin.ChannelNotFound
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "get_event_channel", true);
					_os.write_long(id);
					_is = _invoke(_os);
					org.omg.CosNotifyChannelAdmin.EventChannel _result = org.omg.CosNotifyChannelAdmin.EventChannelHelper.read(_is);
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
						if( _id.equals("IDL:omg.org/CosNotifyChannelAdmin/ChannelNotFound:1.0"))
						{
							throw org.omg.CosNotifyChannelAdmin.ChannelNotFoundHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "get_event_channel", _opsClass );
			if( _so == null )
				continue;
			EventChannelFactoryOperations _localServant = (EventChannelFactoryOperations)_so.servant;
			org.omg.CosNotifyChannelAdmin.EventChannel _result;
			try
			{
				_result = _localServant.get_event_channel(id);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CosNotifyChannelAdmin.ChannelNotFound ex) 
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

	public int[] get_all_channels()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "get_all_channels", true);
					_is = _invoke(_os);
					int[] _result = org.omg.CosNotifyChannelAdmin.ChannelIDSeqHelper.read(_is);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "get_all_channels", _opsClass );
			if( _so == null )
				continue;
			EventChannelFactoryOperations _localServant = (EventChannelFactoryOperations)_so.servant;
			int[] _result;
			try
			{
				_result = _localServant.get_all_channels();
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
