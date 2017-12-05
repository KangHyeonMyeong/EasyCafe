package com.example.we25.seohu.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import com.example.we25.easycafe.R;

/**
 * Created by Kang on 2017-06-30.
 */

public class BaseExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groupList;
    private HashMap<String, String> childList;

    public BaseExpandableAdapter(Context context, List<String> groupList, HashMap<String, String> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get( groupList.get(i));
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String groupName = groupList.get(i);
        View v = view;
        if( v == null){
            LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = (LinearLayout) inflater.inflate(R.layout.list_group , null);
        }
        TextView textGroup = (TextView) v.findViewById(R.id.textView2);
        textGroup.setText(groupName);
        return v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String childString = childList.get(groupList.get(i));
        View v = view;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = (LinearLayout) inflater.inflate(R.layout.list_child, null);
        }

        TextView childText = (TextView) v.findViewById(R.id.textView);
        childText.setText( childString );

        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}


