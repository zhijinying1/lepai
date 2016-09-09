package com.zhijinying.lepaiyizu.utils;

/**
 * Created by Administrator on 16-9-3.
 */
public class Path {

    public static String getWelcomePath(){
        return String.format("http://api.fengniao.com/app_ipad/ipad_index.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1");
    }

    public static String getJXPath(int page){
        return String.format("http://api.fengniao.com/app_ipad/news_jingxuan.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&page=%d", page);
    }

    public static String getQCPath(int cid, int page){
        return String.format("http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&cid=%d&page=%d HTTP/1.1", cid, page);
    }

    public static String getYXPath(int cid, int page){
        return String.format("http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&cid=%d&page=%d", cid, page);
    }

    public static String getXYPath(int cid, int page){
        return String.format("http://api.fengniao.com/app_ipad/news_list.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&cid=%d&page=%d", cid, page);
    }

    public static String getRXPath(int fid, int page){
        return String.format("http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&fid=%d&page=%d", fid, page);
    }

    public static String getFGPath(int fid, int page){
        return String.format("http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&fid=%d&page=%d", fid, page);
    }

    public static String getSTPath(int fid, int page){
        return String.format("http://api.fengniao.com/app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&fid=%d&page=%d", fid, page);
    }

    public static String getSMPath(int fid, int page){
        return String.format("http://api.fengniao.com//app_ipad/pic_bbs_list_v2.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&fid=%d&page=%d", fid, page);
    }

    public static String getLTPath(int page){
        return String.format("http://api.fengniao.com/app_ipad/bbs_all_hot.php?appImei=000000000000000&" +
                "osType=Android&osVersion=4.1.1&page=%d", page);
    }
}