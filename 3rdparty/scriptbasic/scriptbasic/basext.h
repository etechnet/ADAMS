/*
basext.h
*/
#ifndef __BASEXT_H__
#define __BASEXT_H__ 1
#ifdef  __cplusplus
extern "C" {
#endif
#include "report.h"
#include "sym.h"
#include "lexer.h"
#include "expression.h"
#include "syntax.h"
#include "reader.h"
#include "myalloc.h"
#include "builder.h"
#include "memory.h"
#include "execute.h"
#include "command.h"
#include "conftree.h"
#include "filesys.h"
#include "errcodes.h"
#include "tools/global.h"
#include "tools/md5.h"
#include "match.h"
#include "thread.h"
#include "scriba.h"
#include "logger.h"
#include "hndlptr.h"
#include "ipreproc.h"






#if (defined(_WIN32) || defined(__MACOS__))
# if STATIC_LINK
#  ifdef __cplusplus
#   define DLL_EXPORT extern "C" static
#  else
#   define DLL_EXPORT static
#  endif
# else
#  ifdef __cplusplus
#   define DLL_EXPORT extern "C" __declspec(dllexport) 
#  else
#   define DLL_EXPORT __declspec(dllexport) 
#  endif
# endif
#else
# if STATIC_LINK
#  ifdef __cplusplus
#   define DLL_EXPORT extern "C" static
#  else
#   define DLL_EXPORT static
#  endif
# else
#  ifdef __cplusplus
#   define DLL_EXPORT extern "C" 
#  else
#   define DLL_EXPORT
#  endif
# endif
#endif

typedef struct _SLFST {
  char *name;
  void *function;
  } SLFST, *PSLFST;

typedef struct _MODLIST {
  char *name;
  PSLFST *table;
  } MODLIST, *PMODLIST;


typedef struct _SupportTable {

  pExecuteObject pEo; 

  void * (*Alloc)(size_t n, void *p);
#define besALLOC(X) (pSt->Alloc((X),pSt->pEo->pMemorySegment))

#define besPROCALLOC(X) (pSt->pEo->pSTI->Alloc((X),besPROCMEMORYSEGMENT))

  void   (*Free)(void *pMem, void *p);
#define besFREE(X) (pSt->Free((X),pSt->pEo->pMemorySegment),(X)=NULL)

#define besPROCFREE(X) (pSt->pEo->pSTI->Free((X),besPROCMEMORYSEGMENT),(X)=NULL)

#define besPROCMEMORYSEGMENT (pSt->pEo->pSTI->pEo->pMemorySegment)

  
  
  pFixSizeMemoryObject (*NewMortalString)(pMemoryObject pMo, unsigned long StringSize, pMortalList pMortal);
  pFixSizeMemoryObject (*NewMortalLong)(pMemoryObject pMo, pMortalList pMortal);
  pFixSizeMemoryObject (*NewMortalRef)(pMemoryObject pMo, pMortalList pMortal);
  pFixSizeMemoryObject (*NewMortalDouble)(pMemoryObject pMo, pMortalList pMortal);
  pFixSizeMemoryObject (*NewMortalArray)(pMemoryObject pMo, pMortalList pMortal, long IndexLow, long IndexHigh);

#define besNEWMORTALSTRING(X)  (pSt->NewMortalString(pSt->pEo->pMo,(X),pSt->pEo->pGlobalMortalList))

#define besNEWMORTALLONG       (pSt->NewMortalLong(pSt->pEo->pMo,pSt->pEo->pGlobalMortalList))

#define besNEWMORTALREF        (pSt->NewMortalRef(pSt->pEo->pMo,pSt->pEo->pGlobalMortalList))

#define besNEWMORTALDOUBLE     (pSt->NewMortalDouble(pSt->pEo->pMo,pSt->pEo->pGlobalMortalList))

#define besNEWMORTALARRAY(X,Y) (pSt->NewMortalArray(pSt->pEo->pMo,pSt->pEo->pGlobalMortalList,(X),(Y)))

  pFixSizeMemoryObject (*NewString)(pMemoryObject pMo, unsigned long StringSize);
  pFixSizeMemoryObject (*NewLong)(pMemoryObject pMo);
  pFixSizeMemoryObject (*NewRef)(pMemoryObject pMo);
  pFixSizeMemoryObject (*NewDouble)(pMemoryObject pMo);
  pFixSizeMemoryObject (*NewArray)(pMemoryObject pMo, long LowIndex, long HighIndex);

#define besNEWSTRING(X)  (pSt->NewString(pSt->pEo->pMo,(X)))

#define besNEWLONG       (pSt->NewLong(pSt->pEo->pMo))

#define besNEWREF        (pSt->NewRef(pSt->pEo->pMo))

#define besNEWDOUBLE     (pSt->NewDouble(pSt->pEo->pMo))

#define besNEWARRAY(X,Y) (pSt->NewArray(pSt->pEo->pMo,(X),(Y)))

  int (*ReleaseVariable)(pMemoryObject, pFixSizeMemoryObject);
#define besRELEASE(X)    (pSt->ReleaseVariable(pSt->pEo->pMo,(X)))

  int (*SetRef)(pMemoryObject,pFixSizeMemoryObject *,pFixSizeMemoryObject *);
#define besSETREF(X,Y) (pSt->SetRef(St->pEo->pMo,(X),(Y))

  char * (*ConfigData)(ptConfigTree p, char *s);
#define besCONFIG(X) (pSt->ConfigData(pSt->pEo->pConfig,(X)))

  CFT_NODE (*FindNode)(ptConfigTree, CFT_NODE, char *);
#define besCONFIGFINDNODE(X,Y,Z) (pSt->FindNode((X),(Y),(Z)))

  int (*GetEx)(ptConfigTree, char *, CFT_NODE *, char **, long *, double *, int *);
#define besCONFIGEX(CT,CS,NS,CSS,LS,DS,IS) (pSt->GetEx((CT),(CS),(NS),(CSS),(LS),(DS),(IS)))

  CFT_NODE (*EnumFirst)(ptConfigTree, CFT_NODE);
#define besCONFIGENUMFIRST(X,Y) (pSt->EnumFirst((X),(Y)))

  CFT_NODE (*EnumNext)(ptConfigTree, CFT_NODE);
#define besCONFIGENUMNEXT(X,Y) (pSt->EnumNext((X),(Y)))

  char * (*GetKey)(ptConfigTree, CFT_NODE );
#define besCONFIGGETKEY(X,Y) (pSt->GetKey((X),(Y)))

  SymbolTable (*NewSymbolTable)(void* (*memory_allocating_function)(size_t,void *),
                                void *pMemorySegment);
  void (*FreeSymbolTable)(SymbolTable table,
                          void (*memory_releasing_function)(void *,void *),
                          void *pMemorySegment);
  void (*TraverseSymbolTable)(SymbolTable table,
                              void (*call_back_function)(char *SymbolName, void *SymbolValue, void *f),
                              void *f);
  void **(*LookupSymbol)(char *s,
                         SymbolTable hashtable,
                         int insert,
                         void* (*memory_allocating_function)(size_t, void *),
                         void (*memory_releasing_function)(void *, void *),
                         void *pMemorySegment);

  int (*DeleteSymbol)(char *s,
                      SymbolTable hashtable,
                      void (*memory_releasing_function)(void *, void *),
                      void *pMemorySegment);

#define besNEWSYMBOLTABLE() \
  (pSt->NewSymbolTable(pSt->Alloc,pSt->pEo->pMemorySegment))

#define besFREESYMBOLTABLE(X) \
  (pSt->FreeSymbolTable((X),pSt->Free,pSt->pEo->pMemorySegment))

#define besTRAVERSESYMBOLTABLE(X,Y,Z) \
  (pSt->TraverseSymbolTable((X),(Y),(Z)))

#define besLOOKUPSYMBOL(X,Y,Z) \
  (pSt->LookupSymbol((X),(Y),(Z),pSt->Alloc,\
                                 pSt->Free,\
                                 pSt->pEo->pMemorySegment))

#define besDeleteSymbol(X,Y) \
  (pSt->DeleteSymbol((X),(Y),pSt->Free,pSt->pEo->pMemorySegment))

  void *(*LoadLibrary)(char *s); 
  void (*FreeLibrary)(void *);     
  void *(*GetFunctionByName)(void *pLibrary,char *pszFunctionName);

#define besLOADLIBRARY(X) (pSt->LoadLibrary( (X) ))

#define besFREELIBRARY(X) (pSt->FreeLibrary( (X) ))

#define besGETFUNCTIONBYNAME(LIB,FUN) (pSt->GetFunctionByName((LIB),(FUN)))

  FILE *(*fopen)(char *pszFileName,char *pszOpenMode);
  void (*fclose)(FILE *fp);
  long (*size)(char *pszFileName);
  long (*time_accessed)(char *pszFileName);
  long (*time_modified)(char *pszFileName);
  long (*time_created)(char *pszFileName);
  int (*isdir)(char *pszFileName);
  int (*isreg)(char *pszFileName);
  int (*exists)(char *pszFileName);
  int (*truncate)(FILE *,long lNewFileSize);
  int (*fgetc)(FILE *);
  int (*fread)(char *, int, int, FILE *);
  int (*fwrite)(char *, int, int, FILE *);
  void (*setmode)(FILE *, int);
  void (*binmode)(FILE *);
  void (*textmode)(FILE *);
  int (*ferror)(FILE *);
  int (*fputc)(int c, FILE*fp);
  int (*flock)(FILE *fp,int iLockType);
  int (*lock)(FILE *fp,int iLockType,long lStart,long lLength);
  int (*feof)(FILE *fp);
  int (*mkdir)(char *pszDirectoryName);
  int (*rmdir)(char *pszDirectoryName);
  int (*remove)(char *pszFileName);
  int (*deltree)(char *pszDirectoryName);
  int (*MakeDirectory)(char *pszDirectoryName);
  DIR *(*opendir)(char *pszDirectoryName,tDIR *pDirectory);
  struct dirent *(*readdir)(DIR *pDirectory);
  void (*closedir)(DIR *pDirectory);

#define besFOPEN(FN,OM)       (pSt->fopen( (FN),(OM) ))

#define besFCLOSE(FP)         (pSt->fclose( (FP) ))

#define besSIZE(FN)           (pSt->size( (FN) ))

#define besTIME_ACCESSED(FN)  (pSt->time_accessed( (FN) ))

#define besTIME_MODIFIED(FN)  (pSt->time_modified( (FN) ))

#define besTIME_CREATED(FN)   (pSt->time_created( (FN) ))

#define besISDIR(FN)          (pSt->isdir( (FN) ))

#define besISREG(FN)          (pSt->isreg( (FN) ))

#define besEXISTS(FN)         (pSt->exists( (FN) ))

#define besTRUNCATE(FN,NS)    (pSt->truncate( (FN),(NS) ))

#define besFGETC(FP)          (pSt->fgetc( (FP) ))

#define besFREAD(S,X,Y,FP)    (pSt->fread( (S),(X),(Y),(FP) ))

#define besFWRITE(S,X,Y,FP)   (pSt->fwrite( (S),(X),(Y),(FP) ))

#define besSETMODE(FP,M)      (pSt->setmode( (FP),(M) ))

#define besBINMODE(FP)        (pSt->binmode( (FP) ))

#define besTEXTMODE(FP)       (pSt->textmode( (FP) ))

#define besFERROR(FP)         (pSt->ferror( (FP) ))

#define besFPUTC(C,FP)        (pSt->fputc( (C),(FP) ))

#define besFLOCK(FP)          (pSt->flock( (FP) ))

#define besLOCK(FP,LT,LS,LE)  (pSt->lock( (FP),(LT),(LS),(LE) ))

#define besFEOF(FP)           (pSt->feof( (FP) ))

#define besMKDIR(DN)          (pSt->mkdir( (DN) ))

#define besRMDIR(DN)          (pSt->rmdir( (DN) ))

#define besREMOVE(FN)         (pSt->remove( (FN) ))

#define besDELTREE(DN)        (pSt->deltree( (DN) ))

#define besMAKEDIRECTORY(DN)  (pSt->MakeDirectory( (DN) ))

#define besOPENDIR(DN,DP)     (pSt->opendir( (DN),(DP) ))

#define besREADDIR(DP)        (pSt->readdir( (DP) ))

#define besCLOSEDIR(DP)       (pSt->closedir( (DP) ))

  long (*GetOption)(pExecuteObject,char*);
  int (*SetOption)(pExecuteObject,char *,long);
  int (*ResetOption)(pExecuteObject,char *);

#define besOPTION(x)          (pSt->GetOption(pSt->pEo,(x)))

#define besSETOPTION(x,y)     (pSt->SetOption(pSt->pEo,(x),(y)))

#define besRESETOPTION(x)     (pSt->ResetOption(pSt->pEo,(x)))

  pFixSizeMemoryObject (*Convert2String)(pExecuteObject, pFixSizeMemoryObject, pMortalList);
  pFixSizeMemoryObject (*Convert2Long)(pExecuteObject, pFixSizeMemoryObject, pMortalList);
  pFixSizeMemoryObject (*Convert2Double)(pExecuteObject, pFixSizeMemoryObject, pMortalList);
  int                  (*IsStringInteger)(pFixSizeMemoryObject);

  double               (*GetDoubleValue)(pExecuteObject, pFixSizeMemoryObject);
  long                 (*GetLongValue)  (pExecuteObject, pFixSizeMemoryObject);

#define besCONVERT2STRING(x)  (pSt->Convert2String(pSt->pEo,(x),pSt->pEo->pGlobalMortalList))

#define besCONVERT2LONG(x)    (pSt->Convert2Long(pSt->pEo,(x),pSt->pEo->pGlobalMortalList))

#define besGETLONGVALUE(x)    (pSt->GetLongValue(pSt->pEo,(x)))

#define besCONVERT2DOUBLE(x)  (pSt->Convert2Double(pSt->pEo,(x),pSt->pEo->pGlobalMortalList))

#define besGETDOUBLEVALUE(x)    (pSt->GetDoubleValue(pSt->pEo,(x)))

#define besISSTRINGINTEGER(x) (pSt->IsStringInteger(x))

#define besCONVERT2ZCHAR(x,y)   (y) = besALLOC(STRLEN( (x) )+1);\
                                if( (y) == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                                memcpy((y),STRINGVALUE( (x) ),STRLEN( (x) ));\
                                (y)[STRLEN( (x) )] = (char)0;

  int (*InitModuleInterface)(pExecuteObject, int);
  int (*LoadModule)(pExecuteObject, char *, pModule **);
  int (*GetModuleFunctionByName)(pExecuteObject, char *, char *, void **, pModule **);
  int (*UnloadAllModules)(pExecuteObject);
  int (*UnloadModule)(pExecuteObject, char *);

#define besREINITINTERFACE (pSt->InitModuleInterface(pSt->pEo,1)

#define besLOADMODULE(x,y) (pSt->LoadModule(pSt->pEo,(x),(y))

#define besGETMODULEFUNCTIONBYNAME(x,y,z,w) (pSt->GetModuleFunctionByName(pSt->pEo,(x),(y),(z),(w))

#define besUNLOADALLMODULES (pSt->UnloadAllModules(pSt->pEo))

#define besUNLOADMODULE(x) (pSt->UnloadModule(pSt->pEo,(x))

  void (*sleep)(long);
  int  (*curdir)(char *, unsigned long);
  int  (*chdir)(char *);
  int  (*chown)(char *, char *);
  int  (*SetCreateTime)(char *, long);
  int  (*SetModifyTime)(char *, long);
  int  (*SetAccessTime)(char *, long);

#define besSLEEP(x)           (pSt->sleep(x))

#define besCURDIR(x,y)        (pSt->curdir((x),(y)))

#define besCHDIR(x)           (pSt->chdir(x))

#define besCHOWN(x,y)         (pSt->chown((x),(y)))

#define besSETCREATETIME(x,y) (pSt->SetCreateTime((x),(y)))

#define besSETMODIFYTIME(x,y) (pSt->SetModifyTime((x),(y)))

#define besSETACCESSTIME(x,y) (pSt->SetAccessTime((x),(y)))

  int (*GetHostName)(char *, long);
  int (*GetHost)(char *, struct hostent *);
  int (*TcpConnect)(SOCKET *, char *);
  int (*TcpSend)(SOCKET, char *, long, int);
  int (*TcpRecv)(SOCKET, char *, long, int);
  int (*TcpClose)(SOCKET);

#define besGETHOSTNAME(X,Y) (pSt->GetHostName((X),(Y)))

#define besGETHOST(X,Y) (pSt->GetHost((X),((Y)))

#define besTCPCONNECT(X,Y) (pSt->TcpConnect((X),(Y)))

#define besTCPSEND(X,Y,Z) (pSt->TcpSend((X),(Y),(Z)))

#define besTCPRECV(X,Y,Z) (pSt->TcpRecv((X),(Y),(Z)))

#define besTCPCLOSE(Y) (pSt->TcpClose((X))

  int (*KillProc)(long);
#define besKILLPROC(X) (pSt->KillProc((X))

  int (*GetOwner)(char *, char *, long);
#define besGETOWNER(X,Y,Z) (pSt->GetOwner((X),(Y),(Z));


  char *(*Crypt)(char *, char *, char *);
#define besCRYPT(X,Y,Z) (pSt->Crypt((X),(Y),(Z)))

  void (*MD5Init)(MD5_CTX *context);
#define besMD5INIT(C) (pSt->MD5Init((C)))

  void (*MD5Update)(MD5_CTX *context, unsigned char *input, unsigned int inputLen);
#define besMD5UPDATE(C,I,L) (pSt->MD5Update((C),(I),(L)))

  void (*MD5Final)(unsigned char digest[16], MD5_CTX *context);
#define besMD5FINAL(D,C) (pSt->MD5Final((D),(C)))

  long (*CreateProcess)(char *);
#define besCREATEPROCESS(X) (pSt->CreateProcess(X))

  int (*CopyCommandTable)(pExecuteObject);
#define besCOPYCOMMANDTABLE (pSt->CopyCommandTable(pSt->pEo))

  long (*GetCommandByName)(pExecuteObject, char *, long);
#define besGETCOMMANDBYNAME(X,Y) (pSt->GetCommandByName(pSt->pEo,(X),(Y)))

  pFixSizeMemoryObject (*DupMortalize)(pMemoryObject,pFixSizeMemoryObject,pMortalList,int *);
  pFixSizeMemoryObject (*Evaluate)(pExecuteObject,unsigned long,pMortalList,int *,int);
  pFixSizeMemoryObject *(*LeftValue)(pExecuteObject,unsigned long,pMortalList,int *,int);
  void (*Immortalize)(pFixSizeMemoryObject,pMortalList);
  void (*ReleaseMortals)(pMemoryObject,pMortalList pMortal);

#define besEVALUATEEXPRESSION(x) (pSt->DupMortalize(pEo->pMo,\
                                            pSt->Evaluate(pEo,x,_pThisCommandMortals,&iErrorCode,0),\
                                            _pThisCommandMortals,\
                                            &iErrorCode))

#define _besEVALUATEEXPRESSION(x) (pSt->Evaluate(pEo,x,_pThisCommandMortals,&iErrorCode,0))

#define _besEVALUATEEXPRESSION_A(x) (pSt->Evaluate(pEo,x,_pThisCommandMortals,&iErrorCode,1))

#define besEVALUATELEFTVALUE(x) (pSt->LeftValue(pEo,x,_pThisCommandMortals,&iErrorCode,0))

#define besEVALUATELEFTVALUE_A(x) (pSt->LeftValue(pEo,x,_pThisCommandMortals,&iErrorCode,1))

#define besIMMORTALIZE(x) (pSt->Immortalize(x,_pThisCommandMortals))

  int (*Dereference)(unsigned long, pFixSizeMemoryObject *);

#define besDEREFERENCE(X) if( pSt->Dereference(pSt->pEo->pMo->maxderef,&(X)) )\
                            return COMMAND_ERROR_CIRCULAR;

  unsigned long (*match_index)(char ch);
#define besMatchIndex(X) (pSt->match_index(X))

  void (*match_InitSets)(pMatchSets pMS);
#define besMatchIniSets(X) (pSt->matchInitSets(X))

  void (*match_ModifySet)(pMatchSets pMS,
                          char JokerCharacter,
                          int nChars,
                          unsigned char *pch,
                          int fAction);
#define besMatchModifySet(X,Y,Z,W,Q) (pSt->match_ModifySet((X),(Y),(Z),(W),(Q)))

  int (*match_match)(char *pszPattern,
                     unsigned long cbPattern,
                     char *pszString,
                     unsigned long cbString,
                     char **ParameterArray,
                     unsigned long *pcbParameterArray,
                     char *pszBuffer,
                     int cArraySize,
                     int cbBufferSize,
                     int fCase,
                     pMatchSets pThisMatchSets,
                     int *iResult);
#define besMatchMatch(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11,P12)\
(pSt->match_match((P1),(P2),(P3),(P4),(P5),(P6),(P7),(P8),(P9),(P10),(P11),(P12)))

  int (*match_count)(char *pszPattern,unsigned long cbPattern);
#define besMatchCount(X,Y) (pSt->match_count((X),(Y)))

  int (*match_parameter)(char *pszFormat,
                         unsigned long cbFormat,
                         char **ParameterArray,
                         unsigned long *pcbParameterArray,
                         char *pszBuffer,
                         int cArraySize,
                         unsigned long *pcbBufferSize);
#define besMatchParameter(P1,P2,P3,P4,P5,P6,P7)\
(pSt->match_parameter((P1),(P2),(P3),(P4),(P5),(P6),(P7)))

  int (*match_size)(char *pszFormat,
                    unsigned long cbFormat,
                    unsigned long *pcbParameterArray,
                    int cArraySize,
                    int *cbBufferSize);
#define besMatchSize(P1,P2,P3,P4,P5)\
(pSt->match_size((P1),(P2),(P3),(P4),(P5)))





  int (*thread_CreateThread)(PTHREADHANDLE pThread,
                      void *pStartFunction,
                      void *pThreadParameter);
#define besCreateThread(X,Y,Z) (pSt->thread_CreateThread((X),(Y),(Z)))

  void (*thread_ExitThread)();
#define besExitThread() (pSt->thread_ExitThread())

  void (*thread_InitMutex)(PMUTEX pMutex);
#define besInitMutex(X) (pSt->thread_InitMutex(X))

  void (*thread_FinishMutex)(PMUTEX pMutex);
#define besFinishMutex(X) (pSt->thread_FinishMutex(X))

  void (*thread_LockMutex)(PMUTEX pMutex);
#define besLockMutex(X) (pSt->thread_LockMutex(X))

  void (*thread_UnlockMutex)(PMUTEX pMutex);
#define besUnlockMutex(X) (pSt->thread_UnlockMutex(X))

  void (*shared_InitLock)(PSHAREDLOCK p);
#define besInitSharedLock(X) (pSt->shared_InitLock(X))

  void (*shared_FinishLock)(PSHAREDLOCK p);
#define besFinishSharedLock(X) (pSt->shared_FinishLock(X))

  void (*shared_LockRead)(PSHAREDLOCK p);
#define besLockSharedRead(X) (pSt->shared_LockRead(X))

  void (*shared_LockWrite)(PSHAREDLOCK p);
#define besLockSharedWrite(X) (pSt->shared_LockWrite(X))

  void (*shared_UnlockRead)(PSHAREDLOCK p);
#define besUnlockSharedRead(X) (pSt->shared_UnlockRead(X))

  void (*shared_UnlockWrite)(PSHAREDLOCK p);
#define besUnlockSharedWrite(X) (pSt->shared_UnlockWrite(X))







  pSbProgram (*scriba_new)(void * (*maf)(size_t), void (*mrf)(void *));
#define besScribaNew(F0,F1) (pSt->scriba_New((F0),(F1)))

  void (*scriba_destroy)(pSbProgram pProgram);
#define besScribaDestroy(F0) (pSt->scriba_Destroy((F0)))

  pSbData (*scriba_NewSbData)(pSbProgram pProgram);
#define besScribaNewSbData(F0) (pSt->scriba_NewSbData((F0)))

  pSbData (*scriba_NewSbLong)(pSbProgram pProgram, long lInitValue);
#define besScribaNewSbLong(F0,F1) (pSt->scriba_NewSbLong((F0),(F1)))

  pSbData (*scriba_NewSbDouble)(pSbProgram pProgram, double dInitValue);
#define besScribaNewSbDouble(F0,F1) (pSt->scriba_NewSbDouble((F0),(F1)))

  pSbData (*scriba_NewSbUndef)(pSbProgram pProgram);
#define besScribaNewSbUndef(F0) (pSt->scriba_NewSbUndef((F0)))

  pSbData (*scriba_NewSbString)(pSbProgram pProgram, char *pszInitValue);
#define besScribaNewSbString(F0,F1) (pSt->scriba_NewSbString((F0),(F1)))

  pSbData (*scriba_NewSbBytes)(pSbProgram pProgram, unsigned long len, unsigned char *pszInitValue);
#define besScribaNewSbBytes(F0,F1,F2) (pSt->scriba_NewSbBytes((F0),(F1),(F2)))

  void (*scriba_DestroySbData)(pSbProgram pProgram, pSbData p);
#define besScribaDestroySbData(F0,F1) (pSt->scriba_DestroySbData((F0),(F1)))

  void (*scriba_PurgeReaderMemory)(pSbProgram pProgram);
#define besScribaPurgeReaderMemory(F0) (pSt->scriba_PurgeReaderMemory((F0)))

  void (*scriba_PurgeLexerMemory)(pSbProgram pProgram);
#define besScribaPurgeLexerMemory(F0) (pSt->scriba_PurgeLexerMemory((F0)))

  void (*scriba_PurgeSyntaxerMemory)(pSbProgram pProgram);
#define besScribaPurgeSyntaxerMemory(F0) (pSt->scriba_PurgeSyntaxerMemory((F0)))

  void (*scriba_PurgeBuilderMemory)(pSbProgram pProgram);
#define besScribaPurgeBuilderMemory(F0) (pSt->scriba_PurgeBuilderMemory((F0)))

  void (*scriba_PurgeExecuteMemory)(pSbProgram pProgram);
#define besScribaPurgeExecuteMemory(F0) (pSt->scriba_PurgeExecuteMemory((F0)))

  int (*scriba_SetFileName)(pSbProgram pProgram, char *pszFileName);
#define besScribaSetFileName(F0,F1) (pSt->scriba_SetFileName((F0),(F1)))

  int (*scriba_LoadConfiguration)(pSbProgram pProgram, char *pszForcedConfigurationFileName);
#define besScribaLoadConfiguration(F0,F1) (pSt->scriba_LoadConfiguration((F0),(F1)))

  int (*scriba_InheritConfiguration)(pSbProgram pProgram, pSbProgram pFrom);
#define besScribaInheritConfiguration(F0,F1) (pSt->scriba_InheritConfiguration((F0),(F1)))

  void (*scriba_SetCgiFlag)(pSbProgram pProgram);
#define besScribaSetCgiFlag(F0) (pSt->scriba_SetCgiFlag((F0)))

  void (*scriba_SetReportFunction)(pSbProgram pProgram, void *fpReportFunction);
#define besScribaSetReportFunction(F0,F1) (pSt->scriba_SetReportFunction((F0),(F1)))

  void (*scriba_SetReportPointer)(pSbProgram pProgram, void *pReportPointer);
#define besScribaSetReportPointer(F0,F1) (pSt->scriba_SetReportPointer((F0),(F1)))

  void (*scriba_SetStdin)(pSbProgram pProgram, void *fpStdinFunction);
#define besScribaSetStdin(F0,F1) (pSt->scriba_SetStdin((F0),(F1)))

  void (*scriba_SetStdout)(pSbProgram pProgram, void *fpStdoutFunction);
#define besScribaSetStdout(F0,F1) (pSt->scriba_SetStdout((F0),(F1)))

  void (*scriba_SetEmbedPointer)(pSbProgram pProgram, void *pEmbedder);
#define besScribaSetEmbedPointer(F0,F1) (pSt->scriba_SetEmbedPointer((F0),(F1)))

  void (*scriba_SetEnvironment)(pSbProgram pProgram, void *fpEnvirFunction);
#define besScribaSetEnvironment(F0,F1) (pSt->scriba_SetEnvironment((F0),(F1)))

  int (*scriba_LoadBinaryProgram)(pSbProgram pProgram);
#define besScribaLoadBinaryProgram(F0) (pSt->scriba_LoadBinaryProgram((F0)))

  int (*scriba_InheritBinaryProgram)(pSbProgram pProgram, pSbProgram pFrom);
#define besScribaInheritBinaryProgram(F0,F1) (pSt->scriba_InheritBinaryProgram((F0),(F1)))

  int (*scriba_ReadSource)(pSbProgram pProgram);
#define besScribaReadSource(F0) (pSt->scriba_ReadSource((F0)))

  int (*scriba_DoLexicalAnalysis)(pSbProgram pProgram);
#define besScribaDoLexicalAnalysis(F0) (pSt->scriba_DoLexicalAnalysis((F0)))

  int (*scriba_DoSyntaxAnalysis)(pSbProgram pProgram);
#define besScribaDoSyntaxAnalysis(F0) (pSt->scriba_DoSyntaxAnalysis((F0)))

  int (*scriba_BuildCode)(pSbProgram pProgram);
#define besScribaBuildCode(F0) (pSt->scriba_BuildCode((F0)))

  int (*scriba_IsFileBinaryFormat)(pSbProgram pProgram);
#define besScribaIsFileBinaryFormat(F0) (pSt->scriba_IsFileBinaryFormat((F0)))

  int (*scriba_GetCacheFileName)(pSbProgram pProgram);
#define besScribaGetCacheFileName(F0) (pSt->scriba_GetCacheFileName((F0)))

  int (*scriba_UseCacheFile)(pSbProgram pProgram);
#define besScribaUseCacheFile(F0) (pSt->scriba_UseCacheFile((F0)))

  int (*scriba_SaveCacheFile)(pSbProgram pProgram);
#define besScribaSaveCacheFile(F0) (pSt->scriba_SaveCacheFile((F0)))

  int (*scriba_RunExternalPreprocessor)(pSbProgram pProgram, char **ppszArgPreprocessor);
#define besScribaRunExternalPreprocessor(F0,F1) (pSt->scriba_RunExternalPreprocessor((F0),(F1)))

  int (*scriba_SaveCode)(pSbProgram pProgram, char *pszCodeFileName);
#define besScribaSaveCode(F0,F1) (pSt->scriba_SaveCode((F0),(F1)))

  void (*scriba_SaveCCode)(pSbProgram pProgram, char *pszCodeFileName);
#define besScribaSaveCCode(F0,F1) (pSt->scriba_SaveCCode((F0),(F1)))

  int (*scriba_LoadSourceProgram)(pSbProgram pProgram);
#define besScribaLoadSourceProgram(F0) (pSt->scriba_LoadSourceProgram((F0)))

  int (*scriba_Run)(pSbProgram pProgram, char *pszCommandLineArgument);
#define besScribaRun(F0,F1) (pSt->scriba_Run((F0),(F1)))

  int (*scriba_NoRun)(pSbProgram pProgram);
#define besScribaNoRun(F0) (pSt->scriba_NoRun((F0)))

  void (*scriba_ResetVariables)(pSbProgram pProgram);
#define besScribaResetVariables(F0) (pSt->scriba_ResetVariables((F0)))

  int (*scriba_Call)(pSbProgram pProgram, unsigned long lEntryNode);
#define besScribaCall(F0,F1) (pSt->scriba_Call((F0),(F1)))

  int (*scriba_CallArg)(pSbProgram pProgram, unsigned long lEntryNode, char *pszFormat, ...);
#define besScribaCallArg(F0,F1,F2,F3) (pSt->scriba_CallArg((F0),(F1),(F2),(F3)))

  void (*scriba_DestroySbArgs)(pSbProgram pProgram, pSbData Args, unsigned long cArgs);
#define besScribaDestroySbArgs(F0,F1,F2) (pSt->scriba_DestroySbArgs((F0),(F1),(F2)))

  pSbData (*scriba_NewSbArgs)(pSbProgram pProgram, char *pszFormat, ...);
#define besScribaNewSbArgs(F0,F1,F2) (pSt->scriba_NewSbArgs((F0),(F1),(F2)))

  int (*scriba_CallArgEx)(pSbProgram pProgram, unsigned long lEntryNode, pSbData ReturnValue, unsigned long cArgs, pSbData Args);
#define besScribaCallArgEx(F0,F1,F2,F3,F4) (pSt->scriba_CallArgEx((F0),(F1),(F2),(F3),(F4)))

  long (*scriba_LookupFunctionByName)(pSbProgram pProgram, char *pszFunctionName);
#define besScribaLookupFunctionByName(F0,F1) (pSt->scriba_LookupFunctionByName((F0),(F1)))

  long (*scriba_LookupVariableByName)(pSbProgram pProgram, char *pszVariableName);
#define besScribaLookupVariableByName(F0,F1) (pSt->scriba_LookupVariableByName((F0),(F1)))

  long (*scriba_GetVariableType)(pSbProgram pProgram, long lSerial);
#define besScribaGetVariableType(F0,F1) (pSt->scriba_GetVariableType((F0),(F1)))

  int (*scriba_GetVariable)(pSbProgram pProgram, long lSerial, pSbData *pVariable);
#define besScribaGetVariable(F0,F1,F2) (pSt->scriba_GetVariable((F0),(F1),(F2)))

  int (*scriba_SetVariable)(pSbProgram pProgram, long lSerial, int type, long lSetValue, double dSetValue, char *pszSetValue, unsigned long size);
#define besScribaSetVariable(F0,F1,F2,F3,F4,F5,F6) (pSt->scriba_SetVariable((F0),(F1),(F2),(F3),(F4),(F5),(F6)))

  int (*log_state)(ptLogger pLOG);
#define besLogState(X) (pSt->log_state(X))


  int (*log_init)(ptLogger pLOG,
                  void *(*memory_allocating_function)(size_t, void *),
                  void (*memory_releasing_function)(void *, void *),
                  void *pMemorySegment,
                  char *pszLogFileName,
                  int iLogType);
#define besLogInit(F0,F1,F2,F3,F4,F5) (pSt->log_init((F0),(F1),(F2),(F3),(F4),(F5)))

  int (*log_printf)(ptLogger pLOG,
                    char *pszFormat,
                    ...);
#define besLogPrintf pSt->log_printf

  int (*log_shutdown)(ptLogger pLOG);
#define besLogShutdown(X) (pSt->log_shutdown(X))

  unsigned long (*handle_GetHandle)(void **pHandle,
                                    void *pMEM,
                                    void *pointer);
#define besHandleGetHandle(X,Y) (pSt->handle_GetHandle(&(X),pSt->pEo->pMemorySegment,(Y)))

  void *(*handle_GetPointer)(void **pHandle,
                             unsigned long handle);
#define besHandleGetPointer(X,Y) (pSt->handle_GetPointer( &(X),(Y)))

  void (*handle_FreeHandle)(void **pHandle,
                            unsigned long handle);
#define besHandleFreeHandle(X,Y) (pSt->handle_FreeHandle( &(X), (Y)))

  void (*handle_DestroyHandleArray)(void **pHandle,
                                    void *pMEM);
#define besHandleDestroyHandleArray(X) (pSt->handle_DestroyHandleArray( &(X),pSt->pEo->pMemorySegment))

  int (*basext_GetArgsF)(pSupportTable pSt,
                      pFixSizeMemoryObject pParameters,
                      char *pszFormat,
                      ...
                      );

#define besGETARGS pSt->basext_GetArgsF(pSt,pParameters,
#define besGETARGE );

#define besARGUMENTS(X) int iError; iError = pSt->basext_GetArgsF(pSt,pParameters,(X),
#define besARGEND ); if( iError )return iError;

  void *(*InitSegment)(void * (*maf)(size_t),
                            void   (*mrf)(void *));
  long (*SegmentLimit)(void *p,unsigned long NewMaxSize);
  void (*FreeSegment)(void *p);
  void (*FinishSegment)(void *p);

#define besINIT_SEGMENT(MAF,MRF) (pSt->InitSegment(MAF,MRF))

#define besSEGMENT_LIMIT(PMS,L) (pSt->SegmentLimit(PMS,L))

#define besFREE_SEGMENT(PMS) (pSt->FreeSegment(PMS))

#define besFINISH_SEGMENT(PMS) (pSt->FinishSegment(PMS))

  } SupportTable
