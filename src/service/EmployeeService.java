package service;

import bean.Customer;
import bean.DiningTable;
import bean.Employee;
import dao.CustomerDAO;
import dao.EmployeeDAO;

import java.util.List;
import java.util.UUID;

public class EmployeeService {
    // 通过调用EmployeeDAO来完成对employ表的各种操作
    // 定义一个EmployDAO属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();


    //获得顾客所有的信息
    public List<Employee> listEmployee(){
        List<Employee> employeeList = employeeDAO.queryMulti("select id, empID , NAME,job from coffeeShop.employee", Employee.class);

        return employeeList;
    }
    //根据id，删除顾客信息

    public boolean deleteEmployeeByID(String empID){
        int update = employeeDAO.update("delete from coffeeShop.employee where empID = ?",
                empID);
        return update > 0;
    }

    //更新顾客信息
    public boolean updateEmployeePasswordByID(String pwd,String empID){
        int update = employeeDAO.update("update coffeeShop.employee set pwd = md5(?) where empID = ?",
                pwd,empID);
        return update > 0;
    }

    // 方法，根据empID和pwd返回一个Employee对象
    public boolean setEmployeeByIdAndPwd(String empID, String pwd,String name){
        Employee employee = employeeDAO.querySingle("select * from coffeeShop.employee where empID=? ", Employee.class, empID);

        if(employee != null)
        {
            return false;
        }
        else {
            int update = employeeDAO.update("insert into coffeeShop.employee values(null,?,md5(?),?,'店员')",
                    empID, pwd, name);
            return update>0;
        }

    }
    public Employee getEmployeeByIdAndPwd(String empID, String pwd){
        Employee employee = employeeDAO.querySingle("select * from coffeeShop.employee where empID=? and pwd=md5(?)", Employee.class, empID, pwd);
        return employee;
    }
}
