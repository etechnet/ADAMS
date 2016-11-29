package org.omg.ATLAS;


/**
 * Generated from IDL interface "AuthTokenDispenser".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public class _AuthTokenDispenserStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.omg.ATLAS.AuthTokenDispenser
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:omg.org/ATLAS/AuthTokenDispenser:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.omg.ATLAS.AuthTokenDispenserOperations.class;
	public org.omg.ATLAS.AuthTokenData get_my_authorization_token() throws org.omg.ATLAS.IllegalTokenRequest
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "get_my_authorization_token", true);
					_is = _invoke(_os);
					org.omg.ATLAS.AuthTokenData _result = org.omg.ATLAS.AuthTokenDataHelper.read(_is);
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
						if( _id.equals("IDL:omg.org/ATLAS/IllegalTokenRequest:1.0"))
						{
							throw org.omg.ATLAS.IllegalTokenRequestHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "get_my_authorization_token", _opsClass );
			if( _so == null )
				continue;
			AuthTokenDispenserOperations _localServant = (AuthTokenDispenserOperations)_so.servant;
			org.omg.ATLAS.AuthTokenData _result;
			try
			{
				_result = _localServant.get_my_authorization_token();
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.ATLAS.IllegalTokenRequest ex) 
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

	public org.omg.ATLAS.AuthTokenData translate_authorization_token(org.omg.CSI.IdentityToken the_subject, org.omg.CSI.AuthorizationElement[] the_token) throws org.omg.ATLAS.IllegalTokenRequest,org.omg.ATLAS.TokenOkay
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "translate_authorization_token", true);
					org.omg.CSI.IdentityTokenHelper.write(_os,the_subject);
					org.omg.CSI.AuthorizationTokenHelper.write(_os,the_token);
					_is = _invoke(_os);
					org.omg.ATLAS.AuthTokenData _result = org.omg.ATLAS.AuthTokenDataHelper.read(_is);
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
						if( _id.equals("IDL:omg.org/ATLAS/IllegalTokenRequest:1.0"))
						{
							throw org.omg.ATLAS.IllegalTokenRequestHelper.read(_ax.getInputStream());
						}
						else 
						if( _id.equals("IDL:omg.org/ATLAS/TokenOkay:1.0"))
						{
							throw org.omg.ATLAS.TokenOkayHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "translate_authorization_token", _opsClass );
			if( _so == null )
				continue;
			AuthTokenDispenserOperations _localServant = (AuthTokenDispenserOperations)_so.servant;
			org.omg.ATLAS.AuthTokenData _result;
			try
			{
				_result = _localServant.translate_authorization_token(the_subject,the_token);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (org.omg.ATLAS.IllegalTokenRequest ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (org.omg.ATLAS.TokenOkay ex) 
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
