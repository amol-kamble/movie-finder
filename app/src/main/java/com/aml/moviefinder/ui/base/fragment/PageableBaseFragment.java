package com.aml.moviefinder.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.aml.moviefinder.R;
import com.aml.moviefinder.core.utils.ui.EndlessRecyclerViewScrollListener;
import com.aml.moviefinder.ui.base.model.Pagination;
import com.aml.moviefinder.ui.base.view.PageableBaseViewBasic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 30/9/16.
 */

/**
 * @param <T> List Item
 * @param <E> Adapter
 */
public abstract class PageableBaseFragment<T, E extends RecyclerView.Adapter> extends BaseFragment implements PageableBaseViewBasic {

    public View rootView;
    public ImageView imgEmptyStateIcon;
    public TextView txtEmptyState;
    private EndlessRecyclerViewScrollListener scrollListener;
    private LinearLayoutManager llm;
    public Pagination pagination;
    public RecyclerView list;
    public List<T> itemList = new ArrayList<>();
    public E adapter;
    public int listOrientation = LinearLayoutManager.VERTICAL;
    public SwipeRefreshLayout swipeRefreshLayout;
    Boolean isSwipeToRefresh = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(listOrientation);
        list.setLayoutManager(llm);
        try{
            imgEmptyStateIcon = (ImageView) rootView.findViewById(R.id.img_empty_icon);
            txtEmptyState = (TextView) rootView.findViewById(R.id.txt_empty_text);
        }catch (Exception e){
            e.printStackTrace();
        }
        setSwipeToRefreshColor();
    }

    @Override
    public void initListener() {
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if(swipeRefreshLayout==null || !swipeRefreshLayout.isRefreshing()){
                    pagination.setNumber(page);
                    PageableBaseFragment.this.onLoadMore(pagination);
                }else{
                    scrollListener.resetState();
                }
            }
        };
        list.addOnScrollListener(scrollListener);

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    isSwipeToRefresh = true;
                    refreshList();
                }
            });
        }
    }

    public void hideSwipeRefresh() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public void setSwipeToRefreshColor() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        }
    }

    public void hideSwipeRefreshLayout() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setVisibility(View.GONE);
        }
    }

    public void showSwipeRefreshLayout() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    public void hideList() {
        hideSwipeRefreshLayout();
        hideSwipeRefresh();
        list.setVisibility(View.GONE);
    }

    public void setImgEmptyStateIcon(int drawable) {
        try {
            imgEmptyStateIcon.setImageResource(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTxtEmptyState(String message) {
        try {
            if(message!=null){
                txtEmptyState.setText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showList() {
        showSwipeRefreshLayout();
        hideSwipeRefresh();
        list.setVisibility(View.VISIBLE);
    }

    @Override
    public void initObjects() {
        pagination = new Pagination();
        list.setAdapter(adapter);
    }

    @Override
    public void onPaginationConfigChanged(Pagination pagination) {
        pagination.setFirst(this.pagination.getFirst());
        pagination.setLast(this.pagination.getLast());
        this.pagination = pagination;
    }

    public void setListItems(List<T> items) {
        if (items != null) {
            itemList.addAll(items);
        }
        processListData();
    }


    private void processListData() {
        if (itemList.size() <= 0) {
            hideList();
            return;
        }
        adapter.notifyDataSetChanged();
        showList();
    }

    public void refreshList() {
        this.itemList.clear();
        adapter.notifyDataSetChanged();
        pagination = new Pagination();
        onLoadMore(pagination);
    }

    @Override
    public void showProgress() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            return;
        }
        super.showProgress();
    }


    public int getListOrientation() {
        return listOrientation;
    }

    public void setListOrientation(int listOrientation) {
        this.listOrientation = listOrientation;
    }

    public abstract void onLoadMore(Pagination pagination);

}
