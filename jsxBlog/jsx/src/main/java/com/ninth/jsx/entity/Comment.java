package com.ninth.jsx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Comment {
    private int id;
    private int news_id;
    private String content;
    private String create_date;
    private int create_by;
    private String level;
    private String recvto;
}
