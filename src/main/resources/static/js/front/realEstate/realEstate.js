/******************** 부동산시장동향 *********************/
let errorMsg = "데이터 호출 에러";

window.onload = function(){
    fnAptSalesStatus();
    fnAgeTrade();
    fnBuiltYear();
    fnRegionPopulation();
}
// 행정구역별 매매거래
function fnAptSalesStatus(){
    let callBackFn = function (data) {
        fnAdmDivTradeChart(data);
    }
    commonAjax("/front/realEstate/api/aptSalesStatus", callBackFn, "get", null, errorMsg);

}

// 연령대별 매매거래
function fnAgeTrade(){
    let callBackFn = function (data) {
        fnAgeTradeGraphOp(data);
    }
    commonAjax("/front/realEstate/api/ageAptSales", callBackFn, "get", null, errorMsg);

}

// 건축년수별 매매거래
function fnBuiltYear(){
    let callBackFn = function (data) {
        fnConstTradeGraphOp(data);
    }
    commonAjax("/front/realEstate/api/builtYear", callBackFn, "get", null, errorMsg);

}

// 지역별 인구수
function fnRegionPopulation(){
    let callBackFn = function (data) {
        fnPopRegionGraphOp(data);
    }
    commonAjax("/front/realEstate/api/regionPopulation", callBackFn, "get", null, errorMsg);

}
