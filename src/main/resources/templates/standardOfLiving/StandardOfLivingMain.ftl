<#include "*/common/navigation.ftl"/>
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
                <form name="getGrossNationalIncome">
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
                <div class="card-body">
                    <span>년도 선택</span>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    최저임금 일반현황
                </div>
                <div class="card-body">
                    <span>년도 선택</span>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
    </div>
</div>
<#include "*/common/footer.ftl"/>
<script src="/js/bi/standardOfLiving/standardOfLiving.js"></script>

