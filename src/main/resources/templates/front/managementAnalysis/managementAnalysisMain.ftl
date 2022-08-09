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
      <form class="box">
        <div class="tit">기간설정</div>
        <div class="input-group">
          <input
            type="radio"
            name="ecGrowthTerm"
            id="ecGrowthTerm1"
            checked
          />
          <label for="ecGrowthTerm1">5년</label>
          <input type="radio" name="ecGrowthTerm" id="ecGrowthTerm2" />
          <label for="ecGrowthTerm2">10년</label>
          <input type="radio" name="ecGrowthTerm" id="ecGrowthDatePick" />
          <label for="ecGrowthDatePick">기간선택</label>
        </div>
        <div class="date-picker-group">
          <input
            type="text"
            name="ecGrowthDatePicker"
            class="w-100"
            disabled
          />
          <i class="ri-calendar-line"></i>
        </div>
        <button type="button" class="btn-primary w-100">
          <i class="ri-check-line"></i>적용
        </button>
      </form>
      <form class="box">
        <div class="tit">지역설정</div>
        <div class="input-group">
          <input type="radio" name="ecLoc" id="ecLocAll" checked />
          <label for="ecLocAll">전국</label>
          <input type="radio" name="ecLoc" id="ecLocSlcts" />
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
        <div class="col-inner-12" style="height: 90px">
          <div class="wrapper text-boxes row">
            <div class="text-box">
              <h6 class="tit">인원수</h6>
              <span class="txt count-ani">58</span>
            </div>
            <div class="text-box">
              <h6 class="tit">임원수</h6>
              <span class="txt count-ani">2</span>
            </div>
            <div class="text-box">
              <h6 class="tit">여성인원수</h6>
              <span class="txt count-ani">20</span>
            </div>
            <div class="text-box">
              <h6 class="tit">일반직</h6>
              <span class="txt count-ani">4</span>
            </div>
            <div class="text-box">
              <h6 class="tit">디자인</h6>
              <span class="txt count-ani">5</span>
            </div>
            <div class="text-box">
              <h6 class="tit">개발자</h6>
              <span class="txt count-ani">30</span>
            </div>
            <div class="text-box">
              <h6 class="tit">파견직</h6>
              <span class="txt count-ani">32</span>
            </div>
            <div class="text-box">
              <h6 class="tit">본사</h6>
              <span class="txt count-ani">26</span>
            </div>
            <div class="text-box">
              <h6 class="tit">프리랜서</h6>
              <span class="txt count-ani">3</span>
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
        <div class="col-inner-3">
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
        </div>
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

<script src="/js/front/managementAnalysis/managementAnalysisChart.js"></script>