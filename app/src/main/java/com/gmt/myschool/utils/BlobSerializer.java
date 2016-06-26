package com.gmt.myschool.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.activeandroid.serializer.TypeSerializer;
import com.activeandroid.util.SQLiteUtils;

import java.io.ByteArrayOutputStream;

/**
 * Created by user on 6/6/2016.
 */
public class BlobSerializer extends TypeSerializer {


    @Override
    public Class<?> getDeserializedType() {
        return Bitmap.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return SQLiteUtils.SQLiteType.BLOB.getClass();
    }

    @Override
    public  byte[] serialize(Object data) {
        if (data == null) {
            return null;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ((Bitmap)data).compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    @Override
    public Bitmap deserialize(Object data) {
        if (data == null) {
            return null;
        }
        byte[] temp = (byte[]) data;
        Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
        return bitmap;
    }

}
