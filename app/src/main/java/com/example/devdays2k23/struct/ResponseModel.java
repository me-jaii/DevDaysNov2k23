package com.example.devdays2k23.struct;

import java.util.List;

public class ResponseModel {
    private String status;
    private int totalResukts;
    private List<ArticleModel> articleModels =null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResukts() {
        return totalResukts;
    }

    public void setTotalResukts(int totalResukts) {
        this.totalResukts = totalResukts;
    }

    public List<ArticleModel> getArticles() {
        return articleModels;
    }

    public void setArticles(List<ArticleModel> articleModels) {
        this.articleModels = articleModels;
    }
}
