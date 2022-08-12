<#include "*/common/front/header.ftl">

<body>
<#include "*/common/front/navigation.ftl">
<!-- 본문 시작 -->
<div class="contents">
    <div class="inner stnd-living">
        <div class="col side-bar">
            <div class="inner-title">
                <h3>생활수준지표</h3>
            </div>
            <#include "*/common/front/left.ftl" />
        </div>
        <div class="col container">

                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">생활수준지표</h6>
                    </div>
                    <div class="cont">
                        <div class="wrapper">
                            <div class="left">
                                <div class="card min-wage">
                                    <figure class="icon">
                                        <i class="ri-hand-coin-line"></i>
                                    </figure>
                                    <div class="txt-wrap">
                                        <h6 class="tit">최저임금</h6>
                                        <#assign minPayCurrent = minPay.current?replace(",","")>
                                        <#assign minPayPast = minPay.past?replace(",","")>
                                        <#if minPayCurrent?number lt minPayPast?number>
                                            <b class="center-txt count-ani-per" data-unit="${minPay.unit}" data-digit="2">${minPay.current}</b>
                                            <span class="sub-txt down">
                                                ▼ ${minPay.subtraction} (${minPay.subRate}%)
                                            </span>
                                        <#elseif minPayCurrent?number == minPayPast?number>
                                            <b class="center-txt count-ani-per" data-unit="${minPay.unit}" data-digit="2">${minPay.current}</b>
                                            <span class="sub-txt">
                                                변동없음
                                            </span>
                                        <#else>
                                            <b class="center-txt count-ani-per" data-unit="${minPay.unit}" data-digit="2">${minPay.current}</b>
                                            <span class="sub-txt up">
                                                ▲ ${minPay.subtraction} (${minPay.subRate}%)
                                            </span>
                                        </#if>
                                        <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${minPay.baseDate}</p>
                                    </div>
                                </div>
                                <div class="card cs-infl">
                                    <figure class="icon">
                                        <i class="ri-funds-box-fill"></i>
                                    </figure>
                                    <div class="txt-wrap">
                                        <h6 class="tit">경제성장률</h6>
                                        <#assign economicGrowthCurrent = economicGrowth.current?replace(",","")>
                                        <#assign economicGrowthPast = economicGrowth.past?replace(",","")>
                                        <#if economicGrowthCurrent?number lt economicGrowthPast?number>
                                            <b class="center-txt count-ani-per" data-unit="${economicGrowth.unit}" data-digit="2">${economicGrowth.current}</b>
                                            <span class="sub-txt down">
                                                ▼ ${economicGrowth.subtraction} (${economicGrowth.subRate}%)
                                            </span>
                                        <#elseif economicGrowthCurrent?number == economicGrowthPast?number>
                                            <b class="center-txt count-ani-per" data-unit="${economicGrowth.unit}" data-digit="2">${economicGrowth.current}</b>
                                            <span class="sub-txt">
                                                변동없음
                                            </span>
                                        <#else>
                                            <b class="center-txt count-ani-per" data-unit="${economicGrowth.unit}" data-digit="2">${economicGrowth.current}</b>
                                            <span class="sub-txt up">
                                                ▲ ${economicGrowth.subtraction} (${economicGrowth.subRate}%)
                                            </span>
                                        </#if>
                                        <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${economicGrowth.baseDate}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="right">
                                <div class="card">
                                    <figure class="icon">
                                        <img src="/img/ico-csinfl.png" alt="" />
                                    </figure>
                                    <div class="txt-wrap">
                                        <h6 class="tit">소비자 물가상승률</h6>
                                        <#assign consumerInflationCurrent = consumerInflation.current?replace(",","")>
                                        <#assign consumerInflationPast = consumerInflation.past?replace(",","")>
                                        <#if consumerInflationCurrent?number lt consumerInflationPast?number>
                                            <b class="center-txt count-ani-per" data-unit="${consumerInflation.unit}" data-digit="2">${consumerInflation.current}</b>
                                            <span class="sub-txt down">
                                                ▼ ${consumerInflation.subtraction} (${consumerInflation.subRate}%)
                                            </span>
                                        <#elseif consumerInflationCurrent?number == consumerInflationPast?number>
                                            <b class="center-txt count-ani-per" data-unit="${consumerInflation.unit}" data-digit="2">${consumerInflation.current}</b>
                                            <span class="sub-txt">
                                                변동없음
                                            </span>
                                        <#else>
                                            <b class="center-txt count-ani-per" data-unit="${consumerInflation.unit}" data-digit="2">${consumerInflation.current}</b>
                                            <span class="sub-txt up">
                                                ▲ ${consumerInflation.subtraction} (${consumerInflation.subRate}%)
                                            </span>
                                        </#if>
                                        <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${consumerInflation.baseDate}</p>
                                    </div>
                                </div>
                                <div class="card gni">
                                    <figure class="icon">
                                        <i class="ri-earth-fill"></i>
                                    </figure>
                                    <div class="txt-wrap">
                                        <h6 class="tit">1인당 국민총소득(GNI)</h6>
                                        <#assign gniCurrent = gni.current?replace(",","")>
                                        <#assign gniPast = gni.past?replace(",","")>
                                        <#if gniCurrent?number lt gniPast?number>
                                            <b class="center-txt count-ani-per" data-unit="${gni.unit}" data-digit="2">${gni.current}</b>
                                            <span class="sub-txt down">
                                                ▼ ${gni.subtraction} (${gni.subRate}%)
                                            </span>
                                        <#elseif gniCurrent?number == gniPast?number>
                                            <b class="center-txt count-ani-per" data-unit="${gni.unit}" data-digit="2">${gni.current}</b>
                                            <span class="sub-txt">
                                                변동없음
                                            </span>
                                        <#else>
                                            <b class="center-txt count-ani-per" data-unit="${gni.unit}" data-digit="2">${gni.current}</b>
                                            <span class="sub-txt up">
                                                ▲ ${gni.subtraction} (${gni.subRate}%)
                                            </span>
                                        </#if>
                                        <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${gni.baseDate}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">물가상승률 통계</h6>
                    </div>
                    <div class="cont">
                        <p class="info-txt">
                            전년동월비
                            <#if consumerInflationCurrent?number lt consumerInflationPast?number>
                                <span class="down"><b class="emp count-ani-per" data-unit="%" data-digit="2">${consumerInflation.current}</b></span>
                                하락했습니다.
                            <#elseif consumerInflationCurrent?number == consumerInflationPast?number>
                                <span><b class="emp count-ani-per" data-unit="%" data-digit="2">${consumerInflation.current}</b></span>
                                변동없습니다.
                            <#else>
                                <span class="up"><b class="emp count-ani-per" data-unit="%" data-digit="2">${consumerInflation.current}</b></span>
                                상승했습니다.
                            </#if>
                        </p>
                        <div class="wrapper">
                            <div class="left">
                                <div
                                        id="yearsInflGraph"
                                        style="width: 100%; height: 180px"
                                ></div>
                            </div>
                            <div class="right">
                                <div
                                        id="presInflGraph"
                                        style="width: 100%; height: 180px"
                                ></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">지니계수</h6>
                    </div>
                    <div class="cont">
                        <div id="giniGraph" style="width: 100%; height: 236px"></div>
                    </div>
                </div>


                <div class="col-inner-3">
                    <div class="title-wrap">
