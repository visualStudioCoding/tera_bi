package com.teraenergy.global.common.utilities;

import com.teraenergy.global.common.constants.AreaCodeConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class AreaNameUtil {
    public static String areaName(String areaCd) {
        if(areaCd.length() <= 5) {areaCd = areaCd.substring(0, 2);}
        if(areaCd.contains(".")) {
            System.out.println(areaCd.contains("."));
            String[] areaCdArray = areaCd.split("\\.");
            areaCd = areaCdArray[1].substring(0, 4);
        }
        return getName(areaCd);
    }

    private static String getName(String areaCd) {
        String name = "";

        if (AreaCodeConstant.SEOUL.contains(areaCd)) name = "서울특별시";
        if (AreaCodeConstant.BUSAN.contains(areaCd)) name = "부산광역시";
        if (AreaCodeConstant.DAEGU.contains(areaCd)) name = "대구광역시";
        if (AreaCodeConstant.INCHEON.contains(areaCd)) name = "인천광역시";
        if (AreaCodeConstant.GWANGJU.contains(areaCd)) name = "광주광역시";
        if (AreaCodeConstant.DAEJEON.contains(areaCd)) name = "대전광역시";
        if (AreaCodeConstant.ULSAN.contains(areaCd)) name = "울산광역시";
        if (AreaCodeConstant.SEJONG.contains(areaCd)) name = "세종특별자치시";
        if (AreaCodeConstant.GYEONGGI.contains(areaCd)) name = "경기도";
        if (AreaCodeConstant.GANGWON.contains(areaCd)) name = "강원도";
        if (AreaCodeConstant.CHUNGBUK.contains(areaCd)) name = "충청북도";
        if (AreaCodeConstant.CHUNGNAM.contains(areaCd)) name = "충청남도";
        if (AreaCodeConstant.JEONBUK.contains(areaCd)) name = "전라북도";
        if (AreaCodeConstant.JEONNAM.contains(areaCd)) name = "전라남도";
        if (AreaCodeConstant.GYEONGBUK.contains(areaCd)) name = "경상북도";
        if (AreaCodeConstant.GYEONGNAM.contains(areaCd)) name = "경상남도";
        if (AreaCodeConstant.JEJU.contains(areaCd)) name = "제주특별자치도";
        if (AreaCodeConstant.ALL.contains(areaCd)) name = "전국";

        return name;
    }

}
