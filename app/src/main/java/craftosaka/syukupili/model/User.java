package craftosaka.syukupili.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yocchi on 2017/08/24.
 */
@Getter
@Setter
public class User {

    String name;
    String password;
    int id;
    int point;

    public void pointAdd(int point){
        this.setPoint(this.getPoint() + point);
    }

    //ポイントを引けたらtrue、引けなかったらfalse
    public boolean pointMinus(int point){
        if(this.getPoint() >= point){
            this.setPoint(this.getPoint() - point);
            return true;
        }else{
            return false;
        }
    }

}
