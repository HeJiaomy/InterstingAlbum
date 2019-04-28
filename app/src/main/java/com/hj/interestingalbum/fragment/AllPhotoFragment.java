package com.hj.interestingalbum.fragment;

import com.hj.interestingalbum.R;
import com.hj.interestingalbum.base.BaseFragment;

/**
 * 所有照片
 */
public class AllPhotoFragment extends BaseFragment {


    @Override
    public void initView() {
        setHasOptionsMenu(true);
    }

    @Override
    public void initData() {

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_all_photo,menu);
////        menu.setGroupVisible(R.menu.menu_main,false);
//    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_all_photo;
    }
}
