package plus.atama.cordova.plugins.marketavailability;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;

import android.net.Uri;
import android.util.Log;

import java.util.List;

/**
 * Interact with Google Play.
 *
 * @author Ippei Ukai <ukai@atama.plus>
 * @license Apache 2.0
 */
public class PlayStoreAvailability extends CordovaPlugin
{

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {        
        if (action.equals("check")) {
            if (this.checkGooglePlayStoreAvailability()) {
                callbackContext.success("available");
                return true;
            } else {
                callbackContext.error("not available");
                return false;
            }
        }
        return false;
    }
    
    private final String DUMMY_APP_ID = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE;

    public boolean checkGooglePlayStoreAvailability() {
        Context context = this.cordova.getActivity().getApplicationContext();

        Intent openMarketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + DUMMY_APP_ID));
        final List<ResolveInfo> resolvedList = context.getPackageManager().queryIntentActivities(openMarketIntent, 0);
        for (ResolveInfo resolved: resolvedList) {
            if (resolved.activityInfo.applicationInfo.packageName.equals(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE)) {
                return true;
            }
        }
        return false;
    }

}
