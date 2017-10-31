package com.example.coolteam.dataprotection;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConstantFunctions {

    public static String saveBitmap(Bitmap bitmapImage, Context activity, String folder, String filename) {
        // path to /data/data/yourapp/app_data/imageDir
        File directory = activity.getExternalFilesDir(folder);

        File mypath = new File(directory, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Log.i("TAG", "a new image was created:\n " + directory.getAbsolutePath() + "/" + filename);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "bitmap not saved");
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath() + "/" + filename;
    }
}
