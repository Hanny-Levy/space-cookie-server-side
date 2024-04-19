package com.dev.objects;

import com.dev.utils.Constants;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String username;

    @Column
    private String token;
    @Column
    private int securityQuestionNumber;
    @Column
    private String answerSecurityQuestion;


    public User(String username, String token,int securityQuestionNumber,String answerSecurityQuestion) {
        this.username = username;
        this.token = token;
        this.securityQuestionNumber=securityQuestionNumber;
        this.answerSecurityQuestion=answerSecurityQuestion;


    }

    public User() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSecurityQuestionNumber() {
        return securityQuestionNumber;
    }

    public void setSecurityQuestionNumber(int securityQuestionNumber) {
        this.securityQuestionNumber = securityQuestionNumber;
    }

    public String getAnswerSecurityQuestion() {
        return answerSecurityQuestion;
    }

    public void setAnswerSecurityQuestion(String answerSecurityQuestion) {
        this.answerSecurityQuestion = answerSecurityQuestion;
    }
}
