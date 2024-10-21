package com.zjbti.exam1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zjbti.exam1.adapter.RightAdapter;
import com.zjbti.exam1.bean.FoodBean;

import java.io.Serializable;
import java.util.List;

public class RightFragment extends Fragment {
    private ListView lv_list;

    public RightFragment() {
    }

    public RightFragment getInstance(List<FoodBean> list) {
        RightFragment rightFragment = new RightFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) list);
        rightFragment.setArguments(bundle);
        return rightFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        lv_list = view.findViewById(R.id.food_list);
        if (getArguments() != null) {
            List<FoodBean> list = (List<FoodBean>) getArguments().getSerializable("list");
            RightAdapter adapter = new RightAdapter(getActivity(), list);
            lv_list.setAdapter(adapter);
        }
        return view;
    }
}