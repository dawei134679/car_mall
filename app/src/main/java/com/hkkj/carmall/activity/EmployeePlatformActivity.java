package com.hkkj.carmall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hkkj.carmall.R;
import com.hkkj.carmall.utils.CheckPermissionUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class EmployeePlatformActivity extends Activity implements View.OnClickListener, EasyPermissions.PermissionCallbacks{

    @Bind(R.id.ib_employee_platform_back)
    ImageButton ibEpBack;

    @Bind(R.id.rg_ep)
    RadioGroup rgEp;

    @Bind(R.id.rb_ep_all)
    RadioButton epAll;

    @Bind(R.id.rb_ep_dfw)
    RadioButton epDfw;

    @Bind(R.id.rb_ep_ywc)
    RadioButton epYwc;

    @Bind(R.id.iv_ep_scann)
    ImageView epScann;


    private final int REQUEST_CODE = 106;

    private final int MANIFESTPERMISSIONCAMERA = 17;

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_platform);
        ButterKnife.bind(this);
        initListener();

    }

    private void initListener() {
        epScann.setOnClickListener(this);
        ibEpBack.setOnClickListener(this);
        rgEp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_ep_all:
                        break;
                    case R.id.rb_ep_dfw:
                        break;
                    case R.id.rb_ep_ywc:
                        break;
                }
            }
        });
        rgEp.check(R.id.rb_ep_all);
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ibEpBack) {
            finish();
        }
        if (v == epScann) {
            Intent intent = new Intent(this, CaptureActivity.class);
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
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    public void onPermissionsGranted(int i, List<String> list) {
        Toast.makeText(this, "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode , List<String> perms) {
        Toast.makeText(this, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
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
