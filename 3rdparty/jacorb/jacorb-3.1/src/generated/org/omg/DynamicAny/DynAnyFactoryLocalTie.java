package org.omg.DynamicAny;


/**
 * Generated from IDL interface "DynAnyFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class DynAnyFactoryLocalTie
	extends _DynAnyFactoryLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private DynAnyFactoryOperations _delegate;

	public DynAnyFactoryLocalTie(DynAnyFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public DynAnyFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(DynAnyFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public org.omg.DynamicAny.DynAny create_dyn_any_without_truncation(org.omg.CORBA.Any value) throws org.omg.DynamicAny.MustTruncate,org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode
	{
		return _delegate.create_dyn_any_without_truncation(value);
	}

	public org.omg.CORBA.Any[] create_multiple_anys(org.omg.DynamicAny.DynAny[] values)
	{
		return _delegate.create_multiple_anys(values);
	}

	public org.omg.DynamicAny.DynAny create_dyn_any(org.omg.CORBA.Any value) throws org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode
	{
		return _delegate.create_dyn_any(value);
	}

	public org.omg.DynamicAny.DynAny[] create_multiple_dyn_anys(org.omg.CORBA.Any[] values, boolean allow_truncate) throws org.omg.DynamicAny.MustTruncate,org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode
	{
		return _delegate.create_multiple_dyn_anys(values,allow_truncate);
	}

	public org.omg.DynamicAny.DynAny create_dyn_any_from_type_code(org.omg.CORBA.TypeCode type) throws org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode
	{
		return _delegate.create_dyn_any_from_type_code(type);
	}

}
