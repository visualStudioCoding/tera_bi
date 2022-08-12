/******************** 생활수준지표 *********************/
/* 차트 - 연대별 물가상승률 */
function fnYearsInflChart(data) {
    let year = [];
    let inflation = [];

    for (let i = 0; i < data.inflationYearList.length; i++) {
        inflation.push(data.inflationYearList[i].val);
        year.push(data.inflationYearList[i].yrDt);
    }

    const yearsInflChartDom = document.getElementById("yearsInflGraph");
    if (yearsInflChartDom) {
        const yearsInflChart = echarts.init(yearsInflChartDom);
        let yearsInflGraphOp;

        yearsInflGraphOp = {
            grid: {
                containLabel: true,
                top: "25%",
                bottom: 0,
                left: "1%",
                right: "8%",
            },
            title: {
                text: "연대별 물가상승률",
                left: "center",
                textStyle: {
                    fontSize: 12,
                },
            },
            tooltip: {
                trigger: "axis",
            },
            xAxis: {
                type: "category",
                boundaryGap: ["50%", "20%"],
                data: year,
                axisLabel: {
                    fontSize: 10,
                },
                axisTick: {
                    alignWithLabel: true,
                },
            },
            yAxis: {
                type: "value",
                name: "",
                axisLabel: {
                    fontSize: 10,
                    formatter: "{value}%",
                },
            },
            series: [
                {
                    data: inflation,
                    type: "line",
                    label: {
                        show: true,
                        fontSize: 10,
                    },
                    symbol: "circle",
                    symbolSize: 6,
                    smooth: true,
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {
                                offset: 0,
                                color: "rgba(30,112,231,1)",
                            },
                            {
                                offset: 1,
                                color: "rgba(255,255,255,0.5)",
                            },
                        ]),
                    },
                    lineStyle: {
                        color: "#1e70e7",
                    },
                    itemStyle: {
                        color: "#1e70e7",
                        borderWidth: 1,
                        borderColor: "#fff",
                        shadowColor: "rgba(0, 0, 0, 0.2)",
                        shadowBlur: 3,
                    },
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        yearsInflChart.setOption(yearsInflGraphOp);
    }
}

/* 차트 - 대통령별 물가상승률 */
function fnPresInflChart(data) {

    let category = [];
    let inflation = [];

    for (let i = 0; i < data.inflationPresidentList.length; i++) {
        inflation.push(data.inflationPresidentList[i].val);
        category.push(data.inflationPresidentList[i].category);
    }

    const presInflChartDom = document.getElementById("presInflGraph");
    if (presInflChartDom) {
        const presInflChart = echarts.init(presInflChartDom);
        let presInflGraphOp;
        let presInflData = inflation;

        presInflGraphOp = {
            grid: {
                containLabel: true,
                top: "25%",
                bottom: -5,
                left: "1%",
                right: 0,
            },
            title: {
                text: "대통령별 물가상승률",
                left: "center",
                textStyle: {
                    fontSize: 12,
                },
            },
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "line",
                },
            },
            xAxis: [
                {
                    type: "category",
                    // prettier-ignore
                    data: category,
                    axisLabel: {
                        fontSize: 9,
                        interval: 0,
                        rotate: 30,
                    },
                    axisTick: {
                        alignWithLabel: true,
                    },
                },
            ],
            yAxis: [
                {
                    type: "value",
                    name: "",
                    axisLabel: {
                        fontSize: 10,
                        formatter: "{value}%",
                    },
                },
            ],
            series: [
                {
                    type: "bar",
                    data: presInflData,
                    label: {
                        show: true,
                        position: "top",
                        fontSize: 10,
                        formatter: function (params) {
                            // 최소값, 최대값 label 숨김
                            let maxVal = Math.max.apply(Math, presInflData);
                            let minVal = Math.min.apply(Math, presInflData);
                            if (params.data === maxVal || params.data === minVal) {
                                return "";
                            } else {
                                return params.data;
                            }
                        },
                    },
                    itemStyle: {
                      color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                        { offset: 0, color: "rgba(30,112,231,1)" },
                        { offset: 1, color: "rgba(30,112,231,0.2)" },
                      ]),
                    },
                    barWidth: 12,
                    emphasis: {
                        itemStyle: {
                          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            { offset: 0, color: "#2378f7" },
                            { offset: 0.7, color: "#2378f7" },
                            { offset: 1, color: "#83bff6" },
                          ]),
                        },
                    },
                    markPoint: {
                        data: [
                            { type: "max", name: "Max", itemStyle: { color: "#ff3c3c" } },
                            { type: "min", name: "Min", itemStyle: { color: "#3ba272" } },
                        ],
                        symbolSize: 36,
                        label: {
                            fontSize: 10,
                        },
                    },
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        presInflChart.setOption(presInflGraphOp);
    }
}

