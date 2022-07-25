/******************** 부동산시장동향 *********************/
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

