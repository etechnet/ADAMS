/*
 * This file was generated by qdbusxml2cpp version 0.7
 * Command line was: qdbusxml2cpp -m -p ssm_logger_if /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml
 *
 * qdbusxml2cpp is Copyright (C) 2013 Digia Plc and/or its subsidiary(-ies).
 *
 * This is an auto-generated file.
 * Do not edit! All changes made to it will be lost.
 */

#ifndef SSM_LOGGER_IF_H
#define SSM_LOGGER_IF_H

#include <QtCore/QObject>
#include <QtCore/QByteArray>
#include <QtCore/QList>
#include <QtCore/QMap>
#include <QtCore/QString>
#include <QtCore/QStringList>
#include <QtCore/QVariant>
#include <QtDBus/QtDBus>

/*
 * Proxy class for interface net.etech.adams.ssm_logger
 */
class NetEtechAdamsSsm_loggerInterface: public QDBusAbstractInterface
{
    Q_OBJECT
public:
    static inline const char *staticInterfaceName()
    { return "net.etech.adams.ssm_logger"; }

public:
    NetEtechAdamsSsm_loggerInterface(const QString &service, const QString &path, const QDBusConnection &connection, QObject *parent = 0);

    ~NetEtechAdamsSsm_loggerInterface();

public Q_SLOTS: // METHODS
    inline QDBusPendingReply<int> getLogLevel(const QString &process)
    {
        QList<QVariant> argumentList;
        argumentList << QVariant::fromValue(process);
        return asyncCallWithArgumentList(QLatin1String("getLogLevel"), argumentList);
    }

    inline QDBusPendingReply<> toLogger(int level, const QDateTime &time, const QString &process, int processPid, const QString &msg)
    {
        QList<QVariant> argumentList;
        argumentList << QVariant::fromValue(level) << QVariant::fromValue(time) << QVariant::fromValue(process) << QVariant::fromValue(processPid) << QVariant::fromValue(msg);
        return asyncCallWithArgumentList(QLatin1String("toLogger"), argumentList);
    }

    inline QDBusPendingReply<> toMonitor(const QDateTime &time, const QString &process, const QString &msg)
    {
        QList<QVariant> argumentList;
        argumentList << QVariant::fromValue(time) << QVariant::fromValue(process) << QVariant::fromValue(msg);
        return asyncCallWithArgumentList(QLatin1String("toMonitor"), argumentList);
    }

Q_SIGNALS: // SIGNALS
};

namespace net {
  namespace etech {
    namespace adams {
      typedef ::NetEtechAdamsSsm_loggerInterface ssm_logger;
    }
  }
}
#endif
