let errorMsg = "api 호출 에러";
let enaraParam = {"statsCode": "106801"}

function getKosisParam(form) {

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
        "startPrdDe": form.PrdDe.value,
        "endPrdDe": form.PrdDe.value,
        "newEstPrdCnt": "",
        "loadGubun": form.loadGubun.value,
        "orgId": form.orgId.value,
        "tblId": form.tblId.value
    }
    return kosisParam;
}


function getMonthlyExchangeRate() {

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    enaraApiAjax("/economicGrowth/api/getMonthlyExchangeRate", callBackFn, 'get', enaraParam, errorMsg);
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

    let params = getKosisParam((formData))

    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
    kosisApiAjax("/economicGrowth/api/getGdpAndGni", callBackFn, 'get', params, errorMsg);
}