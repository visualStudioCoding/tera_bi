<#include "*/common/admin/navigation.ftl"/>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    주가지수-코스닥 종합지수
                </div>
                <form name="formCompositeIndex">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example" disabled>
                            <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                        </select>
                        <input type="hidden" name="statsCode" value="108001" />
                    </div>
                </form>
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnCompositeIndex()">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    출생 사망 추이
                </div>
                <form name="formBirthDeath">
                    <div class="card-body">
                        <span>년도 선택</span>
                        <select class="form-select" name="prdDe" aria-label="Default select example" disabled>
                            <option selected>해당 API는 년도를 선택할 수 없습니다.</option>
                        </select>
                        <input type="hidden" name="statsCode" value="101101" />
                    </div>
                </form>
                <button type="button" id="MonthlyExchangeRate" class="btn btn-outline-secondary activator" onclick="fnBirthDeath()">실행</button>
            </div>
        </div>
    </div>
</div>
<#include "*/common/admin/footer.ftl"/>

<script src="/js/admin/stockPrices/stockPrices.js"></script>


