<#include "*/common/front/header.ftl">

<body>
<#include "*/common/front/navigation.ftl">
<!-- 본문 시작 -->
<div class="contents">
    <div class="inner stock-index">
        <div class="col side-bar">
            <div class="inner-title">
                <h3>종합주가지수</h3>
            </div>
            <#include "*/common/front/left.ftl" />
        </div>
        <div class="col container">
            <div class="row">
                <div class="col-inner-3">
                    <div class="title-wrap">
                        <h6 class="tit">종합 주가 지수(KOSPI)</h6>
                    </div>
                    <div class="cont">
                        <div class="circle-wrap">
                            <div class="circle">
                                <div class="txt-wrap">
                                    <#if kospiIndex.current?number lt kospiIndex.past?number>
                                        <span class="txt count-ani-per" data-unit="P">${kospiIndex.current}</span>
                                        <span class="sub-txt">
                                        ▼ ${kospiIndex.subtraction} (${kospiIndex.subRate?number}%)
                                        </span>
                                    <#elseif kospiIndex.current?number == kospiIndex.past?number>
                                        <span class="txt count-ani-per" data-unit="P">${kospiIndex.current} %</span>
                                        <span class="sub-txt">변동없음</span>
                                    <#else>
                                        <span class="txt count-ani-per" data-unit="P">${kospiIndex.current}</span>
                                        <span class="sub-txt">
                                        ▲ ${kospiIndex.subtraction} (${kospiIndex.subRate}%)
                                        </span>
                                    </#if>
                                    <#--<span class="txt" id="kospiPoint" data-unit="P"></span>
                                    <span class="sub-txt" id="kospiVariance"></span>-->
                                </div>
                                <div class="wave wave1"></div>
                                <div class="wave wave2"></div>
                                <div class="wave wave3"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3">
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
                                    <b class="center-txt count-ani-per" data-unit="원/달러">${exchangeRate.current}</b>
                                    <span class="sub-txt down">
                                        ▼ ${exchangeRate.subtraction} (${exchangeRate.subRate}%)
                                    </span>
                                <#elseif exchangeRate.current?number == exchangeRate.past?number>
                                    <b class="center-txt count-ani-per" data-unit="원/달러">${exchangeRate.current} %</b>
                                    <span class="sub-txt">
                                    변동없음
                                </span>
                                <#else>
                                    <b class="center-txt count-ani-per" data-unit="원/달러">${exchangeRate.current}</b>
                                    <span class="sub-txt up">
                                        ▲ ${exchangeRate.subtraction} (${exchangeRate.subRate}%)
                                    </span>
                                </#if>
                                <#--<b class="center-txt" id="exchangeRatePoint" data-unit="원/달러"></b>
                                <span class="sub-txt" id="exchangeRateVariance"></span>-->
                            </div>
                        </div>
                        <div class="card">
                            <figure class="icon">
                                <i class="ri-coin-line"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">기준금리</h6>
                                <#if baseRate.current?number lt baseRate.past?number>
                                    <b class="center-txt count-ani-per" data-unit="%">${baseRate.current}</b>
                                    <span class="sub-txt down">
                                        ▼ ${baseRate.subtraction} (${baseRate.subRate}%)
                                    </span>
                                <#elseif baseRate.current?number == baseRate.past?number>
                                    <b class="center-txt count-ani-per" data-unit="%">${baseRate.current} %</b>
                                    <span class="sub-txt">
                                    변동없음
                                </span>
                                <#else>
                                    <b class="center-txt count-ani-per" data-unit="%">${baseRate.current}</b>
                                    <span class="sub-txt up">
                                        ▲ ${baseRate.subtraction} (${baseRate.subRate}%)
                                    </span>
                                </#if>
                                <#--<b class="center-txt" id="baseRatePoint" data-unit="%"></b>
                                <span class="sub-txt" id="baseRateVariance"></span>-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div class="title-wrap">
                        <h6 class="tit">코스닥 & 유가</h6>
                    </div>
                    <div class="cont">
                        <div id="kosdaqCard" class="card">
                            <figure class="icon">
                                <i class="ri-stock-line"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">코스닥</h6>
                                <#if kosdaqIndex.current?number lt kosdaqIndex.past?number>
                                    <b class="center-txt count-ani-per" data-unit="P">${kosdaqIndex.current}</b>
                                    <span class="sub-txt down">
                                        ▼ ${kosdaqIndex.subtraction} (${kosdaqIndex.subRate}%)
                                    </span>
                                <#elseif kosdaqIndex.current?number == kosdaqIndex.past?number>
                                    <b class="center-txt count-ani-per" data-unit="P">${kosdaqIndex.current} %</b>
                                    <span class="sub-txt">변동없음</span>
                                <#else>
                                    <b class="center-txt count-ani-per" data-unit="P">${kosdaqIndex.current}</b>
                                    <span class="sub-txt up">
                                        ▲ ${kosdaqIndex.subtraction} (${kosdaqIndex.subRate}%)
                                    </span>
                                </#if>
                                <#--<b class="center-txt" id="kosdaqPoint" data-unit="P"></b>
                                <span class="sub-txt" id="kosdaqVariance"></span>-->
                            </div>
                        </div>
                        <div id="wtiCard" class="card">
                            <figure class="icon">
                                <i class="ri-drop-fill"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">유가</h6>
                                <#if oilPrice.current?number lt oilPrice.past?number>
                                    <b class="center-txt count-ani-per" data-unit="달러/배럴">${oilPrice.current}</b>
                                    <span class="sub-txt down">
                                        ▼ ${oilPrice.subtraction} (${oilPrice.subRate}%)
                                    </span>
                                <#elseif oilPrice.current?number == oilPrice.past?number>
                                    <b class="center-txt count-ani-per" data-unit="달러/배럴">${oilPrice.current} %</b>
                                    <span class="sub-txt">변동없음</span>
                                <#else>
                                    <b class="center-txt count-ani-per" data-unit="달러/배럴">${oilPrice.current}</b>
                                    <span class="sub-txt up">
                                        ▲ ${oilPrice.subtraction} (${oilPrice.subRate}%)
                                    </span>
                                </#if>
                                <#--<b class="center-txt" id="oilPoint" data-unit="달러/배럴"></b>
                                <span class="sub-txt" id="oilVariance"></span>-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div class="title-wrap">
                        <h6 class="tit">GDP</h6>
                    </div>
                    <div class="cont">
                        <div id="gdpGraph" style="width:100%; height:200px;"></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">코로나 시기 KOSPI</h6>
                    </div>
                    <div class="cont">
                        <div id="covidKospiGraph" style="width:100%; height:334px"></div>
                    </div>
                </div>
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">기준금리 & KOSPI</h6>
                    </div>
                    <div class="cont">
                        <div id="baseRateKospiGraph" style="width:100%; height:334px;"></div>
                    </div>
                </div>
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">연도별 경제 성장률</h6>
                    </div>
                    <div class="cont">
                        <div id="inflationYearlyGraph" style="width:100%; height:326px"></div>
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

<script src="/js/front/stockPrices/stockPrices.js"></script>
<script src="/js/front/stockPrices/stockPricesChart.js"></script>