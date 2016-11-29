package org.omg.CosCollection;


/**
 * Generated from IDL interface "SortedRelation".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class SortedRelationPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosCollection.SortedRelationOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "remove_all_elements_with_key", Integer.valueOf(0));
		m_opsHash.put ( "locate_next_element", Integer.valueOf(1));
		m_opsHash.put ( "contains_element", Integer.valueOf(2));
		m_opsHash.put ( "add_or_replace_element_with_key_set_iterator", Integer.valueOf(3));
		m_opsHash.put ( "contains_all_keys_from", Integer.valueOf(4));
		m_opsHash.put ( "locate_or_add_element_set_iterator", Integer.valueOf(5));
		m_opsHash.put ( "number_of_different_elements", Integer.valueOf(6));
		m_opsHash.put ( "locate_next_element_with_key", Integer.valueOf(7));
		m_opsHash.put ( "_get_element_type", Integer.valueOf(8));
		m_opsHash.put ( "create_iterator", Integer.valueOf(9));
		m_opsHash.put ( "retrieve_element_at_position", Integer.valueOf(10));
		m_opsHash.put ( "locate_element_with_key", Integer.valueOf(11));
		m_opsHash.put ( "number_of_elements_with_key", Integer.valueOf(12));
		m_opsHash.put ( "is_empty", Integer.valueOf(13));
		m_opsHash.put ( "remove_element_at_position", Integer.valueOf(14));
		m_opsHash.put ( "locate_or_add_element_with_key_set_iterator", Integer.valueOf(15));
		m_opsHash.put ( "locate_or_add_element", Integer.valueOf(16));
		m_opsHash.put ( "remove_first_element", Integer.valueOf(17));
		m_opsHash.put ( "remove_all_occurrences", Integer.valueOf(18));
		m_opsHash.put ( "retrieve_last_element", Integer.valueOf(19));
		m_opsHash.put ( "locate_or_add_element_with_key", Integer.valueOf(20));
		m_opsHash.put ( "retrieve_first_element", Integer.valueOf(21));
		m_opsHash.put ( "remove_element", Integer.valueOf(22));
		m_opsHash.put ( "locate_element", Integer.valueOf(23));
		m_opsHash.put ( "add_element", Integer.valueOf(24));
		m_opsHash.put ( "replace_element_at", Integer.valueOf(25));
		m_opsHash.put ( "add_or_replace_element_with_key", Integer.valueOf(26));
		m_opsHash.put ( "remove_element_with_key", Integer.valueOf(27));
		m_opsHash.put ( "retrieve_element_at", Integer.valueOf(28));
		m_opsHash.put ( "_get_key_type", Integer.valueOf(29));
		m_opsHash.put ( "destroy", Integer.valueOf(30));
		m_opsHash.put ( "replace_element_with_key_set_iterator", Integer.valueOf(31));
		m_opsHash.put ( "keys", Integer.valueOf(32));
		m_opsHash.put ( "contains_all_from", Integer.valueOf(33));
		m_opsHash.put ( "number_of_elements", Integer.valueOf(34));
		m_opsHash.put ( "add_all_from", Integer.valueOf(35));
		m_opsHash.put ( "create_ordered_iterator", Integer.valueOf(36));
		m_opsHash.put ( "locate_next_element_with_different_key", Integer.valueOf(37));
		m_opsHash.put ( "remove_element_at", Integer.valueOf(38));
		m_opsHash.put ( "number_of_occurrences", Integer.valueOf(39));
		m_opsHash.put ( "number_of_different_keys", Integer.valueOf(40));
		m_opsHash.put ( "remove_all", Integer.valueOf(41));
		m_opsHash.put ( "replace_element_with_key", Integer.valueOf(42));
		m_opsHash.put ( "compare", Integer.valueOf(43));
		m_opsHash.put ( "add_element_set_iterator", Integer.valueOf(44));
		m_opsHash.put ( "contains_element_with_key", Integer.valueOf(45));
		m_opsHash.put ( "all_elements_do", Integer.valueOf(46));
		m_opsHash.put ( "remove_last_element", Integer.valueOf(47));
		m_opsHash.put ( "retrieve_element_with_key", Integer.valueOf(48));
		m_opsHash.put ( "locate_next_different_element", Integer.valueOf(49));
		m_opsHash.put ( "key", Integer.valueOf(50));
	}
	private String[] ids = {"IDL:omg.org/CosCollection/SortedRelation:1.0","IDL:omg.org/CosCollection/KeyCollection:1.0","IDL:omg.org/CosCollection/Collection:1.0","IDL:omg.org/CosCollection/EqualityKeySortedCollection:1.0","IDL:omg.org/CosCollection/OrderedCollection:1.0","IDL:omg.org/CosCollection/EqualityCollection:1.0","IDL:omg.org/CosCollection/SortedCollection:1.0"};
	public org.omg.CosCollection.SortedRelation _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.SortedRelation __r = org.omg.CosCollection.SortedRelationHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.SortedRelation _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.SortedRelation __r = org.omg.CosCollection.SortedRelationHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // remove_all_elements_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_ulong(remove_all_elements_with_key(_arg0));
			}
			catch(org.omg.CosCollection.KeyInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.KeyInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // locate_next_element
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(locate_next_element(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 2: // contains_element
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(contains_element(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 3: // add_or_replace_element_with_key_set_iterator
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(add_or_replace_element_with_key_set_iterator(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 4: // contains_all_keys_from
			{
			try
			{
				org.omg.CosCollection.KeyCollection _arg0=org.omg.CosCollection.KeyCollectionHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(contains_all_keys_from(_arg0));
			}
			catch(org.omg.CosCollection.KeyInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.KeyInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 5: // locate_or_add_element_set_iterator
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(locate_or_add_element_set_iterator(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 6: // number_of_different_elements
			{
				_out = handler.createReply();
				_out.write_ulong(number_of_different_elements());
				break;
			}
			case 7: // locate_next_element_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(locate_next_element_with_key(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.KeyInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.KeyInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 8: // _get_element_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(element_type());
				break;
			}
			case 9: // create_iterator
			{
				boolean _arg0=_input.read_boolean();
				_out = handler.createReply();
				org.omg.CosCollection.IteratorHelper.write(_out,create_iterator(_arg0));
				break;
			}
			case 10: // retrieve_element_at_position
			{
			try
			{
				int _arg0=_input.read_ulong();
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_element_at_position(_arg0,_arg1));
				_out.write_any(_arg1.value);
			}
			catch(org.omg.CosCollection.PositionInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.PositionInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 11: // locate_element_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(locate_element_with_key(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.KeyInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.KeyInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 12: // number_of_elements_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_ulong(number_of_elements_with_key(_arg0));
			}
			catch(org.omg.CosCollection.KeyInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.KeyInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 13: // is_empty
			{
				_out = handler.createReply();
				_out.write_boolean(is_empty());
				break;
			}
			case 14: // remove_element_at_position
			{
			try
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				remove_element_at_position(_arg0);
			}
			catch(org.omg.CosCollection.PositionInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.PositionInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 15: // locate_or_add_element_with_key_set_iterator
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(locate_or_add_element_with_key_set_iterator(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 16: // locate_or_add_element
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(locate_or_add_element(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 17: // remove_first_element
			{
			try
			{
				_out = handler.createReply();
				remove_first_element();
			}
			catch(org.omg.CosCollection.EmptyCollection _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.EmptyCollectionHelper.write(_out, _ex0);
			}
				break;
			}
			case 18: // remove_all_occurrences
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_ulong(remove_all_occurrences(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 19: // retrieve_last_element
			{
			try
			{
				org.omg.CORBA.AnyHolder _arg0= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_last_element(_arg0));
				_out.write_any(_arg0.value);
			}
			catch(org.omg.CosCollection.EmptyCollection _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.EmptyCollectionHelper.write(_out, _ex0);
			}
				break;
			}
			case 20: // locate_or_add_element_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(locate_or_add_element_with_key(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 21: // retrieve_first_element
			{
			try
			{
				org.omg.CORBA.AnyHolder _arg0= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_first_element(_arg0));
				_out.write_any(_arg0.value);
			}
			catch(org.omg.CosCollection.EmptyCollection _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.EmptyCollectionHelper.write(_out, _ex0);
			}
				break;
			}
			case 22: // remove_element
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(remove_element(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 23: // locate_element
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(locate_element(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 24: // add_element
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(add_element(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 25: // replace_element_at
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				org.omg.CORBA.Any _arg1=_input.read_any();
				_out = handler.createReply();
				replace_element_at(_arg0,_arg1);
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
			catch(org.omg.CosCollection.IteratorInBetween _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInBetweenHelper.write(_out, _ex2);
			}
				break;
			}
			case 26: // add_or_replace_element_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(add_or_replace_element_with_key(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 27: // remove_element_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(remove_element_with_key(_arg0));
			}
			catch(org.omg.CosCollection.KeyInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.KeyInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 28: // retrieve_element_at
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_element_at(_arg0,_arg1));
				_out.write_any(_arg1.value);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInBetween _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInBetweenHelper.write(_out, _ex1);
			}
				break;
			}
			case 29: // _get_key_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(key_type());
				break;
			}
			case 30: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 31: // replace_element_with_key_set_iterator
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(replace_element_with_key_set_iterator(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 32: // keys
			{
			try
			{
				org.omg.CORBA.Any[] _arg0=org.omg.CosCollection.AnySequenceHelper.read(_input);
				org.omg.CosCollection.AnySequenceHolder _arg1= new org.omg.CosCollection.AnySequenceHolder();
				_out = handler.createReply();
				keys(_arg0,_arg1);
				org.omg.CosCollection.AnySequenceHelper.write(_out,_arg1.value);
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 33: // contains_all_from
			{
			try
			{
				org.omg.CosCollection.Collection _arg0=org.omg.CosCollection.CollectionHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(contains_all_from(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 34: // number_of_elements
			{
				_out = handler.createReply();
				_out.write_ulong(number_of_elements());
				break;
			}
			case 35: // add_all_from
			{
			try
			{
				org.omg.CosCollection.Collection _arg0=org.omg.CosCollection.CollectionHelper.read(_input);
				_out = handler.createReply();
				add_all_from(_arg0);
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 36: // create_ordered_iterator
			{
				boolean _arg0=_input.read_boolean();
				boolean _arg1=_input.read_boolean();
				_out = handler.createReply();
				org.omg.CosCollection.OrderedIteratorHelper.write(_out,create_ordered_iterator(_arg0,_arg1));
				break;
			}
			case 37: // locate_next_element_with_different_key
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(locate_next_element_with_different_key(_arg0));
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInBetween _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInBetweenHelper.write(_out, _ex1);
			}
				break;
			}
			case 38: // remove_element_at
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				remove_element_at(_arg0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInBetween _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInBetweenHelper.write(_out, _ex1);
			}
				break;
			}
			case 39: // number_of_occurrences
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_ulong(number_of_occurrences(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 40: // number_of_different_keys
			{
				_out = handler.createReply();
				_out.write_ulong(number_of_different_keys());
				break;
			}
			case 41: // remove_all
			{
				_out = handler.createReply();
				_out.write_ulong(remove_all());
				break;
			}
			case 42: // replace_element_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(replace_element_with_key(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 43: // compare
			{
				org.omg.CosCollection.SortedRelation _arg0=org.omg.CosCollection.SortedRelationHelper.read(_input);
				org.omg.CosCollection.Comparator _arg1=org.omg.CosCollection.ComparatorHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(compare(_arg0,_arg1));
				break;
			}
			case 44: // add_element_set_iterator
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CosCollection.Iterator _arg1=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(add_element_set_iterator(_arg0,_arg1));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex1);
			}
				break;
			}
			case 45: // contains_element_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(contains_element_with_key(_arg0));
			}
			catch(org.omg.CosCollection.KeyInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.KeyInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 46: // all_elements_do
			{
				org.omg.CosCollection.Command _arg0=org.omg.CosCollection.CommandHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(all_elements_do(_arg0));
				break;
			}
			case 47: // remove_last_element
			{
			try
			{
				_out = handler.createReply();
				remove_last_element();
			}
			catch(org.omg.CosCollection.EmptyCollection _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.EmptyCollectionHelper.write(_out, _ex0);
			}
				break;
			}
			case 48: // retrieve_element_with_key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_element_with_key(_arg0,_arg1));
				_out.write_any(_arg1.value);
			}
			catch(org.omg.CosCollection.KeyInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.KeyInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 49: // locate_next_different_element
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(locate_next_different_element(_arg0));
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
			catch(org.omg.CosCollection.IteratorInBetween _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInBetweenHelper.write(_out, _ex1);
			}
				break;
			}
			case 50: // key
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				key(_arg0,_arg1);
				_out.write_any(_arg1.value);
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
