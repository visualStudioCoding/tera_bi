const errorMsg = "데이터 호출 실패";

// 데이터 호출 함수
window.onload = function () {
    getGdp();
    getCovidKospi();
    // getKospi();
    // getInflYear();
}

// GDP 차트
function getGdp(){
    let callBackFn = function (data) {
        fnGdpChart(data);
    }
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }
    commonAjax("/front/economicGrowth/api/getStateDebt", callBackFn, "get", period, errorMsg);
}
// 코로나 시기 kospi 차트
function getCovidKospi(){
    let callBackFn = function (data) {
        fnCovidKospiChart(data);
    }
    commonAjax("/front/stockPrices/api/", callBackFn, "get", null, errorMsg);
}
// 기준금리 & kospi 차트
function getKospi(){
    let callBackFn = function (data) {
        fnKospiChart(data);
    }
    commonAjax("/front/stockPrices/api/", callBackFn, "get", null, errorMsg);
}
// 연도별 경제성장률 차트
function getInflYear(){
    let callBackFn = function (data) {
        fnInflYearChart(data);
    }
    commonAjax("/front/stockPrices/api/", callBackFn, "get", null, errorMsg);
}




