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
**++
**  FACILITY:Util_Date  processo UTIL  sistema RDA progetto STS
**
**  MODULE DESCRIPTION:
**
**      In questo modulo sono contenute le funzioni che operano sulle date.
**
**  AUTHOR:
**
**          Vincenzo Parrello - Digital -
**
**  CREATION DATE:  27-Giugno-1996
**  DESIGN ISSUES:
**      Questo modulo fa parte del sistema RDA di STS
**
**  MODIFICATION HISTORY:
**
**
**--
*/

#include "Util_Date.h"
#include "Util_Define.h"
#include "Util_Typedef.h"

#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define LUN_GIORNO                      20
#define LUN_DESCRIZIONE			200
#define LUN_HELP			200

typedef struct
{
	char   giorno[LUN_GIORNO];
	char   descrizione[LUN_DESCRIZIONE];
	char   help[LUN_HELP];
}TPARXFFile;

typedef struct{
        int DayOfWeek;
        int Index;
        int Month;
        int Day;
        int MonthOfYesterday;
        int Yesterday;
        int HollydayFlag;
   int year;
   int yearOfYesterday;
}SINCHRO_Time;


/*
** Prototipi
*/

int iOpenFile(FILE ** pfileFile, char * acFileName , char * acMode);
int iReadFile2Buffer(FILE * pfileFile , void * pvBuffer , long int liLen);
char *  acGetNameFileFromVarAmb( char  * acVarDir , char * acNF);
int iGetLastDay(int iDay, int iMonth,int iYear);
int iGetEnv(char  * acVar, char  * acBuffer);
void vGetPrevDay(int *iDay, int *iMonth, int *iYear);

void  vFillUtilDate(UTIL_Date * stNow );

char static nomefile1[]  = "ggXFest";
static char  UtilDate_StrMonth[][4] ={"JAN" , "FEB","MAR", "APR", "MAY"	,"JUN"	, "JUL"	, "AUG"	, "SEP"	, "OCT"	,"NOV"	, "DEC"};
SINCHRO_Time  *  Util_Sinchro_SharedMem ;

#define SINCHRO_SHAREDMEM  Util_Sinchro_SharedMem
#define STR_MONTH_OF_YEAR UtilDate_StrMonth
#define SINCHRO_GET_DATA(s)   s = SINCHRO_SHAREDMEM;


