package ru.dyadischevma.smartfintest.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import ru.dyadischevma.smartfintest.R;
import ru.dyadischevma.smartfintest.data.enums.Country;
import ru.dyadischevma.smartfintest.ui.tabs.ViewPagerAdapter;

public class GoodsFragment extends Fragment {
    private final ArrayList<String> TABS = Country.getCountryNames();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        ViewPager2 viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(view.getContext(), R.color.colorText),
                ContextCompat.getColor(view.getContext(), R.color.colorSelectedText)
        );
        new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> tab.setText(TABS.get(position))
        ).attach();
    }
}