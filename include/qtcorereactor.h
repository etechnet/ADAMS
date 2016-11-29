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

//=============================================================================
/**
 *  @file    QtCoreReactor.h
 *
 *  $Id: QtCoreReactor.h 94053 2011-05-11 13:44:41Z mhengstmengel $
 *
 *  @author Hamish Friedlander <ullexco@wave.co.nz>
 *  @author Balachandran Natarajan <bala@cs.wustl.edu>
 */
//=============================================================================

#ifndef ACE_QTCOREREACTOR_H
#define ACE_QTCOREREACTOR_H

#include /**/ "ace/pre.h"

#include "ace_qtcorereactor_export.h"

#if !defined (ACE_LACKS_PRAGMA_ONCE)
# pragma once
#endif /* ACE_LACKS_PRAGMA_ONCE */

#include "ace/Select_Reactor.h"
#include "ace/Map_Manager.h"

# include "QtCore/qglobal.h"

// QT toolkit specific includes.
#include <Qt/qcoreapplication.h>
#include <Qt/qobject.h>
#include <Qt/qsocketnotifier.h>
#include <Qt/qtimer.h>

ACE_BEGIN_VERSIONED_NAMESPACE_DECL

/**
 * @class ACE_QtCoreReactor
 *
 * @brief An object-oriented event demultiplexor and event handler
 * dispatcher that uses the Qt Library. This class declaration
 * also uses the extension facilities  provided by the Qt. So,
 * readers  of the class declaration should not be upset with
 * the appearence of the Keywords like Q_OBJECT, private slots
 * etc. They are specific to Qt which uses these as a call back
 * methods implementation mechanism.
 *
 * \note Marek Brudka <mbrudka@elka.pw.edu.pl>: ACE_QtCoreReactor was
 * quickly bugfixed to meet ACE 5.4.2 (6.0.0?) deadline.
 * While it passes QtCoreReactor_Test now, there is a great
 * room for improvements as the implementation is rather inefficient
 * and obfuscated
 * To be more specific:
 * - reset_timeout always creates and removes qtimer after each
 * timeout event! Obviously, for fast triggering timers this may
 * lead to excessive memory management.
 * - create/destroy_notifiers_for_handle may also be reworked to
 * establish more clean relations between handles and QSocketNotifiers.
 * - read/write_exception_event disable now SocketNotifier for a while
 * to clear pending events. The cost of this operation is high: two hash
 * acces in ACE and at least two next ones in Qt. This makes QtReator slow,
 * but how clear pending events another way ?
 * - there is qcoreapplication() mutator, which sets new qcoreapplication for
 * QtCoreReactor. This mutator violates implicit assumption about the
 * relations between QTimer and QSocketNotifiers and QCoreApplication for
 * this reactor, namely one may expect that after qcoreapplication(), none
 * of QtCoreReactor artifacts is bound to old qcoreapplication. That's not true
 * now, as QTimer and QSocketNotifiers are not reparent to new
 * QCoreApplication. As a result, the sequence:
 * QCoreApplication *old_qapp = new QCoreApplication(..);
 * QtCoreReactor qreactor( old_qapp);
 * // .. register handlers, schedule_timers etc
 * QCoreApplication *new_qapp = new QCoreApplication(..);
 * qreactor.qpplication( new_qapp );
 * delete old_qapp;
 * almost always leads to problems and memory violation, because
 * QSocketNotifiers are released by old_qapp. Therefore QtCoreReactor
 * should not be reparent now by setting new qcoreapplication.
 * - the lifecycle of Qt objects in ACE contects is rather mysterious
 * and should be made more explicit.
 * - valgrind reports a small memory leak in QtCoreReactor_Test, though as for now
 * it is not clear if the leak is introduced by  QtCoreReactor, or rather incorrect
 * memory management in QtCoreReactor_Test.
 */
