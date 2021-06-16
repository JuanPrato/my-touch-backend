package com.juanprato.mytouch.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseService {

    private final FirebaseApp firebaseApp;
    private final FirebaseDatabase firebaseDatabase;
    private final Bucket bucket;

    public FirebaseService(@Value ("${firebase.credentials}") String credentials) throws IOException {
         this.firebaseApp = FirebaseApp.initializeApp(getOptions(credentials));
         this.firebaseDatabase = FirebaseDatabase.getInstance(firebaseApp);
         this.bucket = StorageClient.getInstance().bucket();
    }

    private static FirebaseOptions getOptions(String credentials){
        try {
            return FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(credentials.getBytes())))
                    .setDatabaseUrl("https://my-touch-69b3c-default-rtdb.firebaseio.com/")
                    .setStorageBucket("my-touch-69b3c.appspot.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String uploadFile(String route, MultipartFile file) throws Exception{
        try {
            Blob blob = bucket.create(String.format("%s/%s", route, file.getName()), file.getInputStream(), file.getContentType());
            return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",blob.getBlobId().getBucket(), blob.getBlobId().getName().replace("/", "%2F"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error on upload of file " + file.getName(), e);
        }
    }

}
