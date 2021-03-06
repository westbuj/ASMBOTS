package com.jnn.bots;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;import com.jnn.asm.bots.R;




import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;


public class ANR {
	
	public static String appStoragePath="";
	
	
//	public static Bitmap chip=Bitmap.createBitmap(StaticResources.CHIP_WIDTH, StaticResources.CHIP_HEIGHT, Bitmap.Config.RGB_565);
	
	public static int[] getBitArray(Bitmap iMap) {
		int[] pix = new int[iMap.getWidth() * iMap.getHeight()];
		iMap.getPixels(pix, 0, iMap.getWidth(), 0, 0, iMap.getWidth(), iMap
				.getHeight());
		return pix;
	}
	
	public static Bitmap getBitmap(Context c,String source){
		if (source.startsWith("INT:"))
		{
			return getRawResourceAsBitmapByName(c.getResources(),source.substring(4));
			
		}
		if (source.startsWith("URI:"))
		{
			return getBitmapFromURL(c,source.substring(4));
			
		}
		
		return BitmapFactory.decodeFile(ANR.appStoragePath+"/"+source);
		
		
	}
	public static Bitmap getBitmapFromURL(Context c,String targetURL)
	{

		
		if (targetURL.startsWith("INT:"))
		{
			return getRawResourceAsBitmapByName(c.getResources(),targetURL.substring(4));
			
		}
		
		byte[] buff = new byte[32 * 1024];
		// final byte[] data =null;
		byte[] data = null;

		try {
			BufferedInputStream in = new BufferedInputStream(new URL(targetURL)
					.openStream(), 24000);

			final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();

			BufferedOutputStream out = new BufferedOutputStream(dataStream,
					24000);

			// copy(in, out);
			int len;
			while ((len = in.read(buff)) > 0)
				out.write(buff, 0, len);

			out.flush();
			data = dataStream.toByteArray();

		} catch (Exception e) {

			return BitmapFactory.decodeResource(c.getResources(), R.drawable.error_icon);
			
 			

		}
		return BitmapFactory.decodeByteArray(data, 0, data.length);

	}
	
	
	
	public static final void alert(Context c,String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alert = builder.create();

		alert.show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String getFile(Resources iRes,String url)
	{
		
		if (url.startsWith("INT:"))
		{
			
			return getRawResourceAsStringByName(iRes, url.substring(4));

		}
		//StringBuilder out = new StringBuilder(); 
		
		
		//New MEthod
		
		byte[] buff = new byte[32 * 1024];
		// final byte[] data =null;		

		try {
			BufferedInputStream in = new BufferedInputStream(new URL(url)
					.openStream(), 24000);

			final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();

			BufferedOutputStream out = new BufferedOutputStream(dataStream,
					24000);

			// copy(in, out);
			int len;
			while ((len = in.read(buff)) > 0)
				out.write(buff, 0, len);

			out.flush();
			out.close();
			
			return dataStream.toString();

		}catch(Exception e){
			return e.getMessage();
		}
		
		
		
		///
		
		
		
		
		/*
		 * 

		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet getMethod=new HttpGet(url);
			HttpResponse httpResponse = client.execute(getMethod);
			HttpEntity httpEntity = httpResponse.getEntity();
		ResponseHandler<String> responseHandler = new
		BasicResponseHandler();
		String responseBody=client.execute(getMethod, responseHandler);
		final char[] buffer = new char[0x10000]; 
		
		Reader in = new InputStreamReader(httpEntity.getContent(), "UTF-8"); 
		int read; 
		do { 
		  read = in.read(buffer, 0, buffer.length); 
		  if (read>0) { 
		    out.append(buffer, 0, read); 
		  } 
		} while (read>=0); 

		
		}
		catch(Exception e){
			return e.getMessage();
		}
		return out.toString();
		
			
					 * 
		 */
	}
	public static Bitmap makeIcon(int x, int y,Bitmap source)
	{
		int tW=300;
		int tH=64;
		
		Bitmap bitmap = Bitmap.createBitmap(tW,tH, Bitmap.Config.RGB_565); 
		Canvas canvas = new Canvas(bitmap); 
		
		Rect src=new Rect(source.getWidth()/2 - tW/2,source.getHeight()/4,source.getWidth()/2 + tW/2,tH+source.getHeight()/4);
		Rect dst=new Rect(0,0,tW,tH);
		
		canvas.drawBitmap(source, src, dst,null);
		return bitmap;
	}
	
	public static Bitmap getRawResourceAsBitmapByName(Resources iRes,String resName) {
		Bitmap rBitmap = null;
		int res = iRes.getIdentifier("com.jnn.jw.look.a:raw/" + resName,
				null, null);
		InputStream is = iRes.openRawResource(res);

		try {
			rBitmap = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
			} catch (IOException e1) {
				// Ignore.
			}
		}

		return rBitmap;

	}
	public static String getRawResourceAsStringByName(Resources iRes, String resName) {
		String oString = "";
		int res = iRes.getIdentifier("com.jnn.bots:raw/" + resName,
				null, null);
		InputStream is = iRes.openRawResource(res);
		
		try {
			int size = is.available();

			// Read the entire resource into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			oString = new String(buffer);
		} catch (Exception e) {

		}

		return oString;

		
	}
	