<#--                        <h6 class="tit">우크라이나 전쟁 이후 물가상승률</h6>-->
                        <h6 class="tit">우크라이나 전쟁 이후 물가상승지수</h6>
                    </div>
                    <div class="cont">
                        <p class="caption">
                            <i class="ri-information-fill"></i> 우크라이나 전쟁: 2021년
                            10월 말 발생
                        </p>
                        <div id="ukrInflGraph" style="width: 100%; height: 270px"></div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div class="title-wrap">
                        <h6 class="tit">임금 대비 물가상승률</h6>
                    </div>
                    <div class="cont">
                        <p class="caption">
                            <i class="ri-information-fill"></i> 해당년도 자료입니다.
                        </p>
                        <div class="graph-wrap">
                            <div
                                    id="wageInflGraph"
                                    style="width: 100%; height: 270px"
                            ></div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div class="title-wrap">
                        <h6 class="tit">지역별 경제성장률 순위</h6>
                    </div>
                    <div class="cont">
                        <p class="caption">
                            <i class="ri-information-fill"></i> 해당년도 자료입니다.
                        </p>
                        <div id="regRankGraph" style="width: 100%; height: 270px"></div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div class="title-wrap">
                        <h6 class="tit">연령별 해외여행 통계</h6>
                    </div>
                    <div class="cont">
                        <div id="travelGraph" style="width: 100%; height: 302px"></div>
                    </div>
                </div>

        </div>
    </div>
</div>
<!-- /본문 끝 -->
<!-- 푸터 시작 -->
<#include "*/common/front/footer.ftl">
<!-- /푸터 끝 -->
</body>

<script src="/js/front/standardOfLiving/standardOfLiving.js"></script>
<script src="/js/front/standardOfLiving/standardOfLivingChart.js"></script>