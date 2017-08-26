CREATE TABLE fwms_main_menu (
WMM_USERID                                                     VARCHAR2(25),
WMM_SNO                                                        NUMBER(5),
WMM_MENU                                                       NUMBER(3),
WMM_PNODE                                                      NUMBER(5),
WMM_CNODE                                                      NUMBER(5),
WMM_DESC                                                       VARCHAR2(100),
WMM_FILE                                                       VARCHAR2(30),
WMM_VIEW                                                       VARCHAR2(1),
WMM_INSERT                                                     VARCHAR2(1),
WMM_UPDATE                                                     VARCHAR2(1),
WMM_DELETE                                                     VARCHAR2(1),
WMM_MAKER_CHECKER                                              VARCHAR2(1),
WMM_OPTION_FLAG                                                VARCHAR2(50),
WMM_LAST_UPDATE_UID                                            VARCHAR2(20),
WMM_LAST_FPC                                                   VARCHAR2(30),
WMM_LAST_UPDATE_DATE                                           DATE,
WMM_APPROVE_UID                                                VARCHAR2(20),
WMM_APPROVE_FPC                                                VARCHAR2(30),
WMM_APPROVE_DATE                                               DATE,
WMM_APPROVE_COMMENTS                                           VARCHAR2(300),
WMS_STATUS                                                     VARCHAR2(20));

ALTER TABLE FWMS_MAIN_MENU ADD TASK_FLOW_ID VARCHAR2(1000);

UPDATE FWMS_MAIN_MENU SET TASK_FLOW_ID = '/WEB-INF/com/ciobera/fwms/system/ui/taskflows/user-log-task-flow-definition.xml#user-log-task-flow-definition' WHERE WMM_CNODE = 52;

UPDATE FWMS_MAIN_MENU SET TASK_FLOW_ID = '/WEB-INF/com/ciobera/fwms/system/ui/taskflows/stock-information-task-flow-definition.xml#stock-information-task-flow-definition' WHERE WMM_CNODE = 54;

UPDATE FWMS_MAIN_MENU SET TASK_FLOW_ID = '/WEB-INF/com/ciobera/fwms/trading/ui/taskflows/order-management-task-flow-definition.xml#order-management-task-flow-definition' WHERE WMM_CNODE = 301;

UPDATE FWMS_MAIN_MENU SET TASK_FLOW_ID = '/WEB-INF/com/ciobera/fwms/trading/ui/taskflows/dashboard-task-flow-definition.xml#dashboard-task-flow-definition' WHERE WMM_CNODE = 302;

UPDATE FWMS_MAIN_MENU SET TASK_FLOW_ID = '/WEB-INF/com/ciobera/fwms/trading/ui/taskflows/trade-book-task-flow-definition.xml#trade-book-task-flow-definition' WHERE WMM_CNODE = 303;


============================SYSTEM PARAMATER wmm_menu=1 ==========================================================

For tree view u can use below query 

SELECT WMM_PNODE,WMM_CNODE,WMM_DESC FROM FWMS_MAIN_MENU WHERE WMM_USERID='SRINI' AND wmm_menu=1 AND WMM_VIEW='Y'
order by WMM_SNO,wmm_pnode,wmm_cnode;

Tree should come like below
System Paramater
	User Managmenent
		User Log
	Stock Setup
		Stock


INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',1,1,50,'System Paramater','MENU','Y','Y','Y','Y','Y',1);


INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',1,50,51,'User Management','MENU','Y','Y','Y','Y','Y',2);
INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',1,51,52,'User Log','screen name','Y','Y','Y','Y','Y',2);



INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',1,50,53,'Stock Setup','MENU','Y','Y','Y','Y','Y',3);
INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',1,53,54,'Stock','screen name','Y','Y','Y','Y','Y',3);



==========================================TRADING MANAMAGNET WMM_MENU=2========================================================


For tree view u can use below query 

