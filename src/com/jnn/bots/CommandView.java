package com.jnn.bots;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


public class CommandView extends TextView {
	
	Paint tPaint = new Paint();
	

	
	public CommandView(Context context,String code) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.setText(code);
		this.setGravity( Gravity.CENTER_VERTICAL|Gravity.LEFT );
		this.setTextColor(Color.BLACK);
		init();
		
	}
	
	public CommandView(Context context) {
		super(context);
		init();
	}

	public void init()
	{
		this.setMinimumHeight(18);
	
		tPaint.setColor(Color.DKGRAY);
		tPaint.setTextSize(10);
		this.setTextSize(10);
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		
		super.onDraw(canvas);
	}
	
}
