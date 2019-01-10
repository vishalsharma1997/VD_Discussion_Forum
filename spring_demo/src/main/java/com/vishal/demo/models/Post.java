package com.vishal.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import java.util.Date;

@Document
public class Post {
    @Id
    private String id;
    private String userEmailId;

    private String heading, body;
    private Date timestamp;

    public Post(String userEmailId, String heading, String body){
        this.userEmailId = userEmailId;
        this.heading = heading;
        this.body = body;
    }
    public Post() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }
    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getUserEmailId() {
        return userEmailId;
    }
    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }



    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", userEmailId='" + userEmailId + '\'' +
                ", heading='" + heading + '\'' +
                ", body='" + body + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
