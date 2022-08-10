/* 차트 - 코로나 시기 KOSPI */
function fnInsightKospiChart(data) {
    let covidKospi = [];
    let covidKospi2 = [];  //예측
    let kospiDate = [];

    for (let i = 0; i < data.covidKospi.length; i++) {
        covidKospi.push(data.covidKospi[i].val);
            if(i <= data.covidKospi.length-2) covidKospi2.push('');
            if(i == data.covidKospi.length-1)  { covidKospi2.push(data.covidKospi[i].val);  }
        kospiDate.push(data.covidKospi[i].kospiDate);
    }
//console.log(covidKospi2)
    for (let i = 0; i < data.insightKospi.length; i++) {
        covidKospi2.push(data.insightKospi[i].val);
        kospiDate.push(data.insightKospi[i].kospiDate);
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
                }, {
                    name: "INSIGHT",
                    type: "line",
                    symbol: "none",
                    itemStyle: {
                        color: "rgb(0, 0, 131)",
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
        covidKospiGraphOp.xAxis.data = kospiDate;        //날짜
        covidKospiGraphOp.series[0].data = covidKospi    //값
        covidKospiGraphOp.series[1].data = covidKospi2    //값

        covidKospiChart.setOption(covidKospiGraphOp);
    }
}