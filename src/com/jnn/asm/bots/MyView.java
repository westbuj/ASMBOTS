package com.jnn.asm.bots;

import com.jnn.asm.bots.R;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;





public class MyView extends View {
    
    
	public static Bitmap splash = null;
	public static int hideMFCP=5;
	
    private Path    mPath;
  
    private Paint textPaint;
    private Paint gameOverPaint;
    
    Rect previewRect=new Rect(5,5,35,35);
	
	boolean drawing=false;

    public Handler updateImage = new Handler() {

		public void handleMessage(Message msg) {
			invalidate();
		}};

	public Handler hideMFCPHandler = new Handler() {

			public void handleMessage(Message msg) {
				BotLoader.dbV.setVisibility(View.INVISIBLE);
			}};
			
    public static void extendTimeOut()
    {
    	hideMFCP=6;
    }
    public MyView(Context c) {
    	
    	super(c);
        
    	textPaint =new Paint();
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(18); 
        
        gameOverPaint = new Paint();
        gameOverPaint.setColor(Color.WHITE);
        gameOverPaint.setTextSize(32); 
    	
    
        splash = BitmapFactory.decodeResource(getResources(), R.drawable.splashbot);
        
    
	
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	
    
        super.onSizeChanged(w, h, oldw, oldh);
    }
     
    @Override
    protected void onDraw(Canvas canvas) {

    	
    	
    	
    	
    	
    	if (BotLoader.mode == BotLoader.PLAYING)
    		BotLoader.game.draw(canvas,this);
    	
    	
    	if (BotLoader.launch.getVisibility() == View.VISIBLE)
    	{
    		Rect src=new Rect(0,0,splash.getWidth(),splash.getHeight());
    		Rect trg=new Rect(0,0,this.getWidth(),this.getHeight());
    		canvas.drawBitmap(splash, src, trg, null);
    		//canvas.drawBitmap(splash, 0, 0, null);
    		
    	//	canvas.drawRect(5,5, canvas.getWidth()/2,canvas.getHeight()/2,textPaint);
    		
    	}
    	
    	
    	//canvas.drawBitmap(BotLoader.gameOverSplash, (int)(this.getWidth()/2 - BotLoader.gameOverSplash.getWidth()/2),(int)(this.getHeight()/2 - BotLoader.gameOverSplash.getHeight()/2),null);
    	
    	
    	if (BotLoader.mode == BotLoader.GAMEOVER)
    		{
    		
    		canvas.drawBitmap(BotLoader.gameOverSplash, (int)(this.getWidth()/2 - BotLoader.gameOverSplash.getWidth()/2),(int)(this.getHeight()/2 - BotLoader.gameOverSplash.getHeight()/2),null);
        	
    		BotLoader.game.draw(canvas,this);
    		 

    		}
      
       super.onDraw(canvas);
        
    }
    
    

	private float mX, mY;
    private static final float TOUCH_TOLERANCE = 1;
    
    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        drawing=true;
    }
    private void touch_move(float x, float y) {
    	mX = x;
        mY = y;
    	mPath.lineTo(mX, mY);
    	
    	if (true) return;
    	
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        //mCanvas.drawPath(mPath, mPaint);
     //   MUStudio.maskCanvas.drawPath(mPath, MUStudio.cBrush.tPaint);
        // kill this so we don't double draw            
        mPath.reset();
        drawing=false;
    //    MUStudio.queue.addTask(new CalcThread());
                
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	if (BotLoader.mode==BotLoader.PLAYING || BotLoader.mode == BotLoader.GAMEOVER)
    		{BotLoader.dbV.setVisibility(View.VISIBLE);
    		 hideMFCP=5;
    		
    		}
//        float x = event.getX();
//        float y = event.getY();
//        
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                touch_start(x, y);
//                invalidate();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                touch_move(x, y);
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//                touch_up();
//                invalidate();
//                break;
//        }
        return true;
    }
}

