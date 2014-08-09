package com.bignerdranch.android.criminalintent;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jared on 8/8/2014.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private String mDate;
    private boolean mSolved;


    public Crime() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        Date date = new Date();
        mDate = DateFormat.format("EEEE, MMM dd, yyyy", date).toString();

    }

    @Override
    public String toString() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

}
