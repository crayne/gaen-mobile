package covidsafepaths.bt.exposurenotifications;

import android.content.pm.PackageInfo;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import javax.annotation.Nonnull;

@ReactModule(name = DeviceInfoModule.MODULE_NAME)
public class DeviceInfoModule extends ReactContextBaseJavaModule {
    public static final String MODULE_NAME = "DeviceInfoModule";
    public static final String TAG = "DeviceInfoModule";

    public DeviceInfoModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public @Nonnull
    String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getApplicationName(final Promise promise) {
        String appName = getReactApplicationContext()
                .getApplicationInfo()
                .loadLabel(getReactApplicationContext().getPackageManager()).toString();
        promise.resolve(appName);
    }

    @ReactMethod
    public void getBuildNumber(final Promise promise) {
        try {
            promise.resolve(Integer.toString(getPackageInfo().versionCode));
        } catch (Exception e) {
            promise.resolve("unknown");
        }
    }

    @ReactMethod
    public void getVersion(final Promise promise) {
        try {
            promise.resolve(getPackageInfo().versionName);
        } catch (Exception e) {
            promise.resolve("unknown");
        }
    }

    private PackageInfo getPackageInfo() throws Exception {
        return getReactApplicationContext()
                .getPackageManager()
                .getPackageInfo(getReactApplicationContext().getPackageName(), 0);
    }
}