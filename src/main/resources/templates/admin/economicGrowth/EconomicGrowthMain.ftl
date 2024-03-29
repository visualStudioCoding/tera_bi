<#include "*/common/admin/header.ftl">


<#assign nowDate = .now/>

<body>
<#include "*/common/admin/navigation.ftl"/>

<div class="contents">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>한국은행 기준금리</h5>
                </div>
                <hr>
                <form name="getBaseRate_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>시작 년도 선택</span>
                            </div>
                            <div class="row">
                                <input type="date" name="startDt">
                            </div>
                            <div class="row">
                                <span>끝 년도 선택</span>
                            </div>
                            <div class="row">
                                <input type="date" name="endDt">
                            </div>
                        </div>
                        <#assign name_list = ["serviceNm", "resultType", "tableCode", "cycle","itmCode"]>
                        <#assign val_list = ["StatisticSearch", "json", "722Y001", "D", "0101000"]>
                        <#list 0..4 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                        </#list>
                    </div>
                </form>
                <hr>
                <button type="button" id="MonthlyExchangeRate" class="btn btn-primary w-100"
                        onclick="getBaseRate()">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>한국은행 환율</h5>
                </div>
                <hr>
                <form name="getExchangeRate_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>시작 년도 선택</span>
                            </div>
                            <div class="row">
                                <input type="date" name="startDt">
                            </div>
                            <div class="row">
                                <span>끝 년도 선택</span>
                            </div>
                            <div class="row">
                                <input type="date" name="endDt">
                            </div>
                        </div>
                        <#assign name_list = ["serviceNm", "resultType", "tableCode", "cycle","itmCode"]>
                        <#assign val_list = ["StatisticSearch", "json", "731Y001", "D", "0000001"]>
                        <#list 0..4 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                        </#list>
                    </div>
                </form>
                <hr>
                <button type="button" id="MonthlyExchangeRate" class="btn btn-primary w-100"
                        onclick="getExchangeRate()">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>소비자/근원/생활 물가상승률</h5>
                </div>
                <hr>
                <form name="getInflationRate_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe_Y" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 1966..1999 as i>
                                        <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                                    </#list>
                                    <#list 2000..2022 as j>
                                        <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="row">
                                <span>월 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe_M" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 1..12 as i>
                                        <option value=${i}>${i}월</option>
                                    </#list>
                                </select>
                            </div>
                            <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["T03+", "0+1+3+", "M", "2", "101", "DT_1J20042"]>
                            <#list 0..5 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="getInflationRate()">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>GDP</h5>
                </div>
                <hr>
                <form name="getGDP_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="startDt" aria-label="Default select example">
                                    <option selected>조회를 시작할 년도를 선택하세요</option>
                                    <#list 1953..1999 as i>
                                        <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                                    </#list>
                                    <#list 2000..2021 as j>
                                        <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="row">
                                <span>월 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="endDt" aria-label="Default select example">
                                    <option selected>조회할 마지막 년도를 선택하세요</option>
                                    <#list 1953..1999 as i>
                                        <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                                    </#list>
                                    <#list 2000..2021 as j>
                                        <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#assign name_list = ["serviceNm", "resultType", "tableCode", "cycle","itmCode"]>
                            <#assign val_list = ["StatisticSearch", "json", "200Y009", "A", "10601"]>
                            <#list 0..4 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="getGDP()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>국제 주요국 물가상승률</h5>
                </div>
                <hr>
                <form name="priceIncreaseInternational_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>시작 년도 선택</span>
                            </div>
                            <div class="row">
                                <input type="date" name="startDt">
                            </div>
                            <div class="row">
                                <span>끝 년도 선택</span>
                            </div>
                            <div class="row">
                                <input type="date" name="endDt">
                            </div>
                            <#assign name_list = ["serviceNm", "resultType", "tableCode", "cycle","itmCode"]>
                            <#assign val_list = ["StatisticSearch", "json", "902Y008", "M", "KR"]>
                            <#list 0..4 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100"
                        onclick="getPriceIncreaseInternational()">실행
                </button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>경제활동별 GDI 및 GNI</h5>
                </div>
                <hr>
                <form name="gdpAndgni_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 1953..1999 as i>
                                        <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                                    </#list>
                                    <#list 2000..2021 as j>
                                        <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["13103134593999+", "13102134593ACC_ITEM.1400+13102134593ACC_ITEM.1600+13102134593ACC_ITEM.1800+", "Y", "2", "301", "DT_200Y006"]>
                            <#list 0..5 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" id="GdpAndGni" class="btn btn-primary w-100"
                        onclick="getGdpAndGni()">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>국가채무현황</h5>
                </div>
                <hr>
                <form name="stateDebt_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 1997..1999 as i>
                                        <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                                    </#list>
                                    <#list 2000..2021 as j>
                                        <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["T01+", "01", "Y", "2", "102", "DT_102N_A001"]>
                            <#list 0..5 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="getStateDebt()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card" style="height:100%">
                <div class="card-header">
                    <h5>시도별 경제성장률</h5>
                </div>
                <hr>
                <form name="growthRate_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 1986..1999 as i>
                                        <option value=${i?replace(",","")}>${i?replace(",","")}년</option>
                                    </#list>
                                    <#list 2000..2022 as j>
                                        <option value=${j?replace(",","")}>${j?replace(",","")}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["T10+", "ALL", "Y", "2", "101", "DT_1YL20571"]>
                            <#list 0..5 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="getGrowthRate()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card" style="height:100%">
                <div class="card-header">
                    <h5>국제 주요국 경제성장률</h5>
                </div>
                <hr>
                <form name="growthRateInternational_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row"><select class="form-select" name="prdDe"
                                                     aria-label="Default select example" disabled>
                                    <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                                </select>
                            </div>

                            <#assign name_list = ["serviceNm", "resultType", "tableCode", "cycle","itmCode"]>
                            <#assign val_list = ["StatisticSearch", "json", "902Y015", "A", "KOR"]>
                            <#list 0..4 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                            <input type="hidden" name="startDt" value="1950"/>
                            <input type="hidden" name="endDt" value="${nowDate?string("yyyy")}"/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100"
                        onclick="getGrowthRateInternational()">실행
                </button>
            </div>
        </div>
    </div>
</div>

<#include "*/common/admin/footer.ftl"/>
</body>
<script src="/js/admin/economicGrowth/economicGrowth.js"></script>