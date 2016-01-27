package com.pioneer.aaron.simplemedia.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pioneer.aaron.simplemedia.R;

import butterknife.ButterKnife;

/**
 * Created by Aaron on 1/27/16.
 */
public class MusicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ButterKnife.bind(this);
        
    }
}
