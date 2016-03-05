package com.example.downlod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.widget.ImageView;

public class HttpThreadDoawlod extends Thread {
	private String path;
	private ImageView img;
	private Handler handler;
	private InputStream is = null;

	public HttpThreadDoawlod(String path, ImageView img, Handler handler) {
		super();
		this.path = path;
		this.img = img;
		this.handler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			URL url = new URL(path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(5000);
			con.setRequestMethod("GET");
			con.setDoInput(true);
			is = con.getInputStream();
			File img_path = null;
			String name = String.valueOf(System.currentTimeMillis()+".png");
			File sdk = Environment.getExternalStorageDirectory();
			img_path = new File(sdk, name);
			// 给图片设置一个名字,用时间来命名
			FileOutputStream fos = new FileOutputStream(img_path);
			byte[] bt = new byte[1024];
			int in;
			while ((in = is.read(bt)) != -1) {
				fos.write(bt, 0, in);
			}
			
			final Bitmap bitmap = BitmapFactory.decodeFile(img_path
					.getAbsolutePath());
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// Bitmap bitmap=BitmapFactory.decodeStream(is);
					img.setImageBitmap(bitmap);
				}
			});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
