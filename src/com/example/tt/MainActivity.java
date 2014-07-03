package com.example.tt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private static final String TAG = "LocalBrowser";
	private static final String LOG_TAG = "WebViewDemo";
	private BackPressCloseHandler backPressCloseHandler;
	private long backKeyPressedTime = 0;
	private Toast toast;
	private Activity activity;
	private final Handler handler = new Handler();
	private WebView webView;
	private TextView textView;
	Button buttonForCall = null;
	Button buttonForClear = null;
	
	
	
	@SuppressLint("JavascriptInterface")
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		webView = new WebView(this);
		webView.getSettings().setJavaScriptEnabled(true);
		//webView.addJavascriptInterface(new AndroidBridge(), "HybridApp");
		webView.setWebChromeClient(new WebChromeClient());
		webView.setWebViewClient(new WebViewClient());
		
		
		webView.setWebChromeClient(new WebChromeClient(){
			
			@Override
			public boolean onJsAlert(final WebView view, final String url, final String message, JsResult result) {
				Log.d(TAG, "onJsAlert(" + view + ", " + url + ", " + message + ", " + result + ")");
					
				
				 NotificationManager notimng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		   		PendingIntent notiP = PendingIntent.getActivity(	getApplicationContext(), 0, 
		   				new Intent(getApplicationContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

		   		Notification notice = new NotificationCompat.Builder(getApplicationContext())
		   				.setContentTitle("제목~~~~~~")
		   				.setContentText("내용~~~~~~")
		   				.setSmallIcon(R.drawable.ic_launcher)
		   				.setTicker("하이~~~~~~~~").setAutoCancel(true)
		   				.setContentIntent(notiP).build();

		   		notimng.notify(0, notice);
		      
		  		/*
		    	if ( notimng != null ) {
		  			notimng.cancel(0);
		  		}
		    	*/
		      
		   		 Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		      vibe.vibrate(1000);
				
		      AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
		    	.setIcon(R.drawable.ic_launcher)
		    	.setTitle("제목")
		    	.setMessage("내용")
		    	.setCancelable(false)
		    	.setPositiveButton("확인", new DialogInterface.OnClickListener() {	
		  			@Override
		  			public void onClick(DialogInterface dialog, int which) {
		  				Toast.makeText(MainActivity.this, "ㅇㅇ", Toast.LENGTH_LONG).show();
		  				//webView.loadUrl("javascript:alert('dd')");
		  				webView.loadUrl("javascript:callJS('확```````````')");
		  				
		  			}
		  		}).setNegativeButton("취소", new DialogInterface.OnClickListener() {
		      @Override
		      public void onClick(DialogInterface dialog, int which) {
		       	Toast.makeText(MainActivity.this, "ㄴㄴ", Toast.LENGTH_LONG).show();
		      	
		      	webView.loadUrl("javascript:callJS('취````````````````')");
		      	
		        }
		      }).create();
		    	alert.show();
		      
		      
				result.confirm(); 
				return true;
				
			}
      });
		
		webView.loadUrl("file:///android_asset/www/index.html");
		setContentView(webView);
	

	
}
	

}
