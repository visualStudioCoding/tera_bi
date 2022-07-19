<#include "*/common/navigation.ftl"/>
<script>
    function fnParam(frm){
        return kosisParam = {
            "itmId": frm.itmId.value,
            "objL1": frm.objL1.value,
            "objL2": frm.objL2.value,
            "objL3": "",
            "objL4": "",
            "objL5": "",
            "objL6": "",
            "objL7": "",
            "objL8": "",
            "prdSe": frm.prdSe.value,
            "startPrdDe": frm.startPrdDe.value,
            "endPrdDe": frm.startPrdDe.value,
            "loadGubun": frm.loadGubun.value,
            "newEstPrdCnt": frm.newEstPrdCnt.value,
            "orgId": frm.orgId.value,
            "tblId": frm.tblId.value
        }
    }
    //1. 삶의 만족도
    function api1(){
        const frm = document.forms['frm1'];
        let param = fnParam(frm);
        let errorMsg = "error";
        let result="";

        //ajax가 정상 호출 되었을때 실행 되는 함수
        $("#rst1").html("");
        let callBackFn = function( data ) {
            //alert(data.success + " ,  " +data.size);
            result += data.data.yrdt + " " + data.success + " ,  " + data.size + "건<br>";
            $("#rst1").html(result);
            console.log(data.data)
        }
        //공통모듈 ajax 함수 호출하기
        kosisApiAjax("/lifeSatisfaction/LifeSatisfaction", callBackFn, 'get', param, errorMsg);
    }

    //2. 월별 혼인
    function api2(){
        const frm = document.forms['frm2'];
        let param = fnParam(frm);
        let errorMsg = "error";
        let result="";

        //ajax가 정상 호출 되었을때 실행 되는 함수
        $("#rst2").html("");
        let callBackFn = function( data ) {
            //alert(data.success + " ,  " +data.size);
            result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " + data.size + "건<br>";
            $("#rst2").html(result);
            console.log(data.data)
        }
        let syear = frm.startPrdDe.value;
        syear = syear.substr(0,4);
        let month = ["01","02","03","04","05","06","07","08","09","10","11","12"];

        for(let i=0;i<month.length;i++){
            let yearmonth = syear + month[i];
            frm.startPrdDe.value = yearmonth;
            //frm.endPrdDe.value = yearmonth;
            let param = fnParam(frm);
            console.log(param);
            kosisApiAjax("/lifeSatisfaction/Marriage", callBackFn, 'get', param, errorMsg);
        }

        //공통모듈 ajax 함수 호출하기
        //kosisApiAjax("/lifeSatisfaction/Marriage", callBackFn, 'get', param, errorMsg);
    }

    //3. 월별 이혼
    function api3(){
        const frm = document.forms['frm3'];
        //let param = fnParam(frm);
        let errorMsg = "error";
        let result="";

        //ajax가 정상 호출 되었을때 실행 되는 함수
        $("#rst3").html("");
        let callBackFn = function( data ) {
            //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
            result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size + "건<br>";
            $("#rst3").html(result);
            console.log(data.data)
        }
        let syear = frm.startPrdDe.value;
        syear = syear.substr(0,4);
        let month = ["01","02","03","04","05","06","07","08","09","10","11","12"];

        for(let i=0;i<month.length;i++){
            let yearmonth = syear + month[i];
            frm.startPrdDe.value = yearmonth;
            let param = fnParam(frm);
            console.log(param);
            kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
        }

        //공통모듈 ajax 함수 호출하기
        //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
    }

    //4. 고용률
    function api4(){
        const frm = document.forms['frm4'];
        //let param = fnParam(frm);
        let errorMsg = "error";
        let result="";

        //ajax가 정상 호출 되었을때 실행 되는 함수
        $("#rst4").html("");
        let callBackFn = function( data ) {
            //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
            result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size + "건<br>";
            $("#rst4").html(result);
            console.log(data.data)
        }
        let syear = frm.startPrdDe.value;
        syear = syear.substr(0,4);
        let month = ["01","02","03","04","05","06","07","08","09","10","11","12"];

        for(let i=0;i<month.length;i++){
            let yearmonth = syear + month[i];
            frm.startPrdDe.value = yearmonth;
            let param = fnParam(frm);
            console.log(param);
            kosisApiAjax("/lifeSatisfaction/emplyrate", callBackFn, 'get', param, errorMsg);
        }

        //공통모듈 ajax 함수 호출하기
        //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
    }

    //5. 행정구역(시도)/성별 실업률
    function api5(){
        const frm = document.forms['frm5'];
        //let param = fnParam(frm);
        let errorMsg = "error";
        let result="";

        //ajax가 정상 호출 되었을때 실행 되는 함수
        $("#rst5").html("");
        let callBackFn = function( data ) {
            //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
            result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size + "건<br>";
            $("#rst5").html(result);
            console.log(data.data)
        }
        let syear = frm.startPrdDe.value;
        syear = syear.substr(0,4);
        let month = ["01","02","03","04","05","06","07","08","09","10","11","12"];

        for(let i=0;i<month.length;i++){
            let yearmonth = syear + month[i];
            frm.startPrdDe.value = yearmonth;
            let param = fnParam(frm);
            console.log(param);
            kosisApiAjax("/lifeSatisfaction/unmplrate", callBackFn, 'get', param, errorMsg);
        }

        //공통모듈 ajax 함수 호출하기
        //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
    }

    //6. 전산업생산지수
    function api6(){
        const frm = document.forms['frm6'];
        //let param = fnParam(frm);
        let errorMsg = "error";
        let result="";

        //ajax가 정상 호출 되었을때 실행 되는 함수
        $("#rst6").html("");
        let callBackFn = function( data ) {
            //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
            result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size + "건<br>";
            $("#rst6").html(result);
            console.log(data.data)
        }
        let syear = frm.startPrdDe.value;
        syear = syear.substr(0,4);
        let month = ["01","02","03","04","05","06","07","08","09","10","11","12"];

        for(let i=0;i<month.length;i++){
            let yearmonth = syear + month[i];
            frm.startPrdDe.value = yearmonth;
            let param = fnParam(frm);
            console.log(param);
            kosisApiAjax("/lifeSatisfaction/allprindex", callBackFn, 'get', param, errorMsg);
        }

        //공통모듈 ajax 함수 호출하기
        //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
    }
