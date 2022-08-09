/* 차트 - 손익현황 달성률 */
const plAchvRateChartDom = document.getElementById("plAchvRateGraph");
if (plAchvRateChartDom) {
  const plAchvRateChart = echarts.init(plAchvRateChartDom);
  let plAchvRateGraphOp;
  let achvRate = 73;
  let notAchv = 100 - achvRate;

  plAchvRateGraphOp = {
    title: {
      text: "달성률",
      left: "center",
      textStyle: {
        fontSize: 12,
      },
      padding: 10,
    },
    series: [
      {
        type: "pie",
        radius: ["45%", "70%"],
        center: ["50%", "65%"],
        avoidLabelOverlap: false,
        showEmptyCircle: true,
        stillShowZeroSum: true,
        label: {
          show: true,
          position: "center",
          formatter: "{c}%",
          fontSize: 14,
          fontWeight: 600,
        },
        clockwise: false,
        labelLine: {
          show: false,
        },
        silent: true,
        data: [
          {
            value: achvRate,
            name: "달성률",
            itemStyle: {
              color: "rgba(30,112,231,1)",
            },
          },
          { value: notAchv, name: "", itemStyle: { color: "#eeeeee" } },
        ],
      },
    ],
    textStyle: {
      fontFamily: "NanumSquare",
    },
  };
  plAchvRateChart.setOption(plAchvRateGraphOp);
}

/* 차트 - 매출현황 달성률 */
const slAchvRateChartDom = document.getElementById("slAchvRateGraph");
if (slAchvRateChartDom) {
  const slAchvRateChart = echarts.init(slAchvRateChartDom);
  let slAchvRateGraphOp;
  let achvRate = 78;
  let notAchv = 100 - achvRate;

  slAchvRateGraphOp = {
    title: {
      text: "달성률",
      left: "center",
      textStyle: {
        fontSize: 12,
      },
      padding: 10,
    },
    grid: {
      containLabel: true,
      top: 0,
      left: 0,
    },
    series: [
      {
        type: "pie",
        radius: ["45%", "70%"],
        center: ["50%", "65%"],
        avoidLabelOverlap: false,
        showEmptyCircle: true,
        stillShowZeroSum: true,
        label: {
          show: true,
          position: "center",
          formatter: "{c}%",
          fontSize: 14,
          fontWeight: 600,
        },
        clockwise: false,
        labelLine: {
          show: false,
        },
        silent: true,
        data: [
          {
            value: achvRate,
            name: "달성률",
            itemStyle: {
              color: "rgba(30,112,231,1)",
            },
          },
          { value: notAchv, name: "", itemStyle: { color: "#eeeeee" } },
        ],
      },
    ],
    textStyle: {
      fontFamily: "NanumSquare",
    },
  };
  slAchvRateChart.setOption(slAchvRateGraphOp);
}

