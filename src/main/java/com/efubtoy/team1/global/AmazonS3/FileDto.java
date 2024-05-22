package com.efubtoy.team1.global.AmazonS3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileDto {

    public String getFileName() {
        return fileName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    private String fileName;
    private String imgUrl;



}
