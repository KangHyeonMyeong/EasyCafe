package customclass;

import java.io.Serializable;

/**
 * Created by we25 on 2017-07-04.
 */

public class ItemBasket implements Serializable {
    String menuName;
    String info;
    int num;
    int price;


    public ItemBasket(String menuName, String info, int num, int price) {
        this.menuName = menuName;
        this.info = info;
        this.num = num;
        this.price = price;
    }

    public ItemBasket() {

    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
