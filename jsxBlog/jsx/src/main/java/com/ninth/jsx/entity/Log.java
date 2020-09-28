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
public class Log {
    private int id;
    private String title;
    private int create_by;
    private String create_date;
    private String user_agent;
    private String method;

    private Integer start;            // 起始行
    private Integer rows;             // 所取行数
}
