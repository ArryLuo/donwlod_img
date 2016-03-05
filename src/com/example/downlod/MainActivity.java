package com.example.downlod;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView img;
	private String path="http://www.baidu.com/img/baidu_jgylogo3.gif";
	private Handler handler=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img=(ImageView) findViewById(R.id.img);
		new HttpThreadDoawlod("http://www.baidu.com/img/baidu_jgylogo3.gif", img, handler).start();
	}

}
