package example.com.samsung.afinal.Adapter;

import android.app.Activity;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by samsung on 4/13/2018.
 */

public class JavaScriptInterface {
    private WebView mAppView;
    private Activity mContext;

    private Handler jsiHandler;

    public JavaScriptInterface(Activity activity, WebView view, Handler handler){
        mAppView = view;
        mContext = activity;
        jsiHandler = handler;
    }

    public JavaScriptInterface() {

    }

    @JavascriptInterface
    public void toastShort (String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void testMove(final String url){
        jsiHandler.post(new Runnable() {
            @Override
            public void run() {
                mAppView.loadUrl(url);
            }
        });
    }

}
