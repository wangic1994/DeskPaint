package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.wangicclock.R;


/**
 * Created by Administrator on 2017/5/16.
 */

public class DeskView extends View {
    private Paint paint;
    private Bitmap bitmap;
    private int speed;
    private int measuredHeight;
    private int count;
    private static final String TAG = "DeskView";
    private int time;

    public DeskView(Context context) {
        super(context);
    }

    public DeskView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.water);
        speed = 0;
        count = 1;
        time = 0;
    }

    public DeskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        measuredHeight = this.getMeasuredHeight();
        for (int i = 0; i < count; i++) {
            canvas.drawBitmap(bitmap, i+300, speed, paint);
        }
        if (speed < measuredHeight) {
            time++;
            speed = time * time / 6;
        } else {
            time = 0;
            speed = 0;

        }
        invalidate();

    }
}
