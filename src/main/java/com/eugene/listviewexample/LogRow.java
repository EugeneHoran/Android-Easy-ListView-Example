package com.eugene.listviewexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Eugene on 3/30/2015.
 */
public class LogRow extends LinearLayout {
    Context mContext;
    Log mLog;

    public LogRow(Context context) {
        super(context);
        mContext = context;
        setup();
    }

    public LogRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setup();
    }

    private void setup() {
        LayoutInflater inflater1 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater1.inflate(R.layout.log_row, this);
    }

    public void setLog(Log log) {
        mLog = log;
        TextView tvName1 = (TextView) findViewById(R.id.txtItem);
        tvName1.setText(mLog.getItem());
    }
}