const errorMsg = "데이터 호출 실패";

// 데이터 호출 함수
window.onload = function () {
    getEmployeeStatus();
    getTechnicalList();
    getDepartmentList();
    fnClientSales();
    fnCapitalSales();
    fnWorkYear();
    fnProfitLossAndSales();
}
// 손익/매출현황
function fnProfitLossAndSales(){
    let callBackFn = function (data) {
        console.log(data)
        fnProfitLossAndSalesChart(data);
    }
    let param = {}
    commonAjax("/front/managementAnalysis/api/getProfitLossAndSales", callBackFn, "get", param, errorMsg);
}

// 사원현황
function getEmployeeStatus(){
    let callBackFn = function (data) {
        console.log(data)
        fnEmployeeStatusChart(data);
    }
    let param = {}
    commonAjax("/front/managementAnalysis/api/getEmployeeStatus", callBackFn, "get", param, errorMsg);
}
// 기술자 보유 현황
function getTechnicalList(){
    let callBackFn = function (data) {
        console.log(data);
        fnTechnicalChart(data);
    }
    commonAjax("/front/managementAnalysis/api/getTechnical", callBackFn, "get", null, errorMsg);
}
// 기술자 보유 현황
function getDepartmentList(){
    let callBackFn = function (data) {
        console.log(data);
        fnDepartmentChart(data);
    }
    commonAjax("/front/managementAnalysis/api/getDepartment", callBackFn, "get", null, errorMsg);
}

// 거래처별 매출현황
function fnClientSales() {

    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }

    let param = {parameter: period}
    let callBackFn = function (data) {
        fnCstmSalesChartDom(data)

    }
    commonAjax("/front/managementAnalysis/api/getClientSales", callBackFn, "get", param, errorMsg);
}

// 재무제표
function fnCapitalSales() {

    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }

    let param = {parameter: period}
    let callBackFn = function (data) {
        console.log(data)
        fnFinStateChartDom(data)
    }
    commonAjax("/front/managementAnalysis/api/getCapitalSales", callBackFn, "get", param, errorMsg);
}

// 근속년수 현황
function fnWorkYear() {

    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }

    let param = {parameter: period}
    let callBackFn = function (data) {
        console.log(data)
        fnWorkYearsChartDom(data)
    }
    commonAjax("/front/managementAnalysis/api/getWorkYears", callBackFn, "get", param, errorMsg);
}

function setGraphColor(){
    var colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);

    if(colorCode === "#ffffff"){
        setGraphColor();
    }
    return colorCode;
}

