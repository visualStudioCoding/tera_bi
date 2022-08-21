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
    obj.value = inputComma(obj);
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
            "                            <div class=\"row\">\n" +
            "                                <span>거래처</span>\n" +
            "                                <div class=\"addOrSubCurrent\" style=\"margin-left: 75%;\">\n" +
            "                               <div class=\"row\">\n" +
            "                                  <button type=\"button\" class=\"subContents\" style=\"display:inline;\" onclick=\"subForms(this)\"><i class=\"ri-subtract-line\"></i></button>\n" +
            "                                  <button type=\"button\" class=\"addContents\" onclick=\"addFormCurrent()\"><i class=\"ri-add-line\"></i></button>\n" +
            "                               </div>\n" +
            "                            </div>\n" +
            "                            </div>\n" +
            "                            <div class=\"row\">\n" +
            "                                <select>\n" +
            "                                   <option>거래처를 선택해주세요</option>" +
                                                innerCode +
            "                                </select>\n" +
            "                            </div>\n" +
            "                           <div class=\"row\">\n" +
            "                               <span>거래 년도</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <span>거래 횟수</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <span>매출</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>\n" +
            "                        </div>")

        $(".addOrSubCurrent").css("margin-left", "65%");
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
            "                            <div class=\"row\">\n" +
            "                                <span>거래처</span>\n" +
            "                                <div class=\"addOrSubNew\" style=\"margin-left: 75%;\">\n" +
            "                               <div class=\"row\">\n" +
            "                                  <button type=\"button\" class=\"subContents\" style=\"display:inline;\" onclick=\"subForms(this)\"><i class=\"ri-subtract-line\"></i></button>\n" +
            "                                  <button type=\"button\" class=\"addContents\" onclick=\"addFormNew()\"><i class=\"ri-add-line\"></i></button>\n" +
            "                               </div>\n" +
            "                            </div>\n" +
            "                            </div>\n" +
            "                            <div class=\"row\">\n" +
            "                                <select>\n" +
            "                                   <option>거래처를 선택해주세요</option>" +
            innerCode +
            "                                </select>\n" +
            "                            </div>\n" +
            "                           <div class=\"row\">\n" +
            "                               <span>거래 년도</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <span>거래 횟수</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <span>매출</span>\n" +
            "                           </div>" +
            "                           <div class=\"row\">\n" +
            "                               <input type=\"text\" onKeyUp=\"inputCommaValues(this)\">\n" +
            "                           </div>\n" +
            "                        </div>")

        $(".addOrSubNew").css("margin-left", "65%");
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

function subForms(obj) {
    // console.log(obj.className);
    // let valList = obj.className.split(" ");
    // let valiable = valList[1];
    // console.log(valList)
    // console.log(valiable);
    // // console.log(obj.parentNode.parentNode.parentNode.parentNode);
    $(obj.parentNode.parentNode.parentNode.parentNode).remove();
    //
    if (formIndex > 0) {
        formIndex--;
    }
}