package com.mk.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    /**
     * 初始化
     * @param context 上下文
     * @return
     */
    public DatabaseManager init(Context context)
    {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 初始化参数
     * @param context
     */
    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    /**
     * 获取 UserProfileDao
     * @return
     */
    public final UserProfileDao getDao()
    {
        return mDao;
    }

}
