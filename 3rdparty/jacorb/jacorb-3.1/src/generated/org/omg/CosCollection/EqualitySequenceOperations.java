package org.omg.CosCollection;


/**
 * Generated from IDL interface "EqualitySequence".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface EqualitySequenceOperations
	extends org.omg.CosCollection.EqualitySequentialCollectionOperations
{
	/* constants */
	/* operations  */
	boolean equal(org.omg.CosCollection.EqualitySequence collector);
	boolean not_equal(org.omg.CosCollection.EqualitySequence collector);
	int compare(org.omg.CosCollection.EqualitySequence collector, org.omg.CosCollection.Comparator comparison);
}
