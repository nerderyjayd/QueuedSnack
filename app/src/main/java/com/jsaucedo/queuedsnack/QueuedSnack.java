package com.jsaucedo.queuedsnack;


import android.app.Activity;
import android.support.annotation.ColorRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

/**
 * Wrapper for snackbar to allow queueing so they don't just override eachother.
 * <p/>
 * Created by jsaucedo on 4/4/16.
 */
public class QueuedSnack implements View.OnClickListener {

    private Snackbar snackbar;
    private SnackClickListener snackClickListener;

    public interface SnackClickListener{
        void onClick(Snackbar snackbar);
    }

    /**
     * Shows a snack bar with the given message
     *
     * @param activity  - current activity
     * @param message   -  message for the snackbar
     * @param textColor - text color in the snackbar
     * @param bgColor   - background color of the snack bar
     */
    public QueuedSnack(Activity activity, String message, @ColorRes int textColor,
                       @ColorRes int bgColor,
                       int duration) {
        snackbar = buildSnack(activity, message, textColor, bgColor, duration, null, null);
    }

    /**
     * Shows a snack bar with the given message
     *
     * @param activity        - current activity
     * @param message         -  message for the snackbar
     * @param textColor       - text color in the snackbar
     * @param bgColor         - background color of the snack bar
     * @param action          - action text for snackbar (set to null if no action text is required)
     * @param snackClickListener - click listener for action text - set to null if no action text
     */
    public QueuedSnack(Activity activity, String message, @ColorRes int textColor,
                       @ColorRes int bgColor,
                       int duration,
                       String action, SnackClickListener snackClickListener) {
        snackbar = buildSnack(activity, message, textColor, bgColor, duration, action,
                snackClickListener);

    }

    private Snackbar buildSnack(Activity activity, String message, @ColorRes int textColor,
                                @ColorRes int bgColor,
                                int duration,
                                String action, SnackClickListener snackClickListener) {
        this.snackClickListener = snackClickListener;
        snackbar =
                Snackbar.make(activity.findViewById(R.id.root), message,
                        duration);

        if (action != null) {
            snackbar.setAction(action, this);
        }

        snackbar.setActionTextColor(ContextCompat.getColor(activity, textColor));
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(activity, bgColor));
        TextView textView =
                (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(activity, textColor));
        return snackbar;
    }

    @Override
    public void onClick(View v) {
        snackClickListener.onClick(getSnackbar());
    }

    public Snackbar getSnackbar() {
        return snackbar;
    }
}

