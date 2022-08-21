<#include "*/common/admin/header.ftl">

<body>
<#include "*/common/admin/navigation.ftl"/>

<#assign populationObjL1 = "00+11+11110+11140+11170+11200+11215+11230+11260+11290+11305+11320+11350+11380+11410+11440+11470+11500+11530+11545+11560+11590+11620+11650+11680+11710+11740+26+26110+26140+26170+26200+26230+26260+26290+26320+26350+26380+26410+26440+26470+26500+26530+26710+27+27110+27140+27170+27200+27230+27260+27290+27710+28+28110+28140+28170+28177+28185+28200+28237+28245+28260+28710+28720+29+29110+29140+29155+29170+29200+30+30110+30140+30170+30200+30230+31+31110+31140+31170+31200+31710+36+36110+41+41105+41110+41111+41113+41115+41117+41130+41131+41133+41135+41150+41170+41171+41173+41190+41195+41197+41199+41210+41220+41250+41270+41271+41273+41280+41281+41285+41287+41290+41310+41360+41370+41390+41410+41430+41450+41460+41461+41463+41465+41480+41500+41550+41570+41590+41610+41630+41650+41670+41730+41800+41820+41830+42+42105+42110+42130+42150+42170+42190+42210+42230+42720+42730+42750+42760+42770+42780+42790+42800+42810+42820+42830+43+43110+43111+43112+43113+43114+43130+43150+43710+43720+43730+43740+43745+43750+43760+43770+43800+44+44130+44131+44133+44150+44180+44200+44210+44230+44250+44270+44710+44730+44760+44770+44790+44800+44810+44825+44830+45+45110+45111+45113+45130+45140+45180+45190+45210+45710+45720+45730+45740+45750+45770+45790+45800+46+46110+46130+46150+46170+46230+46710+46720+46730+46770+46780+46790+46800+46810+46820+46830+46840+46860+46870+46880+46890+46900+46910+47+47110+47111+47113+47130+47150+47170+47190+47210+47230+47250+47280+47290+47720+47730+47750+47760+47770+47820+47830+47840+47850+47900+47920+47930+47940+48+48120+48121+48123+48125+48127+48129+48170+48220+48240+48250+48270+48310+48330+48720+48730+48740+48820+48840+48850+48860+48870+48880+48890+50+50110+50130+" />

