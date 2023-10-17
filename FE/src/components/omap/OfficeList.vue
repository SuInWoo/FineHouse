<template>
  <div class="mt-4">
    <b-card
      :img-src="require('@/assets/' + randnum[index] + '.jpg')"
      img-left
      class="mb-3"
      v-model="offi"
      v-for="(o, index) in offi"
      :key="index"
      @click="moveCenter(index)"
    >
      <b-card-text>
        <h4 v-if="o.월세 == 0">
          전세 <br />
          {{ o.보증금 }}만원
        </h4>
        <h4 v-else>
          월세 <br />
          {{ o.보증금 }}만원/{{ o.월세 }}만원
        </h4>
        <b-list-group-item>{{ o.단지 }}</b-list-group-item>
        <b-list-group-item>{{ o.법정동 }}-{{ o.지번 }} {{ o.층 }}층</b-list-group-item>
        <b-list-group-item>{{ o.전용면적 }}㎡</b-list-group-item>
        <b-list-group-item
          ><b-button @click="clickDetail(o, index)">상세보기</b-button>
        </b-list-group-item>
      </b-card-text>
    </b-card>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { apiInstance } from "@/api/index.js";

const http = apiInstance();
const memberStore = "memberStore";
const mapStore = "mapStore";

export default {
  name: "OfficeList",
  data() {
    return {
      offi: [],
      detail: {
        id: "",
        sidoname: "",
        jeon: "",
        wal: 0,
        name: "",
        dong: "",
        jibun: "",
        floor: "",
        area: 0,
        img: 0,
        type: "월세",
        construction: 0,
      },
      randnum: [],
    };
  },
  computed: {
    ...mapState(mapStore, ["sidos", "guguns", "dongs", "officetels", "house"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(mapStore, ["setHouseDetail"]),
    ...mapMutations(mapStore, ["SET_DETAIL_HOUSE"]),
    goDetail(o, imgnum) {
      this.$emit("changeToggle1", false);
      this.detail.id = this.userInfo.userId;
      this.detail.jeon = o.보증금;
      this.detail.wal = o.월세;
      this.detail.name = o.단지;
      this.detail.dong = o.법정동;
      this.detail.jibun = o.지번;
      this.detail.floor = String(o.층);
      this.detail.area = o.전용면적;
      this.detail.construction = o.건축년도;

      if (isNaN(imgnum)) this.detail.img = 0;
      else this.detail.img = imgnum;

      if (o.월세금액 == 0) {
        this.detail.type = "전세";
      }
      this.setHouseDetail(this.detail);
    },
    moveCenter(index) {
      // 지역 코드 찾는 for문
      for (let i = 0; i < 18; i++) {
        if (String(this.offi[index].지역코드).substr(0, 2) == this.sidos[i].value) {
          this.detail.sidoname = this.sidos[i].text;
          break;
        }
      }
      let addr = this.detail.sidoname + " " + this.offi[index].법정동 + " " + this.offi[index].지번;
      console.log("선택된 주소", addr);
      this.$emit("addr", addr);
      this.$emit("flag", true);
    },
    moveCenterOnly(index) {
      // 지역 코드 찾는 for문
      for (let i = 0; i < 18; i++) {
        if (String(this.offi[index].지역코드).substr(0, 2) == this.sidos[i].value) {
          this.detail.sidoname = this.sidos[i].text;
          break;
        }
      }
      let addr = this.detail.sidoname + " " + this.offi[index].법정동 + " " + this.offi[index].지번;
      console.log("선택된 주소", addr);
      this.$emit("addr", addr);
      this.$emit("flag", false);
    },
    isCheck() {
      http
        .post(`/like/getOne`, this.detail)
        .then((response) => {
          console.log("getOne detail", this.detail);
          console.log(response);
          if (response.data == "") {
            // 목록에 없으면
            console.log("getOne false");
            this.$emit("check", 1);
          } else {
            console.log("getOne true"); // 목록에 있으면 0
            this.$emit("check", 0);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    clickDetail(o, index) {
      this.moveCenterOnly(index);
      this.goDetail(o, this.randnum[index]);
      this.isCheck();
    },
  },
  watch: {
    officetels() {
      this.offi = this.officetels;
      for (var i = 0; i < this.officetels.length; i++) {
        this.randnum[i] = Math.floor(Math.random() * 20) + 1;
        console.log(this.randnum[i]);
      }
    },
  },
};
</script>
<style scoped></style>
