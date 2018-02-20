package com.kssoft.kcurrency2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] country;
    ListView goodListView;
    String[] prices;
    private Gson gson;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        Spinner countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Country));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(spinnerAdapter);
        goodListView = (ListView) findViewById(R.id.spinnerListView);
        country = res.getStringArray(R.array.Country);
        prices = res.getStringArray(R.array.Prices);
        goodListView.setAdapter(new ItemAdapter(this.prices, this.country, this));

        final TextView testTextView = (TextView)findViewById(R.id.testTextView);
        String url = "https://www.amdoren.com/api/currency.php?api_key=X8vBUhRdqgfM3LR9mEv6DtDnVgSFyU&from=USD&to=AUD";

        //GsonBuilder gsonBuilder = new GsonBuilder();
        //gson = gsonBuilder.create();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //List<Currency> currencies = Arrays.asList(gson.fromJson(response, Currency[].class));
                JSONObject Response = new JSONObject(response);

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