#ifndef PSUPPORTTABLE
  , *pSupportTable
#endif
  ;








#define besFUNCTION(X) DLL_EXPORT int X(pSupportTable pSt, \
                          void **ppModuleInternal, \
                          pFixSizeMemoryObject pParameters, \
                          pFixSizeMemoryObject *pReturnValue){\
                          pExecuteObject pEo=NULL;

#define besASSERT_FUNCTION if( pSt == NULL || pSt->pEo == NULL || pSt->pEo->pST != pSt ){\
                              pEo = (pExecuteObject)pSt;\
                              pEo->ErrorCode = COMMAND_ERROR_BAD_CALL;\
                              return 0;\
                              }

#define besCOMMAND(x) DLL_EXPORT int x(pExecuteObject pEo,void **ppModuleInternal){\
                        MortalList _ThisCommandMortals=NULL;\
                        pMortalList _pThisCommandMortals = &_ThisCommandMortals;\
                        unsigned long _ActualNode= pEo && pEo->pST && pEo->pST->pEo == pEo ? PROGRAMCOUNTER : 0;\
                        int iErrorCode;\
                        pSupportTable pSt= pEo ? pEo->pST : NULL;

#define besASSERT_COMMAND if( pEo == NULL || pEo->pST == NULL || pEo->pST->pEo != pEo )return COMMAND_ERROR_BAD_CALL;


