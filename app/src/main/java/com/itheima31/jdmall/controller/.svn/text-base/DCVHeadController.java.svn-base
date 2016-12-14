package com.itheima31.jdmall.controller;

import android.view.View;
import android.widget.AdapterView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.adapter.DCVheadListAdapter;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.DCVheadListView;

import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by wu on 2016/10/25.
 */
public class DCVHeadController extends BaseController<Object> {

    List<Map<String, Object>> headlist;
    @InjectView(R.id.dcv_head_listView)
    DCVheadListView mDcvHeadListView;
    private View mInflate;

    public DCVHeadController(List<Map<String, Object>> headlist) {
        this.headlist = headlist;
    }


    @Override
    public View getView() {
        mInflate = View.inflate(UIUtils.getContext(), R.layout.dcv_head_listview, null);

        return mInflate;
    }

    @Override
    public void refresHolderView(Object data) {
        DCVheadListAdapter adapter = new DCVheadListAdapter(UIUtils.getContext(), headlist);
        mDcvHeadListView.setAdapter(adapter);
        mDcvHeadListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(UIUtils.getContext(), "你点击l"+(headlist.get(position).get)+"选项", Toast.LENGTH_SHORT).show();
                if (onHeadItemListener!=null){
                    onHeadItemListener.onHeadItem(position);
                }
            }
        });
    }
    OnHeadItemListener onHeadItemListener;

    public void setOnHeadItemListener(OnHeadItemListener onHeadItemListener){
             this.onHeadItemListener = onHeadItemListener;
    }
    public interface OnHeadItemListener {
        void onHeadItem(int position);
    }
}