/*
** ++ *******************************************************************
**
** Unit:  iGetLastDay
**
** Module: Util_Date.C
**
** Description:
**
**           Calcola l'ultimo giorno del mese compresi gli anni
**				bisestili
**
** Calling Sequence:
**
** Parameters Description:
** 	INPUT :
**    	int	iDay 		=> Giorno del Mese
**    	int	iMonth 	=> Mese
**    	int	iYear 	=> Anno
**
**		OUTPUT :
**
**
**    RESULT:
**					La funzione restituisce :
**					L'Ultimo giorno del mese.
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/

int iGetLastDay(int iDay, int iMonth,int iYear)
{
  if (iMonth == FEBRUARY)
     return ( isLeapYear(iYear) ? BISEST : NO_BISEST );
  else
     return ( aiMonthDays[iMonth-1] );
}

/*
** ++ *******************************************************************
**
** Unit: iGet15IntoDay
**
** Module: UTIL_PAR.C
**
** Description:
**
**      Restituisce il numero dei quarti d'ora di un orario.
**
** Calling Sequence:
**
** Parameters Description:
** 	INPUT :
**    			iMin  => Identifica i minuti.
**    			iHour => Identifica le ore.
**		OUTPUT :
**
**
**    RESULT:
**					La funzione restituisce :
**							vedi description
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/
int iGet15IntoDay(int iMin, int iHour)
{
  int 	iNum15IntoDay = 0;
  div_t	div_tDiv;

  iNum15IntoDay = ( iHour * 4 ) ;

  div_tDiv = div(iMin,15);

  if ( ( iMin <= 14) && (iHour == 0) )
	 iNum15IntoDay = 0;
  else
    iNum15IntoDay += div_tDiv.quot;

  return (iNum15IntoDay);

}

/*
** ++ *******************************************************************
**
** Unit:  vGetCurrentDate
**
** Module: Util_Date.C
**
** Description:
**
**           Calcola la data corrente del sistema
**				 Valorizzando 3 puntatori ad interi passati come parametri.
**
** Calling Sequence:
**
** Parameters Description:
** 	INPUT :
**    	int * 	iDay 		=> Giorno del Mese
**    	int * 	iMonth 	=> Mese
**    	int *		iYear 	=> Anno
**
**		OUTPUT :
**
**    RESULT:
**					La funzione non restituisce alcun valore.
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/
void vGetCurrentDate(int *iDay,int *iMonth,int *iYear)
{
  time_t		timeToday;
  struct tm   *timeTm;

  time(&timeToday);
  timeTm = localtime(&timeToday);
  *iDay    = timeTm->tm_mday;
  *iMonth  = timeTm->tm_mon + 1;
  *iYear   = timeTm->tm_year ;

  vGetCurYear(iYear);
}

/*
** ++ *******************************************************************
**
** Unit:  acStrCurDate
**
** Module: Util_Date.C
**
** Description:
**
**           Calcola la data e ora corrente del sistema
**				 valorizzando una stringa ritornata per referenza.
**           Es. 12-JAN-1997 10:00:00
** Calling Sequence:
**
** Parameters Description:
** 	INPUT :
**				void
**		OUTPUT :
**
**    RESULT:
**					La funzione restituisce l'indirizzo alla stringa.
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/
char * acStrCurDate()
{
 	static char  acCurDate[25];

  	static time_t		timeToday;
  static	struct tm   *timeTm;

  	time(&timeToday);
  	timeTm = localtime(&timeToday);

  vGetCurYear(&timeTm->tm_year);
  sprintf(acCurDate,"%02d-%03s-%04d %02d:%02d:%02d",
						timeTm->tm_mday,
						STR_MONTH_OF_YEAR[timeTm->tm_mon],
						timeTm->tm_year ,
						timeTm->tm_hour,
						timeTm->tm_min,
						timeTm->tm_sec);
	return acCurDate;
}
char * acStrCurDate1()
{
 	static char  acCurDate[25];

  	static time_t		timeToday;
  static	struct tm   *timeTm;

  	time(&timeToday);
  	timeTm = localtime(&timeToday);

  vGetCurYear(&timeTm->tm_year);
  sprintf(acCurDate,"%2d-%03s-%04d %02d:%02d:%02d",
						timeTm->tm_mday,
						STR_MONTH_OF_YEAR[timeTm->tm_mon],
						timeTm->tm_year ,
						timeTm->tm_hour,
						timeTm->tm_min,
						timeTm->tm_sec);
	return acCurDate;
}


/*
** ++ *******************************************************************
**
** Unit:  acStrCurDateMinus45
**
** Module: Util_Date.c
**
** Description:
**
**           Calcola la data e ora corrente del sistema
**				 valorizzando una stringa ritornata per referenza,
**           che contiene la data con l'ora relativa a tre quarti
**           d'ora prima.
**           Es. se la data attuale e' 12-JAN-1997 10:00:00
**           il valore ritornato sara' 12-JAN-1997  9:15:00
** Calling Sequence:
**
** Parameters Description:
** 	INPUT :
**				void
**		OUTPUT :
**
**    RESULT:
**					La funzione restituisce l'indirizzo alla stringa.
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/
char * acStrCurDateMinus45()
{
 	static char  acCurDate[25];

  	static time_t		timeToday;
  	static struct tm   *timeTm;
	int					iMonth;

  time(&timeToday);
  timeTm = localtime(&timeToday);

  if ( ( timeTm->tm_hour == 0 ) && ( timeTm->tm_min < 45 ) )
  {
	 iMonth = timeTm->tm_mon + 1;
	 vGetPrevDay( &timeTm->tm_mday, &iMonth, &timeTm->tm_year );
	 timeTm->tm_mon = iMonth - 1;
  }

  if ( timeTm->tm_min >= 45 )
  {
    timeTm->tm_min= ( timeTm->tm_min - 45 );
  }
  else
  {
    timeTm->tm_min = ( timeTm->tm_min - 45 ) + 60;
    timeTm->tm_hour = timeTm->tm_hour - 1;
    if ( timeTm->tm_hour < 0 )
      timeTm->tm_hour = 23;
  }
  vGetCurYear(&timeTm->tm_year);
  sprintf(acCurDate,"%02d-%03s-%04d %02d:%02d:%02d",
						timeTm->tm_mday,
						STR_MONTH_OF_YEAR[timeTm->tm_mon],
						timeTm->tm_year ,
						timeTm->tm_hour,
						timeTm->tm_min,
						timeTm->tm_sec);
	return acCurDate;
}




/*
** ++ *******************************************************************
**
** Unit: iCmpDate
**
** Module: Util_Date.c
**
** Description:
**
**      Confronta due date
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      char       acDate1                   Stringa contenente la data
**      char       acDate2                   Stringa contenente la data
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**		  char	     acCmpDate1
**      char        acCmpDate2
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/

int iCmpDate(char * acDate1 , char * acDate2)
{
 	char  acCmpDate1[25];
 	char  acCmpDate2[25];
 	char  acMon1[4];
 	char  acMon2[4];
 	char  acMonNew1[4];
 	char  acMonNew2[4];
	int 	iLenArrMon;

	int i;

	iLenArrMon = sizeof(STR_MONTH_OF_YEAR) / sizeof(STR_MONTH_OF_YEAR[0]);

 	memset(acCmpDate1 , '\0',sizeof(acCmpDate1));
 	memset(acCmpDate2 , '\0',sizeof(acCmpDate2));
 	memset(acMon1 		, '\0',sizeof(acMon1));
 	memset(acMon2 		, '\0',sizeof(acMon2));
 	memset(acMonNew1 	, '\0',sizeof(acMonNew1));
 	memset(acMonNew2 	, '\0',sizeof(acMonNew2));


	memcpy(acMon1,acDate1+3,3);
	memcpy(acMon2,acDate2+3,3);

	for ( i = 0 ; i < iLenArrMon ; i ++)
	{
		if ( strcmp(acMon1,STR_MONTH_OF_YEAR[i]) == 0)
		{
			sprintf(acMonNew1, "%02d",i);
		}
		if ( strcmp(acMon2,STR_MONTH_OF_YEAR[i]) == 0)
		{
			sprintf(acMonNew2, "%02d",i);
		}
	}


	memset(acCmpDate1,'\0' , sizeof(acCmpDate1));
	memset(acCmpDate2,'\0' , sizeof(acCmpDate2));

	memcpy(acCmpDate1,acDate1+7,4);
	memcpy(acCmpDate2,acDate2+7,4);

	memcpy(acCmpDate1+4,acMonNew1,2);
	memcpy(acCmpDate2+4,acMonNew2,2);

	memcpy(acCmpDate1+6,acDate1,2);
	memcpy(acCmpDate2+6,acDate2,2);


	memcpy(acCmpDate1+8,acDate1+12,8);
	memcpy(acCmpDate2+8,acDate2+12,8);

	return strcmp(acCmpDate1, acCmpDate2);
}



/*
** ++ *******************************************************************
**
** Unit: vGetCurYear
**
** Module: Util_Date.c
**
** Description:
**
**       La funzione valorizza il puntatore iYear che alla fine dell'elaborazione
**       conterra l'anno corrente
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      int         iYear                    Contiene l'anno corrente
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


void vGetCurYear(int *iYear)
{
  /*
  ** Commentato per correzione Y2K bug
  **
  ** if ( *iYear > LIM )
  **  *iYear+=XX_SEC;
  ** else
  **  *iYear+=XXI_SEC;
  */

  *iYear+=XX_SEC;
}


