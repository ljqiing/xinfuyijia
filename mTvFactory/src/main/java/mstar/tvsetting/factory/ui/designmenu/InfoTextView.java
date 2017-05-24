
package mstar.tvsetting.factory.ui.designmenu;

import mstar.factorymenu.ui.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoTextView extends TextView {

    private void init() {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.LEFT;
        params.setMargins(7, 0, 0, 0);
        this.setLayoutParams(params);
        this.setGravity(Gravity.LEFT);
        this.setBackgroundResource(R.drawable.button_state);
        this.setTextColor(Color.parseColor("#ffffff"));
        this.setTextSize(13);
    }

    public InfoTextView(Context context) {
        super(context);
        init();

    }

    public InfoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public InfoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

}
