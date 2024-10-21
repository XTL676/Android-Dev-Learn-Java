package com.zjbti.exam1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zjbti.exam1.bean.FoodBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LeftFragment leftFragment;
    private TextView tv_recommend,tv_must_buy;
    private RightFragment rightFragment;

    private Map<String, List<FoodBean>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        setData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        clickEvent();
    }

    private void clickEvent() {
        tv_recommend.setOnClickListener((v) -> {
            switchData(map.get("1"));
            tv_recommend.setBackgroundColor(Color.WHITE);
            tv_must_buy.setBackgroundResource(R.color.gray);
        });

        tv_must_buy.setOnClickListener((v) -> {
            switchData(map.get("2"));
            tv_must_buy.setBackgroundColor(Color.WHITE);
            tv_recommend.setBackgroundResource(R.color.gray);
        });

        switchData(map.get("1"));
    }

    private void switchData(List<FoodBean> list) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        rightFragment = new RightFragment().getInstance(list);
        fragmentTransaction.replace(R.id.right_fragment, rightFragment);
        fragmentTransaction.commit();
    }

    private void setData() {
        map = new HashMap<>();
        List<FoodBean> list1 = List.of(
                new FoodBean("三荤五素一份米饭", "月售520 好评度80%", "￥23", R.drawable.recom_one),
                new FoodBean("豪华双人套餐", "月售184 好评度68%", "￥41", R.drawable.recom_two),
                new FoodBean("双人套餐（含两份米饭）", "月售114 好评度60%", "￥32", R.drawable.recom_three)
        );
        List<FoodBean> list2 = List.of(
                new FoodBean("单人经典套餐", "月售26 好评度70%", "￥44", R.drawable.must_buy_one),
                new FoodBean("双人经典套餐", "月售12 好评度50%", "￥132", R.drawable.must_buy_two),
                new FoodBean("三人经典套餐", "月售4 好评度40%", "￥180", R.drawable.must_buy_three)
        );
        map.put("1", list1);
        map.put("2", list2);
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        leftFragment = (LeftFragment) fragmentManager.findFragmentById(R.id.left_fragment);
        tv_recommend = leftFragment.getView().findViewById(R.id.food_recommend);
        tv_must_buy = leftFragment.getView().findViewById(R.id.food_buy);
    }
}