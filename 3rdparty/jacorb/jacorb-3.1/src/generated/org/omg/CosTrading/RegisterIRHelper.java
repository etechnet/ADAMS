package org.omg.CosTrading;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class RegisterIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("withdraw", "(in:id org.omg.CosTrading.OfferId)");
		irInfo.put("describe", "(in:id org.omg.CosTrading.OfferId)");
		irInfo.put("resolve", "(in:name org.omg.CosTrading.TraderName)");
		irInfo.put("export", "org.omg.CosTrading.OfferId(in:reference ,in:type org.omg.CosTrading.ServiceTypeName,in:properties org.omg.CosTrading.PropertySeq)");
		irInfo.put("modify", "(in:id org.omg.CosTrading.OfferId,in:del_list org.omg.CosTrading.PropertyNameSeq,in:modify_list org.omg.CosTrading.PropertySeq)");
		irInfo.put("withdraw_using_constraint", "(in:type org.omg.CosTrading.ServiceTypeName,in:constr org.omg.CosTrading.Constraint)");
	}
}
