package org.omg.CosTrading.ProxyPackage;

/**
 * Generated from IDL exception "IllegalRecipe".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalRecipe
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalRecipe()
	{
		super(org.omg.CosTrading.ProxyPackage.IllegalRecipeHelper.id());
	}

	public java.lang.String recipe;
	public IllegalRecipe(java.lang.String _reason,java.lang.String recipe)
	{
		super(_reason);
		this.recipe = recipe;
	}
	public IllegalRecipe(java.lang.String recipe)
	{
		super(org.omg.CosTrading.ProxyPackage.IllegalRecipeHelper.id());
		this.recipe = recipe;
	}
}
