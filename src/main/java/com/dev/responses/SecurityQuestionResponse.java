package com.dev.responses;

public class SecurityQuestionResponse extends BasicResponse {
    private int securityQuestion;

    public SecurityQuestionResponse(int securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public SecurityQuestionResponse(boolean success, Integer errorCode, int securityQuestion) {
        super(success, errorCode);
        this.securityQuestion = securityQuestion;
    }

    public int getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(int securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
}
