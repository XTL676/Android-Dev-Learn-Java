package org.xlyo.fragmentlearn6;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RightAdapter extends BaseAdapter {
    private final List<FoodBean> list;

    public RightAdapter(List<FoodBean> list) {
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_food_list_item, null);
        TextView title = view.findViewById(R.id.food_title);
        TextView desc = view.findViewById(R.id.food_desc);
        TextView price = view.findViewById(R.id.food_price);
        FoodBean food = (FoodBean) getItem(i);
        title.setText(food.getName());
        desc.setText(food.getSales());
        price.setText(food.getPrice());
        return view;
    }
}
