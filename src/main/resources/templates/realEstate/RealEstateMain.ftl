<#include "*/common/navigation.ftl"/>

<#assign populationObjL1 = "00+11+11110+11140+11170+11200+11215+11230+11260+11290+11305+11320+11350+11380+11410+11440+11470+11500+11530+11545+11560+11590+11620+11650+11680+11710+11740+26+26110+26140+26170+26200+26230+26260+26290+26320+26350+26380+26410+26440+26470+26500+26530+26710+27+27110+27140+27170+27200+27230+27260+27290+27710+28+28110+28140+28170+28177+28185+28200+28237+28245+28260+28710+28720+29+29110+29140+29155+29170+29200+30+30110+30140+30170+30200+30230+31+31110+31140+31170+31200+31710+36+36110+41+41105+41110+41111+41113+41115+41117+41130+41131+41133+41135+41150+41170+41171+41173+41190+41195+41197+41199+41210+41220+41250+41270+41271+41273+41280+41281+41285+41287+41290+41310+41360+41370+41390+41410+41430+41450+41460+41461+41463+41465+41480+41500+41550+41570+41590+41610+41630+41650+41670+41730+41800+41820+41830+42+42105+42110+42130+42150+42170+42190+42210+42230+42720+42730+42750+42760+42770+42780+42790+42800+42810+42820+42830+43+43110+43111+43112+43113+43114+43130+43150+43710+43720+43730+43740+43745+43750+43760+43770+43800+44+44130+44131+44133+44150+44180+44200+44210+44230+44250+44270+44710+44730+44760+44770+44790+44800+44810+44825+44830+45+45110+45111+45113+45130+45140+45180+45190+45210+45710+45720+45730+45740+45750+45770+45790+45800+46+46110+46130+46150+46170+46230+46710+46720+46730+46770+46780+46790+46800+46810+46820+46830+46840+46860+46870+46880+46890+46900+46910+47+47110+47111+47113+47130+47150+47170+47190+47210+47230+47250+47280+47290+47720+47730+47750+47760+47770+47820+47830+47840+47850+47900+47920+47930+47940+48+48120+48121+48123+48125+48127+48129+48170+48220+48240+48250+48270+48310+48330+48720+48730+48740+48820+48840+48850+48860+48870+48880+48890+50+50110+50130+" />

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
                        <input type="hidden" name="objL2" value="" />
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnGenderPopulation()">실행</button>
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
                        <input type="hidden" name="objL2" value="" />
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnAptSalesStatus()">실행</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <form id="formPopulationAge" name="formPopulationAge">
                    <div class="card-header">
                        남녀별 연령별 인구구조
                    </div>
                    <div class="card-body">
                        <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                            <#--                        <option selected>데이터를 호출할 년도를 선택하세요</option>-->
                            <#list 2012..2022 as i>
                                <option value=${i?c}>${i?c}년</option>
                            </#list>
                        </select>
                        <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                        <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                        <#--                        <#list 1..12 as i>-->
                        <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                        <#--                        </#list>-->
                        <#--                    </select>-->
                        <#assign name_list = ["itmId", "objL1", "objL2", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["T3+T4+", populationObjL1, "ALL", "M", "2", "101", "DT_1B04005N"]>
                        <#list 0..6 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                        </#list>
                            <#--<input type="hidden" name="objL1" value=${populationObjL1}  />-->
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnPopulationAge();">실행</button>
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
                <form id="formUnsoldHouse" name="formUnsoldHouse">
                    <#--                <input type="hidden" id="prdDe" name="prdDe" value=""/>-->
                    <div class="card-header">미분양주택현황</div>
                    <div class="card-body">
                        <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                            <#--                        <option selected>데이터를 호출할 년도를 선택하세요</option>-->
                            <#list 2007..2022 as i>
                                <option value=${i?c}>${i?c}년</option>
                            </#list>
                        </select>
                        <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                        <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                        <#--                        <#list 1..12 as i>-->
                        <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                        <#--                        </#list>-->
                        <#--                    </select>-->
                        <#assign name_list = ["itmId", "objL1", "objL2", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["13103871087T1+", "ALL", "ALL", "M", "2", "101", "DT_1YL202001E"]>
                        <#list 0..6 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                        </#list>
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnUnsoldHouse()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <form id="formAgeAptSales" name="formAgeAptSales">
                    <#--                <input type="hidden" id="prdDe" name="prdDe" value=""/>-->
                    <div class="card-header">매입자연령대별 아파트 매매거래 현황</div>
                    <div class="card-body">
                        <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                            <#--                        <option selected>데이터를 호출할 년도를 선택하세요</option>-->
                            <#list 2019..2022 as i>
                                <option value=${i?c}>${i?c}년</option>
                            </#list>
                        </select>
                        <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                        <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                        <#--                        <#list 1..12 as i>-->
                        <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                        <#--                        </#list>-->
                        <#--                    </select>-->
                        <#assign name_list = ["itmId", "objL1", "objL2", "prdSe", "loadGubun", "orgId", "tblId"]>
                        <#assign val_list = ["13103130735T1+", "ALL", "13102130735B.00010001+13102130735B.00010002+13102130735B.00010003+13102130735B.00010004+13102130735B.00010005+13102130735B.00010006+13102130735B.00010007+", "M", "2", "408", "DT_408_2006_S0077"]>
                        <#list 0..6 as k>
                            <input type="hidden" name=${name_list[k]} value=${val_list[k]}  />
                        </#list>
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnAgeAptSales()">실행</button>
            </div>
        </div>
    </div>
</div>
<#include "*/common/footer.ftl"/>

<script src="/js/bi/realEstate/realEstate.js"></script>

