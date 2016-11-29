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
// -*- C++ -*-
// $Id: ACE_QtCoreReactor_export.h 80826 2008-03-04 14:51:23Z wotte $
// Definition for Win32 Export directives.
// This file is generated automatically by generate_export_file.pl -s ACE_QtCoreReactor
// ------------------------------
#ifndef ACE_QTCOREREACTOR_EXPORT_H
#define ACE_QTCOREREACTOR_EXPORT_H

#include /**/ "ace/config-all.h"

#if defined (ACE_AS_STATIC_LIBS) && !defined (ACE_QTCOREREACTOR_HAS_DLL)
#  define ACE_QTCOREREACTOR_HAS_DLL 0
#endif /* ACE_AS_STATIC_LIBS && ACE_QTCOREREACTOR_HAS_DLL */

#if !defined (ACE_QTCOREREACTOR_HAS_DLL)
#  define ACE_QTCOREREACTOR_HAS_DLL 1
#endif /* ! ACE_QTCOREREACTOR_HAS_DLL */

#if defined (ACE_QTCOREREACTOR_HAS_DLL) && (ACE_QTCOREREACTOR_HAS_DLL == 1)
#  if defined (ACE_QTCOREREACTOR_BUILD_DLL)
#    define ACE_QtCoreReactor_Export ACE_Proper_Export_Flag
#    define ACE_QTCOREREACTOR_SINGLETON_DECLARATION(T) ACE_EXPORT_SINGLETON_DECLARATION (T)
#    define ACE_QTCOREREACTOR_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK) ACE_EXPORT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#  else /* ACE_QTCOREREACTOR_BUILD_DLL */
#    define ACE_QtCoreReactor_Export ACE_Proper_Import_Flag
#    define ACE_QTCOREREACTOR_SINGLETON_DECLARATION(T) ACE_IMPORT_SINGLETON_DECLARATION (T)
#    define ACE_QTCOREREACTOR_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK) ACE_IMPORT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#  endif /* ACE_QTCOREREACTOR_BUILD_DLL */
#else /* ACE_QTCOREREACTOR_HAS_DLL == 1 */
#  define ACE_QtCoreReactor_Export
#  define ACE_QTCOREREACTOR_SINGLETON_DECLARATION(T)
#  define ACE_QTCOREREACTOR_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#endif /* ACE_QTCOREREACTOR_HAS_DLL == 1 */

// Set ACE_QTCOREREACTOR_NTRACE = 0 to turn on library specific tracing even if
// tracing is turned off for ACE.
#if !defined (ACE_QTCOREREACTOR_NTRACE)
#  if (ACE_NTRACE == 1)
#    define ACE_QTCOREREACTOR_NTRACE 1
#  else /* (ACE_NTRACE == 1) */
#    define ACE_QTCOREREACTOR_NTRACE 0
#  endif /* (ACE_NTRACE == 1) */
#endif /* !ACE_QTCOREREACTOR_NTRACE */

#if (ACE_QTCOREREACTOR_NTRACE == 1)
#  define ACE_QTCOREREACTOR_TRACE(X)
#else /* (ACE_QTCOREREACTOR_NTRACE == 1) */
#  if !defined (ACE_HAS_TRACE)
#    define ACE_HAS_TRACE
#  endif /* ACE_HAS_TRACE */
#  define ACE_QTCOREREACTOR_TRACE(X) ACE_TRACE_IMPL(X)
#  include "ace/Trace.h"
#endif /* (ACE_QTCOREREACTOR_NTRACE == 1) */

#endif /* ACE_QTCOREREACTOR_EXPORT_H */

// End of auto generated file.
