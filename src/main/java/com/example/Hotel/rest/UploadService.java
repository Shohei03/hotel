package com.example.Hotel.rest;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /**
     * ファイルアップロードサービス
     *
     * @param file アップロードしたファイル
     */
    public String fileUpload(MultipartFile file);
}
