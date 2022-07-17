package com.teraenergy.global.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class AgeConstant {
    public static final List<String> TOTAL = List.of("0");
    public static final List<String> TEENAGE_LESS = List.of("5", "10");
    public static final List<String> TEENAGE = List.of("15", "20");
    public static final List<String> TWENTIES = List.of("25", "30");
    public static final List<String> THIRTIES = List.of("35", "40");
    public static final List<String> FORTIES = List.of("45", "50");
    public static final List<String> FIFTIES = List.of("55", "60");
    public static final List<String> SIXTIES = List.of("65", "70");
    public static final List<String> SEVENTIES = List.of("75", "80");
    public static final List<String> EIGHTIES = List.of("85", "90");
    public static final List<String> NINETIES = List.of("95", "100");
    public static final List<String> HUNDRED_MORE = List.of("105");
}
