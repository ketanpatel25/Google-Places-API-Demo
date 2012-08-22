package com.indianic.googleplaceapi.adapter;

import java.util.ArrayList;

import com.indianic.googleplaceapi.R;
import com.indianic.googleplaceapi.model.AddressModel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddressListAdapter extends BaseAdapter{
	
	private Context mContext;
	private ArrayList<AddressModel> listAddress;
	private LayoutInflater inflater;
	
	
	public AddressListAdapter(Context context,	ArrayList<AddressModel> tempListAddress) {
		this.mContext = context;
		this.listAddress = tempListAddress;
		inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return listAddress.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	private class ViewHolder{
		private TextView txtName, txtLat, txtLon, txtArea,txtAdd;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		final AddressModel model = listAddress.get(position);
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.row_addresslist_activity, null);
			
			viewHolder = new ViewHolder();
			viewHolder.txtName = (TextView)convertView.findViewById(R.id.row_addresslist_activty_txtName);
			viewHolder.txtAdd = (TextView)convertView.findViewById(R.id.row_addresslist_activty_txtadd);
			viewHolder.txtLat = (TextView)convertView.findViewById(R.id.row_addresslist_activty_txtLat);
			viewHolder.txtLon = (TextView)convertView.findViewById(R.id.row_addresslist_activty_txtLon);
			viewHolder.txtArea = (TextView)convertView.findViewById(R.id.row_addresslist_activty_txtArea);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		Log.v("address",model.getAddress());
		viewHolder.txtName.setText(model.getName());
		viewHolder.txtAdd.setText(model.getAddress());
		viewHolder.txtLat.setText(model.getLat());
		viewHolder.txtLon.setText(model.getLon());
		viewHolder.txtArea.setText(model.getArea());
		return convertView;
	}

}
