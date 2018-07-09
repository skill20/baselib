package com.kksearch;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.library.base.BaseActivity;
import com.library.imageloader.ImageLoader;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.getInstance().display("http://p6jtfbpem.bkt.clouddn.com/img/901c205f-6c2d-445e-9e24-e2b9887de219.jpg", iv);
            }
        });
    }
}
