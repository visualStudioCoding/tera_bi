<#include "*/common/front/header.ftl">

<body>
<#include "*/common/front/navigation.ftl">

<!-- 본문 시작 -->
<div class="contents">
  <div class="inner manage-analysis">
    <div class="col side-bar on">
      <!--
      <button type="button" class="side-toggle-btn">
        <i class="ri-arrow-right-s-line"></i>
      </button>
      -->
      <div class="inner-title">
        <h3>경영지표분석</h3>
      </div>
      <#include "*/common/front/left.ftl" />
    </div>
    <div class="col container">
      <div class="row">
        <div class="col-inner-12" style="height: 90px">
          <div class="wrapper text-boxes row">
            <div class="text-box">
              <h6 class="tit">인원수</h6>
              <span class="txt count-ani">${emplyCnt.totalCnt}</span>
            </div>
            <div class="text-box">
              <h6 class="tit">본사</h6>
              <span class="txt count-ani">${emplyCnt.hdqrtCnt}</span>
            </div>
            <div class="text-box">
              <h6 class="tit">파견</h6>
              <span class="txt count-ani">${emplyCnt.dsptcCnt}</span>
            </div>
            <div class="text-box">
              <h6 class="tit">지사</h6>
              <span class="txt count-ani">${emplyCnt.brnchCnt}</span>
            </div>
            <div class="text-box">
              <h6 class="tit">재택</h6>
              <span class="txt count-ani">${emplyCnt.homejobCnt}</span>
            </div>
            <div class="text-box">
              <h6 class="tit">경영</h6>
              <span class="txt count-ani">${emplyCnt.gnrlJobCnt}</span>
            </div>
            <div class="text-box">
              <h6 class="tit">디자인</h6>
              <span class="txt count-ani">${emplyCnt.dsgnrCnt}</span>
            </div>
            <div class="text-box">
              <h6 class="tit">개발자</h6>
              <span class="txt count-ani">${emplyCnt.dvlprCnt}</span>
            </div>
            <div class="text-box">
              <h6 class="tit">프리랜서</h6>
              <span class="txt count-ani">${emplyCnt.freeCnt}</span>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-inner-3">
          <div class="title-wrap">
            <h6 class="tit">손익/매출현황</h6>
          </div>
          <div class="cont">
            <div class="wrapper">
              <div class="left" style="width: 45%">
                <p class="sub-tit">손익현황</p>
                <div class="text-box" style="text-align: left">
                  <h6 class="txt-xs tit">목표</h6>
                  <h5 class="count-ani" data-unit="만원">100,000</h5>
                </div>
                <div class="text-box" style="text-align: left">
                  <h6 class="txt-xs tit">전년대비</h6>
                  <!-- '+' 기호: 'plus' 클래스 추가 -->
                  <!-- '-' 기호: 'minus' 클래스 추가 -->
                  <h5 class="count-ani up plus" data-unit="만원">50,000</h5>
                </div>
                <div
                        id="plAchvRateGraph"
                        style="width: 100%; height: 112px"
                ></div>
              </div>
              <div class="right" style="width: 45%">
                <p class="sub-tit">매출현황</p>
                <div class="wrapper">
                  <div class="text-box" style="text-align: left">
                    <h6 class="txt-xs tit">목표</h6>
                    <h5 class="count-ani" data-unit="만원">300,000</h5>
                  </div>
                  <div class="text-box" style="text-align: left">
                    <h6 class="txt-xs tit">전년대비</h6>
                    <h5 class="count-ani down minus" data-unit="만원">
                      25,000
                    </h5>
                  </div>
                </div>
                <div
                        id="slAchvRateGraph"
                        style="width: 100%; height: 112px"
                ></div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-inner-3">
          <div class="title-wrap">
            <h6 class="tit">거래처별 매출현황</h6>
          </div>
          <div class="cont">
            <div
                    id="cstmSalesGraph"
                    style="width: 100%; height: 242px"
            ></div>
          </div>
        </div>
        <div class="col-inner-6">
          <div class="title-wrap">
            <h6 class="tit">재무제표 현황</h6>
          </div>
          <div class="cont">
            <div
                    id="finStateGraph"
                    style="width: 100%; height: 240px"
            ></div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-inner-6">
          <div class="title-wrap">
            <h6 class="tit">사원 & 기술자 보유 현황</h6>
          </div>
          <div class="cont">
            <div class="wrapper">
              <div class="left">
                <div
                        id="manPowerGraph"
                        style="width: 100%; height: 192px"
                ></div>
              </div>
              <div class="right">
                <div
                        id="techHoldGraph"
                        style="width: 100%; height: 192px"
                ></div>
              </div>
            </div>
          </div>
        </div>
        <#--<div class="col-inner-3">
          <div class="title-wrap">
            <h6 class="tit">사원 현황</h6>
          </div>
          <div class="cont">
            <div class="graph-wrap">
              <div
                id="manPowerGraph"
                style="width: 100%; height: 192px"
              ></div>
            </div>
          </div>
        </div>
        <div class="col-inner-3">
          <div class="title-wrap">
            <h6 class="tit">기술자 보유 현황</h6>
          </div>
          <div class="cont">
            <div class="graph-wrap">
              <div
                id="techHoldGraph"
                style="width: 100%; height: 192px"
              ></div>
            </div>
          </div>
        </div>-->
        <div class="col-inner-3">
          <div class="title-wrap">
            <h6 class="tit">부서별 인원 현황</h6>
          </div>
          <div class="cont">
            <div class="graph-wrap">
              <div
                      id="deptMpGraph"
                      style="width: 100%; height: 192px"
              ></div>
            </div>
          </div>
        </div>
        <div class="col-inner-3">
          <div class="title-wrap">
            <h6 class="tit">근속년수 현황</h6>
          </div>
          <div class="cont">
            <p class="caption">
              <i class="ri-information-fill"></i> 해당년도 자료입니다.
            </p>
            <div
                    id="workYearsGraph"
                    style="width: 100%; height: 162px"
            ></div>
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
<script src="/js/front/managementAnalysis/managementAnalysis.js"></script>
<script src="/js/front/managementAnalysis/managementAnalysisChart.js"></script>
