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
        "startPrdDe": form.prdDe.value,
        "endPrdDe": form.prdDe.value,
        "newEstPrdCnt": "",
        "loadGubun": form.loadGubun.value,
        "orgId": form.orgId.value,
        "tblId": form.tblId.value
    }
    return kosisParam;
}

function getKosisParams(form, years) {

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
        "startPrdDe": years,
        "endPrdDe": years,
        "newEstPrdCnt": "",
        "loadGubun": form.loadGubun.value,
        "orgId": form.orgId.value,
        "tblId": form.tblId.value
    }
    return kosisParam;
}

function getKosisParams(form, years) {

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
        "startPrdDe": years,
        "endPrdDe": years,
        "newEstPrdCnt": "",
        "loadGubun": form.loadGubun.value,
        "orgId": form.orgId.value,
        "tblId": form.tblId.value
    }
    return kosisParam;
}

function getCapitaPersonal(){

    let formData = document.forms["capitalPersonal_opt"]

    let params = getKosisParam(formData)

    let callBackFn = function(data){
        alert(data.success);
        console.log(data.data)

    }
    kosisApiAjax("/standardOfLiving/api/getCapitaPersonal", callBackFn, 'get', params, errorMsg)

}

function getLargeData(){
    let years = [];
    let formData = document.forms["capitalPersonal_opt"]

    for(var i = 2001; i <= 2020; i++){
        years.push(i)
    }
    for(var j = 0; j < years.length; j++){

        let params = getKosisParams(formData, years[j])

        let callBackFn = function(data){
            alert(data.success)
            console.log(data.data)
        }
        console.log(params)
        //공통모듈 ajax 함수 호출하기
        // kosisApiAjax("/getIncome", callBackFn, 'get', kosisParam, errorMsg);
        kosisApiAjax("/standardOfLiving/api/getCapitaPersonal", callBackFn, 'get', params, errorMsg)
    }


}