<#include "*/common/front/header.ftl">

<body>
<#include "*/common/front/navigation.ftl">

<!-- 본문 시작 -->
<div class="contents">
    <div class="inner economic-growth">
        <div class="col side-bar">
            <button type="button" class="side-toggle-btn">
              <i class="ri-arrow-right-s-line"></i>
            </button>
            <div class="inner-title">
                <h3>경제성장지표</h3>
            </div>
            <#include "*/common/front/left.ftl" />
        </div>
        <div class="col container">
            <div class="col-inner-3 col-lg-4 col-md-6 col-sm-12">
                <div class="title-wrap">
                    <h6 class="tit">전국 경제 성장률</h6>
                </div>
                <div class="cont">
                    <p class="caption">
                        <i class="ri-information-fill"></i> 해당년도의 자료입니다.
                    </p>
                    <div class="circle-wrap">
                        <div class="circle">
                            <span class="txt" id="wholeRegion" data-unit="%" data-digit="1"></span>
                            <div class="wave wave1"></div>
                            <div class="wave wave2"></div>
                            <div class="wave wave3"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-inner-3 col-lg-4 col-md-6 col-sm-12">
                <div class="title-wrap">
                    <h6 class="tit">환율 & 기준금리</h6>
                </div>
                <div class="cont">
                    <div class="card">
                        <figure class="icon">
                            <i class="ri-exchange-dollar-line"></i>
                        </figure>
                        <div class="txt-wrap">
                            <h6 class="tit">환율</h6>
                            <#if exchangeRate.current?number lt exchangeRate.past?number>
                                <b class="center-txt down count-ani-per" id="currentExChange" data-unit="원 / 달러" data-digit="2">${exchangeRate.current}</b>
                                <span class="sub-txt down" id="diffExchange">
                                    ▼ ${exchangeRate.subtraction} (${exchangeRate.differ?number}%)
                                </span>
                            <#else>
                                <b class="center-txt up count-ani-per" id="currentExChange" data-unit="원 / 달러" data-digit="2">${exchangeRate.current}</b>
                                <span class="sub-txt up" id="diffExchange">
                                    ▲ ${exchangeRate.subtraction} (${exchangeRate.differ?number}%)
                                </span>
                            </#if>
                            <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${exchangeRate.dataDate}</p>
<#--                            <b class="center-txt down" id="currentExChange">1314.50</b>-->
<#--                            <span class="sub-txt down" id="diffExchange">▼ 10.50 (-0.79%)</span>-->
                        </div>
                    </div>
                    <div class="card">
                        <figure class="icon">
                            <i class="ri-coin-line"></i></i>
                        </figure>
                        <div class="txt-wrap">
                            <h6 class="tit">기준금리</h6>
                            <#assign current = baseRate.current?number>
                            <#assign past = baseRate.past?number>
                            <#if current lt past>
                                <b class="center-txt down count-ani-per" data-unit="%" data-digit="2">${baseRate.current}</b>
                                <span class="sub-txt down">
                                ▼ ${baseRate.differ} (${baseRate.subRate}%)
                            </span>
                            <#elseif current == past>
                                <b class="center-txt count-ani-per" data-unit="%" data-digit="2">${baseRate.current}</b>
                                <span class="sub-txt">
                                변동없음
                            </span>
                            <#else>
                                <b class="center-txt up count-ani-per" data-unit="%" data-digit="2">${baseRate.current}</b>
                                <span class="sub-txt up">
                                ▲ ${baseRate.differ} (${baseRate.subRate}%)
                            </span>
                            </#if>
                            <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${baseRate.date}</p>
                            <#--                                <b class="center-txt">${baseRate.current} %</b>-->
<#--                            <span class="sub-txt up">▲ 0.25▲ 0.25</span>-->
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-inner-3 col-lg-4 col-md-6 col-sm-12">
                <div class="title-wrap">
                    <h6 class="tit">GDP & GNI</h6>
                </div>
                <div class="cont">
                    <div id="gdpCard" class="card">
                        <figure class="icon">
                            <i class="ri-earth-line"></i>
                        </figure>
                        <div class="txt-wrap">
                            <h6 class="tit">GDP</h6>
                            <b class="center-txt count-ani-per" data-unit=" ${gdpGni.GDP.unit}" data-digit="1">${gdpGni.GDP.val}</b>
                            <span class="sub-txt txt-xs">단위: ${gdpGni.GDP.unit}</span>
                            <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준년도 : ${gdpGni.GDP.date}</p>
                        </div>
                    </div>
                    <div id="gniCard" class="card">
                        <figure class="icon">
                            <i class="ri-earth-fill"></i>
                        </figure>
                        <div class="txt-wrap">
                            <h6 class="tit">GNI</h6>
                            <b class="center-txt count-ani-per" data-unit="${gdpGni.GNI.unit}" data-digit="1">${gdpGni.GNI.val}</b>
                            <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준년도 : ${gdpGni.GNI.date}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-inner-3 col-lg-6 col-md-6 col-sm-12">
                <div class="title-wrap">
                    <h6 class="tit">GDP 대비 국가채무</h6>
                </div>
                <div class="cont">
                    <div id="gdpDeptGraph" style="width:100%; height:200px;"></div>
                </div>
            </div>

            <div class="col-inner-3 col-lg-6 col-sm-12">
                <div class="title-wrap">
                    <h6 class="tit">지역별 경제 성장률</h6>
                </div>
                <div class="cont">
                    <p class="caption">
                        <i class="ri-information-fill"></i> 해당년도의 자료입니다.
                    </p>
                    <div id="regionGrowthGraph" style="width:100%; height:300px"></div>
                </div>
            </div>
            <div class="col-inner-3 col-lg-4 col-md-6 col-sm-12">
                <div class="title-wrap">
                    <h6 class="tit">코로나 시기 성장률</h6>
                </div>
                <div class="cont">
                    <div id="covidGrowthGraph" style="width:100%; height:332px;"></div>
                </div>
            </div>
            <div class="col-inner-6 col-lg-8 col-sm-12">
                <div class="title-wrap">
                    <h6 class="tit">물가 상승률 & 상승 추이</h6>
                </div>
                <div class="cont">
                    <div class="diagrams">
                        <div class="left">
                            <div class="card">
                                <figure class="icon">
                                    <i class="ri-exchange-funds-line"></i>
                                </figure>
                                <div class="txt-wrap">
                                    <h6 class="tit">소비</h6>
                                    <b class="center-txt count-ani-per" data-unit="%" data-digit="1">${inflationRate.consume}</b>
                                </div>
                            </div>
                            <div class="card">
                                <figure class="icon">
                                    <i class="ri-swap-line"></i>
                                </figure>
                                <div class="txt-wrap">
                                    <h6 class="tit">근원</h6>
                                    <b class="center-txt count-ani-per" data-unit="%" data-digit="1">${inflationRate.source}</b>
                                </div>
                            </div>
                            <div class="card">
                                <figure class="icon">
                                    <i class="ri-shopping-basket-line"></i>
                                </figure>
                                <div class="txt-wrap">
                                    <h6 class="tit">생활</h6>
                                    <b class="center-txt count-ani-per" data-unit="%" data-digit="1">${inflationRate.living}</b>
                                </div>
                            </div>
                        </div>
                        <div class="right">
                            <div id="inflationGraph" style="width:100%; height:330px"></div>
                        </div>
                    </div>
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

<script src="/js/front/economicGrowth/economicGrowth.js"></script>
<script src="/js/front/economicGrowth/economicGrowthChart.js"></script>