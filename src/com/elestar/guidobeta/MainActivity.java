package com.elestar.guidobeta;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.elestar.usmskit.R;


@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
	WebView webview ;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webview = (WebView) findViewById(R.id.webview);
		webview.setWebChromeClient(new WebChromeClient());
		WebSettings webSettings = webview.getSettings();
		webview.getSettings().setSupportZoom(true);
		webview.getSettings().setBuiltInZoomControls(false);
		//webview.loadData(readTextFromResource(R.raw.index), "text/html", "utf-8");
		webSettings.setJavaScriptEnabled(true);
		webview.addJavascriptInterface(new WebAppInterface(this), "Android");
		webview.loadUrl("file:///android_asset/index.html");
	}
	

	public void callIn(){
		/*Intent intent = new Intent(this, MapLow.class);
		startActivity(intent);*/
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		Intent intent = new Intent(this, Map.class);
		startActivity(intent);
		}
		else
		{
			Intent i = new Intent(this, MapLow.class);
			startActivity(i);
		}
	}
	
	public void sendMail(){
		Intent eIntent = new Intent(android.content.Intent.ACTION_SEND);
		eIntent.setType("text/html");
		eIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"elestardev@gmail.com"});
		try {
		    startActivity(Intent.createChooser(eIntent, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void callIn(int w, int h){
		Intent intent = new Intent(this, Map.class);
		intent.putExtra("w", w);
		intent.putExtra("h", h);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.home:
        	webview.loadUrl("file:///android_asset/index.html");
        	return true;
        	
        default:
			return super.onOptionsItemSelected(item);
		}
		
		
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
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
	super.onSaveInstanceState(savedInstanceState);
	webview.restoreState(savedInstanceState);
	webview.addJavascriptInterface(new WebAppInterface(this), "Android");
	}

}
