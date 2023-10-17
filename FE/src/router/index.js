import Vue from "vue";
import VueRouter from "vue-router";

import AppMain from "../views/AppMain.vue";
import AppLogin from "../views/AppLogin.vue";
import AppBoard from "../views/AppBoard.vue";
import AppMap from "../views/AppMap.vue";
import HouseDetail from "../components/map/HouseDetail.vue";
import HouseList from "../components/map/HouseList.vue";
import HouseMap from "../components/map/HouseMap.vue";
import MemberSignUp from "../components/user/MemberSignUp.vue";
import HouseListParent from "../components/map/HouseListParent.vue";
import HouseSearchBar from "../components/map/HouseSearchBar.vue";
import HouseMenuSideBar from "../components/map/HouseMenuSideBar.vue";
import MyPage from "../components/mypage/MyPage.vue";
import BoardDetail from "../components/board/BoardDetail.vue";
import BoardWrite from "../components/board/BoardWrite.vue";
import AppCal from "../views/AppCal.vue";

import OfficeList from "../components/omap/OfficeList.vue";
import AppOfficeMap from "../views/AppOfficeMap.vue";
import OfficeMap from "../components/omap/OfficeMap.vue";
import OfficeListParent from "../components/omap/OfficeListParent.vue";
import OfficeDetail from "../components/omap/OfficeDetail.vue";
import OfficeMenuSideBar from "../components/omap/OfficeMenuSideBar.vue";
import OfficeSearchBar from "../components/omap/OfficeSearchBar.vue";
// import BoardList from "../components/board/BoardList.vue";

import store from "@/store/index.js";
// import { component } from "vue/types/umd";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  //const getUserInfo = store._actions["memberStore/getUserInfo"];

  let token = sessionStorage.getItem("access-token");
  // if (checkUserInfo == null && token) {
  //   await getUserInfo(token);
  //   //console.log(getUser);
  // }

  if (checkUserInfo === null && !token) {
    alert("로그인이 필요한 페이지입니다.");
    // alert("너 누구니 ?");
    next({ name: "main" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/login",
    name: "login",
    component: AppLogin,
  },
  {
    path: "/signup",
    name: "signup",
    component: MemberSignUp,
  },
  {
    path: "/calculator",
    name: "calculator",
    beforeEnter: onlyAuthUser,
    component: AppCal,
  },
  {
    path: "/mypage",
    name: "mypage",
    component: MyPage,
  },
  {
    path: "/board",
    name: "board",
    beforeEnter: onlyAuthUser,
    component: AppBoard,
    // children: [
    //   {
    //     path: "list",
    //     name: "boardList",
    //     component: BoardList,
    //     children: [
    //       {
    //         path: "write",
    //         name: "boardWrite",
    //         component: BoardWrite,
    //       },
    //       {
    //         path: "detail/:bno",
    //         name: "boardDetail",
    //         component: BoardDetail,
    //       },
    //     ],
    //   },
    // ],
  },
  {
    path: "/board/write",
    name: "boardWrite",
    component: BoardWrite,
  },
  {
    path: "/board/detail/:bno",
    name: "boardDetail",
    component: BoardDetail,
  },
  {
    path: "/map",
    name: "map",
    beforeEnter: onlyAuthUser,
    component: AppMap,
    children: [
      {
        path: "hmap",
        name: "hmap",
        component: HouseMap,
      },
      {
        path: "hlistp",
        name: "hlistp",
        component: HouseListParent,
        children: [
          {
            path: "hlist",
            name: "hlist",
            component: HouseList,
          },
          {
            path: "hdetail",
            name: "hdetail",
            component: HouseDetail,
            children: [
              {
                path: "sidebar",
                name: "sidebar",
                component: HouseMenuSideBar,
              },
            ],
          },
          {
            path: "hsearchbar",
            name: "hsearchbar",
            component: HouseSearchBar,
          },
        ],
      },
    ],
  },
  {
    path: "/omap",
    name: "omap",
    beforeEnter: onlyAuthUser,
    component: AppOfficeMap,
    children: [
      {
        path: "ofmap",
        name: "ofmap",
        component: OfficeMap,
      },
      {
        path: "olistp",
        name: "olistp",
        component: OfficeListParent,
        children: [
          {
            path: "olist",
            name: "olist",
            component: OfficeList,
          },
          {
            path: "odetail",
            name: "odetail",
            component: OfficeDetail,
            children: [
              {
                path: "osidebar",
                name: "osidebar",
                component: OfficeMenuSideBar,
              },
            ],
          },
          {
            path: "osearchbar",
            name: "osearchbar",
            component: OfficeSearchBar,
          },
        ],
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
