package ru.dyadischevma.smartfintest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import ru.dyadischevma.smartfintest.R;
import ru.dyadischevma.smartfintest.data.Country;
import ru.dyadischevma.smartfintest.ui.home.tabs.ViewPagerAdapter;

public class GoodsFragment extends Fragment {
    ViewPagerAdapter viewPagerAdapter;
    ViewPager2 viewPager;

    ArrayList<String> tabs = Country.getCountryNames();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(
                getResources().getColor(R.color.colorText),
                getResources().getColor(R.color.colorSelectedText)
        );
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabs.get(position))
        ).attach();
    }
}