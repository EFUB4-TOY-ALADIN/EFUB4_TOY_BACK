package com.efubtoy.team1.global.AmazonS3;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final AmazonS3 amazonS3Client;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /* 이미지 S3에 업로드 */
    public List<FileDto> fileUpload(List<MultipartFile> multipartFiles) {
        List<FileDto> fileDtos = new ArrayList<>();
        if(multipartFiles != null && !multipartFiles.isEmpty()){
            if(multipartFiles.size() > 4) {
                throw new CustomException(ErrorCode.TOO_MANY_FILES);
            }
            for (MultipartFile file : multipartFiles) {
                String fileName = makeFileName(file); // 파일 이름 생성
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(file.getSize());
                objectMetadata.setContentType(file.getContentType());

                try (InputStream inputStream = file.getInputStream()) {
                    amazonS3Client.putObject(new PutObjectRequest(bucket + "/review/image", fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                    String imgUrl = amazonS3Client.getUrl(bucket + "/review/image", fileName).toString();
                    fileDtos.add(new FileDto(fileName , imgUrl));
                } catch (IOException e) {
                    throw new CustomException(ErrorCode.IMAGE_UPLOAD_ERROR, "image upload fail");
                }

            }

        }
        return fileDtos;
    }

    /* 파일명 생성 */
    private String makeFileName(MultipartFile multipartFile){
        String originalName = multipartFile.getOriginalFilename();
        final String ext = originalName.substring(originalName.lastIndexOf("."));
        final String fileName = UUID.randomUUID().toString() + ext;
        return fileName;
    }

    /* S3 이미지 삭제 */
    public void deleteImage(String ImageUrl , String fileName) {
        String key = "review/image/" + fileName;
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket , key));
    }
}
