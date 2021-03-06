DROP TABLE OPS$ORBIX.TAB_ALARM_RELATION_ELEMENTS CASCADE CONSTRAINTS;

CREATE TABLE OPS$ORBIX.TAB_ALARM_RELATION_ELEMENTS
(
  ID_ALARM_RELATION       NUMBER(6)             NOT NULL,
  TIPO_DI_CONFIGURAZIONE  VARCHAR2(30 BYTE)     NOT NULL,
  ID_ELEMENT              NUMBER(6)             NOT NULL,
  POSITION_ELEMENT        NUMBER,
  ENABLED_DETAIL          NUMBER(2)
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
NOLOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;