#define besEND_COMMAND goto _FunctionFinishLabel;\
            _FunctionFinishLabel: \
            pSt->ReleaseMortals(pEo->pMo,&_ThisCommandMortals);\
            iErrorCode = 0;\
            FINISH;\
            return 0;\
            }

#define besARGNR       (pParameters ? pParameters->ArrayHighLimit : 0)

#define besARGUMENT(X) ((X) <= besARGNR ? pParameters->Value.aValue[(X)-1]: NULL)

#define besPARAMETERLIST (pEo->OperatorNode)

#define besLEFTVALUE(X,Y) do{if( TYPE((X)) == VTYPE_REF ){\
                               __refcount_ = pSt->pEo->pMo->maxderef;\
                               Y=(X)->Value.aValue;\
                               while( *(Y) && TYPE(*(Y))== VTYPE_REF ){\
                                 (Y=(*(Y))->Value.aValue);\
                                 if( ! __refcount_ -- ){\
                                   return COMMAND_ERROR_CIRCULAR;\
                                   }\
                                 }\
                               }else Y=NULL;}while(0)


#define MODULE_VERSIONER   "versmodu"

#define MODULE_INITIALIZER "bootmodu"


#define MODULE_FINALIZER   "finimodu"

#define MODULE_SHUTDOWN    "shutmodu"


