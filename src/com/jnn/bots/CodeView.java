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


public class CodeView extends TextView {
	static Paint grayBack=new Paint();
	Paint tPaint = new Paint();
	public int mSize=1;
	public int mBlur=3;
	public int mColor=3;
	public int mAlpha=255;
	public boolean hilight=false;
	BlurMaskFilter tBlur = new BlurMaskFilter(this.mBlur, BlurMaskFilter.Blur.NORMAL);
	
	

	
	public CodeView(Context context,String code) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.setText(code);
		this.setGravity( Gravity.CENTER_VERTICAL|Gravity.LEFT );
		this.setTextColor(Color.LTGRAY);
		init();
		
	}
	
	public CodeView(Context context) {
		super(context);
		init();
	}

	public void init()
	{
		this.setMinimumHeight(25);
		this.setBlur(mBlur);
		
		  tPaint.setAntiAlias(true);
	        tPaint.setDither(true);
	       //tPaint.setAlpha(this.mAlpha);
	        tPaint.setStyle(Paint.Style.FILL);
	        tPaint.setStrokeJoin(Paint.Join.ROUND);
	        tPaint.setStrokeCap(Paint.Cap.ROUND);
	        tPaint.setStrokeWidth(1);
	        tPaint.setMaskFilter(this.tBlur);
		tPaint.setColor(Color.DKGRAY);
		tPaint.setTextSize(12);
		
		grayBack.setColor(0xDDFEFEFE);
		
		this.setPadding(20, 0, 0, 0);
		
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		//tPaint.setColor(Color.WHITE);
		
		if (this.hilight)
			canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), tPaint);
		
		//canvas.drawCircle(canvas.getWidth()-canvas.getWidth()/4, 25, this.mSize, tPaint);
		
		super.onDraw(canvas);
	}
	public void setBlur(int iBlur){
		this.mBlur=iBlur;
		if (iBlur == 0)
			tPaint.setMaskFilter(null);
		else
			{this.tBlur = new BlurMaskFilter(this.mBlur, BlurMaskFilter.Blur.NORMAL);
			tPaint.setMaskFilter(this.tBlur);
			}
	}
	public void setSize(int iSize){
		//tPaint.setStrokeWidth(iSize);
		this.mSize=iSize;
	}
	public void setAlpha(int iAlpha){
		//tPaint.setStrokeWidth(iSize);
		this.mAlpha=iAlpha;
		//tPaint.setAlpha(this.mAlpha);
		tPaint.setColor( iAlpha);
	}

}
