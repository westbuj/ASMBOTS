package com.jnn.bots;

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
import android.view.MotionEvent;
import android.view.View;





public class MyView extends View {
    
    
	public static Bitmap splash = null;
    private Path    mPath;
  
    private Paint textPaint;
    
    Rect previewRect=new Rect(5,5,35,35);
	
	boolean drawing=false;

    public Handler updateImage = new Handler() {

		public void handleMessage(Message msg) {
			invalidate();
		}};

   
    public MyView(Context c) {
    	
    	super(c);
        
    	textPaint =new Paint();
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(18); 
    	
    
        splash = BitmapFactory.decodeResource(getResources(), R.drawable.splashbot);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {

    	
    	if (BotLoader.mode == BotLoader.PLAYING)
    		BotLoader.game.draw(canvas);
    	if (BotLoader.mode == BotLoader.READY || BotLoader.mode == BotLoader.LOADING)
    	{
    		Rect src=new Rect(0,0,splash.getWidth(),splash.getHeight());
    		Rect trg=new Rect(0,0,canvas.getWidth(),canvas.getHeight());
    		canvas.drawBitmap(splash, src, trg, null);
    		//canvas.drawBitmap(splash, 0, 0, null);
    		
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
    	
    	
    	BotLoader.dbV.setVisibility(View.VISIBLE);
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

