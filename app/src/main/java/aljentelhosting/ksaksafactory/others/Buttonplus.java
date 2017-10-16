package aljentelhosting.ksaksafactory.others;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Shaymaa on 7/11/2017.
 */

public class Buttonplus extends Button {
    public Buttonplus(Context context) {
        super(context);
        init();
    }

    public Buttonplus(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public Buttonplus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Buttonplus(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.DEFAULT;
            tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/flat.ttf");
            setTypeface(tf);
        }
    }
}
