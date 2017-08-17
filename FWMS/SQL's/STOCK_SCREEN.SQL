--CREATE TBALE FWMS_PRODUCT
CREATE TABLE  FWMS_PRODUCT (
 WMS_PRODUCT_TYPE                                               VARCHAR2(50),
 WMS_PRODUCT_ID                                        	        NUMBER(15),
 WMS_PRODUCT_NAME                                               VARCHAR2(100),
 WMS_TICKER                                                     VARCHAR2(20),
 WMS_RETUER_CODE                                                VARCHAR2(20),
 WMS_BLOOMBERG_CODE                                             VARCHAR2(40),
 WMS_ISIN_CODE                                                  VARCHAR2(40),
 WMS_OTHER_CODE                                                 VARCHAR2(40),
 WMS_OTHER_DESC                                                 VARCHAR2(40),
 WMS_PRICE_UPDATE                                               VARCHAR2(40),
 WMS_COUNTRY                                                    NUMBER(4),
 WMS_CURRENCY                                                   NUMBER(4),
 WMS_STOCK_EXCHANGE                                             NUMBER(4),
 WMS_SECTOR                                                     NUMBER(4),
 WMS_ASSET                                                      NUMBER(4),
 WMS_BOND_ISSUE_NO                                              VARCHAR2(20),
 WMS_BOND_ISSUE_DATE                                            DATE,
 WMS_BOND_MATURITY_DT                                           DATE,
 WMS_BOND_UNIT_PRICE                                            NUMBER(15,3),
 WMS_BOND_COUPON_RATE                                           NUMBER(25,6),
 WMS_BOND_COUPON_FREQUENCY                                      VARCHAR2(15),
 WMS_BOND_DIVISOR_DAYS_MONTH                                    VARCHAR2(15),
 WMS_BOND_DIVISOR_DAYS_YEAR                                     NUMBER(5),
 WMS_BOND_COUPON_1ST_INTDATE                                    VARCHAR2(5),
 WMS_BOND_COUPON_2ND_INTDATE                                    VARCHAR2(5),
 WMS_BOND_COUPON_3RD_INTDATE                                    VARCHAR2(5),
 WMS_BOND_COUPON_4TH_INTDATE                                    VARCHAR2(5),
 WMS_PUT_CALL_FLAG                                              VARCHAR2(12),
 WMS_PUT_CALL_EXPIRY_DATE                                       DATE,
 WMS_PUT_CALL_LOTSIZE                                           NUMBER(10),
 WMS_PUT_CALL_SPRICE                                            NUMBER(25,8),
 WMS_PUT_CALL_STATUS                                            NUMBER(25,8),
 WMS_MUTUAL_FUNDS_ISSUE                                         VARCHAR2(75),
 WMS_MUTUAL_INHOUSE_3RD_FLAG                                    VARCHAR2(30),
 WMS_MUTUAL_FUNDS_CLOSDED_OPEN                                  VARCHAR2(25),
 WMS_MUTUAL_FUNDS_NAV_FLAG                                      VARCHAR2(30),
 WMS_COMMENTS                                                   VARCHAR2(300),
 WMS_STATUS                                                     VARCHAR2(20),
 WMS_ENTER_UID                                                  VARCHAR2(20),
 WMS_ENTER_FPC                                                  VARCHAR2(30),
 WMS_ENTER_DATE                                                 DATE,
 WMS_LAST_UPDATE_UID                                            VARCHAR2(20),
 WMS_LAST_FPC                                                   VARCHAR2(30),
 WMS_LAST_UPDATE_DATE                                           DATE,
 WMS_APPROVE_UID                                                VARCHAR2(20),
 WMS_APPROVE_FPC                                                VARCHAR2(30),
 WMS_APPROVE_DATE                                               DATE,
 WMS_MUTUAL_FUNDS_VALUE                                         NUMBER(35,3),
 WMS_MUTUAL_IB_TYPE                                             VARCHAR2(50),
 WMS_SEDOL                                                      VARCHAR2(20));

--CREATE TBALE FWMS_BOND_COUPEN
CREATE TABLE FWMS_BOND_COUPEN        (
 WMS_SNO                                                        NUMBER(10),
 WMS_BOND                                                       NUMBER(10),
 WMS_DATE                                                       DATE,
 WMS_COUPEN                                                     NUMBER(15,8));


--------------------------------------LIST BOX TABLES

--CREATE TBALE FWMS_COUNTRY
CREATE TABLE FWMS_COUNTRY (
 WMS_COUNTRY_ID                                         NUMBER(25),
 WMS_COUNTRY_DESC                                               VARCHAR2(75));

--CREATE TBALE FWMS_CURRENCY
 CREATE TABLE FWMS_CURRENCY (
 WMS_CURRENCY_ID                                        NUMBER(4),
 WMS_CURRENCY_DESC                                              VARCHAR2(75),
 WMS_SHORT_DESC                                                 VARCHAR2(10));

 --CREATE TBALE FWMS_ASSET
CREATE TABLE FWMS_ASSET (
 WMS_ASSET_ID                                           NUMBER(4),
 WMS_ASSET_DESC                                                 VARCHAR2(75));
 
 --CREATE TBALE FWMS_SECTOR
CREATE TABLE FWMS_SECTOR (
 WMS_SECTOR_ID                                          NUMBER(4),
 WMS_SECTOR_DESC                                                VARCHAR2(75));

--CREATE TBALE FWMS_STOCK_EXCHANGE
CREATE TABLE FWMS_STOCK_EXCHANGE (  
 WMS_STOCK_EXCHANGE_ID                                 NUMBER(4),
 WMS_STOCK_EXCHANGE_DESC                                        VARCHAR2(75));

 --CREATE SEQUENCE FWMS_PRODUCT_SEQ
CREATE SEQUENCE FWMS_PRODUCT_SEQ INCREMENT BY 1 START WITH 1 MAXVALUE 999999999999 MINVALUE 1;
CREATE SEQUENCE FWMS_PRODUCT_CREATE_SEQ INCREMENT BY 1 START WITH 1 MAXVALUE 999999999999 MINVALUE 1;
