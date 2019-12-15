package com.botelho.commons;
import java.io.Serializable;
import java.util.List;

public class Search implements Serializable {
    public String words;
    public transient List<WebPage> order_result;

    public Search(String words, List<WebPage> result) {
        this.words = words;
        this.order_result = result;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public void setOrder_result(List<WebPage> order_result) {
        this.order_result = order_result;
    }

    public List<WebPage> getOrder_result() {
        return order_result;
    }

    public String getWords() {
        return words;
    }

    @Override
    public String toString() {
        return words;
    }
}