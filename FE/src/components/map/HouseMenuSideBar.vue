<template>
  <div>
    <b-sidebar :width="`40%`" id="sidebar-1" title="주거 지원 제도" shadow>
      <div class="px-3 py-2" v-if="flag">
        <b-card class="mb-3" v-for="(a, index) in assist" :key="index">
          <b-card-text>
            <b-list-group-item>정책일련번호: {{ a.polyBizSecd._text }}</b-list-group-item>
            <b-list-group-item>정책명: {{ a.polyBizSjnm._cdata }}</b-list-group-item>
            <b-list-group-item>정책소개: {{ a.polyItcnCn._cdata }}</b-list-group-item>
            <b-list-group-item>지원규모: {{ a.sporScvl._cdata }}</b-list-group-item>
            <b-list-group-item>지원내용: {{ a.sporCn._cdata }}</b-list-group-item>
            <b-list-group-item>신청기간: {{ a.rqutPrdCn._cdata }}</b-list-group-item>
            <b-list-group-item
              >자세한 내용은 링크를 확인해주세요. <br />
              (<a
                v-if="a.rqutUrla._cdata.substr(0, 2) == 'ht'"
                :href="a.rqutUrla._cdata"
                target="_blank"
                >{{ a.rqutUrla._cdata }}</a
              >)
            </b-list-group-item>
          </b-card-text>
        </b-card>
      </div>
      <div class="px-3 py-2" v-else>
        <b-card-text> <h2>지원 가능한 정책이 없습니다.</h2> </b-card-text>
      </div>
    </b-sidebar>
  </div>
</template>

<script>
import { youthInstance } from "@/api/index.js";
import { xml2js } from "xml-js";
import { mapState } from "vuex";

const mapStore = "mapStore";

export default {
  name: "HouseMenu",
  data() {
    return {
      http: youthInstance(),
      sido: [
        { code: "003002001", name: "서울특별시", sidoCode: "11" },
        { code: "003002002", name: "부산광역시", sidoCode: "26" },
        { code: "003002003", name: "대구광역시", sidoCode: "27" },
        { code: "003002004", name: "인천광역시", sidoCode: "28" },
        { code: "003002005", name: "광주광역시", sidoCode: "29" },
        { code: "003002006", name: "대전광역시", sidoCode: "30" },
        { code: "003002007", name: "울산광역시", sidoCode: "31" },
        { code: "003002008", name: "경기도", sidoCode: "41" },
        { code: "003002009", name: "강원도", sidoCode: "42" },
        { code: "003002010", name: "충청북도", sidoCode: "43" },
        { code: "003002011", name: "충청남도", sidoCode: "44" },
        { code: "003002012", name: "전라북도", sidoCode: "45" },
        { code: "003002013", name: "전라남도", sidoCode: "46" },
        { code: "003002014", name: "경상북도", sidoCode: "47" },
        { code: "003002015", name: "경상남도", sidoCode: "48" },
        { code: "003002016", name: "제주특별자치도", sidoCode: "50" },
        { code: "003002017", name: "세종특별자치시", sidoCode: "36" },
      ],
      selectHouse: {},
      assist: [],
      srchsrchPolyBizSecd: "",
      flag: true,
    };
  },
  computed: {
    ...mapState(mapStore, ["house", "selectsido"]),
  },
  created() {
    const url = "https://www.youthcenter.go.kr/opi/empList.do";
    const pageIndex = "1";
    const display = "30";
    const query = "주거";
    const bizTycdSel = "004003,004003002";
    const openApiVlak = "8482b2af1543273c2ce4f5c0";
    const srchPolyBizSecd = this.mappingSido(); // 일단 서울로 셋팅

    const fullURL =
      url +
      "?pageIndex=" +
      pageIndex +
      "&display=" +
      display +
      "&query=" +
      query +
      "&bizTycdSel=" +
      bizTycdSel +
      "&openApiVlak=" +
      openApiVlak +
      "&srchPolyBizSecd=" +
      srchPolyBizSecd;

    this.http.get(fullURL).then((response) => {
      const options = {
        compact: true,
        ignoreComment: true,
        spaces: 4,
      };

      const json = xml2js(response.data, options); // XML을 JSON으로 변환
      if (json.empsInfo.emp.length == 0) {
        this.flag = false;
      } else {
        this.flag = true;
        this.assist = json.empsInfo.emp;
      }

      // console.log(this.assist[0].polyBizSecd._cdata);
    });
  },
  methods: {
    mappingSido() {
      for (let i = 0; i < this.sido.length; i++) {
        if (this.sido[i].sidoCode == this.selectsido) {
          return this.sido[i].code;
        }
      }
    },
  },
};
</script>

<style scoped></style>
