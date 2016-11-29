ALTER TABLE OPS$ORBIX.TAB_KIT_CONTATORI
 DROP PRIMARY KEY CASCADE;
DROP TABLE OPS$ORBIX.TAB_KIT_CONTATORI CASCADE CONSTRAINTS;

CREATE TABLE OPS$ORBIX.TAB_KIT_CONTATORI
(
  TIPO_DI_CONFIGURAZIONE  VARCHAR2(30 BYTE)     NOT NULL,
  IDCOUNTER               NUMBER,
  TAG                     VARCHAR2(30 BYTE),
  USEPLUGIN               CHAR(1 BYTE)          NOT NULL,
  PLUGINNAME              VARCHAR2(256 BYTE),
  USEPATH                 CHAR(1 BYTE)          NOT NULL,
  PATHNAME                VARCHAR2(1540 BYTE)
--  COUNTERKIT              OPS$ORBIX.TAB_COUNTERKIT
)
TABLESPACE TBS_RETE7
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          160K
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


ALTER TABLE OPS$ORBIX.TAB_KIT_CONTATORI ADD (
  PRIMARY KEY
 (TIPO_DI_CONFIGURAZIONE, IDCOUNTER)
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

GRANT DELETE, INSERT, SELECT, UPDATE ON OPS$ORBIX.TAB_KIT_CONTATORI TO RDA_MODIFY_ALL;