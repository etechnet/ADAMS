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
 *  @file   QtCoreResource_Factory.h
 *
 *  $Id: QtCoreResource_Factory.h 78128 2007-04-20 08:07:58Z johnnyw $
 *
 *  @author Balachandran Natarajan <bala@dre.vanderbilt.edu>
 *  @author Marek Brudka <mbrudka@aster.pl>
 */
//=============================================================================
#ifndef TAO_QTCORERESOURCE_FACTORY_H
#define TAO_QTCORERESOURCE_FACTORY_H
#include /**/ "ace/pre.h"

#include "tao_qtcoreresource_export.h"

#if !defined (ACE_LACKS_PRAGMA_ONCE)
# pragma once
#endif /* ACE_LACKS_PRAGMA_ONCE */

#include "qtcorereactor.h"
#include "tao/GUIResource_Factory.h"

class QCoreApplication;

TAO_BEGIN_VERSIONED_NAMESPACE_DECL

namespace TAO
{
/**
 * @class QtCoreResource_Factory
 *
 * @brief TAO_GUI_Resource_Factory for creating QtCoreReactor.
 *
 * This factory is intended for creating QtCoreReactor for ORB. This
 * factory can be feed into ORB using
 * TAO_ORB_Core::set_gui_resource_factory method which is usually
 * done by TAO_QtCoreResource_Loader.
 */
class /*TAO_QtCoreResource_Export*/ QtCoreResource_Factory : public GUIResource_Factory
{
public:

	QtCoreResource_Factory ( QCoreApplication* qapp );

protected:

	/// Create or obtain current reactor implementation
	virtual ACE_Reactor_Impl *reactor_impl ( void );

private:

	/// Reactor created by this factory.
	ACE_QtCoreReactor *reactor_impl_;

	/// QCoreApplication running reactor
	QCoreApplication *qapp_;
};
}

TAO_END_VERSIONED_NAMESPACE_DECL

#include /**/ "ace/post.h"
#endif /* TAO_QTCORERESOURCE_FACTORY_H */
