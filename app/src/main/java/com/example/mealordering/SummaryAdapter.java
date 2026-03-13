package com.example.mealordering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class SummaryAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<SummaryListItem> data;

    public SummaryAdapter(Context context, List<SummaryListItem> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{

        TextView tvNameItem;
        TextView tvItemPrice;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.receipt_item, parent, false);
            vh = new ViewHolder();
            vh.tvNameItem = convertView.findViewById(R.id.tvNameItem);
            vh.tvItemPrice = convertView.findViewById(R.id.tvItemPrice);

            convertView.setTag(vh);

        } else {

            vh = (ViewHolder) convertView.getTag();

        }

        SummaryListItem item = data.get(position);
        vh.tvNameItem.setText(item.iName);
        vh.tvItemPrice.setText(item.iPrice);

        return convertView;
    }
}
