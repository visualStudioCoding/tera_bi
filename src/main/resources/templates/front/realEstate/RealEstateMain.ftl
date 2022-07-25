<#include "*/common/front/header.ftl">

<body>
<#include "*/common/front/navigation.ftl">
<div class="contents">
    <div class="inner economic-growth">
        <div class="col side-bar">
            <div class="inner-title">
                <h3>부동산</h3>
            </div>
            <form class="box">
                <div class="tit">기간설정</div>
                <div class="input-group">
                    <input type="radio" name="ecGrowthTerm" id="ecGrowthTerm1" checked/>
                    <label for="ecGrowthTerm1">5년</label>
                    <input type="radio" name="ecGrowthTerm" id="ecGrowthTerm2"/>
                    <label for="ecGrowthTerm2">10년</label>
                    <input type="radio" name="ecGrowthTerm" id="ecGrowthDatePick"/>
                    <label for="ecGrowthDatePick">기간선택</label>
                </div>
                <div class="date-picker-group">
                    <input type="text" name="ecGrowthDatePicker" class="w-100" disabled/>
                    <i class="ri-calendar-line"></i>
                </div>
                <button type="button" class="btn-primary w-100">
                    <i class="ri-check-line"></i>적용
                </button>
            </form>
            <form class="box">
                <div class="tit">지역설정</div>
                <div class="input-group">
                    <input type="radio" name="ecLoc" id="ecLocAll" checked/>
                    <label for="ecLocAll">전국</label>
                    <input type="radio" name="ecLoc" id="ecLocSlcts"/>
                    <label for="ecLocSlcts">지역선택</label>
                </div>
                <div class="select-group">
                    <label for="ecLocSlct1">시/도</label>
                    <select name="ecLocSlct" id="ecLocSlct1" disabled>
                        <option value="">서울특별시</option>
                        <option value="">부산광역시</option>
                        <option value="">대구광역시</option>
                        <option value="">인천광역시</option>
                        <option value="">광주광역시</option>
                        <option value="">대전광역시</option>
                        <option value="">울산광역시</option>
                        <option value="">세종특별자치시</option>
                        <option value="">경기도</option>
                        <option value="">강원도</option>
                        <option value="">충청북도</option>
                        <option value="">충청남도</option>
                        <option value="">전라북도</option>
                        <option value="">전라남도</option>
                        <option value="">경상북도</option>
                        <option value="">경상남도</option>
                        <option value="">제주특별자치도</option>
                    </select>
                </div>
                <div class="select-group">
                    <label for="ecLocSlct2">시/군/구</label>
                    <select name="ecLocSlct" id="ecLocSlct2" disabled>
                        <option value="">강남구</option>
                        <option value="">강동구</option>
                        <option value="">강북구</option>
                        <option value="">강서구</option>
                        <option value="">관악구</option>
                        <option value="">광진구</option>
                        <option value="">구로구</option>
                        <option value="">금천구</option>
                    </select>
                </div>
                <div class="select-group">
                    <label for="ecLocSlct3">읍/면/동</label>
                    <select name="ecLocSlct" id="ecLocSlct3" disabled>
                        <option value="">신사동</option>
                        <option value="">논현동</option>
                        <option value="">압구정동</option>
                        <option value="">청담동</option>
                        <option value="">삼성동</option>
                        <option value="">대치동</option>
                        <option value="">역삼동</option>
                        <option value="">도곡동</option>
                        <option value="">개포동</option>
                    </select>
                </div>
                <button type="button" class="btn-primary w-100">
                    <i class="ri-check-line"></i>적용
                </button>
            </form>
            <div class="btn-wrap">
                <button type="button" class="btn-secondary w-100">
                    AI Display
                </button>
                <button type="button" class="btn-secondary w-100">
                    File Report
                </button>
            </div>
        </div>
        <div class="col container">
            <div class="row">
                <div class="col-inner-3">
                    <div id="exRate" class="card">
                        <figure class="icon">
                            <i class="ri-exchange-dollar-line"></i>
                        </figure>
                        <div class="txt-wrap">
                            <h6 class="tit">환율</h6>
                            <b class="center-txt down" id="exchangeValue">1314.50</b>
                            <span class="sub-txt down">▼ 10.50 (-0.79%)</span>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div id="baseRate" class="card">
                        <figure class="icon">
                            <i class="ri-coin-line"></i></i>
                        </figure>
                        <div class="txt-wrap">
                            <h6 class="tit">기준금리</h6>
                            <b class="center-txt up">1.75%</b>
                            <span class="sub-txt up">▲ 0.25</span>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div id="gdpCard" class="card">
                        <figure class="icon">
                            <i class="ri-earth-line"></i>
                        </figure>
                        <div class="txt-wrap">
                            <h6 class="tit">GDP</h6>
                            <b class="center-txt">1,915,777.5</b>
                            <span class="sub-txt">단위: 백만</span>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div id="gniCard" class="card">
                        <figure class="icon">
                            <i class="ri-earth-fill"></i>
                        </figure>
                        <div class="txt-wrap">
                            <h6 class="tit">GNI</h6>
                            <b class="center-txt">2,094,721.3</b>
                            <span class="sub-txt">단위: 백만</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-inner-9">
                    <div class="title-wrap">
                        <h6 class="tit">경제 성장률</h6>
                    </div>
                    <div class="cont">
                        <div class="graph-wrap">
                            <div class="left">
                                <div class="circle-wrap">
                                    <div class="sub-tit">전체</div>
                                    <div class="circle">
                                        <span class="txt">3.5%</span>
                                        <div class="wave wave1"></div>
                                        <div class="wave wave2"></div>
                                        <div class="wave wave3"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="right">
                                <div class="graph">
                                    <span class="sub-tit">지역별</span>
                                    <div id="regionGrowthGraph" style="width:800px; height:500px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-3">
                    <div class="title-wrap">
                        <h6 class="tit">코로나 시기 성장률</h6>
                    </div>
                    <div class="cont">
                        <div id="covidGrowthGraph" style="width:100%; height:500px;"></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-inner-6">
                    <div class="title-wrap">
                        <h6 class="tit">물가 상승률</h6>
                    </div>
                    <div class="cont">
                        <p class="caption"><i class="ri-information-fill"></i> 해당년도의 항목별 물가상승률입니다.</p>
                        <div class="card col-inner-4">
                            <figure class="icon">
                                <i class="ri-earth-fill"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">소비</h6>
                                <b class="center-txt">2.5%</b>
                            </div>
                        </div>
                        <div class="card col-inner-4">
                            <figure class="icon">
                                <i class="ri-earth-fill"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">근원</h6>
                                <b class="center-txt">3.4%</b>
                            </div>
                        </div>
                        <div class="card col-inner-4">
                            <figure class="icon">
                                <i class="ri-earth-fill"></i>
                            </figure>
                            <div class="txt-wrap">
                                <h6 class="tit">생활</h6>
                                <b class="center-txt">10%</b>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-inner-6">
                    <div class="title-wrap">
                        <h6 class="tit">물가 상승 추이</h6>
                    </div>
                    <div class="cont">
                        <div id="inflationGraph" style="width:100%; height:500px;"></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-inner-12">
                    <div class="title-wrap">
                        <h6 class="tit">GDP 대비 국가채무</h6>
                    </div>
                    <div class="cont">
                        <div id="gdpDeptGraph" style="width:100%; height:500px;"></div>
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

<#--<script src="/js/front/realEstate/realEstate.js"></script>-->
<#--<script src="/js/front/realEstate/realEstateChart.js"></script>-->