#define MODULE_AUTOLOADER  "automodu"




#define MODULE_KEEPER      "keepmodu"


#define MODULE_ERRMSG      "emsgmodu"

#define besVERSION_NEGOTIATE int DLL_EXPORT versmodu(int Version, char *pszVariation, void **ppModuleInternal){

#define besSUB_START  besFUNCTION(bootmodu)

#define besSUB_FINISH besFUNCTION(finimodu)

#define besSUB_ERRMSG char DLL_EXPORT * emsgmodu(pSupportTable pSt, \
                                                 void **ppModuleInternal, \
                                                 int iError){
#if STATIC_LINK
#define besSUB_PROCESS_START static int _init(){
#else
#define besSUB_PROCESS_START int _init(){
#endif

#if STATIC_LINK
#define besSUB_PROCESS_FINISH static int _fini(){
#else
#define besSUB_PROCESS_FINISH int _fini(){
#endif

#define besSUB_KEEP int DLL_EXPORT keepmodu(){

#define besSUB_SHUTDOWN besFUNCTION(shutmodu)

#define besSUB_AUTO int DLL_EXPORT automodu(pSupportTable pSt, \
                                                     void **ppModuleInternal, \
                                                     char *pszFunction, \
                                                     void **ppFunction){
#define besEND return 0;}

#define besRETURNVALUE (*pReturnValue)

#define besMODULEPOINTER (*ppModuleInternal)

#define besALLOC_RETURN_STRING(x) do{besRETURNVALUE  = besNEWMORTALSTRING(x);\
                                     if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                                    }while(0);

