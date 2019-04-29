package com.hj.interestingalbum.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {

    public static String getRandomKey() {
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();
        key = key.replace("-", "");
        key = key.substring(0, 16);
        return key;
    }

    public static String getUniqueId(Context context) {
        @SuppressLint("HardwareIds") String androidID = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        @SuppressLint("HardwareIds") String id = androidID + Build.SERIAL;
        return toMD5(id);
    }


    private static String toMD5(String text) {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte aDigest : digest) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = aDigest & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }

        //返回整个结果
        return sb.toString();
    }

    public static String getStringByView(TextView textView) {
        String text = textView.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            return "";
        } else {
            return text;
        }
    }

    public static void setTextViewContent(TextView tv, String content) {
        if (TextUtils.isEmpty(content)) {
            tv.setText("");
        } else {
            tv.setText(content);
        }
    }

    public static void setTextViewContentWithDefault(TextView tv, String content, String defalut) {
        if (TextUtils.isEmpty(content)) {
            tv.setText(defalut);
        } else {
            tv.setText(content);
        }
    }

    public static void setTextViewContentGone(TextView tv, String content) {
        if (TextUtils.isEmpty(content)) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(content);
        }
    }

    public static void setTextViewContentInvisible(TextView tv, String content) {
        if (TextUtils.isEmpty(content)) {
            tv.setVisibility(View.INVISIBLE);
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(content);
        }
    }

    public static boolean unEmpty(Collection list) {
        return list != null && list.size() > 0;
    }


    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取当前版本号
     */

    public static int getLocalVersion(Context context) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 比较服务端和本地版本号
     *
     * @param serverCode
     * @param localCode
     * @return
     */
    public static boolean compareVersion(int serverCode, int localCode) {
        return serverCode > localCode;
    }

//    /**
//     * Android
//     */
//
//    public static String ANDROID_TYPE = "1";
//
//    public static String getEncodeString(@NonNull String steamId, @NonNull String name, @NonNull String content) {
//        String key = steamId.substring(3, 14) + "#" + name;
//        String toMd5 = toMD5(key).toLowerCase();
//        String newKey = toMd5.substring(0, 16);
//        return AESUtil.decrypt(newKey, content);
//    }

    public static String setOldEmail(String email) {
        try {
            if (!TextUtils.isEmpty(email) && email.contains("@")) {
                if (!TextUtils.isEmpty(email) && email.contains("@")) {
                    int index = email.indexOf("@");
                    String str1 = email.substring(0, index);
                    String str2 = email.substring(index);
                    if (str1.length() > 3) {
                        str1 = email.substring(0, str1.length() - 3);
                        return str1 + "***" + str2;
                    } else {
                        str1 = "***";
                        return str1 + str2;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String setOldPhone(String phone) {
        try {
            if (!TextUtils.isEmpty(phone)) {
                if (phone.length() > 10) {
                    String str1 = phone.substring(0, 3);
                    String str2 = phone.substring(7, phone.length());
                    return str1 + "****" + str2;
                } else {
                    String str1 = phone.substring(0, 3);
                    String str2 = phone.substring(5, phone.length());
                    return str1 + "**" + str2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String setAuthenName(String name) {
        try {
            if (!TextUtils.isEmpty(name)) {
                String str1 = name.substring(1, name.length());
                return "*" + str1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String setAuthSellerNo(String no) {
        try {
            if (!TextUtils.isEmpty(no)) {
                String str1 = no.substring(0, 3);
                String str2 = no.substring(no.length() - 4, no.length());
                return str1 + "************" + str2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String setAuthBankNo(String bankNo) {
        try {
            if (!TextUtils.isEmpty(bankNo)) {
                String str1 = bankNo.substring(0, 7);
                String str2 = bankNo.substring(bankNo.length() - 4, bankNo.length());
                return str1 + "************" + str2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getSteamShortId(String steam) {
        try {
            if (!TextUtils.isEmpty(steam)) {
                String str2 = steam.substring(steam.length() - 4, steam.length());
                return "(***" + str2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return telephonyManager.getDeviceId();
    }

    public static String roundByScale(float v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        if (scale == 0) {
            return new DecimalFormat("0").format(v);
        }
        StringBuilder formatStr = new StringBuilder("0.");
        for (int i = 0; i < scale; i++) {
            formatStr.append("0");
        }
        return new DecimalFormat(formatStr.toString()).format(v);
    }

    public static void closeSoft(Activity context) {
        View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputmanger != null;
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

//    public static boolean isAppAvilible(Context context, String packageName) {
//        final PackageManager packageManager = context.getPackageManager();//获取packagemanager
//        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息
//        List<String> pName = new ArrayList<>();//用于存储所有已安装程序的包名
//        if (pinfo != null) {
//            for (int i = 0; i < pinfo.size(); i++) {
//                String pn = pinfo.get(i).packageName;
//                pName.add(pn);
//            }
//        }
//        return pName.contains(packageName);//判断pName中是否有目标程序的包名，有TRUE，没有FALSE
//    }

    /**
     * 判断小数点位数
     */
    public static boolean isTwo(String inStr) {
        if (inStr.contains(".")) {
            //获取小数点的位置
            int bitPos = inStr.indexOf(".");
            //字符串总长度减去小数点位置，再减去1，就是小数位数
            return inStr.length() - bitPos - 1 == 2;
        }
        return false;
    }
}
