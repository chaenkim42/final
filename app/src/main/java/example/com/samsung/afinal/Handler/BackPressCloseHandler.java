package example.com.samsung.afinal.Handler;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by samsung on 4/13/2018.
 */

public class BackPressCloseHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.moveTaskToBack(true);
            activity.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "한번 더 누르면 종료", Toast.LENGTH_SHORT);
        toast.show();
    }


}
