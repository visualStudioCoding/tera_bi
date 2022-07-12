package com.teraenergy.global.common.utilities;

import com.teraenergy.global.common.constants.AreaCodeConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class AreaNameUtil {
    public static String areaName(String areaCd, String other) {
        if(areaCd.length() <= 5) {areaCd = areaCd.substring(0, 2);}
        if(areaCd.contains(".")) {
            String[] areaCdArray = areaCd.split("\\.");
            areaCd = areaCdArray[1].substring(0, 4);
        }
        return getName(areaCd, other);
    }

    private static String getName(String areaCd, String other) {
        String name = "";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_SEOUL.contains(areaCd) : AreaCodeConstant.SEOUL.contains(areaCd)) name = "서울특별시";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_BUSAN.contains(areaCd) : AreaCodeConstant.BUSAN.contains(areaCd)) name = "부산광역시";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_DAEGU.contains(areaCd) : AreaCodeConstant.DAEGU.contains(areaCd)) name = "대구광역시";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_INCHEON.contains(areaCd) : AreaCodeConstant.INCHEON.contains(areaCd)) name = "인천광역시";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_GWANGJU.contains(areaCd) : AreaCodeConstant.GWANGJU.contains(areaCd)) name = "광주광역시";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_DAEJEON.contains(areaCd) : AreaCodeConstant.DAEJEON.contains(areaCd)) name = "대전광역시";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_ULSAN.contains(areaCd) : AreaCodeConstant.ULSAN.contains(areaCd)) name = "울산광역시";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_SEJONG.contains(areaCd) : AreaCodeConstant.SEJONG.contains(areaCd)) name = "세종특별자치시";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_GYEONGGI.contains(areaCd) : AreaCodeConstant.GYEONGGI.contains(areaCd)) name = "경기도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_GANGWON.contains(areaCd) : AreaCodeConstant.GANGWON.contains(areaCd)) name = "강원도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_CHUNGBUK.contains(areaCd) : AreaCodeConstant.CHUNGBUK.contains(areaCd)) name = "충청북도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_CHUNGNAM.contains(areaCd) : AreaCodeConstant.CHUNGNAM.contains(areaCd)) name = "충청남도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_JEONBUK.contains(areaCd) : AreaCodeConstant.JEONBUK.contains(areaCd)) name = "전라북도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_JEONNAM.contains(areaCd) : AreaCodeConstant.JEONNAM.contains(areaCd)) name = "전라남도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_GYEONGBUK.contains(areaCd) : AreaCodeConstant.GYEONGBUK.contains(areaCd)) name = "경상북도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_GYEONGNAM.contains(areaCd) : AreaCodeConstant.GYEONGNAM.contains(areaCd)) name = "경상남도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_JEJU.contains(areaCd) : AreaCodeConstant.JEJU.contains(areaCd)) name = "제주특별자치도";
        if ("other".equals(other) ? AreaCodeConstant.OTHER_ALL.contains(areaCd) : AreaCodeConstant.ALL.contains(areaCd)) name = "전국";

        return name;
    }

}