/*
** ++ *******************************************************************
**
** Unit: vGetNextDay
**
** Module: Util_Date.c
**
** Description:
**
**      Calcola il giorno successivo a quello avuto in ingresso
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      int    iDay,iMonth,iYear             Contengono giorno mese e anno
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


void vGetNextDay(int *iDay, int *iMonth, int *iYear)
{
  int iLastDay;

  iLastDay = iGetLastDay( *iDay,*iMonth,*iYear);

  if ( *iMonth != LAST_MONTH )
    {
      if ( *iDay == iLastDay )
        {
          *iDay = 1;
          (*iMonth)++;
        }
      else
		  {
          (*iDay)++;
        }
   }
  else
   {
     if ( *iDay == iLastDay )
       {
         (*iYear)++;
         *iDay   = 1;
         *iMonth = 1;
       }
     else
		{
         (*iDay)++;
      }
   }
}




/*
** ++ *******************************************************************
**
** Unit: vGetPrevDay
**
** Module: Util_Date.c
**
** Description:
**
**        Calcola il giorno precedente a quello passatogli in ingresso
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      int      iDay,iMonth,iYear           Contengono giorno mese e anno
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


void vGetPrevDay(int *iDay, int *iMonth, int *iYear)
{
  int iLastDay;

  if ( *iMonth != FIRST_MONTH )
    if ( *iDay != FIRST_DAY )
      (*iDay)--;
    else
    {
      (*iMonth)--;
      *iDay = iGetLastDay(*iDay,*iMonth,*iYear);
    }
  else
    if ( *iDay != FIRST_DAY )
      (*iDay)--;
    else
    {
      *iMonth = LAST_MONTH;
      (*iYear)--;
      (*iDay) = iGetLastDay(*iDay,*iMonth,*iYear);
    }
}




/*
** ++ *******************************************************************
**
** Unit: iGetDayOfWeek
**
** Module: Util_Date.c
**
** Description:
**
**      La funzione restituisce un intero che identifica
**      il giorno della settimana
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      int      iDay,iMonth,iYear           Contengono giorno mese e anno
**
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**			int        liDayOfWeek              Identifica il giorno della
**                                           settimana
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


int iGetDayOfWeek(int iDay, int iMonth, int iYear)
{
  long   liDiffYears,
         liDiffMonths,
         liDiffDays,
         liNumYearsBis,
         liTotDays;
  long   liDayOfWeek;
  int		iCount;

  liDiffYears = iYear - FIRST_YEAR;
  liDiffMonths = iMonth - FIRST_MONTH;

  liDiffDays = 0;

  /* Calcola il numero di giorni che ci sono dall'inizio dell'anno
     alla data corrente*/
  for (iCount=0; iCount<liDiffMonths; iCount++)
    liDiffDays += aiMonthDays[iCount];
  if ( (iDay == FIRST_DAY) && (iMonth == FIRST_MONTH) )
    liDiffDays = 0;
  else
    liDiffDays += iDay;

  /* Calcola il numero di giorni relativi agli anni bisestili */
  liNumYearsBis = ( (long) (iYear/RANGE_BIS) );

  /* Numero totale di giorni che ci sono dal 1 gennaio dell'anno 1
     alla data corrente */
  liTotDays = liDiffYears * GG_YEAR + liDiffDays + liNumYearsBis;

  /* dividi iTot_giorni per GG_WEEK se il resto viene 0 (= sabato)
     oppure 1 (=domenica) allora il giorno e' festivo altrimenti e' feriale*/
  liDayOfWeek =  liTotDays%GG_WEEK;
  return ( (( liDayOfWeek + 5 ) % GG_WEEK) + 1 );
}




