package com.teraenergy.bisolution.admin.economicGrowth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("economicGrowthDTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EconomicGrowthDTO {

    private String yr_dt;
    private String unit;
    private Float var;
}
