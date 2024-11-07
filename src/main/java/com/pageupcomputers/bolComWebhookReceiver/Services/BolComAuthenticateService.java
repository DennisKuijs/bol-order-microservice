package com.pageupcomputers.bolComWebhookReceiver.Services;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pageupcomputers.bolComWebhookReceiver.DTO.TokenDTO;
import com.pageupcomputers.bolComWebhookReceiver.Exceptions.BolComBadCredentialsException;
import com.pageupcomputers.bolComWebhookReceiver.Utils.LocalDateTimeHelper;
import kong.unirest.Unirest;

@Service
public class BolComAuthenticateService {

    @Value("${bol.clientId}")
    private String clientId;

    @Value("${bol.clientSecret}")
    private String clientSecret;

    /**
     * Instanstiate the Logger class
     */
    Logger logger = LoggerFactory.getLogger(BolComAuthenticateService.class);

    /**
     * API Call to the bol.com API to retrieve a new access token based on the user credentials (ClientId + Client Secret)
     */
    private void retrieveToken() {
        Unirest.post("https://login.bol.com/token?grant_type=client_credentials")
        .basicAuth(clientId, clientSecret)
        .asObject(TokenDTO.class)
        .ifSuccess(response -> {
            logger.debug(String.format("Created new Bol.com Accesstoken, token valid for the next %s seconds", response.getBody().getExpiresIn()));
            System.setProperty("accessToken", response.getBody().getAccessToken());
        })
        .ifFailure(response -> {
            throw new BolComBadCredentialsException("There is something wrong with the Bol.com credentials, please make sure you enter a valid Client ID and Client Secret");
        });
    }

    /**
     * Function to decode the access token (JWT) we receive from the Bol.com API
     * @param accessToken
     * @return DecodedJWT token
     */
    private DecodedJWT decodeToken(String accessToken) {

        logger.debug(String.format("accessToken contents: %s", accessToken));
        
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.decode(accessToken);
        } catch (JWTDecodeException e) {
            throw new JWTDecodeException("There was a problem while decoding the Bol.com Accesstoken");
        }

        logger.debug(String.format("Return DecodedJWT %s", decodedJWT.getId()));

        return decodedJWT;
    }

    /**
     * Function to check if the access token is still valid based on the expiry time
     * @param accessToken
     * @return Boolean which indicates if the access token is still valid
     */
    private boolean isTokenValid(String accessToken) {

        logger.debug(String.format("accessToken contents: %s", accessToken));

        DecodedJWT decodedJWT = decodeToken(accessToken);
        LocalDateTime expiresAt = LocalDateTimeHelper.convertDateToLocalDateTime(decodedJWT.getExpiresAt());

        logger.debug(String.format("Token expires at %s", expiresAt));

        return expiresAt.isAfter(LocalDateTime.now());
    }

    /**
     * Function to check if the access token is still valid and if necessary retrieve a new token
     */
    public void checkAccessToken() {
        String accessToken = System.getProperty("accessToken");
        if(accessToken == null || !isTokenValid(accessToken)) {
            logger.debug("Token doesn't exists or is not valid anymore, creating a new one");
            retrieveToken();
        }
    }
}
