package com.zjbti.exam1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjbti.exam1.R;
import com.zjbti.exam1.bean.FoodBean;

import java.util.List;

public class RightAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodBean> list;

    public RightAdapter(Context mContext, List<FoodBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.activity_list_item, null);
            holder = new ViewHolder();
            holder.tv_name = view.findViewById(R.id.food_name);
            holder.tv_sale = view.findViewById(R.id.food_sales);
            holder.tv_price = view.findViewById(R.id.food_price);
            holder.tv_img = view.findViewById(R.id.food_img);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        FoodBean foodBean = list.get(i);
        holder.tv_name.setText(foodBean.getName());
        holder.tv_sale.setText(foodBean.getSales());
        holder.tv_price.setText(foodBean.getPrice());
        holder.tv_img.setBackgroundResource(foodBean.getImg());

        return view;
    }

    private static class ViewHolder {
        TextView tv_name,tv_sale,tv_price;
        ImageView tv_img;
    }
}
