package com.hkkj.carmall.merchant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hb.dialog.myDialog.MyAlertDialog;
import com.hb.dialog.myDialog.MyAlertInputDialog;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.CategoryBean;
import com.hkkj.carmall.bean.UserInfoBean;
import com.hkkj.carmall.merchant.adapter.CategoryAdapter;
import com.hkkj.carmall.utils.Config;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.hkkj.carmall.utils.ToastUtils;
import com.hkkj.carmall.utils.UtilSharedPreference;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.alibaba.fastjson.JSON.parseArray;

public class CategoryActivity extends Activity {
    
    private Context mContext = this;

    @Bind(R.id.ib_category__back)
    ImageButton ibBack;

    @Bind(R.id.rv_category)
    RecyclerView rvCategory;

    private CategoryAdapter categoryAdapter;

    private List<CategoryBean> cdatas = new ArrayList<CategoryBean>();

    private String shopId;

    MyAlertInputDialog myAlertInputDialog;

    Integer updId;

    CategoryBean categoryItem;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCategoryList();
    }
    @OnClick(R.id.tv_category_add)
    void addCategory(){
        myAlertInputDialog = new MyAlertInputDialog(mContext).builder().setTitle("请输入商品类目名称").setEditText("");

        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCategory(myAlertInputDialog.getResult());
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }

    private void saveCategory(String name) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name",name);
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.CATEGORY_SAVE)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        switch (id) {
                            case 100:
                                if (response != null) {
                                    if (!TextUtils.isEmpty(response)) {
                                        JSONObject jsonObject = JSON.parseObject(response);
                                        //得到状态码
                                        String code = jsonObject.getString("code");
                                        if ("200".equals(code)){
                                            myAlertInputDialog.dismiss();
                                            getCategoryList();

                                        }else {
                                            Log.e("e", "添加商品类目失败");
                                        }
                                    }
                                }
                                break;
                            case 101:
                                break;
                        }
                    }
                });
    }

    private void updateCategory(Integer id, String name) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id",String.valueOf(id));
        params.put("name",name);
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.CATEGORY_UPD)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        switch (id) {
                            case 100:
                                if (response != null) {
                                    if (!TextUtils.isEmpty(response)) {
                                        JSONObject jsonObject = JSON.parseObject(response);
                                        //得到状态码
                                        String code = jsonObject.getString("code");
                                        if ("200".equals(code)){
                                            myAlertInputDialog.dismiss();
                                            getCategoryList();

                                        }else {
                                            Log.e("e", "修改商品类目失败");
                                        }
                                    }
                                }
                                break;
                            case 101:
                                break;
                        }
                    }
                });
    }

    //获取商品分类
    private void getCategoryList() {
        String userInfoStr = UtilSharedPreference.getStringValue(getApplicationContext(), Config.USER_INFO);
        UserInfoBean userInfo = JSONObject.parseObject(userInfoStr, UserInfoBean.class);
        HashMap<String, String> params = new HashMap<String, String>();
        if (userInfo.getShopId() != null){
            shopId = userInfo.getShopId().toString();
        }
        params.put("shopId",shopId);
        OkHttpUtils
            .post()
            .headers(HeadersUtils.getHeaders(params))
            .params(params)
            .url(Constants.get_category_ListBy_ShopId)
            .id(100)
            .build()
            .execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("TAG", "联网失败" + e.getMessage());
                }

                @Override
                public void onResponse(String response, int id) {
                    switch (id) {
                        case 100:
                            if (response != null) {
                                if (!TextUtils.isEmpty(response)) {
                                    JSONObject jsonObject = JSON.parseObject(response);
                                    //得到状态码
                                    String code = jsonObject.getString("code");
                                    if ("200".equals(code)){
                                        cdatas = parseArray(jsonObject.get("data").toString(), CategoryBean.class);
                                        categoryAdapter = new CategoryAdapter(R.layout.item_category, cdatas);
                                        rvCategory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                                        rvCategory.setAdapter(categoryAdapter);
                                        //设置点击事件
                                        rvCategory.addOnItemTouchListener(new OnItemChildClickListener() {
                                            @Override
                                            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                            }

                                            @Override
                                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                                super.onItemChildClick(adapter, view, position);
                                                int itemViewId = view.getId();
                                                categoryItem = cdatas.get(position);
                                                switch (itemViewId){
                                                    case R.id.category_item_name:
                                                        showUpdDialog(categoryItem.getId(),categoryItem.getName());
                                                        break;
                                                    case R.id.category_item_delete:
                                                        //删除

                                                        MyAlertDialog myAlertDialog = new MyAlertDialog(mContext).builder()
                                                                .setTitle("确认吗？")
                                                                .setMsg("删除内容")
                                                                .setPositiveButton("确认", new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        deleteCategoryItem(categoryItem.getId());
                                                                    }
                                                                }).setNegativeButton("取消", new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        return;
                                                                    }
                                                                });
                                                        myAlertDialog.show();
                                                        break;
                                                }
                                            }
                                        });
                                    }else {
                                        Log.e("e", "获取商品类目列表详情失败");
                                    }
                                }
                            }
                            break;
                        case 101:
                            break;
                    }
                }
            });
    }

    private void showUpdDialog(Integer id, String name) {

        updId = id;
        myAlertInputDialog = new MyAlertInputDialog(mContext).builder().setTitle("请输入新的商品类目名称").setEditText(name);

        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCategory(updId,myAlertInputDialog.getResult());
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }

    private void deleteCategoryItem(Integer id) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("shopId",shopId);
        params.put("id",id.toString());
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.CATEGORY_DEL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        switch (id) {
                            case 100:
                                if (response != null) {
                                    if (!TextUtils.isEmpty(response)) {
                                        JSONObject jsonObject = JSON.parseObject(response);
                                        //得到状态码
                                        String code = jsonObject.getString("code");
                                        if ("200".equals(code)){
                                            ToastUtils.showMessage("删除成功");
                                            //重新查询
                                            getCategoryList();

                                        }else {
                                            Log.e("e", "获取商品类目列表详情失败");
                                        }
                                    }
                                }
                                break;
                            case 101:
                                break;
                        }
                    }
                });
    }

}
