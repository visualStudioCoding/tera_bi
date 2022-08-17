const errorMsg = "데이터 호출 실패";

// 데이터 호출 함수
window.onload = function () {
    getInsightKospi();
    ///getTemp();
}

//kospi 차트
function getInsightKospi(){
    let callBackFn = function (data) {
        fnInsightKospiChart(data);
    }
    commonAjax("/front/predict/api/getKospi", callBackFn, "get", null, errorMsg);
}

// 경제성장률 AJAX
function getTemp() {
    let param = {parameter: period}
    let callBackFn = function (data) {
        fnTempChart(data);
    }
    commonAjax("/front/predict/api/getTemp", callBackFn, "get", param, errorMsg);
}