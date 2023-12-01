package bean;

import java.util.Date;

public class TakeoutsBill {
    private int id;
    private String billID;
    private Integer menuID;
    private Integer nums;
    private Double money;
    private Date billDate;
    private String state;

    private String address;

    @Override

    public String toString() {
        return "takeoutsBill{" +
                "id=" + id +
                ", billID='" + billID + '\'' +
                ", menuID=" + menuID +
                ", nums=" + nums +
                ", money=" + money +
                ", billDate=" + billDate +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                '}';
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TakeoutsBill(int id, String billID, Integer menuID, Integer nums, Double money, Date billDate, String state, String address) {
        this.id = id;
        this.billID = billID;
        this.menuID = menuID;
        this.nums = nums;
        this.money = money;
        this.billDate = billDate;
        this.state = state;
        this.address = address;
    }
}
