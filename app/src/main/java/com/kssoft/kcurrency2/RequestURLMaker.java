package com.kssoft.kcurrency2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Created by 관리자 on 2018-02-22.
 */

public class RequestURLMaker {

    public RequestURLMaker(Activity _activity) {
        Activity activity = _activity;
        ListView goodListView = (ListView) activity.findViewById(R.id.spinnerListView);
        String hello = goodListView.getChildAt(0).findViewById(R.id.countrySpinner).toString();
        View v;
        Spinner sp;
        for (int i=0 ; i<goodListView.getCount() ; i++) {
            v = goodListView.getAdapter().getView(i,null,null);
            sp = (Spinner) v.findViewById(i);
            hello = hello + "," + sp.getSelectedItem().toString();
        }
    }
}
