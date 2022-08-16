<#include "*/common/front/header.ftl">

<body>
<#include "*/common/front/navigation.ftl">
<!-- 본문 시작 -->
<div class="contents">
    <div class="inner stock-index">
        <div class="col side-bar">
            <button type="button" class="side-toggle-btn">
              <i class="ri-arrow-right-s-line"></i>
            </button>
            <div class="inner-title">
                <h3>종합주가지수</h3>
            </div>
            <#include "*/common/front/left.ftl" />
        </div>
        <div class="col container">

                <div class="col-inner-3 col-lg-4 col-sm-12">
                    <div class="title-wrap">
                        <h6 class="tit">종합 주가 지수(KOSPI)</h6>
                    </div>
                    <div class="cont">
                        <p class="caption"><i class="ri-information-fill"></i> 기준일 : ${kospiIndex.baseDate}</p>
                        <#--<p class="caption"><i class="ri-information-fill"></i><span id="kospiBaseDate"></span></p>-->
                        <div class="circle-wrap">
                            <div class="circle">
                                <div class="txt-wrap">
                                    <#assign kospiIndexCurrent = kospiIndex.current?replace(",","")>
                                    <#assign kospiIndexPast = kospiIndex.past?replace(",","")>
                                    <#if kospiIndexCurrent?number lt kospiIndexPast?number>
                                        <span class="txt count-ani-per" data-unit="P" data-digit="2">${kospiIndex.current}</span>
                                        <span class="sub-txt">
                                        ▼ ${kospiIndex.subtraction} (${kospiIndex.subRate}%)
                                        </span>
                                    <#elseif kospiIndexCurrent?number == kospiIndexPast?number>
                                        <span class="txt count-ani-per" data-unit="P" data-digit="2">${kospiIndex.current} %</span>
                                        <span class="sub-txt">변동없음</span>
                                    <#else>
                                        <span class="txt count-ani-per" data-unit="P" data-digit="2">${kospiIndex.current}</span>
                                        <span class="sub-txt">
                                        ▲ ${kospiIndex.subtraction} (${kospiIndex.subRate}%)
                                        </span>
                                    </#if>
                                    <#--<span class="txt" id="kospiPoint" data-unit="P" data-digit="2"></span>
                                    <span class="sub-txt" id="kospiVariance"></span>-->
                                </div>
                                <div class="wave wave1"></div>
                                <div class="wave wave2"></div>
                                <div class="wave wave3"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3 col-lg-4 col-sm-6 col-xs-12">
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
                                <#assign exchangeRateCurrent = exchangeRate.current?replace(",","")>
                                <#assign exchangeRatePast = exchangeRate.past?replace(",","")>
                                <#if exchangeRateCurrent?number lt exchangeRatePast?number>
                                    <b class="center-txt count-ani-per" data-unit="원/달러" data-digit="2">${exchangeRate.current}</b>
                                    <span class="sub-txt down">
                                        ▼ ${exchangeRate.subtraction} (${exchangeRate.subRate}%)
                                    </span>
                                <#elseif exchangeRateCurrent?number == exchangeRatePast?number>
                                    <b class="center-txt count-ani-per" data-unit="원/달러" data-digit="2">${exchangeRate.current}</b>
                                    <span class="sub-txt">
                                    변동없음
                                </span>
                                <#else>
                                    <b class="center-txt count-ani-per" data-unit="원/달러" data-digit="2">${exchangeRate.current}</b>
                                    <span class="sub-txt up">
                                        ▲ ${exchangeRate.subtraction} (${exchangeRate.subRate}%)
                                    </span>
                                </#if>
                                <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${exchangeRate.baseDate}</p>
                                <#--<b class="center-txt" id="exchangeRatePoint" data-unit="원/달러" data-digit="2"></b>
                                <span class="sub-txt" id="exchangeRateVariance"></span>
                                <p class="caption txt-xs"><i class="ri-information-fill"></i><span id="exchangeRateBaseDate"></span></p>-->
                            </div>
                        </div>
                        <div class="card">
                            <figure class="icon">
                                <i class="ri-coin-line"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">기준금리</h6>
                                <#assign baseRateCurrent = baseRate.current?replace(",","")>
                                <#assign baseRatePast = baseRate.past?replace(",","")>
                                <#if baseRateCurrent?number lt baseRatePast?number>
                                    <b class="center-txt count-ani-per" data-unit="%" data-digit="2">${baseRate.current}</b>
                                    <span class="sub-txt down">
                                        ▼ ${baseRate.subtraction} (${baseRate.subRate}%)
                                    </span>
                                <#elseif baseRateCurrent?number == baseRatePast?number>
                                    <b class="center-txt count-ani-per" data-unit="%" data-digit="2">${baseRate.current} %</b>
                                    <span class="sub-txt">
                                    변동없음
                                </span>
                                <#else>
                                    <b class="center-txt count-ani-per" data-unit="%" data-digit="2">${baseRate.current}</b>
                                    <span class="sub-txt up">
                                        ▲ ${baseRate.subtraction} (${baseRate.subRate}%)
                                    </span>
                                </#if>
                                <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${baseRate.baseDate}</p>
                                <#--<b class="center-txt" id="baseRatePoint" data-unit="%" data-digit="2"></b>
                                <span class="sub-txt" id="baseRateVariance"></span>
                                <p class="caption txt-xs"><i class="ri-information-fill"></i><span id="baseRateBaseDate"></span></p>-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3 col-lg-4 col-sm-6 col-xs-12">
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
                                <#assign kosdaqIndexCurrent = kosdaqIndex.current?number>
                                <#assign kosdaqIndexPast = kosdaqIndex.past?number>
                                <#if kosdaqIndexCurrent?number lt kosdaqIndexPast?number>
                                    <b class="center-txt count-ani-per" data-unit="P" data-digit="2">${kosdaqIndex.current}</b>
                                    <span class="sub-txt down">
                                        ▼ ${kosdaqIndex.subtraction} (${kosdaqIndex.subRate}%)
                                    </span>
                                <#elseif kosdaqIndexCurrent?number == kosdaqIndexPast?number>
                                    <b class="center-txt count-ani-per" data-unit="P" data-digit="2">${kosdaqIndex.current}</b>
                                    <span class="sub-txt">변동없음</span>
                                <#else>
                                    <b class="center-txt count-ani-per" data-unit="P" data-digit="2">${kosdaqIndex.current}</b>
                                    <span class="sub-txt up">
                                        ▲ ${kosdaqIndex.subtraction} (${kosdaqIndex.subRate}%)
                                    </span>
                                </#if>
                                <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${kosdaqIndex.baseDate}</p>
                                <#--<b class="center-txt" id="kosdaqPoint" data-unit="P" data-digit="2"></b>
                                <span class="sub-txt" id="kosdaqVariance"></span>
                                <p class="caption txt-xs"><i class="ri-information-fill"></i><span id="kosdaqBaseDate"></span></p>-->
                            </div>
                        </div>
                        <div id="wtiCard" class="card">
                            <figure class="icon">
                                <i class="ri-drop-fill"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">유가</h6>
                                <#assign oilPriceCurrent = oilPrice.current?number>
                                <#assign oilPricePast = oilPrice.past?number>
                                <#if oilPriceCurrent lt oilPricePast>
                                    <b class="center-txt count-ani-per" data-unit="달러/배럴" data-digit="2">${oilPrice.current}</b>
                                    <span class="sub-txt down">
                                        ▼ ${oilPrice.subtraction} (${oilPrice.subRate}%)
                                    </span>
                                <#elseif oilPriceCurrent == oilPricePast>
                                    <b class="center-txt count-ani-per" data-unit="달러/배럴" data-digit="2">${oilPrice.current} %</b>
                                    <span class="sub-txt">변동없음</span>
                                <#else>
                                    <b class="center-txt count-ani-per" data-unit="달러/배럴" data-digit="2">${oilPrice.current}</b>
                                    <span class="sub-txt up">
                                        ▲ ${oilPrice.subtraction} (${oilPrice.subRate}%)
                                    </span>
                                </#if>
                                <p class="caption txt-xs"><i class="ri-information-fill"></i> 기준일 : ${oilPrice.baseDate}</p>
                                <#--<b class="center-txt" id="oilPoint" data-unit="달러/배럴" data-digit="2"></b>
                                <span class="sub-txt" id="oilVariance"></span>
                                <p class="caption txt-xs"><i class="ri-information-fill"></i><span id="oilBaseDate"></span></p>-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3 col-lg-6 col-sm-12" id="gdpChartBox">
                    <div class="title-wrap">
                        <h6 class="tit">GDP</h6>
                    </div>
                    <div class="cont">
                        <div id="gdpGraph" style="width:100%; height:208px;"></div>
                    </div>
                </div>

                <div class="col-inner-4 col-lg-6 col-sm-12">
                    <div class="title-wrap">
                        <h6 class="tit">코로나 시기 KOSPI</h6>
                    </div>
                    <div class="cont">
                        <p class="caption"><i class="ri-information-fill"></i> 코로나19 범유행 : 2019년 12월 </p>
                        <div id="covidKospiGraph" style="width:100%; height:300px"></div>
                    </div>
                </div>
                <div class="col-inner-4 col-lg-6 col-sm-12">
                    <div class="title-wrap">
                        <h6 class="tit">기준금리 & KOSPI</h6>
                    </div>
                    <div class="cont">
                        <div id="baseRateKospiGraph" style="width:100%; height:330px;"></div>
                    </div>
                </div>
                <div class="col-inner-4 col-lg-6 col-sm-12">
                    <div class="title-wrap">
                        <h6 class="tit">연도별 경제 성장률</h6>
                    </div>
                    <div class="cont">
                        <div id="inflationYearlyGraph" style="width:100%; height:330px"></div>
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