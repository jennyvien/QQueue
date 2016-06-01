package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CardQueueActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_queue);
        setupNavigationDrawer();

    }
}
