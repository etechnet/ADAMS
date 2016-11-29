/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

#ifndef SIMPLEVECTOR_H
#define SIMPLEVECTOR_H


/**Questa classe viene utilizzata come base per avere una
lista veloce a singolo link.
  *@author Piergiorgio Betti
  */

template <class T> class SimpleVector {
private:
	class _sv_node
	{
	public:
		_sv_node(void * ptr) : _data(0), _next(0) { _data = ptr; }
		~_sv_node() {}

		inline void * data() { return _data; }
		inline _sv_node * next() { return _next; }
		inline void setNext(_sv_node * ptr) { _next = ptr; }
	private:
		void * _data;
		_sv_node * _next;
	};

	_sv_node * headptr;
	_sv_node * tailptr;
	_sv_node * currentptr;
	long nodecount;

public:
	SimpleVector() : headptr(0), tailptr(0), nodecount(0), currentptr(0) {}
	~SimpleVector()
	{
		clear();
	}

	void clear()
        {
                if (headptr == 0) return;
                do {
                        currentptr = headptr->next();
                        delete (T *)headptr->data();
                        delete headptr;
                } while ((headptr = currentptr) != 0);
		headptr = 0;
		tailptr = 0;
		currentptr = 0;
		nodecount = 0;
        }


	inline long count() { return nodecount; }
	inline T * current() { return (T *)((currentptr) ? currentptr->data() : 0); }
	inline T * toHead() { currentptr = headptr; return current(); }
	inline T * toTail() { currentptr = tailptr; return current(); }
	inline T * next() { if (currentptr) currentptr = currentptr->next(); return current(); }

	inline void add(T * item)
	{
		if (tailptr) {
			tailptr->setNext(new _sv_node((void *)item));
			tailptr = tailptr->next();
		}
		else {
			headptr = tailptr = new _sv_node((void *)item);
		}
		++nodecount;
	}
};

#endif
