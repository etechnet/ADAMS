package org.omg.CosCollection;


/**
 * Generated from IDL interface "Map".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface MapOperations
	extends org.omg.CosCollection.EqualityKeyCollectionOperations
{
	/* constants */
	/* operations  */
	void difference_with(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid;
	void add_difference(org.omg.CosCollection.Map collector1, org.omg.CosCollection.Map collector2) throws org.omg.CosCollection.ElementInvalid;
	void intersection_with(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid;
	void add_intersection(org.omg.CosCollection.Map collector1, org.omg.CosCollection.Map collector2) throws org.omg.CosCollection.ElementInvalid;
	void union_with(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid;
	void add_union(org.omg.CosCollection.Map collector1, org.omg.CosCollection.Map collector2) throws org.omg.CosCollection.ElementInvalid;
	boolean equal(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid;
	boolean not_equal(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid;
}