SELECT WMM_PNODE,WMM_CNODE,WMM_DESC FROM FWMS_MAIN_MENU WHERE WMM_USERID='SRINI' AND wmm_menu=2 AND WMM_VIEW='Y'
order by WMM_SNO,wmm_pnode,wmm_cnode;

Tree should come like below
Trading Managment
	Order Managment 
                Dashboard
		Trade Book


INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',2,2,300,'Trading Management','MENU','Y','Y','Y','Y','Y',1);



INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',2,300,301,'Order Management','MENU','Y','Y','Y','Y','Y',2);
INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',2,300,302,'Dashboard','screen name','Y','Y','Y','Y','Y',2);
INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',2,300,303,'Trade Book','screen name','Y','Y','Y','Y','Y',2);





==========================================Accounting Module WMM_MENU=3========================================================


For tree view u can use below query 

SELECT WMM_PNODE,WMM_CNODE,WMM_DESC FROM FWMS_MAIN_MENU WHERE WMM_USERID='SRINI' AND wmm_menu=3 AND WMM_VIEW='Y'
order by WMM_SNO,wmm_pnode,wmm_cnode;

Tree should come like below
Accounting Module


INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',3,3,600,'Accounting Module','MENU','Y','Y','Y','Y','Y',1);


==========================================Payroll=4========================================================


Fixed Assets
Compliance Reports
MIS Reports


For tree view u can use below query 

SELECT WMM_PNODE,WMM_CNODE,WMM_DESC FROM FWMS_MAIN_MENU WHERE WMM_USERID='SRINI' AND wmm_menu=4 AND WMM_VIEW='Y'
order by WMM_SNO,wmm_pnode,wmm_cnode;

Tree should come like below
Accounting Module


INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',4,4,900,'PAYROLL','MENU','Y','Y','Y','Y','Y',1);


==========================================Fixed Assets=5========================================================



Compliance Reports
MIS Reports


For tree view u can use below query 

SELECT WMM_PNODE,WMM_CNODE,WMM_DESC FROM FWMS_MAIN_MENU WHERE WMM_USERID='SRINI' AND wmm_menu=5 AND WMM_VIEW='Y'
order by WMM_SNO,wmm_pnode,wmm_cnode;

Tree should come like below
Accounting Module


INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',5,5,1200,'Fixed Assets','MENU','Y','Y','Y','Y','Y',1);



==========================================Compliance Reports=6========================================================




MIS Reports


For tree view u can use below query 

SELECT WMM_USERID, WMM_MENU, WMM_PNODE, WMM_CNODE, WMM_DESC, WMM_FILE, WMM_VIEW, WMM_INSERT, WMM_UPDATE, WMM_DELETE, WMM_MAKER_CHECKER, WMM_SNO FROM FWMS_MAIN_MENU WHERE WMM_USERID='SRINI' AND wmm_menu=6 AND WMM_VIEW='Y'
order by WMM_SNO,wmm_pnode,wmm_cnode;

Tree should come like below
Accounting Module


INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',6,6,1500,'Fixed Assets','MENU','Y','Y','Y','Y','Y',1);

==========================================MIS Reports=7========================================================







For tree view u can use below query 

SELECT WMM_PNODE,WMM_CNODE,WMM_DESC FROM FWMS_MAIN_MENU WHERE WMM_USERID='SRINI' AND wmm_menu=7 AND WMM_VIEW='Y'
order by WMM_SNO,wmm_pnode,wmm_cnode;

Tree should come like below
Accounting Module


INSERT INTO FWMS_MAIN_MENU (WMM_USERID,WMM_MENU,WMM_PNODE,WMM_CNODE,WMM_DESC,WMM_FILE,WMM_VIEW,WMM_INSERT,WMM_UPDATE,WMM_DELETE,WMM_MAKER_CHECKER,WMM_SNO)
VALUES ('SRINI',7,7,1800,'Fixed Assets','MENU','Y','Y','Y','Y','Y',1);
