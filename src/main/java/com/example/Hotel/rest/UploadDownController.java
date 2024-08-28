package com.example.Hotel.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadDownController {
    @Autowired
    UploadService uploadService;

//    @Autowired
//    DownloadService downloadService;

    /**
     * ファイルアップロードAPI
     * @param file アップロードしたファイル
     */
    @PostMapping(value = "/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        uploadService.fileUpload(file);
    }

//    /**
//     * ファイルダウンロードAPI
//     * @param file アップロードしたファイル
//     */
//    @PostMapping(value = "/downloadFile")
//    public void downloadFile(HttpServletResponse response) {
//        downloadService.fileDownload(response);
//    }

}