/* 차트 - 지니계수 */
function fnGiniCoefficient(data) {

    let year = [];
    let gini = [];

    let resultList = data.giniCoefficientList;

    for (let i = 0; i < resultList.length; i++) {
        gini.push(resultList[i].val);
        year.push(resultList[i].yrDt);
    }
    const giniChartDom = document.getElementById("giniGraph");
    if (giniChartDom) {
        const giniChart = echarts.init(giniChartDom);
        let giniGraphOp;

        giniGraphOp = {
            grid: {
                containLabel: true,
                top: "12%",
                bottom: 0,
                left: "1%",
                right: 0,
            },
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
            },
            xAxis: {
                type: "category",
                data: year,
                axisTick: {
                    alignWithLabel: true,
                },
            },
            yAxis: {
                type: "value",
                name: "지니계수(비율)",
                min: function (val) {
                    return val.min - 0.01;
                },
            },
            series: [
                {
                    data: gini,
                    type: "line",
                    name: "지니계수",
                    symbol: "circle",
                    symbolSize: 10,
                    label: {
                        show: true,
                        fontSize: 10,
                    },
                    lineStyle: {
                        color: "#1e70e7",
                        width: 4,
                        type: "solid",
                        shadowColor: "rgba(0,0,0,0.3)",
                        shadowBlur: 8,
                        shadowOffsetY: 4,
                        cap: "round",
                    },
                    itemStyle: {
                        borderWidth: 3,
                        borderColor: "#fff",
                        color: "#1e70e7",
                        shadowColor: "rgba(0, 0, 0, 0.2)",
                        shadowBlur: 4,
                    },
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        giniChart.setOption(giniGraphOp);
    }
}

function fnUkrInflChartDom(data) {
    /* 차트 - 우크라이나 전쟁 이후 물가상승률 */
    const ukrInflChartDom = document.getElementById("ukrInflGraph");
    if (ukrInflChartDom) {
        const ukrInflChart = echarts.init(ukrInflChartDom);
        let ukrInflGraphOp;

        ukrInflGraphOp = {
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
            },
            legend: {
                padding: 0,
                itemWidth: 15,
                itemHeight: 10,
                textStyle: {
                    fontSize: 11,
                },
            },
            grid: {
                left: "-2%",
                right: "4%",
                top: "8%",
                bottom: 0,
                containLabel: true,
            },
            xAxis: {
                type: "value",
                name: "",
                axisLabel: {
                    formatter: "{value}%",
                    fontSize: 11,
                },
            },
            yAxis: {
                type: "category",
                data: [],
            },
            series: [
                {
                    name: "2021/11",
                    type: "bar",
                    data: [],
                    emphasis: {
                        focus: "series",
                    },
                    label: {
                        show: true,
                        position: "outside",
                        textStyle: {
                            fontSize: 10,
                        },
                    },
                    itemStyle: {
                        color: "#ced4da",
                    },
                    barWidth: 8,
                    barGap: 2,
                },
                {
                    name: "2022/06",
                    type: "bar",
                    data: [],
                    emphasis: {
                        focus: "series",
                    },
                    label: {
                        show: true,
                        position: "outside",
                        textStyle: {
                            fontSize: 10,
                        },
                    },
                    itemStyle: {
                        color: "#1e70e7",
                    },
                    barWidth: 8,
                    barGap: 0.5,
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        ukrInflGraphOp.yAxis.data = data.country;
        ukrInflGraphOp.series[0].data = data.begin;
        ukrInflGraphOp.series[1].data = data.end;
        ukrInflChart.setOption(ukrInflGraphOp);
    }
}

function fnWageInflChartDom(data) {
    console.log(data)
    let minPay = [];
    let price = [];
    let wage = [];
    let period = [];

    for (let i = 0; i < data.minPayRate.length; i++) {
        minPay.push(data.minPayRate[i].val)
    }
    for (let i = 0; i < data.priceRate.length; i++) {
        price.push(data.priceRate[i].val)
    }
    for (let i = 0; i < data.wageRate.length; i++) {
        wage.push(data.wageRate[i].val)
    }

    if (Math.max(minPay.length, price.length, wage.length) == minPay.length){
        for (let i = 0; i < data.minPayRate.length; i++) {
            period.push(data.minPayRate[i].period);
        }
    }else if(Math.max(minPay.length, price.length, wage.length) == price.length){
        for (let i = 0; i < data.priceRate.length; i++) {
            period.push(data.priceRate[i].period);
        }
    }else{
        for (let i = 0; i < data.wageRate.length; i++) {
            period.push(data.wageRate[i].period);
        }
    }

    /* 차트 - 임금 대비 물가상승률 */
    const wageInflChartDom = document.getElementById("wageInflGraph");
    if (wageInflChartDom) {
        const wageInflChart = echarts.init(wageInflChartDom);
        let wageInflGraphOp;

        wageInflGraphOp = {
            grid: {
                containLabel: true,
                top: "13%",
                bottom: 0,
                left: "1%",
                right: "4%",
            },
            legend: {
                padding: 0,
                textStyle: {
                    fontSize: 11,
                },
                itemWidth: 18,
                itemHeight: 11,
                itemStyle: {
                    borderWidth: 0,
                },
            },
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "line",
                },
            },
            xAxis: {
                type: "category",
                data: [],
                axisLabel: {
                    fontSize: 11,
                },
                axisTick: {
                    alignWithLabel: true,
                },
                boundaryGap: false,
            },
            yAxis: {
                type: "value",
                name: "",
                axisLabel: {
                    formatter: "{value}%",
                    fontSize: 11,
                },
            },
            series: [
                {
                    data: [],
                    type: "line",
                    name: "물가상승률",
                    symbol: "circle",
                    symbolSize: 7,
                    showSymbol: false, // hover 할때만 symbol 표시
                    smooth: true,
                    lineStyle: {
                        color: "#1e70e7",
                        type: "solid",
                        cap: "round",
                        width: 2,
                    },
                    itemStyle: {
                        color: "#1e70e7",
                        borderWidth: 3,
                        borderColor: "#fff",
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {
                                offset: 0,
                                color: "rgba(30,112,231,1)",
                            },
                            {
                                offset: 1,
                                color: "rgba(255,255,255,0.5)",
                            },
                        ]),
                    },
                    markPoint: {
                        // 최소값, 최대값 label 별도 스타일
                        data: [
                            {type: "max", name: "Max"},
                            {type: "min", name: "Min"},
                        ],
                        symbolSize: 36,
                        label: {
                            fontSize: 9.5,
                            fontWeight: 300,
                        },
                    },
                },
                {
                    data: [],
                    type: "line",
                    name: "임금상승률",
                    symbol: "circle",
                    symbolSize: 7,
                    showSymbol: false, // hover 할때만 symbol 표시
                    smooth: true,
                    lineStyle: {
                        color: "#3ba272",
                        type: "solid",
                        width: 2,
                    },
                    itemStyle: {
                        color: "#3ba272",
                        borderWidth: 3,
                        borderColor: "#fff",
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {
                                offset: 0,
                                color: "rgba(59,162,114,1)",
                            },
                            {
                                offset: 1,
                                color: "rgba(255,255,255,0.5)",
                            },
                        ]),
                    },
                    markPoint: {
                        // 최소값, 최대값 label 별도 스타일
                        data: [
                            {type: "max", name: "Max"},
                            {type: "min", name: "Min"},
                        ],
                        symbolSize: 36,
                        label: {
                            fontSize: 9.5,
                            fontWeight: 300,
                            color: "#fff",
                        },
                    },
                },
                {
                    data: [],
                    type: "line",
                    name: "최저임금상승률",
                    symbol: "circle",
                    symbolSize: 7,
                    showSymbol: false, // hover 할때만 symbol 표시
                    smooth: true,
                    lineStyle: {
                        color: "#ee6666",
                        type: "solid",
                        width: 2,
                    },
                    itemStyle: {
                        color: "#ee6666",
                        borderWidth: 3,
                        borderColor: "#fff",
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {
                                offset: 0,
                                color: "rgba(238,102,102,1)",
                            },
                            {
                                offset: 1,
                                color: "rgba(255,255,255,0.5)",
                            },
                        ]),
                    },
                    markPoint: {
                        // 최소값, 최대값 label 별도 스타일
                        data: [
                            {type: "max", name: "Max"},
                            {type: "min", name: "Min"},
                        ],
                        symbolSize: 36,
                        label: {
                            fontSize: 9.5,
                            fontWeight: 300,
                            color: "#fff",
                        },
                    },
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        wageInflGraphOp.xAxis.data = period;
        wageInflGraphOp.series[0].data = minPay;
        wageInflGraphOp.series[1].data = wage;
        wageInflGraphOp.series[2].data = price;
        wageInflChart.setOption(wageInflGraphOp);
    }
}

