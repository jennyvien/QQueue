package edu.ucsb.cs.cs185.qqueue.qqueue;

import java.util.ArrayList;

/**
 * Created by Jenny on 6/3/2016.
 */
public class MyData {
    static String[] questions_1 = {
        "What's just as fun at the age of 5 as it is at the age of 25?\n",
        "What does everyone else do that you refuse to do?\n",
        "What is something people brag about, but should NOT be proud of?\n",
        "What is an invention that harmed humanity far more than it helped humanity?\n",
        "What's something you did as a child that you still feel horrible about?\n",
    };

//    ArrayList<String> questionsAL_1 = new ArrayList<>( Arrays.asList(questions_1));


    static String[] questions_2 = {
            "Why aren't you working your dream job?\n",
            "What fictional creature would taste the best if eaten?\n",
            "What used to be normal not too long ago?\n",
            "What is something the world NEEDS, but hasn't been made?\n",
    };

//    ArrayList<String> questionsAL_2 = new ArrayList<>( Arrays.asList(questions_2));


    static String[] questions_3 = {
            "Dumpster divers, what's your best find?\n",
            "What pop culture reference do you make all the time, yet no one gets?\n",
            "What is your most worthless achievement?\n",
            "Why aren't you working your dream job?\n",
    };

//    ArrayList<String> questionsAL_3 = new ArrayList<>( Arrays.asList(questions_3));


    static String[] questions_4 = {
            "What used to be normal not too long ago?\n",
            "What is a previously strongly held belief that you were embarrassingly wrong about?\n",
            "What is the laziest thing you have witnessed?\n",
            "What is something that you can honestly say you're good at?\n",
            "What's something you hate but would grow to miss if it were gone?"
    };

//    ArrayList<String> questionsAL_4 = new ArrayList<>( Arrays.asList(questions_4));


    static String[] browseQueueNames = {
            "Group fun",
            "French",
            "Ice Breaker",
            "First Date",
            "Get to know the bestie",
    };



    static String[] yourLibraryQueueNames = {
            "Favorites"
//            ,
//            "Campfire",
//            "German",
//            "What's hot",
//            "Something"
    };

//    ArrayList<String> yourLibraryQueueNamesAL = new ArrayList<>( Arrays.asList(yourLibraryQueueNames));

    ArrayList<String> questionsNSFW = new ArrayList<String>();
    ArrayList<String> questionsSerious = new ArrayList<String>();

    ArrayList<String> questionsSeriousNSFW = new ArrayList<String>();


    public ArrayList<String> getQuestionsSerious() {
        return questionsSerious;
    }

    public ArrayList<String> getQuestionsNSFW() {
        return questionsNSFW;
    }

    public ArrayList<String> getQuestionsSeriousNSFW() {
        return questionsSeriousNSFW;
    }

    public MyData() {
        questionsNSFW.add("What's the craziest thing you've ever walked in on?");
        questionsNSFW.add("What's the most fucked up story you've ever heard?");
        questionsNSFW.add("What is your best truth or dare story?");
        questionsNSFW.add("What is a really inappropriate question you have always wanted to ask?");
        questionsNSFW.add("What is a really morbid question you have always wanted to ask?");

        questionsSerious.add("What is something that you can honestly say you're good at?\n");
        questionsSerious.add("What's something you hate but would grow to miss if it were gone?\n");
        questionsSerious.add("What is something the world NEEDS, but hasn't been made?\n");
        questionsSerious.add("What's something that everyone finds normal but makes you extremely uncomfortable?\n");
        questionsSerious.add(" What is the story of/behind the worst date you've ever been on?\n");

        questionsSeriousNSFW.add("What's something that everyone finds normal but makes you extremely uncomfortable? ");
        questionsSeriousNSFW.add("What is the story of/behind the worst date you've ever been on?");
        questionsSeriousNSFW.add("What is your fetish and how common do you think it is?");
        questionsSeriousNSFW.add("What is something you find unacceptable, but society has labeled it acceptable?");
        questionsSeriousNSFW.add("Whats the most fucked up thing you've witnessed?");
    }

}
