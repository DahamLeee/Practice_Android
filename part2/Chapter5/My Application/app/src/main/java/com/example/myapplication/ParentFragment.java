package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ParentFragment extends Fragment implements View.OnClickListener{
    public static ParentFragment newInstance() {
        return new ParentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_parent, container, false);

        Button btn_one, btn_tow;
        btn_one = (Button) fv.findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_tow = (Button) fv.findViewById(R.id.btn_tow);
        btn_tow.setOnClickListener(this);

        return fv;
    }

    @Override
    public void onClick(View view) {

        Fragment fg;
        switch (view.getId()) {
            case R.id.btn_one:
                fg = ChildOneFragment.newInstance();
                setChildFragment(fg);
                break;
            case R.id.btn_tow:
                fg = ChildTwoFragment.newInstance();
                setChildFragment(fg);
                break;
        }
    }

    private void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            childFt.replace(R.id.child_fragment_container, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    }
}
