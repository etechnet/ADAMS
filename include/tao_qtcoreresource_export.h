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
// $Id: TAO_QtCoreResource_Export.h 73516 2006-07-14 21:10:15Z jwillemsen $
// Definition for Win32 Export directives.
// This file is generated automatically by generate_export_file.pl -s TAO_QtCoreResource
// ------------------------------
#ifndef TAO_QTCORERESOURCE_EXPORT_H
#define TAO_QTCORERESOURCE_EXPORT_H

#include "ace/config-all.h"

#if defined (ACE_AS_STATIC_LIBS) && !defined (TAO_QTCORERESOURCE_HAS_DLL)
#  define TAO_QTCORERESOURCE_HAS_DLL 0
#endif /* ACE_AS_STATIC_LIBS && TAO_QTCORERESOURCE_HAS_DLL */

#if !defined (TAO_QTCORERESOURCE_HAS_DLL)
#  define TAO_QTCORERESOURCE_HAS_DLL 1
#endif /* ! TAO_QTCORERESOURCE_HAS_DLL */

#if defined (TAO_QTCORERESOURCE_HAS_DLL) && (TAO_QTCORERESOURCE_HAS_DLL == 1)
#  if defined (TAO_QTCORERESOURCE_BUILD_DLL)
#    define TAO_QtCoreResource_Export ACE_Proper_Export_Flag
#    define TAO_QTCORERESOURCE_SINGLETON_DECLARATION(T) ACE_EXPORT_SINGLETON_DECLARATION (T)
#    define TAO_QTCORERESOURCE_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK) ACE_EXPORT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#  else /* TAO_QTCORERESOURCE_BUILD_DLL */
#    define TAO_QtCoreResource_Export ACE_Proper_Import_Flag
#    define TAO_QTCORERESOURCE_SINGLETON_DECLARATION(T) ACE_IMPORT_SINGLETON_DECLARATION (T)
#    define TAO_QTCORERESOURCE_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK) ACE_IMPORT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#  endif /* TAO_QTCORERESOURCE_BUILD_DLL */
#else /* TAO_QTCORERESOURCE_HAS_DLL == 1 */
#  define TAO_QtCoreResource_Export
#  define TAO_QTCORERESOURCE_SINGLETON_DECLARATION(T)
#  define TAO_QTCORERESOURCE_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#endif /* TAO_QTCORERESOURCE_HAS_DLL == 1 */

// Set TAO_QTCORERESOURCE_NTRACE = 0 to turn on library specific tracing even if
// tracing is turned off for ACE.
#if !defined (TAO_QTCORERESOURCE_NTRACE)
#  if (ACE_NTRACE == 1)
#    define TAO_QTCORERESOURCE_NTRACE 1
#  else /* (ACE_NTRACE == 1) */
#    define TAO_QTCORERESOURCE_NTRACE 0
#  endif /* (ACE_NTRACE == 1) */
#endif /* !TAO_QTCORERESOURCE_NTRACE */

#if (TAO_QTCORERESOURCE_NTRACE == 1)
#  define TAO_QTCORERESOURCE_TRACE(X)
#else /* (TAO_QTCORERESOURCE_NTRACE == 1) */
#  if !defined (ACE_HAS_TRACE)
#    define ACE_HAS_TRACE
#  endif /* ACE_HAS_TRACE */
#  define TAO_QTCORERESOURCE_TRACE(X) ACE_TRACE_IMPL(X)
#  include "ace/Trace.h"
#endif /* (TAO_QTCORERESOURCE_NTRACE == 1) */

#endif /* TAO_QTCORERESOURCE_EXPORT_H */

// End of auto generated file.
