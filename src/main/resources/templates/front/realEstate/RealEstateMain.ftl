<#include "*/common/front/header.ftl">

<body>
<#include "*/common/front/navigation.ftl">
<div class="contents">
    <div class="inner estate-trend">
        <div class="col side-bar">
            <div class="inner-title">
                <h3>부동산시장동향</h3>
            </div>
            <#include "*/common/front/left.ftl" />
        </div>
        <div class="col container">
            <div class="row">
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">행정구역별 매매거래</h6>
                    </div>
                    <div class="cont">
                        <p class="caption">
                            <i class="ri-information-fill"></i> 해당년도의 자료입니다.
                        </p>
                        <div
                                id="admDivTradeGraph"
                                style="width: 100%; height: 280px"
                        ></div>
                    </div>
                </div>
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">연령대별 & 건축년수별 매매거래</h6>
                    </div>
                    <div class="cont">
                        <p class="caption">
                            <i class="ri-information-fill"></i> 해당년도의 자료입니다.
                            (전국 기준)
                        </p>
                        <div
                                class="graph-wrap"
                                id="ageTradeGraph"
                                style="width: 100%; height: 130px"
                        ></div>
                        <div
                                class="graph-wrap"
                                id="constTradeGraph"
                                style="width: 100%; height: 128px"
                        ></div>
                    </div>
                </div>
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">지역별 인구수</h6>
                    </div>
                    <div class="cont">
                        <p class="caption">
                            <i class="ri-information-fill"></i> 해당년도의 자료입니다.
                        </p>
                        <div
                                id="popRegionGraph"
                                style="width: 100%; height: 280px"
                        ></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-inner-6">
                    <div class="title-wrap">
                        <h6 class="tit">기준금리별 미분양주택</h6>
                    </div>
                    <div class="cont">
                        <div
                                id="brHousingGraph"
                                style="width: 100%; height: 224px"
                        ></div>
                    </div>
                </div>
                <div class="col-inner-6">
                    <div class="title-wrap">
                        <h6 class="tit">소유자 비율</h6>
                    </div>
                    <div class="cont">
                        <p class="caption">
                            <i class="ri-information-fill"></i> 해당년도의 자료입니다.
                            (전국 기준)
                        </p>
                        <div class="wrapper">
                            <div class="left">
                                <div
                                        id="ownerByGenderGraph"
                                        style="width: 100%; height: 192px"
                                ></div>
                            </div>
                            <div class="right">
                                <div
                                        id="ownerByAgeGraph"
                                        style="width: 100%; height: 192px"
                                ></div>
                            </div>
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


<script src="/js/front/realEstate/realEstate.js"></script>
<script src="/js/front/realEstate/realEstateChart.js"></script>