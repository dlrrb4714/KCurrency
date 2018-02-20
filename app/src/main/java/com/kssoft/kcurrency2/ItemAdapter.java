package com.kssoft.kcurrency2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
    Context mContext;
    String[] mData;
    String[] mSpinnerItems;

    public ItemAdapter(String[] data, String[] spinnerItems, Context context) {
        this.mData = data;
        this.mSpinnerItems = spinnerItems;
        this.mContext = context;
    }

    public int getCount() {
        return this.mData.length;
    }

    public Object getItem(int position) {
        return this.mData[position];
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.listview_detail, null);
        }
        Spinner spinner = (Spinner) view.findViewById(R.id.countrySpinner);
        ((TextView) view.findViewById(R.id.priceTextView)).setText(this.mData[position]);
        spinner.setAdapter(new ArrayAdapter(this.mContext,android.R.layout.simple_spinner_item, this.mSpinnerItems));
        return view;
    }
}