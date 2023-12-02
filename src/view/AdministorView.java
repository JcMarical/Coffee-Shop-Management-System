package view;

import bean.*;
import service.*;
import utils.Utility;

import java.util.List;

public class AdministorView {
    // 控制是否推出菜单
    private boolean loop = true;
    private String key = "";
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();

    private CustomerService customerService = new CustomerService();

    private EmployeeService employeeService = new EmployeeService();


    // 显示所有菜品
    public void listMenu(){
        List<Menu> list = menuService.list();
        System.out.println("\t餐品编号\t\t餐品名\t\t类别\t\t价格");
        for(Menu menu:list){
            System.out.println(menu);
        }
        System.out.println("=================显示完毕===============");
    }


    public void listCustomer(){
        System.out.println("============顾客信息查询修改==============");
        List<Customer> listCustomer  = customerService.listCustomer();
        System.out.println("编号\t账号\t\t姓名\t\t权限");
        for(Customer customer : listCustomer){
            System.out.println(customer);
        }
        System.out.print("请选择要修改信息的顾客账号(-1退出): ");
        String customerID = Utility.readString(50);
        if("-1".equals(customerID)){
            System.out.println("==================取消顾客查询===============");
            return;
        }
        else{
            System.out.print("请输入顾客需要修改的密码(-1退出): ");
            String pwd = Utility.readString(50);
            if("-1".equals(pwd)){
                System.out.println("==================取消顾客查询===============");

            }
            else{
                System.out.print("确认修改信息？");
                char key = Utility.readConfirmSelection();
                if(key == 'Y') {
                    // 修改密码
                    if ( customerService.updateCustomerPasswordByID(pwd, customerID)){
                        System.out.println("==================信息修改成功===============");
                    }else{
                        System.out.println("==================信息修改失败===============");

                    }
                }
            }

        }


    }

    public void listEmployee(){

        System.out.println("============顾客信息查询修改==============");
        List<Employee> listEmployee  = employeeService.listEmployee();
        System.out.println("编号\t账号\t\t姓名\t\t权限");
        for(Employee employee : listEmployee){
            System.out.println(employee);
        }
        System.out.print("请选择要修改信息的顾客账号(-1退出): ");
        String employeeID = Utility.readString(50);
        if("-1".equals(employeeID)){
            System.out.println("==================取消顾客查询===============");
            return;
        }
        else{
            System.out.print("请输入顾客需要修改的密码(-1退出): ");
            String pwd = Utility.readString(50);
            if("-1".equals(pwd)){
                System.out.println("==================取消顾客查询===============");

            }
            else{
                System.out.print("确认修改信息？");
                char key = Utility.readConfirmSelection();
                if(key == 'Y') {
                    // 修改密码
                    if ( employeeService.updateEmployeePasswordByID(pwd, employeeID)){
                        System.out.println("==================信息修改成功===============");
                    }else{
                        System.out.println("==================信息修改失败===============");

                    }
                }
            }

        }


    }



    public void deleteCustomer(){
        System.out.println("================顾客信息删除==============");
        System.out.print("请选择要修改信息的顾客账号(-1退出): ");
        String customerID = Utility.readString(50);
        if("-1".equals(customerID)){
            System.out.println("==================取消顾客查询===============");
            return;
        }
        else{
                System.out.print("确认删除账号（此操作不可逆）？");
                char key = Utility.readConfirmSelection();
                if(key == 'Y') {
                    // 修改密码
                    if ( customerService.deleteCustomerByID(customerID)){
                        System.out.println("==================账号删除成功===============");
                    }else{
                        System.out.println("==================账号删除失败===============");

                    }
                }


        }


    }

    public void deleteEmployee(){
        System.out.println("================店员信息删除==============");
        System.out.print("请选择要修改信息的店员账号(-1退出): ");
        String employeeID = Utility.readString(50);
        if("-1".equals(employeeID)){
            System.out.println("==================取消账号查询===============");
            return;
        }
        else{
            System.out.print("确认删除账号（此操作不可逆）？");
            char key = Utility.readConfirmSelection();
            if(key == 'Y') {
                // 修改密码
                if ( employeeService.deleteEmployeeByID(employeeID)){
                    System.out.println("==================账号删除成功===============");
                }else{
                    System.out.println("==================账号删除失败===============");

                }
            }


        }


    }

    // 显示主菜单
    public void mainMenu(){
        //EmployeeService employeeService = new EmployeeService();
        AdministratorService administratorService = new AdministratorService();
        while(loop){
            System.out.println("================品悦咖啡===============");
            System.out.println("\t\t 1 登录品悦咖啡");
            System.out.println("\t\t 2 注册品悦咖啡");
            System.out.println("\t\t 3 退出品悦咖啡");

            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);
            switch (key){
                case "1" :
                    // System.out.println("登录品悦咖啡");
                    System.out.print("请输入账号: ");
                    String empID = Utility.readString(50);
                    System.out.print("请输入密码: ");
                    String pwd = Utility.readString(50);


                    // 到数据库去判断

                   //Employee employee = employeeService.getEmployeeByIdAndPwd(empID, pwd);
                    Administrator administrator = administratorService.getAdministratorByIdAndPwd(empID,pwd);


                    if(administrator !=null){
                        System.out.println("=================登录成功，管理员["+ administrator.getName()+"]=============\n");
                        // 显示二级菜单,这里应该是循环操作
                        while(loop){
                            System.out.println("=================品悦咖啡===============");
                            System.out.println("\t\t 1 显示所有饮品/甜点");
                            System.out.println("\t\t 2 顾客信息查询修改");
                            System.out.println("\t\t 3 店员信息查询修改");
                            System.out.println("\t\t 4 顾客信息删除");
                            System.out.println("\t\t 5 店员信息删除");
                            System.out.println("\t\t 6 退出");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    listMenu();
                                    break;
                                case "2":
                                    listCustomer();
                                    break;
                                case "3":
                                    listEmployee();
                                    break;
                                case "4":
                                    deleteCustomer();
                                    break;
                                case "5":
                                    deleteEmployee();
                                    break;


                                case "9":
                                    loop = false;
                                    System.out.println("退出品悦咖啡系统");
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
                    // System.out.println("登录品悦咖啡");
                    System.out.print("请输入账号: ");
                    String empRegisterID = Utility.readString(50);
                    System.out.print("请输入密码: ");
                    String pwdRegister = Utility.readString(50);
                    System.out.print("请输入姓名: ");
                    String nameRegister = Utility.readString(50);

                    // 到数据库去判断
                    boolean flag =  administratorService.setAdministratorByIdAndPwd(empRegisterID, pwdRegister,nameRegister);
                    if(flag){
                        System.out.print("注册成功！！！");
                    }else{
                        System.out.print("注册失败！！！");
                    }
                    break;
                case "3":
                    loop = false;
                    System.out.println("退出品悦咖啡系统");
                    break;
                default:
                    System.out.println("你的输入有误,请重新输入");
            }
        }
    }

    public static void main(String[] args) {
        AdministorView administratorView = new AdministorView();
        administratorView.mainMenu();
    }
}
