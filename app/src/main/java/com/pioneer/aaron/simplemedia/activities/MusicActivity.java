package com.pioneer.aaron.simplemedia.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.pioneer.aaron.simplemedia.R;
import com.pioneer.aaron.simplemedia.utils.MusicFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Aaron on 1/27/16.
 * @author Aaron
 */
public class MusicActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private List<String> musicList;
    @Bind(R.id.music_listview)
    ListView listView;
    private ArrayAdapter<String> adapter;
    private int currentIndex = 0;
    private String MUSIC_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        MUSIC_PATH = getSDPath() + "/Music/";
        mediaPlayer = new MediaPlayer();
        musicList = getData();

        /*for (String name : musicList) {
            Log.d("List----", name);
        }*/

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
//        listView = new ListView(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentIndex = position;
                playmusic(MUSIC_PATH + musicList.get(currentIndex));
            }
        });
    }

    /**
     * get music list
     * */
    private List<String> getData() {
        List<String> tmp = new ArrayList<>();
        File path = new File(MUSIC_PATH);

        if (path.listFiles(new MusicFilter()).length > 0) {
            for (File file : path.listFiles(new MusicFilter())) {
                tmp.add(file.getName());
            }
        }

        return tmp;
    }


    @OnClick({R.id.bt_play, R.id.bt_pause, R.id.bt_stop, R.id.bt_next, R.id.bt_pre})
    public void musicFun(Button button) {
        switch (button.getId()) {
            case R.id.bt_play:
                playmusic(MUSIC_PATH + musicList.get(currentIndex));
                break;
            case R.id.bt_pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
                break;
            case R.id.bt_stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                }
                break;
            case R.id.bt_next:
                nextmusic();
                break;
            case R.id.bt_pre:
                premusic();
                break;
        }
    }

    /**
     * start playing
     * @param path specific song path
     * */
    private void playmusic(String path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    nextmusic();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * next song
     * */
    private void nextmusic() {
        if (++currentIndex >= musicList.size()) {
            currentIndex = 0;
        } else {
            playmusic(MUSIC_PATH + musicList.get(currentIndex));
        }
    }

    /**
     * previous song
     * */
    private void premusic() {
        if (--currentIndex < 0) {
            currentIndex = musicList.size();
        }
        playmusic(MUSIC_PATH + musicList.get(currentIndex));
    }

    private String getSDPath() {
        File sdDir = null;
        boolean sdExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        Log.d("SDCARD-----", sdDir.toString());
        return sdDir.toString();
    }
}
