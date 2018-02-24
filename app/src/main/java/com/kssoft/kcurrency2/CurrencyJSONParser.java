package com.kssoft.kcurrency2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 관리자 on 2018-02-21.
 */

public class CurrencyJSONParser {
    static Currency monies;
    public static Currency parseData(String money) {
        JSONObject amount = null;
        Currency currency = null;
        try {
            amount = new JSONObject(money);
            monies = new Currency();
            String obj = amount.getString("amount");
            currency = new Currency();
            currency.setAmount(obj);
            monies = currency;
            return monies;
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