	public static String saveABitmap(Bitmap sBMP, String name)
	{
			
		String retval="";
		FileOutputStream outStream = null;
		try {
			// write to local sandbox file system
			// outStream =
			// CameraDemo.this.openFileOutput(String.format("%d.jpg",
			// System.currentTimeMillis()), 0);
			// Or write to sdcard
			File root = new File(ANR.appStoragePath);
			if (root.canWrite()){
				File gpxfile = new File(root,name);
				retval=gpxfile.toURI().toASCIIString();
				outStream = new FileOutputStream(gpxfile);
				
				sBMP.compress(Bitmap.CompressFormat.JPEG, 80, outStream); 
				outStream.flush();
				
				//outStream.write(bmOverlay.data);
				outStream.close();
				
			}				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return retval;
	}
	
	public static String setupStorage(String applicationName)
	{
		File root = Environment.getExternalStorageDirectory();
		if (root.canWrite()){
			File tFile = new File(root,"/"+applicationName+"/"); 
			tFile.mkdirs();
			
			ANR.appStoragePath=tFile.getPath();

		}
		
		return ANR.appStoragePath;
		
	}
	
	public static boolean isFile(String filename)
	{
		File root = new File(ANR.appStoragePath);
	
		return new File(root,filename).isFile();
	
	}
	public static String saveXMLFile(String content, String name)
	{
		String retval="";
		FileOutputStream outStream = null;
		try {
			// write to local sandbox file system
			// outStream =
			// CameraDemo.this.openFileOutput(String.format("%d.jpg",
			// System.currentTimeMillis()), 0);
			// Or write to sdcard
			File root = new File(ANR.appStoragePath);
			if (root.canWrite()){
				File gpxfile = new File(root,name);
				retval=gpxfile.toURI().toASCIIString();
				outStream = new FileOutputStream(gpxfile);				
				outStream.write(content.getBytes());
				outStream.flush();				

				outStream.close();

			}				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return retval;
	} 

	public static String getNextProjectName(String prefix,String ext)
	{
		int c=1;		
		File root = new File(ANR.appStoragePath);
		String cName = prefix+new Integer(c).toString()+ext;
		File cFile= new File(root,cName);
		
		while (cFile.isFile() && c < 20 ){
			c++;
			cName = prefix+new Integer(c).toString()+ext;
			cFile= new File(root,cName);
			
		}
		return cName;
	}
	
	
	public static String getFileContentsAsString(String name)
	{
		String oString = "";
		FileInputStream inStream = null;
		try {
			// write to local sandbox file system
			// outStream =
			// CameraDemo.this.openFileOutput(String.format("%d.jpg",
			// System.currentTimeMillis()), 0);
			// Or write to sdcard
			File root = new File(ANR.appStoragePath);
			File source= new File(root,name);
			inStream = new FileInputStream(source);
			
			
			int size = inStream.available();

				// Read the entire resource into a local byte buffer.
				byte[] buffer = new byte[size];
				inStream.read(buffer);
				inStream.close();
				oString = new String(buffer);
			
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return oString;
	}
	
	public static String saveMaskFile(byte[] content, String name)
	{
		String retval="";
		FileOutputStream outStream = null;
		try {
			// write to local sandbox file system
			// outStream =
			// CameraDemo.this.openFileOutput(String.format("%d.jpg",
			// System.currentTimeMillis()), 0);
			// Or write to sdcard
			File root = new File(ANR.appStoragePath);
			if (root.canWrite()){
				File gpxfile = new File(root,name);
				retval=gpxfile.toURI().toASCIIString();
				outStream = new FileOutputStream(gpxfile);				
				outStream.write(content);
				outStream.flush();				

				outStream.close();

			}				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return retval;
	}

	public static boolean getMaskFile(String fName,byte[] target)
	{
		try{
		//InputStream is = new FileInputStream(fName);
		File root = new File(ANR.appStoragePath);
		if (root.canWrite()){
			File gpxfile = new File(root,fName);
			FileInputStream inStream = new FileInputStream(gpxfile);
			inStream.read(target);
			inStream.close();				
	
			}
		
		}
		catch (Exception e){}
		return true;
	}
}