#define besALLOC_RETURN_POINTER do{besRETURNVALUE  = besNEWMORTALSTRING(sizeof( void *));\
                                     if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                                    }while(0);

#define besALLOC_RETURN_LONG do{besRETURNVALUE = besNEWMORTALLONG;\
                                     if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                                    }while(0);

#define besALLOC_RETURN_DOUBLE do{besRETURNVALUE = besNEWMORTALDOUBLE;\
                                  if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                                  }while(0);


#define besRETURN_STRING(x) do{if( NULL == (x) ){besRETURNVALUE = NULL; return COMMAND_ERROR_SUCCESS; }\
                               besRETURNVALUE  = besNEWMORTALSTRING(strlen(x));\
                               if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                               memcpy(STRINGVALUE(besRETURNVALUE),(x),STRLEN(besRETURNVALUE));\
                               return COMMAND_ERROR_SUCCESS;\
                              }while(0);

#define besSET_RETURN_STRING(x) do{if( NULL == (x) ){besRETURNVALUE = NULL;}else{\
                                     besRETURNVALUE  = besNEWMORTALSTRING((unsigned long)strlen(x));\
                                     if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                                     memcpy(STRINGVALUE(besRETURNVALUE),(x),STRLEN(besRETURNVALUE));\
                                     }\
                                   }while(0);


