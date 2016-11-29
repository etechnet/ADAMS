/****************************************************************************
** Meta object code from reading C++ file 'ssm_logger.h'
**
** Created by: The Qt Meta Object Compiler version 63 (Qt 4.8.5)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../../../include/ssm_logger.h"
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'ssm_logger.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 63
#error "This file was generated using the moc from 4.8.5. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
static const uint qt_meta_data_ssm_logger[] = {

 // content:
       6,       // revision
       0,       // classname
       1,   14, // classinfo
       3,   16, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // classinfo: key, value
      38,   11,

 // slots: signature, parameters, type, tag, flags
      89,   55,   54,   54, 0x0a,
     150,  133,   54,   54, 0x0a,
     199,  191,  187,   54, 0x0a,

       0        // eod
};

static const char qt_meta_stringdata_ssm_logger[] = {
    "ssm_logger\0net.etech.adams.ssm_logger\0"
    "D-Bus Interface\0\0level,time,process,processPid,msg\0"
    "toLogger(int,QDateTime,QString,int,QString)\0"
    "time,process,msg\0toMonitor(QDateTime,QString,QString)\0"
    "int\0process\0getLogLevel(QString)\0"
};

void ssm_logger::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        Q_ASSERT(staticMetaObject.cast(_o));
        ssm_logger *_t = static_cast<ssm_logger *>(_o);
        switch (_id) {
        case 0: _t->toLogger((*reinterpret_cast< int(*)>(_a[1])),(*reinterpret_cast< QDateTime(*)>(_a[2])),(*reinterpret_cast< QString(*)>(_a[3])),(*reinterpret_cast< int(*)>(_a[4])),(*reinterpret_cast< QString(*)>(_a[5]))); break;
        case 1: _t->toMonitor((*reinterpret_cast< QDateTime(*)>(_a[1])),(*reinterpret_cast< QString(*)>(_a[2])),(*reinterpret_cast< QString(*)>(_a[3]))); break;
        case 2: { int _r = _t->getLogLevel((*reinterpret_cast< QString(*)>(_a[1])));
            if (_a[0]) *reinterpret_cast< int*>(_a[0]) = _r; }  break;
        default: ;
        }
    }
}

const QMetaObjectExtraData ssm_logger::staticMetaObjectExtraData = {
    0,  qt_static_metacall 
};

const QMetaObject ssm_logger::staticMetaObject = {
    { &QObject::staticMetaObject, qt_meta_stringdata_ssm_logger,
      qt_meta_data_ssm_logger, &staticMetaObjectExtraData }
};

#ifdef Q_NO_DATA_RELOCATION
const QMetaObject &ssm_logger::getStaticMetaObject() { return staticMetaObject; }
#endif //Q_NO_DATA_RELOCATION

const QMetaObject *ssm_logger::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->metaObject : &staticMetaObject;
}

void *ssm_logger::qt_metacast(const char *_clname)
{
    if (!_clname) return 0;
    if (!strcmp(_clname, qt_meta_stringdata_ssm_logger))
        return static_cast<void*>(const_cast< ssm_logger*>(this));
    return QObject::qt_metacast(_clname);
}

int ssm_logger::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QObject::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 3)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 3;
    }
    return _id;
}
QT_END_MOC_NAMESPACE
