package model;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListItem {

    public KadListItem(int kadID){
        this.kadID = kadID;
    }

    int kadID;

    @Setter
    @Getter
    String kadName;

    @Setter
    @Getter
    String kadContent;

    @Setter
    @Getter
    int childID;

    @Setter
    @Getter
    String childName;

    @Setter
    @Getter
    int point;

    @Setter
    @Getter
    int startDate;

    @Setter
    @Getter
    int endDate;

    @Setter
    @Getter
    int progressFrag;

    @Setter
    @Getter
    boolean settingFrag;

//    public void setKadName(String kadName) {
//        this.kadName = kadName;
//    }
//
//    public String getKadName() {
//        return kadName;
//    }


}
