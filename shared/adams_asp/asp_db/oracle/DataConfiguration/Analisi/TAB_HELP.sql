ALTER TABLE OPS$ORBIX.TAB_HELP
 DROP PRIMARY KEY CASCADE;
DROP TABLE OPS$ORBIX.TAB_HELP CASCADE CONSTRAINTS;

CREATE TABLE OPS$ORBIX.TAB_HELP
(
  ID                      NUMBER                NOT NULL,
  TIPO_DI_CONFIGURAZIONE  VARCHAR2(30 BYTE),
  IDHELP                  NUMBER,
  HELPVALUE               NUMBER                NOT NULL,
  DESCRIPTION             VARCHAR2(160 BYTE)
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


ALTER TABLE OPS$ORBIX.TAB_HELP ADD (
  PRIMARY KEY
 (IDHELP, HELPVALUE, DESCRIPTION)
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

GRANT DELETE, INSERT, SELECT, UPDATE ON OPS$ORBIX.TAB_HELP TO RDA_MODIFY_ALL;