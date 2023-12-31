import Vue from "vue";
import Vuex from "vuex";
import memberStore from "@/store/modules/memberStore.js";
import mapStore from "@/store/modules/mapStore.js";
//import http from "@/api/index";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    memberStore,
    mapStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
