package edu.mum.onlineshopping.domain;

public class TinyEmailObject {
    private String to;
    private String subject;
    private String text;

    private TinyEmailObject(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static TinyEmailObject buildEmailObject(String to, String subject, String text) {
        return new TinyEmailObject(to, subject, text);
    }
}
