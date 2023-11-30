package service;

import bean.Employee;
import dao.EmployeeDAO;

public class AdministorService {
    // 通过调用EmployeeDAO来完成对employ表的各种操作
    // 定义一个EmployDAO属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

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
