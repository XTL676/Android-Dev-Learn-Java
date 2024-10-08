package org.xlyo.fragmentlearn6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

public class RightFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        ListView list = view.findViewById(R.id.right_list_view);
        list.setAdapter(new RightAdapter(List.of(new FoodBean("三荤一素","月销114 好评度1024%", "￥648.0"))));
        return view;
    }
}