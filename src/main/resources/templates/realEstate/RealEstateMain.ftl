<#include "*/common/navigation.ftl"/>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    행정구역별, 성별 인구 수
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
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnGenderPopulation()">실행</button>
            </div>
        </div>
        <form id="formGenderPopulation" name="formGenderPopulation">
            <input type="hidden" id="itmId" name="itmId" value="T21+T22+"/>
            <input type="hidden" id="objL1" name="objL1" value="ALL"/>
            <input type="hidden" id="prdSe" name="prdSe" value="M"/>
            <input type="hidden" id="startPrdDe" name="startPrdDe" value=""/>
            <input type="hidden" id="endPrdDe" name="endPrdDe" value=""/>
            <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value="1"/>
            <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
            <input type="hidden" id="orgId" name="orgId" value="101"/>
            <input type="hidden" id="tblId" name="tblId" value="DT_1B040A3"/>
        </form>

        <div class="col">
            <div class="card">
                <div class="card-header">
                    행정구역별 아파트 매매거래 현황
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
                <button type="button" class="btn btn-outline-secondary activator" onclick="fnAptSalesStatus()">실행</button>
            </div>
        </div>
        <form id="formAptSalesStatus" name="formAptSalesStatus">
            <input type="hidden" id="itmId" name="itmId" value="13103114448T1+"/>
            <input type="hidden" id="objL1" name="objL1" value="ALL"/>
            <input type="hidden" id="prdSe" name="prdSe" value="M"/>
            <input type="hidden" id="startPrdDe" name="startPrdDe" value=""/>
            <input type="hidden" id="endPrdDe" name="endPrdDe" value=""/>
            <input type="hidden" id="newEstPrdCnt" name="newEstPrdCnt" value="1"/>
            <input type="hidden" id="loadGubun" name="loadGubun" value="2"/>
            <input type="hidden" id="orgId" name="orgId" value="408"/>
            <input type="hidden" id="tblId" name="tblId" value="DT_408_2006_S0064"/>
        </form>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    남녀별 연령별 인구구조
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
                    지역내총생산(GRDP)
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
                    미분양주택현황
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
                    매입자연령대별 아파트 매매거래 현황
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

<script src="/js/bi/realEstate/realEstate.js"></script>