/*
** ++ *******************************************************************
**
** Unit: iGetFestivo
**
** Module: Util_Date.c
**
** Description:
**
**      La funzione idemtifica se un giorno e' festivo o feriale
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      int      iDay,iMonth,iYear           Contengono giorno mese e anno
**
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**			int                    FALSE        Il giorno e' festivo
**                              TRUE         Il giorno non  e' festivo
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


int iGetFestivo(int iDay, int iMonth, int iYear)
{
  int iDayOfWeek;

  iDayOfWeek = iGetDayOfWeek( iDay,  iMonth,  iYear);

  /*
  iDayOfWeek  = iDayOfWeek - 1 ;
  if ( iDayOfWeek < 0)
  {
	  iDayOfWeek = 6;
  }
  */
  if ( (iDayOfWeek == SATURDAY) ||
       (iDayOfWeek == MONDAY )    )
    return (TRUE);
  else
    return (FALSE);
}




/*
** ++ *******************************************************************
**
** Unit: vGetPrev3Data
**
** Module: Util_Date.c
**
** Description:
**
**      La funzione calcola la data reletiva a tre mesi prima
**      rispetto a quella ricevuta in ingresso valorizzando
**      i puntatori iMonth e iYear
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      int      iMonth,Year                 Contengono mese e anno
**
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


void vGetPrev3Data(int *iMonth, int *iYear)
{
  if ( ( *iMonth - RANGE ) <= 0 )
    {
      *iMonth = *iMonth - RANGE + MM_YEAR;
      *iYear = *iYear - 1;
    }
  else
      *iMonth = *iMonth - RANGE;
}




/*
** ++ *******************************************************************
**
** Unit: iGetExtraFestivo
**
** Module: Util_Date.c
**
** Description:
**
**      la funzione riconosce i giorni extra festivi e valorizza
**      un flag con true se il giorno e' stato riconosciuto
**      con false altrimenti
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**       int     iDay,iMonth,iYear           Contengono giorno mese anno
**       int         ipFlag                  Identifica con true o false
**                                           se il giorno e'extra festivo
**                                           o meno.
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**			int                 	UTIL_OK 			esecuzione corretta.
**										UTIL_NOK  		esecuzione non corretta.
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


int iGetExtraFestivo(int iDay, int iMonth, int iYear, int *ipFlag)
{
  int 			iRet 		= UTIL_OK;
  int 			iEsito	= UTIL_OK;
  char 			acDate[LUN_GIORNO];
  char 			acFileName[MAX_LEN_NAMEFILES];
  TPARXFFile 	tparxf;
  FILE 			*pfileFile;
  char 			acEnv[MAX_LEN_NAMEFILES];

  *ipFlag = FALSE;

  memset(acDate,'\0',sizeof(acDate));
  sprintf(acDate,"%02d/%02d/%04d",iDay,iMonth,iYear);

  memset(acFileName,'\0',sizeof(acFileName));
  sprintf(acFileName, "%s%04d.DAT",nomefile1,iYear);
  strcpy(acFileName,acGetNameFileFromVarAmb(ENV_PATH_FEP_DB,acFileName));

  iRet = iOpenFile(&pfileFile , acFileName , "rb");

  if ( iRet == UTIL_OK )
  {
	  while ( !feof(pfileFile) )
	  {
		  iEsito = iReadFile2Buffer( pfileFile ,&tparxf , sizeof(TPARXFFile));

        if ( strcmp(tparxf.giorno,acDate) == 0 )
        {
          *ipFlag = TRUE;
        }
	  }

  	  if (iEsito == UTIL_NOK )
	  {
		 iRet = UTIL_NOK;
		 fclose(pfileFile );
	  }
	  else
	  {
	 	 iRet = fclose(pfileFile );
	  }
  }
  else
  {;
  }
	// TRACE_ERROR_EVENT_P1("\nProblemi nell'apertura del file : %s",
	//							   acFileName);


  return iRet;
}





/*
** ++ *******************************************************************
**
** Unit: vGetExactTime
**
** Module: Util_Date.c
**
** Description:
**
**      La funzione centra l'ora in ingresso al quarto d'ora
**      e valorizza i puntatori iMin iHour.
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      int          iHour,iMin              Contengono Ore e minuti
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


void vGetExactTime( int *iHour, int *iMin )
{
  if ( (*iMin>7)&&(*iMin<=21) )
	 *iMin = 15;

  if ( (*iMin>=22)&&(*iMin<=37) )
	 *iMin = 30;

  if ( (*iMin>=38)&&(*iMin<=52) )
	 *iMin = 45;

  if ( (*iMin>=53)&&(*iMin<0) )
  {
	 *iMin = 0;
	 *iHour = (*iHour+1)%24;
  }

  if ( (*iMin>=0)&&(*iMin<=7) )
	 *iMin = 0;
}




/*
** ++ *******************************************************************
**
** Unit: iNormMinute
**
** Module: Util_Date.c
**
** Description:
**
**      Normalizza i minuti al quarto d'ora
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      int          iMin                    Minuti
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**		  int          iMin                    Minuti normalizzati
**
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


int  iNormMinute( int iMin )
{
	int iRet ;

	iRet = iMin / 15;
  	if(iRet > 3)
  	{
	 return 0 ;
	}

	return 15*iRet;
}



/*
** ++ *******************************************************************
**
** Unit: vFillUtilDate
**
** Module: Util_Date.c
**
** Description:
**
**      La funzione riempie la struttura stNow che alla fine
**      dell'elaborazione conterra data e ora corrente, data
**      e ora riferite al giorno precedente e numeri di quaeti d'ora
**      verificando ,sia per il giorno attuale che per quello
**      precedente ,se si tratta di un giorno festivo o extra festivo.
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**    UTIL_Date      stNow                   Struttura data
**
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**			int                 	UTIL_OK 			esecuzione corretta.
**										UTIL_NOK  		esecuzione non corretta.
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


void  vFillUtilDate(UTIL_Date * stNow )
{
	int iRet = UTIL_OK;
  	time_t		timeToday;
  	struct tm   *timeTm;

	#define OGGI stNow->Tod
	#define IERI stNow->Yes

	time(&timeToday);
  	timeTm = localtime(&timeToday);

	stNow->iHour 		= timeTm->tm_hour;
	stNow->iMin 		= timeTm->tm_min	;
	stNow->iSec 		= timeTm->tm_sec	;
	stNow->iNumQuart 	= iGet15IntoDay(stNow->iMin, stNow->iHour );



  OGGI.HollydayFlag 	= iGetFestivo  (  OGGI.Day, OGGI.Month, OGGI.Year);
  iRet = iGetExtraFestivo(OGGI.Day,OGGI.Month,OGGI.Year,
									&OGGI.ExtraHollydayFlag);
  if(iRet == UTIL_NOK)
  {
	  OGGI.ExtraHollydayFlag = FALSE;
  }

	IERI.Day       = OGGI.Day;
	IERI.Month     = OGGI.Month;
	IERI.Year      = OGGI.Year;

	vGetPrevDay(&IERI.Day, &IERI.Month, &IERI.Year);

	IERI.HollydayFlag = iGetFestivo  ( IERI.Day, IERI.Month, IERI.Year);

  iRet = iGetExtraFestivo(IERI.Day,IERI.Month,IERI.Year,
									&IERI.ExtraHollydayFlag);
  if(iRet == UTIL_NOK)
  {
	  IERI.ExtraHollydayFlag = FALSE;
  }

   /* Bisogna caricare la struttra con il flag che indica se il giorno
			e' extrafestivo */

	return ;
	#undef OGGI
	#undef IERI
}




