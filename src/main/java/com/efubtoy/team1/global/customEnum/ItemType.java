package com.efubtoy.team1.global.customEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {
    BOOK("도서"),
    RECORD("음반"),
    GOODS("굿즈");

    private String type;
}
