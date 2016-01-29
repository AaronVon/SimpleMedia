package com.pioneer.aaron.simplemedia.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Aaron on 1/28/16.
 */
public class MusicFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String filename) {
        return filename.endsWith(".mp3");
    }
}
