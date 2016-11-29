ALTER TABLE OPS$ORBIX.TAB_LIST_SCRIPT
 DROP PRIMARY KEY CASCADE;
DROP TABLE OPS$ORBIX.TAB_LIST_SCRIPT CASCADE CONSTRAINTS;

CREATE TABLE OPS$ORBIX.TAB_LIST_SCRIPT
(
	TIPO_DI_CONFIGURAZIONE    VARCHAR2(30 BYTE)   NOT NULL,
	POSIZIONE_ELEMENTO        NUMBER              NOT NULL,
	SCRIPT 		          NUMBER,
	SCRIPT_TYPE	          NUMBER
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

ALTER TABLE OPS$ORBIX.TAB_LIST_SCRIPT ADD (
  PRIMARY KEY
 (TIPO_DI_CONFIGURAZIONE, POSIZIONE_ELEMENTO, SCRIPT)
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

GRANT DELETE, INSERT, SELECT, UPDATE ON OPS$ORBIX.TAB_LIST_SCRIPT TO RDA_MODIFY_ALL;