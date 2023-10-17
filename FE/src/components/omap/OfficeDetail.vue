<template>
  <div>
    <hr />
    <b-container style="position: relative">
      <div style="position: relative; z-index: 1">
        <b-row>
          <b-col cols="8">
            <b-img :src="require('@/assets/' + house.img + '.jpg')" style="width: 100%"></b-img>
          </b-col>
          <b-col cols="4">
            <b-icon
              icon="heart"
              scale="3"
              @click="changeBothFunc"
              v-if="toggle == 1"
              style="color: red"
            ></b-icon>
            <b-icon
              icon="heart-fill"
              scale="3"
              @click="unchangeBothFunc"
              v-else
              style="color: red"
            ></b-icon>
          </b-col>
        </b-row>
      </div>
      <hr />
      <b-list-group>
        <b-list-group-item v-if="house.wal == 0">
          <b-row>
            <b-col cols="2">
              <b-img :src="require('@/assets/price.png')" style="width: 70%; float: left"></b-img>
            </b-col>
            <b-col cols="3">
              <div clss="content" style="float: left; margin-left: 5%; margin-right: 10%">
                <strong>전세</strong>
              </div>
            </b-col>
            <b-col cols="7">
              <div style="float: left">{{ house.jeon }} 만원</div>
            </b-col>
          </b-row>
        </b-list-group-item>

        <b-list-group-item v-else>
          <b-row>
            <b-col cols="2">
              <b-img :src="require('@/assets/price.png')" style="width: 70%; float: left"></b-img>
            </b-col>
            <b-col cols="3">
              <div clss="content" style="float: left; margin-left: 5%; margin-right: 10%">
                <strong>월세</strong>
              </div>
            </b-col>
            <b-col cols="7">
              <div style="float: left">{{ house.jeon }} 만원 / {{ house.wal }} 만원</div>
            </b-col>
          </b-row>
        </b-list-group-item>

        <b-list-group-item>
          <b-row>
            <b-col cols="2">
              <b-img :src="require('@/assets/title.png')" style="width: 70%; float: left"></b-img>
            </b-col>
            <b-col cols="3">
              <div clss="content" style="float: left; margin-left: 5%; margin-right: 10%">
                <strong>건물명</strong>
              </div>
            </b-col>
            <b-col cols="7">
              <div style="float: left">{{ house.name }}</div>
            </b-col>
          </b-row>
        </b-list-group-item>

        <b-list-group-item>
          <b-row>
            <b-col cols="2">
              <b-img :src="require('@/assets/address.png')" style="width: 70%; float: left"></b-img>
            </b-col>
            <b-col cols="3">
              <div clss="content" style="float: left; margin-left: 5%; margin-right: 10%">
                <strong>주소</strong>
              </div>
            </b-col>
            <b-col cols="7">
              <div style="float: left">{{ house.dong }}-{{ house.jibun }} {{ house.floor }}층</div>
            </b-col>
          </b-row>
        </b-list-group-item>

        <b-list-group-item>
          <b-row>
            <b-col cols="2">
              <b-img :src="require('@/assets/area.png')" style="width: 70%; float: left"></b-img>
            </b-col>
            <b-col cols="3">
              <div clss="content" style="float: left; margin-left: 5%; margin-right: 10%">
                <strong>전용면적</strong>
              </div>
            </b-col>
            <b-col cols="7">
              <div style="float: left">{{ house.area }}㎡ ( {{ pyeong.toFixed(2) }} 평)</div>
            </b-col>
          </b-row>
        </b-list-group-item>

        <b-list-group-item>
          <b-row>
            <b-col cols="2">
              <b-img
                :src="require('@/assets/calender.png')"
                style="width: 70%; float: left"
              ></b-img>
            </b-col>
            <b-col cols="3">
              <div clss="content" style="float: left; margin-left: 5%; margin-right: 10%">
                <strong>건축연도</strong>
              </div>
            </b-col>
            <b-col cols="7">
              <div style="float: left">{{ house.construction }}년</div>
            </b-col>
          </b-row>
        </b-list-group-item>
      </b-list-group>
    </b-container>

    <hr />
    <b-row>
      <b-col cols="3">
        <b-button pill variant="outline-light" @click="changeKeyword('도서관')"
          ><img src="@/assets/lib.png" style="width: 100px"
        /></b-button>
        <!-- :img-src="require('@/assets/libraryImg.png')" -->
        <!-- <b-button pill variant="outline-dark" @click="changeKeyword('도서관')"> 도서관 </b-button> -->
      </b-col>
      <b-col cols="3">
        <b-button pill size="sm" variant="outline-light" @click="changeKeyword('스터디 카페')"
          ><img src="@/assets/study.png" style="width: 100px"
        /></b-button>
        <!-- <b-button pill variant="outline-dark" @click="changeKeyword('스터디 카페')"
            >스터디 카페
          </b-button> -->
      </b-col>
      <b-col cols="3">
        <b-button pill size="sm" variant="outline-light" @click="changeKeyword('편의점')"
          ><img src="@/assets/store.png" style="width: 100px"
        /></b-button>
        <!-- <b-button pill variant="outline-dark" @click="changeKeyword('편의점')">편의점</b-button> -->
      </b-col>
      <b-col cols="3">
        <b-button pill size="sm" variant="outline-light" @click="changeKeyword('지하철역')"
          ><img src="@/assets/sub.png" style="width: 100px"
        /></b-button>
        <!-- <b-button pill variant="outline-dark" @click="changeKeyword('지하철역')"
            >지하철역
          </b-button> -->
      </b-col>
      <b-col cols="1"></b-col>
    </b-row>

    <b-container class="mb-3">
      <b-button size="lg" style="float: right; width: 100%; margin-top: 10px" @click="getUnivList"
        >주변 대학 검색</b-button
      >
    </b-container>
    <b-container class="mb-3">
      <b-button v-b-toggle.sidebar-1 size="lg" style="float: right; width: 100%; margin-top: 10px"
        >지원 제도 검색</b-button
      >
    </b-container>

    <office-menu-side-bar></office-menu-side-bar>
    <router-view />
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import OfficeMenuSideBar from "@/components/omap/OfficeMenuSideBar.vue";
import { apiInstance } from "@/api/index.js";
import { writeRecent } from "@/api/recent.js";

