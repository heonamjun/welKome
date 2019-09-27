package com.example.sktrip.Data;
public class Footer extends RecyclerItem {
    private String Quote;
    private String Author;
    private String ImageUrl;

    public Footer(String quote, String author, String imageUrl) {
        Quote = quote;
        Author = author;
        ImageUrl = imageUrl;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
