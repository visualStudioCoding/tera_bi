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
                                    <span class="txt" id="kospiPoint"></span>
                                    <span class="sub-txt" id="kospiVariance"></span>
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
                                <b class="center-txt" id="exchangeRatePoint"></b>
                                <span class="sub-txt" id="exchangeRateVariance"></span>
                            </div>
                        </div>
                        <div class="card">
                            <figure class="icon">
                                <i class="ri-coin-line"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">기준금리</h6>
                                <b class="center-txt" id="baseRatePoint"></b>
                                <span class="sub-txt" id="baseRateVariance"></span>
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
                                <b class="center-txt" id="kosdaqPoint"></b>
                                <span class="sub-txt" id="kosdaqVariance"></span>
                            </div>
                        </div>
                        <div id="wtiCard" class="card">
                            <figure class="icon">
                                <i class="ri-drop-fill"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">유가</h6>
                                <b class="center-txt" id="oilPoint"></b>
                                <span class="sub-txt" id="oilVariance"></span>
                                <span class="sub-txt">단위 : 달러/배럴</span>
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