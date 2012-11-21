package com.cpinera.Factorial;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import factorial.FactorialClient;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.d("factorial", "The factorial of 10 is " + FactorialClient.factorial(10L));	
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
