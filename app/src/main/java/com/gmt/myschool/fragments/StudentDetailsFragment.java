package com.gmt.myschool.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmt.myschool.R;
import com.gmt.myschool.activity.MainActivity;
import com.gmt.myschool.activity.SuperActivity;
import com.gmt.myschool.database.DatabaseManager;
import com.gmt.myschool.database.MySchoolPreferences;
import com.gmt.myschool.database.tables.Parent;
import com.gmt.myschool.ui.CircularImageView;
import com.gmt.myschool.ui.SweetAlertDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Date;

/**
 * Created by user on 6/1/2016.
 */
public class StudentDetailsFragment extends SuperFragment implements View.OnClickListener {

    MaterialEditText mStudentName, mStudentClass, mParentName, mParentPhone, mParentAddress;
    Button mUpdate;
    ImageView mProfilePic;
    TextView mRollNumber;

    @Override
    public String getTitle() {
        return "Student Details";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_details, container, false);
        mRollNumber = (TextView) view.findViewById(R.id.roll_number);
        mStudentName = (MaterialEditText) view.findViewById(R.id.student_name);
        mStudentClass = (MaterialEditText) view.findViewById(R.id.student_class);
        mParentName = (MaterialEditText) view.findViewById(R.id.parent_name);
        mParentPhone = (MaterialEditText) view.findViewById(R.id.parent_phone);
        mParentAddress = (MaterialEditText) view.findViewById(R.id.parent_address);
        mUpdate = (Button) view.findViewById(R.id.update);
        mProfilePic = (CircularImageView) view.findViewById(R.id.student_profile_pic);
        mUpdate.setOnClickListener(this);

        initUI();

        return view;
    }

    private void initUI() {
        mRollNumber.setText(MySchoolPreferences.getString(getActivity(), MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER));
        mRollNumber.setEnabled(false);
        Parent parent = DatabaseManager.getInstance(getActivity()).getParent(MySchoolPreferences.getString(getActivity(), MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER));
        if (parent != null) {
            mStudentName.setText(parent.getStudent_name());
            mStudentClass.setText(parent.getClas());
            mParentName.setText(parent.getParent_name());
            mParentPhone.setText(parent.getParent_phone());
            mParentAddress.setText(parent.getParent_address());
        }
        Bitmap bitmap = DatabaseManager.getInstance(getActivity()).getImage(MySchoolPreferences.getString(getActivity(), MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER));
        if (bitmap != null) {
            mProfilePic.setImageBitmap(bitmap);
        }
        ((MainActivity) getActivity()).updateProfilePicInstance(mProfilePic);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                mUpdate.setEnabled(false);
                Parent parent = DatabaseManager.getInstance(getActivity()).getParent(MySchoolPreferences.getString(getActivity(), MySchoolPreferences.SCHOOL_PREFERENCES.ROLL_NUMBER));
                if (parent != null) {
                    ((SuperActivity) getActivity()).showProgressDialog("Updating...");
                    parent.setStudent_name(mStudentName.getText().toString().trim());
                    parent.setClas(mStudentClass.getText().toString().trim());
                    parent.setParent_name(mParentName.getText().toString().trim());
                    parent.setParent_phone(mParentPhone.getText().toString().trim());
                    parent.setParent_address(mParentAddress.getText().toString().trim());
                    parent.setLast_updated_on(new Date().toString());
                    parent.save();
                    ((SuperActivity) getActivity()).dismissProgressDialogSuccess("Updated Successfully", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            getActivity().onBackPressed();
                        }
                    });
                }
                mUpdate.setEnabled(true);
                break;
        }
    }
}
