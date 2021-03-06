package com.skibnev.soundrecorder.listeners;

/**
 * Listen for add/rename items in database
 */
public interface OnDatabaseChangedListener{
    void onNewDatabaseEntryAdded();
    void onDatabaseEntryRenamed();
}