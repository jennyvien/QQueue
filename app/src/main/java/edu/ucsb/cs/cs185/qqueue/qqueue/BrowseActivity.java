package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.res.TypedArray;
import android.os.Bundle;

public class BrowseActivity extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_browse);
            setupNavigationDrawer();
        }

}
