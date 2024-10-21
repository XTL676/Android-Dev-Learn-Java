package org.xlyo.datastorelearn7;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Queue<IHandle> handles = new ArrayDeque<>(List.of(
                (ctx, name, pwd) -> {
                    String[] spl = (String[]) ctx;
                    return name.equals(spl[0]) && pwd.equals(spl[1]);
                },
                (ctx, name, pwd) -> !(name.isEmpty() || pwd.isEmpty())
        ));

        findViewById(R.id.btn_login).setOnClickListener((v) -> {
            TextView name = (TextView) findViewById(R.id.tb_name);
            TextView pwd = (TextView) findViewById(R.id.tb_pwd);

            try(FileInputStream fis = openFileInput("data.bin")) {
                byte[] data = new byte[fis.available()];
                fis.read(data);
                String ctx = new String(data);
                String[] split = ctx.trim().split("\\$");

                AtomicBoolean isSucc = new AtomicBoolean(true);
                handles.forEach(handle -> {
                    if (handle.handle(split, name.getText().toString(), pwd.getText().toString())) return;
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                    isSucc.set(false);
                });
                if (isSucc.get())
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }


            String ctx = name.getText() + "$" + pwd.getText();

            try (FileOutputStream fos = openFileOutput("data.bin", MODE_PRIVATE)) {
                fos.write(ctx.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}