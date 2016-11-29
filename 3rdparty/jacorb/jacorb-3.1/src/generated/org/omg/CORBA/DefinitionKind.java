package org.omg.CORBA;
/**
 * Generated from IDL enum "DefinitionKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class DefinitionKind
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _dk_none = 0;
	public static final DefinitionKind dk_none = new DefinitionKind(_dk_none);
	public static final int _dk_all = 1;
	public static final DefinitionKind dk_all = new DefinitionKind(_dk_all);
	public static final int _dk_Attribute = 2;
	public static final DefinitionKind dk_Attribute = new DefinitionKind(_dk_Attribute);
	public static final int _dk_Constant = 3;
	public static final DefinitionKind dk_Constant = new DefinitionKind(_dk_Constant);
	public static final int _dk_Exception = 4;
	public static final DefinitionKind dk_Exception = new DefinitionKind(_dk_Exception);
	public static final int _dk_Interface = 5;
	public static final DefinitionKind dk_Interface = new DefinitionKind(_dk_Interface);
	public static final int _dk_Module = 6;
	public static final DefinitionKind dk_Module = new DefinitionKind(_dk_Module);
	public static final int _dk_Operation = 7;
	public static final DefinitionKind dk_Operation = new DefinitionKind(_dk_Operation);
	public static final int _dk_Typedef = 8;
	public static final DefinitionKind dk_Typedef = new DefinitionKind(_dk_Typedef);
	public static final int _dk_Alias = 9;
	public static final DefinitionKind dk_Alias = new DefinitionKind(_dk_Alias);
	public static final int _dk_Struct = 10;
	public static final DefinitionKind dk_Struct = new DefinitionKind(_dk_Struct);
	public static final int _dk_Union = 11;
	public static final DefinitionKind dk_Union = new DefinitionKind(_dk_Union);
	public static final int _dk_Enum = 12;
	public static final DefinitionKind dk_Enum = new DefinitionKind(_dk_Enum);
	public static final int _dk_Primitive = 13;
	public static final DefinitionKind dk_Primitive = new DefinitionKind(_dk_Primitive);
	public static final int _dk_String = 14;
	public static final DefinitionKind dk_String = new DefinitionKind(_dk_String);
	public static final int _dk_Sequence = 15;
	public static final DefinitionKind dk_Sequence = new DefinitionKind(_dk_Sequence);
	public static final int _dk_Array = 16;
	public static final DefinitionKind dk_Array = new DefinitionKind(_dk_Array);
	public static final int _dk_Repository = 17;
	public static final DefinitionKind dk_Repository = new DefinitionKind(_dk_Repository);
	public static final int _dk_Wstring = 18;
	public static final DefinitionKind dk_Wstring = new DefinitionKind(_dk_Wstring);
	public static final int _dk_Fixed = 19;
	public static final DefinitionKind dk_Fixed = new DefinitionKind(_dk_Fixed);
	public static final int _dk_Value = 20;
	public static final DefinitionKind dk_Value = new DefinitionKind(_dk_Value);
	public static final int _dk_ValueBox = 21;
	public static final DefinitionKind dk_ValueBox = new DefinitionKind(_dk_ValueBox);
	public static final int _dk_ValueMember = 22;
	public static final DefinitionKind dk_ValueMember = new DefinitionKind(_dk_ValueMember);
	public static final int _dk_Native = 23;
	public static final DefinitionKind dk_Native = new DefinitionKind(_dk_Native);
	public static final int _dk_AbstractInterface = 24;
	public static final DefinitionKind dk_AbstractInterface = new DefinitionKind(_dk_AbstractInterface);
	public static final int _dk_LocalInterface = 25;
	public static final DefinitionKind dk_LocalInterface = new DefinitionKind(_dk_LocalInterface);
	public static final int _dk_Component = 26;
	public static final DefinitionKind dk_Component = new DefinitionKind(_dk_Component);
	public static final int _dk_Home = 27;
	public static final DefinitionKind dk_Home = new DefinitionKind(_dk_Home);
	public static final int _dk_Factory = 28;
	public static final DefinitionKind dk_Factory = new DefinitionKind(_dk_Factory);
	public static final int _dk_Finder = 29;
	public static final DefinitionKind dk_Finder = new DefinitionKind(_dk_Finder);
	public static final int _dk_Emits = 30;
	public static final DefinitionKind dk_Emits = new DefinitionKind(_dk_Emits);
	public static final int _dk_Publishes = 31;
	public static final DefinitionKind dk_Publishes = new DefinitionKind(_dk_Publishes);
	public static final int _dk_Consumes = 32;
	public static final DefinitionKind dk_Consumes = new DefinitionKind(_dk_Consumes);
	public static final int _dk_Provides = 33;
	public static final DefinitionKind dk_Provides = new DefinitionKind(_dk_Provides);
	public static final int _dk_Uses = 34;
	public static final DefinitionKind dk_Uses = new DefinitionKind(_dk_Uses);
	public static final int _dk_Event = 35;
	public static final DefinitionKind dk_Event = new DefinitionKind(_dk_Event);
	public int value()
	{
		return value;
	}
	public static DefinitionKind from_int(int value)
	{
		switch (value) {
			case _dk_none: return dk_none;
			case _dk_all: return dk_all;
			case _dk_Attribute: return dk_Attribute;
			case _dk_Constant: return dk_Constant;
			case _dk_Exception: return dk_Exception;
			case _dk_Interface: return dk_Interface;
			case _dk_Module: return dk_Module;
			case _dk_Operation: return dk_Operation;
			case _dk_Typedef: return dk_Typedef;
			case _dk_Alias: return dk_Alias;
			case _dk_Struct: return dk_Struct;
			case _dk_Union: return dk_Union;
			case _dk_Enum: return dk_Enum;
			case _dk_Primitive: return dk_Primitive;
			case _dk_String: return dk_String;
			case _dk_Sequence: return dk_Sequence;
			case _dk_Array: return dk_Array;
			case _dk_Repository: return dk_Repository;
			case _dk_Wstring: return dk_Wstring;
			case _dk_Fixed: return dk_Fixed;
			case _dk_Value: return dk_Value;
			case _dk_ValueBox: return dk_ValueBox;
			case _dk_ValueMember: return dk_ValueMember;
			case _dk_Native: return dk_Native;
			case _dk_AbstractInterface: return dk_AbstractInterface;
			case _dk_LocalInterface: return dk_LocalInterface;
			case _dk_Component: return dk_Component;
			case _dk_Home: return dk_Home;
			case _dk_Factory: return dk_Factory;
			case _dk_Finder: return dk_Finder;
			case _dk_Emits: return dk_Emits;
			case _dk_Publishes: return dk_Publishes;
			case _dk_Consumes: return dk_Consumes;
			case _dk_Provides: return dk_Provides;
			case _dk_Uses: return dk_Uses;
			case _dk_Event: return dk_Event;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _dk_none: return "dk_none";
			case _dk_all: return "dk_all";
			case _dk_Attribute: return "dk_Attribute";
			case _dk_Constant: return "dk_Constant";
			case _dk_Exception: return "dk_Exception";
			case _dk_Interface: return "dk_Interface";
			case _dk_Module: return "dk_Module";
			case _dk_Operation: return "dk_Operation";
			case _dk_Typedef: return "dk_Typedef";
			case _dk_Alias: return "dk_Alias";
			case _dk_Struct: return "dk_Struct";
			case _dk_Union: return "dk_Union";
			case _dk_Enum: return "dk_Enum";
			case _dk_Primitive: return "dk_Primitive";
			case _dk_String: return "dk_String";
			case _dk_Sequence: return "dk_Sequence";
			case _dk_Array: return "dk_Array";
			case _dk_Repository: return "dk_Repository";
			case _dk_Wstring: return "dk_Wstring";
			case _dk_Fixed: return "dk_Fixed";
			case _dk_Value: return "dk_Value";
			case _dk_ValueBox: return "dk_ValueBox";
			case _dk_ValueMember: return "dk_ValueMember";
			case _dk_Native: return "dk_Native";
			case _dk_AbstractInterface: return "dk_AbstractInterface";
			case _dk_LocalInterface: return "dk_LocalInterface";
			case _dk_Component: return "dk_Component";
			case _dk_Home: return "dk_Home";
			case _dk_Factory: return "dk_Factory";
			case _dk_Finder: return "dk_Finder";
			case _dk_Emits: return "dk_Emits";
			case _dk_Publishes: return "dk_Publishes";
			case _dk_Consumes: return "dk_Consumes";
			case _dk_Provides: return "dk_Provides";
			case _dk_Uses: return "dk_Uses";
			case _dk_Event: return "dk_Event";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected DefinitionKind(int i)
	{
		value = i;
	}
	/**
	 * Designate replacement object when deserialized from stream. See
	 * http://www.omg.org/docs/ptc/02-01-03.htm#Issue4271
	 *
	 * @throws java.io.ObjectStreamException
	 */
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
