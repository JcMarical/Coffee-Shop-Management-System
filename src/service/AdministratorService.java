package service;

import bean.Administrator;
import bean.Administrator;
import bean.Employee;
import dao.AdministratorDAO;
import dao.EmployeeDAO;

import java.util.List;

public class AdministratorService {
    // 通过调用EmployeeDAO来完成对employ表的各种操作
    // 定义一个EmployDAO属性
    private AdministratorDAO administratorDAO = new AdministratorDAO();

    //获得顾客所有的信息
    public List<Administrator> listAdministrator(){
        List<Administrator> list = administratorDAO.queryMulti("select id, empID, NAME, job from coffeeShop.administrator", Administrator.class);

        return list;
    }
    //根据id，删除顾客信息

    public boolean delecteAdministratorByID(String empID){
        int update = administratorDAO.update("delete from coffeeShop.administrator where empID = ?",
                empID);
        return update > 0;
    }

    //更新顾客信息
    public boolean updateAdministratorPasswordByID(String pwd,String empID){
        int update = administratorDAO.update("update coffeeShop.administrator set pwd=md5(?) where empID=?",
                pwd,empID);
        return update > 0;
    }

    //注册会员
    public boolean setAdministratorByIdAndPwd(String empID, String pwd,String name){
        Administrator administrator = administratorDAO.querySingle("select * from coffeeShop.administrator where empID=? ", Administrator.class, empID);

        if(administrator != null)
        {
            return false;
        }
        else {
            int update = administratorDAO.update("insert into coffeeShop.administrator values(null,?,md5(?),?,'顾客')",
                    empID, pwd, name);
            return update>0;
        }

    }

    //根据ID和密码获得会员信息
    public Administrator getAdministratorByIdAndPwd(String empID, String pwd){
        Administrator administrator= administratorDAO.querySingle("select * from coffeeShop.administrator where empID=? and pwd=md5(?)", Administrator.class, empID, pwd);
        return administrator;
    }


}
