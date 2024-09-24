package org.xlyo.commonviewlearn3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2.监听器设置按钮点击事件
        findViewById(R.id.button2).setOnClickListener((view) -> {
            if (!(view instanceof Button)) return;
            ((Button) view).setText("按钮2被点击了");
        });

        // 3.实现接口设置按钮点击事件
        findViewById(R.id.button3).setOnClickListener(this);
    }

    // 1.xml设置按钮点击事件
    public void onClickBtnM1(View view) {
        if (!(view instanceof Button)) return;
        ((Button) view).setText("按钮1被点击了");
    }

    // 3.实现接口设置按钮点击事件
    @Override
    public void onClick(View view) {
        if (!(view instanceof Button)) return;
        ((Button) view).setText("按钮3被点击了");
    }
}