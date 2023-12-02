package service;

import bean.Customer;
import bean.Employee;
import dao.CustomerDAO;
import dao.EmployeeDAO;

import java.util.List;

public class CustomerService {
    // 通过调用EmployeeDAO来完成对employ表的各种操作
    // 定义一个EmployDAO属性
    private CustomerDAO customerDAO = new CustomerDAO();

    // 方法，根据empID和pwd返回一个Employee对象
    //获得顾客所有的信息
    public List<Customer> listCustomer(){
        List<Customer> list = customerDAO.queryMulti("select id, empID, NAME, job from coffeeShop.customer", Customer.class);

        return list;
    }
    //根据id，删除顾客信息

    public boolean delectCustomerByID(String empID){
        int update = customerDAO.update("delete from coffeeShop.customer where empID = ?",
                empID);
        return update > 0;
    }

    //更新顾客信息
    public boolean updateCustomerPasswordByID(String pwd,String empID){
        int update = customerDAO.update("update coffeeShop.customer set pwd=md5(?) where empID=?",
                pwd,empID);
        return update > 0;
    }

    //注册会员
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

    //根据ID和密码获得会员信息
    public Customer getCustomerByIdAndPwd(String empID, String pwd){
        Customer customer= customerDAO.querySingle("select * from coffeeShop.customer where empID=? and pwd=md5(?)", Customer.class, empID, pwd);
        return customer;
    }






}
