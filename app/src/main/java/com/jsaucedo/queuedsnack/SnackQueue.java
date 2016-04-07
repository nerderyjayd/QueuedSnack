package com.jsaucedo.queuedsnack;

import android.support.design.widget.Snackbar;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages queue of snackbars
 *
 * Created by jsaucedo on 4/6/16.
 */
public class SnackQueue {
    private static SnackQueue ourInstance = new SnackQueue();
    Queue<QueuedSnack> snackbars = new LinkedList<>();

    public static SnackQueue getInstance() {
        return ourInstance;
    }

    private SnackQueue() {
    }


    /**
     * This method adds snackbars onto the snackbar queue and gives them a callback
     *
     * @param queuedSnack
     */
    public void enqueueSnack(QueuedSnack queuedSnack) {
        Snackbar snackbar = queuedSnack.getSnackbar();
        if (snackbars.size() == 0) {
            snackbar.show();
        }
        snackbars.add(queuedSnack);
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                if (event == Snackbar.Callback.DISMISS_EVENT_ACTION || event == Snackbar.Callback
                        .DISMISS_EVENT_TIMEOUT) {
                    snackbars.remove();
                    showNextSnack();
                }
            }
        });
    }

    /**
     * Finds next available snack in the queue and shows it
     */
    private void showNextSnack() {
        if (snackbars.size() > 0) {
            if (snackbars.peek().getSnackbar() != null) {
                snackbars.peek().getSnackbar().show();
            } else {
                snackbars.remove();
                showNextSnack();
            }
        }
    }
}
