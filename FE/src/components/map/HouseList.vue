<template>
  <div class="mt-4">
    <b-card
      :img-src="require('@/assets/' + randnum[index] + '.jpg')"
      img-left
      class="mb-3"
      v-model="hs"
      v-for="(h, index) in hs"
      :key="index"
      @click="moveCenter(index)"
    >
      <b-card-text>
        <h4 v-if="h.월세금액 == 0">
          전세 <br />
          {{ h.보증금액 }}만원
        </h4>
        <h4 v-else>
          월세 <br />
          {{ h.보증금액 }}만원/{{ h.월세금액 }}만원
        </h4>
        <b-list-group-item>{{ h.연립다세대 }}</b-list-group-item>
        <b-list-group-item>{{ h.법정동 }}-{{ h.지번 }} {{ h.층 }}층</b-list-group-item>
        <b-list-group-item>{{ h.전용면적 }}㎡</b-list-group-item>
        <b-list-group-item>
          <b-button pill variant="outline-light" @click="clickDetail(h, index)"
            ><img src="@/assets/detail.png" style="width: 100px"
          /></b-button>
          <!-- <button @click="clickDetail(h, index)">상세보기</button> -->
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
  components: {},
  name: "HouseList",
  data() {
    return {
      hs: [],
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
    ...mapState(mapStore, ["sidos", "guguns", "dongs", "houses", "house", "check"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(mapStore, ["setHouseDetail", "setLikeCheck"]),
    ...mapMutations(mapStore, ["SET_DETAIL_HOUSE"]),
    goDetail(h, imgnum) {
      this.$emit("changeToggle1", false);
      this.detail.id = this.userInfo.userId;
      this.detail.jeon = h.보증금액;
      this.detail.wal = h.월세금액;
      this.detail.name = h.연립다세대;
      this.detail.dong = h.법정동;
      this.detail.jibun = h.지번;
      this.detail.floor = String(h.층); //  db dto때문에 string 변환
      this.detail.area = h.전용면적;
      this.detail.construction = h.건축년도;

      if (isNaN(imgnum)) this.detail.img = 0;
      else this.detail.img = imgnum;

      if (h.월세금액 == 0) {
        this.detail.type = "전세";
      }
      this.setHouseDetail(this.detail);
    },
    moveCenter(index) {
      // 지역 코드 찾는 for문
      for (let i = 0; i < 18; i++) {
        if (String(this.hs[index].지역코드).substr(0, 2) == this.sidos[i].value) {
          this.detail.sidoname = this.sidos[i].text;
          break;
        }
      }
      let addr = this.detail.sidoname + " " + this.hs[index].법정동 + " " + this.hs[index].지번;
      console.log("선택된 주소", addr);
      this.$emit("addr", addr);
      this.$emit("flag", true);
    },
    moveCenterOnly(index) {
      // 지역 코드 찾는 for문
      for (let i = 0; i < 18; i++) {
        if (String(this.hs[index].지역코드).substr(0, 2) == this.sidos[i].value) {
          this.detail.sidoname = this.sidos[i].text;
          break;
        }
      }
      let addr = this.detail.sidoname + " " + this.hs[index].법정동 + " " + this.hs[index].지번;
      console.log("선택된 주소", addr);
      this.$emit("addr", addr);
      this.$emit("flag", false);
    },
    isCheck() {
      http
        .post(`/like/getOne`, this.detail)
        .then((response) => {
          console.log("GetOne resp", response);
          if (response.data == "") {
            // 목록에 없으면 0
            console.log("getOne false");
            this.setLikeCheck(false);
          } else {
            console.log("getOne true"); // 목록에 있으면 1
            this.setLikeCheck(true);
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
    houses() {
      this.hs = this.houses;
      // console.log("hs", this.hs);
      for (var i = 0; i < this.houses.length; i++) {
        this.randnum[i] = Math.floor(Math.random() * 20) + 1;
        console.log(this.randnum[i]);
      }
    },
    house() {},
  },
};
</script>

<style scoped></style>
