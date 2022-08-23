let errorMsg = "데이터 입력 에러";

/* input 태그 1000단위 콤마 */
function inputComma(param) {
    let values = param.value;
    values = Number(values.replaceAll(',', ''));
    if (isNaN(values)) {
        values = 0
    }
    const formatValue = values.toLocaleString('ko-KR');
    console.log(formatValue);
    return formatValue;
}

function inputCommaValues(obj) {
    if(obj.name != 'yrDt') {
        obj.value = inputComma(obj);
    }
}

let formIndexCurrent = 0;

function addFormCurrent() {
    if (formIndexCurrent === 1) {
        alert("거래처별 매출 입력 폼을 더 이상 추가할 수 없습니다")
    } else {
        let innerCode = "";
        for (let i = 0; i < variables.length; i++) {
            innerCode += "<option>" + variables[i] + "</option>"
        }

        $(".addFormsCurrent").append(
            "                        <div class=\"col cmpList\" style=\"margin-top:1rem\">\n" +
            "                           <div class=\"row\">\n" +
            "                               <span>거래 년도</span>\n" +
            "                                <div class=\"addOrSubCurrent\" style=\"margin-left: 68%;\">\n" +
            "                               <div class=\"row\">\n" +
            "                                  <button type=\"button\" class=\"subContents\" style=\"display:inline;\" onclick=\"subFormCurrent(this)\"><i class=\"ri-subtract-line\"></i></button>\n" +
            "                                  <button type=\"button\" class=\"addContents\" onclick=\"addFormCurrent()\"><i class=\"ri-add-line\"></i></button>\n" +
            "                               </div>\n" +
            "                            </div>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" name=\"yrDt\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>" +
            "                            <div class=\"row\">\n" +
            "                                <span>거래처</span>\n" +
            "                            </div>\n" +
            "                            <div class=\"row\">\n" +
            "                                <select name = \"clientsNm\">\n" +
            "                                   <option>거래처를 선택해주세요</option>" +
                                                innerCode +
            "                                </select>\n" +
            "                            </div>\n" +
            "                           <div class=\"row\">\n" +
            "                               <span>거래 횟수</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"number\" name=\"tradeCnt\" onKeyUp=\"inputCommaValues(this)\">\n" +

            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <span>매출</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" name=\"sales\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>\n" +
            "                        </div>")

        $(".addOrSubCurrent").css("margin-left", "60%");
        // if(formIndex > 0){
        //     $(".subContents").addClass("frmIdx" + formIndex);
        //     $(".cmpList:first").removeClass("cmpList");
        //     $(".cmpList").attr('id', 'frmIdx' + formIndex);
        //     $(".frmIdx" + (formIndex - 1) + ":nth-child(1)").removeClass("frmIdx" + (formIndex));
        //     $(".frmIdx" + formIndex + ":nth-last-child(1)").removeClass("frmIdx" + (formIndex - 1));
        //     // $(".cmpList").removeAttr(id, 'frmIdx' + formIndex);
        //     // $(".cmpList").removeAttr(id, 'frmIdx' + formIndex);
        // }else {
        //     $(".subContents").addClass("frmIdx" + formIndex);
        //     $(".cmpList").attr('id', 'frmIdx' + formIndex);
        // }
        formIndexCurrent++;
    }
}
let formIndexNew = 0;

function addFormNew() {
    if (formIndexNew === 1) {
        alert("신규 거래처 등록 폼을 더 이상 추가할 수 없습니다")
    } else {
        let innerCode = "";
        for (let i = 0; i < variables.length; i++) {
            innerCode += "<option>" + variables[i] + "</option>"
        }

        $(".addFormsNew").append(
            "                        <div class=\"col cmpList\" style=\"margin-top:1rem\">\n" +
            "                           <div class=\"row\">\n" +
            "                               <span>거래 년도</span>\n" +
            "                                <div class=\"addOrSubNew\" style=\"margin-left: 68%;\">\n" +
            "                                   <div class=\"row\">\n" +
            "                                       <button type=\"button\" class=\"subContents\" style=\"display:inline;\" onclick=\"subFormNew(this)\"><i class=\"ri-subtract-line\"></i></button>\n" +
            "                                       <button type=\"button\" class=\"addContents\" onclick=\"addFormNew()\"><i class=\"ri-add-line\"></i></button>\n" +
            "                                   </div>\n" +
            "                               </div>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" name=\"yrDt\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>" +
            "                            <div class=\"row\">\n" +
            "                                <span>거래처</span>\n" +
            "                            </div>\n" +
            "                            <div class=\"row\">\n" +
            "                               <input type=\"text\" name=\"clientsNm\"/>\n" +
            "                            </div>\n" +
            "                           <div class=\"row\">\n" +
            "                               <span>거래 횟수</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"number\" name=\"tradeCnt\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <span>매출</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" name=\"sales\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>\n" +
            "                        </div>")

        $(".addOrSubNew").css("margin-left", "60%");
        // if(formIndex > 0){
        //     $(".subContents").addClass("frmIdx" + formIndex);
        //     $(".cmpList:first").removeClass("cmpList");
        //     $(".cmpList").attr('id', 'frmIdx' + formIndex);
        //     $(".frmIdx" + (formIndex - 1) + ":nth-child(1)").removeClass("frmIdx" + (formIndex));
        //     $(".frmIdx" + formIndex + ":nth-last-child(1)").removeClass("frmIdx" + (formIndex - 1));
        //     // $(".cmpList").removeAttr(id, 'frmIdx' + formIndex);
        //     // $(".cmpList").removeAttr(id, 'frmIdx' + formIndex);
        // }else {
        //     $(".subContents").addClass("frmIdx" + formIndex);
        //     $(".cmpList").attr('id', 'frmIdx' + formIndex);
        // }
        formIndexNew++;
    }
}