function fnRegRankChartDom(data) {
    /* 차트 - 지역별 경제성장률 순위 */
    const regRankChartDom = document.getElementById("regRankGraph");
    if (regRankChartDom) {
        const regRankChart = echarts.init(regRankChartDom);
        let regRankData = [
        ];

        regRankData = data.emncGrrt

        regRankChartOp = {
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "line",
                    animation: true,
                },
            },
            grid: {
                containLabel: true,
                top: 0,
                bottom: 0,
                left: -10,
                right: 10,
            },
            legend: {},
            color: [
                "#394049",
                "#424a54",
                "#626d7c",
                "#7e8a9b",
                "#97a1ae",
                "#acb4be",
                "#bec4cd",
                "#cfd3d9",
                "#dee1e5",
                "#eceef0",
                "#e5eefc",
                "#bcd5f8",
                "#a4c6f5",
                "#8ab5f2",
                "#6aa2ef",
                "#4289eb",
                "#1e70e7",
            ],
            dataset: [
                {
                    dimensions: ["region", "score"],
                    source: regRankData,
                },
                {
                    transform: {
                        type: "sort",
                        config: {dimension: "score", order: "asc"},
                    },
                },
            ],
            xAxis: {
                type: "value",
                axisLabel: {
                    formatter: "{value}%",
                },
            },
            yAxis: {
                type: "category",
                axisLabel: {
                    textStyle: {
                        fontSize: 11,
                    },
                    color: function (val, idx) {
                        // 3위까지 레이블 강조
                        return idx + 1 > regRankData.length - 4 ? "#1e70e7" : "#7a8489";
                    },
                },
            },
            series: {
                type: "bar",
                encode: {x: "score", y: "region"},
                datasetIndex: 1,
                colorBy: "data",
                label: {
                    show: true,
                    position: "outside",
                    fontSize: 10,
                },
            },
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        regRankChart.setOption(regRankChartOp);
    }
}

