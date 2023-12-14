package com.app.Jackson;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
    private Date date;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
