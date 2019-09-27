package com.lakenona.lambda.authorizer.io;

public class TokenAuthorizerContext {
	String type;
    String authorizationToken;
    String methodArn;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthorizationToken() {
		return authorizationToken;
	}
	public void setAuthorizationToken(String authorizationToken) {
		this.authorizationToken = authorizationToken;
	}
	public String getMethodArn() {
		return methodArn;
	}
	public void setMethodArn(String methodArn) {
		this.methodArn = methodArn;
	}
	@Override
	public String toString() {
		return "TokenAuthorizerContext [type=" + type + ", authorizationToken=" + authorizationToken + ", methodArn="
				+ methodArn + "]";
	}
	public TokenAuthorizerContext(String type, String authorizationToken, String methodArn) {
		super();
		this.type = type;
		this.authorizationToken = authorizationToken;
		this.methodArn = methodArn;
	}
	public TokenAuthorizerContext() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
