package com.dev.responses;

public class SecurityQuestionAnswerResponse extends BasicResponse{
     private String securityQuestionAnswer;

    public SecurityQuestionAnswerResponse() {
    }

    public SecurityQuestionAnswerResponse(boolean success, Integer errorCode,String securityQuestionAnswer) {
        super(success, errorCode);
        this.securityQuestionAnswer=securityQuestionAnswer;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }
}
