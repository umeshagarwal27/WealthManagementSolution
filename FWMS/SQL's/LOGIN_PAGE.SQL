--CREATE TABLE FWMS_USERS
CREATE TABLE FWMS_USERS ( 
 WMS_USER_SNO                                                   NUMBER(10),
 WMS_CREATE_DATE                                                DATE,
 WMS_EMPLOYEE_NO                                                NUMBER(15),
 WMS_NAME                                                       VARCHAR2(100),
 WMS_USER_ID                                           	        VARCHAR2(20),
 WMS_USER_PASSWORD                                              VARCHAR2(20),
 WMS_GROUP                                                      NUMBER(4),
 WMS_GROUP_PREVILIGE                                            NUMBER(4),
 WMS_PREVILIGE_FLAG                                             NUMBER(4),
 WMS_EXPIRY_DAYS                                                NUMBER(3),
 WMS_LAST_LOGIN                                                 DATE,
 WMS_LAST_PASSWORD_CHANGE                                       DATE,
 WMS_BLOCK                                                      VARCHAR2(1),
 WMS_STATUS                                                     VARCHAR2(25),
 WMS_STATUS_CDATE                                               DATE,
 WMS_REMARKS                                                    VARCHAR2(25),
 WMS_ENTER_UID                                                  VARCHAR2(20),
 WMS_ENTER_FPC                                                  VARCHAR2(30),
 WMS_ENTER_DATE                                                 DATE,
 WMS_LAST_UPDATE_UID                                            VARCHAR2(20),
 WMS_LAST_FPC                                                   VARCHAR2(30),
 WMS_LAST_UPDATE_DATE                                           DATE,
 WMS_APPROVE_UID                                                VARCHAR2(20),
 WMS_APPROVE_FPC                                                VARCHAR2(30),
 WMS_APPROVE_DATE                                               DATE,
 WMS_COMMENTS                                                   VARCHAR2(300),
 WMS_EMAIL                                                      VARCHAR2(100),
 WMS_COMP_ID                                                    NUMBER(3));

-------------------------------------------------------------------------------------------------------------------------------------------------------
--CREATE TABLE FWMS_USERS_WPWD
CREATE TABLE FWMS_USERS_WPWD ( 
  WMS_USER_ID  VARCHAR2 (20), 
  WMS_DATE     DATE, 
  WMS_PC       VARCHAR2 (30));
  
--CREATE TABLE FWMS_USER_LOG
CREATE TABLE FWMS_USER_LOG (
WMS_LOG_DATE DATE,
WMS_USER_ID VARCHAR2(20),
WMS_PC VARCHAR2(30),
WMS_SCREEN VARCHAR2(100),
WMS_TRANSACTION_TYPE VARCHAR2(30),
WMS_LOG VARCHAR2(2000)
);

--CREATE SEQUENCE FWMS_SEQ_LOGIN
CREATE SEQUENCE FWMS_SEQ_LOGIN INCREMENT BY 1 START WITH 1 MAXVALUE 9999999999 MINVALUE 1 CYCLE NOCACHE NOORDER;

-- THIS Procedure will take care wrong user attempt 
--This procedure will insert new record in wrong password attempt table and if count>5 it will block the user
CREATE OR REPLACE PROCEDURE           FWMS_Wpwd_Proc_01 (PWMS_UID CHAR,PWMS_PC CHAR)IS
IVP_COUNT NUMBER;
BEGIN
INSERT INTO FWMS_USERS_WPWD VALUES (PWMS_UID,SYSDATE,PWMS_PC);
COMMIT;
SELECT COUNT(*) INTO IVP_COUNT FROM FWMS_USERS_WPWD WHERE WMS_User_Id=PWMS_UID;
IF IVP_COUNT>=5 THEN
UPDATE FWMS_USERS SET WMS_BLOCK='Y' WHERE WMS_USER_ID=PWMS_UID;
COMMIT;
END IF;
EXCEPTION
 WHEN NO_DATA_FOUND THEN
 NULL;
END;
/

-- This procedure will clear wrong attempts data when user logged correct 
CREATE OR REPLACE PROCEDURE  FWMS_Wpwd_Proc_02 (PWMS_UID CHAR,PWMS_PC CHAR)IS
BEGIN
DELETE FROM FWMS_USERS_WPWD WHERE WMS_USER_ID=PWMS_UID;
COMMIT;
UPDATE FWMS_USERS SET WMS_LAST_LOGIN=SYSDATE WHERE WMS_USER_ID=PWMS_UID;
COMMIT;
EXCEPTION
 WHEN NO_DATA_FOUND THEN
 NULL;
