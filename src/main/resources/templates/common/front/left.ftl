<form class="box">
    <div class="tit">기간설정</div>
    <div class="input-group">
        <input type="radio" name="term" id="term0" value="0" checked/>
        <label for="term0">최근</label>
        <input type="radio" name="term" id="term1" value="5"/>
        <label for="term1">5년</label>
        <input type="radio" name="term" id="term2" value="10"/>
        <label for="term2">10년</label>
        <input type="radio" name="term" id="termDatePick"/>
        <label for="termDatePick">기간선택</label>
    </div>
    <div class="date-picker-group">
        <input
                type="text"
                name="termDatePicker"
                class="w-100"
                disabled
        />
        <i class="ri-calendar-line"></i>
    </div>
    <button type="button" class="btn-primary w-100" id="termSetting">
        <i class="ri-check-line"></i>적용
    </button>
</form>
<form class="box">
    <div class="tit">지역설정</div>
    <div class="input-group">
        <input type="radio" name="ecLoc" id="ecLocAll" checked/>
        <label for="ecLocAll">전국</label>
        <input type="radio" name="ecLoc" id="ecLocSlcts"/>
        <label for="ecLocSlcts">지역선택</label>
    </div>
    <div class="select-group">
        <label for="locSlct1">시/도</label>
        <select name="locSlct" id="locSlct1" disabled>
            <option value="">서울특별시</option>
            <option value="">부산광역시</option>
            <option value="">대구광역시</option>
            <option value="">인천광역시</option>
            <option value="">광주광역시</option>
            <option value="">대전광역시</option>
            <option value="">울산광역시</option>
            <option value="">세종특별자치시</option>
            <option value="">경기도</option>
            <option value="">강원도</option>
            <option value="">충청북도</option>
            <option value="">충청남도</option>
            <option value="">전라북도</option>
            <option value="">전라남도</option>
            <option value="">경상북도</option>
            <option value="">경상남도</option>
            <option value="">제주특별자치도</option>
        </select>
    </div>
    <div class="select-group">
        <label for="locSlct2">시/군/구</label>
        <select name="locSlct" id="locSlct2" disabled>
            <option value="">강남구</option>
            <option value="">강동구</option>
            <option value="">강북구</option>
            <option value="">강서구</option>
            <option value="">관악구</option>
            <option value="">광진구</option>
            <option value="">구로구</option>
            <option value="">금천구</option>
        </select>
    </div>
    <div class="select-group">
        <label for="locSlct3">읍/면/동</label>
        <select name="locSlct" id="locSlct3" disabled>
            <option value="">신사동</option>
            <option value="">논현동</option>
            <option value="">압구정동</option>
            <option value="">청담동</option>
            <option value="">삼성동</option>
            <option value="">대치동</option>
            <option value="">역삼동</option>
            <option value="">도곡동</option>
            <option value="">개포동</option>
        </select>
    </div>
    <button type="button" class="btn-primary w-100">
        <i class="ri-check-line"></i>적용
    </button>
</form>
<div class="btn-wrap">
    <button type="button" class="btn-secondary w-100">
        AI Display
    </button>
    <button type="button" class="btn-secondary w-100">
        File Report
    </button>
</div>