const http = apiInstance();
const mapStore = "mapStore";
const memberStore = "memberStore";

export default {
  name: "OfficeDetail",
  components: { OfficeMenuSideBar },
  props: {
    toggle: Number,
  },
  data() {
    return {
      around: "",
      arounds: ["스터디카페", "도서관", "지하철역"],
    };
  },
  created() {
    writeRecent(
      this.house,
      (response) => {
        console.log(response);
        console.log("최근목록 실행");
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(mapStore, ["house", "keyword", "selectsido", "sidos", "houseLatLng"]),
    ...mapState(memberStore, ["userInfo"]),
    pyeong() {
      return this.house.area * 0.3025;
    },
  },
  methods: {
    ...mapActions(mapStore, ["setKeyword", "setUnivList"]),
    ...mapMutations(mapStore, ["SET_KEYWORD"]),
    searchAround() {
      console.log(this.around);
    },
    changeBothFunc() {
      this.checkLikeHouse();
    },
    unchangeBothFunc() {
      this.deleteLikeHouse();
    },
    changeKeyword(keyword) {
      //var addr = this.house.dong +" "+ +" " +keyword;
      var addr = this.house.dong + " " + this.house.jibun + " " + keyword;
      this.setKeyword(addr);
    },
    getUnivList() {
      let latlng = { lat: this.houseLatLng.lat, lng: this.houseLatLng.lng };
      this.setUnivList(latlng);
    },
    checkLikeHouse() {
      http
        .post(`/like/getOne`, this.house)
        .then((response) => {
          console.log("getOne resp", response);
          this.putLikeHouse();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    putLikeHouse() {
      console.log("찜목록에 추가된 하우스", this.house);
      http
        .post(`/like/write`, JSON.stringify(this.house))
        .then((response) => {
          this.$emit("toggle", 0);
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteLikeHouse() {
      console.log("찜목록에 삭제된 하우스", this.house);
      http
        .post(`/like/delete`, JSON.stringify(this.house))
        .then((response) => {
          this.$emit("toggle", 1);
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
.mb-3 {
  margin-top: 20px;
}
.content {
  width: 500px;
  margin-left: 50px;
}
</style>
