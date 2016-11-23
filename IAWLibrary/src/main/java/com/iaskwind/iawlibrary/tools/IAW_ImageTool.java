package com.iaskwind.iawlibrary.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by winston on 16/5/5.
 */
public class IAW_ImageTool {
    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    public static File getCompressImg(Context context,String fileUrl){
        File imgFile = new File(fileUrl);
        Bitmap bitmap =  getSmallBitmap(fileUrl);
        String filePath = context.getCacheDir() + imgFile.getName();
        final File tempfile = new File(filePath);// 压缩图片用到的文件
        try {
            tempfile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(tempfile);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fOut)) {
                fOut.flush();
                fOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempfile;
    }

}
