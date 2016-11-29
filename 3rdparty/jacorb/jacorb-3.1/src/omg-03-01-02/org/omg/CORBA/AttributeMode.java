/***** Copyright (c) 1999-2000 Object Management Group. Unlimited rights to
       duplicate and use this code are hereby granted provided that this
       copyright notice is included.
*****/

/***** This class is generated by an IDL compiler and is ORB-vendor specific.
       A "dummy" implementation is provided so that the "official" org.omg.*
       packages may be compiled.  In order to actually use a Java ORB,
       the ORB vendor's implementation will provide a "real"
       implementation of the class.

       In order to be conformant the class shall support the signatures
       specified here, but will have an orb-specific implementation.

       The class may support additional vendor specific functionality.
       It shall have at least the inheritance relationships specified
       here. Any additional (vendor specific) inheritance relationships may
       only be with other classes and interfaces that are guaranteed to be
       present in the JDK core.
*****/

package org.omg.CORBA;

public class AttributeMode implements org.omg.CORBA.portable.IDLEntity {

    public static final int _ATTR_NORMAL = 0;
    public static final AttributeMode ATTR_NORMAL =
        new AttributeMode(_ATTR_NORMAL);

    public static final int _ATTR_READONLY = 1;
    public static final AttributeMode ATTR_READONLY =
        new AttributeMode(_ATTR_READONLY);

    public int value() {
        throw new org.omg.CORBA.NO_IMPLEMENT();
    }

    public static AttributeMode from_int(int val)
                       /* Issue 3669 throws org.omg.CORBA.BAD_PARAM */ {
        switch (val) {
        case _ATTR_NORMAL:
            return ATTR_NORMAL;
        case _ATTR_READONLY:
            return ATTR_READONLY;
        default:
            throw new org.omg.CORBA.BAD_PARAM();
        }
    }

    protected AttributeMode(int _value) {
        throw new org.omg.CORBA.NO_IMPLEMENT();
    }

    public java.lang.Object readResolve() throws java.io.ObjectStreamException
    {
       return from_int( value() ) ;
    }
}
