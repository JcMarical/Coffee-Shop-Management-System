package service;

import bean.Employee;
import dao.EmployeeDAO;

public class EmployeeService {
    // 通过调用EmployeeDAO来完成对employ表的各种操作
    // 定义一个EmployDAO属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    // 方法，根据empID和pwd返回一个Employee对象

    
    public Employee getEmptyeeByIdAndPwd(String empID, String pwd){
        Employee employee = employeeDAO.querySingle("select * from employee where empID=? and pwd=md5(?)", Employee.class, empID, pwd);
        return employee;
    }
}
