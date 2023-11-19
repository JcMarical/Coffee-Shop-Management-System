## 品悦咖啡
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
package view;

import utils.Utility;

public class MHLView {
    // 控制是否推出菜单
    private boolean loop = true;
    private String key = "";
    // 显示主菜单
    public void mainMenu(){
        while(loop){
            System.out.println("=================满汉楼===============");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);
            switch (key){
                case "1" :
                    // System.out.println("登录满汉楼");
                    System.out.print("请输入员工号: ");
                    String id = Utility.readString(50);
                    System.out.print("请输入密码: ");
                    String pwd = Utility.readString(50);

                    // 到数据库去判断
                    if("123".equals(pwd)){
                        System.out.println("=================登录成功===============\n");
                        // 显示二级菜单,这里应该是循环操作
                        while(loop){
                            System.out.println("=================满汉楼===============");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 7 ...");
                            System.out.println("\t\t 8 ...");
                            System.out.println("\t\t 9 退出");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    System.out.println("显示餐桌状态");
                                    break;
                                case "2":
                                    System.out.println("预定餐桌");
                                    break;
                                case "3":
                                    System.out.println("显示所有菜品");
                                    break;
                                case "4":
                                    System.out.println("点餐服务");
                                    break;
                                case "5":
                                    System.out.println("查看账单");
                                    break;
                                case "6":
                                    System.out.println("结账");
                                case "7":
                                case "8":
                                    break;
                                case "9":
                                    loop = false;
                                    System.out.println("退出满汉楼系统");
                                    break;
                                default:
                                    System.out.println("你的输入有误，请重新输入");
                            }
                        }

                    }else{
                        System.out.println("=================登录失败===============");
                    }

                    break;
                case "2":
                    loop = false;
                    System.out.println("退出满汉楼");
                    break;
                default:
                    System.out.println("你的输入有误,请重新输入");
            }
        }
    }

    public static void main(String[] args) {
        MHLView mhlView = new MHLView();
        mhlView.mainMenu();
    }
}
```

![image-20210701164612447](C:\Users\songpeng\IdeaProjects\manhanlou\README.assets\image-20210701164612447.png)

### 3、sql

```sql
-- 创建满汉楼数据库
CREATE DATABASE mhl;
USE mhl;

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
INSERT INTO employee VALUES(NULL, '6668615', MD5('123456'), '张三丰', '经理');
INSERT INTO employee VALUES(NULL, '6668612', MD5('123456'), '小龙女', '服务员');
INSERT INTO employee VALUES(NULL, '6668633', MD5('123456'), '张无忌', '收银员');
INSERT INTO employee VALUES(NULL, '666666', MD5('123456'), '老韩', '经理');

SELECT * FROM employee;
DROP TABLE employee;

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

INSERT INTO menu VALUES(NULL, '八宝饭', '主食', 10);
INSERT INTO menu VALUES(NULL, '叉烧包', '主食', 20);
INSERT INTO menu VALUES(NULL, '宫保鸡丁', '热菜', 30);
INSERT INTO menu VALUES(NULL, '松鼠鳜鱼', '热菜', 50);
INSERT INTO menu VALUES(NULL, '水煮鱼', '热菜',50);
INSERT INTO menu VALUES(NULL, '甲鱼汤', '汤类', 100);
INSERT INTO menu VALUES(NULL, '鸡蛋汤', '汤类', 15);

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

# update diningTable set state='空' where id=1;
```

## 使用说明

### 1、配置相关环境

- 将上面的sql语句执行（mysql 8.0.22）
- 修改druid.properties中的相关配置：

- ......
