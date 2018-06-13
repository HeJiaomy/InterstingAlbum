package com.hj.interstingalbum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hj.interstingalbum.R;
import com.hj.interstingalbum.adapter.FragmentAdapter;
import com.hj.interstingalbum.adapter.PopupWindowAdapter;
import com.hj.interstingalbum.base.BaseActivity;
import com.hj.interstingalbum.bean.PopupBean;
import com.hj.interstingalbum.fragment.AllFragment;
import com.hj.interstingalbum.fragment.FullFragment;
import com.hj.interstingalbum.fragment.MineFragment;
import com.hj.interstingalbum.fragment.ThreeDFragment;
import com.hj.interstingalbum.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener {

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
    private ArrayList<PopupBean> popList;
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
        List<Fragment> fragmentList= new ArrayList<>();
        ThreeDFragment threeDFragment= new ThreeDFragment();
        FullFragment fullFragment= new FullFragment();
        AllFragment allFragment= new AllFragment();
        MineFragment mineFragment= new MineFragment();
        fragmentList.add(threeDFragment);
        fragmentList.add(fullFragment);
        fragmentList.add(allFragment);
        fragmentList.add(mineFragment);
        fragmentAdapter= new FragmentAdapter(getSupportFragmentManager(),fragmentList);
        mainViewPager.setAdapter(fragmentAdapter);
        mainViewPager.setOffscreenPageLimit(2);//预加载两个页面
        mainViewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
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
        ListView popupListView = new ListView(this);
        popupListView.setDividerHeight(1);
        popList = new ArrayList<>();
        PopupWindowAdapter adapter = new PopupWindowAdapter(popList, this);

        PopupBean popupBean1 = new PopupBean();
        popupBean1.setImage(getResources().getDrawable(R.mipmap.toolbar_add_friends));
        popupBean1.setTitle("添加朋友");
        popList.add(popupBean1);
        PopupBean popupBean2 = new PopupBean();
        popupBean2.setImage(getResources().getDrawable(R.mipmap.toolbar_scan));
        popupBean2.setTitle("扫一扫");
        popList.add(popupBean2);
        PopupBean popupBean3 = new PopupBean();
        popupBean3.setImage(getResources().getDrawable(R.mipmap.toolbar_pay));
        popupBean3.setTitle("收付款");
        popList.add(popupBean3);

        popupListView.setAdapter(adapter);
        popupListView.setOnItemClickListener(this);

        popupWindow = new PopupWindow(popupListView, (int) (toolbar.getMeasuredWidth() / 2.5), ViewGroup.LayoutParams.WRAP_CONTENT);
        //显示在指定控件下
        popupWindow.showAsDropDown(toolbar, toolbar.getWidth(), 0);
        popupWindow.setFocusable(true);
        popupWindow.update();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, popList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
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
     * @param position
     */
    private void setCurrentPager(int position) {
        if (position!=mainViewPager.getCurrentItem()){
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
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
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
     * @param radioButton
     */
    private void checkButton(RadioButton radioButton) {
        radioButton.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
