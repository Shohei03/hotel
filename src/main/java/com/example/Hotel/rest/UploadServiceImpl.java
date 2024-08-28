package com.example.Hotel.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadServiceImpl  implements UploadService {
    /**
     * ファイルをInputStreamに変換し、出力用ファイルオブジェクトを作成してOutputStreamに変換し
     * InputStreamの内容を読み込んでOutputStreamに出力する。
     *
     * @param file アップロードしたファイル
     *             return pathを返す（pathはDBに登録用）
     */
    @Override
    public String fileUpload(MultipartFile file) {
        // ファイル名（別名をつけても良い）
        String filename = file.getOriginalFilename();
        // 保存先パス
        //String filePath = "./src/main/resources/static/" + filename;
        // 保存先ディレクトリパスを絶対パスで指定
        String directoryPath = "C:\\hotel_work\\";

        String filePath = directoryPath + filename;

        try {
            // 保存先ディレクトリが存在しない場合は作成
            Path path = Paths.get(directoryPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // ファイルをバイナリデータとして取得
            byte[] content = file.getBytes();
            // 保存
            Files.write(Paths.get(filePath), content);

        } catch (Exception e) {
            // エラー時
            e.printStackTrace();
        } finally {

        }
        return filename;
    }
}

//        // 出力先パス+ファイル名を作成
//        String path = "./src/main/resources/static/" + file.getOriginalFilename();
//
//        // ファイルオブジェクトを作成
//        File convFile = new File(path);
//
//        try (InputStream inputStream = file.getInputStream();
//             OutputStream outputStream = new FileOutputStream(convFile)) {
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            // InputStreamの内容を読み込んでOutputStreamに出力する。
//            while ((read = inputStream.read(bytes)) != -1) {
//                outputStream.write(bytes, 0, read);
//            }
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }

//}
