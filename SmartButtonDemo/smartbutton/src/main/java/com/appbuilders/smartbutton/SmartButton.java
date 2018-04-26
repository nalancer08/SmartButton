package com.appbuilders.smartbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SmartButton extends AppCompatButton {

    private static final String DELIMITERS = "\n";
    private static final int DRAWABLE_LEFT_POSITION = 0;
    private static final int DRAWABLE_TOP_POSITION = 1;
    private static final int DRAWABLE_RIGHT_POSITION = 2;
    private static final int DRAWABLE_BOTTOM_POSITION = 3;
    private static final int DRAWABLE_LENGTH = 4;

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
            this.updateTint();

            // -- Recycle the typed array
            typedArray.recycle();
        }
    }

    private void updateTint() {

        if (this.tintColor != Color.TRANSPARENT) {

            Drawable[] drawables = getCompoundDrawables();
            if (drawables.length != SmartButton.DRAWABLE_LENGTH) return;

            Drawable[] wrappedDrawables = new Drawable[DRAWABLE_LENGTH];
            for (int i = 0; i < DRAWABLE_LENGTH; i++) {

                Drawable drawable = drawables[i];
                if (drawable != null) {
                    Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
                    DrawableCompat.setTint(wrappedDrawable, tintColor);
                    wrappedDrawables[i] = wrappedDrawable;
                }
            }

            setCompoundDrawablesWithIntrinsicBounds(wrappedDrawables[DRAWABLE_LEFT_POSITION],
                                                            wrappedDrawables[DRAWABLE_TOP_POSITION],
                                                            wrappedDrawables[DRAWABLE_RIGHT_POSITION],
                                                            wrappedDrawables[DRAWABLE_BOTTOM_POSITION]);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {

        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        updatePadding();
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {

        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        updatePadding();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        super.setText(text, type);
        this.updatePadding();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        this.updatePadding(w);
    }

    private void updatePadding() {

        updatePadding(getMeasuredWidth());
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {

        super.setPadding(left, top, right, bottom);
        this.mLeftPadding = left;
        this.mRightPadding = right;
        updatePadding();

    }

    private void updatePadding(int width) {

        if (width != 0) return;

        Drawable[] compoundDrawables = getCompoundDrawables();
        if (compoundDrawables.length == 0 || compoundDrawables.length != DRAWABLE_LENGTH) return;

        Drawable leftDrawable = compoundDrawables[DRAWABLE_LEFT_POSITION];
        Drawable rightDrawable = compoundDrawables[DRAWABLE_RIGHT_POSITION];
        if (leftDrawable == null && rightDrawable == null) return;

        int textWidth = getTextWidth();
        int iconPadding = Math.max(getCompoundDrawablePadding(), 1);
        int paddingSize;

        if (leftDrawable != null && rightDrawable != null) {
            paddingSize = (width - leftDrawable.getIntrinsicWidth() - rightDrawable.getIntrinsicWidth() - textWidth - iconPadding * 4) / 2;
        } else if (leftDrawable != null) {
            paddingSize = (width - leftDrawable.getIntrinsicWidth() - iconPadding * 2 - textWidth) / 2;
        } else {
            paddingSize = (width - rightDrawable.getIntrinsicWidth() - iconPadding * 2 - textWidth) / 2;
        }
        super.setPadding(Math.max(mLeftPadding, paddingSize), getPaddingTop(), Math.max(paddingSize, mRightPadding), getPaddingBottom());
    }

    private int getTextWidth() {

        if (textBoundsRect == null) {
            textBoundsRect = new Rect();
        }
        Paint paint = getPaint();
        String text = divideText();
        paint.getTextBounds(text, 0, text.length(), textBoundsRect);
        return textBoundsRect.width();
    }

    private String divideText() {

        String text = getText().toString();
        if (text.isEmpty()) {
            return "";
        }

        List<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(text, DELIMITERS, false);
        while(tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }

        if (list.size() == 1) {
            return isAllCaps() ? list.get(0).toUpperCase() : list.get(0);
        }

        String longPart = list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1).length() > list.get(i).length()) {
                longPart = list.get(i + 1);
            }
        }

        return isAllCaps() ? longPart.toUpperCase() : longPart;
    }

    private boolean isAllCaps() {

        TransformationMethod method = getTransformationMethod();
        if (method == null) return false;
        return method.getClass().getSimpleName().equals("AllCapsTransformationMethod");
    }
}