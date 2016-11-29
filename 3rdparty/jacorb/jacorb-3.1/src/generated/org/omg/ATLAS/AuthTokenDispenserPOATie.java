package org.omg.ATLAS;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "AuthTokenDispenser".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public class AuthTokenDispenserPOATie
	extends AuthTokenDispenserPOA
{
	private AuthTokenDispenserOperations _delegate;

	private POA _poa;
	public AuthTokenDispenserPOATie(AuthTokenDispenserOperations delegate)
	{
		_delegate = delegate;
	}
	public AuthTokenDispenserPOATie(AuthTokenDispenserOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.ATLAS.AuthTokenDispenser _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.ATLAS.AuthTokenDispenser __r = org.omg.ATLAS.AuthTokenDispenserHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.ATLAS.AuthTokenDispenser _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.ATLAS.AuthTokenDispenser __r = org.omg.ATLAS.AuthTokenDispenserHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public AuthTokenDispenserOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AuthTokenDispenserOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public org.omg.ATLAS.AuthTokenData get_my_authorization_token() throws org.omg.ATLAS.IllegalTokenRequest
	{
		return _delegate.get_my_authorization_token();
	}

	public org.omg.ATLAS.AuthTokenData translate_authorization_token(org.omg.CSI.IdentityToken the_subject, org.omg.CSI.AuthorizationElement[] the_token) throws org.omg.ATLAS.IllegalTokenRequest,org.omg.ATLAS.TokenOkay
	{
		return _delegate.translate_authorization_token(the_subject,the_token);
	}

}
