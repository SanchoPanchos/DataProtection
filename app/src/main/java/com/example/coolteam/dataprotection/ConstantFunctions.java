package com.example.coolteam.dataprotection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConstantFunctions {

    public static String saveBitmap(Bitmap bitmapImage, Context activity, String folder, String filename) {
        // path to /data/data/dataprotection/app_data/folder
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

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        double height = bitmap.getHeight();
        double width = bitmap.getWidth();
        double proportion;
        if (height > width)
            proportion = 1 / (height / Constants.IMAGES_MAX_SIZE);
        else
            proportion = 1 / (width / Constants.IMAGES_MAX_SIZE);

        Matrix matrix = new Matrix();
        matrix.postScale((float) proportion, (float) proportion);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
