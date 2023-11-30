package service;

import bean.Menu;
import dao.MenuDAO;

import java.util.List;

public class MenuService {
    // 完成对menu表的各种操作
    private MenuDAO menuDAO = new MenuDAO();

    // 返回所有菜品
    public List<Menu> list(){
        return menuDAO.queryMulti("select * from menu", Menu.class);
    }

    // 根据id返回Menu对象
    public Menu getMenuByID(int id){
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }
}
