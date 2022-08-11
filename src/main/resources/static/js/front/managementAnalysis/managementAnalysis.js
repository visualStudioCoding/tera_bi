const errorMsg = "데이터 호출 실패";

// 데이터 호출 함수
window.onload = function () {
    getEmployeeStatus();
    getTechnicalList();
    getDepartmentList();
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



