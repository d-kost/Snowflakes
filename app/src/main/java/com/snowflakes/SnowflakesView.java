package com.snowflakes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class SnowflakesView extends View {
    class Snowflake {
        float x, y, r;
        int velocity;
        int maxHeight;
        Random random = new Random();

        Snowflake(int w, int h) {
            maxHeight = h;
            x = random.nextInt(w);
            y = random.nextInt(h);
            r = random.nextInt(15)+5;
            velocity = random.nextInt(15)+1;
        }

        void fall() {
            y += velocity;
            if (y >= maxHeight) {
                y = 0;
            }
        }
    }

    Paint p = new Paint();
    ArrayList<Snowflake> snowflakes = new ArrayList<>();



    public SnowflakesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SnowflakesView(Context context) {
        super(context);
    }

    public void initializeSnowflakes(int width, int height) {
        for (int i = 1; i < 41; i++) {
            Snowflake s = new Snowflake(width, height);
            snowflakes.add(s);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initializeSnowflakes(getWidth(), getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (Snowflake s : snowflakes) {
            s.fall();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        canvas.drawColor(Color.rgb(51, 0, 102));

        p.setColor(Color.rgb(230, 238, 255));

        for (Snowflake snowflake : snowflakes) {
            canvas.drawCircle(snowflake.x, snowflake.y, snowflake.r, p);
        }

    }
}
