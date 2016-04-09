package com.teamlinking.single.storage;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by admin on 16/1/12.
 */
public class QiniuUpload implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(QiniuUpload.class);


    private String accessKey;
    private String secretKey;
    //目标空间
    private String bucket;
    //设置断点文件保存的位置
    private String pathFile;

    private Auth auth;
    private UploadManager uploadManager;

    public void afterPropertiesSet() throws Exception {
        auth = Auth.create(accessKey,secretKey);
        if (pathFile != null && pathFile.length() > 0){
            uploadManager = new UploadManager(new FileRecorder(pathFile));
        }else {
            uploadManager = new UploadManager();
        }
    }

    //上传图片
    public ImageResult uploadImage(byte[] data,String key){
        String returnBody = "{\"key\":$(key),\"hash\": $(hash), \"width\": $(imageInfo.width), \"height\": $(imageInfo.height)}";
        StringMap policy = new StringMap().putNotEmpty("returnBody", returnBody);
        String token = auth.uploadToken(bucket, null, 60, policy);
        return upload(data,key,token,ImageResult.class);
    }

    //从内存中上传
    public <T> T upload(byte[] data, String key, String upToken,Class<T> classOfT){
        try {
            Response res = uploadManager.put(data, key, upToken);
            logger.info(res.toString());
            if(res.isOK()){
                return res.jsonToObject(classOfT);
            }
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时简单状态信息
            logger.error(r.toString());
            try {
                // 响应的文本信息
                logger.error(r.bodyString());
            } catch (QiniuException e1) {
            }

        }
        return null;
    }

    public String getDomain(){
        return "http://"+bucket+".u.qiniudn.com/";
    }

    public Auth getAuth(){
        return auth;
    }

    public UploadManager getUploadManager() {
        return uploadManager;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
