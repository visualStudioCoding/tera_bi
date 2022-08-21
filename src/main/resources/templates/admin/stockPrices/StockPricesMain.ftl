<#include "*/common/admin/header.ftl">

<body>
<#include "*/common/admin/navigation.ftl"/>

<#assign nowDate = .now/>
<div class="contents">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>주가지수-코스닥 종합지수</h5>
                </div>
                <hr>
                <form name="formCompositeIndex">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" aria-label="Default select example" disabled>
                                    <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                                </select>
                            </div>
                            <input type="hidden" name="tableCode" value="802Y001"/>
                            <input type="hidden" name="categoryCode" value="0001000"/>
                            <input type="hidden" name="periodCode" value="D"/>
                            <input type="hidden" name="starPrdDe" value="19800101"/>
                            <input type="hidden" name="endPrdDe" value="${nowDate?string("yyyyMMdd")}"/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnCompositeIndex()">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>두바이유 배럴당 달러</h5>
                </div>
                <hr>
                <form name="formOilPrice">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" aria-label="Default select example" disabled>
                                    <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                                </select>
                            </div>
                            <input type="hidden" name="tableCode" value="902Y003"/>
                            <input type="hidden" name="categoryCode" value="4010102"/>
                            <input type="hidden" name="periodCode" value="M"/>
                            <input type="hidden" name="starPrdDe" value="198601"/>
                            <input type="hidden" name="endPrdDe" value="${nowDate?string("yyyyMM")}"/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" class="btn btn-primary w-100" onclick="fnOilPrice()">실행
                </button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h5>출생 사망 추이</h5>
                </div>
                <hr>
                <form name="formBirthDeath">
                    <div class="card-body">
                        <div class="col">
                            <div class="row">
                                <span>년도 선택</span>
                            </div>
                            <div class="row">
                                <select class="form-select" name="prdDe" aria-label="Default select example"
                                        disabled>
                                    <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                                </select>
                            </div>
                            <input type="hidden" name="statsCode" value="101101"/>
                        </div>
                    </div>
                </form>
                <hr>
                <button type="button" id="MonthlyExchangeRate" class="btn btn-primary w-100"
                        onclick="fnBirthDeath()">실행
                </button>
            </div>
        </div>
    </div>
</div>
</div>
<#include "*/common/admin/footer.ftl"/>
</body>
<script src="/js/admin/stockPrices/stockPrices.js"></script>


