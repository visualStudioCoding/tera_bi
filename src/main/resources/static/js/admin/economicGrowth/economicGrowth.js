let errorMsg = "api 호출 에러";
let enaraParam = {"statsCode": "106801"}

function getKosisParam(form) {
    let kosisParam;

    if (form == document.forms["getInflationRate_opt"]){
        let month;
        if(form.prdDe_M.value < 10){
            month = "0" + form.prdDe_M.value
        }else{
            month = form.prdDe_M.value
        }

        kosisParam = {
            "itmId": form.itmId.value,
            "objL1": form.objL1.value,
            "objL2": "",
            "objL3": "",
            "objL4": "",
            "objL5": "",
            "objL6": "",
            "objL7": "",
            "objL8": "",
            "prdSe": form.prdSe.value,
            "startPrdDe": "",
            "endPrdDe": "",
            "newEstPrdCnt": "",
            "loadGubun": form.loadGubun.value,
            "orgId": form.orgId.value,
            "tblId": form.tblId.value
        }
    }else {
        kosisParam = {
            "itmId": form.itmId.value,
            "objL1": form.objL1.value,
            "objL2": "",
            "objL3": "",
            "objL4": "",
            "objL5": "",
            "objL6": "",
            "objL7": "",
            "objL8": "",
            "prdSe": form.prdSe.value,
            "startPrdDe": form.prdDe.value,
            "endPrdDe": form.prdDe.value,
            "newEstPrdCnt": "",
            "loadGubun": form.loadGubun.value,
            "orgId": form.orgId.value,
            "tblId": form.tblId.value
        }
    }
    return kosisParam;
}

function getKosisParams(form, years, months) {
    let month;
    if(months < 10){
        month = "0" + months
    }else{
        month = months
    }

    let kosisParam = {
        "itmId": form.itmId.value,
        "objL1": form.objL1.value,
        "objL2": "",
        "objL3": "",
        "objL4": "",
        "objL5": "",
        "objL6": "",
        "objL7": "",
        "objL8": "",
        "prdSe": form.prdSe.value,
        "startPrdDe": years + month,
        "endPrdDe": years + month,
        "newEstPrdCnt": "",
        "loadGubun": form.loadGubun.value,
        "orgId": form.orgId.value,
        "tblId": form.tblId.value
    }
    return kosisParam;
}

function getEcosParam(form){
    // const startDtBf = document.querySelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div > form > div > div:nth-child(1) > input[type=date]").value;
    const startDtBf = form.startDt.value
    const endDtBf = form.endDt.value
    // const endDtBf = document.querySelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div > form > div > div:nth-child(2) > input[type=date]").value;

    let startDt = startDtBf.replace(/\-/g,"")
    let endDt = endDtBf.replace(/\-/g,"")

    let ecosParam = form.serviceNm.value
                + "/apiKey/" + form.resultType.value
                + "/kr/1/10000/" + form.tableCode.value
                + "/" + form.cycle.value + "/" + startDt + "/" + endDt + "/"
                + form.itmCode.value + "/?/?/?"

    return ecosParam;
}

function getBaseRate() {

    let formData = document.forms["getBaseRate_opt"];

    let params = getEcosParam(formData);

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    ecosApiAjax("/admin/economicGrowth/api/getBaseRate", callBackFn, 'get', params, errorMsg);
}

function getExchangeRate() {

    let formData = document.forms["getExchangeRate_opt"];

    let params = getEcosParam(formData);

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    ecosApiAjax("/admin/economicGrowth/api/getExchangeRate", callBackFn, 'get', params, errorMsg);
}

function getStateDebt() {
    let formData = document.forms["stateDebt_opt"]

    let params = getKosisParam(formData);

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    kosisApiAjax("/admin/economicGrowth/api/getStateDebt", callBackFn, 'get', params, errorMsg);
}

function getGdiAndGni() {
    let formData = document.forms["gdpAndgni_opt"]

    let params = getKosisParam(formData)

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    kosisApiAjax("/admin/economicGrowth/api/getGdiAndGni", callBackFn, 'get', params, errorMsg);
}

function getGDP() {
    let formData = document.forms["getGDP_opt"];

    let params = getEcosParam(formData);

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    ecosApiAjax("/admin/economicGrowth/api/getGdp", callBackFn, 'get', params, errorMsg);
}

function getGrowthRateInternational() {
    let formData = document.forms["growthRateInternational_opt"];

    let params = getEcosParam(formData);

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    ecosApiAjax("/admin/economicGrowth/api/getGrowthRateInternational", callBackFn, 'get', params, errorMsg);
}

function getPriceIncreaseInternational() {
    let formData = document.forms["priceIncreaseInternational_opt"];

    let params = getEcosParam(formData);

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    ecosApiAjax("/admin/economicGrowth/api/getPriceIncreaseInternational", callBackFn, 'get', params, errorMsg);
}

function getGrowthRate(){
    let formData = document.forms["growthRate_opt"]

    let params = getKosisParam(formData)

    let callBackFn = function(data){
        alert(data.success)
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    kosisApiAjax("/admin/economicGrowth/api/getGrowthRate", callBackFn, 'get', params, errorMsg);
}

function getInflationRate(){
    let formData = document.forms["getInflationRate_opt"]

    let params = getKosisParam(formData)

    let callBackFn = function(data){
        alert(data.success)
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    kosisApiAjax("/admin/economicGrowth/api/getInflationRate", callBackFn, 'get', params, errorMsg);
}


function getLargeData(){
    let years = [];
    let month = [];
    let formData = document.forms["getInflationRate_opt"]

    for(var i = 1966; i <= 2022; i++){
        years.push(i)
    }
    for(var j = 1 ; j <=12; j++){
        month.push(j);
    }

    for(var k = 0; k < years.length; k++){
        for(var l = 0 ; l < month.length; l++){
            if(years[k] == "2022" && month[l] == "6"){
                break;
            }

            let params = getKosisParams(formData, years[k], month[l])

            let callBackFn = function(data){
                alert(data.success)
                console.log(data.data)
            }
            console.log(params)
            //공통모듈 ajax 함수 호출하기
            // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
            kosisApiAjax("/economicGrowth/api/getInflationRate", callBackFn, 'get', params, errorMsg);
        }
    }


}