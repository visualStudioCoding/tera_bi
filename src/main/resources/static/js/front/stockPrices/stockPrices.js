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
    // countAnimation();

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
        console.log(data)
        if(data[0] === "Fail"){
            if($("#nullCkGdp").length <= 0) {
                echarts.dispose(document.getElementById("gdpGraph"));
                $("#gdpGraph").append("<p id='nullCkGdp'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }else{
            fnGdpChart(data);
        }

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
        if(data.result === "Success") {
            fnBaseRateAndKospi(data);
        }else{
            if($("#nullCkBaseKospi").length <= 0) {
                echarts.dispose(document.getElementById("baseRateKospiGraph"));
                $("#baseRateKospiGraph").append("<p id='nullCkBaseKospi'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }
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
        if(data.result === "Success") {
            fnInflYearChart(data);

        }else{
            if($("#nullCkInfYear").length <= 0) {
                echarts.dispose(document.getElementById("inflationYearlyGraph"));
                $("#inflationYearlyGraph").append("<p id='nullCkInfYear'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }
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
    let baseDateId = $('#' + id + 'BaseDate');

    pointId.addClass('count-ani-per');

    let current = data.current;
    let past = data.past;
    let subtraction = data.subtraction;
    let subRate = data.subRate;
    let baseDate = data.baseDate;

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

    baseDateId.text(' 기준일 : ' + baseDate);

}
*/



