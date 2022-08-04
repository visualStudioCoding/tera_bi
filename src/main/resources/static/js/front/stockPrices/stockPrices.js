const errorMsg = "데이터 호출 실패";

// 데이터 호출 함수
window.onload = function () {
    // getKospiIndex();
    // getKosdaqIndex();
    // getOilPrice();
    // getBaseRate();
    // getExchangeRate();
    getGdp();
    getCovidKospi();
    getBaseRateAndKospi();
    getInflYear();
    countAnimation();

}
$("#termSetting").click(function(){
    getGdp();
    getInflYear();
    getBaseRateAndKospi();
});

/*
// kospi
function getKospiIndex(){
    let callBackFn = function (data) {
        fnDashboardSetting(data, 'kospi');
    }
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {term: period}
    commonAjax("/front/stockPrices/api/getKospiIndex", callBackFn, "get", param, errorMsg);
}
// kosdaq
function getKosdaqIndex(){
    let callBackFn = function (data) {
        fnDashboardSetting(data, 'kosdaq');
    }
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {term: period}
    commonAjax("/front/stockPrices/api/getKosdaqIndex", callBackFn, "get", param, errorMsg);
}
// oilPrice
function getOilPrice(){
    let callBackFn = function (data) {
        fnDashboardSetting(data, 'oil');
    }
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {term: period}
    commonAjax("/front/stockPrices/api/getOilPrice", callBackFn, "get", param, errorMsg);
}
// baseRate
function getBaseRate(){
    let callBackFn = function (data) {
        fnDashboardSetting(data, 'baseRate');
    }
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {term: period}
    commonAjax("/front/stockPrices/api/getBaseRate", callBackFn, "get", param, errorMsg);
}
// exchangeRate
function getExchangeRate(){
    let callBackFn = function (data) {
        fnDashboardSetting(data, 'exchangeRate');
    }
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {term: period}
    commonAjax("/front/stockPrices/api/getExchangeRate", callBackFn, "get", param, errorMsg);
}
*/

// GDP 차트
function getGdp(){
    let callBackFn = function (data) {
        fnGdpChart(data);
    }
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {term: period}
    commonAjax("/front/stockPrices/api/getGdpData", callBackFn, "get", param, errorMsg);
}
// 코로나 시기 kospi 차트
function getCovidKospi(){
    let callBackFn = function (data) {
        fnCovidKospiChart(data);
    }
    commonAjax("/front/stockPrices/api/getCovidKospi", callBackFn, "get", null, errorMsg);
}
// 기준금리 & kospi 차트
function getBaseRateAndKospi(){
    let callBackFn = function (data) {
        console.log(data);
        fnBaseRateAndKospi(data);
    }

    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {term: period}
    commonAjax("/front/stockPrices/api/getBaseRateAndKospi", callBackFn, "get", param, errorMsg);
}
// 연도별 경제성장률 차트
function getInflYear(){
    let callBackFn = function (data) {
        fnInflYearChart(data);
    }

    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    let param = {term: period}
    commonAjax("/front/stockPrices/api/getAnnualGrowthRate", callBackFn, "get", param, errorMsg);
}

/*
function fnDashboardSetting(data, id){

    let pointId = $('#' + id + 'Point');
    let VarianceId = $('#' + id + 'Variance');

    pointId.addClass('count-ani-per');

    let current = data.current;
    let past = data.past;
    let subtraction = data.subtraction;
    let subRate = data.subRate;

    if(current < past) {
        pointId.text(current);
        VarianceId.text('▼' + subtraction + ' ' + '(' + subRate + ')').addClass('down');
        if(id === 'kospi') $('#kospiVariance').removeClass('down')
    } else if(current === past) {
        pointId.text(current);
        VarianceId.text('변동없음');
    } else {
        pointId.text(current);
        VarianceId.text('▲' + subtraction + ' ' + '(' + subRate + ')').addClass('up');
        if(id === 'kospi') $('#kospiVariance').removeClass('up')
    }

}
*/



