package com.skibnev.soundrecorder.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import lombok.Data;

@Data
@Entity(active = true, nameInDb = "sound_records")
public class SoundRecord {

    @Id
    private Long id;

    @Property(nameInDb = "recording_name")
    private String recordingName;

    @Property(nameInDb = "file_path")
    private String filePath;

    @Property(nameInDb = "length")
    private long length;

    @Property(nameInDb = "time_added")
    private long timeAdded;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 328465294)
    private transient SoundRecordDao myDao;

    @Generated(hash = 944377008)
    public SoundRecord(Long id, String recordingName, String filePath, long length,
            long timeAdded) {
        this.id = id;
        this.recordingName = recordingName;
        this.filePath = filePath;
        this.length = length;
        this.timeAdded = timeAdded;
    }

    @Generated(hash = 425226223)
    public SoundRecord() {
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordingName() {
        return this.recordingName;
    }

    public void setRecordingName(String recordingName) {
        this.recordingName = recordingName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getTimeAdded() {
        return this.timeAdded;
    }

    public void setTimeAdded(long timeAdded) {
        this.timeAdded = timeAdded;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1958079261)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSoundRecordDao() : null;
    }
}