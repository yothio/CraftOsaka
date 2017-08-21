package craftosaka.syukupili.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by yocchi on 2017/08/19.
 */

public class App extends Application {

    private static Context sContext;

    public static Context getAppContext(){
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