/* 거래처별 매출현황 */
const cstmSalesChartDom = document.getElementById("cstmSalesGraph");
if (cstmSalesChartDom) {
  const cstmSalesChart = echarts.init(cstmSalesChartDom);
  let cstmSalesGraphOp;

  cstmSalesGraphOp = {
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["한전KDN", "농어촌공사", "우정사업본부", "R&D"],
      textStyle: {
        fontSize: 11,
      },
      padding: 0,
      itemWidth: 20,
      itemHeight: 12,
      itemStyle: {
        borderWidth: 0,
      },
    },
    grid: {
      left: "1%",
      right: "4%",
      top: "23%",
      bottom: 0,
      containLabel: true,
    },
    xAxis: {
      type: "category",
      boundaryGap: false,
      data: ["2019", "2020", "2021", "2022"],
      axisLabel: {
        textStyle: {
          fontSize: 11,
        },
      },
    },
    yAxis: {
      type: "value",
      name: "단위: 만원",
      axisLabel: {
        textStyle: {
          fontSize: 11,
        },
      },
    },
    series: [
      {
        name: "한전KDN",
        type: "line",
        symbol: "circle",
        symbolSize: 6,
        showSymbol: false,
        itemStyle: {
          color: "#1e70e7",
          borderWidth: 1,
          borderColor: "#fff",
        },
        areaStyle: {
          color: "#1e70e7",
        },
        data: [320000, 332000, 301000, 334000],
        zlevel: 0,
      },
      {
        name: "농어촌공사",
        type: "line",
        symbol: "circle",
        symbolSize: 6,
        showSymbol: false,
        itemStyle: {
          color: "#3ba272",
          borderWidth: 1,
          borderColor: "#fff",
        },
        areaStyle: {
          color: "#3ba272",
        },
        data: [10000, 12200, 15100, 23000],
        zlevel: 3,
      },
      {
        name: "우정사업본부",
        type: "line",
        symbol: "circle",
        symbolSize: 6,
        showSymbol: false,
        itemStyle: {
          color: "#fac858",
          borderWidth: 1,
          borderColor: "#fff",
        },
        areaStyle: {
          color: "#fac858",
        },
        data: [13500, 23300, 20100, 15400],
        zlevel: 2,
      },
      {
        name: "R&D",
        type: "line",
        symbol: "circle",
        symbolSize: 6,
        showSymbol: false,
        itemStyle: {
          color: "#ee6666",
          borderWidth: 1,
          borderColor: "#fff",
        },
        areaStyle: {
          color: "#ee6666",
        },
        data: [98000, 77000, 101000, 99000],
        zlevel: 1,
      },
    ],
    textStyle: {
      fontFamily: "NanumSquare",
    },
  };
  cstmSalesChart.setOption(cstmSalesGraphOp);
}

/* 재무제표 현황 */
const finStateChartDom = document.getElementById("finStateGraph");
if (finStateChartDom) {
  const finStateChart = echarts.init(finStateChartDom);
  let finStateGraphOp;

  finStateGraphOp = {
    // color: [
    //   "#1e70e7",
    //   "#3ba272",
    //   "#fac858",
    //   "#ee6666",
    //   "#5aa7de",
    //   "#fc8452",
    //   "#7780e4",
    //   "#eb9ac9",
    //   "#b2e26e",
    //   "#007a70",
    //   "#00b3a4",
    //   "#7a8489",
    //   "#bfc5c9",
    //   "#294700",
    // ],
    tooltip: {
      trigger: "axis",
      axisPointer: {
        type: "shadow",
      },
    },
    legend: {
      top: "bottom",
      left: "center",
      textStyle: {
        fontSize: 11,
      },
      itemWidth: 14,
      itemHeight: 10,
      padding: 0,
    },
    grid: {
      top: "12%",
      left: "1%",
      right: "4%",
      bottom: "10%",
      containLabel: true,
    },
    xAxis: [
      {
        type: "category",
        data: ["2019", "2020", "2021", "2022"],
      },
    ],
    yAxis: [
      {
        type: "value",
        name: "단위: 만원",
      },
    ],
    series: [
      {
        name: "총자산",
        type: "bar",
        emphasis: {
          focus: "series",
        },
        data: [300000, 510000, 1200000, 1500000],
        barWidth: 16,
        itemStyle: {
          color: "#3ba272",
        },
      },
      {
        name: "자본금",
        type: "bar",
        emphasis: {
          focus: "series",
        },
        data: [10000, 10000, 30000, 50000],
        barWidth: 16,
        itemStyle: {
          color: "#007a70",
        },
      },
      {
        name: "자기자본",
        type: "bar",
        emphasis: {
          focus: "series",
        },
        data: [280000, 450000, 850000, 880000],
        barWidth: 16,
        itemStyle: {
          color: "#b2e26e",
        },
      },
      {
        name: "유동부채",
        type: "bar",
        emphasis: {
          focus: "series",
        },
        data: [20000, 120000, 250000, 300000],
        barWidth: 16,
        itemStyle: {
          color: "#fac858",
        },
      },
      {
        name: "컨설팅",
        type: "bar",
        stack: "sales",
        data: [25000, 15000, 20000, 40000],
        barWidth: 16,
        emphasis: {
          focus: "series",
        },
        itemStyle: {
          color: "#7780e4",
        },
      },
      {
        name: "시스템개발",
        type: "bar",
        stack: "sales",
        emphasis: {
          focus: "series",
        },
        data: [40000, 450000, 510000, 530000],
        barWidth: 16,
        itemStyle: {
          color: "#ee6666",
        },
      },
      {
        name: "유지보수",
        type: "bar",
        stack: "sales",
        emphasis: {
          focus: "series",
        },
        data: [200000, 400000, 800000, 800000],
        barWidth: 16,
        itemStyle: {
          color: "#5aa7de",
        },
      },
      {
        name: "응용S/W",
        type: "bar",
        stack: "sales",
        emphasis: {
          focus: "series",
        },
        data: [80000, 60000, 400000, 450000],
        barWidth: 16,
        itemStyle: {
          color: "#eb9ac9",
        },
      },
      {
        name: "총매출액",
        type: "bar",
        emphasis: {
          focus: "series",
        },
        data: [345000, 925000, 1730000, 1820000],
        barWidth: 16,
        itemStyle: {
          color: "#1e70e7",
        },
        markLine: {
          lineStyle: {
            type: "dashed",
            color: "#424a54",
          },
          label: {
            position: "insideEndTop",
            fontSize: 10,
          },
          data: [[{ type: "min" }, { type: "max" }]],
        },
      },
    ],
    textStyle: {
      fontFamily: "NanumSquare",
    },
  };
  finStateChart.setOption(finStateGraphOp);
}

