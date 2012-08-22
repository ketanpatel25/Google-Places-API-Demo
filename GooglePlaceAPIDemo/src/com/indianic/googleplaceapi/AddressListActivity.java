package com.indianic.googleplaceapi;

import java.net.URLEncoder;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.indianic.googleplaceapi.adapter.AddressListAdapter;
import com.indianic.googleplaceapi.kk.MainActivity;
import com.indianic.googleplaceapi.model.AddressModel;
import com.indianic.googleplaceapi.ws.WSAddressList;

public class AddressListActivity extends Activity {

	private String addr;
	private final String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
	private final String Key = "&sensor=true&key=AIzaSyClqEk4Nrx-s5DCiSDgx9wIZYqp49-ca_M";
	private ProgressDialog progressDialog;
	private ListView list;
	private ArrayList<AddressModel> myList;
	private WSAddressList addressWebService;
	private CallWS callWS;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addresslist_activity);
		context = AddressListActivity.this;
		callWS = new CallWS();
		addressWebService = new WSAddressList();
		initComp();

		addr = getIntent().getStringExtra("address");

		addr = URLEncoder.encode(addr);
		startTask();
	}

	private void initComp() {
		// TODO Auto-generated method stub
		list = (ListView) findViewById(R.id.addresslist_activity_list);
		myList = new ArrayList<AddressModel>();
		
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				AddressModel model = new AddressModel();
				model = myList.get(arg2);
				Intent intent = new Intent(AddressListActivity.this,MainActivity.class);
				intent.putExtra("user_latitude", model.getLat());
				intent.putExtra("user_longitude",model.getLon());
				startActivity(intent);
				
			}
		});
		
		
		
		
	}

	private void startTask() {
		if (callWS.getStatus() == AsyncTask.Status.PENDING)
			callWS.execute();
		else if (callWS.getStatus() == AsyncTask.Status.FINISHED) {
			callWS = new CallWS();
			callWS.execute();
		}
	}

	public class CallWS extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = ProgressDialog.show(AddressListActivity.this, "",
					"Loading...", true, false);
		}

		@Override
		protected String doInBackground(String... params) {
			String response = "";
			for (int i = 0; i < 10; i++) {
			
				response = addressWebService.getData(URL + addr + Key);
				Log.e("TAG", "counter"+i);
				Log.e("TAG", "response"+response);
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (progressDialog != null && progressDialog.isShowing())
				progressDialog.dismiss();

			final ArrayList<AddressModel> arrayList = addressWebService
					.parseAddressListJSON(result);
			final AddressListAdapter adapter = new AddressListAdapter(context,
					arrayList);
			myList = arrayList;
			list.setAdapter(adapter);
		}
	}
}
