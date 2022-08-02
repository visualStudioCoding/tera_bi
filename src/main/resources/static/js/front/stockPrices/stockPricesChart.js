/******************** 종합주가지수 *********************/

/* 차트 - GDP */
function fnGdpChart(data) {
    // console.log(data);
    const gdpChartDom = document.getElementById("gdpGraph");
    if (gdpChartDom) {
        const gdpChart = echarts.init(gdpChartDom);
        let gdpGraphOp;

        gdpGraphOp = {
            grid: {
                containLabel: true,
                left: 3,
                right: 0,
                top: 35,
                bottom: 7,
            },
            legend: {
                padding: 0,
            },
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
            },
            dataset: {
                source: data,
            },
            xAxis: {type: "category", axisLabel: {padding: 7}},
            yAxis: {name: "단위: 조원"},
            series: [
                {type: "bar", name: "명목"},
                {type: "bar", name: "실질"},
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        gdpChart.setOption(gdpGraphOp);
    }
}

/* 차트 - 코로나 시기 KOSPI */
function fnCovidKospiChart(data) {

    let covidKospi = [];
    let kospiDate = [];

    for (let i = 0; i < data.covidKospi.length; i++) {
        covidKospi.push(data.covidKospi[i].val);
        kospiDate.push(data.covidKospi[i].kospiDate);
    }

    const covidKospiChartDom = document.getElementById("covidKospiGraph");
    if (covidKospiChartDom) {
        const covidKospiChart = echarts.init(covidKospiChartDom);
        let covidKospiGraphOp;

        covidKospiGraphOp = {
            grid: {
                containLabel: true,
                top: 10,
                bottom: 70,
                left: 10,
                right: 6,
            },
            tooltip: {
                trigger: "axis",
            },
            xAxis: {
                type: "category",
                boundaryGap: false,
                axisLabel: {
                    padding: 10,
                },
                // prettier-ignore
                data: [],
            },
            yAxis: {
                type: "value",
            },
            dataZoom: [
                {
                    type: "inside",
                    start: 0,
                },
                {
                    start: 0,
                },
            ],
            series: [
                {
                    name: "KOSPI",
                    type: "line",
                    symbol: "none",
                    itemStyle: {
                        color: "rgb(255, 70, 131)",
                    },
                    lineStyle: {
                        width: 2.5,
                        shadowColor: "rgba(0,0,0,0.3)",
                        shadowBlur: 8,
                        shadowOffsetY: 4,
                    },
                    // prettier-ignore
                    data: [],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        covidKospiGraphOp.xAxis.data = kospiDate;
        covidKospiGraphOp.series[0].data = covidKospi

        covidKospiChart.setOption(covidKospiGraphOp);
    }
}

/* 차트 - 기준금리와 KOSPI */
function fnKospiChart() {
    const kospiChartDom = document.getElementById("baseRateKospiGraph");
    if (kospiChartDom) {
        const kospiChart = echarts.init(kospiChartDom);
        let kospiGraphOp;

        // prettier-ignore
        let timeData = [
            '2010/7/9',
            '2010/11/16',
            '2011/1/13',
            '2011/3/10',
            '2011/6/10',
            '2012/7/12',
            '2012/10/11',
            '2013/5/9',
            '2014/8/14',
            '2014/10/15',
            '2015/3/12',
            '2015/6/11',
            '2016/6/9',
            '2017/11/30',
            '2018/11/30',
            '2019/7/18',
            '2019/10/16',
            '2020/3/17',
            '2020/5/28',
            '2021/8/26',
            '2021/11/25',
            '2022/1/14',
            '2022/4/14',
            '2022/5/26',
            '2022/7/13',
        ];

        kospiGraphOp = {
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    animation: false,
                },
            },
            legend: {
                data: ["기준금리", "KOSPI"],
                center: "center",
                padding: 0,
            },
            axisPointer: {
                link: [
                    {
                        xAxisIndex: "all",
                    },
                ],
            },
            dataZoom: [
                {
                    show: true,
                    realtime: true,
                    xAxisIndex: [0, 1],
                },
                {
                    type: "inside",
                    realtime: true,
                    xAxisIndex: [0, 1],
                },
            ],
            grid: [
                {
                    top: 30,
                    left: 40,
                    right: 6,
                    height: "27%",
                },
                {
                    left: 40,
                    right: 6,
                    top: "55%",
                    height: "27%",
                },
            ],
            xAxis: [
                {
                    type: "category",
                    boundaryGap: false,
                    axisLine: {onZero: true},
                    data: timeData,
                },
                {
                    gridIndex: 1,
                    type: "category",
                    boundaryGap: false,
                    axisLine: {onZero: true},
                    data: timeData,
                    position: "top",
                },
            ],
            yAxis: [
                {
                    name: "",
                    type: "value",
                },
                {
                    gridIndex: 1,
                    name: "",
                    type: "value",
                    inverse: true,
                },
            ],
            series: [
                {
                    name: "기준금리",
                    type: "line",
                    symbolSize: 0,
                    lineStyle: {
                        width: 2,
                        shadowColor: "rgba(0,0,0,0.3)",
                        shadowBlur: 8,
                        shadowOffsetY: 4,
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {
                                offset: 1,
                                color: "rgb(67,203,255)",
                            },
                            {
                                offset: 0,
                                color: "rgb(115,110,254)",
                            },
                        ]),
                    },
                    // prettier-ignore
                    data: [
                        2.25, 2.50, 2.75, 3.00, 3.25, 3.00, 2.75, 2.50, 2.25, 2.00, 1.75, 1.50, 1.25, 1.50, 1.75, 1.50, 1.25, 0.75, 0.50, 0.75, 1.00, 1.25, 1.50, 1.75, 2.25,
                    ],
                },
                {
                    name: "KOSPI",
                    type: "line",
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    symbolSize: 0,
                    lineStyle: {
                        color: "#1db962",
                        width: 2,
                        shadowColor: "rgba(0,0,0,0.1)",
                        shadowBlur: 8,
                        shadowOffsetY: -4,
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {
                                offset: 0,
                                color: "rgb(129,251,184)",
                            },
                            {
                                offset: 1,
                                color: "rgb(40,199,111)",
                            },
                        ]),
                    },
                    // prettier-ignore
                    data: [
                        1759.33, 1904.63, 2069.73, 2106.7, 2100.69, 1881.99, 1912.06, 2001.05, 2068.54, 1964.43, 2041.03, 2074.2, 1970.35, 2476.37, 2096.86, 2024.55, 2083.48, 1754.64, 2029.6, 3199.27, 2839.01, 2663.34, 2695.05, 2685.9, 2411.92,
                    ],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        kospiChart.setOption(kospiGraphOp);
    }
}

/* 차트 - 연도별 경제성장률 */
function fnInflYearChart(data) {
    console.log(data);

    let growthRate = [];
    let year = [];

    for (let i = 0; i < data.annualGrowthRate.length; i++) {
        growthRate.push(data.annualGrowthRate[i].val);
        year.push(data.annualGrowthRate[i].yrDt);
    }

    const inflYearChartDom = document.getElementById("inflationYearlyGraph");
    if (inflYearChartDom) {
        const inflYearChart = echarts.init(inflYearChartDom);
        let inflYearGraphOp;

        inflYearGraphOp = {
            grid: {
                containLabel: true,
                top: 10,
                bottom: 0,
                left: 0,
                right: 0,
            },
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
                valueFormatter: function (value) {
                    return value + "%";
                },
            },
            xAxis: {
                type: "category",
                data: [],
                axisLabel: {
                    margin: 20,
                },
            },
            yAxis: {
                type: "value",
                axisLabel: {
                    formatter: "{value}%",
                },
            },
            series: [
                {
                    data: [],
                    type: "line",
                    name: "경제 성장률",
                    symbol: "circle",
                    symbolSize: 12,
                    lineStyle: {
                        color: "#1e70e7",
                        width: 4,
                        type: "solid",
                    },
                    itemStyle: {
                        borderWidth: 4,
                        borderColor: "#d9e7fd",
                        color: "#1e70e7",
                    },
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };

        inflYearGraphOp.xAxis.data = year;
        inflYearGraphOp.series[0].data = growthRate

        inflYearChart.setOption(inflYearGraphOp);
    }
}