function subFormCurrent(obj) {
    // console.log(obj.className);
    // let valList = obj.className.split(" ");
    // let valiable = valList[1];
    // console.log(valList)
    // console.log(valiable);
    // // console.log(obj.parentNode.parentNode.parentNode.parentNode);
    $(obj.parentNode.parentNode.parentNode.parentNode).remove();
    //
    if (formIndexCurrent > 0) {
        formIndexCurrent--;
    }
}

function subFormNew(obj) {
    // console.log(obj.className);
    // let valList = obj.className.split(" ");
    // let valiable = valList[1];
    // console.log(valList)
    // console.log(valiable);
    // // console.log(obj.parentNode.parentNode.parentNode.parentNode);
    $(obj.parentNode.parentNode.parentNode.parentNode).remove();
    //
    if (formIndexNew > 0) {
        formIndexNew--;
    }
}

function inputCapital(){

    let formData = document.forms["inputCapitalData"];

    let params = {"yrDt": formData.yrDt.value, "ttlAsset": formData.ttlAsset.value.replace(/,/g, ''),
                    "capital": formData.capital.value.replace(/,/g, ''), "eqtyCptl": formData.eqtyCptl.value.replace(/,/g, ''),
                        "crntLblts": formData.crntLblts.value.replace(/,/g, ''), "ttlLblts": formData.ttlLblts.value.replace(/,/g, ''),
                            "lqdAsset": formData.lqdAsset.value.replace(/,/g, '')};

    console.log(params)

    let callBackFn = function( data ) {
        alert(data);
    }
    commonAjax("/admin/managementAnalysis/api/insertCapital", callBackFn, 'get', params, errorMsg);
}

function inputSales(){

    let formData = document.forms["inputSalesData"];

    let params = {"yrDt": formData.yrDt.value, "consulting": formData.consulting.value.replace(/,/g, ''),
        "sysDevPart": formData.sysDev.value.replace(/,/g, ''), "smPart": formData.smPart.value.replace(/,/g, ''),
        "swPart": formData.swPart.value.replace(/,/g, ''),"totalSales": formData.totalSales.value.replace(/,/g, '')};

    console.log(params)

    let callBackFn = function( data ) {
        alert(data);
    }
    commonAjax("/admin/managementAnalysis/api/insertSales", callBackFn, 'get', params, errorMsg);
}

function inputProfitLossAndSales(){

    let formData = document.forms["inputProfitLossAndSalesData"];

    let params = {"yrDt": formData.yrDt.value, "trgtPrftLoss": formData.trgtPrftLoss.value.replace(/,/g, ''),
        "prftLoss": formData.prftLoss.value.replace(/,/g, ''), "trgtSales": formData.trgtSales.value.replace(/,/g, ''),
        "sales": formData.sales.value.replace(/,/g, '')};

    let callBackFn = function( data ) {
        alert(data);
    }
    commonAjax("/admin/managementAnalysis/api/insertProfitLossAndSales", callBackFn, 'get', params, errorMsg);
}

function inputNewClients(){
    // let formData = document.forms["inputNewClientsData"];

    // let params = {"yrDt": formData.yrDt.value, "clientsNm": formData.clientsNm.value,
    //                 "count": formData.tradeCnt.value, "sales": formData.sales.value}
    var params = $("form[name=inputNewClientsData]").serializeArray();

    console.log(params)
    let callBackFn = function(data){
        alert(data);
    }
    commonAjax("/admin/managementAnalysis/api/insertNewClients", callBackFn, 'post', params, errorMsg);
}

function inputClientsSales(){
    // let formData = document.forms["inputNewClientsData"];

    // let params = {"yrDt": formData.yrDt.value, "clientsNm": formData.clientsNm.value,
    //                 "count": formData.tradeCnt.value, "sales": formData.sales.value}
    var params = $("form[name=inputClientsSalesData]").serializeArray();

    console.log(params)
    let callBackFn = function(data){
        alert(data);
    }
    commonAjax("/admin/managementAnalysis/api/updateClientsSales", callBackFn, 'post', params, errorMsg);
}