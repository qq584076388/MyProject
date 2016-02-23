package BasTool;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import java.util.logging.Handler;

import BasCustom.ToolTipView;
import a.myproject.R;

/**
 * Created by Administrator on 2016/2/22.
 * 自定义提示框
 */
public class MyToast {
    public static ToolTip toolTip;
    public static ToolTipView toolTipView;

    /**
     * @param context         当前的页面
     * @param anchorView      选中的view
     * @param gravity         显示位置
     * @param text            显示内容
     * @param backgroundColor 显示的颜色
     * @param delay           无用参数
     */
    public static void showToolTipView(Context context, final View anchorView, int gravity, CharSequence text, int backgroundColor, long delay) {
        showToolTipView(context, anchorView, null, gravity, text, backgroundColor, delay);
    }

    private static ToolTip createToolTip(Context context, CharSequence text, int backgroundColor) {
        Resources resources = context.getResources();
        int padding = resources.getDimensionPixelSize(R.dimen.Toast_padding);
        int textSize = resources.getDimensionPixelSize(R.dimen.Toast_text_size);
        int radius = resources.getDimensionPixelSize(R.dimen.Toast_radius);
        return new ToolTip.Builder()
                .withText(text)
                .withTextColor(Color.WHITE)
                .withTextSize(textSize)
                .withBackgroundColor(backgroundColor)
                .withPadding(padding, padding, padding, padding)
                .withCornerRadius(radius)
                .build();
    }

    private static ToolTipView createToolTipView(Context context, ToolTip toolTip, View anchorView, ViewGroup parentView, int gravity) {
        return new ToolTipView.Builder(context)
                .withAnchor(anchorView)
                .withParent(parentView)
                .withToolTip(toolTip)
                .withGravity(gravity)
                .build();
    }

    private static void showToolTipView(Context context, final View anchorView, ViewGroup parentView, int gravity,
                                        CharSequence text, int backgroundColor, long delay) {
        if (anchorView.getTag() != null) {
            ((ToolTipView) anchorView.getTag()).remove();
            anchorView.setTag(null);
            return;
        }
        toolTip = createToolTip(context, text, backgroundColor);
        toolTipView = createToolTipView(context, toolTip, anchorView, parentView, gravity);
        if (delay > 0L) {
            toolTipView.showDelayed(delay);
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    anchorView.setTag(toolTipView);
                    ((ToolTipView) anchorView.getTag()).remove();
                    anchorView.setTag(null);
                }
            }, 1000);
        } else {
            toolTipView.show();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    anchorView.setTag(toolTipView);
                    ((ToolTipView) anchorView.getTag()).remove();
                    anchorView.setTag(null);
                }
            }, 1000);

        }
        anchorView.setTag(toolTipView);
        toolTipView.setOnToolTipClickedListener(new ToolTipView.OnToolTipClickedListener() {
            @Override
            public void onToolTipClicked(ToolTipView toolTipView) {
                anchorView.setTag(null);
            }
        });
    }


}
