/*
scriba.h
*/
#ifndef __SCRIBA_H__
#define __SCRIBA_H__ 1
#ifdef  __cplusplus
extern "C" {
#endif
#include "report.h"
#include "lexer.h"
#include "sym.h"
#include "expression.h"
#include "syntax.h"
#include "reader.h"
#include "myalloc.h"
#include "builder.h"
#include "memory.h"
#include "execute.h"
#include "buildnum.h"
#include "conftree.h"
#include "filesys.h"
#include "errcodes.h"
#ifdef _DEBUG
#include "testalloc.h"
#endif
#include "command.h"
#include "epreproc.h"
#include "ipreproc.h"
#include "uniqfnam.h"
#include "modumana.h"
#include "ipreproc.h"

typedef struct _SbProgram {
  void *pMEM;
  void * (*maf)(size_t);
  void   (*mrf)(void *);
  unsigned long fErrorFlags;
  char *pszFileName;
  char *pszCacheFileName;
  char *FirstUNIXline;

  void *fpStdouFunction;
  void *fpStdinFunction;
  void *fpEnvirFunction;
  void *pEmbedder;
  void *fpReportFunction;
  void *pReportPointer;
  pSupportTable pSTI;
  ExecuteObject *pEPo;

  tConfigTree   *pCONF;
  ReadObject    *pREAD;
  LexObject     *pLEX;
  eXobject      *pEX;
  BuildObject   *pBUILD;
  ExecuteObject *pEXE;
  PreprocObject *pPREP;
  } SbProgram, *pSbProgram;


typedef struct _SbData {
  unsigned char type;
  unsigned long size;
  union {
    double d;
    long   l;
    unsigned char *s;
    } v;
  } SbData, *pSbData;
#define SBT_UNDEF  0
#define SBT_DOUBLE 1
#define SBT_LONG   2
#define SBT_STRING 3
#define SBT_ZCHAR  4


#define scriba_GetType(Y,X)   ( (X).type )
#define scriba_GetLength(Y,X) ( (X).size )
#define scriba_GetString(Y,X) ( (X).v.s  )
#define scriba_GetLong(Y,X)   ( (X).v.l  )
#define scriba_GetDouble(Y,X) ( (X).v.d  )


#ifdef WIN32
#define CONFIG_ENVIR "Software\\ScriptBasic\\config"
#define CONFIG_FILE  "SCRIBA.INI"
#else
#define CONFIG_ENVIR "SCRIBACONF"
#define CONFIG_FILE  "/etc/scriba/basic.conf"
#endif

#if (defined(_WIN32) || defined(__MACOS__) || defined(WIN32))
# ifdef __cplusplus
#  define SCRIBA_MAIN_LIBSPEC extern "C" __declspec(dllexport) 
# else
#  define SCRIBA_MAIN_LIBSPEC __declspec(dllexport) 
# endif
#else
# ifdef __cplusplus
#  define SCRIBA_MAIN_LIBSPEC extern "C" 
# else
#  define SCRIBA_MAIN_LIBSPEC
# endif
#endif

/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC pSbProgram scriba_new(void * (*maf)(size_t),
                      void   (*mrf)(void *));
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_destroy(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC pSbData scriba_NewSbData(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_InitSbData(pSbProgram pProgram,
                         pSbData p);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_UndefSbData(pSbProgram pProgram,
                        pSbData p);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC pSbData scriba_NewSbLong(pSbProgram pProgram,
                         long lInitValue);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC pSbData scriba_NewSbDouble(pSbProgram pProgram,
                           double dInitValue);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC pSbData scriba_NewSbUndef(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC pSbData scriba_NewSbString(pSbProgram pProgram,
                           char *pszInitValue);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC pSbData scriba_NewSbBytes(pSbProgram pProgram,
                          unsigned long len,
                          unsigned char *pszInitValue);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_DestroySbData(pSbProgram pProgram,
                          pSbData p);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_PurgeReaderMemory(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_PurgeLexerMemory(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_PurgeSyntaxerMemory(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_PurgeBuilderMemory(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_PurgePreprocessorMemory(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_PurgeExecuteMemory(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_SetFileName(pSbProgram pProgram,
                       char *pszFileName);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_LoadConfiguration(pSbProgram pProgram,
                             char *pszForcedConfigurationFileName);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_GetConfigFileName(pSbProgram pProgram,
                             char **ppszFileName);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_InheritConfiguration(pSbProgram pProgram,
                                pSbProgram pFrom);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_InitModuleInterface(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_InheritModuleInterface(pSbProgram pProgram,
                                  pSbProgram pFrom);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_InheritExecuteObject(pSbProgram pProgram,
                                  pSbProgram pFrom);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_SetProcessSbObject(pSbProgram pProgram,
                              pSbProgram pProcessObject);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_ShutdownMtModules(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SetCgiFlag(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SetReportFunction(pSbProgram pProgram,
                              void *fpReportFunction);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SetReportPointer(pSbProgram pProgram,
                             void *pReportPointer);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SetStdin(pSbProgram pProgram,
                     void *fpStdinFunction);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SetStdout(pSbProgram pProgram,
                      void *fpStdoutFunction);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SetEmbedPointer(pSbProgram pProgram,
                            void *pEmbedder);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SetEnvironment(pSbProgram pProgram,
                           void *fpEnvirFunction);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_LoadBinaryProgramWithOffset(pSbProgram pProgram,
                                       long lOffset,
                                       long lEOFfset);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_LoadBinaryProgram(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_InheritBinaryProgram(pSbProgram pProgram,
                                pSbProgram pFrom);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_LoadInternalPreprocessor(pSbProgram pProgram,
                            char *ppszPreprocessorName[]);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_ReadSource(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_DoLexicalAnalysis(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_DoSyntaxAnalysis(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_BuildCode(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_IsFileBinaryFormat(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_GetCacheFileName(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_UseCacheFile(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_SaveCacheFile(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_RunExternalPreprocessor(pSbProgram pProgram,
                                   char **ppszArgPreprocessor);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_SaveCode(pSbProgram pProgram,
                     char *pszCodeFileName);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SaveCCode(pSbProgram pProgram,
                      char *pszCodeFileName);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_SaveECode(pSbProgram pProgram,
                      char *pszInterpreter,
                      char *pszCodeFileName);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_LoadSourceProgram(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_LoadProgramString(pSbProgram pProgram,
                             char *pszSourceCode,
                             unsigned long cbSourceCode);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_Run(pSbProgram pProgram,
               char *pszCommandLineArgument);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_NoRun(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_ResetVariables(pSbProgram pProgram);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_Call(pSbProgram pProgram,
                unsigned long lEntryNode);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_CallArg(pSbProgram pProgram,
                   unsigned long lEntryNode,
                   char *pszFormat, ...);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_DestroySbArgs(pSbProgram pProgram,
                          pSbData Args,
                          unsigned long cArgs);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC pSbData scriba_NewSbArgs(pSbProgram pProgram,
                         char *pszFormat, ...);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_CallArgEx(pSbProgram pProgram,
                     unsigned long lEntryNode,
                     pSbData ReturnValue,
                     unsigned long cArgs,
                     pSbData Args);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC long scriba_LookupFunctionByName(pSbProgram pProgram,
                                 char *pszFunctionName);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC long scriba_LookupVariableByName(pSbProgram pProgram,
                                 char *pszVariableName);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC long scriba_GetVariableType(pSbProgram pProgram,
                            long lSerial);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_GetVariable(pSbProgram pProgram,
                       long lSerial,
                       pSbData *pVariable);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC int scriba_SetVariable(pSbProgram pProgram,
                       long lSerial,
                       int type,
                       long lSetValue,
                       double dSetValue,
                       char *pszSetValue,
                       unsigned long size);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_InitStaticModules(void);
/*FEDNUF*/
/*FUNDEF*/

SCRIBA_MAIN_LIBSPEC void scriba_FinishStaticModules(void);
/*FEDNUF*/
#ifdef __cplusplus
}
#endif
#endif
