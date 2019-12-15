package com.botelho.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class WebPage implements Serializable {
    public String url, title, citation;
    public transient Map<String, Integer> words;
    public int id, link_count;


    public WebPage(int id, String url, String title, String citation, Map<String, Integer> words){
        this.id = id;
        this.url = url;
        this.title = title;
        this.citation = citation;
        this.words = words;
    }

    public void setLinks(ArrayList<String> links) {
        this.link_count = links.size();
    }

    @Override
    public String toString() {
        return "\nTitle: " + this.title + "\nurl: " + this.url + "\nCitation: " + this.citation + "\nnumber of links: " + this.link_count + "\n\n";
    }
}
