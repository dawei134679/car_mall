package com.hkkj.carmall.home.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hkkj.carmall.R;
import com.hkkj.carmall.base.BaseFragment;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

    private FrameLayout frameLayout;

    private RadioButton rbsCar;

    private RadioButton rbbCar;

    private ImageView ivSearch;

    private RadioGroup rgCar;

    private ArrayList<BaseFragment> fragments;

    private int position;

    private Fragment tempFragment;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        frameLayout = view.findViewById(R.id.home_frameLayout);
        rbbCar = view.findViewById(R.id.rb_bCar);
        rbsCar = view.findViewById(R.id.rb_sCar);
        rgCar = view.findViewById(R.id.rg_car);
        ivSearch = view.findViewById(R.id.iv_search);
        initFragment();
        initListener();
        return view;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ScarFragment());
        fragments.add(new BcarFragment());
    }

    private void initListener() {
        rgCar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_sCar:
                        position = 0;
                        break;
                    case R.id.rb_bCar:
                        position = 1;
                        break;
                }
                BaseFragment nextFragment = getFragment(position);
                switchFragment(tempFragment, nextFragment);
            }
        });
        rgCar.check(R.id.rb_sCar);
    }


    /**
     *
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }

                    transaction.add(R.id.home_frameLayout, nextFragment, "tagFragment").commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}
