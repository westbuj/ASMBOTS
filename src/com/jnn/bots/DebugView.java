package com.jnn.bots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.view.View;



public class DebugView extends View {

	
	
	public Paint infoText = new Paint();
	
	public DebugView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		infoText.setColor(Color.WHITE);
    
		//get our id
	}
	
	public DebugView(Context context, AttributeSet attrs){
		super(context,attrs);
		infoText.setColor(Color.WHITE);
		
		
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//canvas.drawColor(Color.LTGRAY);
		canvas.drawText("Debugger",20, 40, infoText);
		canvas.drawText(Soccer.cStep,20, 80, infoText);
		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
	    int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
	    this.setMeasuredDimension( parentWidth, parentHeight/2);
	    
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	
}
