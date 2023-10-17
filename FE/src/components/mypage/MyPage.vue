<template>
  <div>
    <div>
      <br />
      <b-container class="bv-example-row mt-3">
        <b-card>
          <b-row>
            <h4>
              <b-col class="col text-left">{{ userInfo.name }} 님의 회원 정보 </b-col>
            </h4>
          </b-row>

          <b-container class="text-left">
            <b-row>
              <b-col cols="2">아이디</b-col>
              <b-col cols="10" class="text-left">{{ userInfo.userId }} </b-col>
            </b-row>
            <b-row>
              <b-col cols="2">이름</b-col>
              <b-col cols="10" class="text-left">{{ userInfo.name }} </b-col>
            </b-row>
            <b-row>
              <b-col cols="2">이메일</b-col>
              <b-col cols="10" class="text-left">{{ userInfo.email }} </b-col>
            </b-row>
            <b-row>
              <b-col cols="2">주소</b-col>
              <b-col cols="10" class="text-left">{{ userInfo.addr }} </b-col>
            </b-row>
            <b-row>
              <b-col cols="2">전화번호</b-col>
              <b-col cols="10" class="text-left">{{ userInfo.phone }} </b-col>
            </b-row>
          </b-container>
        </b-card>
      </b-container>
    </div>

    <like-list></like-list>
  </div>
</template>

<script>
import LikeList from "../mypage/LikeList.vue";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "MyPage",
  components: {
    LikeList,
  },
  computed: {
    //처음부터 가져오는 로그인 정보
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
#input1 {
  width: 300px;
  height: 50px;
}
td {
  width: 100px;
}
</style>
