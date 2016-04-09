package com.teamlinking.single.uitl;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * Created by admin on 16/3/16.
 */
public class MultipartFileUtil {

    public static MultipartFile getMultipartFile(HttpServletRequest request,String name){
        try {
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                if (name.equals(part.getName())) {
                    String disposition = part.getHeader("content-disposition");
                    String filename = extractFilename(disposition);
                    if (filename == null) {
                        filename = extractFilenameWithCharset(disposition);
                    }
                    if (filename != null) {
                        return new StandardMultipartFile(part, filename);
                    }
                }
            }
        }catch (Exception e){
        }
        return null;
    }

    public static String baseFileName(String filename){
        return SecurityUtil.md5(filename)+getFileExt(filename);
    }

    public static String getFileExt(String filename){
        if (filename == null) {
            return "";
        }
        int startIndex = filename.lastIndexOf(".");
        if (startIndex == -1) {
            return "";
        }
        return filename.substring(startIndex);
    }

    private static String extractFilename(String contentDisposition) {
        return extractFilename(contentDisposition, "filename=");
    }

    private static String extractFilename(String contentDisposition, String key) {
        if (contentDisposition == null) {
            return null;
        }
        int startIndex = contentDisposition.indexOf(key);
        if (startIndex == -1) {
            return null;
        }
        String filename = contentDisposition.substring(startIndex + key.length());
        if (filename.startsWith("\"")) {
            int endIndex = filename.indexOf("\"", 1);
            if (endIndex != -1) {
                return filename.substring(1, endIndex);
            }
        }
        else {
            int endIndex = filename.indexOf(";");
            if (endIndex != -1) {
                return filename.substring(0, endIndex);
            }
        }
        return filename;
    }

    private static String extractFilenameWithCharset(String contentDisposition) {
        String filename = extractFilename(contentDisposition, "filename*=");
        if (filename == null) {
            return null;
        }
        int index = filename.indexOf("'");
        if (index != -1) {
            Charset charset = null;
            try {
                charset = Charset.forName(filename.substring(0, index));
            }
            catch (IllegalArgumentException ex) {
                // ignore
            }
            filename = filename.substring(index + 1);
            // Skip language information..
            index = filename.indexOf("'");
            if (index != -1) {
                filename = filename.substring(index + 1);
            }
            if (charset != null) {
                filename = new String(filename.getBytes(Charset.forName("us-ascii")), charset);
            }
        }
        return filename;
    }

    private static class StandardMultipartFile implements MultipartFile, Serializable {

        private final Part part;

        private final String filename;

        public StandardMultipartFile(Part part, String filename) {
            this.part = part;
            this.filename = filename;
        }

        @Override
        public String getName() {
            return this.part.getName();
        }

        @Override
        public String getOriginalFilename() {
            return this.filename;
        }

        @Override
        public String getContentType() {
            return this.part.getContentType();
        }

        @Override
        public boolean isEmpty() {
            return (this.part.getSize() == 0);
        }

        @Override
        public long getSize() {
            return this.part.getSize();
        }

        @Override
        public byte[] getBytes() throws IOException {
            return FileCopyUtils.copyToByteArray(this.part.getInputStream());
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return this.part.getInputStream();
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            this.part.write(dest.getPath());
        }
    }

    public static void main(String[] args){
        System.out.println(getFileExt("文件1.es.doc"));
        System.out.println(getFileExt("文件2.doc"));
        System.out.println(getFileExt("文件3."));
        System.out.println(getFileExt("文件4"));
    }
}
