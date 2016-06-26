package com.gmt.myschool.database;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;

import com.activeandroid.query.Select;
import com.gmt.myschool.database.tables.Parent;
import com.gmt.myschool.utils.InvalidPasswordException;

import java.io.ByteArrayOutputStream;

/**
 * Created by user on 6/1/2016.
 */
public class DatabaseManager {

    private final String TAG = getClass().getSimpleName();

    private static DatabaseManager mSelf = null;

    private static DatabaseHandler mDatabase = null;

    public static DatabaseManager getInstance(Context context) {
        if (mSelf == null) {
            mSelf = new DatabaseManager();
        }

        if (mDatabase == null) {
            mDatabase = new DatabaseHandler(context);
        }
        return mSelf;
    }

    public void signUp(Parent parent) {
        if (parent.save() == -1)
            throw new SQLiteConstraintException();
    }

    public Parent signIn(String username, String password) throws InvalidPasswordException {
        Parent parent = new Select().from(Parent.class).where("roll_number = ?", username).executeSingle();
        if (parent != null) {
            if (parent.getPassword().equals(password)) {
                return parent;
            } else {
                throw new InvalidPasswordException();
            }
        }
        return null;
    }

    public Parent getParent(String username) {
        return new Select().from(Parent.class).where("roll_number = ?", username).executeSingle();
    }

    public Bitmap storeImage(String roll_number, Bitmap bitmap) {
        Parent parent = new Select().from(Parent.class).where("roll_number = ?", roll_number).executeSingle();
        if (parent != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            long id = mDatabase.insertImage(byteArray);
            parent.setImage_id("" + id);
            parent.save();
        }
        return bitmap;
    }

    public Bitmap getImage(String roll_number) {
        Parent parent = new Select().from(Parent.class).where("roll_number = ?", roll_number).executeSingle();
        if (parent != null && parent.getImage_id() != null && !parent.getImage_id().isEmpty()) {
            return mDatabase.readImage(parent.getImage_id());
        }
        return null;
    }
}
