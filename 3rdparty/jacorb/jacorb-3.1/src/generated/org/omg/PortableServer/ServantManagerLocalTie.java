package org.omg.PortableServer;


/**
 * Generated from IDL interface "ServantManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class ServantManagerLocalTie
	extends _ServantManagerLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ServantManagerOperations _delegate;

	public ServantManagerLocalTie(ServantManagerOperations delegate)
	{
		_delegate = delegate;
	}
	public ServantManagerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ServantManagerOperations delegate)
	{
		_delegate = delegate;
	}
}
