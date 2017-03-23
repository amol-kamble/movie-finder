package com.aml.moviefinder.ui.base.view;

import com.aml.moviefinder.ui.base.model.Pagination;


/**
 * Created by owner on 4/11/16.
 */

public interface PageableBaseViewBasic extends BaseViewBasic{
    public void onPaginationConfigChanged(Pagination pagination);
}
