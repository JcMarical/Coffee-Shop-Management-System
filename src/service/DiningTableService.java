package service;

import bean.DiningTable;
import dao.DiningTableDAO;

import java.util.List;

public class DiningTableService {
    // 定义一个DiningTableDAO对象
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    // 返回所有餐桌的状态
    public List<DiningTable> list(){
        List<DiningTable> list = diningTableDAO.queryMulti("select id, state from coffeeShop.diningTable", DiningTable.class);

        return list;
    }

    // 根据id，查询对应的餐桌DiningTable对象
    public DiningTable getDiningTableByID(int id){
        // 把sql语句放到sqlyog中简单测试
        return diningTableDAO.querySingle("select * from coffeeShop.diningTable where id = ?", DiningTable.class, id);
    }

    // 如果餐桌可以预定，调用方法，对其状态进行更新
    public boolean orderDiningTable(int id, String orderName, String orderTel){
        System.out.println("开始update");
        int update = diningTableDAO.update("update coffeeShop.diningTable set state='已经预定', orderName=?, orderTel=? where id=?", orderName, orderTel, id);
        System.out.println("结束update");
        return update>0;
    }

    // 更新餐桌状态
    public boolean updateDiningTableState(int id, String state){
        int update = diningTableDAO.update("update coffeeShop.diningTable set state=? where id=?", state, id);
        return update>0;
    }

    // 将指定餐桌设置为初始状态
    public boolean updateDiningTableToFree(int id, String state){
        int update = diningTableDAO.update("update coffeeShop.diningTable set state=?,orderName='',orderTel='' where id=?", state, id);
        return update>0;
    }
}
