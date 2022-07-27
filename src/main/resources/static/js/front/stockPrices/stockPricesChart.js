/******************** 종합주가지수 *********************/

/* 차트 - GDP */
function fnGdpChart(data) {
    console.log(data);
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
                source: [
                    // ["year", "명목", "실질"],
                    ["2017", 1835, 1760],
                    ["2018", 1898, 1812],
                    ["2019", 1924, 1852],
                    ["2020", 1940, 1839],
                    ["2021", 2071, 1915],
                ],
            },
            xAxis: {type: "category", axisLabel: {padding: 7}},
            yAxis: {},
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
function fnCovidKospiChart() {
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
                data: [
                    '2019/12/30', '2020/01/06', '2020/01/13', '2020/01/20', '2020/01/27', '2020/02/03', '2020/02/10', '2020/02/17', '2020/02/24', '2020/03/02', '2020/03/09', '2020/03/16', '2020/03/23', '2020/03/30', '2020/04/07', '2020/04/14', '2020/04/21', '2020/04/28', '2020/05/04', '2020/05/11', '2020/05/18', '2020/05/25', '2020/06/01', '2020/06/08', '2020/06/15', '2020/06/22', '2020/06/29', '2020/07/06', '2020/07/13', '2020/07/20', '2020/07/27', '2020/08/03', '2020/08/10', '2020/08/17', '2020/08/25', '2020/08/31', '2020/09/07', '2020/09/14', '2020/09/21', '2020/09/28', '2020/10/05', '2020/10/12', '2020/10/19', '2020/10/26', '2020/11/02', '2020/11/09', '2020/11/16', '2020/11/23', '2020/11/30', '2020/12/07', '2020/12/14', '2020/12/21', '2020/12/28', '2021/01/06', '2021/01/13', '2021/01/20', '2021/01/27', '2021/02/03', '2021/02/10', '2021/02/17', '2021/02/24', '2021/03/02', '2021/03/09', '2021/03/16', '2021/03/23', '2021/03/30', '2021/04/07', '2021/04/14', '2021/04/21', '2021/04/28', '2021/05/04', '2021/05/11', '2021/05/18', '2021/05/25', '2021/06/01', '2021/06/08', '2021/06/15', '2021/06/22', '2021/06/29', '2021/07/06', '2021/07/13', '2021/07/20', '2021/07/27', '2021/08/03', '2021/08/10', '2021/08/17', '2021/08/25', '2021/08/31', '2021/09/07', '2021/09/14', '2021/09/21', '2021/09/28', '2021/10/05', '2021/10/12', '2021/10/19', '2021/10/26', '2021/11/02', '2021/11/09', '2021/11/16', '2021/11/23', '2021/11/30', '2021/12/07', '2021/12/14', '2021/12/21', '2021/12/28', '2022/01/06', '2022/01/13', '2022/01/20', '2022/01/27', '2022/02/03', '2022/02/10', '2022/02/17', '2022/02/24', '2022/03/02', '2022/03/09', '2022/03/16', '2022/03/23', '2022/03/30', '2022/04/07', '2022/04/14', '2022/04/21', '2022/04/28', '2022/05/04', '2022/05/11', '2022/05/18', '2022/05/25', '2022/06/01', '2022/06/08', '2022/06/15', '2022/06/22', '2022/06/29', '2022/07/06', '2022/07/13', '2022/07/20', '2022/07/27',
                ],
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
                    data: [
                        2176.46, 2206.39, 2250.57, 2246.13, 2119.01, 2211.95, 2243.59, 2162.84,
                        1987.01, 2040.22, 1771.44, 1566.15, 1717.73, 1725.44, 1860.7, 1914.53,
                        1889.01, 1947.56, 1945.82, 1927.28, 1970.13, 2029.6, 2181.87, 2132.3,
                        2141.32, 2134.65, 2152.41, 2150.25, 2201.19, 2200.44, 2249.37, 2351.67,
                        2407.49, 2304.59, 2353.8, 2368.25, 2396.69, 2412.4, 2278.79, 2327.89,
                        2391.96, 2364.53, 2360.81, 2267.15, 2416.5, 2493.87, 2553.5, 2633.45,
                        2731.45, 2770.06, 2772.18, 2806.86, 2873.47, 3152.18, 3085.9, 3140.63,
                        2976.21, 3120.63, 3100.58, 3107.62, 3012.95, 3026.26, 3054.39, 3039.53,
                        3041.02, 3112.81, 3131.89, 3198.63, 3186.11, 3147.87, 3197.21, 3153.33,
                        3156.43, 3188.74, 3240.09, 3249.33, 3267.94, 3302.85, 3287.79, 3217.96,
                        3276.92, 3254.43, 3202.32, 3270.37, 3171.3, 3060.52, 3133.91, 3201.07,
                        3125.77, 3140.52, 3012.25, 3019.19, 2956.31, 3015.07, 3006.17, 2970.69,
                        2969.28, 2968.81, 2971.03, 2936.45, 2968.34, 3010.24, 3017.73, 3012.44,
                        2977.66, 2954.9, 2921.93, 2834.3, 2663.35, 2750.27, 2747.72, 2744.53,
                        2676.77, 2713.44, 2661.29, 2707.03, 2729.99, 2739.86, 2700.4, 2696.07,
                        2704.72, 2695.06, 2644.52, 2604.25, 2639.3, 2638.06, 2670.66, 2595.88,
                        2440.94, 2366.61, 2305.43, 2350.62, 2330.99, 2393.15, 2407.91
                    ],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
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
function fnInflYearChart() {
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
                data: [
                    "2010",
                    "2011",
                    "2012",
                    "2013",
                    "2014",
                    "2015",
                    "2016",
                    "2017",
                    "2018",
                    "2019",
                    "2020",
                    "2021",
                    "2022",
                ],
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
                    data: [
                        6.8, 3.7, 2.4, 3.2, 3.2, 2.8, 2.9, 3.2, 2.9, 2.2, -0.9, 4.1, 3.0,
                    ],
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
        inflYearChart.setOption(inflYearGraphOp);
    }
}
