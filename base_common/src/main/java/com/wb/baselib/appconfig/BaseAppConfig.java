package com.wb.baselib.appconfig;

import java.util.Map;

/**
 * Created by desin on 2017/4/12.
 * http://zby.u.com/index.php?g=Client&m=Login&a=login
 */

public abstract class BaseAppConfig {
    private static final String HTTP_HEAD = "http://";
    private static final String HTTP = "";
    public static final String BaseUrl = HTTP_HEAD + HTTP;
    //glide缓存的文件
    public static final String GLIDE_PATH="basel_catch";
    //缓存大小
    public static final int GLIDE_SIZE=150 * 1000 * 1000;
    //每页最大显示
    public static final int MXAPAGESIZE=10;
    //空的map
    public static Map<String, Long> map;
    //强制下线
    public static final int EXITAPPCODE=201;
}
