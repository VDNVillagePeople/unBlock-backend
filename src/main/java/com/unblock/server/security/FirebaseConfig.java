package com.unblock.server.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;

@Configuration
public class FirebaseConfig {

  @PostConstruct
  public void init() throws Exception {
    File file = ResourceUtils.getFile("classpath:firebase_service_account_credentials.json");
    FileInputStream serviceAccount =
        new FileInputStream(file);

    FirebaseOptions options =
        new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://probable-splice-195005.firebaseio.com")
            .build();

    FirebaseApp.initializeApp(options);
  }
}
