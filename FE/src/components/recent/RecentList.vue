<template>
  <div>
    <div class="mt-2"></div>
    <b-row>
      <h5>
        <b-col class="col text-left"
          ><h3 style="font-family: GTFont; padding-left: 15px">
            {{ userInfo.name }} 님의 최근 조회 매물
          </h3></b-col
        >
      </h5>
    </b-row>

    <div class="mt-3"></div>
    <b-col>
      <b-row v-if="recents.length">
        <recent-list-item v-for="(recent, index) in recents" :key="index" v-bind="recent" />
      </b-row>
      <b-row v-else>최근 조회한 매물이 없습니다.</b-row>
    </b-col>
  </div>
</template>

<script>
import { listRecent } from "@/api/recent.js";
import RecentListItem from "../recent/RecentListItem";
import { mapState } from "vuex";
import "@/assets/css/fonts.css";

const memberStore = "memberStore";

export default {
  name: "RecentList",
  components: {
    RecentListItem,
  },
  data() {
    return {
      recents: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let id = this.userInfo.userId;
    console.log("메인아이디", id);

    listRecent(
      id,
      (response) => {
        this.recents = response.data;
        //console.log("likelist", this.likes);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {},
};
</script>

<style scoped></style>
