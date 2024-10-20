package jp.co.demo.controller;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {

    @Pattern(regexp="^\\w{3,32}$", message="size must be between 3 and 32, each character must be alphanumeric or underscore (A-Za-z0-9_)")
    private String username;

    @Size(min=10, max=255)
    private String password;

}