/******************** 부동산시장동향 *********************/
let errorMsg = "데이터 호출 에러";

window.onload = function(){
    fnAptSalesStatus();
    fnAgeTrade();
    fnBuiltYear();
    fnRegionPopulation();
    fnUnsoldCnsmr();
    fnPopulationByGender();
}

$("#termSetting").click(function(){
    fnAptSalesStatus();
    fnAgeTrade();
    fnBuiltYear();
    fnRegionPopulation();
    fnUnsoldCnsmr();
    fnPopulationByGender();
});


// 행정구역별 매매거래
function fnAptSalesStatus(){
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        fnAdmDivTradeChart(data);
    }
    commonAjax("/front/realEstate/api/aptSalesStatus", callBackFn, "get", param, errorMsg);

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
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        fnConstTradeGraphOp(data);
    }
    commonAjax("/front/realEstate/api/builtYear", callBackFn, "get", param, errorMsg);

}

// 지역별 인구수
function fnRegionPopulation(){
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        fnPopRegionGraphOp(data);
    }
    commonAjax("/front/realEstate/api/regionPopulation", callBackFn, "get", param, errorMsg);

}

// 소비자 물가 상승률 및 미분양 주택 수
function fnUnsoldCnsmr(){
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        console.log(data)
        fnCMHousingChartDom(data);
    }
    commonAjax("/front/realEstate/api/unsoldAndCnsmr", callBackFn, "get", param, errorMsg);

}

// 소비자 물가 상승률 및 미분양 주택 수
function fnPopulationByGender(){
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        console.log(data)
        fnPopulationByGenderChartDom(data);
    }
    commonAjax("/front/realEstate/api/populationByGender", callBackFn, "get", param, errorMsg);

}
