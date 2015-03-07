package ru.guar7387.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class UnclickableButton extends Button {

    public UnclickableButton(Context context) {
        super(context);
    }

    public UnclickableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnclickableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UnclickableButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        //do nothing
    }
}
