package dennymades.space.surfaceviewtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Paint.Style;
import android.view.View;

/**
 * Created by abrain on 8/20/16.
 */
public class TextSurfaceView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private static SurfaceHolder sh;
    private static final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TextSurfaceView(Context context){
        super(context);
        sh = getHolder();
        sh.addCallback(this);
        this.setOnTouchListener(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvas = surfaceHolder.lockCanvas();
        paint.setColor(Color.BLUE);
        paint.setStyle(Style.FILL);
        canvas.drawColor(Color.BLACK);
        canvas.drawCircle(100,200,50, paint);
        paint.setTextSize(100);
        paint.setColor(Color.GREEN);
        canvas.drawText("what does this mean?", 10,600,paint);
        paint.setColor(Color.MAGENTA);
        canvas.save();
        canvas.rotate(350,50,20);
        canvas.drawText("any idea?", 40, 900, paint);
        canvas.restore();
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public TextSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sh = getHolder();
        sh.addCallback(this);
    }

    public TextSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sh = getHolder();
        sh.addCallback(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("Denny", "mouse X :"+String.valueOf(motionEvent.getX())+"mouse Y :"+String.valueOf(motionEvent.getY()));
        return false;
    }
}
