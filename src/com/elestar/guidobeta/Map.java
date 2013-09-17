package com.elestar.guidobeta;



import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.elestar.usmskit.R;

public class Map extends Activity implements LocationListener{
	WebView webview ;
	public static final String PREFS_NAME = "MyPrefsFile";
	Option op;
	Boolean[] opArray;
	Boolean gpsEnabled;
	private LocationManager locationManager;
	private String provider;
	
	@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		op = new Option();
		opArray = new Boolean[op.getMax()];
		for (int i = 1; i<op.getMax()+1; i++){
			opArray[i-1]=settings.getBoolean("op"+i, false);
		}
		op.setOptions(opArray);
		setContentView(R.layout.activity_map);
		webview = (WebView) findViewById(R.id.mapview);
		webview.setWebChromeClient(new WebChromeClient());
		WebSettings webSettings = webview.getSettings();
		webview.getSettings().setSupportZoom(true);
		webview.getSettings().setBuiltInZoomControls(true);
		webview.getSettings().setUseWideViewPort(true);
		webview.addJavascriptInterface(new WebAppInterface(this), "Android");
		webSettings.setJavaScriptEnabled(true);
		if (savedInstanceState == null)
		{
			if (getIntent().hasExtra("w")){
				Bundle extras =getIntent().getExtras(); 
				int w = extras.getInt("w");
				int h = extras.getInt("h");
				webview.loadUrl("file:///android_asset/map.html?"+w+"a"+h);
				}
			else
				webview.loadUrl("file:///android_asset/map.html");
			
		}

