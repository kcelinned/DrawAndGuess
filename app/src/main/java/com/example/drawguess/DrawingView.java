package com.example.drawguess;

import android.graphics.Color;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.graphics.PorterDuff;

public class DrawingView extends View {

    private Path drawPath;

    private Paint drawPaint, canvasPaint;

    private int paintColour = 0xFF660000;

    private int whiteColour = 0xFFFFFFFF;

    private Canvas drawCanvas;

    private Bitmap canvasBitmap;

    private float brushSize = 20;

    private boolean erase = false;

    public DrawingView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        setupDrawing();
    }

    /** Sets up the variables to draw **/
    private void setupDrawing(){
        drawPath = new android.graphics.Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColour);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    /** Changes Colour of Paint **/
    public void setColor(String newColour){
        invalidate();
        paintColour = Color.parseColor(newColour);
        drawPaint.setColor(paintColour);
    }

    /**Registers user's touch and draws the path**/
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }


    /**Clears the canvas**/
    public void clearCanvas(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    /**Sets the eraser**/
    public void setErase(boolean isErase) {
        erase = isErase;
        if (erase) {
            drawPaint.setColor(whiteColour);
        }else{
            drawPaint.setColor(paintColour);
        }
    }

    /**Fills the canvas**/
    public void fillCanvas(){
        drawCanvas.drawColor(paintColour);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }


    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh){
        super.onSizeChanged(width, height, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

}

