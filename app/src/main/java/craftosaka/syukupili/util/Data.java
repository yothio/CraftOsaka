package craftosaka.syukupili.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import craftosaka.syukupili.model.KadListItem;

/**
 * Created by yocchi on 2017/08/19.
 */

public class Data {


    public boolean childParentFrag;

    public static  Data date = new Data();

    private Data(){
    }

    public static Data getInstance(){
        return date;
    }




}
