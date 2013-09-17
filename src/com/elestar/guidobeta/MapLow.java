package com.elestar.guidobeta;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.elestar.usmskit.R;

public class MapLow extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_low);
		WebView webview ;
		webview = (WebView) findViewById(R.id.mapviewlow);
		webview.setWebChromeClient(new WebChromeClient());	
		webview.getSettings().setSupportZoom(true);
		webview.getSettings().setBuiltInZoomControls(true);
		webview.getSettings().setUseWideViewPort(true);
		webview.loadUrl("file:///android_asset/map3.html");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_low, menu);
		return true;
	}

}
