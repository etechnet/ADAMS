package org.omg.CosCollection;


/**
 * Generated from IDL interface "SequentialIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class SequentialIteratorPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosCollection.SequentialIteratorOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "clone", Integer.valueOf(0));
		m_opsHash.put ( "add_n_elements_set_iterator", Integer.valueOf(1));
		m_opsHash.put ( "add_n_elements_as_next_set_iterator", Integer.valueOf(2));
		m_opsHash.put ( "replace_element_set_to_previous", Integer.valueOf(3));
		m_opsHash.put ( "is_valid", Integer.valueOf(4));
		m_opsHash.put ( "add_element_as_previous_set_iterator", Integer.valueOf(5));
		m_opsHash.put ( "remove_next_n_elements", Integer.valueOf(6));
		m_opsHash.put ( "set_to_next_nth_element", Integer.valueOf(7));
		m_opsHash.put ( "replace_element", Integer.valueOf(8));
		m_opsHash.put ( "add_n_elements_as_previous_set_iterator", Integer.valueOf(9));
		m_opsHash.put ( "is_for_same", Integer.valueOf(10));
		m_opsHash.put ( "is_reverse", Integer.valueOf(11));
		m_opsHash.put ( "assign", Integer.valueOf(12));
		m_opsHash.put ( "position", Integer.valueOf(13));
		m_opsHash.put ( "retrieve_element", Integer.valueOf(14));
		m_opsHash.put ( "set_to_first_element", Integer.valueOf(15));
		m_opsHash.put ( "retrieve_next_n_elements", Integer.valueOf(16));
		m_opsHash.put ( "replace_previous_n_elements", Integer.valueOf(17));
		m_opsHash.put ( "set_to_previous_element", Integer.valueOf(18));
		m_opsHash.put ( "is_last", Integer.valueOf(19));
		m_opsHash.put ( "set_to_next_element", Integer.valueOf(20));
		m_opsHash.put ( "set_to_nth_previous_element", Integer.valueOf(21));
		m_opsHash.put ( "destroy", Integer.valueOf(22));
		m_opsHash.put ( "set_to_last_element", Integer.valueOf(23));
		m_opsHash.put ( "add_element_as_next_set_iterator", Integer.valueOf(24));
		m_opsHash.put ( "not_equal_retrieve_element_set_to_next", Integer.valueOf(25));
		m_opsHash.put ( "not_equal_replace_element_set_to_previous", Integer.valueOf(26));
		m_opsHash.put ( "remove_element_set_to_previous", Integer.valueOf(27));
		m_opsHash.put ( "remove_element", Integer.valueOf(28));
		m_opsHash.put ( "not_equal_remove_element_set_to_next", Integer.valueOf(29));
		m_opsHash.put ( "is_equal", Integer.valueOf(30));
		m_opsHash.put ( "retrieve_element_set_to_previous", Integer.valueOf(31));
		m_opsHash.put ( "invalidate", Integer.valueOf(32));
		m_opsHash.put ( "remove_element_set_to_next", Integer.valueOf(33));
		m_opsHash.put ( "retrieve_previous_n_elements", Integer.valueOf(34));
		m_opsHash.put ( "remove_previous_n_elements", Integer.valueOf(35));
		m_opsHash.put ( "not_equal_remove_element_set_to_previous", Integer.valueOf(36));
		m_opsHash.put ( "replace_element_set_to_next", Integer.valueOf(37));
		m_opsHash.put ( "not_equal_replace_element_set_to_next", Integer.valueOf(38));
		m_opsHash.put ( "is_for", Integer.valueOf(39));
		m_opsHash.put ( "is_first", Integer.valueOf(40));
		m_opsHash.put ( "add_element_set_iterator", Integer.valueOf(41));
		m_opsHash.put ( "replace_next_n_elements", Integer.valueOf(42));
		m_opsHash.put ( "not_equal_retrieve_element_set_to_previous", Integer.valueOf(43));
		m_opsHash.put ( "retrieve_element_set_to_next", Integer.valueOf(44));
		m_opsHash.put ( "is_const", Integer.valueOf(45));
		m_opsHash.put ( "set_to_position", Integer.valueOf(46));
		m_opsHash.put ( "is_in_between", Integer.valueOf(47));
	}
	private String[] ids = {"IDL:omg.org/CosCollection/SequentialIterator:1.0","IDL:omg.org/CosCollection/Iterator:1.0","IDL:omg.org/CosCollection/OrderedIterator:1.0"};
	public org.omg.CosCollection.SequentialIterator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.SequentialIterator __r = org.omg.CosCollection.SequentialIteratorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.SequentialIterator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.SequentialIterator __r = org.omg.CosCollection.SequentialIteratorHelper.narrow(__o);
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
			case 0: // clone
			{
				_out = handler.createReply();
				org.omg.CosCollection.IteratorHelper.write(_out,_clone());
				break;
			}
			case 1: // add_n_elements_set_iterator
			{
			try
			{
				org.omg.CORBA.Any[] _arg0=org.omg.CosCollection.AnySequenceHelper.read(_input);
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				_out.write_boolean(add_n_elements_set_iterator(_arg0,_arg1));
				_out.write_ulong(_arg1.value);
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // add_n_elements_as_next_set_iterator
			{
			try
			{
				org.omg.CORBA.Any[] _arg0=org.omg.CosCollection.AnySequenceHelper.read(_input);
				_out = handler.createReply();
				add_n_elements_as_next_set_iterator(_arg0);
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
			case 3: // replace_element_set_to_previous
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(replace_element_set_to_previous(_arg0));
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
			case 4: // is_valid
			{
				_out = handler.createReply();
				_out.write_boolean(is_valid());
				break;
			}
			case 5: // add_element_as_previous_set_iterator
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(add_element_as_previous_set_iterator(_arg0));
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
			case 6: // remove_next_n_elements
			{
			try
			{
				int _arg0=_input.read_ulong();
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				_out.write_boolean(remove_next_n_elements(_arg0,_arg1));
				_out.write_ulong(_arg1.value);
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
			case 7: // set_to_next_nth_element
			{
			try
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_boolean(set_to_next_nth_element(_arg0));
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 8: // replace_element
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				replace_element(_arg0);
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
			case 9: // add_n_elements_as_previous_set_iterator
			{
			try
			{
				org.omg.CORBA.Any[] _arg0=org.omg.CosCollection.AnySequenceHelper.read(_input);
				_out = handler.createReply();
				add_n_elements_as_previous_set_iterator(_arg0);
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
			case 10: // is_for_same
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(is_for_same(_arg0));
				break;
			}
			case 11: // is_reverse
			{
				_out = handler.createReply();
				_out.write_boolean(is_reverse());
				break;
			}
			case 12: // assign
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				assign(_arg0);
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 13: // position
			{
			try
			{
				_out = handler.createReply();
				_out.write_ulong(position());
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 14: // retrieve_element
			{
			try
			{
				org.omg.CORBA.AnyHolder _arg0= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_element(_arg0));
				_out.write_any(_arg0.value);
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
			case 15: // set_to_first_element
			{
				_out = handler.createReply();
				_out.write_boolean(set_to_first_element());
				break;
			}
			case 16: // retrieve_next_n_elements
			{
			try
			{
				int _arg0=_input.read_ulong();
				org.omg.CosCollection.AnySequenceHolder _arg1= new org.omg.CosCollection.AnySequenceHolder();
				org.omg.CORBA.BooleanHolder _arg2= new org.omg.CORBA.BooleanHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_next_n_elements(_arg0,_arg1,_arg2));
				org.omg.CosCollection.AnySequenceHelper.write(_out,_arg1.value);
				_out.write_boolean(_arg2.value);
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
			case 17: // replace_previous_n_elements
			{
			try
			{
				org.omg.CORBA.Any[] _arg0=org.omg.CosCollection.AnySequenceHelper.read(_input);
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				_out.write_boolean(replace_previous_n_elements(_arg0,_arg1));
				_out.write_ulong(_arg1.value);
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
			case 18: // set_to_previous_element
			{
			try
			{
				_out = handler.createReply();
				_out.write_boolean(set_to_previous_element());
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 19: // is_last
			{
				_out = handler.createReply();
				_out.write_boolean(is_last());
				break;
			}
			case 20: // set_to_next_element
			{
			try
			{
				_out = handler.createReply();
				_out.write_boolean(set_to_next_element());
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 21: // set_to_nth_previous_element
			{
			try
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_boolean(set_to_nth_previous_element(_arg0));
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 22: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 23: // set_to_last_element
			{
				_out = handler.createReply();
				_out.write_boolean(set_to_last_element());
				break;
			}
			case 24: // add_element_as_next_set_iterator
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(add_element_as_next_set_iterator(_arg0));
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
			case 25: // not_equal_retrieve_element_set_to_next
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(not_equal_retrieve_element_set_to_next(_arg0,_arg1));
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
			case 26: // not_equal_replace_element_set_to_previous
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				org.omg.CORBA.Any _arg1=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(not_equal_replace_element_set_to_previous(_arg0,_arg1));
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
			case 27: // remove_element_set_to_previous
			{
			try
			{
				_out = handler.createReply();
				_out.write_boolean(remove_element_set_to_previous());
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
			case 28: // remove_element
			{
			try
			{
				_out = handler.createReply();
				remove_element();
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
			case 29: // not_equal_remove_element_set_to_next
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(not_equal_remove_element_set_to_next(_arg0));
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
			case 30: // is_equal
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(is_equal(_arg0));
			}
			catch(org.omg.CosCollection.IteratorInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.IteratorInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 31: // retrieve_element_set_to_previous
			{
			try
			{
				org.omg.CORBA.AnyHolder _arg0= new org.omg.CORBA.AnyHolder();
				org.omg.CORBA.BooleanHolder _arg1= new org.omg.CORBA.BooleanHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_element_set_to_previous(_arg0,_arg1));
				_out.write_any(_arg0.value);
				_out.write_boolean(_arg1.value);
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
			case 32: // invalidate
			{
				_out = handler.createReply();
				invalidate();
				break;
			}
			case 33: // remove_element_set_to_next
			{
			try
			{
				_out = handler.createReply();
				_out.write_boolean(remove_element_set_to_next());
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
			case 34: // retrieve_previous_n_elements
			{
			try
			{
				int _arg0=_input.read_ulong();
				org.omg.CosCollection.AnySequenceHolder _arg1= new org.omg.CosCollection.AnySequenceHolder();
				org.omg.CORBA.BooleanHolder _arg2= new org.omg.CORBA.BooleanHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_previous_n_elements(_arg0,_arg1,_arg2));
				org.omg.CosCollection.AnySequenceHelper.write(_out,_arg1.value);
				_out.write_boolean(_arg2.value);
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
			case 35: // remove_previous_n_elements
			{
			try
			{
				int _arg0=_input.read_ulong();
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				_out.write_boolean(remove_previous_n_elements(_arg0,_arg1));
				_out.write_ulong(_arg1.value);
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
			case 36: // not_equal_remove_element_set_to_previous
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(not_equal_remove_element_set_to_previous(_arg0));
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
			case 37: // replace_element_set_to_next
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(replace_element_set_to_next(_arg0));
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
			case 38: // not_equal_replace_element_set_to_next
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				org.omg.CORBA.Any _arg1=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(not_equal_replace_element_set_to_next(_arg0,_arg1));
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
			case 39: // is_for
			{
				org.omg.CosCollection.Collection _arg0=org.omg.CosCollection.CollectionHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(is_for(_arg0));
				break;
			}
			case 40: // is_first
			{
				_out = handler.createReply();
				_out.write_boolean(is_first());
				break;
			}
			case 41: // add_element_set_iterator
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(add_element_set_iterator(_arg0));
			}
			catch(org.omg.CosCollection.ElementInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.ElementInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 42: // replace_next_n_elements
			{
			try
			{
				org.omg.CORBA.Any[] _arg0=org.omg.CosCollection.AnySequenceHelper.read(_input);
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				_out.write_boolean(replace_next_n_elements(_arg0,_arg1));
				_out.write_ulong(_arg1.value);
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
			case 43: // not_equal_retrieve_element_set_to_previous
			{
			try
			{
				org.omg.CosCollection.Iterator _arg0=org.omg.CosCollection.IteratorHelper.read(_input);
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(not_equal_retrieve_element_set_to_previous(_arg0,_arg1));
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
			case 44: // retrieve_element_set_to_next
			{
			try
			{
				org.omg.CORBA.AnyHolder _arg0= new org.omg.CORBA.AnyHolder();
				org.omg.CORBA.BooleanHolder _arg1= new org.omg.CORBA.BooleanHolder();
				_out = handler.createReply();
				_out.write_boolean(retrieve_element_set_to_next(_arg0,_arg1));
				_out.write_any(_arg0.value);
				_out.write_boolean(_arg1.value);
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
			case 45: // is_const
			{
				_out = handler.createReply();
				_out.write_boolean(is_const());
				break;
			}
			case 46: // set_to_position
			{
			try
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				set_to_position(_arg0);
			}
			catch(org.omg.CosCollection.PositionInvalid _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosCollection.PositionInvalidHelper.write(_out, _ex0);
			}
				break;
			}
			case 47: // is_in_between
			{
				_out = handler.createReply();
				_out.write_boolean(is_in_between());
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
