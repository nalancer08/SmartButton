package com.appbuilders.smartbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class SmartButton extends AppCompatButton {

    private static int DRAWABLE_LEFT = 0;
    private static int DRAWABLE_RIGHT = 1;

    // -- View logic variables
    private Rect textBoundsRect;
    private int tintColor = Color.TRANSPARENT;
    private int mLeftPadding;
    private int mRightPadding;

    public SmartButton(Context context) {

        super(context);
        this.init(context, null);
    }

    public SmartButton(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.init(context, attrs);
    }

    public SmartButton(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SmartButton);
            this.tintColor = typedArray.getColor(R.styleable.SmartButton_drawableTint, Color.TRANSPARENT);
            float defaultDrawablePadding = getResources().getDimension(R.dimen.smartbutton_drawable_padding);
            int drawablePadding = (int) typedArray.getDimension(R.styleable.SmartButton_android_drawablePadding, defaultDrawablePadding);
            setCompoundDrawablePadding(drawablePadding);

            // -- If the drawable has color we can change it
            // updateTint();

            // -- Recycle de typed array
            typedArray.recycle();
        }
    }
}
