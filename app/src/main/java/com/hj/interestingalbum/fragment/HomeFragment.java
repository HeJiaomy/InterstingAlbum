package com.hj.interestingalbum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hj.interestingalbum.R;
import com.hj.interestingalbum.activity.PhotoActivity;
import com.hj.interestingalbum.activity.SearchActivity;
import com.hj.interestingalbum.base.BaseFragment;
import com.hj.interestingalbum.bean.PhotoBean;
import com.hj.interestingalbum.dialog.HomePopupWindow;
import com.hj.interestingalbum.fragment.viewbinder.PhotoViewBinder;
import com.hj.interestingalbum.utils.BannerUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.MultiTypeAdapter;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_category_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.banner)
    Banner banner;

    private MultiTypeAdapter myAdapter;
    private ArrayList<PhotoBean> photoBeans;
    public static final String PHOTOBEAN_LIST = null;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        toolbar.setTitle("首页");
        myAdapter.register(PhotoBean.class, new PhotoViewBinder());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(myAdapter);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(PHOTOBEAN_LIST, photoBeans);
                goActivity(PhotoActivity.class, bundle);
            }
        });
    }

    @Override
    public void initData() {
        photoBeans = new ArrayList<>();
        PhotoBean bean1 = new PhotoBean();
        bean1.setId(1);
        bean1.setTitle("1月");
        bean1.setThreedImg(R.mipmap.ic_launcher);

        PhotoBean bean2 = new PhotoBean();
        bean2.setId(2);
        bean2.setTitle("2月");
        bean2.setThreedImg(R.mipmap.ic_launcher);

        PhotoBean bean3 = new PhotoBean();
        bean3.setId(3);
        bean3.setTitle("3月");
        bean3.setThreedImg(R.mipmap.ic_launcher);

        PhotoBean bean4 = new PhotoBean();
        bean4.setId(4);
        bean4.setTitle("4月");
        bean4.setThreedImg(R.mipmap.ic_launcher);

        PhotoBean bean5 = new PhotoBean();
        bean5.setId(5);
        bean5.setTitle("5月");
        bean5.setThreedImg(R.mipmap.ic_launcher);

        PhotoBean bean6 = new PhotoBean();
        bean6.setId(6);
        bean6.setTitle("6月");
        bean6.setThreedImg(R.mipmap.ic_launcher);

        photoBeans.add(bean1);
        photoBeans.add(bean2);
        photoBeans.add(bean3);
        photoBeans.add(bean4);
        photoBeans.add(bean5);
        photoBeans.add(bean6);
        myAdapter = new MultiTypeAdapter(photoBeans);
        List<Integer> imageViewList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            imageViewList.add(R.mipmap.ic_launcher);
        }
        List<String> bannerTitles = new ArrayList<>();
        bannerTitles.add("1月");
        bannerTitles.add("2月");
        bannerTitles.add("3月");
        BannerUtil.setBanner(banner, imageViewList, bannerTitles);
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
}
