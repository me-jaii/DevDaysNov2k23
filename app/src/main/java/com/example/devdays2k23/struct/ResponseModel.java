package com.example.devdays2k23.struct;

import java.util.List;

public class ResponseModel {
    private String status;
    private int totalResults;
    private List<ArticleModel> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articleModels) {
        this.articles = articles;
    }
}
