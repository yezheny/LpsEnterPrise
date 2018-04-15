package com.yxcl.lpsenterprise.config;

import java.util.ArrayList;
import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/1/5 11:32
 */

public class Constants {

    public static String MOBKEY = "1b8a769191c30";

    public static String BASE_URL = "http://apicloud.mob.com";

    public static String CityAssets = "city.txt";

    public static int page_size = 10;//分页数据条数

    public static String Bugly_AppID = "5548a97ff9";


    //标签数据
    public static List<String> dataSource;

    static {
        dataSource = new ArrayList<>();
        dataSource.add("修改密码");
        dataSource.add("意见反馈");
        dataSource.add("关于");
    }
}
