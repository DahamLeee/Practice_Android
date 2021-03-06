package com.example.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    public static Fragment1 newInstacne(){
        return new Fragment1();
    }
    ViewPager pager;
    MyPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        pager = rootView.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(18);

        adapter = new  MyPagerAdapter(getActivity().getSupportFragmentManager());

        Fragment4 fragment4 = new Fragment4();
        Fragment5 fragment5 = new Fragment5();
        Fragment6 fragment6 = new Fragment6();

        adapter.addItem(fragment4);
        adapter.addItem(fragment5);
        adapter.addItem(fragment6);
        pager.setClipToPadding(false);
        pager.setPadding(120,0,120,0);

        pager.setAdapter(adapter);
        return rootView;
    }
    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
