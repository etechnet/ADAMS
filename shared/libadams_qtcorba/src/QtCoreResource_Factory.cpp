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
//$Id: QtCoreResource_Factory.cpp 91628 2010-09-07 11:11:12Z johnnyw $

#include <qtcoreresource_factory.h>
#include "tao/debug.h"
#include <qtcorereactor.h>

TAO_BEGIN_VERSIONED_NAMESPACE_DECL

namespace TAO
{
  QtCoreResource_Factory::QtCoreResource_Factory ( QCoreApplication *qapp)
    : reactor_impl_ (0)
    , qapp_ (qapp)
  {
  }

  ACE_Reactor_Impl *
  QtCoreResource_Factory::reactor_impl (void)
  {    // synchronized by external locks
    if (this->qapp_ == 0)
      {
        ACE_ERROR ((LM_ERROR,
                    "TAO (%P|%t) - QCoreApplication is undefined.",
                    " Cannot create ACE_QtCoreReactor\n"));
        return 0;
      }

    if (!this->reactor_impl_)
      {
        ACE_NEW_RETURN (this->reactor_impl_,
                        ACE_QtCoreReactor (qapp_),
                        0);

        if (TAO_debug_level > 0)
          ACE_DEBUG ((LM_DEBUG,
                      "TAO (%P|%t) - ACE_QtCoreReactor created\n"));
      }

    return this->reactor_impl_;
  }
}

TAO_END_VERSIONED_NAMESPACE_DECL
