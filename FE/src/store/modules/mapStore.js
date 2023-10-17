import { apiInstance } from "@/api/index.js";

const http = apiInstance();

const mapStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    officetels: [],
    house: {},
    selectsido: 0,
    likehouse: [],
    keyword: "",
    univs: [],
    houseLatLng: {},
    check: false,
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        // console.log("setsido", sido);
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },

    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DETAIL_HOUSE(state) {
      state.house = {};
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_OFFICETEL_LIST: (state, officetels) => {
      state.officetels = officetels;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_SELECT_SIDO: (state, selectsido) => {
      state.selectsido = selectsido;
    },
    SET_KEYWORD: (state, keyword) => {
      state.keyword = keyword;
    },
    SET_LIKE_HOUSE: (state, likehouse) => {
      state.likehouse = likehouse;
    },
    PUT_LIKE_HOUSE: (state, house) => {
      state.likehouse.push(house);
    },
    SET_UNIV_LIST: (state, univs) => {
      state.univs = univs;
    },
    SET_HOUSE_LATLNG: (state, latlng) => {
      state.houseLatLng = latlng;
    },
    SET_LIKE_CHECK: (state, check) => {
      state.check = check;
    },
  },
  actions: {
    getSido({ commit }) {
      http
        .get(`/house/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      http
        .get(`/house/gugun/${sidoCode}`)
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
          commit("SET_SELECT_SIDO", sidoCode);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      http
        .get(`/house/dong/${gugunCode}`)
        .then(({ data }) => {
          console.log("조회된 동", data);
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseList: ({ commit }, { gugunCode, dongText }) => {
      const gCode = gugunCode;
      const ymd = "202305";

      const SERVICE_KEY =
        "U2FbirzZ%2BJstO00xy5XipInhYjiiitS439bouJlDMu9o%2BZEuxlNjdNS1D%2ByGOvAEAq4Em1GfiDTwjaoYj08pWg%3D%3D";

      const url =
        "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHRent?serviceKey=" +
        SERVICE_KEY +
        "&LAWD_CD=" +
        gCode +
        "&DEAL_YMD=" +
        ymd;

      http
        .get(url)
        .then(({ data }) => {
          let items = [];

          for (let i = 0; i < data.response.body.items.item.length; i++) {
            if (data.response.body.items.item[i].법정동 == dongText) {
              items.push(data.response.body.items.item[i]);
            }
          }

          console.log("house items", items);
          commit("SET_HOUSE_LIST", items);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getOfficetelList: ({ commit }, { gugunCode, dongText }) => {
      const gCode = gugunCode;
      const ymd = "202305";

      const SERVICE_KEY =
        "U2FbirzZ%2BJstO00xy5XipInhYjiiitS439bouJlDMu9o%2BZEuxlNjdNS1D%2ByGOvAEAq4Em1GfiDTwjaoYj08pWg%3D%3D";

      const url =
        "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcOffiRent?serviceKey=" +
        SERVICE_KEY +
        "&LAWD_CD=" +
        gCode +
        "&DEAL_YMD=" +
        ymd;

      http
        .get(url)
        .then(({ data }) => {
          let items = [];

          for (let i = 0; i < data.response.body.items.item.length; i++) {
            if (data.response.body.items.item[i].법정동 == dongText) {
              items.push(data.response.body.items.item[i]);
            }
          }

          commit("SET_OFFICETEL_LIST", items);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setHouseDetail: ({ commit }, house) => {
      commit("SET_DETAIL_HOUSE", house);
    },
    setKeyword: ({ commit }, keyword) => {
      commit("SET_KEYWORD", keyword);
    },
    putLikeHouse: ({ commit }, house) => {
      commit("PUT_LIKE_HOUSE", house);
    },
    setUnivList: ({ commit }, latlng) => {
      http.get(`/univ/list/${latlng.lat}/${latlng.lng}`).then(({ data }) => {
        console.log(data);
        commit("SET_UNIV_LIST", data);
      });
    },
    setHouseLatLng: ({ commit }, latlng) => {
      commit("SET_HOUSE_LATLNG", latlng);
    },
    setLikeCheck: ({ commit }, check) => {
      commit("SET_LIKE_CHECK", check);
    },
  },
};

export default mapStore;
