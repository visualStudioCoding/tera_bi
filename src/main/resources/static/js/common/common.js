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