/* 사원 현황 */
const manPowerChartDom = document.getElementById("manPowerGraph");
if (manPowerChartDom) {
  const manPowerChart = echarts.init(manPowerChartDom);
  let manPowerGraphOp;

  manPowerGraphOp = {
    title: {
      subtext: "단위: 명",
      left: "center",
      top: "bottom",
      padding: 10,
    },
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b}: {c} ({d}%)",
    },
    color: [
      "#1e70e7",
      "#3ba272",
      "#fac858",
      "#ee6666",
      "#5aa7de",
      "#fc8452",
      "#7780e4",
      "#eb9ac9",
      "#b2e26e",
      "#007a70",
      "#00b3a4",
      "#7a8489",
      "#bfc5c9",
      "#294700",
    ],
    series: [
      {
        name: "부서",
        type: "pie",
        selectedMode: "single",
        radius: [0, "50%"],
        center: ["50%", "45%"],
        label: {
          position: "inner",
          fontSize: 10,
          color: "#fff",
        },
        labelLine: {
          show: false,
        },
        data: [
          { value: 20, name: "개발" },
          { value: 3, name: "프리랜서" },
          { value: 11, name: "디자인" },
          { value: 7, name: "경영" },
          { value: 5, name: "게임" },
        ],
      },
      {
        name: "근무지",
        type: "pie",
        radius: ["65%", "80%"],
        center: ["50%", "45%"],
        data: [
          { value: 26, name: "본사" },
          { value: 15, name: "한전KDN" },
          { value: 2, name: "농어촌공사" },
          { value: 4, name: "우정사업본부" },
          { value: 2, name: "대전KDN" },
          { value: 4, name: "전력연구원" },
        ],
      },
    ],
    textStyle: {
      fontFamily: "NanumSquare",
    },
  };
  manPowerChart.setOption(manPowerGraphOp);
}

/* 기술자 보유 현황 */
const techHoldChartDom = document.getElementById("techHoldGraph");
if (techHoldChartDom) {
  const techHoldChart = echarts.init(techHoldChartDom);
  let techHoldGraphOp;

  techHoldGraphOp = {
    tooltip: {
      trigger: "axis",
      axisPointer: {
        type: "shadow",
      },
    },
    legend: {
      padding: 0,
    },
    grid: {
      left: 0,
      top: "30%",
      right: 0,
      bottom: 0,
      containLabel: true,
    },
    xAxis: {
      type: "category",
      data: ["기술사", "특급기술자", "고급기술자", "중급기술자", "초급기술자"],
      axisLabel: {
        interval: 0,
        fontSize: 11,
      },
    },
    yAxis: {
      type: "value",
      name: "단위: 명",
    },
    label: {
      show: true,
      position: "top",
      fontSize: 10,
    },
    series: [
      {
        name: "남성",
        type: "bar",
        data: [0, 2, 9, 10, 18],
        barWidth: 16,
        itemStyle: {
          color: "#1e70e7",
        },
      },
      {
        name: "여성",
        type: "bar",
        data: [0, 0, 1, 5, 8],
        barWidth: 16,
        itemStyle: {
          color: "#3ba272",
        },
      },
    ],
    textStyle: {
      fontFamily: "NanumSquare",
    },
  };
  techHoldChart.setOption(techHoldGraphOp);
}

