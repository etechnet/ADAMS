package org.omg.IOP;


/**
 * Generated from IDL interface "CodecFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public class CodecFactoryLocalTie
	extends _CodecFactoryLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private CodecFactoryOperations _delegate;

	public CodecFactoryLocalTie(CodecFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public CodecFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(CodecFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public org.omg.IOP.Codec create_codec(org.omg.IOP.Encoding enc) throws org.omg.IOP.CodecFactoryPackage.UnknownEncoding
	{
		return _delegate.create_codec(enc);
	}

}
