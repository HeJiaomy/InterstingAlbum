package com.hj.interestingalbum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hj.interestingalbum.R;
import com.hj.interestingalbum.activity.PhotoActivity;
import com.hj.interestingalbum.activity.SearchActivity;
import com.hj.interestingalbum.base.BaseFragment;
import com.hj.interestingalbum.bean.ThreedBean;
import com.hj.interestingalbum.dialog.HomePopupWindow;
import com.hj.interestingalbum.fragment.viewbinder.ThreedViewBinder;
import com.hj.interestingalbum.roll3d.DividerGridItemDecoration;
import com.hj.interestingalbum.utils.LayoutUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.drakeet.multitype.MultiTypeAdapter;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_category_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_more_ll)
    LinearLayout moreLl;

    private MultiTypeAdapter myAdapter;
    private ArrayList<ThreedBean> threedBeans;
    public static final String PHOTOBEAN_LIST = null;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        toolbar.setTitle("首页");
        myAdapter.register(ThreedBean.class, new ThreedViewBinder());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration());
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public void initData() {
        threedBeans = new ArrayList<>();
        ThreedBean bean1 = new ThreedBean();
        bean1.setId(1);
        bean1.setTitle("1月");
        bean1.setThreedImg(R.mipmap.apple);

        ThreedBean bean2 = new ThreedBean();
        bean2.setId(2);
        bean2.setTitle("2月");
        bean2.setThreedImg(R.mipmap.banana);

        ThreedBean bean3 = new ThreedBean();
        bean3.setId(3);
        bean3.setTitle("3月");
        bean3.setThreedImg(R.mipmap.strawberry);

        ThreedBean bean4 = new ThreedBean();
        bean4.setId(4);
        bean4.setTitle("4月");
        bean4.setThreedImg(R.mipmap.watermelon);

        ThreedBean bean5 = new ThreedBean();
        bean5.setId(5);
        bean5.setTitle("5月");
        bean5.setThreedImg(R.mipmap.nav_icon);

        ThreedBean bean6 = new ThreedBean();
        bean6.setId(6);
        bean6.setTitle("6月");
        bean6.setThreedImg(R.mipmap.nav_icon);

        threedBeans.add(bean1);
        threedBeans.add(bean2);
        threedBeans.add(bean3);
        threedBeans.add(bean4);
        threedBeans.add(bean5);
        threedBeans.add(bean6);
        myAdapter = new MultiTypeAdapter(threedBeans);
        List imageViewList = new ArrayList<>();
        imageViewList.add(R.mipmap.apple);
        imageViewList.add(R.mipmap.banana);
        imageViewList.add(R.mipmap.nav_icon);
        List<String> bannerTitles = new ArrayList<>();
        bannerTitles.add("1月");
        bannerTitles.add("2月");
        bannerTitles.add("3月");
        LayoutUtils.setBanner(banner, imageViewList, bannerTitles);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.toolbar_search:
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_plus:
                HomePopupWindow.getInstance().init(getActivity()).show(toolbar);
                break;
        }
        return true;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }


    @OnClick(R.id.home_more_ll)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PHOTOBEAN_LIST, threedBeans);
        goActivity(PhotoActivity.class, bundle);
    }
}
