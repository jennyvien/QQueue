package edu.ucsb.cs.cs185.qqueue.qqueue;

/**
 * Created by Jenny on 6/3/2016.
 */
public class BrowseItem {

    private String queueName;

    private String[] queueItems;

    public BrowseItem(String name, String[] questions) {
        queueName = name;
        queueItems = questions;
    }

    public String[] getQueueItems() {
        return queueItems;
    }


    public void setQueueItems(String[] queueItems) {
        this.queueItems = queueItems;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }



}
