package view;

import bean.Bill;
import bean.DiningTable;
import bean.Employee;
import bean.Menu;
import service.BillService;
import service.DiningTableService;
import service.EmployeeService;
import service.MenuService;
import utils.Utility;

import java.util.List;

public class MHLView {
    // 控制是否推出菜单
    private boolean loop = true;
    private String key = "";
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();

    public void listDiningTable(){
        System.out.println("显示餐桌状态");
        List<DiningTable> list  = diningTableService.list();
        System.out.println("餐桌编号\t\t餐桌状态");
        for(DiningTable diningTable : list){
            System.out.println(diningTable);
        }

    }

    // 完成订座
    public void orderDiningTable(){
        System.out.println("==================预定餐桌===============");
        System.out.print("请选择要预定的餐桌编号(-1退出): ");
        int orderID = Utility.readInt();
        if(orderID == -1){
            System.out.println("==================取消预定餐桌===============");
            return;
        }
        // System.out.print("确认是否预定餐桌（Y/N）:");
        char key = Utility.readConfirmSelection();
        if(key == 'Y'){
            // 根绝orderID返回对应DiningTable对象，如果为null，说明对象不存在
            DiningTable diningTable = diningTableService.getDiningTableByID(orderID);
            if(diningTable == null){
                System.out.println("==================预定餐桌不存在===============");
                return;
            }
            // 判断餐桌的状态是否为空
            if(!"空".equals(diningTable.getState())){
                System.out.println("==================餐桌已经被预定===============");
                return;
            }else{
                System.out.print("请输入预定人的姓名: ");
                String orderName = Utility.readString(50);
                System.out.print("请输入预定人的电话: ");
                String orderTel = Utility.readString(50);
                if(diningTableService.orderDiningTable(orderID, orderName, orderTel)){
                    System.out.println("==================餐桌预定成功===============");
                }else{
                    System.out.println("==================餐桌预定失败===============");
                }
            }
        }else{
            System.out.println("==================取消预定餐桌===============");
        }

    }

    // 显示所有菜品
    public void listMenu(){
        List<Menu> list = menuService.list();
        System.out.println("\t菜品编号\t\t菜品名\t\t类别\t\t价格");
        for(Menu menu:list){
            System.out.println(menu);
        }
        System.out.println("=================显示完毕===============");
    }

    // 完成点餐
    public void orderMenu(){
        System.out.println("===================点餐服务======================");
        System.out.print("请输入点餐的桌号(-1t退出): ");
        int orderDiningTableID = Utility.readInt();
        if(orderDiningTableID==-1){
            System.out.println("===================取消点餐=============");
            return;
        }
        System.out.print("请输入点餐的菜品号(-1t退出): ");
        int orderMenuID = Utility.readInt();
        if(orderMenuID==-1){
            System.out.println("===================取消点餐=============");
            return;
        }
        System.out.print("请输入点餐的菜品量(-1t退出): ");
        int orderNums = Utility.readInt();
        if(orderNums==-1){
            System.out.println("===================取消点餐=============");
            return;
        }

        // 验证餐桌号是否存在
        DiningTable diningTableByID = diningTableService.getDiningTableByID(orderDiningTableID);
        if(diningTableByID==null){
            System.out.println("===================餐桌号不存在=============");
            return;
        }
        // 验证菜品编号
        Menu menuByID = menuService.getMenuByID(orderMenuID);
        if(menuByID==null){
            System.out.println("===================菜品不存在=============");
            return;
        }
        if(billService.orderMenu(orderMenuID, orderNums, orderDiningTableID)){
            System.out.println("===================点餐成功=============");
        }else{
            System.out.println("===================点餐失败=============");
            return;
        }

    }

    public void listBill(){
        List<Bill> allBills = billService.list();
        System.out.println("\t编号\t\t菜品号\t\t菜品量\t\t金额\t\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        for(Bill bill : allBills){
            System.out.println(bill);
        }
        System.out.println("================显示完毕================");
    }
    // 完成结账
    public void payBill(){
        System.out.println("=================结账服务==================");
        System.out.print("请选择要结账的餐桌编号(-1退出): ");
        int diningTableID = Utility.readInt();
        if(diningTableID==-1){
            System.out.println("==================取消结账===============");
            return;
        }
        // 验证餐桌是否存在
        DiningTable diningTable = diningTableService.getDiningTableByID(diningTableID);
        if(diningTable==null){
            System.out.println("==================结账的餐桌不存在===============");
            return;
        }
        // 验证餐桌是否有需要结账的账单
        if(!billService.hasPayBillByDiningTableID(diningTableID)){
            System.out.println("==================没有需要结账的账单===============");
            return;
        }
        System.out.print("请选择结账方式(现金/支付宝/微信): ");
        String payMode = Utility.readString(10);
        if(payMode.equals("")){
            System.out.println("==================取消结账===============");
            return;
        }
        char key = Utility.readConfirmSelection();
        if(key=='Y'){
            // 结账
            boolean result = billService.payBill(diningTableID, payMode);
            if(result){
                System.out.println("==================结账成功===============");
            }else{
                System.out.println("==================结账失败===============");
            }
        }
        else{
            System.out.println("==================取消结账===============");
        }
    }

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
                    String empID = Utility.readString(50);
                    System.out.print("请输入密码: ");
                    String pwd = Utility.readString(50);

                    // 到数据库去判断
                    EmployeeService employeeService = new EmployeeService();
                    Employee employee = employeeService.getEmptyeeByIdAndPwd(empID, pwd);

                    if(employee!=null){
                        System.out.println("=================登录成功["+employee.getName()+"]===============\n");
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
                                    listDiningTable();
                                    break;
                                case "2":
                                    orderDiningTable();
                                    break;
                                case "3":
                                    listMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    payBill();
                                    break;
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
