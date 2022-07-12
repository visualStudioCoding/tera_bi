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

        //ajax가 정상 호출 되었을때 실행 되는 함수
        let callBackFn = function( data ) {
            alert(data.success + " ,  " +data.size);
            console.log(data.data)
        }
        //공통모듈 ajax 함수 호출하기
        kosisApiAjax("/life/LifeSatisfaction", callBackFn, 'get', param, errorMsg);
    }

    //2. 월별 혼인
    function api2(){
        const frm = document.forms['frm2'];
        let param = fnParam(frm);
        let errorMsg = "error";

        //ajax가 정상 호출 되었을때 실행 되는 함수
        let callBackFn = function( data ) {
            alert(data.success + " ,  " +data.size);
            console.log(data.data)
        }
        //공통모듈 ajax 함수 호출하기
        kosisApiAjax("/life/Marriage", callBackFn, 'get', param, errorMsg);
    }

    //3. 월별 이혼
    function api3(){
        const frm = document.forms['frm3'];
        //let param = fnParam(frm);
        let errorMsg = "error";
        let result="";
        let formon="";  //for 문에서 현재 월
        //ajax가 정상 호출 되었을때 실행 되는 함수
        let callBackFn = function( data ) {
            //alert(data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size);
            result += data.data.yrdt + " " + data.data.mondt + " " + data.success + " ,  " +data.size + "\n";
            console.log(data.data)

            //if(data.data.mondt == 12) {
            if(formon == 12) {
                alert(result);
            }
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
            formon = month[i];  //현재 작업중인 월
            kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
        }

        //공통모듈 ajax 함수 호출하기
        //kosisApiAjax("/lifeSatisfaction/Divorce", callBackFn, 'get', param, errorMsg);
    }
</script>
<div class="container">
    <div class="row">
        <div class="col">
                <form id="frm1" name="frm1">
            <div class="card">
                <#--https://kosis.kr/openapi/Param/statisticsParameterData.do
                ?method=getList
                &apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=
                &itmId=T1+
                &objL1=ALL
                &objL2=ALL
                &objL3=
                &objL4=
                &objL5=
                &objL6=
                &objL7=
                &objL8=
                &format=json
                &jsonVD=Y
                &prdSe=Y
                &startPrdDe=2021
                &endPrdDe=2021
                &loadGubun=2
                &orgId=417
                &tblId=DT_417001_0002-->



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
                    년도입력 <input type="text" name="startPrdDe" id="startPrdDe" value="" style="width:100%" >
                   <#-- <select class="form-select" aria-label="Default select example">
                        <option selected>...</option>
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
                </div>
                <button type="button" class="btn btn-outline-secondary activator" onclick="api1();">실행</button>
            </div>
            </form>
        </div>
        <div class="col">
            <form id="frm2" name="frm2">
                <!-- https://kosis.kr/openapi/Param/statisticsParameterData.do
                ?method=getList
                &apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=
                &itmId=T3+
                &objL1=ALL
                &objL2=
                &objL3=
                &objL4=
                &objL5=
                &objL6=
                &objL7=
                &objL8=
                &format=json
                &jsonVD=Y
                &prdSe=M
                &startPrdDe=202112
                &endPrdDe=202112
                &loadGubun=2
                &orgId=101
                &tblId=DT_1B83A35


                -->
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

                    년월입력 <input type="text" name="startPrdDe" id="startPrdDe" value="" style="width:100%" >
                </div>
                <button type="button" class="btn btn-outline-secondary activator" onclick="api2();">실행</button>
            </div></form>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <form id="frm3" name="frm3">
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
                   년월입력 <input type="text" name="startPrdDe" id="startPrdDe" value="" style="width:100%" >
                </div>

                <button type="button" class="btn btn-outline-secondary activator" onclick="api3();">실행</button>
            </div></form>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    고용률(시/군/구)
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
    </div> <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    시도/시군구/월별 이혼
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
                    고용률(시/군/구)
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

