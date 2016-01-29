package com.pioneer.aaron.simplemedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.pioneer.aaron.simplemedia.activities.MusicActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_music})
    public void chooseFun(Button button) {
        switch (button.getId()) {
            case R.id.bt_music:
                startActivity(new Intent(this, MusicActivity.class));
                break;

        }
    }
}
