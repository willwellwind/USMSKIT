package com.elestar.guidobeta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface extends Activity {
	Context mContext;
	MainActivity parentActivity;
	Map mapActivity;
	

    /** Instantiate the interface and set the context */
    WebAppInterface( MainActivity activity) {
        parentActivity = activity;
        mContext=parentActivity.getBaseContext();
    }
    
    WebAppInterface( Map activity) {
        mapActivity = activity;
        mContext= mapActivity.getBaseContext();
    }

	/** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        /*Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
                Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                startActivity(intent);*/
    }
    
    @JavascriptInterface
    public void tomaj(){
    	this.parentActivity.callIn();
    }
    
    @JavascriptInterface
    public void sendEmail(){
    	this.parentActivity.sendMail();
    }
    
    @JavascriptInterface
    public void takeLocation(double lat, double lon){
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
         	int h, w;
        	lat = lat -5.362937;
        	lon = lon -100.288332;
        	w = (int) Math.round(lon*92990.674664);
        	h = (int) Math.round(lat*-1*93612.23960358);
        	this.parentActivity.callIn(w, h);   	
    	}
    	else{
    		Toast.makeText(mContext, "This feature is currently not supported by your Android version, please wait for future updates", Toast.LENGTH_SHORT).show();    	}

    }
}