/*
** ++ *******************************************************************
**
** Unit: iGetCurUtilDate
**
** Module: Util_Date.c
**
** Description:
**
**      La funzione riempie la struttura atNow con la data �rrente
**
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**    UTIL_Date      stNow                   Struttura data
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**			int                 	UTIL_OK 			esecuzione corretta.
**										UTIL_NOK  		esecuzione non corretta.
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/



int  iGetCurUtilDate(UTIL_Date * stNow )
{
	int iRet = UTIL_OK;
  	time_t		timeToday;
  	struct tm   *timeTm;

  	time(&timeToday);
  	timeTm = localtime(&timeToday);

	stNow->Tod.Day   = timeTm->tm_mday;
  	stNow->Tod.Month = timeTm->tm_mon + 1;
  	stNow->Tod.Year  = timeTm->tm_year ;
  	vGetCurYear(&stNow->Tod.Year);

  	stNow->Tod.DayOfWeek	= iGetDayOfWeek(	stNow->Tod.Day,
													stNow->Tod.Month,
													stNow->Tod.Year );

  stNow->Yes.DayOfWeek	= stNow->Tod.DayOfWeek -1 ;
	if ( stNow->Yes.DayOfWeek <= 0)
	{
		stNow->Yes.DayOfWeek = 7;
	}

	vFillUtilDate(stNow);

	return 0 ;
}




/*
** ++ *******************************************************************
**
** Unit: iGetCurDateFromSINCHRO
**
** Module: Util_Date.c
**
** Description:
**
**      La funzione riempie la stuttura stNow con i dati presenti
**      sulla shared memory di sinchro. Identifica inoltre il
**      giorno precedente a quello corrente.
**
** Calling Sequence:
**
** Parameters Description:
**
** 	--INPUT------------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**      UTIL_Date    stNow                   Struttura data
** 	--OUTPUT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**
** 	--RESULT-----------------------------------------------------------
**		| TYPE    		NAME       VALUE    		DESCRIPTION						|
**		-------------------------------------------------------------------
**			int                 	UTIL_OK 			esecuzione corretta.
**										UTIL_NOK  		esecuzione non corretta.
**
**      Global Variables Affected:
**
**
** -- *******************************************************************
*/


