<#include "*/common/admin/header.ftl">
<#assign nowDate = .now/>

<body>
<#include "*/common/admin/navigation.ftl"/>
<div class="contents">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>재무제표 (자산)</h5>
                </div>
                <hr>
                <div class="card-body">
                    <form>
                        <div class="col">
                            <div class="row">
                                <span>년도</span>
                            </div>
                            <div class="row">
                                <input type="number" min="2019" max="2030">
                            </div>
                            <div class="row">
                                <span>총자산</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>자본금</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>자기자본</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>유동부채</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>부채총액</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>유동자산</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                        </div>
                    </form>
                    <hr>
                    <button type="button" class="btn btn-primary w-100">확인
                    </button>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>재무제표 (매출)</h5>
                </div>
                <hr>
                <div class="card-body">
                    <form>
                        <div class="col">
                            <div class="row">
                                <span>년도</span>
                            </div>
                            <div class="row">
                                <input type="number" min="2019" max="2030">
                            </div>
                            <div class="row">
                                <span>컨설팅 부문</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>시스템개발 부문</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>유지관리 부문</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>응용S/W 부문</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                        </div>
                    </form>
                    <hr>
                    <button type="button" class="btn btn-primary w-100">확인
                    </button>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>손익 / 매출현황</h5>
                </div>
                <hr>
                <div class="card-body">
                    <form>
                        <div class="col">
                            <div class="row">
                                <span>목표 손익</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>현재 손익</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>목표 매출</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>현재 매출</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                        </div>
                    </form>
                    <hr>
                    <button type="button" class="btn btn-primary w-100">확인
                    </button>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>거래처별 매출</h5>
                </div>
                <hr>
                <div class="card-body">
                    <form class="addFormsCurrent">
                        <div class="col" id="defaultForm">
                            <div class="row">
                                <span>거래처</span>
                                <div style="margin-left: 75%;">
                                    <#--                                <button type="button" class="subContents"><i class="ri-subtract-line"></i></button>-->
                                    <button type="button" class="addContents" onclick="addFormCurrent()"><i
                                                class="ri-add-line"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                <#--                                <#assign clientsList = "${clients}">-->
                                <select>
                                    <option>거래처를 선택해주세요</option>
                                    <#list clients as cli>
                                        <option>${cli}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="row">
                                <span>거래 년도</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>거래 횟수</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>매출</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                        </div>
                    </form>
                    <hr>
                    <button type="button" class="btn btn-primary w-100">확인
                    </button>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>신규 거래처 등록</h5>
                </div>
                <hr>
                <div class="card-body">
                    <form class="addFormsNew">
                        <div class="col" id="defaultForm">
                            <div class="row">
                                <span>거래처</span>
                                <div style="margin-left: 75%;">
                                    <#--                                <button type="button" class="subContents"><i class="ri-subtract-line"></i></button>-->
                                    <button type="button" class="addContents" onclick="addFormNew()">
                                        <i class="ri-add-line"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                <#--                                <#assign clientsList = "${clients}">-->
                                <input type="text">
                            </div>
                            <div class="row">
                                <span>거래 년도</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>거래 횟수</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                            <div class="row">
                                <span>매출</span>
                            </div>
                            <div class="row">
                                <input type="text" onkeyup="inputCommaValues(this)">
                            </div>
                        </div>
                    </form>
                    <hr>
                    <button type="button" class="btn btn-primary w-100">확인
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<#include "*/common/admin/footer.ftl"/>
<script src="/js/admin/managementAnalysis/managementAnalysis.js"></script>
<script type="text/javascript">
    let variables = [];
    <#list clients as cli>
    variables.push("${cli}");
    </#list>
</script>

