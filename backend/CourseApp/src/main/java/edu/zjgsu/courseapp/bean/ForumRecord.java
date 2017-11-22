package edu.zjgsu.courseapp.bean;

public class ForumRecord {

    private String courseId;
    private String commentTime;
    private String commentBy;
    private String content;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ForumRecord() {

    }

    public ForumRecord(String courseId, String commentTime, String commentBy, String content) {

        this.courseId = courseId;
        this.commentTime = commentTime;
        this.commentBy = commentBy;
        this.content = content;
    }
}
