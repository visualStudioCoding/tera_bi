/******************** 경제성장지표 *********************/
/* 기간 선택 */
$("input[name='ecGrowthTerm']").click(function (e) {
    if (e.target.id !== "ecGrowthDatePick") {
        ecGrowthTerm.attr("disabled", true);
    } else if (e.target.id === "ecGrowthDatePick") {
        ecGrowthTerm.attr("disabled", false);
    }
});

/* date range picker */
const ecGrowthTerm = $("input[name='ecGrowthDatePicker']");
ecGrowthTerm.daterangepicker({
    locale: {
        format: "YYYY-MM-DD",
        daysOfWeek: ["월", "화", "수", "목", "금", "토", "일"],
        monthNames: [
            "1월",
            "2월",
            "3월",
            "4월",
            "5월",
            "6월",
            "7월",
            "8월",
            "9월",
            "10월",
            "11월",
            "12월",
        ],
        applyLabel: "확인",
        cancelLabel: "취소",
    },
});

/* 지역 선택 */
const ecGrowthLoc = $("input[name='ecLoc']");
const ecGrowthLocSlct = $(".economic-growth .box .select-group select");
ecGrowthLoc.click(function (e) {
    if (e.target.id === "ecLocAll") {
        ecGrowthLocSlct.attr("disabled", true);
    } else if (e.target.id === "ecLocSlcts") {
        ecGrowthLocSlct.attr("disabled", false);
    }
});

// 데이터 호출 함수
// window.onload = function(){
//     getExchangeRate();
// }

let errorMsg = "데이터 호출 에러";

function getExchangeRate(){
    let current;
    let past;
    let diff;

    let callBackFn = function( data ) {
        console.log(data.current)
        console.log(data.past)
        console.log(data.differ)

        current = data.current
        past = data.past
        diff = data.differ

        $("#currentExChange").text(current)

        // document.getElementById("currentExChange").value = current
        // $("#currentExChange").innerHTML+='<h1>TEST TEST</h1>'
    }

    getApiResult("/front/economicGrowth/api/getExchangeRate",callBackFn, "get", null, errorMsg);

}