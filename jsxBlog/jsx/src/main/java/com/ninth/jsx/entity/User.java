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
public class User {
    private Integer u_id;
    private String user_name;
    private String password;
    private String email;
    private String create_date;
    private String update_date;
    private Integer user_rank;
    private String status;
    private Integer belike;

    private String birthday;
    private String work;
    private String addr;
    private Integer age;
    private String mobile;
    private String sex;

    private Integer start;            // 起始行
    private Integer rows;             // 所取行数
}
