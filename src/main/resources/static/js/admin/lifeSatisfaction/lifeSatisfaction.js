function fnParam(frm) {
    return kosisParam = {
        "itmId": frm.itmId.value,
        "objL1": frm.objL1.value,
        "objL2": frm.objL2.value,
        "objL3": "",
        "objL4": "",
        "objL5": "",
        "objL6": "",
        "objL7": "",
        "objL8": "",
        "prdSe": frm.prdSe.value,
        "startPrdDe": frm.startPrdDe.value,
        "endPrdDe": frm.startPrdDe.value,
        "loadGubun": frm.loadGubun.value,
        "newEstPrdCnt": frm.newEstPrdCnt.value,
        "orgId": frm.orgId.value,
        "tblId": frm.tblId.value
    }
}

//1. 삶에 대한 만족도
function api1() {
    const frm = document.forms['frm1'];
    let param = fnParam(frm);
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst1").html("");
    let callBackFn = function (data) {
        //alert(data.success + " ,  " +data.size);
        result += data.data.yrdt + " " + data.success + " ,  " + data.size + "건<br>";
        $("#rst1").html(result);
        console.log(data.data)
    }
    //공통모듈 ajax 함수 호출하기
    kosisApiAjax("/admin/lifeSatisfaction/LifeSatisfaction", callBackFn, 'get', param, errorMsg);
}

//2. 월별 혼인
function api2() {
    const frm = document.forms['frm2'];
    let param = fnParam(frm);
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst2").html("");
    let callBackFn = function (data) {
        //alert(data.success + " ,  " +data.size);
        result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " + data.size + "건<br>";
        $("#rst2").html(result);
        console.log(data.data)
    }
    let syear = frm.startPrdDe.value;
    syear = syear.substr(0, 4);
    let month = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];

    for (let i = 0; i < month.length; i++) {
        let yearmonth = syear + month[i];
        frm.startPrdDe.value = yearmonth;
        //frm.endPrdDe.value = yearmonth;
        let param = fnParam(frm);
        console.log(param);
        kosisApiAjax("/admin/lifeSatisfaction/Marriage", callBackFn, 'get', param, errorMsg);
    }

    //공통모듈 ajax 함수 호출하기
    //kosisApiAjax("/lifeSatisfaction/Marriage", callBackFn, 'get', param, errorMsg);
}

//3. 월별 이혼
function api3() {
    const frm = document.forms['frm3'];
    //let param = fnParam(frm);
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst3").html("");
    let callBackFn = function (data) {
        //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
        result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " + data.size + "건<br>";
        $("#rst3").html(result);
        console.log(data.data)
    }
    let syear = frm.startPrdDe.value;
    syear = syear.substr(0, 4);
    let month = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];

    for (let i = 0; i < month.length; i++) {
        let yearmonth = syear + month[i];
        frm.startPrdDe.value = yearmonth;
        let param = fnParam(frm);
        console.log(param);
        kosisApiAjax("/admin/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
    }

    //공통모듈 ajax 함수 호출하기
    //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
}

//4. 고용률
function api4() {
    const frm = document.forms['frm4'];
    //let param = fnParam(frm);
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst4").html("");
    let callBackFn = function (data) {
        //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
        result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " + data.size + "건<br>";
        $("#rst4").html(result);
        console.log(data.data)
    }
    let syear = frm.startPrdDe.value;
    syear = syear.substr(0, 4);
    let month = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];

    for (let i = 0; i < month.length; i++) {
        let yearmonth = syear + month[i];
        frm.startPrdDe.value = yearmonth;
        let param = fnParam(frm);
        console.log(param);
        kosisApiAjax("/admin/lifeSatisfaction/emplyrate", callBackFn, 'get', param, errorMsg);
    }

    //공통모듈 ajax 함수 호출하기
    //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
}

//5. 행정구역(시도)/성별 실업률
function api5() {
    const frm = document.forms['frm5'];
    //let param = fnParam(frm);
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst5").html("");
    let callBackFn = function (data) {
        //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
        result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " + data.size + "건<br>";
        $("#rst5").html(result);
        console.log(data.data)
    }
    let syear = frm.startPrdDe.value;
    syear = syear.substr(0, 4);
    let month = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];

    for (let i = 0; i < month.length; i++) {
        let yearmonth = syear + month[i];
        frm.startPrdDe.value = yearmonth;
        let param = fnParam(frm);
        console.log(param);
        kosisApiAjax("/admin/lifeSatisfaction/unmplrate", callBackFn, 'get', param, errorMsg);
    }

    //공통모듈 ajax 함수 호출하기
    //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
}

//6. 전산업생산지수
function api6() {
    const frm = document.forms['frm6'];
    //let param = fnParam(frm);
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst6").html("");
    let callBackFn = function (data) {
        //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
        result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " + data.size + "건<br>";
        $("#rst6").html(result);
        console.log(data.data)
    }
    let syear = frm.startPrdDe.value;
    syear = syear.substr(0, 4);
    let month = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];

    for (let i = 0; i < month.length; i++) {
        let yearmonth = syear + month[i];
        frm.startPrdDe.value = yearmonth;
        let param = fnParam(frm);
        console.log(param);
        kosisApiAjax("/admin/lifeSatisfaction/allprindex", callBackFn, 'get', param, errorMsg);
    }

    //공통모듈 ajax 함수 호출하기
    //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
}

//7. 해외여행
function api7() {
    const frm = document.forms['frm7'];
    //let param = fnParam(frm);
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst7").html("");
    let callBackFn = function (data) {
        // //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
        // result += data.data.yrdt + data.success + " ,  " +data.size + "건<br>";
        // $("#rst7").html(result);
        console.log(data.data)
    }
    let syear = frm.startPrdDe.value;
    syear = syear.substr(0, 4);

    frm.startPrdDe.value = syear;
    let param = fnParam(frm);
    console.log(param);
    kosisApiAjax("/admin/lifeSatisfaction/ovrsstrip", callBackFn, 'get', param, errorMsg);

    //공통모듈 ajax 함수 호출하기
    //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
}

//8. 기업규모별 개인소득 점유율
function api8() {
    const frm = document.forms['frm8'];
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst8").html("");
    let callBackFn = function (data) {
        //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
        result += data.data.yrdt + data.success + " ,  " + data.size + "건<br>";
        $("#rst8").html(result);
        console.log(data.data)
    }
    let syear = frm.startPrdDe.value;
    syear = syear.substr(0, 4);

    frm.startPrdDe.value = syear;
    let param = fnParam(frm);
    console.log(param);
    kosisApiAjax("/admin/lifeSatisfaction/prsnlnshr", callBackFn, 'get', param, errorMsg);

    //공통모듈 ajax 함수 호출하기
    //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
}

//9. 삶의 만족도
function api9() {
    const frm = document.forms['frm9'];
    let errorMsg = "error";
    let result = "";

    //ajax가 정상 호출 되었을때 실행 되는 함수
    $("#rst9").html("");
    let callBackFn = function (data) {
        //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
        result += data.data.yrdt + data.success + " ,  " + data.size + "건<br>";
        $("#rst9").html(result);
        console.log(data.data)
    }
    let syear = frm.startPrdDe.value;
    syear = syear.substr(0, 4);

    frm.startPrdDe.value = syear;
    let param = fnParam(frm);
    console.log(param);
    kosisApiAjax("/admin/lifeSatisfaction/LifeSatisfaction2", callBackFn, 'get', param, errorMsg);

    //공통모듈 ajax 함수 호출하기
    //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
}