package service;

import bean.Customer;
import bean.Employee;
import dao.CustomerDAO;
import dao.EmployeeDAO;

public class CustomerService {
    // 通过调用EmployeeDAO来完成对employ表的各种操作
    // 定义一个EmployDAO属性
    private CustomerDAO customerDAO = new CustomerDAO();

    // 方法，根据empID和pwd返回一个Employee对象

    public boolean setCustomerByIdAndPwd(String empID, String pwd,String name){
        Customer customer = customerDAO.querySingle("select * from coffeeShop.customer where empID=? ", Customer.class, empID);

        if(customer != null)
        {
            return false;
        }
        else {
            int update = customerDAO.update("insert into coffeeShop.customer values(null,?,md5(?),?,'顾客')",
                    empID, pwd, name);
            return update>0;
        }

    }
    public Customer getCustomerByIdAndPwd(String empID, String pwd){
        Customer customer= customerDAO.querySingle("select * from coffeeShop.customer where empID=? and pwd=md5(?)", Customer.class, empID, pwd);
        return customer;
    }
}
