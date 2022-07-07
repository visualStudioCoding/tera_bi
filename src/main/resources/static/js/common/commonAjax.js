/**
 * commonAjax 공통모듈
 * ------------------
 */

let commonAjax = function(url, data, fn, methodType, errorMsg){
    // 데이터 값이 잘 넘어왔는지 확인
    console.log("url : ", url);
    console.log("data : ", data);
    console.log("methodType : ", methodType);
     console.log("errorMsg : ", errorMsg);
    let request = $.ajax({
        url: url,
        method: methodType,
        data: data,
        dataType: "json"
    });


    //콜백함수
    request.done(fn);
    console.log(fn);

    request.fail(function( jqXHR, textStatus ) {
        alert( errorMsg + textStatus );
    });
}

/**
 * kosisApiAjax 공통모듈
 * ------------------
 */
let kosisApiAjax = function(url, fn, methodType, param, errorMsg){
    let apiUrl = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
    let parameter = "?" + "method=getList";
    parameter += "&" + "apiKey=";
    parameter += "&" + "itmId=" + param.itmId;
    parameter += "&" + "objL1=" + param.objL1;
    parameter += "&" + "objL2=" + param.objL2;
    parameter += "&" + "objL3=" + param.objL3;
    parameter += "&" + "objL4=" + param.objL4;
    parameter += "&" + "objL5=" + param.objL5;
    parameter += "&" + "objL6=" + param.objL6;
    parameter += "&" + "objL7=" + param.objL7;
    parameter += "&" + "objL8=" + param.objL8;
    parameter += "&" + "format=json";
    parameter += "&" + "jsonVD=Y";
    parameter += "&" + "prdSe=" + param.prdSe;
    parameter += "&" + "startPrdDe=" + param.startPrdDe;
    parameter += "&" + "endPrdDe=" + param.endPrdDe;
    if(param.newEstPrdCnt != undefined) {
        parameter += "&" + "newEstPrdCnt=" + param.newEstPrdCnt;
    }
    parameter += "&" + "loadGubun=" + param.loadGubun;
    parameter += "&" + "orgId=" + param.orgId;
    parameter += "&" + "tblId=" + param.tblId;

    console.log(parameter);
    console.log(param);

    let data = {url:apiUrl, parameter:parameter};

    // 데이터 값이 잘 넘어왔는지 확인
    console.log(url);
    console.log(data);
    console.log(methodType);
    console.log(errorMsg);
    let request = $.ajax({
        url: url,
        method: methodType,
        data: data,
        dataType: "json"
    });

    //콜백함수
    request.done(fn);
    console.log(fn);

    request.fail(function( jqXHR, textStatus ) {
        alert( errorMsg + textStatus );
    });
}

/**
 * enaraApiAjax 공통모듈
 * ------------------
 */
let enaraApiAjax = function(url, fn, methodType, param, errorMsg){
    let apiUrl = "http://www.index.go.kr/openApi/xml_stts.do";
    let parameter = "?" + "userId=";
    parameter += "&" + "statsCode=" + param.statsCode;
    console.log("enaraApiAjax: ", parameter);
    console.log("enaraApiAjax: ", param);

    let data = {url:apiUrl, parameter:parameter};

    // 데이터 값이 잘 넘어왔는지 확인
    console.log("url: ", url);
    console.log(data);
    console.log(methodType);
    console.log(errorMsg);
    let request = $.ajax({
        url: url,
        method: methodType,
        data: data,
        dataType: "json"
    });

    //콜백함수
    request.done(fn);
    console.log(fn);

    request.fail(function( jqXHR, textStatus ) {
        alert( errorMsg + textStatus );
    });
}