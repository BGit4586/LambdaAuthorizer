package com.lakenona.lambda.authorizer.io;

public class ValidateToken {
	// validate the incoming token
    // and produce the principal user identifier associated with the token

    // this could be accomplished in a number of ways:
    // 1. Call out to OAuth provider
    // 2. Decode a JWT token in-line
    // 3. Lookup in a self-managed DB
	
	public Boolean validate(String token) {
		
		return true;
	}

}
