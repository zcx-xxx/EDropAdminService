package com.edrop.utils;

import java.io.InputStream;
import java.util.UUID;
import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UCloudProvider {
    @Value("TOKEN_6af2b312-5e90-4f3b-b1fb-b9ace08fc14f")
    private String publicKey;
    @Value("d0013b4b-9638-4a3c-aaa2-a43ada48061b")
    private String privateKey;
    @Value("hiasenna")
    private String bucketName;
    @Value("cn-bj")
    private String region;
    @Value("ufileos.com")
    private String suffix;
    @Value("315360000")
    private Integer expires;
    public String upload(InputStream fileStream, String mimeType, String fileName) {
        String generatedFileName = null;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
        	return "error";
        }
        try {
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey, privateKey);
            ObjectConfig config = new ObjectConfig(region, suffix);
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(generatedFileName)
                    .toBucket(bucketName)
                    .setOnProgressListener((bytesWritten, contentLength) -> {
                    })
                    .execute();
            if (response != null && response.getRetCode() == 0) {
                String url = UfileClient.object(objectAuthorization, config)
                        .getDownloadUrlFromPrivateBucket(generatedFileName, bucketName, expires)
                        .createUrl();
                return url;
            } else {
            	return "error";
              
            }
        } catch (UfileClientException e) {
        	System.out.println(e);
        	return "error";
        } catch (UfileServerException e) {
        	System.out.println(e);
        	return "error";
        }	
    }
}