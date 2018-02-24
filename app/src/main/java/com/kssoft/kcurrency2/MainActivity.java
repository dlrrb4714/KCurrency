package com.kssoft.kcurrency2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class MainActivity extends AppCompatActivity {
    String[] country;
    String[] currencies;
    ListView goodListView;
    ArrayList<String> prices;
    Currency monies;
    Button goodButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        Spinner outerCountrySpinner = (Spinner) findViewById(R.id.outerCountrySpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Country));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        outerCountrySpinner.setAdapter(spinnerAdapter);

        goodListView = (ListView) findViewById(R.id.spinnerListView);
        country = res.getStringArray(R.array.Country);
        currencies = res.getStringArray(R.array.Currency);

        final Spinner outerCountrySpinner2 = (Spinner) findViewById(R.id.outerCountrySpinner);
        //goodListView.setAdapter(new ItemAdapter(this.prices, this.country, this));

        for (int i = 0 ; i < goodListView.getCount() ; i++) {
            View view = goodListView.getChildAt(i);
            Spinner sp = view.findViewById(R.id.countrySpinner);
            EditText et = findViewById(R.id.amountText);
            String source =RequestURLMaker.CurrencyFinder(outerCountrySpinner2.getSelectedItem().toString(),country,currencies);
            String destination = RequestURLMaker.CurrencyFinder(sp.getSelectedItem().toString(),country,currencies);
            String amount = et.getText().toString();
            String url = RequestURLMaker.RequestURLMaker(source, destination, amount);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    monies = CurrencyJSONParser.parseData(response);
                    prices.add(monies.getAmount());
                    //tv.setText(monies.getAmount());
                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    prices.add("ERROR");
                    //tv.setText("SCREAM IT DIDN'T WORK");
                }
            });
            queue.add(request);
        }
        
        goodButton = (Button) findViewById(R.id.goButton);
        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0 ; i < goodListView.getCount() ; i++) {
                    View view = goodListView.getChildAt(i);
                    Spinner sp = view.findViewById(R.id.countrySpinner);
                    EditText et = findViewById(R.id.amountText);
                    String source =RequestURLMaker.CurrencyFinder(outerCountrySpinner2.getSelectedItem().toString(),country,currencies);
                    String destination = RequestURLMaker.CurrencyFinder(sp.getSelectedItem().toString(),country,currencies);
                    String amount = et.getText().toString();
                    String url = RequestURLMaker.RequestURLMaker(source, destination, amount);
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            monies = CurrencyJSONParser.parseData(response);
                            prices.add(monies.getAmount());
                            //tv.setText(monies.getAmount());
                        }
                    }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            prices.add("ERROR");
                            //tv.setText("SCREAM IT DIDN'T WORK");
                        }
                    });
                    queue.add(request);
                }

            }
        });
        /*for (int i = 0 ; i < goodListView.getCount() ; i ++) {
            View v = goodListView.getChildAt(i);
            Spinner sp = v.findViewById(R.id.countrySpinner);
            EditText et = findViewById(R.id.amountText);
            String source =RequestURLMaker.CurrencyFinder(outerCountrySpinner.getSelectedItem().toString(),country,currencies);
            String destination = RequestURLMaker.CurrencyFinder(sp.getSelectedItem().toString(),country,currencies);
            String amount = et.getText().toString();
            String url = RequestURLMaker.RequestURLMaker(source, destination, amount);
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    monies = CurrencyJSONParser.parseData(response);
                    prices.add(monies.getAmount());
                    //tv.setText(monies.getAmount());
                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    prices.add("ERROR");
                    //tv.setText("SCREAM IT DIDN'T WORK");
                }
            });
            queue.add(request);
        } */
        goodListView.setAdapter(new ItemAdapter(this.prices, this.country, this));
        /*final TextView testTextView = (TextView)findViewById(R.id.testTextView);
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
        */
    }
}