package org.omg.ETF;


/**
 * Generated from IDL interface "Factories".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class FactoriesLocalTie
	extends _FactoriesLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private FactoriesOperations _delegate;

	public FactoriesLocalTie(FactoriesOperations delegate)
	{
		_delegate = delegate;
	}
	public FactoriesOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(FactoriesOperations delegate)
	{
		_delegate = delegate;
	}
	public org.omg.ETF.Profile demarshal_profile(org.omg.IOP.TaggedProfileHolder tagged_profile, org.omg.IOP.TaggedComponentSeqHolder components)
	{
		return _delegate.demarshal_profile(tagged_profile,components);
	}

	public int profile_tag()
	{
		return _delegate.profile_tag();
	}

	public org.omg.ETF.Connection create_connection(org.omg.RTCORBA.ProtocolProperties props)
	{
		return _delegate.create_connection(props);
	}

	public org.omg.ETF.Listener create_listener(org.omg.RTCORBA.ProtocolProperties props, int stacksize, short base_priority)
	{
		return _delegate.create_listener(props,stacksize,base_priority);
	}

	public org.omg.ETF.Profile decode_corbaloc(java.lang.String corbaloc)
	{
		return _delegate.decode_corbaloc(corbaloc);
	}

}