		gpsEnabled=false;
		 // Get the location manager


	}
	
	public void callIn(){
		Toast.makeText(getApplicationContext(), "Your Location is - \nLat: \nLong: " , Toast.LENGTH_LONG).show();  
	}
	
	public static void waiting (double n){
	        
	        long t0, t1;

	        t0 =  System.currentTimeMillis();

	        do{
	            t1 = System.currentTimeMillis();
	        }
	        while ((t1 - t0) < (n * 1000));
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		waiting(0.3);
		if (op.getOptions()[0] == true){
	    	menu.findItem(R.id.desa).setChecked(true);
	    	webview.loadUrl("javascript:Tog('desa')");
	    }
	    else
	    	menu.findItem(R.id.desa).setChecked(false);
	    if (op.getOptions()[1] == true){
		    menu.findItem(R.id.school).setChecked(true);
		    webview.loadUrl("javascript:Tog('school')");
		    }
	    if (op.getOptions()[2] == true){
		    menu.findItem(R.id.hall).setChecked(true);
		    webview.loadUrl("javascript:Tog('dewan')");
		    }
	    if ((op.getOptions()[3] == true)){
		    menu.findItem(R.id.troom).setChecked(true);
		    webview.loadUrl("javascript:Tog('troom')");
		    }
	    if ((op.getOptions()[4] == true)){
	    	menu.findItem(R.id.block).setChecked(true);
		    webview.loadUrl("javascript:Tog('block')");
		    }
	    if ((op.getOptions()[5] == true)){
		    menu.findItem(R.id.bus_stop).setChecked(true);
		    webview.loadUrl("javascript:Tog('bus')");
		    }
	    if ((op.getOptions()[6] == true)){
		    menu.findItem(R.id.rroute).setChecked(true);
		    webview.loadUrl("javascript:Tog('rroute')");
		    }
	    if ((op.getOptions()[7] == true)){
		    menu.findItem(R.id.broute).setChecked(true);
		    webview.loadUrl("javascript:Tog('broute')");
		    }
	    if ((op.getOptions()[8] == true)){
		    menu.findItem(R.id.yroute).setChecked(true);
		    webview.loadUrl("javascript:Tog('yroute')");
		    }
	    if ((op.getOptions()[9] == true)){
		    menu.findItem(R.id.proute).setChecked(true);
		    webview.loadUrl("javascript:Tog('proute')");
		    }

		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	    super.onPrepareOptionsMenu(menu);
	    
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.desa:
	        	webview.loadUrl("javascript:Tog('desa')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[0]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[0]=true;
	            }
	            return true;
	        case R.id.school:
	        	webview.loadUrl("javascript:Tog('school')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[1]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[1]=true;
	            }
	            return true;
	        case R.id.hall:
	        	webview.loadUrl("javascript:Tog('dewan')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[2]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[2]=true;
	            }
	            return true;
	        case R.id.troom:
	        	webview.loadUrl("javascript:Tog('troom')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[3]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[3]=true;
	            }
	            return true;
	            
	        case R.id.block:
	        	webview.loadUrl("javascript:Tog('block')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[4]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[4]=true;
	            }
	            return true;
	            
	        case R.id.bus_stop:
	        	webview.loadUrl("javascript:Tog('bus')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[5]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[5]=true;
	            }
	            return true;
	        case R.id.rroute:
	        	webview.loadUrl("javascript:Tog('rroute')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[6]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[6]=true;
	            }
	            return true;

	        
	        case R.id.broute:
	        	webview.loadUrl("javascript:Tog('broute')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[7]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[7]=true;
	            }
	            return true;
	        
	        case R.id.yroute:
	        	webview.loadUrl("javascript:Tog('yroute')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[8]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[8]=true;
	            }
	            return true;
	        case R.id.proute:
	        	webview.loadUrl("javascript:Tog('proute')");
	        	if (item.isChecked()){
	        		item.setChecked(false);
	        		op.getOptions()[9]=false;
	        		}
	            else {
	            	item.setChecked(true);
	            	op.getOptions()[9]=true;
	            }
	            return true;
	        case R.id.clear:
	        	invalidateOptionsMenu();
	        	webview.reload();
	        	for (int i=0; i< op.getMax(); i++){
	        		op.getOptions()[i]=false;
	        	}

	        	return true;
	        case R.id.gps:
	        	getLocation();

	        	return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void getLocation(){
		if (!gpsEnabled){
		    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		    // Define the criteria how to select the locatioin provider -> use
		    // default
		    Criteria criteria = new Criteria();
		    provider = locationManager.getBestProvider(criteria, false);
		    boolean gps_enabled=false;
		    boolean network_enabled=false;
		    gps_enabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	        network_enabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	        if (gps_enabled == false || network_enabled == false){
	        	Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			    startActivity(intent);
			    gpsEnabled=false;
			    locationManager.removeUpdates(this);
			    Toast.makeText(this, "After turning on location access, select my location again to enable it.",Toast.LENGTH_SHORT).show();
	        }
	        else{
	        gpsEnabled=true;
		    Location location = locationManager.getLastKnownLocation(provider);
		    if (gps_enabled)
	        locationManager.requestLocationUpdates((LocationManager.GPS_PROVIDER), 400, 1, this);
		    if (network_enabled)
		    locationManager.requestLocationUpdates((LocationManager.NETWORK_PROVIDER), 400, 1, this);
		    // Initialize the location fields
		    if (location != null) {
		    	
		    	System.out.println("Provider " + provider + " has been selected.");
		    	System.out.println(gpsEnabled);
		    	onLocationChanged(location);
		    	
		    }
	        }
		}
		else{
		    locationManager.removeUpdates(this);
		    gpsEnabled=false;
		    webview.loadUrl("javascript:Invisible('myloc')");
		}
	}
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
      
        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");
  
        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
  
        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
               getApplicationContext().startActivity(intent);
            }
        });
  
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
  
        // Showing Alert Message
        alertDialog.show();
    }
	public int convertedLat(double lat){
		lat = lat -5.362937;
		return (int) Math.round(lat*-1*93612.23960358);
	}
	
	public int convertedLong(double longi){
		longi = longi -100.288332;
		return (int) Math.round(longi*1*92990.674664);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
	        webview.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState )
	{
	super.onSaveInstanceState(outState);
	webview.saveState(outState);
	}
	
	@Override
    protected void onStop(){
		super.onStop();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		for (int i = 0; i<op.getMax(); i++){
			editor.putBoolean("op"+(i+1), op.getOptions()[i]);
		}
		 editor.commit();
		 if(gpsEnabled)
		 locationManager.removeUpdates(this);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
	super.onSaveInstanceState(savedInstanceState);
	webview.restoreState(savedInstanceState);
	}

	@Override
	  protected void onResume() {
	    super.onResume();
	    if(gpsEnabled)
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    if(gpsEnabled)
	    locationManager.removeUpdates(this);
	  }

	  @Override
	  public void onLocationChanged(Location location) {
	   if(convertedLat(location.getLatitude()) <= 1083 && convertedLat(location.getLatitude()) >= 0 && convertedLong(location.getLongitude()) >= 0 &&convertedLong(location.getLongitude()) <= 2124){
 	   webview.loadUrl("javascript:showMyLocation("+convertedLat(location.getLatitude())+","+ convertedLong(location.getLongitude())+")");
	   }
	   else{
		   Toast.makeText(this, "You are outside the range of the map.",Toast.LENGTH_SHORT).show();
	   }
	  }

	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();

	  }

	  @Override
	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }

}
