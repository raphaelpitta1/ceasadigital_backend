package com.ceasa.digital.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class chatFirebaseRunner {


   @Bean
    public void loadFirebaseConf() throws IOException{

        ClassLoader classLoader = chatFirebaseRunner.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("ceasawebchat-adminsdk.json")).getFile());

        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
        
        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();
      
      FirebaseApp.initializeApp(options);
    
    }

}
