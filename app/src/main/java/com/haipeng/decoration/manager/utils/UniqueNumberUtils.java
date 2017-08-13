package com.haipeng.decoration.manager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2017/8/13.
 */

public class UniqueNumberUtils {

    public static Long getUniqueNumber() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        int n = new Random().nextInt(9999999);
        String d = format.format(new Date());
        long l = Long.valueOf(n + d);
        return l;
    }

}
