package com.sda.springbootresthibernate.models.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestParams {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
