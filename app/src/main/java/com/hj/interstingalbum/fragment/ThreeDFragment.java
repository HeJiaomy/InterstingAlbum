package com.hj.interstingalbum.fragment;

import android.app.Service;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hj.interstingalbum.R;
import com.hj.interstingalbum.base.BaseFragment;
import com.hj.interstingalbum.bean.ThreedBean;
import com.hj.interstingalbum.fragment.viewbinder.ThreedViewBinder;
import com.hj.interstingalbum.roll3d.DividerGridItemDecoration;
import com.hj.interstingalbum.roll3d.OnRecyclerItemClickListener;
import com.hj.interstingalbum.utils.LayoutUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 3D
 */
public class ThreeDFragment extends BaseFragment {

    @BindView(R.id.threed_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.threed_banner)
    Banner banner;
    private MultiTypeAdapter myAdapter;
    private List<Object> threedBeans;
    private ItemTouchHelper mItemTouchHelper;

    private List imageViewList;
    private List<String> bannerTitles;

    @Override
    public void initView() {
        myAdapter.register(ThreedBean.class, new ThreedViewBinder(getContext()));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration());
        mRecyclerView.setAdapter(myAdapter);
        setTouch();
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
        imageViewList= new ArrayList<>();
        imageViewList.add(R.mipmap.apple);
        imageViewList.add(R.mipmap.banana);
        imageViewList.add(R.mipmap.nav_icon);
        bannerTitles= new ArrayList<>();
        bannerTitles.add("1月");
        bannerTitles.add("2月");
        bannerTitles.add("3月");
        LayoutUtils.setBanner(banner,imageViewList,bannerTitles);
    }


    private void setTouch() {
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
//                if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                mItemTouchHelper.startDrag(vh);

                //获取系统震动服务
                Vibrator vib = (Vibrator) getContext().getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                vib.vibrate(70);
//                }
            }
        });

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            /**
             * 是否处理滑动事件 以及拖拽和滑动的方向 如果是列表类型的RecyclerView的只存在UP和DOWN，如果是网格类RecyclerView则还应该多有LEFT和RIGHT
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(threedBeans, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(threedBeans, i, i - 1);
                    }
                }
                myAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                myAdapter.notifyItemRemoved(position);
//                threedBeans.remove(position);
            }

            /**
             * 重写拖拽可用
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
                myAdapter.notifyDataSetChanged();
            }
        });
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_three_d;
    }


}
