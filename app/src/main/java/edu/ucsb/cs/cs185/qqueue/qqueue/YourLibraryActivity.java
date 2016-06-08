package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class YourLibraryActivity extends BaseActivity {


    private final String QUESTIONS = "QUESTIONS";
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    static ArrayList<BrowseItem> libraryItems;
    private Button buttonUse;
    private Button buttonEdit;
    private int flag = 0;
    private final String DEBUG = "PA_DEBUG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_library);
        setupNavigationDrawer();
        getSupportActionBar().setTitle(R.string.activity_your_library);

//        View icon =  (View) findViewById(R.id.settings);
//        icon.setForeground(getDrawable(R.drawable.ic_add_black_24dp));
//        icon.setForeground(getDrawable(R.drawable.ic_menu_black_24dp));


        recyclerView = (RecyclerView) findViewById(R.id.yl_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


//            libraryItems = new ArrayList<BrowseItem>();
//            for (int i = 0; i < MyData.yourLibraryQueueNames.length; i++) {
//                libraryItems.add(new BrowseItem(
//                        MyData.yourLibraryQueueNames[i],
//                        MyData.questions_3
//                ));
//            }




        adapter = new ItemAdapter(libraryItems, true);
        recyclerView.setAdapter(adapter);
    }

    static String cat( String[] list){
        String temp = "";
        for(int i = 0; i<list.length; i++)
        {
            temp = temp + list[i] + " ";
        }
        return temp;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_your_library);
        setupNavigationDrawer();
        getSupportActionBar().setTitle(R.string.activity_your_library);

        recyclerView = (RecyclerView) findViewById(R.id.yl_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ItemAdapter(libraryItems, true);
        recyclerView.setAdapter(adapter);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        finish();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        setContentView(R.layout.activity_your_library);
//        setupNavigationDrawer();
//        getSupportActionBar().setTitle(R.string.activity_your_library);
//
//        recyclerView = (RecyclerView) findViewById(R.id.yl_recycler_view);
//        recyclerView.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        adapter = new ItemAdapter(libraryItems, true);
//        recyclerView.setAdapter(adapter);
//    }

    public void refresh(){
        //setContentView(R.layout.activity_your_library);

//        Intent refresh = new Intent(this, YourLibraryActivity.class);
////        startActivity(getIntent());
//        startActivity(refresh);
//        this.finish();


       setContentView(R.layout.activity_your_library);
        setupNavigationDrawer();
        getSupportActionBar().setTitle(R.string.activity_your_library);

        recyclerView = (RecyclerView) findViewById(R.id.yl_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ItemAdapter(libraryItems, true);
        recyclerView.setAdapter(adapter);

    }


    static ArrayList<String> getPlaylistNames(){
        ArrayList<String> plItems = new ArrayList<>();
        for (int i = 0; i < libraryItems.size(); i++) {
            plItems.add(libraryItems.get(i).getQueueName());
        }
        return plItems;
    }

    public void addNewQueue( String name){
        String[] list = new String[0];
        BrowseItem item = new BrowseItem(name, list);
        libraryItems.add(item);
    }

    public void addToLibraryItems( String queueName, String question ){
        for (int i = 0; i < libraryItems.size(); i++) {
            BrowseItem item =libraryItems.get(i);
            Log.d(DEBUG, item.getQueueName());
            String qName = item.getQueueName();
            if(qName.equals(queueName) ){
                String[] list = item.getQueueItems();
                ArrayList<String> temp = CurrentQueueActivity.convertFromString(list);
//                temp.add(question);
                temp.add(question);
                String[] list2 = CurrentQueueActivity.convert(temp);
                item.setQueueItems(list2);
                libraryItems.set(i,item);
                Log.d(DEBUG, "added");

            }
        }

    }
    public void updateLibraryItem( String queueName, String[] questions ){

        Log.d(DEBUG, queueName);

        for (int i = 0; i < libraryItems.size(); i++) {
            BrowseItem item =libraryItems.get(i);
            Log.d(DEBUG, item.getQueueName());
            String qName = item.getQueueName();
            if(qName.equals(queueName) ){
//                ArrayList<String> temp = CurrentQueueActivity.convertFromString(questions);
//                String[] list2 = CurrentQueueActivity.convert(temp);
//                item.setQueueItems(list2);
                item.setQueueItems(questions);
                libraryItems.set(i,item);

                Log.d(DEBUG, "set");

            }
        }

    }

    public void addListToLibraryItem( String queueName, String[] questions) {
        Log.d(DEBUG, queueName);

        for (int i = 0; i < libraryItems.size(); i++) {
            BrowseItem item = libraryItems.get(i);
            Log.d(DEBUG, item.getQueueName());
            String qName = item.getQueueName();
            if (qName.equals(queueName)) {
                for(int j = 0; j < questions.length; j++) {
                    addToLibraryItems(queueName, questions[j]);
                }
            }
        }
    }



    public void removeLibraryItem( String queueName ){

        Log.d(DEBUG, queueName);

        for (int i = 0; i < libraryItems.size(); i++) {
            BrowseItem item =libraryItems.get(i);
            Log.d(DEBUG, item.getQueueName());
            String qName = item.getQueueName();
            if(qName.equals(queueName) ){
//                ArrayList<String> temp = CurrentQueueActivity.convertFromString(questions);
//                String[] list2 = CurrentQueueActivity.convert(temp);
//                item.setQueueItems(list2);

                libraryItems.remove(i);

                Log.d(DEBUG, "set");

            }
        }

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        Intent intent = new Intent(CurrentQueueActivity.CQAview.getContext(), CurrentQueueActivity.class);
//        startActivity(intent);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu_lib, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:

                PlaylistNewPlaylistDialog dialog = new PlaylistNewPlaylistDialog();
                dialog.show(getFragmentManager(), "newQueue");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
