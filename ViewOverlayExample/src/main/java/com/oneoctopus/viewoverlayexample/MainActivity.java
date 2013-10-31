package com.oneoctopus.viewoverlayexample;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new Principal())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class Principal extends Fragment {

        public Principal() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

			Button with = (Button) rootView.findViewById(R.id.animacioncon);
			Button without = (Button) rootView.findViewById(R.id.animacionsin);

			with.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent irconoverlay = new Intent(getActivity(), WithOverlay.class);
					startActivity(irconoverlay);
				}
			});

			without.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent irsinoverlay = new Intent(getActivity(), SinOverlay.class);
					startActivity(irsinoverlay);
				}
			});



            return rootView;
        }
    }

}