<div class="contents">
    <div class="row">
        <div class="col">
            <div class="card">
                <form id="formGenderPopulation" name="formGenderPopulation">
                    <#--                <input type="hidden" id="prdDe" name="prdDe" value=""/>-->
                    <div class="card-header">
                        <h5>행정구역별, 성별 인구 수</h5>
                        <hr>
                    </div>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 2011..2022 as i>
                                        <option value=${i?c}>${i?c}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                            <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                            <#--                        <#list 1..12 as i>-->
                            <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                            <#--                        </#list>-->
                            <#--                    </select>-->
                            <#assign name_list = ["itmId", "objL1", "objL2", "objL3","prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["T21+T22+", "ALL", "", "", "M", "2", "101", "DT_1B040A3"]>
                            <#list 0..7 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                            <input type="hidden" name="objL2" value=""/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnGenderPopulation()">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <form id="formAptSalesStatus" name="formAptSalesStatus">
                    <div class="card-header">
                        <h5>행정구역별 아파트 매매거래 현황</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 2006..2022 as i>
                                        <option value=${i?c}>${i?c}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                            <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                            <#--                        <#list 1..12 as i>-->
                            <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                            <#--                        </#list>-->
                            <#--                    </select>-->
                            <#assign name_list = ["itmId", "objL1", "objL2", "objL3","prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["13103114448T1+", "ALL", "", "", "M", "2", "408", "DT_408_2006_S0064"]>
                            <#list 0..7 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                            <input type="hidden" name="objL2" value=""/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnAptSalesStatus()">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <form id="formPopulationAge" name="formPopulationAge">
                    <div class="card-header">
                        <h5>남녀별 연령별 인구구조</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 2012..2022 as i>
                                        <option value=${i?c}>${i?c}년</option>
                                    </#list>
                                </select></div>
                            <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                            <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                            <#--                        <#list 1..12 as i>-->
                            <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                            <#--                        </#list>-->
                            <#--                    </select>-->
                            <#assign name_list = ["itmId", "objL1", "objL2", "objL3", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["T3+T4+", populationObjL1, "ALL",  "", "M", "2", "101", "DT_1B04005N"]>
                            <#list 0..7 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                            <#--<input type="hidden" name="objL1" value=${populationObjL1}  />-->
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnPopulationAge();">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <form id="formGrp" name="formGrp">
                    <div class="card-header">
                        <h5>지역내총생산(GRP)</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 1985..2020 as i>
                                        <option value=${i?c}>${i?c}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#assign name_list = ["itmId", "objL1", "objL2", "objL3", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["T1+", "ALL", "Z10", "", "Y", "2", "101", "DT_1C81"]>
                            <#list 0..7 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                            <#--<input type="hidden" name="objL1" value=${populationObjL1}  />-->
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnGrp();">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <form id="formUnsoldHouse" name="formUnsoldHouse">
                    <#--                <input type="hidden" id="prdDe" name="prdDe" value=""/>-->
                    <div class="card-header">
                        <h5>미분양주택현황</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 2007..2022 as i>
                                        <option value=${i?c}>${i?c}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                            <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                            <#--                        <#list 1..12 as i>-->
                            <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                            <#--                        </#list>-->
                            <#--                    </select>-->
                            <#assign name_list = ["itmId", "objL1", "objL2", "objL3", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["13103871087T1+", "ALL", "ALL", "", "M", "2", "101", "DT_1YL202001E"]>
                            <#list 0..7 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnUnsoldHouse()">실행</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <form id="formAgeAptSales" name="formAgeAptSales">
                    <#--                <input type="hidden" id="prdDe" name="prdDe" value=""/>-->
                    <div class="card-header">
                        <h5>매입자연령대별 아파트 매매거래 현황</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 2019..2022 as i>
                                        <option value=${i?c}>${i?c}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                            <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                            <#--                        <#list 1..12 as i>-->
                            <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                            <#--                        </#list>-->
                            <#--                    </select>-->
                            <#assign name_list = ["itmId", "objL1", "objL2", "objL3", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["13103130735T1+", "ALL", "13102130735B.00010001+13102130735B.00010002+13102130735B.00010003+13102130735B.00010004+13102130735B.00010005+13102130735B.00010006+13102130735B.00010007+", "", "M", "2", "408", "DT_408_2006_S0077"]>
                            <#list 0..7 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnAgeAptSales()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <form id="formApartmentOwner" name="formApartmentOwner">
                    <#--                <input type="hidden" id="prdDe" name="prdDe" value=""/>-->
                    <div class="card-header">
                        <h5>성별, 연령대별 아파트 소유자 현황</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" id="prdDe" aria-label="Default select example">
                                    <option selected>데이터를 호출할 년도를 선택하세요</option>
                                    <#list 2012..2022 as i>
                                        <option value=${i?c}>${i?c}년</option>
                                    </#list>
                                </select>
                            </div>
                            <#--                    <select class="form-select" name="genderPrdDeMonth" id="genderPrdDeMonth" aria-label="Default select example">-->
                            <#--                        <option selected>데이터를 호출할 월을 선택하세요</option>-->
                            <#--                        <#list 1..12 as i>-->
                            <#--                            <option value=${i?left_pad(2,'00')}>${i?left_pad(2,'00')}월</option>-->
                            <#--                        </#list>-->
                            <#--                    </select>-->
                            <#assign name_list = ["itmId", "objL1", "objL2", "objL3", "prdSe", "loadGubun", "orgId", "tblId"]>
                            <#assign val_list = ["T001+", "00+11+11010+11020+11030+11040+11050+11060+11070+11080+11090+11100+11110+11120+11130+11140+11150+11160+11170+11180+11190+11200+11210+11220+11230+11240+11250+21+21010+21020+21030+21040+21050+21060+21070+21080+21090+21100+21110+21120+21130+21140+21150+21310+22+22010+22020+22030+22040+22050+22060+22070+22310+23+23010+23020+23030+23040+23050+23060+23070+23080+23090+23310+23320+24+24010+24020+24030+24040+24050+25+25010+25020+25030+25040+25050+26+26010+26020+26030+26040+26310+29+29010+31+31010+31011+31012+31013+31014+31020+31021+31022+31023+31030+31040+31041+31042+31050+31060+31070+31080+31090+31091+31092+31100+31101+31103+31104+31110+31120+31130+31140+31150+31160+31170+31180+31190+31191+31192+31193+31200+31210+31220+31230+31240+31250+31260+31270+31280+31350+31370+31380+32+32010+32020+32030+32040+32050+32060+32070+32310+32320+32330+32340+32350+32360+32370+32380+32390+32400+32410+33+33020+33030+33040+33041+33042+33043+33044+33320+33330+33340+33350+33360+33370+33380+33390+34+34010+34011+34012+34020+34030+34040+34050+34060+34070+34080+34310+34330+34340+34350+34360+34370+34380+35+35010+35011+35012+35020+35030+35040+35050+35060+35310+35320+35330+35340+35350+35360+35370+35380+36+36010+36020+36030+36040+36060+36310+36320+36330+36350+36360+36370+36380+36390+36400+36410+36420+36430+36440+36450+36460+36470+36480+37+37010+37011+37012+37020+37030+37040+37050+37060+37070+37080+37090+37100+37310+37320+37330+37340+37350+37360+37370+37380+37390+37400+37410+37420+37430+38+38030+38050+38060+38070+38080+38090+38100+38110+38111+38112+38113+38114+38115+38310+38320+38330+38340+38350+38360+38370+38380+38390+38400+39+39010+39020+", "0+1+2+", "000+100+200+300+400+500+600+700+800+", "Y", "2", "101", "DT_1OH0513"]>
                            <#list 0..7 as k>
                                <input type="hidden" name=${name_list[k]} value=${val_list[k]} />
                            </#list>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnApartmentOwner()">실행
                </button>
            </div>
        </div>
    </div>
</div>
<#include "*/common/admin/footer.ftl"/>
</body>

<script src="/js/admin/realEstate/realEstate.js"></script>