/* 부서별 인원 현황 */
const deptMpChartDom = document.getElementById("deptMpGraph");
if (deptMpChartDom) {
  const deptMpChart = echarts.init(deptMpChartDom);
  let deptMpGraphOp;

  deptMpGraphOp = {
    color: [
      "#1e70e7",
      "#3ba272",
      "#fac858",
      "#ee6666",
      "#5aa7de",
      "#fc8452",
      "#7780e4",
      "#eb9ac9",
      "#b2e26e",
      "#007a70",
      "#00b3a4",
      "#7a8489",
      "#bfc5c9",
      "#294700",
    ],
    title: {
      subtext: "단위: 명",
      left: "center",
      top: "bottom",
      padding: 10,
    },
    tooltip: {
      trigger: "item",
    },
    grid: {
      top: 0,
      bottom: 0,
      containLabel: true,
    },
    series: [
      {
        name: "부서",
        type: "pie",
        radius: [10, 80],
        center: ["50%", "40%"],
        roseType: "area",
        itemStyle: {
          borderRadius: 8,
        },
        label: {
          formatter: "{b}: {c}",
          lineHeight: 14,
        },
        data: [
          { value: 8, name: "기업부설연구소" },
          { value: 2, name: "전략기획" },
          { value: 3, name: "경영지원" },
          { value: 7, name: "컨텐츠사업" },
          { value: 10, name: "솔루션사업" },
          { value: 22, name: "시스템사업" },
          { value: 20, name: "공공사업" },
          { value: 18, name: "전력사업" },
        ],
      },
    ],
    textStyle: {
      fontFamily: "NanumSquare",
    },
  };
  deptMpChart.setOption(deptMpGraphOp);
}

/* 차트 - 근속년수 현황 */
const workYearsChartDom = document.getElementById("workYearsGraph");
if (workYearsChartDom) {
  const workYearsChart = echarts.init(workYearsChartDom);
  let workYearsGraphOp;
  let workYearsData = [
    [3, "3개월이하"],
    [10, "3개월이상"],
    [25, "6개월이상"],
    [8, "1년이상"],
    [3, "2년이상"],
    [4, "3년이상"],
  ];
  let workYears = [];

  workYearsData.forEach((data) => {
    workYears.push(data[0]);
    dataMax = Math.max.apply(null, workYears);
    return dataMax;
  });

  workYearsGraphOp = {
    tooltip: {
      trigger: "axis",
      axisPointer: {
        type: "line",
      },
    },
    dataset: {
      source: [
        ["amount", "period"],
        [3, "3개월이하"],
        [10, "3개월이상"],
        [25, "6개월이상"],
        [8, "1년이상"],
        [3, "2년이상"],
        [4, "3년이상"],
      ],
    },
    grid: { containLabel: true, left: 2, right: 10, bottom: 0, top: "32%" },
    yAxis: { name: "단위: 명" },
    xAxis: {
      type: "category",
      axisLabel: {
        interval: 0,
        fontSize: 10,
      },
    },
    visualMap: {
      show: true,
      type: "continuous",
      orient: "horizontal",
      top: 0,
      left: "center",
      padding: 0,
      text: ["max", "min"],
      min: 0,
      max: dataMax,
      itemHeight: "100%",
      itemWidth: "14%",
      dimension: 0,
      textStyle: {
        fontSize: 11,
      },
      inRange: {
        color: ["#626d7c", "#8ab5f2", "#1e70e7"],
      },
    },
    series: [
      {
        type: "bar",
        encode: {
          x: "period",
          y: "amount",
        },
        barWidth: 16,
        label: {
          show: true,
          position: "top",
          distance: 12,
          align: "center",
          verticalAlign: "top",
          fontSize: 10,
        },
      },
    ],
    textStyle: {
      fontFamily: "NanumSquare",
    },
  };
  workYearsChart.setOption(workYearsGraphOp);
}