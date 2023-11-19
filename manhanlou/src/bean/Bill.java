package bean;

import java.util.Date;

public class Bill {
    private int id;
    private String billID;
    private Integer menuID;
    private Integer nums;
    private Double money;
    private Integer diningTableID;
    private Date billDate;
    private String state;

    public Bill(){

    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Bill(int id, String billID, Integer menuID, Integer nums, Integer diningTableID, Date billDate, String state) {
        this.id = id;
        this.billID = billID;
        this.menuID = menuID;
        this.nums = nums;
        this.diningTableID = diningTableID;
        this.billDate = billDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getDiningTableID() {
        return diningTableID;
    }

    public void setDiningTableID(Integer diningTableID) {
        this.diningTableID = diningTableID;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "\t" + id +
                "\t\t" + menuID +
                "\t\t\t" + nums +
                "\t\t\t" + money +
                "\t\t" + diningTableID +
                "\t\t" + billDate +
                "\t\t" + state;
    }
}
