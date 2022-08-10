<#include "*/common/front/header.ftl">

<body>
<#include "*/common/front/navigation.ftl">
<!--- 본문 시작 -->
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
                <div class="col-inner-4">
                    <div class="title-wrap">
                        <h6 class="tit">KOSPI</h6>
                    </div>
                    <div class="cont">
                        <div id="covidKospiGraph" style="width:100%; height:334px"></div>
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

<script src="/js/front/temp/temp.js"></script>
<script src="/js/front/temp/tempChart.js"></script>