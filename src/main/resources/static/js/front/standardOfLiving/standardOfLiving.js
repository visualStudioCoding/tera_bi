const errorMsg = "데이터 호출 실패";
// 데이터 호출 함수
window.onload = function () {
    getInflationYear();
    getInflationPresident();
    getGiniCoefficient();
}
// 대통령별 물가상승률
function getInflationYear(){
    let callBackFn = function (data) {
       fnYearsInflChart(data);
    }
    let param = {}
    commonAjax("/front/standardOfLiving/api/getInflationYear", callBackFn, "get", param, errorMsg);
}
// 대통령별 물가상승률
function getInflationPresident(){
    let callBackFn = function (data) {
        fnPresInflChart(data);
    }
    let param = {}
    commonAjax("/front/standardOfLiving/api/getInflationPresident", callBackFn, "get", param, errorMsg);
}
// 대통령별 물가상승률
function getGiniCoefficient(){
    let callBackFn = function (data) {
        fnGiniCoefficient(data);
    }
    let param = {}
    commonAjax("/front/standardOfLiving/api/getGiniCoefficient", callBackFn, "get", param, errorMsg);
}