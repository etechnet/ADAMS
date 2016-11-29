package org.omg.CosTrading;


/**
 * Generated from IDL interface "OfferIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface OfferIteratorOperations
{
	/* constants */
	/* operations  */
	int max_left() throws org.omg.CosTrading.UnknownMaxLeft;
	boolean next_n(int n, org.omg.CosTrading.OfferSeqHolder offers);
	void destroy();
}
