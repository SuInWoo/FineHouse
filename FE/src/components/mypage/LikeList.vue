<template>
  <b-container class="bv-example-row mt-3">
    <b-card>
      <b-row>
        <h4>
          <b-col class="col text-left">{{ userInfo.name }} 님의 찜목록</b-col>
        </h4>
        <b-button class="ml-2 mb-3" variant="outline-info" @click="changeView(1)">전체</b-button>
        <b-button class="ml-2 mb-3" variant="outline-info" @click="changeView(2)">월세</b-button>
        <b-button class="ml-2 mb-3" variant="outline-info" @click="changeView(3)">전세</b-button>
      </b-row>

      <b-col v-if="changeIdx === 1">
        <b-row v-if="likes.length">
          <like-list-item v-for="like in likes" :key="like.lno" v-bind="like" />
        </b-row>
        <b-row v-else class="text-center">찜한 목록이 없습니다.</b-row>
      </b-col>

      <b-col v-if="changeIdx === 3">
        <b-row v-if="jeons.length">
          <like-list-item v-for="(jeon, index) in jeons" :key="index" v-bind="jeon" />
        </b-row>
        <b-row v-else class="text-center">찜한 전세 목록이 없습니다.</b-row>
      </b-col>
      <b-col v-if="changeIdx === 2">
        <b-row v-if="wals.length">
          <like-list-item v-for="(wal, index) in wals" :key="index" v-bind="wal" />
        </b-row>
        <b-row v-else class="text-center">찜한 월세 목록이 없습니다.</b-row>
      </b-col>
    </b-card>
  </b-container>
</template>

<script>
import { listLike } from "@/api/like.js";
import LikeListItem from "../mypage/LikeListItem";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "LikeList",
  components: {
    LikeListItem,
  },
  data() {
    return {
      slide: 0,
      sliding: null,
      likes: [],
      jeons: [],
      wals: [],
      changeIdx: 1,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let id = this.userInfo.userId;
    console.log("라이크 아이디", id);

    listLike(
      id,
      (response) => {
        this.likes = response.data;
        this.setJeonWal();
        // console.log("likelist", this.likes);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    setJeonWal() {
      this.jeons = [];
      this.wals = [];
      for (var i = 0; i < this.likes.length; i++) {
        if (this.likes[i].wal == 0) {
          this.jeons.push(this.likes[i]);
        } else {
          this.wals.push(this.likes[i]);
        }
      }
      //console.log("전세", this.jeons);
      //console.log("월세", this.wals);
      // console.log("전세", this.jeons.length);
    },
    onSlideStart(slide) {
      console.log(slide);
      this.sliding = true;
    },
    onSlideEnd(slide) {
      console.log(slide);
      this.sliding = false;
    },
    changeView(idx) {
      this.changeIdx = idx;
    },
  },
  watch: {
    likes() {
      listLike(
        this.userInfo.userId,
        (response) => {
          this.likes = response.data;
          this.setJeonWal();
          //console.log("likelist", this.likes);
        },
        (error) => {
          console.log(error);
        }
      );

      // if(this.likes.)
      // this.jeons = this.likes;
      // console.log("전세", this.jeons.length);
    },
  },
};
</script>

<style scoped></style>
