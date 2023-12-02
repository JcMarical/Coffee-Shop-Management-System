## 咖啡店后台管理系统
本项目使用用到了java SE、MySQL、JDBC，其中连接池使用Druid。

### 程序总体框架

![image-20210701160248635](C:\Users\songpeng\IdeaProjects\manhanlou\README.assets\image-20210701160248635.png)

### 1、准备工具类

![image-20210701162459531](C:\Users\songpeng\IdeaProjects\manhanlou\README.assets\image-20210701162459531.png)

将所需要的jar包和工具类导入项目，并进行简单测试。

```java
package utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        // 测试工具类
        // System.out.println("请输入一个整数:");
        // int i = Utility.readInt();

        // 测试JDBCUtils
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println("lianjie chenggong");
    }
}
```

### 2、简单界面

在view层下建立MHLView文件

```java
 略
```

![image-20210701164612447](C:\Users\songpeng\IdeaProjects\manhanlou\README.assets\image-20210701164612447.png)

### 3、sql

```sql
-- 创建品悦咖啡数据库
CREATE DATABASE coffeeShop;
USE coffeeShop;

-- 创建employ表
CREATE TABLE `employee`(
	id INT AUTO_INCREMENT,
	empID VARCHAR(50) UNIQUE NOT NULL DEFAULT '',
	pwd CHAR(32) NOT NULL DEFAULT '',
	NAME VARCHAR(50) NOT NULL DEFAULT '',
	job VARCHAR(50) NOT NULL DEFAULT '',
	PRIMARY KEY (id)
)CHARSET=utf8;
-- 添加测试数据

INSERT INTO employee VALUES(NULL, '000001', MD5('123456'), '李欣怡', '店员');
INSERT INTO employee VALUES(NULL, '000002', MD5('123456'), '李艳超', '店员');
INSERT INTO employee VALUES(NULL, '000003', MD5('123456'), '郑佳颖', '店员');

SELECT * FROM employee;

-- 创建customer表
CREATE TABLE `customer`(
                           id INT AUTO_INCREMENT,
                           empID VARCHAR(50) UNIQUE NOT NULL DEFAULT '',
                           pwd CHAR(32) NOT NULL DEFAULT '',
                           NAME VARCHAR(50) NOT NULL DEFAULT '',
                           job VARCHAR(50) NOT NULL DEFAULT '',
                           PRIMARY KEY (id)
)CHARSET=utf8;
-- 添加测试数据

INSERT INTO customer VALUES(NULL, '100001', MD5('123456'), '李怡欣', '会员');
INSERT INTO customer VALUES(NULL, '100002', MD5('123456'), '李超艳', '会员');
INSERT INTO customer VALUES(NULL, '100003', MD5('123456'), '郑颖佳', '会员');

SELECT * FROM customer;

-- 创建administrator表
CREATE TABLE `administrator`(
                           id INT AUTO_INCREMENT,
                           empID VARCHAR(50) UNIQUE NOT NULL DEFAULT '',
                           pwd CHAR(32) NOT NULL DEFAULT '',
                           NAME VARCHAR(50) NOT NULL DEFAULT '',
                           job VARCHAR(50) NOT NULL DEFAULT '',
                           PRIMARY KEY (id)
)CHARSET=utf8;
-- 添加测试数据

INSERT INTO administrator VALUES(NULL, '200001', MD5('123456'), '李俊呈', '管理员');


SELECT * FROM administrator; 


-- 创建餐桌表
CREATE TABLE `diningTable`(
	id INT AUTO_INCREMENT,
	state VARCHAR(20) NOT NULL DEFAULT '',
	ordername VARCHAR(50) NOT NULL DEFAULT '',
	ordertel VARCHAR(20) NOT NULL DEFAULT '',
	PRIMARY KEY (id)
)CHARSET=utf8;

INSERT INTO diningTable VALUES(NULL, '空', '', '');
INSERT INTO diningTable VALUES(NULL, '空', '', '');
INSERT INTO diningTable VALUES(NULL, '空', '', '');

SELECT * FROM diningTable;

# update diningTable set state='空', orderName='', orderTel='' where id=1

-- 创建餐桌表
CREATE TABLE `diningTable`(
                              id INT AUTO_INCREMENT,
                              state VARCHAR(20) NOT NULL DEFAULT '',
                              ordername VARCHAR(50) NOT NULL DEFAULT '',
                              ordertel VARCHAR(20) NOT NULL DEFAULT '',
                              PRIMARY KEY (id)
)CHARSET=utf8;

INSERT INTO diningTable VALUES(NULL, '空', '', '');
INSERT INTO diningTable VALUES(NULL, '空', '', '');
INSERT INTO diningTable VALUES(NULL, '空', '', '');

SELECT * FROM diningTable;

# update diningTable set state='空', orderName='', orderTel='' where id=1

-- 创建菜单表(id, name, type, price)
CREATE TABLE `menu`(
	id INT AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL DEFAULT '',
	`type` VARCHAR(50) NOT NULL DEFAULT '',
	`price` DOUBLE NOT NULL DEFAULT 0,
	PRIMARY KEY (id)
)CHARSET=utf8;

INSERT INTO menu VALUES(NULL, '生椰拿铁', '咖啡', 10);
INSERT INTO menu VALUES(NULL, '冰吸生椰拿铁', '咖啡', 20);
INSERT INTO menu VALUES(NULL, '经典美式', '咖啡', 15);
INSERT INTO menu VALUES(NULL, '抹茶拿铁', '咖啡', 20);
INSERT INTO menu VALUES(NULL, '曲奇饼', '甜点',20);
INSERT INTO menu VALUES(NULL, '小蛋糕', '甜点', 30);
INSERT INTO menu VALUES(NULL, '菠萝派', '甜点', 15);

SELECT * FROM menu;

-- 创建账单表 bill(id, billID, menuID, nums, billDate, money, stae, diningTableID)
CREATE TABLE bill(
	id INT AUTO_INCREMENT,
	billID VARCHAR(50) NOT NULL DEFAULT '',
	menuID INT NOT NULL DEFAULT 0,
	nums INT NOT NULL DEFAULT 0,
	money DOUBLE NOT NULL DEFAULT 0,
	diningTableID INT NOT NULL DEFAULT 0,
	billDate DATETIME NOT NULL,
	state VARCHAR(50) NOT NULL DEFAULT '',
	PRIMARY KEY (id)
)CHARSET=utf8;

SELECT * FROM bill;


-- 创建账单表 bill(id, billID, menuID, nums, billDate, money, stae, diningTableID)
CREATE TABLE takeoutsBill(
                     id INT AUTO_INCREMENT,
                     billID VARCHAR(50) NOT NULL DEFAULT '',
                     menuID INT NOT NULL DEFAULT 0,
                     nums INT NOT NULL DEFAULT 0,
                     money DOUBLE NOT NULL DEFAULT 0,
                     billDate DATETIME NOT NULL,
                     state VARCHAR(50) NOT NULL DEFAULT '',
                     address VARCHAR(50) NOT NULL DEFAULT '',
                     PRIMARY KEY (id)
)CHARSET=utf8;

SELECT * FROM takeOutsBill;


# update diningTable set state='空' where id=1;
```

## 使用说明

### 1、配置相关环境

- 将上面的sql语句执行（mysql 8.0.22）
- 修改druid.properties中的相关配置：
```aidl
#key=value
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://192.168.173.132:3306/lingrui?&serverTimezone=UTC&rewriteBatchedStatements=true
#url=jdbc:mysql://192.168.173.132:3306/lingrui
username=root
password=123456
#initial connection Size
initialSize=10
#min idle connecton size
minIdle=5
#max active connection size
maxActive=20
#max wait time (5000 mil seconds)
maxWait=5000
```
- url:192.168.173.132:3306 修改为自己的ip地址（莫得服务器）
- 数据库名：lingrui 修改成自己的数据库名
- password：123456 修改成自己的密码
