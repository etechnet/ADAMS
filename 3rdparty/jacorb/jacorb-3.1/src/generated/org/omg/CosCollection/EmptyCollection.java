package org.omg.CosCollection;

/**
 * Generated from IDL exception "EmptyCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class EmptyCollection
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public EmptyCollection()
	{
		super(org.omg.CosCollection.EmptyCollectionHelper.id());
	}

	public EmptyCollection(String value)
	{
		super(value);
	}
}
