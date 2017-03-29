
package com.wanny.appupdate.library;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;


/**
 * 类名 ：StoragePath.java
 * 功能 ：ftp默认,文件相关的操作。
 * 作者 ： wanny
 * 时间 ：2015年6月10日上午10:01:52
 */

public class StoragePath {
    /* ftp资源默认路径 */
    public static boolean storageStatus = true;
    //sd卡更目录
    public static String storageHome = "";
    //跟目录
    public static String homeDir = "";
    //apk缓存路径
    public static String apkDir = "";


    /**
     * 功能 ： 获取sd卡的根路径
     * 传递参数 ：
     * 返回类型 ：String
     * 作者 ： wanny
     * 时间 ：2015年7月2日下午4:35:23
     */

    public static String getSdPath() {
        if (isSdExists()) {
            return Environment.getExternalStorageDirectory().toString();// 获取跟目录
        } else
            return null;
    }

    /**
     * 获取是否存在外部缓存
     */

    public static boolean isSdExists() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 建立缓存目录
     */

    public StoragePath() {

    }


    public static void createDirs() {
        if (!isSdExists())
            return;
        storageHome = getSdPath();
/** 主目录 */

        File file = new File(storageHome, "AppupdateCache");
        if (!file.exists()) {
            file.mkdirs();
        }
        homeDir = file.getAbsolutePath();

        //APK下载目录
        file = new File(homeDir + "/apkDir");
        if (!file.exists()) {
            file.mkdirs();
        }
        apkDir = file.getAbsolutePath();
    }


    /**
     * 存储器剩余容量
     */

    public static long getAvailaleSize() {
        StatFs stat = new StatFs(storageHome);
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        // (availableBlocks * blockSize)/1024 KIB 单位
        // (availableBlocks * blockSize)/1024/1024 MIB单位
        return (availableBlocks * blockSize) / 1024;
    }


    public static boolean isPhotoDir() {
        storageHome = getSdPath();
        homeDir = storageHome + "/HifoAgentSurvey";
        File file = new File(homeDir + "/photo");
        if (!file.exists()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 存储器容量
     */

    public static long getAllSize() {
        StatFs stat = new StatFs(storageHome);
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getBlockCount();
        // (availableBlocks * blockSize)/1024 KIB 单位
        // (availableBlocks * blockSize)/1024/1024 MIB单位
        return (availableBlocks * blockSize) / 1024;
    }

    public static long getAlreadySize() {
        return (getAllSize() - getAvailaleSize());
    }

    public static boolean isStorageStatus() {
        return storageStatus;
    }

    public static void setStorageStatus(boolean storageStatus) {
        StoragePath.storageStatus = storageStatus;
    }

    public static String getHomeDir() {
        return homeDir;
    }

    public static void setHomeDir(String homeDir) {
        StoragePath.homeDir = homeDir;
    }


    /**
     * 获取字符串中某个字符串出现的次数。
     */

    public static int countMatches(String res, String findString) {
        if (res == null) {
            return 0;
        }
        if (findString == null || findString.length() == 0) {
            throw new IllegalArgumentException("The param findString cannot be null or 0 length.");
        }
        return (res.length() - res.replace(findString, "").length()) / findString.length();
    }


    /**
     * 判断该文件是否是一个图片。
     */

    public static boolean isImage(String fileName) {
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png");
    }

}

