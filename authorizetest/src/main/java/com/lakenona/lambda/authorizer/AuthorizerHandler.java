package com.lakenona.lambda.authorizer;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.lakenona.lambda.authorizer.io.AccessLevel;
import com.lakenona.lambda.authorizer.io.AuthPolicy;
import com.lakenona.lambda.authorizer.io.TokenAuthorizerContext;
import com.lakenona.lambda.authorizer.io.ValidateToken;

public class AuthorizerHandler implements RequestHandler<TokenAuthorizerContext, AuthPolicy> {
private boolean valid;
private ValidateToken validator;
private AccessLevel accLevel;
    @Override
    public AuthPolicy handleRequest(TokenAuthorizerContext input, Context context) {
        context.getLogger().log("Input: " + input);
        String principalId = "user124";
        
        if (!validator.validate(input.getAuthorizationToken())) {
        	throw new RuntimeException("Unauthorized");
        }
        
        //Get Details from Token
        
        String methodArn = input.getMethodArn();
    	String[] arnPartials = methodArn.split(":");
    	String region = arnPartials[3];
    	String awsAccountId = arnPartials[4];
    	String[] apiGatewayArnPartials = arnPartials[5].split("/");
    	String restApiId = apiGatewayArnPartials[0];
    	String stage = apiGatewayArnPartials[1];
    	//String httpMethod = apiGatewayArnPartials[2];
    	String resource = ""; // root resource
    	if (apiGatewayArnPartials.length == 4) {
    		resource = apiGatewayArnPartials[3];
    	}

        //Now, if the token is valid we have to find out what is the policy that is 
        //associated with the given IAM user,xxx;
         //This policy can come from Db or be generated dynamically
        accLevel = getAccessLevel(principalId);
        if (accLevel.equals(AccessLevel.ALLOWALL)) {
        	return new AuthPolicy(principalId, AuthPolicy.PolicyDocument.getAllowAllPolicy(region, awsAccountId, restApiId, stage));
        }
        else 
        	return 	new AuthPolicy(principalId, AuthPolicy.PolicyDocument.getDenyAllPolicy(region, awsAccountId, restApiId, stage));

        // TODO: implement your handler
       // return "Hello from Lambda!";
    }
    
    public AccessLevel getAccessLevel(String principal) {
    	AccessLevel accLevel = AccessLevel.DENYALL;
    	switch(principal) {
    	case "user123":
    		accLevel =  AccessLevel.ALLOWALL;
    		break;
    	case "user124":
    		accLevel =  AccessLevel.ALLOWONE;
    		break;
    	case "user125":
    		accLevel =  AccessLevel.DENYALL;
    		break;
    	case "user126":
    		accLevel =  AccessLevel.DENYONE;
    		break;	
    	default:
    		accLevel =  AccessLevel.DENYALL;
    	
    	}
    	return accLevel;
    }

}
