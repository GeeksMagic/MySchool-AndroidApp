package com.gmt.myschool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmt.myschool.R;
import com.gmt.myschool.api.APIManager;
import com.gmt.myschool.api.RetrofitAPIService;
import com.gmt.myschool.api.request.SignInRequest;
import com.gmt.myschool.api.response.SignInResponse;
import com.gmt.myschool.api.response.SuperResponse;
import com.gmt.myschool.database.DatabaseManager;
import com.gmt.myschool.database.MySchoolPreferences;
import com.gmt.myschool.database.tables.Parent;
import com.gmt.myschool.ui.SweetAlertDialog;
import com.gmt.myschool.utils.InvalidPasswordException;
import com.rengwuxian.materialedittext.MaterialEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 6/1/2016.
 */
public class LoginActivity extends SuperActivity implements View.OnClickListener {

    private MaterialEditText mUsername, mPassword;
    private Button mLogin, mSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (MaterialEditText) findViewById(R.id.username);
        mPassword = (MaterialEditText) findViewById(R.id.password);

        mLogin = (Button) findViewById(R.id.login);
        mSignup = (Button) findViewById(R.id.signup);

        mLogin.setOnClickListener(this);
        mSignup.setOnClickListener(this);

        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    mLogin.performClick();
                }

                return false;
            }
        });

        if (MySchoolPreferences.getBoolean(this, MySchoolPreferences.SCHOOL_PREFERENCES.IS_LOGGED_IN)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.signup:
                signup();
                break;
        }
    }

    private void test() {
        try {
            showProgressDialog("Signing in...");
            RetrofitAPIService retrofitAPIService = APIManager.getRetrofitServiceInstance();
            final Call<SuperResponse> loginCall = retrofitAPIService.getAllSchools();
            loginCall.enqueue(new Callback<SuperResponse>() {
                @Override
                public void onResponse(Call<SuperResponse> call, Response<SuperResponse> response) {
                    try {
                        String data = AES256.getInstance().decrypt(response.body().getData());
                        dismissProgressDialogSuccess("Login Success:" +data, new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        dismissProgressDialogFailure("Failure:" + e.getMessage(), new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<SuperResponse> call, Throwable t) {
                    dismissProgressDialogFailure("Failure:" + t.getMessage(), new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            dismissProgressDialogFailure("Failure:" + e.getMessage(), new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismiss();
                }
            });
        }
    }


    private void login() {
        if (mUsername.getText().toString().trim().length() <= 0) {
            mUsername.setError("Please Enter Username");
        } else if (mPassword.getText().toString().trim().length() <= 0) {
            mPassword.setError("Please Enter Password");
        } else {
            showProgressDialog("Signing in...");
            try {
                Parent p = DatabaseManager.getInstance(this).signIn(mUsername.getText().toString().trim(), mPassword.getText().toString().trim());
                if (p == null) {
                    dismissProgressDialogFailure("Invalid Username", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    });
                } else {
                    dismissProgressDialog();
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    MySchoolPreferences.setSharedPreferences(this, MySchoolPreferences.SCHOOL_PREFERENCES.IS_LOGGED_IN, true);
                    MySchoolPreferences.setSharedPreferences(this, MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER, mUsername.getText().toString().trim());
                    MySchoolPreferences.setSharedPreferences(this, MySchoolPreferences.SCHOOL_PREFERENCES.STUDENT_NAME, p.getStudent_name());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            } catch (InvalidPasswordException e) {
                e.printStackTrace();
                dismissProgressDialogFailure("Invalid Password", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
            }
        }
    }

//    private void login() {
//        try {
//            if (mUsername.getText().toString().trim().length() <= 0) {
//                mUsername.setError("Please Enter Username");
//            } else if (mPassword.getText().toString().trim().length() <= 0) {
//                mPassword.setError("Please Enter Password");
//            } else {
//                showProgressDialog("Signing in...");
//                RetrofitAPIService retrofitAPIService = APIManager.getRetrofitServiceInstance();
//                SignInRequest request = new SignInRequest();
//                request.setUsername(mUsername.getText().toString().trim());
//                request.setPassword(mPassword.getText().toString().trim());
//                final Call<SignInResponse> loginCall = retrofitAPIService.login(request);
//                loginCall.enqueue(new Callback<SignInResponse>() {
//                    @Override
//                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
//                        if (response.body().getMessage() != null) {
//                            dismissProgressDialogFailure(response.body().getMessage(), new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                    sweetAlertDialog.dismiss();
//                                }
//                            });
//                        } else {
//                            final SignInResponse.User user = response.body().getUser();
//                            dismissProgressDialogSuccess("Login Success", new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                    MySchoolPreferences.setSharedPreferences(LoginActivity.this, MySchoolPreferences.SCHOOL_PREFERENCES.IS_LOGGED_IN, true);
//                                    MySchoolPreferences.setSharedPreferences(LoginActivity.this, MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER, mUsername.getText().toString().trim());
//                                    MySchoolPreferences.setSharedPreferences(LoginActivity.this, MySchoolPreferences.SCHOOL_PREFERENCES.STUDENT_NAME, user.getFirstName());
//                                    sweetAlertDialog.dismiss();
//                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                    finish();
//                                }
//                            });
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<SignInResponse> call, Throwable t) {
//                        dismissProgressDialogFailure("Failure:" + t.getMessage(), new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                sweetAlertDialog.dismiss();
//                            }
//                        });
//                    }
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            dismissProgressDialogFailure("Failure:" + e.getMessage(), new SweetAlertDialog.OnSweetClickListener() {
//                @Override
//                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                    sweetAlertDialog.dismiss();
//                }
//            });
//        }
//    }

    private void signup() {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
