package com.gmt.myschool.fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmt.myschool.R;
import com.gmt.sliderlibrary.Animations.DescriptionAnimation;
import com.gmt.sliderlibrary.SliderLayout;
import com.gmt.sliderlibrary.SliderTypes.BaseSliderView;
import com.gmt.sliderlibrary.SliderTypes.TextSliderView;
import com.gmt.sliderlibrary.Tricks.ViewPagerEx;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by user on 6/6/2016.
 */
public class FragmentHome extends SuperFragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;
    TextView mAboutSchool, mSchoolDesc;
    ImageView mSayIt;
    TextToSpeech mTTS;

    @Override
    public String getTitle() {
        return "Home";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        mAboutSchool = (TextView) view.findViewById(R.id.about_school);
        mSchoolDesc = (TextView) view.findViewById(R.id.school_desc);
        mSayIt = (ImageView) view.findViewById(R.id.say);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Tumakuru School", R.drawable.school1);
        file_maps.put("Bangaluru School", R.drawable.school2);
        file_maps.put("Mysuru School", R.drawable.school3);
        file_maps.put("New York School", R.drawable.school4);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }

        mTTS = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTTS.setLanguage(Locale.US);
                } else {
                    mSayIt.setVisibility(View.INVISIBLE);
                }
            }
        });

        mSayIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = mSchoolDesc.getText().toString();
                if (mTTS != null)
                    if (mTTS.isSpeaking()) {
                        mTTS.stop();
                    } else {
                        mTTS.speak(data, TextToSpeech.QUEUE_FLUSH, null);
                    }
            }
        });

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        mSchoolDesc.setText(getResources().getString(R.string.school_desc));
        return view;
    }

    @Override
    public void onStop() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }
}
