package com.hj.interestingalbum.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hj.interestingalbum.R;
import com.hj.interestingalbum.adapter.FragmentAdapter;
import com.hj.interestingalbum.base.BaseActivity;
import com.hj.interestingalbum.fragment.AllPhotoFragment;
import com.hj.interestingalbum.fragment.FullScreenFragment;
import com.hj.interestingalbum.fragment.HomeFragment;
import com.hj.interestingalbum.fragment.MineFragment;
import com.hj.interestingalbum.utils.MyToast;

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
        HomeFragment homeFragment = new HomeFragment();
        FullScreenFragment fullScreenFragment = new FullScreenFragment();
        AllPhotoFragment allPhotoFragment = new AllPhotoFragment();
        MineFragment mineFragment = new MineFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(fullScreenFragment);
        fragmentList.add(allPhotoFragment);
        fragmentList.add(mineFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        mainViewPager.setAdapter(fragmentAdapter);
        mainViewPager.setOffscreenPageLimit(2);//预加载两个页面
        mainViewPager.addOnPageChangeListener(this);
    }

    private void showPopupWindow() {


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

    /**
     * 点击按钮选中当前页面
     *
     * @param position
     */
    private void setCurrentPager(int position) {
        if (position != mainViewPager.getCurrentItem()) {
            mainViewPager.setCurrentItem(position);
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
        switch (v.getId()) {
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
