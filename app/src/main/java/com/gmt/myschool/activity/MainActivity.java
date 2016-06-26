package com.gmt.myschool.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.database.DatabaseManager;
import com.gmt.myschool.database.MySchoolPreferences;
import com.gmt.myschool.fragments.ClassTimetableFragment;
import com.gmt.myschool.fragments.FeeDetailsFragment;
import com.gmt.myschool.fragments.FragmentHome;
import com.gmt.myschool.fragments.StudentDetailsFragment;
import com.gmt.myschool.fragments.SuperFragment;
import com.gmt.myschool.fragments.TestScoreDetailsFragment;
import com.gmt.myschool.ui.CircularImageView;
import com.gmt.myschool.ui.SweetAlertDialog;
import com.gmt.myschool.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MainActivity extends SuperActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String UPLOAD_IMAGE = "Upload Profile Pic";
    private final String CHOOSE_FROM_GALLERY = "Choose From Gallery";
    private final String CHOOSE_FROM_CAMERA = "Take Picture";

    DrawerLayout mDrawer = null;
    NavigationView mNavigationView = null;
    FrameLayout mFragmentContainer = null;
    Stack<Map<String, MenuItem>> mTitleStack = null;
    TextView mRollNumber, mStudentName;
    ImageView mProfilePic = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        mTitleStack = new Stack<>();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFragmentContainer = (FrameLayout) findViewById(R.id.container);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mRollNumber = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.home_roll_number);
        mStudentName = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.home_student_name);
        mProfilePic = (CircularImageView) mNavigationView.getHeaderView(0).findViewById(R.id.home_profile_pic);

        MenuItem item = mNavigationView.getMenu().getItem(0);
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
        onNavigationItemSelected(item);

        mRollNumber.setText("Roll Number : " + MySchoolPreferences.getString(this, MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER));
        mStudentName.setText("Student Name : " + MySchoolPreferences.getString(this, MySchoolPreferences.SCHOOL_PREFERENCES.STUDENT_NAME));

    }

    @Override
    protected void onStart() {
        super.onStart();
        mProfilePic = (CircularImageView) mNavigationView.getHeaderView(0).findViewById(R.id.home_profile_pic);
        updateProfilePicInstance(mProfilePic);
        Bitmap bitmap = DatabaseManager.getInstance(this).getImage(MySchoolPreferences.getString(this, MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER));
        if (bitmap != null) {
            mProfilePic.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProfilePicInstance(mProfilePic);
    }

    public void updateProfilePicInstance(ImageView imageView) {
        mProfilePic = imageView;
        registerForContextMenu(mProfilePic);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            if (mTitleStack != null && !mTitleStack.isEmpty()) {
                mTitleStack.pop();
                Map<String, MenuItem> map = mTitleStack.peek();
                String title = (String) map.keySet().toArray()[0];
                getSupportActionBar().setTitle(title);
                if (map.get(title) != null) {
                    map.get(title).setChecked(true);
                }
            }
            super.onBackPressed();
        } else {
            new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Exit The Application?")
                    .setCancelText("No")
                    .setConfirmText("Yes")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                            finish();
                        }
                    })
                    .show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        mDrawer.closeDrawer(GravityCompat.START);
        if (id == R.id.nav_home) {
            replaceFragment(new FragmentHome(), item);
        } else if (id == R.id.nav_student) {
            replaceFragment(new StudentDetailsFragment(), item);
        } else if (id == R.id.nav_test_marks) {
            replaceFragment(new TestScoreDetailsFragment(), item);
        } else if (id == R.id.nav_time_table) {
            replaceFragment(new ClassTimetableFragment(), item);
        } else if (id == R.id.nav_fee) {
            replaceFragment(new FeeDetailsFragment(), item);
        } else if (id == R.id.nav_logout) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure, want to logout?")
                    .setCancelText("No")
                    .setConfirmText("Yes")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                            MySchoolPreferences.clear(getBaseContext());
                            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(SuperFragment fragment, MenuItem item) {
        getSupportActionBar().setTitle(fragment.getTitle());
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            String currentFragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
            if (currentFragmentTag.equals(fragment.getClass().getSimpleName())) {
                Fragment frg = getSupportFragmentManager().findFragmentByTag(currentFragmentTag);
                if (frg != null) {
                    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commitAllowingStateLoss();
                } else {
                    replaceFragment(fragment);
                    if (mTitleStack != null) {
                        Map map = new HashMap<String, MenuItem>();
                        map.put(fragment.getTitle(), item);
                        mTitleStack.add(map);
                    }
                }
            } else {
                replaceFragment(fragment);
                if (mTitleStack != null) {
                    Map map = new HashMap<String, MenuItem>();
                    map.put(fragment.getTitle(), item);
                    mTitleStack.add(map);
                }
            }
        } else {
            replaceFragment(fragment);
            if (mTitleStack != null) {
                Map map = new HashMap<String, MenuItem>();
                map.put(fragment.getTitle(), item);
                mTitleStack.add(map);
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(UPLOAD_IMAGE);
        menu.add(0, v.getId(), 0, CHOOSE_FROM_CAMERA);//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, CHOOSE_FROM_GALLERY);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == CHOOSE_FROM_CAMERA) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, Utils.CAMERA_LIMIT);
            startActivityForResult(intent, Utils.SELECT_CAMERA);
        } else if (item.getTitle() == CHOOSE_FROM_GALLERY) {
            final Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(galleryIntent,
                    "Choose Picture"), Utils.SELECT_GALLERY);
        } else {
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Utils.SELECT_CAMERA:
                try {
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        if (extras != null && extras.containsKey("data")) {
                            Bitmap image = (Bitmap) extras.get("data");
                            mProfilePic.setImageBitmap(DatabaseManager.getInstance(this).storeImage(MySchoolPreferences.getString(this, MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER), image));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Utils.SELECT_GALLERY:
                if (data != null) {
                    Uri selectedImageUri = data.getData();
                    Bitmap bitmap = Utils.getBitmapFromUri(this, selectedImageUri);
                    try {
                        bitmap = Utils.scaleImage(this, selectedImageUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("Exception", "" + e.toString());
                    }
                    mProfilePic.setImageBitmap(DatabaseManager.getInstance(this).storeImage(MySchoolPreferences.getString(this, MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER), bitmap));
                }
                break;
        }
    }
}
