package com.bignerdranch.android.criminalintent;

import android.os.Bundle;

/**
 * Created by jared on 8/8/2014.
 */
public class CrimeFragment extends android.support.v4.app.Fragment {

    private Crime mCrime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

}
