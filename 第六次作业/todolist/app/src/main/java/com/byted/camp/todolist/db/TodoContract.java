package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量

    public static String SQL_CREATE_TABLE =
            "CREATE TABLE todolist " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Content TEXT, Date TEXT, State TEXT, Priority TEXT)";

    public static String SQL_DELETE_TABLE =
            "DROP TABLE todolist IF EXISTS todolist";

    private TodoContract() {
    }
}
