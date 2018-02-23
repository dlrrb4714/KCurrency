package com.kssoft.kcurrency2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 관리자 on 2018-02-21.
 */

public class CurrencyJSONParser {
    static Currency monies;
    public static Currency parseData(String money, Activity activity) {
        JSONObject amount = null;
        Currency currency = null;
        String[] names = new String[5];
        ListView goodListView = activity.findViewById(R.id.spinnerListView);
        Spinner goodSpinner = activity.findViewById(R.id.outerCountrySpinner);
        names[0]=(goodSpinner.getSelectedItem().toString()+goodListView.getChildAt(0).findViewById(R.id.countrySpinner).toString());
        for (int i=1 ; i < goodListView.getCount() ; i++) {
            names[i]=(goodSpinner.getSelectedItem().toString()+goodListView.getChildAt(i).findViewById(R.id.countrySpinner).toString());
        }
        for (int i=0 ; i < names.length ; i++) {
            try {
                amount = new JSONObject(money);
                monies = new Currency();
                JSONObject obj = amount.getJSONObject("quotes");
                String obj2 = obj.getString(names[i]);
                currency = new Currency();
                currency.setAmount(obj2);
                monies = currency;
                return monies;
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
