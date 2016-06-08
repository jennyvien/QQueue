package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jenny on 6/3/2016.
 */
public class MyData {
    static final String DEBUG = "PA_DEBUG";

    static String[] questions_1 = {
            "Why is Professor Hollerer your favorite Professor?\n",
            "Why is CS 185 your favorite class?\n",
            "How are Nataly and Sam so amazing?\n"
//        "What's just as fun at the age of 5 as it is at the age of 25?\n",
//        "What does everyone else do that you refuse to do?\n",
//        "What is something people brag about, but should NOT be proud of?\n",
//        "What is an invention that harmed humanity far more than it helped humanity?\n",
//        "What's something you did as a child that you still feel horrible about?\n",
    };



    static String[] favorites = {
            "Why am I in your favorites?\n",
            "How did I get here?\n",
            "Why is it so dark in here?\n"
    };
//    ArrayList<String> questionsAL_1 = new ArrayList<>( Arrays.asList(questions_1));



    static String[] questions_2 = {
            "Why aren't you working your dream job?\n",
            "What fictional creature would taste the best if eaten?\n",
            "What used to be normal not too long ago?\n",
            "What is something the world NEEDS, but hasn't been made?\n",
            "What pop culture reference do you make all the time, yet no one gets?\n",

    };

//    ArrayList<String> questionsAL_2 = new ArrayList<>( Arrays.asList(questions_2));

    static String[] questions_3 = {
            "What's just as fun at the age of 5 as it is at the age of 25?\n",
            "What does everyone else do that you refuse to do?\n",
            "What is something people brag about, but should NOT be proud of?\n",
            "What is a previously strongly held belief that you were embarrassingly wrong about?\n",
            "What's something you hate but would grow to miss if it were gone?"

    };

    static String[] questions_4 = {
            "What is your worst nightmare?\n",
            "What is your most worthless achievement?\n",
            "What is the laziest thing you have witnessed?\n",
            "What is an invention that harmed humanity far more than it helped humanity?\n",
            "What's something you did as a child that you still feel horrible about?\n",
    };

//    ArrayList<String> questionsAL_3 = new ArrayList<>( Arrays.asList(questions_3));



    static String[] questions_5 = {
            "What's your favorite sports team?\n",
            "Who is your favorite athlete?\n",
            "Which sport do you think is the easiest to play?\n",
            "Which team has the worst fans?\n",
            "What sport do you hate watching but love playing?\n",

    };

//    ArrayList<String> questionsAL_4 = new ArrayList<>( Arrays.asList(questions_4));


    static String[] browseQueueNames = {
//            "Group fun",
//            "French",
//            "Ice Breaker",
//            "First Date",
//            "Get to know the bestie",
            "Mixed Bag",
            "First Date",
            "Negative Nancy",
            "Sportsball",
    };



    static String[] yourLibraryQueueNames = {
            "Favorites"

    };

//    ArrayList<String> yourLibraryQueueNamesAL = new ArrayList<>( Arrays.asList(yourLibraryQueueNames));

    static String[] questionsNSFW = {
        "What's the craziest thing you've ever walked in on?",
        "What's the most fucked up story you've ever heard?",
        "What is your best truth or dare story?",
        "What is a really inappropriate question you have always wanted to ask?",
        "What is a really morbid question you have always wanted to ask?"
    };

    ArrayList<String> questionsNSFWAL = new ArrayList<>(Arrays.asList(questionsNSFW));

    static String[] questionsSerious = {
        "What is something that you can honestly say you're good at?",
        "What's something you hate but would grow to miss if it were gone?\n",
        "What is something the world NEEDS, but hasn't been made?\n",
        "What's something that everyone finds normal but makes you extremely uncomfortable?\n",
        "What is the story of/behind the worst date you've ever been on?\n"
    };

    ArrayList<String> questionsSeriousAL = new ArrayList<>(Arrays.asList(questionsSerious));

    static String[] questionsSeriousNSFW = {
        "What's something that everyone finds normal but makes you extremely uncomfortable? ",
        "What is the story of/behind the worst date you've ever been on?",
        "What is your fetish and how common do you think it is?",
        "What is something you find unacceptable, but society has labeled it acceptable?",
        "Whats the most messed up thing you've witnessed?"
    };

    ArrayList<String> questionsSeriousNSFWAL = new ArrayList<>(Arrays.asList(questionsSeriousNSFW));

    static String[] toArray ( ArrayList<String> arraylist ){
        String[] result = new String[arraylist.size()];
        if(arraylist.size()>0){
            for( int i = 0; i < arraylist.size(); i++){
                result[i] = arraylist.get(i);
            }
        }
        return result;
    }

    static ArrayList<String> toArrayList( String[] array ){
        ArrayList<String> result = new ArrayList<>();
        if(array.length>0){
            for( int i = 0; i < array.length; i++){
                result.add(array[i]);
            }
        }
        return result;
    }

    static String[] shuffle( String[] questions){

        if(questions == null){
            Log.d(DEBUG, "Incoming string array is empty");

            String[] test = new String[0];
            return test;
        }

        if(questions.length == 0) {
            Log.d(DEBUG, "Incoming string array is size 0");
            String[] test = new String[0];
            return test;
        }


        ArrayList<String> temp = toArrayList(questions);
        ArrayList<String> temp2 = new ArrayList<>();
        Random rand = new Random();
        while(!temp.isEmpty()){
            int n = rand.nextInt(temp.size());
            String buffer = temp.get(n);
            temp2.add(buffer);
            temp.remove(n);
        }
        String[] result = toArray(temp2);
        return result;

    }
}
