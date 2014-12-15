package com.example.myresume.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myresume.R;
import com.example.myresume.model.TabElement;

public class DrawerListAdapter extends BaseAdapter {
	Context context;
	ArrayList<TabElement> tabList;

	public DrawerListAdapter(Context context, ArrayList<TabElement> tabList) {
		this.context = context;
		this.tabList = tabList;
	}

	@Override
	public int getCount() {
		return tabList.size();
	}

	@Override
	public Object getItem(int position) {
		return tabList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, parent, false);
		}
		TextView list_item_name = (TextView) convertView
				.findViewById(R.id.tvtabName);
		list_item_name.setText(tabList.get(position).getName());
		return convertView;
	}

}
