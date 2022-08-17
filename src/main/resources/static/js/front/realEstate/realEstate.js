/******************** 부동산시장동향 *********************/
let errorMsg = "데이터 호출 에러";

window.onload = function () {
    fnAptSalesStatus();
    fnAgeTrade();
    fnBuiltYear();
    fnRegionPopulation();
    fnUnsoldCnsmr();
    fnOwnerByGender();
    fnOwnerByAge();
}

$("#termSetting").click(function () {
    fnAptSalesStatus();
    fnAgeTrade();
    fnBuiltYear();
    fnRegionPopulation();
    fnUnsoldCnsmr();
    fnOwnerByGender();
    fnOwnerByAge();
});


// 행정구역별 매매거래
function fnAptSalesStatus() {
    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        if (data.result === "Success") {
            fnAdmDivTradeChart(data);
        } else {
            if ($("#nullCkAptSales").length <= 0) {
                echarts.dispose(document.getElementById("admDivTradeGraph"));
                $("#admDivTradeGraph").append("<p id='nullCkAptSales'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }
    }
    commonAjax("/front/realEstate/api/aptSalesStatus", callBackFn, "get", param, errorMsg);

}

// 연령대별 매매거래
function fnAgeTrade() {

    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        if (data.result === "Fail") {
            if ($("#nullCkAgeTrade").length <= 0) {
                echarts.dispose(document.getElementById("ageTradeGraph"));
                $("#ageTradeGraph").removeClass("graph-wrap")
                $("#ageTradeGraph").append("<p id='nullCkAgeTrade'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        } else {
            if($("#ageTradeGraph").hasClass("graph-wrap")){
                fnAgeTradeGraphOp(data);
            }else{
                $("#ageTradeGraph").addClass("graph-wrap");
                fnAgeTradeGraphOp(data);
            }
        }
    }
    commonAjax("/front/realEstate/api/ageAptSales", callBackFn, "get", param, errorMsg);

}

// 건축년수별 매매거래
function fnBuiltYear() {
    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        console.log(data)
        if (data.result === "Success") {
            if($("#constTradeGraph").hasClass("graph-wrap")){
                fnConstTradeGraphOp(data);
            }else{
                $("#constTradeGraph").addClass("graph-wrap");
                fnConstTradeGraphOp(data);
            }
        } else {
            if ($("#nullCkBuiltYear").length <= 0) {
                echarts.dispose(document.getElementById("constTradeGraph"));
                $("#constTradeGraph").removeClass("graph-wrap")
                $("#constTradeGraph").append("<p id='nullCkBuiltYear'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }
    }
    commonAjax("/front/realEstate/api/builtYear", callBackFn, "get", param, errorMsg);

}

// 지역별 인구수
function fnRegionPopulation() {
    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        if (data.result === "Success") {
            fnPopRegionGraphOp(data);
        } else {
            if ($("#nullCkRegPop").length <= 0) {
                echarts.dispose(document.getElementById("popRegionGraph"));
                $("#popRegionGraph").append("<p id='nullCkRegPop'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }
    }
    commonAjax("/front/realEstate/api/regionPopulation", callBackFn, "get", param, errorMsg);

}

// 소비자 물가 상승률 및 미분양 주택 수
function fnUnsoldCnsmr() {
    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        if (data.result === "Success") {
            fnCMHousingChartDom(data);
        } else {
            if ($("#nullCkUnsold").length <= 0) {
                echarts.dispose(document.getElementById("cmHousingGraph"));
                $("#cmHousingGraph").append("<p id='nullCkUnsold'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        }
    }
    commonAjax("/front/realEstate/api/unsoldAndCnsmr", callBackFn, "get", param, errorMsg);

}

// 성별 부동산 소유자 수
function fnOwnerByGender() {
    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        console.log(data);
        if (data[0] === "Fail") {
            console.log("text")
            if ($("#nullCkOwnerGender").length <= 0) {
                console.log("test")
                echarts.dispose(document.getElementById("ownerByGenderGraph"));
                $("#ownerByGenderGraph").append("<p id='nullCkOwnerGender'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        } else {
            fnPopulationByGenderChartDom(data);
        }
    }
    commonAjax("/front/realEstate/api/ownerByGender", callBackFn, "get", param, errorMsg);

}

// 연령대별 부동산 소유자 수
function fnOwnerByAge() {
    let period = $("input[name=term]:checked").val();

    if (period === 'on') {
        period = $("input[name=termDatePicker]").val();
    }
    let param = {parameter: period}

    let callBackFn = function (data) {
        console.log(data);
        if (data[0]=== "Fail") {
            if ($("#nullCkOwnerAge").length <= 0) {
                echarts.dispose(document.getElementById("ownerByAgeGraph"));
                $("#ownerByAgeGraph").append("<p id='nullCkOwnerAge'>해당하는 기간에 데이터가 존재하지 않습니다.<br>기간을 다시 설정해주세요</p>")
            }
        } else {
            fnOwnerByAgeChartDom(data);
        }
    }
    commonAjax("/front/realEstate/api/ownerByAge", callBackFn, "get", param, errorMsg);

}
