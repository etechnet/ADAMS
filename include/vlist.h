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

/***************************************************************************
                          vlist.h  -  description
                             -------------------
    begin                : Fri Nov 3 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#if !defined VLIST_H
#define VLIST_H

class List
{
public:
    List ();
    ~List ();
    void Add (int id);
private:
	class Link
	{
	public:
		Link (Link * pNext, int id)
			: _pNext (pNext), _id (id) {}

		Link *  Next () const { return _pNext; }
		int     Id () const { return _id; }
	private:
		Link *  _pNext;
		int     _id;
	};

public:
	class Seq
	{
	public:
		Seq (List const & list)
			: _pLink (list.GetHead ()) {}
		bool AtEnd () const { return _pLink == 0; }
		void Advance () { _pLink = _pLink->Next (); }
		int GetId () const { return _pLink->Id (); }
	private:

		Link const * _pLink; // current link
	};

// 	friend Seq;
private:
    Link const * GetHead () const { return _pHead; }

    Link * _pHead;
};

#endif
