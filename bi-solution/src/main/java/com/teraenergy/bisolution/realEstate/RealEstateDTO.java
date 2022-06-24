package com.teraenergy.bisolution.realEstate;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Alias("realEstateDTO")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")


    @Override
    public String toString() {
        return "{" +
            " idx='" + idx + "'" +
            ", aptNm='" + aptNm + "'" +
            ", layer='" + layer + "'" +
            ", payment='" + payment + "'" +
            ", builtYear='" + builtYear + "'" +
            ", tradeYear='" + tradeYear + "'" +
            ", tradeMonth='" + tradeMonth + "'" +
            ", sigunguCd='" + sigunguCd + "'" +
            ", eupmyundongCd='" + eupmyundongCd + "'" +
            ", dong='" + dong + "'" +
            ", roadNm='" + roadNm + "'" +
            ", landNo='" + landNo + "'" +
            ", landArea='" + landArea + "'" +
            "}";
    }
    
}
