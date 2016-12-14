package com.itheima31.jdmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.bean.BrandBean;
import com.itheima31.jdmall.bean.ValueBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.DCVAnimatedExpandableListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends DCVAnimatedExpandableListView.AnimatedExpandableListAdapter {
    private LayoutInflater inflater;

    private List<BrandBean> items;

    public ExampleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<BrandBean> items) {
        this.items = items;
    }

    @Override
    public ValueBean getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).value.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ValueBean item = getChild(groupPosition, childPosition);
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = inflater.inflate(R.layout.item_discover, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.item_discover_tv);
            holder.icon = (ImageView) convertView.findViewById(R.id.item_discover_iv);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.title.setText(item.name);
        Picasso.with(UIUtils.getContext()).load(Constants.URLS.BASEURL + item.pic).into(holder.icon);
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return items.get(groupPosition).value == null ? 0 : items.get(groupPosition).value.size();
    }

    @Override
    public BrandBean getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        BrandBean item = getGroup(groupPosition);
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = inflater.inflate(R.layout.group_item, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.textTitle);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.title.setText(item.key);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    private static class GroupItem {
        String title;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    private static class ChildItem {
        String title;
        String hint;
    }

    private static class ChildHolder {
        TextView  title;
        ImageView icon;
    }

    private static class GroupHolder {
        TextView title;
    }
}