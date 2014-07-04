package com.example.tt;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceClass extends Service{
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Log.d("slog", "onStart()");
		super.onStart(intent, startId); 
		

		 

					
		
	}
	
	@Override
	public void onDestroy() { 
		Log.d("slog", "onDestroy()");
		
		
		
		super.onDestroy();
	}
	
}
