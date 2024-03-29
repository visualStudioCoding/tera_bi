/******************** 경제성장지표 *********************/
let errorMsg = "데이터 호출 에러";

function getExchangeRate() {
    let current;
    let past;
    let diff;

    let callBackFn = function (data) {
        // console.log(data.current)
        // console.log(data.past)
        // console.log(data.differ)

        current = data.current
        past = data.past
        diff = data.differ

        $("#currentExChange").text(current)

        // document.getElementById("currentExChange").value = current
        // $("#currentExChange").innerHTML+='<h1>TEST TEST</h1>'
    }

    getApiResult("/front/economicGrowth/api/getExchangeRate", callBackFn, "get", null, errorMsg);

}

// 데이터 호출 함수
window.onload = function () {
    getEnmcGrrt();
    getCovidEconomicGrowth();
    getStateDebtSetPeriod();
    getInflChart();
}

$("#termSetting").click(function () {
    getStateDebtSetPeriod();
    getInflChart();
    getEnmcGrrt();
});


// 경제성장률 AJAX
function getEnmcGrrt() {

    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }

    let param = {parameter: period}
    let callBackFn = function (data) {

        if (data.result === "Success") {
            fnRegionChartOp(data);
        } else {
            if($("#nullCkEconomyGrrt").length <= 0) {
                echarts.dispose(document.getElementById("regionGrowthGraph"));
                $("#regionGrowthGraph").append("<p id='nullCkEconomyGrrt'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }
    }
    commonAjax("/front/economicGrowth/api/getEconomicGrowth", callBackFn, "get", param, errorMsg);
}

// 코로나 시기 경제성장률 AJAX
function getCovidEconomicGrowth() {

    let callBackFn = function (data) {
        fnCovidChartOp(data);
    }
    commonAjax("/front/economicGrowth/api/getCovidEconomicGrowth", callBackFn, "get", null, errorMsg);
}

// 1인당 국민 총 소득 및 국가 채무 현황 기간설정 AJAX
function getStateDebtSetPeriod() {

    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        console.log(data)
        if (data.result === "Success") {
            fngdpDeptGraphOp(data);
        } else {
            if($("#nullCkStateDebt").length <= 0) {
                echarts.dispose(document.getElementById("gdpDeptGraph"));
                $("#gdpDeptGraph").append("<p id='nullCkStateDebt'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }

    }
    commonAjax("/front/economicGrowth/api/getStateDebt", callBackFn, "get", param, errorMsg);
}

// 물가상승률 AJAX
function getInflChart() {

    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}
    let callBackFn = function (data) {
        if(data[0][0] === "Fail"){
            if($("#nullCkInfl").length <= 0) {
                echarts.dispose(document.getElementById("inflationGraph"));
                $("#inflationGraph").append("<p id='nullCkInfl'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }else {
            run(data);
        }
    }
    commonAjax("/front/economicGrowth/api/getInflationRatePeriod", callBackFn, "get", param, errorMsg);
}
