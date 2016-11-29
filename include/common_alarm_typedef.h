/*
**++
**  common_alarm_typdef.h
**
**	Typedef comuni allarm routing
**
**  AUTHORS:
**      Piergiorgio Betti - piergiorgio.betti@e-tech.net
**
**  CREATION DATE:  20090702
**  DESIGN ISSUES:
**      Questo modulo fa parte del sistema RDA di STS
**
**  MODIFICATION HISTORY:
**
**--
*/
/*
** Tipi di dato
*/

#ifndef _common_alarm_typdef_h
#define _common_alarm_typdef_h

#ifndef STS_FILENAME_MAX
#define STS_FILENAME_MAX	255
#endif
#include <QtCore/qshareddata.h>

// Threshold types
enum thr_ThresholdTypes {
	thresholdType_MIN,
	thresholdType_MAX,
	thresholdType_FIXED,
	num_thresholdType
};

#ifndef	STATUS_OFF		// avoid conflicts with old alarms defines

enum ams_AlarmStatus {
		STATUS_OFF,
		STATUS_PREALARMED,
		STATUS_ON,
		num_ams_AlarmStatus
};

#define	ALARM_TYPE_NOALARM		0
#define MAX_ALLOWED_INTERVALS		16

#endif	// STATUS_OFF

// #define	PID_FILE_NAME_PREFIX	".ams_pid"
#define REL_STATUS_FILE_NAME	".ams_rel_status"

// Generic value container
class UnionValue {
public:
	union {
		int		vInt;
// 		unsigned int	vUnsignedInt;
// 		short		vShort;
// 		unsigned short	vUnsignedShort;
// 		long		vLong;
// 		unsigned long	vUnsignedLong;
// 		void *		vPointer;
// 		unsigned char	vUnsignedChar;
// 		unsigned char *	vArrayOfChar;
// 		float		vFloat;
		double		vDouble;
	};
	unsigned char typ;

	UnionValue() : typ('i') { vInt = 0; }
	UnionValue(int a) : typ('i') { vInt = a; }
	UnionValue(double a) : typ('d') { vDouble = a; }
	inline unsigned char type() { return typ; }
};

typedef struct  VAR_FILE_TAG {
	char acName[STS_FILENAME_MAX];
} VAR_FILE;

typedef struct _policyAlarm {
	int	iTraffId;		/* T_POLITICHE.TRAFF_ID%TYPE,      -- Costante a 1 per RCRD */
	char	cNewPolicy;		/* T_POLITICHE.POL_ID%TYPE,        -- 'M' 'P' media o persistenza (char) */
	int	iInterval;		/* T_POLITICHE.INTERVALLO%TYPE,    -- 1-16 (numero) */
	int 	iPersistence;		/* T_POLITICHE.PERSISTENZA%TYPE,   -- 1-4 (1..4 o null in caso di media) */
	float	coeff;			/* Coefficente di aggiustamento */
} ALARM_POLICY;

typedef struct _active_thresholds {
	unsigned char working_day;
	int bids;
	double abr;
	double aloc;
	double kc;
} ACTIVE_THRESHOLDS;

typedef struct _threshold_value {
	double kc;
	double value;
} THRESHOLD_VALUE;

/*******************************
 Struttura Allarmi per correl
 ******************************/

typedef struct _quality_counters {
	int bids;
	int answers;
	float conversation_erlangs;
} QUALITY_COUNTERS;

#endif /* _common_alarm_typdef_h */

