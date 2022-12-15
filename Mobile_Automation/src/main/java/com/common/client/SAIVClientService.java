package com.common.client;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Lakitha Prabudh on 2/20/20
 */
//todo inject necessary envs from outside
public class SAIVClientService {
    private static final Logger logger = LoggerFactory.getLogger(SAIVClientService.class);
//  private String saivAPI = "https://api-sa.saiv-qa.arimac.xyz/api";
//  private String tokenUrl = "https://auth.saiv-qa.arimac.xyz/auth/realms/sa/protocol/openid-connect/token";   
  
    private String saivAPI = "https://api.saiv-dev.arimac.xyz/api";
    private String tokenUrl = "https://auth.saiv-qa.arimac.xyz/auth/realms/saiv/protocol/openid-connect/token";
    

//      private String tokenUrl = "https://auth.saiv-dev.arimac.xyz/auth/realms/saiv/protocol/openid-connect/token";

    //Old Config
//    public String getClientAccessToken() throws JSONException {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders httpHeaders = getAuthHeaders();
//
//            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("grant_type", "client_credentials");
//            ResponseEntity<String> exchange = restTemplate.exchange(tokenUrl, HttpMethod.POST, new HttpEntity<>(map, httpHeaders), String.class);
//            String access_token = new JSONObject(Objects.requireNonNull(exchange.getBody())).getString("access_token");
//            logger.debug("user_token = {}", access_token);
//            return access_token;
//        } catch (HttpServerErrorException | HttpClientErrorException e) {
//            HttpStatus code = e.getStatusCode();
//            if (code == HttpStatus.BAD_REQUEST)
//                logger.error("Bad Request : {}", HttpStatus.BAD_REQUEST);
//            return null;
//        }
//    }
    
    public String getClientAccessToken() throws JSONException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = getAuthHeaders();

            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "client_credentials");
            ResponseEntity<String> exchange = restTemplate.exchange(tokenUrl, HttpMethod.POST, new HttpEntity<>(map, httpHeaders), String.class);
            String access_token = new JSONObject(Objects.requireNonNull(exchange.getBody())).getString("access_token");
            logger.debug("user_token = {}", access_token);
            return access_token;
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            HttpStatus code = e.getStatusCode();
            if (code == HttpStatus.BAD_REQUEST)
                logger.error("Bad Request : {}", HttpStatus.BAD_REQUEST);
            return null;
        }
    }
 //OLD CONFIG
//    private HttpHeaders getAuthHeaders() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        //todo change client ids as env
////        QA
//        String auth = String.format("%s:%s", "android-client", "462834e6-895c-4535-8f97-c15b57ab241f");
//        
////        //Dev
////        String auth = String.format("%s:%s", "android-client", "509bf544-eb8a-40ac-b5ba-26cef264192f");
//
//        byte[] encodedAuth = Base64.encodeBase64(
//                auth.getBytes(StandardCharsets.US_ASCII));
//        httpHeaders.set("Authorization", String.format("Basic %s", new String(encodedAuth)));
//        return httpHeaders;
//    }
    
    private HttpHeaders getAuthHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        //todo change client ids as env
//        QA
//        String auth = String.format("%s:%s", "client_id", "test-client");
//        String auth = String.format("%s:%s", "client_id", "test-client");
//        httpHeaders.set("client_id", "test-client");
//        //Dev
//        String auth = String.format("%s:%s", "android-client", "509bf544-eb8a-40ac-b5ba-26cef264192f");

//        byte[] encodedAuth = Base64.encodeBase64(
//                auth.getBytes(StandardCharsets.US_ASCII));
//        httpHeaders.set("Authorization", String.format("Basic %s", new String(encodedAuth)));
//        System.out.println(String.format("Basic %s", new String(encodedAuth)));
        return httpHeaders;
    }

    private String getUserAccessToken(String username, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = getAuthHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "password");
            map.add("username", username);
            map.add("password", password);
            map.add("client_id", "test-client");

            ResponseEntity<String> exchange = restTemplate.exchange(tokenUrl,
                    HttpMethod.POST, new HttpEntity<>(map, headers), String.class);
            String access_token = new JSONObject(Objects.requireNonNull(exchange.getBody())).getString("access_token");
            System.out.println(access_token);
            logger.debug("user_token = {}", access_token);
            return access_token;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private String getUserId(String username, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + getUserAccessToken(username, password));
            headers.setContentType(MediaType.APPLICATION_JSON);

 

            ResponseEntity<String> data = restTemplate.exchange(saivAPI + "/user-mgt/users", HttpMethod.GET,
                    new HttpEntity<>("parameters", headers), String.class);
            JSONObject json = new JSONObject(Objects.requireNonNull(data.getBody()));
            return json.getJSONObject("data").get("userId").toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    private String getGroupId(String username, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("user", getUserId(username, password));
            headers.add("Authorization", "Bearer " + getUserAccessToken(username, password));
            headers.add("tenant", "sa");

            ResponseEntity<String> responseEntity = restTemplate.exchange(saivAPI + "/grp-mgt/groups", HttpMethod.GET,
                    new HttpEntity<>("parameters", headers), String.class);
            JSONObject jsonData = new JSONObject(Objects.requireNonNull(responseEntity.getBody()));
            JSONArray jsonArray = jsonData.getJSONArray("data");

            String groupId = null;
            for (Object object : jsonArray) {
                JSONObject groupJson = (JSONObject) object;
                groupId = groupJson.getString("groupId");
            }
            return groupId;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String verifyGroupInvitation(String username, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + getUserAccessToken(username, password));
            headers.add("tenant", "saiv");
            headers.setContentType(MediaType.APPLICATION_JSON);

//            System.out.println("Bearer " + getUserAccessToken(username, password));
            
            ResponseEntity<String> data = restTemplate.exchange(saivAPI + "/spt-svc/groups/acceptAll", HttpMethod.GET,
                    new HttpEntity<>("parameters", headers), String.class);

            JSONObject json = new JSONObject(Objects.requireNonNull(data.getBody()));

            return json.get("status").toString();
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
		return null;
    }
}
