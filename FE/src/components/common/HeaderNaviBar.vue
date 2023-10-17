<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="variant" class="shadow-sm p-3 bg-white rounded">
      <b-navbar-nav>
        <b-nav-item href="#">
          <router-link :to="{ name: 'main' }" class="link">
            <b-icon icon="house" font-scale="1" style="color: #5f04b4"></b-icon>
            <span style="color: #5f04b4">FineHouse</span>
          </router-link>
        </b-nav-item>
      </b-navbar-nav>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'board' }" class="link" style="color: black"
              >공지사항</router-link
            >
          </b-nav-item>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'map' }" class="link" style="color: black">원룸</router-link>
          </b-nav-item>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'omap' }" class="link" style="color: black"
              >오피스텔</router-link
            >
          </b-nav-item>

          <b-nav-item href="#"
            ><router-link :to="{ name: 'calculator' }" class="link" style="color: black"
              >전/월세 변환기</router-link
            >
          </b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="!userInfo">
          <b-nav-item>
            <router-link :to="{ name: 'login' }" class="link" style="color: black"
              >로그인</router-link
            >
          </b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item>
            <div>{{ userInfo.name }}({{ userInfo.userId }}) 님 환영합니다</div>
          </b-nav-item>

          <b-nav-item>
            <router-link :to="{ name: 'mypage' }" class="link" style="color: black"
              >마이페이지</router-link
            >
          </b-nav-item>
          <b-nav-item @click.prevent="Logout">
            <a href="#">로그아웃</a>
          </b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  data() {
    return {
      message: "",
    };
  },
  computed: {
    //처음부터 가져오는 로그인 정보
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    Logout() {
      //this.$store.commit(SET_IS_LOGIN);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped></style>
