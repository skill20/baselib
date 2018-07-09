package com.library.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

/**
 * Create by wangqingqing
 * On 2018/6/11 10:18
 * Copyright(c) 2018 极光
 * Description
 */
public class Network {

    public static final int NETWORK_NONE = 0; // 没有网络连接
    public static final int NETWORK_WIFI = 1; // wifi连接
    public static final int NETWORK_2G = 2; // 2G
    public static final int NETWORK_3G = 3; // 3G
    public static final int NETWORK_4G = 4; // 4G
    public static final int NETWORK_MOBILE = 5; // 手机流量
    public static final int NETWORK_UNKNOWN = 6; // 未知网络


    private Network() {
    }

    /**
     * 判断是否有网络连接且网络可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(@NonNull Context context) {
        boolean isConnected = false;
        ConnectivityManager manager = getManager(context);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            isConnected = true;
        }
        return isConnected;
    }

    /**
     * 判断网络是否是wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(@NonNull Context context) {
        ConnectivityManager manager = getManager(context);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null &&
                networkInfo.getType() == ConnectivityManager.TYPE_WIFI;

    }


    private static ConnectivityManager getManager(@NonNull Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    private static int getNetworkState(@NonNull Context context) {

        int state = NETWORK_NONE;

        ConnectivityManager connManager = getManager(context); // 获取网络服务
        if (null == connManager) { // 为空则认为无网络
            return state;
        }

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if ((networkInfo != null && networkInfo.isAvailable())) {
            int type = networkInfo.getType();
            if (type == ConnectivityManager.TYPE_WIFI) {
                state = NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                switch (networkInfo.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        state = NETWORK_2G;
                        break;

                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        state = NETWORK_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        state = NETWORK_4G;
                        break;

                    default:

                        String subtypeName = networkInfo.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            state = NETWORK_3G;
                        } else {
                            state = NETWORK_MOBILE;
                        }
                        break;
                }
            }
        }
        return state;
    }

}
