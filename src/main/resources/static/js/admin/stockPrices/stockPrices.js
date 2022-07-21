let errorMsg = "api 호출 에러";
function fnParam(frm){
    return enaraParam = {"statsCode": frm.statsCode.value}
    // return enaraParam = {"statsCode": "106801"}
}
function fnCompositeIndex(){
    const frm = document.forms['formCompositeIndex'];
    let param = fnParam(frm);
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    enaraApiAjax("/admin/stockPrices/api/compositeIndex", callBackFn, 'get', param, errorMsg);
}


function fnBirthDeath() {
    const frm = document.forms['formBirthDeath'];
    let param = fnParam(frm);
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    enaraApiAjax("/admin/stockPrices/api/birthDeath", callBackFn, 'get', enaraParam, errorMsg);
}