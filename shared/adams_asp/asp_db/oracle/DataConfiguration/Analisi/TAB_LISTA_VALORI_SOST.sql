ALTER TABLE OPS$ORBIX.TAB_LISTA_VALORI_SOST
 DROP PRIMARY KEY CASCADE;
DROP TABLE OPS$ORBIX.TAB_LISTA_VALORI_SOST CASCADE CONSTRAINTS;

CREATE TABLE OPS$ORBIX.TAB_LISTA_VALORI_SOST
(
	TIPO_DI_CONFIGURAZIONE    VARCHAR2(30 BYTE)   NOT NULL,
	POSIZIONE_ELEMENTO        NUMBER              NOT NULL,
	VALORI_SOSTITUZIONE       VARCHAR2(24)
)
TABLESPACE TBS_RETE7
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          320K
            NEXT             160K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

ALTER TABLE OPS$ORBIX.TAB_LISTA_VALORI_SOST ADD (
  PRIMARY KEY
 (TIPO_DI_CONFIGURAZIONE, POSIZIONE_ELEMENTO, VALORI_SOSTITUZIONE)
    USING INDEX 
    TABLESPACE TBS_RETE7
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          160K
                NEXT             160K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

GRANT DELETE, INSERT, SELECT, UPDATE ON OPS$ORBIX.TAB_LISTA_VALORI_SOST TO RDA_MODIFY_ALL;