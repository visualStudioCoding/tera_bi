package com.teraenergy.bisolution.admin.realestate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateDTO {

    private Integer idx;

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
