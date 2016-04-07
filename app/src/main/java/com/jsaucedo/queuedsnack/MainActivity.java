package com.jsaucedo.queuedsnack;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements QueuedSnack.SnackClickListener {
    SnackQueue snackQueue = SnackQueue.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<QueuedSnack> snacks = new ArrayList<>();

        snacks.add(new QueuedSnack(this, "Knock Knock", R.color.colorAccent, R.color.colorPrimary,
                Snackbar.LENGTH_LONG));

        snacks.add(new QueuedSnack(this, "Who's there?", R.color.colorPrimary, R.color.colorAccent,
                Snackbar.LENGTH_LONG));

        snacks.add(new QueuedSnack(this, "Canoe", R.color.colorAccent, R.color.colorPrimary,
                Snackbar.LENGTH_LONG));

        snacks.add(new QueuedSnack(this, "Canoe who?", R.color.colorPrimary, R.color.colorAccent,
                Snackbar.LENGTH_LONG));

        snacks.add(new QueuedSnack(this, "Canoe acknowledge this snackbar?", R.color.colorAccent,
                R.color.colorPrimary, Snackbar.LENGTH_INDEFINITE, "hahaha", this));

        snacks.add(new QueuedSnack(this, "Okay last one", R.color.colorAccent, R.color.colorPrimary,
                Snackbar.LENGTH_INDEFINITE, "lol", this));

        findViewById(R.id.queuedSnack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnacks(true, snacks);
            }
        });
        findViewById(R.id.nonqueuedSnack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnacks(false, snacks);
            }
        });
    }


    /**
     * Show snackbars either in from a queue or all at once
     *
     * @param queued - whether to show snackbars queued
     * @param snacks - our list of snackbars
     */
    private void showSnacks(boolean queued, ArrayList<QueuedSnack> snacks) {
        for (QueuedSnack snack : snacks) {
            if (queued) {
                snackQueue.enqueueSnack(snack);
            } else {
                snack.getSnackbar().show();
            }
        }
    }

    @Override
    public void onClick(Snackbar snackbar) {
        snackbar.dismiss();
    }

}
