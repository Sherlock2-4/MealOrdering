package com.example.mealordering;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class MainDishAdapter extends BaseAdapter {

    DecimalFormat df;

    LayoutInflater inflater;
    List<MainDishListItem> data;

    public MainDishAdapter(Context context, List<MainDishListItem> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        df = new DecimalFormat("###.00");
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

    static class ViewHolder {

        ImageView ivDish;
        TextView tvDishName;
        TextView tvDishPrice;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null) {

            convertView = inflater.inflate(R.layout.menu_item, parent, false);
            vh = new ViewHolder();
            vh.ivDish = convertView.findViewById(R.id.ivDish);
            vh.tvDishName = convertView.findViewById(R.id.tvName);
            vh.tvDishPrice = convertView.findViewById(R.id.tvPrice);

            convertView.setTag(vh);

        } else {

            vh = (ViewHolder) convertView.getTag();

        }

        MainDishListItem item = data.get(position);
        vh.ivDish.setImageDrawable(item.dMainDish);
        vh.tvDishName.setText(item.mainDishName);
        vh.tvDishPrice.setText("₱  " + df.format(item.mainDishPrice));

        return convertView;
    }
}
