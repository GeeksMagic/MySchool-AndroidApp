package com.gmt.myschool.activity;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gmt.myschool.R;
import com.gmt.myschool.database.DatabaseManager;
import com.gmt.myschool.database.tables.Parent;
import com.gmt.myschool.ui.SweetAlertDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Date;

/**
 * Created by user on 6/1/2016.
 */
public class SignUpActivity extends SuperActivity {

    private MaterialEditText mRollNumber, mStudentName, mStudentClass, mPassword, mConfirmPassword;
    private Button mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mRollNumber = (MaterialEditText) findViewById(R.id.roll_number);
        mStudentName = (MaterialEditText) findViewById(R.id.student_name);
        mStudentClass = (MaterialEditText) findViewById(R.id.student_class);
        mPassword = (MaterialEditText) findViewById(R.id.password);
        mConfirmPassword = (MaterialEditText) findViewById(R.id.confirm_password);
        mSignUp = (Button) findViewById(R.id.signup);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRollNumber.getText().toString().trim().length() <= 0) {
                    mRollNumber.setError("Please Enter Roll Number");
                } else if (mStudentName.getText().toString().trim().length() <= 0) {
                    mStudentName.setError("Please Enter Student Name");
                } else if (mStudentClass.getText().toString().trim().length() <= 0) {
                    mStudentClass.setError("Please Enter Student Class");
                } else if (mPassword.getText().toString().trim().length() <= 0) {
                    mPassword.setError("Please Enter Password");
                } else if (mConfirmPassword.getText().toString().trim().length() <= 0) {
                    mConfirmPassword.setError("Please Enter Confirm Password");
                } else if (!mConfirmPassword.getText().toString().trim().equals(mPassword.getText().toString().trim())) {
                    mConfirmPassword.setError("Confirm Password Mismatch");
                } else {
                    showProgressDialog("Signing up...");
                    Parent parent = new Parent();
                    parent.setRoll_number(mRollNumber.getText().toString().trim());
                    parent.setPassword(mPassword.getText().toString().trim());
                    parent.setStudent_name(mStudentName.getText().toString().trim());
                    parent.setClas(mStudentClass.getText().toString().trim());
                    parent.setCreated_on(new Date().toString());
                    parent.setLast_updated_on(new Date().toString());
                    try {
                        DatabaseManager.getInstance(getBaseContext()).signUp(parent);
                        dismissProgressDialogSuccess("Signed Up Successful", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                finish();
                            }
                        });
                    } catch (SQLiteConstraintException e) {
                        e.printStackTrace();
                        dismissProgressDialogFailure("Roll Number Already Registered!", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                sweetAlertDialog.dismiss();
                            }
                        });
                    }
                }
            }
        });
    }
}