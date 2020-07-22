package ru.dyadischevma.smartfintest.ui.tabs;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.dyadischevma.smartfintest.data.Country;
import ru.dyadischevma.smartfintest.data.entity.Good;
import ru.dyadischevma.smartfintest.R;

public class TabFragment extends Fragment {

    private TabViewModel mViewModel;

    private static final String COUNTRY_NAME = "param1";

    RecyclerView recyclerView;
    GoodsAdapter goodsAdapter;
    List<Good> goodArrayList = new ArrayList<>();
    String countryName;


    public static TabFragment newInstance(int counter) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(COUNTRY_NAME, counter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewGoods);
        goodsAdapter = new GoodsAdapter();
        if (goodArrayList != null) {
            goodsAdapter.setListData(goodArrayList);
        }
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(goodsAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            countryName = Country.getCountryByPosition(getArguments().getInt(COUNTRY_NAME)).name;
        } else {
            countryName = Country.ALL.name;
        }

        mViewModel = new ViewModelProvider(this).get(TabViewModel.class);
        mViewModel.getData(countryName).observe(getViewLifecycleOwner(), dataItems ->
        {
            if (dataItems != null) {
                goodArrayList.clear();
                goodArrayList.addAll(dataItems);
                goodsAdapter.setListData(goodArrayList);
            }
        });
    }
}