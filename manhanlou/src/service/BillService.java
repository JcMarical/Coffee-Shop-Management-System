package service;

import bean.Bill;
import bean.DiningTable;
import bean.Menu;
import dao.BillDAO;
import jdk.nashorn.internal.ir.CallNode;

import java.util.List;
import java.util.UUID;

public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();
    // 生成账单
    // 更新对应餐桌的状态
    public boolean orderMenu(int menuID, int nums, int diningTableID){
        String billID = UUID.randomUUID().toString();

        int update = billDAO.update("insert into mhl.bill values(null,?,?,?,?,?,now(),'未结账')",
                        billID, menuID, nums, menuService.getMenuByID(menuID).getPrice()*nums, diningTableID);
        if(update <= 0){
            return false;
        }
        return diningTableService.updateDiningTableState(diningTableID, "正在用餐");
    }

    // 返回所有账单
    public List<Bill> list(){
        return billDAO.queryMulti("select * from mhl.bill", Bill.class);
    }

    // 查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableID(int diningTableID){
        return billDAO.querySingle("select * from mhl.bill where diningTableID=? and state='未结账' limit 0,1", Bill.class, diningTableID)!=null;
    }

    // 完成结账[如果餐桌存在，并且该餐桌有未结账的账单]
    public boolean payBill(int diningTableID, String payMode){
        // 修改bill表
        int update = billDAO.update("update mhl.bill set state=? where diningTableID=? and state='未结账'", payMode, diningTableID);
        if(update<=0){
            return false;
        }
        // 修改diningTable表
        // 注意：应该调用diningTableService方法
        if(!diningTableService.updateDiningTableToFree(diningTableID, "空")){
            return false;
        }
        return true;
    }
}
