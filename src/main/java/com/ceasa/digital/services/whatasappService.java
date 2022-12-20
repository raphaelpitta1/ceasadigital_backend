package com.ceasa.digital.services;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class whatasappService {

    private String number;
    private String message;


   


    public String getNumber() {
        return number;
    }





    public void setNumber(String number) {
        this.number = number;
    }





    public String getMessage() {
        return message;
    }





    public void setMessage(String message) {
        this.message = message;
    }





    public void sendMessage(){

        HttpPost post = new HttpPost("http://localhost:3000/send-text");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("phone", this.getNumber()));
        urlParameters.add(new BasicNameValuePair("message", this.getMessage()));
        

        

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
             CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post);
             System.out.println(EntityUtils.toString(response.getEntity()));
             
        }catch(Exception e){

            System.out.println(e);
        }

           
        }


    


    
}
