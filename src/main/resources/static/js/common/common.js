/******************** header *********************/
/* 인디케이터 애니메이션 */
const indicator = document.querySelector(".indicator");
const navLis = document.querySelectorAll(".nav li");
let activedNav = document.querySelector(".nav-link.on");

indicator.style.transform = "translateX(" + activedNav.offsetLeft + "px)";

navLis.forEach((li, idx) => {
    let navLiWidth = li.clientWidth;

    li.addEventListener("mouseover", function () {
        indicator.style.transform = "translateX(" + navLiWidth * idx + "px)";
    });
    li.addEventListener("mouseleave", function () {
        indicator.style.transform = "translateX(" + activedNav.offsetLeft + "px)";
    });
});

/******************** 공통 *********************/
/* 기간 선택 */
$("input[name='term']").click(function (e) {
    if (e.target.id !== "termDatePick") {
        term.attr("disabled", true);
    } else if (e.target.id === "termDatePick") {
        term.attr("disabled", false);
    }
});

/* date range picker */
const term = $("input[name='termDatePicker']");
term.daterangepicker({
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
const loc = $("input[name='ecLoc']");
const locSlct = $(".economic-growth .box .select-group select");
loc.click(function (e) {
    if (e.target.id === "ecLocAll") {
        locSlct.attr("disabled", true);
    } else if (e.target.id === "ecLocSlcts") {
        locSlct.attr("disabled", false);
    }
});
