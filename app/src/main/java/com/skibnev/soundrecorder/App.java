package com.skibnev.soundrecorder;

import android.app.Application;

import com.skibnev.soundrecorder.entity.DaoMaster;
import com.skibnev.soundrecorder.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private static App instance;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();
        initDatabase();
    }

    private void initApplication() {
        instance = this;
    }

    private void initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "sound-records-db");
        Database database = helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public static App getInstance() {
        return instance;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}