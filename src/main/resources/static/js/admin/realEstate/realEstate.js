let errorMsg = "error";
function fnParam(frm){
    return kosisParam = {
        "itmId": frm.itmId.value,
        "objL1": frm.objL1.value,
        "objL2": frm.objL2.value,
        "objL3": frm.objL3.value,
        "objL4": "",
        "objL5": "",
        "objL6": "",
        "objL7": "",
        "objL8": "",
        "prdSe": frm.prdSe.value,
        "startPrdDe": frm.prdDe.value,
        "endPrdDe": frm.prdDe.value,
        "newEstPrdCnt" : "",
        "loadGubun": frm.loadGubun.value,
        "orgId": frm.orgId.value,
        "tblId": frm.tblId.value
    }
}
function fnGenderPopulation(){
    // const prdDeYear = $("#genderPrdDeYear").val();
    //const prdDeMonth = $("#genderPrdDeMonth").val();
    // $("#prdDe").val(prdDeYear);
    const frm = document.forms['formGenderPopulation'];
    let param = fnParam(frm);
    // getIncome();
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/admin/realEstate/api/genderPopulation", callBackFn, 'get', param, errorMsg);
}
function fnAptSalesStatus(){
    const frm = document.forms['formAptSalesStatus'];
    let param = fnParam(frm);
    // getIncome();
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/admin/realEstate/api/aptSalesStatus", callBackFn, 'get', param, errorMsg);
}
function fnUnsoldHouse(){
    const frm = document.forms['formUnsoldHouse'];
    let param = fnParam(frm);
    // getIncome();
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/admin/realEstate/api/unsoldHouse", callBackFn, 'get', param, errorMsg);
}
function fnAgeAptSales(){
    const frm = document.forms['formAgeAptSales'];
    let param = fnParam(frm);
    // getIncome();
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/admin/realEstate/api/ageAptSales", callBackFn, 'get', param, errorMsg);
}
function fnPopulationAge(){
    const frm = document.forms['formPopulationAge'];
    let param = fnParam(frm);
    // getIncome();
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        console.log(data.data)
        alert(data.success);
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/admin/realEstate/api/populationAge", callBackFn, 'get', param, errorMsg);
}
function fnGrp(){
    const frm = document.forms['formGrp'];
    let param = fnParam(frm);
    // getIncome();
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/admin/realEstate/api/grp", callBackFn, 'get', param, errorMsg);
}
function fnApartmentOwner(){
    const frm = document.forms['formApartmentOwner'];
    let param = fnParam(frm);
    // getIncome();
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/admin/realEstate/api/apartmentOwner", callBackFn, 'get', param, errorMsg);
}


