package com.eugene.listviewexample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class LogArrayAdapter extends ArrayAdapter<Log> {
    Context mContext;
    public static List<Log> mLogs;

    public LogArrayAdapter(Context context, int textViewResourceId, List<Log> logs) {
        super(context, textViewResourceId);
        mContext = context;
        mLogs = logs;
    }

    public void setLogs(List<Log> logs) {
        mLogs = logs;
    }

    public List<Log> getLogs() {
        return mLogs;
    }

    public void add(Log log) {
        mLogs.add(log);
    }

    public void remove(Log log) {
        LogArrayAdapter.mLogs.remove(log);
    }

    public int getCount() {
        return mLogs.size();
    }

    public Log getItem(int position) {
        return mLogs.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LogRow view = (LogRow) convertView;
        if (view == null) {
            view = new LogRow(mContext);
        }
        Log log = getItem(position);
        view.setLog(log);
        return view;
    }

    /* Not being used

        public void setLogs(List<Log> logs) {
            mLogs = logs;
        }

        public List<Log> getLogs() {
            return mLogs;
        }

        public boolean isItemChecked(int i) {
        return false;
        }
    */

}
