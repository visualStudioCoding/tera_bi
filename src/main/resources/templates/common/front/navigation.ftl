<!-- 헤더 시작 -->
<header>
    <div class="header-wrap">
        <div class="logo-wrap">
            <a href="/" class="logo"><img alt="Bismuth 로고" src="/img/logo.png"/></a>
        </div>
        <div class="nav-wrap">
            <ul class="nav">
                <li>
                    <a href="/front/economicGrowth/main?menuCode=001" class="nav-link <#if menuCode = '001'>on</#if>" aria-current="page">
                        <span><i class="ri-funds-box-fill"></i>경제성장지표</span>
                    </a>
                </li>
                <li>
                    <a href="/front/standardOfLiving/main?menuCode=002" class="nav-link <#if menuCode = '002'>on</#if>">
                        <span><i class="ri-pie-chart-2-fill"></i>생활수준지표</span>
                    </a>
                </li>
                <li>
                    <a href="/front/stockPrices/main?menuCode=003" class="nav-link <#if menuCode = '003'>on</#if>">
                        <span><i class="ri-line-chart-fill"></i>종합주가지수</span>
                    </a>
                </li>
                <li>
                    <a href="/front/realEstate/main?menuCode=004" class="nav-link <#if menuCode = '004'>on</#if>"
                    ><span><i class="ri-building-fill"></i>부동산시장동향</span>
                    </a>
                </li>
                <li>
                    <a href="/front/managementAnalysis/main?menuCode=005" class="nav-link <#if menuCode = '005'>on</#if>">
                        <span><i class="ri-file-list-3-fill"></i>경영지표분석</span>
                    </a>
                </li>
                <li>
                    <a href="/" class="nav-link"
                    ><span><i class="ri-flashlight-fill"></i>전력수급현황</span>
                    </a>
                </li>
                <li>
                    <a href="/admin" class="nav-link"
                    ><span><i class="ri-dashboard-2-fill"></i>API 호출</span>
                    </a>
                </li>
            </ul>
            <span class="indicator"></span>
        </div>
        <div id="darkModeBtn" class="dark-toggle-btn">
            <input type="checkbox" id="checkbox"/>
            <label for="checkbox" class="label">
                <i class="ri-moon-fill dark-btn"></i>
                <i class="ri-sun-fill light-btn"></i>
                <span class="ball"></span>
            </label>
        </div>
    </div>
</header>
<!-- /헤더 끝 -->