#define besRETURN_MEM(x,y)    do{besRETURNVALUE  = besNEWMORTALSTRING(y);\
                                 if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                                 memcpy(STRINGVALUE(besRETURNVALUE),(x),STRLEN(besRETURNVALUE));\
                                 return COMMAND_ERROR_SUCCESS;\
                                }while(0);

#define besRETURN_POINTER(x) do{if( NULL == (x) ){ besRETURNVALUE = NULL; return COMMAND_ERROR_SUCCESS; }\
                                besRETURNVALUE  = besNEWMORTALSTRING(sizeof( void *));\
                                if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                                memcpy(STRINGVALUE(besRETURNVALUE),&(x),sizeof( void *) );\
                                return COMMAND_ERROR_SUCCESS;\
                                }while(0);

#define besRETURN_LONG(X) do{besRETURNVALUE = besNEWMORTALLONG;\
                             if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                             LONGVALUE(besRETURNVALUE) = (X);\
                             return COMMAND_ERROR_SUCCESS;\
                             }while(0);

#define besRETURN_DOUBLE(X) do{besRETURNVALUE = besNEWMORTALDOUBLE;\
                               if( besRETURNVALUE  == NULL )return COMMAND_ERROR_MEMORY_LOW;\
                               DOUBLEVALUE(besRETURNVALUE) = (X);\
                               return COMMAND_ERROR_SUCCESS;\
                               }while(0);

