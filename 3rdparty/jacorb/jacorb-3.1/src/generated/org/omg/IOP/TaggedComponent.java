package org.omg.IOP;

/**
 * Generated from IDL struct "TaggedComponent".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class TaggedComponent
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TaggedComponent(){}
	public int tag;
	public byte[] component_data;
	public TaggedComponent(int tag, byte[] component_data)
	{
		this.tag = tag;
		this.component_data = component_data;
	}
}
