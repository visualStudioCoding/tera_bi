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
    let ecosParam = form.apiType.value

    return ecosParam;
}

function getMonthlyExchangeRate() {

    let formData = document.forms["getMonthlyExchangeRate_opt"];

    let params = getEcosParam(formData);

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    ecosApiAjax("/economicGrowth/api/getMonthlyExchangeRate", callBackFn, 'get', params, errorMsg);
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
    kosisApiAjax("/economicGrowth/api/getStateDebt", callBackFn, 'get', params, errorMsg);
}

function getGdpAndGni() {
    let formData = document.forms["gdpAndgni_opt"]

    let params = getKosisParam(formData)

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    kosisApiAjax("/economicGrowth/api/getGdpAndGni", callBackFn, 'get', params, errorMsg);
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
    kosisApiAjax("/economicGrowth/api/getGrowthRate", callBackFn, 'get', params, errorMsg);
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
    kosisApiAjax("/economicGrowth/api/getInflationRate", callBackFn, 'get', params, errorMsg);
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