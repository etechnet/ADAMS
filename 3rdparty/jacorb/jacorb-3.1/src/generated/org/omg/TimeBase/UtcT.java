package org.omg.TimeBase;

/**
 * Generated from IDL struct "UtcT".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class UtcT
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public UtcT(){}
	public long time;
	public int inacclo;
	public short inacchi;
	public short tdf;
	public UtcT(long time, int inacclo, short inacchi, short tdf)
	{
		this.time = time;
		this.inacclo = inacclo;
		this.inacchi = inacchi;
		this.tdf = tdf;
	}
}
