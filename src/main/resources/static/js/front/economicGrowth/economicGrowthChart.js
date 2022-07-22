/* 차트 - 지역별 경제성장률 */
const regionChartDom = document.getElementById("regionGrowthGraph");
const regionChart = echarts.init(regionChartDom);
let regionChartOp;
let colors = ["#393939", "#f5b031", "#fad797", "#59ccf7", "#c3b4df"];

regionChartOp = {
    dataset: {
        source: [
            ["score", "성장률", "지역"],
            [89.3, 58212, "서울특별시"],
            [57.1, 78254, "부산광역시"],
            [74.4, 41032, "대구광역시"],
            [50.1, 12755, "인천광역시"],
            [89.7, 20145, "광주광역시"],
            [68.1, 79146, "대전광역시"],
            [19.6, 91852, "울산광역시"],
            [10.6, 101852, "세종특별자치시"],
            [0, 20112, "경기도"],
        ],
    },
    grid: { containLabel: true, x: 0, y: 0, y2: 0 },
    xAxis: {
        name: "성장률",
        axisLabel: {
            margin: 20,
            color: "#292b2c",
        },
    },
    yAxis: {
        type: "category",
        axisLabel: {
            color: "#292b2c",
        },
    },
    series: [
        {
            type: "bar",
            encode: {
                // Map the "amount" column to X axis.
                x: "성장률",
                // Map the "product" column to Y axis
                y: "지역",
            },
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        position: "right",
                        color: "#7a8489",
                    },
                    color: "#1e70e7",
                },
            },
        },
    ],
    textStyle: {
        fontFamily: "NanumSquare",
    },
};

regionChart.setOption(regionChartOp);

/* 차트 - 코로나 시기 성장률 */
const covidChartDom = document.getElementById("covidGrowthGraph");
const covidChart = echarts.init(covidChartDom);
let covidChartOp;

covidChartOp = {
    grid: { containLabel: true, x: 0, x2: 0 },
    xAxis: {
        type: "category",
        data: ["2019", "2020", "2021", "2022"],
    },
    yAxis: {
        type: "value",
    },
    series: [
        {
            data: [230, 150, 180, 160],
            type: "line",
        },
    ],
};
covidChart.setOption(covidChartOp);

/* 차트 - 물가 상승 추이 */
const inflChartDom = document.getElementById("inflationGraph");
const inflChart = echarts.init(inflChartDom);
let inflChartOp;

inflChartOp = {
    xAxis: {
        type: "category",
        data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    },
    yAxis: {
        type: "value",
    },
    series: [
        {
            data: [120, 200, 150, 80, 70, 110, 130],
            type: "line",
            symbol: "triangle",
            symbolSize: 14,
            lineStyle: {
                color: "#1e70e7",
                width: 3,
                type: "dashed",
            },
            itemStyle: {
                borderWidth: 3,
                borderColor: "#1e70e7",
                color: "#1e70e7",
            },
        },
        {
            data: [20, 65, 128, 170, 150, 100, 100],
            type: "line",
            symbol: "circle",
            symbolSize: 14,
            lineStyle: {
                color: "#1e70e7",
                width: 3,
                type: "solid",
            },
            itemStyle: {
                borderWidth: 3,
                borderColor: "#1e70e7",
                color: "white",
            },
        },
        {
            data: [75, 120, 110, 20, 130, 150, 190],
            type: "line",
            symbol: "rect",
            symbolSize: 14,
            lineStyle: {
                color: "#1e70e7",
                width: 3,
                type: "dotted",
            },
            itemStyle: {
                borderWidth: 3,
                borderColor: "#1e70e7",
                color: "yellow",
            },
        },
    ],
};
inflChart.setOption(inflChartOp);

/* 차트 - GDP 대비 국가채무 */
const gdpDeptChartDom = document.getElementById("gdpDeptGraph");
const gdpDeptChart = echarts.init(gdpDeptChartDom);
let gdpDeptGraphOp;

const categories = (function () {
    let now = new Date();
    let res = [];
    let len = 10;
    while (len--) {
        res.unshift(now.toLocaleTimeString().replace(/^\D*/, ""));
        now = new Date(+now - 2000);
    }
    return res;
})();
const categories2 = (function () {
    let res = [];
    let len = 10;
    while (len--) {
        res.push(10 - len - 1);
    }
    return res;
})();
const data = (function () {
    let res = [];
    let len = 10;
    while (len--) {
        res.push(Math.round(Math.random() * 1000));
    }
    return res;
})();
const data2 = (function () {
    let res = [];
    let len = 0;
    while (len < 10) {
        res.push(+(Math.random() * 10 + 5).toFixed(1));
        len++;
    }
    return res;
})();

gdpDeptGraphOp = {
    tooltip: {
        trigger: "axis",
        axisPointer: {
            type: "cross",
            label: {
                backgroundColor: "#283b56",
            },
        },
    },
    legend: {},
    toolbox: {
        show: true,
        feature: {
            dataView: { readOnly: false },
            restore: {},
            saveAsImage: {},
        },
    },
    dataZoom: {
        show: false,
        start: 0,
        end: 100,
    },
    xAxis: [
        {
            type: "category",
            boundaryGap: true,
            data: categories,
        },
        {
            type: "category",
            boundaryGap: true,
            data: categories2,
        },
    ],
    yAxis: [
        {
            type: "value",
            scale: true,
            name: "Price",
            max: 30,
            min: 0,
            boundaryGap: [0.2, 0.2],
        },
        {
            type: "value",
            scale: true,
            name: "Order",
            max: 1200,
            min: 0,
            boundaryGap: [0.2, 0.2],
        },
    ],
    series: [
        {
            name: "Dynamic Bar",
            type: "bar",
            xAxisIndex: 1,
            yAxisIndex: 1,
            data: data,
        },
        {
            name: "Dynamic Line",
            type: "line",
            data: data2,
        },
    ],
};
gdpDeptChart.setOption(gdpDeptGraphOp);
