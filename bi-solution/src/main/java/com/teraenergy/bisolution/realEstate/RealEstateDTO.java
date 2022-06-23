package com.teraenergy.bisolution.realEstate;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("realEstateDTO")
@Getter
@Setter
public class RealEstateDTO {

    private int idx;
    private String aptNm;
    private String layer;
    private String payment;
    private String builtYear;
    private String tradeYear;
    private String tradeMonth;
    private String sigunguCd;
    private String eupmyundongCd;
    private String dong;
    private String roadNm;
    private String landNo;
    private String landArea;

}
