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
 * #  QT core application + Corba TAO event dispatching
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #  20130406 P. Betti            Modified to use QCoreApplication class
 * #--
 * #
 */
//$Id: QtCoreResource_Loader.cpp 91628 2010-09-07 11:11:12Z johnnyw $

#include <qtcoreresource_loader.h>
#include "tao/ORB_Core.h"
#include <qtcoreresource_factory.h>

TAO_BEGIN_VERSIONED_NAMESPACE_DECL

namespace TAO
{
QtCoreResource_Loader::QtCoreResource_Loader ( QCoreApplication *qapp )
{
	QtCoreResource_Factory *tmp = 0;

	ACE_NEW ( tmp,
	          QtCoreResource_Factory ( qapp ) );

	TAO_ORB_Core::set_gui_resource_factory ( tmp );
}

QtCoreResource_Loader::~QtCoreResource_Loader ( void )
{
}
}

TAO_END_VERSIONED_NAMESPACE_DECL
