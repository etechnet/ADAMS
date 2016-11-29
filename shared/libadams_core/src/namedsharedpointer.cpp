/*
 * #
 * #                $$$$$$$$                   $$
 * #                   $$                      $$
 * #  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
 * # $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
 * # $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
 * # $$                $$  $$        $$        $$    $$
 * #  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
 * #
 * #  MODULE DESCRIPTION:
 * #  <enter module description here>
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #--
 * #
 */

#include <namedsharedpointer.h>

#include <Qt/qhash.h>

static QHash<QString, void *> ptr_storage;


void named_shared_pointer_container::store ( const QString & ptr_name, void * ptr )
{
	if ( ptr_storage.contains ( ptr_name ) )
		ptr_storage.remove ( ptr_name );

	ptr_storage.insert ( ptr_name, ptr );
}

void * named_shared_pointer_container::retrieve ( const QString & ptr_name )
{
	if ( ptr_storage.contains ( ptr_name ) )
		return ptr_storage.value ( ptr_name );
	else
		return 0;
}

void named_shared_pointer_container::clear()
{
	ptr_storage.clear();
}
