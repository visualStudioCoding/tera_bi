<#include "*/common/admin/navigation.ftl"/>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    1인당 개인소득(시도)
                </div>
                <form name="capitalPersonal_opt">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 2000..2020 as j>
                                <option value=${j?c}>${j?c}년</option>
                            </#list>
                        </select>
                        <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T3+", "ALL", "Y", "2", "101", "INH_1C86_04"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                        </#list>
                        <input type="hidden" name="objL2" value="" />
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="getCapitaPersonal()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    1인당 국민총소득
                </div>
                <form name="getGrossNationalIncome_opt">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example" disabled>
                            <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                        </select>
                            <input type="hidden" name="statsCode" value="422101"  />
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="getGrossNationalIncome()">실행</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    소득분배지표
                </div>
                <form name="getIncomeDistributionIndex_opt">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example">
                            <option selected>데이터를 호출할 년도를 선택하세요</option>
                            <#list 2011..2020 as j>
                                <option value=${j?c}>${j?c}년</option>
                            </#list>
                        </select>
                        <#assign name_list = ["itmId", "objL2", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T002+", "10+", "Y", "2", "101", "DT_1HDLF05"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                        </#list>
                        <input type="hidden" name="objL1" value="" />
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="getIncomeDistributionIndex()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    최저임금 일반현황
                </div>
                <form name="formMinPay">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example" disabled>
                            <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                        </select>
                        <input type="hidden" name="statsCode" value="149201" />
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnMinPay()">실행</button>
            </div>
        </div>
    </div>
</div>
<#include "*/common/admin/footer.ftl"/>
<script src="/js/admin/standardOfLiving/standardOfLiving.js"></script>

