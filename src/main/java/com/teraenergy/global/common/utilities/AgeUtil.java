package com.teraenergy.global.common.utilities;

import com.teraenergy.global.common.constants.AgeConstant;
import com.teraenergy.global.common.constants.AreaCodeConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class AgeUtil {

    public static String getAgeName(String ageCd) {
        String name = "";
        if (AgeConstant.TOTAL.contains(ageCd)) name = "계";
        if (AgeConstant.TEENAGE.contains(ageCd)) name = "10대";
        if (AgeConstant.TWENTIES.contains(ageCd)) name = "20대";
        if (AgeConstant.THIRTIES.contains(ageCd)) name = "30대";
        if (AgeConstant.FORTIES.contains(ageCd)) name = "40대";
        if (AgeConstant.FIFTIES.contains(ageCd)) name = "50대";
        if (AgeConstant.SIXTIES.contains(ageCd)) name = "60대";
        if (AgeConstant.SEVENTIES.contains(ageCd)) name = "70대";
        if (AgeConstant.EIGHTIES.contains(ageCd)) name = "80대";
        if (AgeConstant.NINETIES.contains(ageCd)) name = "90대";
        if (AgeConstant.HUNDRED_OVER.contains(ageCd)) name = "100세이상";
        return name;
    }
}
