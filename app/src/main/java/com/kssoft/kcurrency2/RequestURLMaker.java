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

    public static String RequestURLMaker(String source, String destination, String amount) {

        return "https://www.amdoren.com/api/currency.php?api_key=X8vBUhRdqgfM3LR9mEv6DtDnVgSFyU&from="+source+"&to="+destination+"&amount="+amount;
    }

    public static String CurrencyFinder(String country, String[] countries, String[] currencies) {
        for (int i=0 ; i<countries.length ; i++) {
            if (country == countries[i]) {
                return currencies[i];
            }
        }
        return null;
    }
}
