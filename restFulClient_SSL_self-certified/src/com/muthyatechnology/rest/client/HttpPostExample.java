package com.muthyatechnology.rest.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

public class HttpPostExample {
	public static void main(String[] args) throws UnsupportedEncodingException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://localhost:8443/MuthyaApp/api/v2/users/auth");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName", "admin");
        jsonObject.put("Password", "admin");
       StringEntity engity = new StringEntity(jsonObject.toJSONString());
      /*  // Create some NameValuePair for HttpPost parameters
        List<NameValuePair> arguments = new ArrayList<>(3);
        arguments.add(new BasicNameValuePair("UserName", "admin"));
        arguments.add(new BasicNameValuePair("Password", "admin"));
       // arguments.add(new BasicNameValuePair("lastName", "Administrator"));
*/
        try {
            post.setEntity(engity);
            HttpResponse response = client.execute(post);

            // Print out the response message
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
