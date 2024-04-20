package com.dev.controllers;

import com.dev.objects.User;
import com.dev.responses.BasicResponse;
import com.dev.responses.LoginResponse;
import com.dev.responses.SecurityQuestionAnswerResponse;
import com.dev.responses.SecurityQuestionResponse;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static com.dev.utils.Errors.*;

@RestController
public class LoginController {


    @Autowired
    private Utils utils;

    @Autowired
    private Persist persist;

    @RequestMapping(value = "check-if-username-available")
    public BasicResponse checkIfUsernameAvailable (String username) {
        BasicResponse basicResponse = new BasicResponse();
        User fromDb = persist.getUserByUsername(username);
        boolean success = false;
        Integer errorCode = null;
        if (fromDb == null) {
            success = true;
        }else {
            errorCode = ERROR_USERNAME_ALREADY_EXISTS;

        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse ;
    }


        @RequestMapping(value = "sign-up")
    public BasicResponse signUp (String username, String password,int securityQuestionNumber,String answerSecurityQuestion) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                if (securityQuestionNumber!=0){
                    if (answerSecurityQuestion!=null){
                        if (utils.isStrongPassword(password)) {
                            User fromDb = persist.getUserByUsername(username);
                            if (fromDb == null) {
                                User toAdd=new User(username,utils.createHash(username,password),securityQuestionNumber,answerSecurityQuestion);
                                persist.saveUser(toAdd);
                                success = true;
                            } else {
                                errorCode = ERROR_USERNAME_ALREADY_EXISTS;
                            }
                        } else {
                            errorCode = ERROR_WEAK_PASSWORD;
                        }
                    }else {
                        errorCode=ERROR_ANSWER_SECURITY_QUESTION_NOT_FOUND;
                    }
                }else {
                    errorCode= ERROR_SECURITY_QUESTION_NUMBER_IS_NOT_VALID;
                }

            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }



    @RequestMapping (value = "login")
    public BasicResponse login (String username, String password) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                String token = utils.createHash(username, password);
                User fromDb = persist.getUserByUsernameAndToken(username, token);
                if (fromDb != null) {
                    success = true;
                    basicResponse = new LoginResponse(token);
                } else {
                    errorCode = ERROR_WRONG_LOGIN_DETAILS;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }
    @RequestMapping(value = "get-users-size" , method = RequestMethod.GET)
      public int getUsersSize(){
        return persist.getAllUsers().size();
    }

    @RequestMapping(value = "get-username-by-token" , method = RequestMethod.GET)
    public BasicResponse getUsersSize(String token){
        BasicResponse response;
        User user=persist.getUserByToken(token);
        if (user!=null){
            response=new LoginResponse(true,null, user.getUsername());
        }else response=new BasicResponse(false,ERROR_USER_NOT_FOUND);
        return response;
    }
    @RequestMapping(value = "get-security-question-answer")
        public BasicResponse getSecurityQuestionAnswer(String username){
          BasicResponse basicResponse;
            User user=persist.getUserByUsername(username);
            if (user!=null){
                basicResponse=new SecurityQuestionAnswerResponse(true,null,user.getAnswerSecurityQuestion());
            }else
                basicResponse=new BasicResponse(false, ERROR_USER_NOT_FOUND);
          return basicResponse;
        }
    @RequestMapping(value = "get-security-question")
    public BasicResponse getSecurityQuestion(String username){
        BasicResponse basicResponse;
        User user=persist.getUserByUsername(username);
        if (user!=null){
            basicResponse=new SecurityQuestionResponse(true,null,user.getSecurityQuestionNumber());
        }else
            basicResponse=new BasicResponse(false, ERROR_USER_NOT_FOUND);
        return basicResponse;
    }

@RequestMapping (value = "reset-password")
    public BasicResponse resetPassword(String username,String newPassword){
    User user=persist.getUserByUsername(username);
    BasicResponse basicResponse;
    if (user!=null){
        String newToken=utils.createHash(username, newPassword);
        persist.resetPassword(user,newToken);
        basicResponse=new BasicResponse(true,null);
    }else {
       basicResponse=new BasicResponse(false,ERROR_USER_NOT_FOUND);
    }
    return basicResponse;
    }



}




