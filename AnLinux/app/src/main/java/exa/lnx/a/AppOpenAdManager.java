package exa.lnx.a;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.libraries.ads.mobile.sdk.appopen.AppOpenAd;
import com.google.android.libraries.ads.mobile.sdk.appopen.AppOpenAdEventCallback;
import com.google.android.libraries.ads.mobile.sdk.common.AdLoadCallback;
import com.google.android.libraries.ads.mobile.sdk.common.AdRequest;
import com.google.android.libraries.ads.mobile.sdk.common.AdValue;
import com.google.android.libraries.ads.mobile.sdk.common.FullScreenContentError;
import com.google.android.libraries.ads.mobile.sdk.common.LoadAdError;

import org.jetbrains.annotations.NotNull;

public class AppOpenAdManager {
    private static final String LOG_TAG = "AppOpenAdManager";
    private static final String AD_UNIT_ID = "ca-app-pub-5748356089815497/7922138881";

    private AppOpenAd appOpenAd = null;
    private boolean isLoadingAd = false;
    private boolean isShowingAd = false;

    private long loadTime = 0;

    /** Constructor. */
    public AppOpenAdManager() {}

    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    /** Request an ad. */
    public void loadAd(Context context) {
        // Do not load ad if there is an unused ad or one is already loading.
        if (isLoadingAd || isAdAvailable(context)) {
            return;
        }

        isLoadingAd = true;
        AppOpenAd.load(
                new AdRequest.Builder(AD_UNIT_ID).build(),
                new AdLoadCallback<AppOpenAd>() {
                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd ad) {
                        // Called when an ad has loaded.
                        ad.setAdEventCallback(new AppOpenAdEventCallback() {

                        });
                        appOpenAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                        // Called when ad fails to load.
                        isShowingAd = false;
                    }
                });
    }
    /** Check if ad exists and can be shown. */
    private boolean isAdAvailable(Context context) {
        final long APP_OPEN_AD_INTERVAL = 4 * 60 * 60 *1000;
        SharedPreferences sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        long lastShown = sharedPreferences.getLong("LastShownTime", 0);
        long now = System.currentTimeMillis();
        if(now - lastShown >= APP_OPEN_AD_INTERVAL && appOpenAd != null){
            editor.putLong("LastShownTime", now).apply();
            return true;
        }
        return false;
    }
    public void showAdIfAvailable(
            @NonNull final Activity activity,
            @NonNull OnShowAdCompleteListener onShowAdCompleteListener){
        // If the app open ad is already showing, do not show the ad again.
        if (isShowingAd) {
            Log.d(LOG_TAG, "The app open ad is already showing.");
            return;
        }

        // If the app open ad is not available yet, invoke the callback then load the ad.
        if (!isAdAvailable(activity)) {
            Log.d(LOG_TAG, "The app open ad is not ready yet.");
            onShowAdCompleteListener.onShowAdComplete();
            loadAd(activity);
            return;
        }

        appOpenAd.setAdEventCallback(new AppOpenAdEventCallback() {
            @Override
            public void onAdClicked() {
                AppOpenAdEventCallback.super.onAdClicked();
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when fullscreen content is dismissed.
                // Set the reference to null so isAdAvailable() returns false.
                Log.d(LOG_TAG, "Ad dismissed fullscreen content.");
                appOpenAd = null;
                isShowingAd = false;

                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NotNull FullScreenContentError fullScreenContentError) {
                AppOpenAdEventCallback.super.onAdFailedToShowFullScreenContent(fullScreenContentError);
                Log.d(LOG_TAG, fullScreenContentError.getMessage());
                appOpenAd = null;
                isShowingAd = false;

                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
            }

            @Override
            public void onAdImpression() {
                AppOpenAdEventCallback.super.onAdImpression();
            }

            @Override
            public void onAdPaid(@NotNull AdValue value) {
                AppOpenAdEventCallback.super.onAdPaid(value);
            }

            @Override
            public void onAdShowedFullScreenContent() {
                AppOpenAdEventCallback.super.onAdShowedFullScreenContent();
            }
        });
        isShowingAd = true;
        appOpenAd.show(activity);
    }
}
