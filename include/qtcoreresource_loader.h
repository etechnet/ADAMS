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

//=============================================================================
/**
 *  @file   QtCoreResource_Loader.h
 *
 *  $Id: QtCoreResource_Loader.h 94052 2011-05-11 13:40:32Z mhengstmengel $
 *
 *  @author Marek Brudka <mbrudka@aster.pl>
 *  @author Balachandran Natarajan <bala@cs.wustl.edu>
 */
//=============================================================================

#ifndef TAO_QTCORERESOURCE_LOADER_H
#define TAO_QTCORERESOURCE_LOADER_H
#include /**/ "ace/pre.h"

#include "tao_qtcoreresource_export.h"

#if !defined (ACE_LACKS_PRAGMA_ONCE)
# pragma once
#endif /* ACE_LACKS_PRAGMA_ONCE */

#include "tao/Versioned_Namespace.h"

#include <Qt/qcoreapplication.h>

TAO_BEGIN_VERSIONED_NAMESPACE_DECL

namespace TAO
{
/**
 * @class QtCoreResource_Loader
 *
 * @brief Loads TAO resources related with Qt.
 *
 * This class changes the default reactor implementation into
 * ACE_QtReactor one by calling TAO_ORB_Core::set_gui_resource_factory.
 * User should create an instance of this class before ORB_init
 * when the TAO server has has to be integrated within Qt event loop.
 *
 * Please notice, this class has to be created in the main Qt thread,
 * because set_gui_resource_factory creates a variable in TSS. This way
 * QtReactor is instantiated only in Qt event loop thread.
 */

class /*TAO_QtCoreResource_Export*/ QtCoreResource_Loader
{
public:
	QtCoreResource_Loader ( QCoreApplication *qapp );
	virtual ~QtCoreResource_Loader ( void );
};
}

TAO_END_VERSIONED_NAMESPACE_DECL

#include /**/ "ace/post.h"
#endif /* TAO_QTCORERESOURCE_LOADER_H */