#define besSETCOMMAND(X,Y) (pSt->pEo->pCommandFunction[(X)-START_CMD] = Y)

#define besGETCOMMAND(X)   (pSt->pEo->pCommandFunction[(X)-START_CMD])

#define INTERFACE_VERSION 11

#define besHOOK_FILE_ACCESS(X) (pSt->pEo->pHookers->HOOK_file_access(pSt->pEo,(X)))
#define besHOOK_FOPEN(X,Y) (pSt->pEo->pHookers->HOOK_fopen(pSt->pEo,(X),(Y)))
#define besHOOK_FCLOSE(X) (pSt->pEo->pHookers->HOOK_fclose(pSt->pEo,(X)))
#define besHOOK_SIZE(X) (pSt->pEo->pHookers->HOOK_size(pSt->pEo,(X)))
#define besHOOK_TIME_ACCESSED(X) (pSt->pEo->pHookers->HOOK_time_accessed(pSt->pEo,(X)))
#define besHOOK_TIME_MODIFIED(X) (pSt->pEo->pHookers->HOOK_time_modified(pSt->pEo,(X)))
#define besHOOK_TIME_CREATED(X) (pSt->pEo->pHookers->HOOK_time_created(pSt->pEo,(X)))
#define besHOOK_ISDIR(X) (pSt->pEo->pHookers->HOOK_isdir(pSt->pEo,(X)))
#define besHOOK_ISREG(X) (pSt->pEo->pHookers->HOOK_isreg(pSt->pEo,(X)))
#define besHOOK_EXISTS(X) (pSt->pEo->pHookers->HOOK_exists(pSt->pEo,(X)))
#define besHOOK_TRUNCATE(X,Y) (pSt->pEo->pHookers->HOOK_truncate(pSt->pEo,(X),(Y)))
#define besHOOK_FGETC(X) (pSt->pEo->pHookers->HOOK_fgetc(pSt->pEo,(X)))
#define besHOOK_FREAD(X,Y,Z,W) (pSt->pEo->pHookers->HOOK_fread(pSt->pEo,(X),(Y),(Z),(W)))
#define besHOOK_FWRITE(X,Y,Z,W) (pSt->pEo->pHookers->HOOK_fwrite(pSt->pEo,(X),(Y),(Z),(W)))
#define besHOOK_FERROR(X) (pSt->pEo->pHookers->HOOK_ferror(pSt->pEo,(X)))
#define besHOOK_PUTC(X,Y) (pSt->pEo->pHookers->HOOK_fputc(pSt->pEo,(X),(Y)))
#define besHOOK_FLOCK(X,Y) (pSt->pEo->pHookers->HOOK_flock(pSt->pEo,(X),(Y)))
#define besHOOK_LOCK(X,Y,Z,W) (pSt->pEo->pHookers->HOOK_lock(pSt->pEo,(X),(Y),(Z),(W)))
#define besHOOK_FEOF(X) (pSt->pEo->pHookers->HOOK_feof(pSt->pEo,(X)))
#define besHOOK_MKDIR(X) (pSt->pEo->pHookers->HOOK_mkdir(pSt->pEo,(X)))
#define besHOOK_RMDIR(X) (pSt->pEo->pHookers->HOOK_rmdir(pSt->pEo,(X)))
#define besHOOK_REMOVE(X) (pSt->pEo->pHookers->HOOK_remove(pSt->pEo,(X)))
#define besHOOK_DELTREE(X) (pSt->pEo->pHookers->HOOK_deltree(pSt->pEo,(X)))
#define besHOOK_MAKEDIRECTORY(X) (pSt->pEo->pHookers->HOOK_MakeDirectory(pSt->pEo,(X)))
#define besHOOK_OPENDIR(X,Y) (pSt->pEo->pHookers->HOOK_opendir(pSt->pEo,(X),(Y)))
#define besHOOK_READDIR(X) (pSt->pEo->pHookers->HOOK_readdir(pSt->pEo,(X)))
#define besHOOK_CLOSEDIR(X) (pSt->pEo->pHookers->HOOK_closedir(pSt->pEo,(X)))
#define besHOOK_SLEEP(X) (pSt->pEo->pHookers->HOOK_sleep(pSt->pEo,(X)))
#define besHOOK_CURDIR(X,Y) (pSt->pEo->pHookers->HOOK_curdir(pSt->pEo,(X),(Y)))
#define besHOOK_CHDIR(X) (pSt->pEo->pHookers->HOOK_chdir(pSt->pEo,(X)))
#define besHOOK_CHOWN(X,Y) (pSt->pEo->pHookers->HOOK_chown(pSt->pEo,(X),(Y)))
#define besHOOK_SETCREATETIME(X,Y) (pSt->pEo->pHookers->HOOK_SetCreateTime(pSt->pEo,(X),(Y)))
#define besHOOK_SETMODIFYTIME(X,Y) (pSt->pEo->pHookers->HOOK_SetModifyTime(pSt->pEo,(X),(Y)))
#define besHOOK_SETACCESSTIME(X,Y) (pSt->pEo->pHookers->HOOK_SetAccessTime(pSt->pEo,(X),(Y)))
#define besHOOK_GETHOSTNAME(X,Y) (pSt->pEo->pHookers->HOOK_GetHostName(pSt->pEo,(X),(Y)))
#define besHOOK_GETHOST(X,Y) (pSt->pEo->pHookers->HOOK_GetHost(pSt->pEo,(X),((Y)))
#define besHOOK_TCPCONNECT(X,Y) (pSt->pEo->pHookers->HOOK_TcpConnect(pSt->pEo,(X),(Y)))
#define besHOOK_TCPSEND(X,Y,Z) (pSt->pEo->pHookers->HOOK_TcpSend(pSt->pEo,(X),(Y),(Z)))
#define besHOOK_TCPRECV(X,Y,Z) (pSt->pEo->pHookers->HOOK_TcpRecv(pSt->pEo,(X),(Y),(Z)))
#define besHOOK_TCPCLOSE(Y) (pSt->pEo->pHookers->HOOK_TcpClose(pSt->pEo,(X))
#define besHOOK_KILLPROC(X) (pSt->pEo->pHookers->HOOK_KillProc(pSt->pEo,(X))
#define besHOOK_GETOWNER(X,Y,Z) (pSt->pEo->pHookers->HOOK_GetOwner(pSt->pEo,(X),(Y),(Z));
#define besHOOK_CREATEPROCESS(X) (pSt->pEo->pHookers->HOOK_CreateProcess(pSt->pEo,(X));
#define besHOOK_CALLSCRIBAFUNCTION(X,Y,Z,W) (pSt->pEo->pHookers->HOOK_CallScribaFunction(pSt->pEo,(X),(Y),(Z),(W)))
#define besSETHOOK(X,Y) (pSt->pEo->pHookers->HOOK_##X = Y)

