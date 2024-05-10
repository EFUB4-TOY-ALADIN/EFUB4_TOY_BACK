package com.efubtoy.team1.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {
    GOOD("상"), FAIR("중"), POOR("하");

    private String state;

}
