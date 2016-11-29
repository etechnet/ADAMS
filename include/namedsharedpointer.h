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

#ifndef NAMEDSHAREDPOINTER_H
#define NAMEDSHAREDPOINTER_H

#include <Qt/qstring.h>

class named_shared_pointer_container
{
public:
	static void store ( const QString & ptr_name, void * ptr );
	static void * retrieve ( const QString & ptr_name );
	static void clear();
};

template <typename T> class NamedSharedPointer
{
public:
	NamedSharedPointer() {}
	virtual ~NamedSharedPointer() {}

	static void store ( const QString & ptr_name, T * ptr ) {
		named_shared_pointer_container::store ( ptr_name, ( void * ) ptr );
	}

	static T * retrieve ( const QString & ptr_name ) {
		return ( T * ) named_shared_pointer_container::retrieve ( ptr_name );
	}

	static void clear() {
		named_shared_pointer_container::clear();
	}
};

#endif // NAMEDSHAREDPOINTER_H
