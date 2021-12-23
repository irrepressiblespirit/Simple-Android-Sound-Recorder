package com.skibnev.soundrecorder;

import com.skibnev.soundrecorder.entity.SoundRecord;
import com.skibnev.soundrecorder.entity.SoundRecordDao;
import com.skibnev.soundrecorder.listeners.OnDatabaseChangedListener;

import java.util.Comparator;

public class DBHelper {

    private static DBHelper instance;

    private static OnDatabaseChangedListener mOnDatabaseChangedListener;

    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public static void setOnDatabaseChangedListener(OnDatabaseChangedListener listener) {
        mOnDatabaseChangedListener = listener;
    }

    public RecordingItem getItemAt(int position) {
        SoundRecordDao soundRecordDao = App.getInstance().getDaoSession().getSoundRecordDao();
        SoundRecord soundRecord = soundRecordDao.loadAll().get(position);
        if (soundRecord != null) {
            RecordingItem item = new RecordingItem();
            item.setId(soundRecord.getId().intValue());
            item.setName(soundRecord.getRecordingName());
            item.setFilePath(soundRecord.getFilePath());
            item.setLength((int)soundRecord.getLength());
            item.setTime(soundRecord.getTimeAdded());
            return item;
        }
        return null;
    }

    public void removeItemWithId(int id) {
        SoundRecordDao soundRecordDao = App.getInstance().getDaoSession().getSoundRecordDao();
        soundRecordDao.deleteByKey(new Integer(id).longValue());
    }

    public int getCount() {
        SoundRecordDao soundRecordDao = App.getInstance().getDaoSession().getSoundRecordDao();
        return soundRecordDao.loadAll().size();
    }

    public class RecordingComparator implements Comparator<RecordingItem> {
        public int compare(RecordingItem item1, RecordingItem item2) {
            Long o1 = item1.getTime();
            Long o2 = item2.getTime();
            return o2.compareTo(o1);
        }
    }

    public long addRecording(String recordingName, String filePath, long length) {
        SoundRecordDao soundRecordDao = App.getInstance().getDaoSession().getSoundRecordDao();
        SoundRecord record = new SoundRecord();
        record.setRecordingName(recordingName);
        record.setFilePath(filePath);
        record.setLength(length);
        record.setTimeAdded(System.currentTimeMillis());
        long rowId = soundRecordDao.insert(record);

        if (mOnDatabaseChangedListener != null) {
            mOnDatabaseChangedListener.onNewDatabaseEntryAdded();
        }

        return rowId;
    }

    public void renameItem(RecordingItem item, String recordingName, String filePath) {
        SoundRecordDao soundRecordDao = App.getInstance().getDaoSession().getSoundRecordDao();
        SoundRecord record = soundRecordDao.queryBuilder().where(SoundRecordDao.Properties.Id.eq(item.getId())).unique();
        record.setRecordingName(recordingName);
        record.setFilePath(filePath);
        soundRecordDao.update(record);

        if (mOnDatabaseChangedListener != null) {
            mOnDatabaseChangedListener.onDatabaseEntryRenamed();
        }
    }

//    public long restoreRecording(RecordingItem item) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_NAME, item.getName());
//        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH, item.getFilePath());
//        cv.put(DBHelperItem.COLUMN_NAME_RECORDING_LENGTH, item.getLength());
//        cv.put(DBHelperItem.COLUMN_NAME_TIME_ADDED, item.getTime());
//        cv.put(DBHelperItem._ID, item.getId());
//        long rowId = db.insert(DBHelperItem.TABLE_NAME, null, cv);
//        if (mOnDatabaseChangedListener != null) {
//            //mOnDatabaseChangedListener.onNewDatabaseEntryAdded();
//        }
//        return rowId;
//    }
}
