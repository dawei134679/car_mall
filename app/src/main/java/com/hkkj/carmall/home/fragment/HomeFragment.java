package com.hkkj.carmall.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hkkj.carmall.R;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.utils.CheckPermissionUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeFragment extends BaseFragment implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private FrameLayout frameLayout;

    private RadioButton rbsCar;

    private RadioButton rbbCar;

    private ImageView ivSearch;

    private RadioGroup rgCar;

    private ArrayList<BaseFragment> fragments;

    private int position;

    private Fragment tempFragment;

    private final int REQUEST_CODE = 106;

    private final int MANIFESTPERMISSIONCAMERA = 17;

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        frameLayout = view.findViewById(R.id.home_frameLayout);
        rbbCar = view.findViewById(R.id.rb_bCar);
        rbsCar = view.findViewById(R.id.rb_sCar);
        rgCar = view.findViewById(R.id.rg_car);
        ivSearch = view.findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(this);
        //初始化权限
        initPermission();
        initFragment();
        initListener();
        return view;
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(mContext);
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(getActivity(), permissions, 100);
        }
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

    @Override
    public void onClick(View v) {
        if (v == ivSearch) {
            Intent intent = new Intent(mContext, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    public void onPermissionsGranted(int i, List<String> list) {
        Toast.makeText(mContext, "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode , List<String> perms) {
        Toast.makeText(mContext, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "当前App需要申请camera权限,需要打开设置页面么?")
                .setTitle("权限申请")
                .setPositiveButton("确认")
                .setNegativeButton("取消", null /* click listener */)
                .setRequestCode(REQUEST_CAMERA_PERM)
                .build()
                .show();
}
}
}
