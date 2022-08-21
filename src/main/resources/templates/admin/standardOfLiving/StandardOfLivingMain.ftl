<#include "*/common/admin/header.ftl">

<body>
<#include "*/common/admin/navigation.ftl"/>
<div class="contents">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>1인당 개인소득(시도)</h5>
                </div>
                <hr>
                <form name="capitalPersonal_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 2000..2020 as j>
                                        <option value=${j?c}>${j?c}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["T3+", "ALL", "Y", "2", "101", "INH_1C86_04"]>
                            <#list 0..5 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                            <input type="hidden" name="objL2" value=""/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="getCapitaPersonal()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>1인당 국민총소득</h5>
                </div>
                <hr>
                <form name="getGrossNationalIncome_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" aria-label="Default select example" disabled>
                                    <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                                </select>
                            </div>
                            <input type="hidden" name="statsCode" value="422101"/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="getGrossNationalIncome()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>소득분배지표</h5>
                </div>
                <hr>
                <form name="getIncomeDistributionIndex_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                        <span>년도 선택</span>
                            </div>
                            <div class="row">
                        <select class="form-select" name="prdDe" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 2011..2020 as j>
                                <option value=${j?c}>${j?c}년</option>
                            </#list>
                        </select>
                            </div>
                        <#assign name_list = ["itmId", "objL2", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T002+", "10+", "Y", "2", "101", "DT_1HDLF05"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                        </#list>
                        <input type="hidden" name="objL1" value=""/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="getIncomeDistributionIndex()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>최저임금 일반현황</h5>
                </div>
                <hr>
                <form name="formMinPay">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                        <span>년도 선택</span>
                            </div>
                            <div class="row">
                        <select class="form-select" name="prdDe" aria-label="Default select example" disabled>
                            <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                        </select>
                            </div>
                        <input type="hidden" name="statsCode" value="149201"/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnMinPay()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>임금상승률</h5>
                </div>
                <hr>
                <form name="formIncomeIncreaseRate_opt">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                        <span>년도 선택</span>
                            </div>
                            <div class="row">
                        <select class="form-select" name="prdDe" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 2011..2021 as j>
                                <option value=${j?c}>${j?c}년</option>
                            </#list>
                        </select>
                            </div>
                        <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T001+T002+", "00+11+21+22+23+24+25+26+29+31+32+33+34+35+36+37+38+39+", "Y", "2", "101", "DT_1YL15006"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                        </#list>
                        <input type="hidden" name="objL2" value=""/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="getIncomeIncreaseRate()">실행</button>
            </div>
        </div>
    </div>
</div>
<#include "*/common/admin/footer.ftl"/>
</body>
<script src="/js/admin/standardOfLiving/standardOfLiving.js"></script>

