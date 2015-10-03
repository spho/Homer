package com.example.christoph.homer;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;


/**
 * Created by Christoph on 03.10.2015.
 */
public class DataCache {

    int nrOfFiles = 10;
    String[] path = new String[nrOfFiles];
    File[] file = new File[nrOfFiles];

    public DataCache(Context context) {

        for (int i = 0; i < nrOfFiles; i++) {
            path[i] = context.getCacheDir().getAbsolutePath() + ""
            file[i] = new File(path[i]);
        }
        if (file[i].exists()) {
            // File exists
        } else {
            // File does not exist
        }
    }

    String FILENAME = "hello_file";
    String string = "hello world!";

    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
    fos.write(string.getBytes());
    fos.close();

    File file = getCacheDir();

}


}
