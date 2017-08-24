package craftosaka.syukupili.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.model.User;

/**
 * Created by yocchi on 2017/08/19.
 */

public class Data {

    List<User> list;
    User nowUser;

    public boolean parentFrag;
    public static  Data date = new Data();

    private Data(){
        list = PrefUtil.getUserList();
        if (list == null){
            list = new ArrayList<>();
        }
    }

    public static Data getInstance(){
        return date;
    }

    public void setNowUser(User user){
        nowUser = user;
    }

    public User getNowUser() {
        return nowUser;
    }
}
