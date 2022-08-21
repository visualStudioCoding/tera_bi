<#include "*/common/admin/header.ftl">

<body>
<div class="wrap-loading display-none">
    <div><img src="/img/loading.gif"/></div>
</div>
<!-- 로딩중이미지 여기까지 -->

<#include "*/common/admin/navigation.ftl"/>
<div class="contents">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>삶에 대한 만족도 (년)</h5>
                </div>
                <hr>
                <div class="card-body">
                    <div class="col">
                        <div class="row">
                            <span>년도입력</span>
                        </div>
                        <div class="row">
                            <input type="text" name="startPrdDe" id="startPrdDe" value="${data1.yr_dt}"
                                   style="width:100%">
                            <div id="rst1"></div>
                        </div>
                    </div>
                    <form id="frm1" name="frm1" onsubmit="api1();return false">
                        <input type="hidden" id="itmId" name="itmId" value="T1+"/>
                        <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                        <input type="hidden" id="objL2" name="objL2" value="ALL"/>
                        <input type="hidden" id="prdSe" name="prdSe" value="Y"/>
                        <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                        <input type="hidden" id="orgId" name="orgId" value="417"/>
                        <input type="hidden" id="tblId" name="tblId" value="DT_417001_0002"/>
                        <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                        <hr>
                        <button type="submit" class="btn btn-primary w-100">실행</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col">
            <form id="frm2" name="frm2" onsubmit="api2();return false">
                <input type="hidden" id="itmId" name="itmId" value="T3+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value=""/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1B83A35"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        <h5>시도/시군구/월별 혼인 (월)</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도입력(1~12월까지 자동실행됨)</span>
                            </div>
                            <div class="row">
                                <input type="text" name="startPrdDe" id="startPrdDe"
                                       value="${data2.yr_dt}${data2.mon_dt}" style="width:100%">
                                <div id="rst2"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary w-100">실행</button>
                </div>
            </form>
        </div>
        <div class="col">
            <form id="frm3" name="frm3" onsubmit="api3();return false">
                <input type="hidden" id="itmId" name="itmId" value="T3+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value=""/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1B83A35"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        <h5>시도/시군구/월별 이혼 (월)</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도입력(1~12월까지 자동실행됨)</span>
                            </div>
                            <div class="row">
                                <input type="text" name="startPrdDe" id="startPrdDe"
                                       value="${data3.yr_dt}${data3.mon_dt}" style="width:100%">
                                <div id="rst3"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary w-100">실행</button>
                </div>
            </form>
        </div>
        <div class="col">
            <form id="frm4" name="frm4" onsubmit="api4();return false">
                <input type="hidden" id="itmId" name="itmId" value="T10+T20+T50+T60+T80+T90+T100+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value="ALL"/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1DA7014S"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        <h5>고용률(시/군/구)(월)</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도입력(1~12월까지 자동실행됨)</span>
                            </div>
                            <div class="row">
                                <input type="text" name="startPrdDe" id="startPrdDe"
                                       value="${data4.yr_dt}${data4.mon_dt}" style="width:100%">
                                <div id="rst4"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="button" class="btn btn-primary w-100" onclick="api4();">실행</button>
                </div>
            </form>
        </div>
        <div class="col">
            <form id="frm5" name="frm5" onsubmit="api5();return false">
                <input type="hidden" id="itmId" name="itmId" value="T80+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value="ALL"/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1DA7104S"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        <h5>행정구역(시도)/성별 실업률(월)</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도입력(1~12월까지 자동실행됨)</span>
                            </div>
                            <div class="row">
                                <input type="text" name="startPrdDe" id="startPrdDe"
                                       value="${data5.yr_dt}${data5.mon_dt}" style="width:100%">
                                <div id="rst5"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary w-100">실행</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <form id="frm6" name="frm6" onsubmit="api6();return false">
                <input type="hidden" id="itmId" name="itmId" value="T1+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value=""/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1JH20151"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        <h5>전산업생산지수(월)</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도입력(1~12월까지 자동실행됨)</span>
                            </div>
                            <div class="row">
                                <input type="text" name="startPrdDe" id="startPrdDe"
                                       value="${data6.yr_dt}${data6.mon_dt}" style="width:100%">
                                <div id="rst6"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary w-100">실행</button>
                </div>
            </form>
        </div>
        <div class="col">
            <!--
            https://kosis.kr/openapi/Param/statisticsParameterData.do
            ?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=
            &itmId=T00+T10+T11+T12+T13+T14+T15+T16+T17+T18+
            &objL1=ALL
            &objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=
            &format=json&jsonVD=Y
            &prdSe=Y&startPrdDe=2021&endPrdDe=2021&loadGubun=2&orgId=101&tblId=DT_1SSCL060R
            -->
            <form id="frm7" name="frm7" onsubmit="api7();return false">
                <input type="hidden" id="itmId" name="itmId" value="13103314009T01+"/>
                <input type="hidden" id="objL1" name="objL1"
                       value="13102314009A.2+13102314009A.3+13102314009A.4+13102314009A.5+13102314009A.6+13102314009A.7+"/>
                <input type="hidden" id="objL2" name="objL2" value=""/>
                <input type="hidden" id="prdSe" name="prdSe" value="Y"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="314"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_AGE_DEP_AGG_MONTH"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        <h5>해외여행 경험 및 횟수</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도입력</span>
                            </div>
                            <div class="row">
                                <input type="text" name="startPrdDe" id="startPrdDe"
                                       value="<#if data7??>${data7.yr_dt}</#if>" style="width:100%">
                                <div id="rst7"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary w-100">실행</button>
                </div>
            </form>
        </div>
        <div class="col">
            <!--
            https://kosis.kr/openapi/Param/statisticsParameterData.do
            ?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=&itmId=T001+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=
            &objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&startPrdDe=2020&endPrdDe=2020&loadGubun=2&orgId=101&tblId=DT_1EP_2001
            -->
            <form id="frm8" name="frm8" onsubmit="api8();return false">
                <input type="hidden" id="itmId" name="itmId" value="T001+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value="ALL"/>
                <input type="hidden" id="prdSe" name="prdSe" value="Y"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1EP_2001"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        <h5>기업규모별 개인소득 점유율(년)</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도입력</span>
                            </div>
                            <div class="row">
                                <input type="text" name="startPrdDe" id="startPrdDe"
                                       value="<#if data8??>${data8.yr_dt}</#if>" style="width:100%">
                                <div id="rst8"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary w-100">실행</button>
                </div>
            </form>
        </div>
        <div class="col">
            <form id="frm9" name="frm9" onsubmit="api9();return false">
                <input type="hidden" id="itmId" name="itmId" value="QQQ+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value="ALL"/>
                <input type="hidden" id="prdSe" name="prdSe" value="Y"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="402"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_ES2017_037"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        <h5>삶의 만족도(년)</h5>
                    </div>
                    <hr>
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도입력</span>
                            </div>
                            <div class="row">
                                <input type="text" name="startPrdDe" id="startPrdDe"
                                       value="<#if data9??>${data9.yr_dt}</#if>" style="width:100%">
                                <div id="rst9"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary w-100">실행</button>
                </div>
            </form>
        </div>
        <#--        <div class="col">-->
        <#--<div class="card">
            <div class="card-header">
                기업규모별 소득
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
            <button type="button" class="btn btn-primary w-100">실행</button>
        </div>-->
    </div>
</div>
</div>
<#include "*/common/admin/footer.ftl"/>
</body>

<script src="/js/admin/lifeSatisfaction/lifeSatisfaction.js"></script>