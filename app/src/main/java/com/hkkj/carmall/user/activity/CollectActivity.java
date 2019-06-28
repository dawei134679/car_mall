package com.hkkj.carmall.user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hkkj.carmall.R;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.user.fragment.CommodityFragment;
import com.hkkj.carmall.user.fragment.MerchantFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CollectActivity extends FragmentActivity {

    private ImageButton ib_back;

    @Bind(R.id.collect_frameLayout)
    FrameLayout frameLayout;

    @Bind(R.id.rb_commodity)
    RadioButton rbCommodity;

    @Bind(R.id.rb_merchant)
    RadioButton rbMerchant;

    @Bind(R.id.rg_collect)
    RadioGroup rgCollect;

    private ArrayList<BaseFragment> fragments;

    private int position;

    private BaseFragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ib_back = findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ButterKnife.bind(this);
        initFragment();
        initListener();
    }
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new CommodityFragment());
        fragments.add(new MerchantFragment());

    }

    private void initListener() {
        rgCollect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_commodity:
                        position = 0;
                        break;
                    case R.id.rb_merchant:
                        position = 1;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(mContext, baseFragment);
            }
        });
        rgCollect.check(R.id.rb_commodity);
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
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.collect_frameLayout, nextFragment).commit();
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
