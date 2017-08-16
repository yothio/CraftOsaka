package craftosaka.syukupili.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by yocchi on 2017/08/16.
 */

@Getter
@Setter
public class KadListItem {

    //課題ID
    int kadId;
    //課題名
    String kadName;
    //課題内容
    String kadContent;
    //子ID
    int childId;
    //子の名前
    String childName;
    //取得ポイント
    int point;
    //開始日
    int startDate;
    //終了日
    int endDate;
    //進捗フラグ　達成１　未達成０　失敗２
    int progressFrag;
    //詳細設定フラグ
    boolean settingFrag;

}
