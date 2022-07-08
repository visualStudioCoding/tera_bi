package com.teraenergy.global.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class AreaCodeConstant {

    public static final List<String> ALL = List.of("00", "0001");
    public static final List<String> SEOUL = List.of("11", "0002");
    public static final List<String> BUSAN = List.of("26", "0003");
    public static final List<String> DAEGU = List.of("27", "0004");
    public static final List<String> INCHEON = List.of("28", "0005");
    public static final List<String> GWANGJU = List.of("29", "0006");
    public static final List<String> DAEJEON = List.of("30", "0007");
    public static final List<String> ULSAN = List.of("31", "0008");
    public static final List<String> SEJONG = List.of("36", "0009");
    public static final List<String> GYEONGGI = List.of("41", "0010");
    public static final List<String> GANGWON = List.of("42", "0011");
    public static final List<String> CHUNGBUK = List.of("43", "0012");
    public static final List<String> CHUNGNAM = List.of("44", "0013");
    public static final List<String> JEONBUK = List.of("45", "0014");
    public static final List<String> JEONNAM = List.of("46", "0015");
    public static final List<String> GYEONGBUK = List.of("47", "0016");
    public static final List<String> GYEONGNAM = List.of("48", "0017");
    public static final List<String> JEJU = List.of("50", "0018");
}
