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
public class News {
    private int id;
    private int c_id;
    private String c_name;
    private String title;
    private String content;
    private String create_date;
    private String update_date;
    private int read;
    private int create_by;
    private String status;
    private int like;
    private int isblog;
    private int collect;

    private Integer start;            // 起始行
    private Integer rows;             // 所取行数
}
