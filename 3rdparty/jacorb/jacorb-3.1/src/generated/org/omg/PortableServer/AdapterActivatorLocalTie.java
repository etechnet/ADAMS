package org.omg.PortableServer;


/**
 * Generated from IDL interface "AdapterActivator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class AdapterActivatorLocalTie
	extends _AdapterActivatorLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private AdapterActivatorOperations _delegate;

	public AdapterActivatorLocalTie(AdapterActivatorOperations delegate)
	{
		_delegate = delegate;
	}
	public AdapterActivatorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AdapterActivatorOperations delegate)
	{
		_delegate = delegate;
	}
	public boolean unknown_adapter(org.omg.PortableServer.POA parent, java.lang.String name)
	{
		return _delegate.unknown_adapter(parent,name);
	}

}
