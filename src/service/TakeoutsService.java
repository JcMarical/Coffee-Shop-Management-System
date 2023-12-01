package service;

import bean.Bill;
import bean.TakeoutsBill;
import dao.BillDAO;
import dao.TakeoutsDAO;

import java.util.List;
import java.util.UUID;

public class TakeoutsService {
    private TakeoutsDAO takeoutsDAO = new TakeoutsDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();
    // 生成账单
    // 更新对应餐桌的状态
    public boolean orderTakeoutsMenu(int menuID, int nums,String address){
        String billID = UUID.randomUUID().toString();

        int update = takeoutsDAO.update("insert into coffeeShop.takeoutsBill values(null,?,?,?,?,now(),'正在制作',?)",
                billID, menuID, nums, menuService.getMenuByID(menuID).getPrice()*nums, address);
        if(update <= 0){
            return false;
        }
        return true;
    }

    // 返回所有账单
    public List<TakeoutsBill> list(){
        return takeoutsDAO.queryMulti("select * from coffeeShop.takeoutsBill", TakeoutsBill.class);
    }



}
