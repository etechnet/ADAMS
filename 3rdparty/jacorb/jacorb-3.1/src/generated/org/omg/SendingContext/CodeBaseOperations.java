package org.omg.SendingContext;


/**
 * Generated from IDL interface "CodeBase".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface CodeBaseOperations
	extends org.omg.SendingContext.RunTimeOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.Repository get_ir();
	java.lang.String implementation(java.lang.String x);
	java.lang.String implementationx(java.lang.String x);
	java.lang.String[] implementations(java.lang.String[] x);
	org.omg.CORBA.ValueDefPackage.FullValueDescription meta(java.lang.String x);
	org.omg.CORBA.ValueDefPackage.FullValueDescription[] metas(java.lang.String[] x);
	java.lang.String[] bases(java.lang.String x);
}
