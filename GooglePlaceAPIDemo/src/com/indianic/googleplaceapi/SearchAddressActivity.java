package com.indianic.googleplaceapi;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchAddressActivity extends Activity{
	
	private static final String TAG = SearchAddressActivity.class.getSimpleName();
	private EditText edtSearch;
	private Button btnSearch;
	private double lat, lon;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchaddress_activity);
        initComp();
    }

	private void initComp() {
		edtSearch = (EditText)findViewById(R.id.searchaddress_activty_edtSearch);
		btnSearch = (Button)findViewById(R.id.searchaddress_activty_btnSearch);
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Log.v(TAG, "Button Click");
				String addr = edtSearch.getText().toString().trim();

//				Geocoder coder = new Geocoder(SearchAddressActivity.this);
//				List<Address> address = null;
//
//				try {
//					address = coder.getFromLocationName(addr,1);
//					if (address != null && address.size() > 0) {
//						Address location = address.get(0);
//						Log.v(TAG, "Location : "+location.toString());
//						lat = location.getLatitude();
//						lon = location.getLongitude();
//						Log.v(TAG, "Lat/Lon : "+lat +" ::: "+lon);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}

				if (addr.length()>0) {
					final Intent intent = new Intent(SearchAddressActivity.this, AddressListActivity.class);
					intent.putExtra("address", addr);
					startActivity(intent);
				} else{
					Log.v(TAG, "Address not found");
					Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG);
				}
			}

		});
	}

}