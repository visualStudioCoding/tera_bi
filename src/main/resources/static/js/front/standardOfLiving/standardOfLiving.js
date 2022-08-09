let errorMsg = "데이터 호출 에러";

window.onload = function(){
    getInflationYear();
    getInflationPresident();
    getGiniCoefficient();
    getIncomePriceRate();
    getEconomicGrowth();
    getNationalPriceIncrease();
    getOverseaTrip();

}

$("#termSetting").click(function(){
    getInflationYear();
    getInflationPresident();
    getGiniCoefficient();
    getIncomePriceRate();
    getEconomicGrowth();
    getNationalPriceIncrease();
    getOverseaTrip();
});

// 임금 대비 물가상승률
function getIncomePriceRate() {
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }

    let param = {parameter: period}
    let callBackFn = function (data) {
        fnWageInflChartDom(data);
    }
    commonAjax("/front/standardOfLiving/api/getIncomePriceRate", callBackFn, "get", param, errorMsg);
}

//지역별 경제성장률  순위
function getEconomicGrowth() {
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }

    let param = {parameter: period}
    let callBackFn = function (data) {
        fnRegRankChartDom(data);
    }
    commonAjax("/front/standardOfLiving/api/getEconomicGrowth", callBackFn, "get", param, errorMsg);
}
// 우크라이나 전쟁 이후 물가상승지수
function getNationalPriceIncrease() {

    let callBackFn = function (data) {
        fnUkrInflChartDom(data);
    }
    commonAjax("/front/standardOfLiving/api/getNationalPriceIncrease", callBackFn, "get", null, errorMsg);
}

//연령별 해외여행 통계
function getOverseaTrip() {
    let period =  $("input[name=term]:checked").val();

    if(period === 'on'){
        period =  $("input[name=termDatePicker]").val();
    }

    let param = {parameter: period}
    let callBackFn = function (data) {
        fnTravelChartDom(data);
    }
    commonAjax("/front/standardOfLiving/api/getOverseaTrip", callBackFn, "get", param, errorMsg);
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