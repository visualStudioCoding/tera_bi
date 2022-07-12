<#include "*/common/navigation.ftl"/>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <form id="formGenderPopulation" name="formGenderPopulation">
                    <#--                <input type="hidden" id="prdDe" name="prdDe" value=""/>-->
                    <div class="card-header">행정구역별, 성별 인구 수</div>
                    <div class="card-body">
                        <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                            <#--                        <option selected>데이터를 호출할 년도를 선택하세요</option>-->
                            <#list 2011..2022 as i>
                                <option value=${i?c}>${i?c}년</option>
                            </#list>
                        </select>
                        <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                        <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                        <#--                        <#list 1..12 as i>-->
                        <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                        <#--                        </#list>-->
                        <#--                    </select>-->
                        <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T21+T22+", "ALL", "M", "2", "101", "DT_1B040A3"]>
                        <#list 0..5 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                        </#list>
                    </div>
                    <button type="button" class="btn btn-outline-secondary activator" onclick="fnGenderPopulation()">실행</button>
                </form>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <form id="formAptSalesStatus" name="formAptSalesStatus">
                <div class="card-header">
                    행정구역별 아파트 매매거래 현황
                </div>
                <div class="card-body">
                    <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                        <#--                        <option selected>데이터를 호출할 년도를 선택하세요</option>-->
                        <#list 2006..2022 as i>
                            <option value=${i?c}>${i?c}년</option>
                        </#list>
                    </select>
                    <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                    <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                    <#--                        <#list 1..12 as i>-->
                    <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                    <#--                        </#list>-->
                    <#--                    </select>-->
                    <#assign name_list = ["itmId", "objL1", "prdSe", "loadGubun", "orgId", "tblId"]>
                    <#assign val_list = ["13103114448T1+", "ALL", "M", "2", "408", "DT_408_2006_S0064"]>
                    <#list 0..5 as k>
                        <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                    </#list>
                </div>
                    <button type="button" class="btn btn-outline-secondary activator" onclick="fnAptSalesStatus()">실행</button>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    남녀별 연령별 인구구조
                </div>
                <div class="card-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Default checkbox
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Checked checkbox
                        </label>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    지역내총생산(GRDP)
                </div>
                <div class="card-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Default checkbox
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Checked checkbox
                        </label>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    미분양주택현황
                </div>
                <div class="card-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Default checkbox
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Checked checkbox
                        </label>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    매입자연령대별 아파트 매매거래 현황
                </div>
                <div class="card-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Default checkbox
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Checked checkbox
                        </label>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
    </div>
</div>
<#include "*/common/footer.ftl"/>

<script src="/js/bi/realEstate/realEstate.js"></script>

