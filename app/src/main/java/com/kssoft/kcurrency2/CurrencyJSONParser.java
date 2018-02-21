package com.kssoft.kcurrency2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
