package customclass;

import java.io.Serializable;

/**
 * Created by we25 on 2017-06-26.
 */

public class MemberInfo implements Serializable{

    private static final long serialVersionUID = -3363890705364413851L;
    String ID;
    String password;
    String name;
    boolean managerCode;
    String phone_Num;
    String birth;

    public MemberInfo() {
    }

    public MemberInfo(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public MemberInfo(String ID, String password, String name, String birth, String phone_Num , boolean managerCode) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone_Num = phone_Num;
        this.managerCode = managerCode;
    }


    public MemberInfo(String ID, String password, String name, String phone_Num) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.phone_Num = phone_Num;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isManagerCode() {
        return managerCode;
    }

    public void setManagetCode(boolean managerCode) {
        this.managerCode = managerCode;
    }

    public String getPhone_Num() {
        return phone_Num;
    }

    public void setPhone_Num(String phone_Num) {
        this.phone_Num = phone_Num;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
