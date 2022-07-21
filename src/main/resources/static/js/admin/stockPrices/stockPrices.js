let errorMsg = "api 호출 에러";

function fnParam(frm, site){
    let param;
    if(site === "enara") param = {"statsCode": frm.statsCode.value};
    if(site === "ecos") {
        param = "StatisticSearch/apiKey/json/kr/1/10000/"+frm.tableCode.value+"/D/19800101/20220721/"+frm.categoryCode.value+"/?/?/?";
    }
    return param;
    // return enaraParam = {"statsCode": "106801"}
}
function fnCompositeIndex(){
    const frm = document.forms['formCompositeIndex'];
    let param = fnParam(frm, "ecos");
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    ecosApiAjax("/admin/stockPrices/api/compositeIndex", callBackFn, 'get', param, errorMsg);
}

function fnBirthDeath() {
    const frm = document.forms['formBirthDeath'];
    let param = fnParam(frm, "enara");
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    enaraApiAjax("/admin/stockPrices/api/birthDeath", callBackFn, 'get', param, errorMsg);
}