</script>
<div class="container">
    <div class="row">
        <div class="col">
            <form id="frm1" name="frm1" onsubmit="api1();return false">
                <div class="card">
                    <input type="hidden" id="itmId" name="itmId" value="T1+"/>
                    <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                    <input type="hidden" id="objL2" name="objL2" value="ALL"/>
                    <input type="hidden" id="prdSe" name="prdSe" value="Y"/>
                    <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                    <input type="hidden" id="orgId" name="orgId" value="417"/>
                    <input type="hidden" id="tblId" name="tblId" value="DT_417001_0002"/>
                    <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>

                    <div class="card-header">
                        1.삶의 만족도 (년)
                    </div>
                    <div class="card-body">
                        년도입력 <input type="text" name="startPrdDe" id="startPrdDe" value="${data1.yr_dt}" style="width:100%" >
                        <div id="rst1"></div>
                    </div>
                    <button type="submit" class="btn btn-outline-secondary activator" >실행</button>
                </div>
            </form>
        </div>
        <div class="col">
            <form id="frm2" name="frm2" onsubmit="api2();return false">
                <input type="hidden" id="itmId" name="itmId" value="T3+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value=""/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1B83A35"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        2. 시도/시군구/월별 혼인 (월)
                    </div>
                    <div class="card-body">
                        <#--<select class="form-select" aria-label="Default select example">
                            <option selected>Open this select menu</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                            <label class="form-check-label" for="flexCheckDefault">
                                Default checkbox
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                            <label class="form-check-label" for="flexCheckChecked">
                                Checked checkbox
                            </label>
                        </div>-->

                        년도 <input type="text" name="startPrdDe" id="startPrdDe" value="${data2.yr_dt}${data2.mon_dt}" style="width:100%" >
                        <div id="rst2"></div>
                    </div>
                    <button type="submit" class="btn btn-outline-secondary activator">실행</button>
                </div></form>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <form id="frm3" name="frm3" onsubmit="api3();return false">
                <input type="hidden" id="itmId" name="itmId" value="T3+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value=""/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1B83A35"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        3. 시도/시군구/월별 이혼 (월)
                    </div>
                    <div class="card-body">
                        <#--<select class="form-select" aria-label="Default select example">
                            <option selected>Open this select menu</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                            <label class="form-check-label" for="flexCheckDefault">
                                Default checkbox
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                            <label class="form-check-label" for="flexCheckChecked">
                                Checked checkbox
                            </label>
                        </div>-->
                        년도입력 <input type="text" name="startPrdDe" id="startPrdDe" value="${data3.yr_dt}${data3.mon_dt}" style="width:100%" >
                        <div id="rst3"></div>
                    </div>

                    <button type="submit" class="btn btn-outline-secondary activator" >실행</button>
                </div></form>
        </div>
        <div class="col">
            <form id="frm4" name="frm4"  onsubmit="api4();return false">
                <input type="hidden" id="itmId" name="itmId" value="T10+T20+T50+T60+T80+T90+T100+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value="ALL"/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1DA7014S"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
                <div class="card">
                    <div class="card-header">
                        4.고용률(시/군/구)(월)
                    </div>
                    <div class="card-body">
                        <#-- <select class="form-select" aria-label="Default select example">
                             <option selected>Open this select menu</option>
                             <option value="1">One</option>
                             <option value="2">Two</option>
                             <option value="3">Three</option>
                         </select>
                         <div class="form-check">
                             <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                             <label class="form-check-label" for="flexCheckDefault">
                                 Default checkbox
                             </label>
                         </div>
                         <div class="form-check">
                             <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                             <label class="form-check-label" for="flexCheckChecked">
                                 Checked checkbox
                             </label>
                         </div>-->
                        년도입력 <input type="text" name="startPrdDe" id="startPrdDe" value="${data4.yr_dt}${data4.mon_dt}" style="width:100%" >
                        <div id="rst4"></div>
                    </div>
                    <button type="button" class="btn btn-outline-secondary activator" onclick="api4();">실행</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <form id="frm5" name="frm5" onsubmit="api5();return false">
                <input type="hidden" id="itmId" name="itmId" value="T80+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value="ALL"/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1DA7104S"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
            <div class="card">
                <div class="card-header">
                    5. 행정구역(시도)/성별 실업률(월)
                </div>
                <div class="card-body">
                    년월입력 <input type="text" name="startPrdDe" id="startPrdDe" value="${data5.yr_dt}${data5.mon_dt}" style="width:100%" >
                    <div id="rst5"></div>
                </div>
                <button type="submit" class="btn btn-outline-secondary activator" >실행</button>
            </div></form>
        </div>
        <div class="col">
            <form id="frm6" name="frm6" onsubmit="api6();return false">
                <input type="hidden" id="itmId" name="itmId" value="T1+"/>
                <input type="hidden" id="objL1" name="objL1" value="ALL"/>
                <input type="hidden" id="objL2" name="objL2" value=""/>
                <input type="hidden" id="prdSe" name="prdSe" value="M"/>
                <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
                <input type="hidden" id="orgId" name="orgId" value="101"/>
                <input type="hidden" id="tblId" name="tblId" value="DT_1JH20151"/>
                <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value=""/>
            <div class="card">
                <div class="card-header">
                    6. 전산업생산지수(월)
                </div>
                <div class="card-body">
                    년월입력 <input type="text" name="startPrdDe" id="startPrdDe" value="${data6.yr_dt}${data6.mon_dt}" style="width:100%" >
                    <div id="rst6"></div>
                </div>
                <button type="submit" class="btn btn-outline-secondary activator">실행</button>
            </div></form>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    행정구역별, 성별 실업률
                </div>
                <div class="card-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Default checkbox
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Checked checkbox
                        </label>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    전 산업 생산지수
                </div>
                <div class="card-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Default checkbox
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Checked checkbox
                        </label>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    해외여행 경험 및 횟수
                </div>
                <div class="card-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Default checkbox
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Checked checkbox
                        </label>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    기업규모별 소득
                </div>
                <div class="card-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Default checkbox
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Checked checkbox
                        </label>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-secondary activator">실행</button>
            </div>
        </div>
    </div>
</div>
<#include "*/common/footer.ftl"/>

