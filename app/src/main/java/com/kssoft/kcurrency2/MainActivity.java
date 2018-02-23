package com.kssoft.kcurrency2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] country;
    ListView goodListView;
    String[] prices;
    Currency monies;
    RequestURLMaker instance = new RequestURLMaker(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        Spinner countrySpinner = (Spinner) findViewById(R.id.outerCountrySpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Country));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(spinnerAdapter);

        goodListView = (ListView) findViewById(R.id.spinnerListView);
        country = res.getStringArray(R.array.Country);
        prices = res.getStringArray(R.array.Prices);
        goodListView.setAdapter(new ItemAdapter(this.prices, this.country, this));

        String hello = goodListView.getChildAt(0).findViewById(R.id.countrySpinner).toString();
        for (int i=0 ; i<goodListView.getCount() ; i++) {
            View v = goodListView.getAdapter().getView(i,null,null);
            Spinner sp = (Spinner) v.findViewById(i);
            hello = hello + "," +sp.getSelectedItem().toString();
        }

        final TextView testTextView = (TextView)findViewById(R.id.testTextView);
        String url = "https://www.amdoren.com/api/currency.php?api_key=X8vBUhRdqgfM3LR9mEv6DtDnVgSFyU&from=USD&to=AUD";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                monies = CurrencyJSONParser.parseData(response);
                testTextView.setText(monies.getAmount());
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                testTextView.setText("SCREAM IT DIDN'T WORK");
            }
        });
        queue.add(request);
    }
}