int  iGetCurDateFromSINCHRO(UTIL_Date * stNow )
{
	int iRet = UTIL_OK;
	static SINCHRO_Time * Oggi;

	SINCHRO_GET_DATA(Oggi);
	if (Oggi == NULL)
	{

		return UTIL_NOK;
	}
	else
	{
          ;
	}

  stNow->Tod.Day    		= Oggi->Day;
  stNow->Tod.Month  		= Oggi->Month;
  stNow->Tod.Year   		= Oggi->year;
  stNow->Tod.DayOfWeek	= Oggi->DayOfWeek;

  	stNow->Yes.DayOfWeek	= stNow->Tod.DayOfWeek -1 ;
	if ( stNow->Yes.DayOfWeek <= 0)
	{
		stNow->Yes.DayOfWeek = 7;
	}

	vFillUtilDate(stNow);

	return UTIL_OK;
}

int iOpenFile(FILE ** pfileFile, char * acFileName , char * acMode)
{
   int  iRet = UTIL_OK;
   *pfileFile = fopen( acFileName , acMode);
   if(  *pfileFile == NULL )
   {
      iRet = UTIL_NOK;
   }
        else
        {

      iRet = UTIL_OK;


        }

   return iRet;
}

int iReadFile2Buffer(FILE * pfileFile , void * pvBuffer , long int liLen)
{
   int  iEsito = UTIL_OK;
	char c[3];
	fpos_t ulOldPos;


   if (pfileFile != NULL)
   {
      iEsito =  fread (  pvBuffer , (size_t)liLen , (size_t)1 , pfileFile );
      if (iEsito != 1 )
      {
         iEsito =  UTIL_NOK;
      }
      else
      {
			fgetpos(pfileFile,&ulOldPos ) ;
			fread (  &c , (size_t)1 , (size_t)1 , pfileFile );
			if (feof(pfileFile)== 0)
			{
				fsetpos(pfileFile,&ulOldPos );
			}

			iEsito = UTIL_OK;
      }
   }
   else
   {
      iEsito = UTIL_NOK;
   }
   return iEsito;

}

char *  acGetNameFileFromVarAmb( char  * acVarDir , char * acNF)
{

   static   char acFileName[MAX_LEN_NAMEFILES]; /* Nome file  */
            char acEnv[MAX_LEN_NAMEFILES]   ;   /* VarAmb     */


   /* La variabile d'ambiente acVarDir deve essere nota
      altrimenti viene ritornata una stinga vuota */

   if ( iGetEnv( acVarDir, acEnv)  == UTIL_OK )
   {
      sprintf(acFileName , "%s/%s",acEnv, acNF);
   }
   else
   {
      sprintf(acFileName , "");
   }

   return acFileName;
}

int iGetEnv(char  * acVar, char  * acBuffer)
{

   char * acEnv   ;               /* VarAmb     */
   int iEsito = UTIL_OK;

   /* La variabile d'ambiente acVarDir deve essere nota
      altrimenti la funziona ritorna stinga vuota */

   if ( (acEnv = getenv( acVar) ) != NULL )
   {
      sprintf(acBuffer , "%s",acEnv);
      iEsito = UTIL_OK;
   }
   else
   {
      iEsito = UTIL_NOK;
   }
   return iEsito;
}
