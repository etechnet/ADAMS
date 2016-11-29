package org.omg.CosCollection;

/**
 * Generated from IDL exception "IteratorInvalid".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IteratorInvalid
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IteratorInvalid()
	{
		super(org.omg.CosCollection.IteratorInvalidHelper.id());
	}

	public org.omg.CosCollection.IteratorInvalidReason why;
	public IteratorInvalid(java.lang.String _reason,org.omg.CosCollection.IteratorInvalidReason why)
	{
		super(_reason);
		this.why = why;
	}
	public IteratorInvalid(org.omg.CosCollection.IteratorInvalidReason why)
	{
		super(org.omg.CosCollection.IteratorInvalidHelper.id());
		this.why = why;
	}
}
