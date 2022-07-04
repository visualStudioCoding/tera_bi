function getIncome(){
    console.log("321")

    var url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
    var parameter = "?" + "method=getList";
    parameter += "&" + "apiKey=";
    parameter += "&" + "itmId=T3+";
    parameter += "&" + "objL1=ALL";
    parameter += "&" + "objL2=";
    parameter += "&" + "objL3=";
    parameter += "&" + "objL4=";
    parameter += "&" + "objL5=";
    parameter += "&" + "objL6=";
    parameter += "&" + "objL7=";
    parameter += "&" + "objL8=";
    parameter += "&" + "format=json";
    parameter += "&" + "jsonVD=Y";
    parameter += "&" + "prdSe=Y";
    parameter += "&" + "startPrdDe=2018";
    parameter += "&" + "endPrdDe=2018";
    parameter += "&" + "loadGubun=2";
    parameter += "&" + "orgId=101";
    parameter += "&" + "tblId=INH_1C86_04";

    $.ajax({
        url:"/getIncome",
        type:"post",
        data:{url:url, parameter:parameter},
        success:function(data){
            console.log(data)
        },
        error(request,status,error){
            console.log(error)
        }
    })
}