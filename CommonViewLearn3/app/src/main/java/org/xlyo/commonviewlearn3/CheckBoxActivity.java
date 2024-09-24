package org.xlyo.commonviewlearn3;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheckBoxActivity extends AppCompatActivity implements CheckBox.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_box);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.checkBox2)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.checkBox3)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.checkBox4)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        TextView textView = (TextView) findViewById(R.id.textView3);
        String msg = "选择：" + compoundButton.getText();
        textView.setText(msg);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}