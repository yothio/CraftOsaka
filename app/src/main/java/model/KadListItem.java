package model;


/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListItem {

    public KadListItem(String kadName){
        this.kadName = kadName;
    }


    String kadName;

    public void setKadName(String kadName) {
        this.kadName = kadName;
    }

    public String getKadName() {
        return kadName;
    }
}