#ifdef WIN32
#define besDLL_MAIN \
BOOL __declspec(dllexport) WINAPI DllMain(\
  HINSTANCE hinstDLL,\
  DWORD fdwReason,\
  LPVOID lpvReserved\
  ){\
  int _init(void);\
  int _fini(void);\
  switch( fdwReason ){\
    case DLL_PROCESS_ATTACH:\
      _init();\
      break;\
    case DLL_THREAD_ATTACH: return TRUE;\
    case DLL_THREAD_DETACH: return TRUE;\
    case DLL_PROCESS_DETACH:\
      _fini();\
      break;\
    }\
  return TRUE;\
  }
#else
#define besDLL_MAIN
#endif

#ifdef WIN32
#define INITLOCK WaitForSingleObject(mxInit,INFINITE);
#define INITUNLO ReleaseSemaphore(mxInit,1,NULL);
#else
#define INITLOCK pthread_mutex_lock(&mxInit);
#define INITUNLO pthread_mutex_unlock(&mxInit);
#endif

#define SUPPORT_MULTITHREAD static MUTEX mxThreadCounter,mxInit; static int iFirst;static long lThreadCounter;

#ifdef WIN32
#define INIT_MULTITHREAD      mxThreadCounter = CreateSemaphore(NULL,1,1,NULL);\
                              mxInit = CreateSemaphore(NULL,1,1,NULL);\
                              iFirst = 1;
#define GET_THREAD_COUNTER(X) WaitForSingleObject(mxThreadCounter,INFINITE);\
                              (X) = lThreadCounter;\
                              ReleaseSemaphore(mxThreadCounter,1,NULL);
#define INC_THREAD_COUNTER    WaitForSingleObject(mxThreadCounter,INFINITE);\
                              lThreadCounter++;\
                              ReleaseSemaphore(mxThreadCounter,1,NULL);
#define DEC_THREAD_COUNTER    WaitForSingleObject(mxThreadCounter,INFINITE);\
                              lThreadCounter--;\
                              ReleaseSemaphore(mxThreadCounter,1,NULL);
#define FINISH_MULTITHREAD    CloseHandle(mxThreadCounter);
#else
#define INIT_MULTITHREAD      pthread_mutex_init(&mxThreadCounter,NULL);\
                              pthread_mutex_init(&mxInit,NULL);\
                              iFirst = 1;
#define GET_THREAD_COUNTER(X) pthread_mutex_lock(&mxThreadCounter);\
                              (X) = lThreadCounter;\
                              pthread_mutex_unlock(&mxThreadCounter);
#define INC_THREAD_COUNTER    pthread_mutex_lock(&mxThreadCounter);\
                              lThreadCounter++;\
                              pthread_mutex_unlock(&mxThreadCounter);
#define DEC_THREAD_COUNTER    pthread_mutex_lock(&mxThreadCounter);\
                              lThreadCounter--;\
                              pthread_mutex_unlock(&mxThreadCounter);
#define FINISH_MULTITHREAD    pthread_mutex_destroy(&mxThreadCounter);
#endif

#define START_FUNCTION_TABLE(X) SLFST X[] ={
#define EXPORT_MODULE_FUNCTION(X) { #X , X },
#define END_FUNCTION_TABLE { NULL , NULL } };

/*FUNDEF*/

int basext_GetArgsF(pSupportTable pSt,
                    pFixSizeMemoryObject pParameters,
                    char *pszFormat,
                    ...);
/*FEDNUF*/
#ifdef __cplusplus
}
#endif
#endif
