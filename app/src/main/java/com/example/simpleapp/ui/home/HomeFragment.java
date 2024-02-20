package com.example.simpleapp.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.simpleapp.R;
import com.example.simpleapp.adapter.ViewPagerAdapter;
import com.example.simpleapp.databinding.FragmentHomeBinding;
import com.example.simpleapp.ui.home.adapter.CustomAdapter;
import com.example.simpleapp.ui.home.adapter.EmojiAdapter;
import com.example.simpleapp.ui.home.adapter.ProfileAdapter;
import com.example.simpleapp.ui.home.model.EmojiModel;
import com.example.simpleapp.ui.home.model.ProfileModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    CustomAdapter pagerAdapter;
    ViewPager viewPager;
    List<EmojiModel> emojiModelList;
    List<ProfileModel> profileModelList;
    LinearLayout sliderDotspanel;
    ViewPagerAdapter viewPagerAdapter;
    private int dotscount;
    private LinearLayout dotsLayout;
    private int profileDotsCount;
    private ImageView[] profleDots;
    private ImageView[] dots;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
         viewPagerAdapter = new ViewPagerAdapter(getContext());
         viewPager = binding.viewPager;
      

        sliderDotspanel = binding.SliderDots;
        
        setSliderAdapter();

        setEmojiAdapter();
        setProfileAdapter();
        setProfilePagerAdapter();
        setDotsLayout();
        View root = binding.getRoot();

        return root;
    }

    private void setProfilePagerAdapter() {
        ProfileAdapter adapter1 = new ProfileAdapter(getContext(),profileModelList);
        ProfileAdapter adapter2 = new ProfileAdapter(getContext(),profileModelList);
        // Create a list of RecyclerViews
        List<RecyclerView> recyclerViews = new ArrayList<>();
        recyclerViews.add(createRecyclerView(adapter1));
        recyclerViews.add(createRecyclerView(adapter2));
         pagerAdapter = new CustomAdapter(recyclerViews);
        binding.profileViewPager.setAdapter(pagerAdapter);
        binding.profileViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {

                setDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

    }
    private void setDotsLayout(){
        profileDotsCount = pagerAdapter.getCount();
        profleDots = new ImageView[profileDotsCount];
        for (int i = 0; i < profileDotsCount; i++) {
            profleDots[i] = new ImageView(getContext());
            profleDots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            binding.dotsLayout.addView(profleDots[i], params);
        }
        setDots(0);
    }
    private void setDots(int position) {
        for (int i = 0; i < profileDotsCount; i++) {
            profleDots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_dot));
        }
        profleDots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
    }

    private RecyclerView createRecyclerView(RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    private void setProfileAdapter() {
        profileModelList = new ArrayList<>();
        profileModelList.add(new ProfileModel(R.drawable.profiel1,"MARK"));
        profileModelList.add(new ProfileModel(R.drawable.profiel2,"MARILYN"));
        profileModelList.add(new ProfileModel(R.drawable.profiel3,"OMAR"));
        profileModelList.add(new ProfileModel(R.drawable.profiel4,"MICKAEL"));
        profileModelList.add(new ProfileModel(R.drawable.profiel1,"ADELE"));
        profileModelList.add(new ProfileModel(R.drawable.profiel2,"KEVIN"));
//        ProfileAdapter profileAdapter = new ProfileAdapter(getContext(),profileModelList);

//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
//
//        binding.rvProfile.setLayoutManager(layoutManager);
//        binding.rvProfile.setAdapter(profileAdapter);
    }

    private void setEmojiAdapter() {
        emojiModelList = new ArrayList<>();
        emojiModelList.add(new EmojiModel(R.drawable.emoji1));
        emojiModelList.add(new EmojiModel(R.drawable.emoji2));
        emojiModelList.add(new EmojiModel(R.drawable.emoji3));
        emojiModelList.add(new EmojiModel(R.drawable.emoji4));
        emojiModelList.add(new EmojiModel(R.drawable.emoji5));
        emojiModelList.add(new EmojiModel(R.drawable.emoji6));
        EmojiAdapter adapter = new EmojiAdapter(getContext(),emojiModelList);
        binding.rvEmoji.setHasFixedSize(true);
        binding.rvEmoji.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.rvEmoji.setAdapter(adapter);

    }

    private void setSliderAdapter() {


        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}