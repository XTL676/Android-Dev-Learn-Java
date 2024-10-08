package org.xlyo.fragmentlearn6;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LeftFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        ListView list = view.findViewById(R.id.left_list_view);
        list.setAdapter(new LeftAdapter(List.of("推荐", "进店必买")));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}