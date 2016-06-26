package com.gmt.myschool.activity;

import android.support.v7.app.AppCompatActivity;

import com.gmt.myschool.ui.SweetAlertDialog;

/**
 * Created by user on 6/5/2016.
 */
public class SuperActivity extends AppCompatActivity {

    SweetAlertDialog mSyncingDialog = null;

    public void showProgressDialog(String message) {
        mSyncingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setContentText(message);
        mSyncingDialog.show();
        mSyncingDialog.showCancelButton(false);
        mSyncingDialog.setCancelable(false);
    }

    public void dismissProgressDialogSuccess(String message, SweetAlertDialog.OnSweetClickListener listener) {
        if (mSyncingDialog != null)
            mSyncingDialog.setContentText(message)
                    .setConfirmText("OK")
                    .setConfirmClickListener(listener)
                    .showCancelButton(false)
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
    }

    public void dismissProgressDialogFailure(String message, SweetAlertDialog.OnSweetClickListener listener) {
        if (mSyncingDialog != null)
            mSyncingDialog.setContentText(message)
                    .setConfirmText("OK")
                    .setConfirmClickListener(listener)
                    .showCancelButton(false)
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
    }

    public void dismissProgressDialog() {
        if (mSyncingDialog != null)
            mSyncingDialog.dismiss();
    }
}
