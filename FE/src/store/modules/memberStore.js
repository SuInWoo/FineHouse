import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      console.log("good");
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      //console.log("error");
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      console.log(userInfo);
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      console.log(user);

      await login(
        user,
        (response) => {
          console.log(response.data.message);
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            console.log(token);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            console.log("실패");
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUserInfo({ commit }, token) {
      console.log("토큰:" + token);

      let decode_token = jwt_decode(token);
      console.log(decode_token);
      findById(
        decode_token.userId,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
            console.log(response.data);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default memberStore;