class ACE_QtCoreReactor_Export ACE_QtCoreReactor
  : public QObject,
    public ACE_Select_Reactor
{

    Q_OBJECT

public:
    /** \brief Constructor follows  @ACE_Select_Reactor
        \param QCoreApplication *qapp, qcoreapplication which runs events loop
    */
    ACE_QtCoreReactor (QCoreApplication *qapp = 0,
        ACE_Sig_Handler * = 0,
        ACE_Timer_Queue * = 0,
        int disable_notify_pipe = 0,
        ACE_Reactor_Notify *notify = 0,
        bool mask_signals = true,
        int s_queue = ACE_SELECT_TOKEN::FIFO);

    /** \brief Constructor follows @ACE_Select_Reactor
        \param QCoreApplication *qapp, qcoreapplication which runs events loop
    */
    ACE_QtCoreReactor (size_t size,
        QCoreApplication *qapp = 0,
        bool restart = false,
        ACE_Sig_Handler * = 0,
        ACE_Timer_Queue * = 0,
        int disable_notify_pipe = 0,
        ACE_Reactor_Notify *notify = 0,
        bool mask_signals = true,
        int s_queue = ACE_SELECT_TOKEN::FIFO);

    virtual ~ACE_QtCoreReactor (void);

    void qcoreapplication (QCoreApplication *qapp);

    // = Timer operations.
    virtual long schedule_timer (ACE_Event_Handler *handler,
        const void *arg,
        const ACE_Time_Value &delay_time,
        const ACE_Time_Value &interval);

    virtual int  cancel_timer (ACE_Event_Handler *handler,
        int dont_call_handle_close = 1);

    virtual int  cancel_timer (long timer_id,
        const void **arg = 0,
        int dont_call_handle_close = 1);

protected:

    // = Register timers/handles with Qt

    /// Register a single @a handler.
    virtual int register_handler_i (ACE_HANDLE handle,
        ACE_Event_Handler *handler,
        ACE_Reactor_Mask mask);

    /// Register a set of <handlers> with Qt.
    virtual int register_handler_i (const ACE_Handle_Set &handles,
        ACE_Event_Handler *handler,
        ACE_Reactor_Mask mask);


    /// Remove the <handler> associated with this @a handle.
    virtual int remove_handler_i (ACE_HANDLE handle,
        ACE_Reactor_Mask mask);

    /// Remove a set of <handles>.
    virtual int remove_handler_i (const ACE_Handle_Set &handles,
        ACE_Reactor_Mask mask);

    /// Wait for events to occur.
    virtual int wait_for_multiple_events (ACE_Select_Reactor_Handle_Set &handle_set,
        ACE_Time_Value *max_wait_time);

    virtual int QtWaitForMultipleEvents (int width,
        ACE_Select_Reactor_Handle_Set &wait_set,
        ACE_Time_Value *max_wait_time);

    virtual int bit_ops (ACE_HANDLE handle,
        ACE_Reactor_Mask mask,
        ACE_Select_Reactor_Handle_Set &handle_set,
        int ops);

    int set_enable_flag_by_mask (int flag_value, ACE_HANDLE handle, ACE_Reactor_Mask mask);
    void create_notifiers_for_handle (ACE_HANDLE handle);
    void destroy_notifiers_for_handle (ACE_HANDLE handle);

    // Wait for Qt events to occur

    /// Some Qt stuff that we need to have
    QCoreApplication *qapp_ ;

    /// Typedef of a map.
    typedef ACE_Map_Manager<ACE_HANDLE, QSocketNotifier *, ACE_Null_Mutex> MAP;

    /// A notifier for a read
    MAP read_notifier_;

    /// A write notifier
    MAP write_notifier_;

    /// An exception notifier
    MAP exception_notifier_;

    /// The timer class that would provide timer-sgnals & single-shot timers
    QTimer *qtime_ ;

private:
    /// This method ensures there's an Qt timeout for the first timeout
    /// in the Reactor's Timer_Queue.
    void reset_timeout (void);
    /// reopens notification pipe to create SocketNotifier for it
    void reopen_notification_pipe(void);
    /// Deny access since member-wise won't work...
    ACE_QtCoreReactor (const ACE_QtCoreReactor &);
    ACE_QtCoreReactor &operator= (const ACE_QtCoreReactor &);

private slots:

    // These are all part of the communication mechanism adopted in Qt.
    /// Dispatch a Read Event
    void read_event (int FD);

    /// Dispatch a Write Event
    void write_event (int FD);

    /// Dispatch an exception event
    void exception_event (int FD);

    /// Dispatch a timeout event
    void timeout_event (void);
};

ACE_END_VERSIONED_NAMESPACE_DECL

#include /**/ "ace/post.h"
#endif /* ACE_QTCOREREACTOR_H */
