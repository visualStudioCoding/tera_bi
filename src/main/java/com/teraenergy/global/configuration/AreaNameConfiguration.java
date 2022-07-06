package com.teraenergy.global.configuration;

public class AreaNameConfiguration {

    public static String areaName(String areaCd) {
        String name = "";
        if(areaCd.length() <= 5){
            switch (areaCd.substring(0, 2)) {
                case "11": name = "서울특별시";
                    break;
                case "26": name = "부산광역시";
                    break;
                case "27": name = "대구광역시";
                    break;
                case "28": name = "인천광역시";
                    break;
                case "29": name = "광주광역시";
                    break;
                case "30": name = "대전광역시";
                    break;
                case "31": name = "울산광역시";
                    break;
                case "36": name = "세종특별자치시";
                    break;
                case "41": name = "경기도";
                    break;
                case "42": name = "강원도";
                    break;
                case "43": name = "충청북도";
                    break;
                case "44": name = "충청남도";
                    break;
                case "45": name = "전라북도";
                    break;
                case "46": name = "전라남도";
                    break;
                case "47": name = "경상북도";
                    break;
                case "48": name = "경상남도";
                    break;
                case "50": name = "제주특별자치도";
                    break;
                default: name = "전국";
                    break;
            }
        }
        return name;
    }
}
