package ru.tinkoff.edu.java.bot.service;

import java.util.ArrayList;
import java.util.List;

public class LinkTrackerImpl implements LinkTracker{

    private final List<String> listOflLinks;

    public LinkTrackerImpl(){
        listOflLinks = new ArrayList<>();
    }

    @Override
    public void addLink(String currentLink) {
        listOflLinks.add(currentLink);
    }

    @Override
    public String getLinks() {
        String message = "";
        if (listOflLinks.size() != 0){
            for (int i = 0; i < listOflLinks.size(); ++i)
                message += listOflLinks.get(i);
        }
        return message;
    }


}
