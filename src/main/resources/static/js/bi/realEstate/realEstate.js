let errorMsg = "error";
function fnParam(frm){
    return kosisParam = {
        "itmId": frm.itmId.value,
        "objL1": frm.objL1.value,
        "objL2": "",
        "objL3": "",
        "objL4": "",
        "objL5": "",
        "objL6": "",
        "objL7": "",
        "objL8": "",
        "prdSe": frm.prdSe.value,
        "startPrdDe": frm.startPrdDe.value,
        "endPrdDe": frm.endPrdDe.value,
        "newEstPrdCnt" : frm.newEstPrdCnt.value,
        "loadGubun": frm.loadGubun.value,
        "orgId": frm.orgId.value,
        "tblId": frm.tblId.value
    }
}
function fnGenderPopulation(){
    const frm = document.forms['formGenderPopulation'];
    let param = fnParam(frm);
    // getIncome();
    //ajax가 정상 호출 되었을때 실행 되는 함수
    let callBackFn = function( data ) {
        alert(data.success);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/realEstate/api/genderPopulation", callBackFn, 'get', param, errorMsg);
}