function fnTravelChartDom(data) {
    /* 차트 - 연령별 해외여행 통계 */
    const travelChartDom = document.getElementById("travelGraph");
    if (travelChartDom) {
        const travelChart = echarts.init(travelChartDom);
        let travelGraphOp;

        travelGraphOp = {
            color: [
                "#1e70e7",
                "#0099f6",
                "#23bdf9",
                "#7cddf9",
                "#8de2d9",
                "#61c8a9",
                "#3ba272",
                "#107c4f",
            ],
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "line",
                },
                valueFormatter: function (value) {
                    return value.toLocaleString("ko-KR") + "명";
                },
            },
            legend: {
                left: "right",
                top: "center",
                orient: "vertical",
                textStyle: {
                    fontSize: 10,
                },
                padding: 0,
            },
            grid: {
                left: "1%",
                right: "21%",
                top: "10%",
                bottom: 0,
                containLabel: true,
            },
            xAxis: {
                type: "category",
                data: [],
                axisTick: {
                    alignWithLabel: true,
                },
            },
            yAxis: {
                type: "value",
                name: "단위: 만(건)",
                axisLabel: {
                    formatter: function (val) {
                        return (val / 10000).toLocaleString("ko-KR");
                    },
                },
            },
            series: [
                {
                    name: "",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: false,
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: [],
                },
                {
                    name: "",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: false,
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: [],
                },
                {
                    name: "",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: false,
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: [],
                },
                {
                    name: "",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: false,
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: [],
                },
                {
                    name: "",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: false,
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: [],
                },
                {
                    name: "",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: false,
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: [],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        travelGraphOp.xAxis.data = data.period;

        travelGraphOp.series[0].name = data.twenty[0];
        travelGraphOp.series[0].data = data.twenty.splice(1);

        travelGraphOp.series[1].name = data.thirty[0];
        travelGraphOp.series[1].data = data.thirty.splice(1);

        travelGraphOp.series[2].name = data.fourty[0];
        travelGraphOp.series[2].data = data.fourty.splice(1);

        travelGraphOp.series[3].name = data.fifty[0];
        travelGraphOp.series[3].data = data.fifty.splice(1);

        travelGraphOp.series[4].name = data.sixty[0];
        travelGraphOp.series[4].data = data.sixty.splice(1);

        travelGraphOp.series[5].name = data.seventy[0];
        travelGraphOp.series[5].data = data.seventy.splice(1);

        travelGraphOp.series.forEach((item) => {
            item.barWidth = 16;
        })

        travelChart.setOption(travelGraphOp);
    }
}