END;
/

--This procedure is used to log user activity in FMWS_USER_LOG table LIKE LOGGING INTO system etc.
CREATE OR REPLACE PROCEDURE  FWMS_ULOG_Proc_01 (PWMS_UID CHAR,PWMS_PC CHAR,PWMS_SCREEN CHAR,PWMS_TYPE CHAR,PWMS_LOG CHAR)IS
BEGIN
INSERT INTO FWMS_USER_LOG (WMS_LOG_DATE,WMS_USER_ID,WMS_PC,WMS_SCREEN,WMS_TRANSACTION_TYPE,WMS_LOG)
VALUES (SYSDATE,PWMS_UID,PWMS_PC,PWMS_SCREEN,PWMS_TYPE,PWMS_LOG);
COMMIT;
EXCEPTION
 WHEN NO_DATA_FOUND THEN
 NULL;
END;
/
-------------------------------------------------------------------------------------------------------------------------------------------------------
--INSERT INTO FWMS_USERS
INSERT INTO FWMS_USERS (WMS_USER_SNO,WMS_CREATE_DATE,WMS_EMPLOYEE_NO,WMS_NAME,WMS_USER_ID,WMS_USER_PASSWORD,WMS_LAST_LOGIN,WMS_LAST_PASSWORD_CHANGE,WMS_BLOCK,WMS_EMAIL,WMS_EXPIRY_DAYS)
VALUES (1,SYSDATE,1,'VASU','VASU','VASU456',SYSDATE-10,SYSDATE-10,'N','vasuas@hotmail',60);

INSERT INTO FWMS_USERS (WMS_USER_SNO,WMS_CREATE_DATE,WMS_EMPLOYEE_NO,WMS_NAME,WMS_USER_ID,WMS_USER_PASSWORD,WMS_LAST_LOGIN,WMS_LAST_PASSWORD_CHANGE,WMS_BLOCK,WMS_EMAIL,WMS_EXPIRY_DAYS)
VALUES (2,SYSDATE,2,'RAJ','RAJ','VASU456',SYSDATE-10,SYSDATE-10,'N','vasuas@hotmail',60);


INSERT INTO FWMS_USERS (WMS_USER_SNO,WMS_CREATE_DATE,WMS_EMPLOYEE_NO,WMS_NAME,WMS_USER_ID,WMS_USER_PASSWORD,WMS_LAST_LOGIN,WMS_LAST_PASSWORD_CHANGE,WMS_BLOCK,WMS_EMAIL,WMS_EXPIRY_DAYS)
VALUES (3,SYSDATE,2,'KING','KING','VASU456',SYSDATE-10,SYSDATE-10,'Y','vasuas@hotmail',60);


INSERT INTO FWMS_USERS (WMS_USER_SNO,WMS_CREATE_DATE,WMS_EMPLOYEE_NO,WMS_NAME,WMS_USER_ID,WMS_USER_PASSWORD,WMS_LAST_LOGIN,WMS_LAST_PASSWORD_CHANGE,WMS_BLOCK,WMS_EMAIL,WMS_EXPIRY_DAYS)
VALUES (4,SYSDATE,2,'VARUN','VARUN','VASU456',SYSDATE-10,SYSDATE-10,'N','vasuas@hotmail',60);


INSERT INTO FWMS_USERS (WMS_USER_SNO,WMS_CREATE_DATE,WMS_EMPLOYEE_NO,WMS_NAME,WMS_USER_ID,WMS_USER_PASSWORD,WMS_LAST_LOGIN,WMS_LAST_PASSWORD_CHANGE,WMS_BLOCK,WMS_EMAIL,WMS_EXPIRY_DAYS)
VALUES (5,SYSDATE,2,'RAMU','RAMU','VASU456',SYSDATE-10,SYSDATE-10,'N','vasuas@hotmail',60);


INSERT INTO FWMS_USERS (WMS_USER_SNO,WMS_CREATE_DATE,WMS_EMPLOYEE_NO,WMS_NAME,WMS_USER_ID,WMS_USER_PASSWORD,WMS_LAST_LOGIN,WMS_LAST_PASSWORD_CHANGE,WMS_BLOCK,WMS_EMAIL,WMS_EXPIRY_DAYS)
VALUES (6,SYSDATE,2,'TRUMP','TRUMP','VASU456',SYSDATE-10,SYSDATE-10,'Y','vasuas@hotmail',60);

