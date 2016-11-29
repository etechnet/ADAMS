package org.omg.CSIIOP;

/**
 * Generated from IDL struct "CompoundSecMechList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class CompoundSecMechList
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public CompoundSecMechList(){}
	public boolean stateful;
	public org.omg.CSIIOP.CompoundSecMech[] mechanism_list;
	public CompoundSecMechList(boolean stateful, org.omg.CSIIOP.CompoundSecMech[] mechanism_list)
	{
		this.stateful = stateful;
		this.mechanism_list = mechanism_list;
	}
}
