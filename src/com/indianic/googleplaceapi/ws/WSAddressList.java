package com.indianic.googleplaceapi.ws;

import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.indianic.googleplaceapi.model.AddressModel;
import android.util.Log;

public class WSAddressList {

	public String getData(String URL) {

		final DefaultHttpClient httpClient = new DefaultHttpClient();
		final HttpGet httpget = new HttpGet(URL);

		HttpResponse response = null;

		try {
			Log.i("Util", "posted Url:" + URL);
			httpget.setHeader("json", "application/json");
			httpget.setHeader(HTTP.CONTENT_TYPE, "application/json");
			response = httpClient.execute(httpget);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public ArrayList<AddressModel> parseAddressListJSON(String response){
			Log.e("ok", "response"+response);
		final ArrayList<AddressModel> list = new ArrayList<AddressModel>();
		
		try {
			AddressModel model = null;
			final JSONObject jsonResponseObj = new JSONObject(response);
			JSONArray jsonArray = jsonResponseObj.getJSONArray("results");
			
			for (int i = 0; i < jsonArray.length(); i++) {
				model = new AddressModel();
				Log.v("get add",jsonArray.getJSONObject(i).optString("formatted_address"));
				model.setAddress(jsonArray.getJSONObject(i).optString("formatted_address"));
				JSONObject jsonResGeometry = jsonArray.getJSONObject(i).getJSONObject("geometry");
				JSONObject jsonResLocation = jsonResGeometry.getJSONObject("location");
				model.setLat(jsonResLocation.optString("lat"));
				model.setLon(jsonResLocation.optString("lng"));
				model.setName(jsonArray.getJSONObject(i).optString("name"));
				model.setArea(jsonArray.getJSONObject(i).optString("vicinity"));
				list.add(model);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
