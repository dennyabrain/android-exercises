package dennymades.space.Gestures;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by abrain on 8/20/16.
 */
public class CanvasAsync extends AsyncTask<CompoundObjectForASync, Integer, Integer> {
    private static Canvas canvas;
    private static Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    @Override
    protected Integer doInBackground(CompoundObjectForASync... surfaceHolders) {
        canvas = surfaceHolders[0].mSurfaceHolder.lockCanvas();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(surfaceHolders[0].mTouchX,surfaceHolders[0].mTouchY,50,50,paint);
        surfaceHolders[0].mSurfaceHolder.unlockCanvasAndPost(canvas);
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Log.d(MainActivity.LOG_TAG,"done drawing in tCanvasASync");
    }
}
