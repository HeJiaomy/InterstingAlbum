package com.hj.interstingalbum.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hj.interstingalbum.R;
import com.hj.interstingalbum.adapter.FragmentAdapter;
import com.hj.interstingalbum.base.BaseActivity;
import com.hj.interstingalbum.fragment.AllPhotoFragment;
import com.hj.interstingalbum.fragment.FullScreenFragment;
import com.hj.interstingalbum.fragment.MineFragment;
import com.hj.interstingalbum.fragment.ThreeDFragment;
import com.hj.interstingalbum.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.main_view_pager)
    ViewPager mainViewPager;
    @BindView(R.id.rBtn_3d)
    RadioButton rBtn3d;
    @BindView(R.id.rBtn_fullScreen)
    RadioButton rBtnFullScreen;
    @BindView(R.id.rBtn_all)
    RadioButton rBtnAll;
    @BindView(R.id.rBtn_mine)
    RadioButton rBtnMine;

    private PopupWindow popupWindow;
    private long pressTime;
    private FragmentAdapter fragmentAdapter;


    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        List<Fragment> fragmentList = new ArrayList<>();
        ThreeDFragment threeDFragment = new ThreeDFragment();
        FullScreenFragment fullScreenFragment = new FullScreenFragment();
        AllPhotoFragment allPhotoFragment = new AllPhotoFragment();
        MineFragment mineFragment = new MineFragment();
        fragmentList.add(threeDFragment);
        fragmentList.add(fullScreenFragment);
        fragmentList.add(allPhotoFragment);
        fragmentList.add(mineFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        mainViewPager.setAdapter(fragmentAdapter);
        mainViewPager.setOffscreenPageLimit(2);//预加载两个页面
        mainViewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.toolbar_search:
                intent.setClass(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_plus:
                showPopupWindow();
                break;
        }
        return true;
    }

    private void showPopupWindow() {

        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //显示在指定控件下;xoff表示x轴的偏移，正值表示向左，负值表示向右；yoff表示相对y轴的偏移，正值是向下，负值是向上；
        popupWindow.showAsDropDown(toolbar, toolbar.getWidth(), 0);
//        popupWindow.setFocusable(true);

        TextView tvToolbarAdd= contentView.findViewById(R.id.tv_toolbar_add);
        TextView toolbarScan= contentView.findViewById(R.id.toolbar_scan);
        TextView toolbarPay= contentView.findViewById(R.id.toolbar_pay);
        tvToolbarAdd.setOnClickListener(this);
        toolbarScan.setOnClickListener(this);
        toolbarPay.setOnClickListener(this);
        //背景变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        popupWindow.update();
        //背景还原
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });
    }

    @OnClick({R.id.rBtn_3d, R.id.rBtn_fullScreen, R.id.rBtn_all, R.id.rBtn_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rBtn_3d:
                setCurrentPager(0);
                break;
            case R.id.rBtn_fullScreen:
                setCurrentPager(1);
                break;
            case R.id.rBtn_all:
                setCurrentPager(2);
                break;
            case R.id.rBtn_mine:
                setCurrentPager(3);
                break;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (mainViewPager.getCurrentItem()){
            case 0:
                menu.findItem(R.id.toolbar_search).setVisible(true);
                menu.findItem(R.id.toolbar_plus).setVisible(true);
                menu.findItem(R.id.toolbar_switch_layout).setVisible(false);
                break;
            case 1:
                menu.findItem(R.id.toolbar_search).setVisible(false);
                menu.findItem(R.id.toolbar_plus).setVisible(false);
                menu.findItem(R.id.toolbar_switch_layout).setVisible(false);
                break;
            case 2:
                menu.findItem(R.id.toolbar_search).setVisible(false);
                menu.findItem(R.id.toolbar_plus).setVisible(false);
                menu.findItem(R.id.toolbar_switch_layout).setVisible(true);
                break;
            case 3:
                menu.findItem(R.id.toolbar_search).setVisible(true);
                menu.findItem(R.id.toolbar_plus).setVisible(false);
                menu.findItem(R.id.toolbar_switch_layout).setVisible(false);
                break;
        }
        invalidateOptionsMenu(); //调用更新menu
        return true;
    }

    /**
     * 点击按钮选中当前页面
     *
     * @param position
     */
    private void setCurrentPager(int position) {
        if (position != mainViewPager.getCurrentItem()) {
            mainViewPager.setCurrentItem(position);
//            fragmentAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 按两次退出程序
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - pressTime > 2000) {
                MyToast.makeText(MainActivity.this, "再按一次退出程序", 0).show();
                pressTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }
        return true;
    }

    /**
     * ViewPager监听
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                checkButton(rBtn3d);
                break;
            case 1:
                checkButton(rBtnFullScreen);
//                getSupportActionBar().hide();
//                getWindow().getDecorView().setSystemUiVisibility(
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//                getWindow().setNavigationBarColor(Color.TRANSPARENT);
//                getWindow().setStatusBarColor(Color.TRANSPARENT);
                break;
            case 2:
                checkButton(rBtnAll);
                break;
            case 3:
                checkButton(rBtnMine);
                break;
        }
    }

    /**
     * 导航栏，状态栏透明
     * @param activity
     */
    public static void setNavigationBarStatusBarTranslucent(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = activity.getActionBar();
        actionBar.hide();
    }

    /**
     * ViewPager滑动时RadioButton同步选中
     *
     * @param radioButton
     */
    private void checkButton(RadioButton radioButton) {
        radioButton.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_toolbar_add:
                MyToast.makeText(MainActivity.this, "添加朋友", 0).show();
                popupWindow.dismiss();
                break;
            case R.id.toolbar_scan:
                MyToast.makeText(MainActivity.this, "扫一扫", 0).show();
                popupWindow.dismiss();
                break;
            case R.id.toolbar_pay:
                MyToast.makeText(MainActivity.this, "收付款", 0).show();
                popupWindow.dismiss();
                break;
        }
    }
}
