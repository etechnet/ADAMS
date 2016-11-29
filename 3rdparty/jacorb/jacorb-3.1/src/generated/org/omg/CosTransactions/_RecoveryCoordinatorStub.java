package org.omg.CosTransactions;


/**
 * Generated from IDL interface "RecoveryCoordinator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class _RecoveryCoordinatorStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.omg.CosTransactions.RecoveryCoordinator
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:omg.org/CosTransactions/RecoveryCoordinator:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.omg.CosTransactions.RecoveryCoordinatorOperations.class;
	public org.omg.CosTransactions.Status replay_completion(org.omg.CosTransactions.Resource r) throws org.omg.CosTransactions.NotPrepared
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "replay_completion", true);
					org.omg.CosTransactions.ResourceHelper.write(_os,r);
					_is = _invoke(_os);
					org.omg.CosTransactions.Status _result = org.omg.CosTransactions.StatusHelper.read(_is);
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
						if( _id.equals("IDL:omg.org/CosTransactions/NotPrepared:1.0"))
						{
							throw org.omg.CosTransactions.NotPreparedHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "replay_completion", _opsClass );
			if( _so == null )
				continue;
			RecoveryCoordinatorOperations _localServant = (RecoveryCoordinatorOperations)_so.servant;
			org.omg.CosTransactions.Status _result;
			try
			{
				_result = _localServant.replay_completion(r);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.CosTransactions.NotPrepared ex) 
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
