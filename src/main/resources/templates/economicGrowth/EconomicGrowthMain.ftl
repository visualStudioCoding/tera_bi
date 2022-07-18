<#ftl encoding="utf-8"/>
<#include "*/common/navigation.ftl"/>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    한국은행 기준금리 및 환율
                </div>
                <form name="getMonthlyExchangeRate_opt">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" aria-label="Default select example" disabled>
                            <option selected>해당 API는 당일 데이터 호출만 가능합니다. </option>
                        </select>
                        <input type="hidden" name="apiType" value="KeyStatisticList">
                    </div>
                </form>
                <button type="button" id="MonthlyExchangeRate" class="btn btn-outline-secondary activator" onclick="getMonthlyExchangeRate()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    소비자/근원/생활 물가상승률
                </div>
                <form name="getInflationRate_opt">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe_Y" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 1966..1999 as i>
                                <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                            </#list>
                            <#list 2000..2022 as j>
                                <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                            </#list>
                        </select>
                        <br>
                        <select class="form-select" name="prdDe_M" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 1..12 as i>
                                <option value=${i}>${i}월</option>
                            </#list>

                        </select>
                        <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T03+", "0+1+3+", "M", "2", "101", "DT_1J20042"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                        </#list>
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="getInflationRate()">실행</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    경제활동별 GDP 및 GNI
                </div>
                <form name="gdpAndgni_opt">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 1953..1999 as i>
                                <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                            </#list>
                            <#list 2000..2021 as j>
                                <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                            </#list>
                        </select>
                        <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["13103134593999+", "13102134593ACC_ITEM.1400+13102134593ACC_ITEM.1600+13102134593ACC_ITEM.1800+", "Y", "2", "301", "DT_200Y006"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                        </#list>
                    </div>
                </form>
                <button type="button" id="GdpAndGni" class="btn btn-outline-secondary activator" onclick="getGdpAndGni()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    국가채무현황
                </div>
                <form name="stateDebt_opt">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 1997..1999 as i>
                                <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                            </#list>
                            <#list 2000..2020 as j>
                                <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                            </#list>
                        </select>
                        <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T01+", "01", "Y", "2", "102", "DT_102N_A001"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                        </#list>
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="getStateDebt()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    경제성장률(시도)
                </div>
                <form name="growthRate_opt">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 1986..1999 as i>
                                <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                            </#list>
                            <#list 2000..2020 as j>
                                <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                            </#list>
                        </select>
                        <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T10+", "ALL", "Y", "2", "101", "DT_1YL20571"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                        </#list>
                </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="getGrowthRate()">실행</button>
            </div>
        </div>
    </div>
</div>
</body>
<#include "*/common/footer.ftl"/>
<script src="/js/bi/economicGrowth/economicGrowth.js"></script>