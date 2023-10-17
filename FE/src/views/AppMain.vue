<template>
  <div class="main">
    <!-- 백그라운드 사진 -->
    <div id="house">
      <div class="page-header min-vh-75">
        <!-- 이 부분을 헤더 부분으로 이동 -->
        <br />
        <br />
        <h1 class="text-white pt-3 mt-n5 me-2" :style="{ display: 'inline-block ' }">
          Fin<span class="typed-text">{{ typeValue }}</span>
          <span class="blinking-cursor">|</span>
          <span class="cursor" :class="{ typing: typeStatus }">&nbsp;</span>
        </h1>
        <div>
          <div style="height: 230px"></div>

          <b-card class="mr-5 ml-5" style="height: 600px; background-color: white">
            <div class="text-center">
              <div class="py-7" v-if="!userInfo">
                <p style="font-family: GTFont">
                  대학생을 위한 집구하기 FineHouse 에 오신것을 환영합니다!<br />
                  로그인 후 다양한 서비스를 사용해 보세요
                </p>
                <h3 style="font-family: GTFont; color: #5f04b4">
                  FineHouse 에서 제공하는 서비스<br />
                </h3>
                <br />

                <b-row>
                  <b-col>
                    <!-- <b-img :src="require('@/assets/' + house.img + '.jpg')" style="width: 80%"></b-img> -->

                    <b-img :src="require('@/assets/support.png')" style="width: 50%"></b-img>
                    <hr />
                    <h4 style="font-family: GTFont; color: #5f04b4">주거 지원 서비스 Find</h4>
                    <span style="font-family: GTFont"
                      >매물을 조회하고 나에게 맞는 해당 지역의 <br />
                      주거 지원제도를 확인!<br
                    /></span>
                  </b-col>
                  <b-col>
                    <b-img :src="require('@/assets/findHouse.jpg')" style="width: 50%"></b-img>
                    <hr />
                    <h4 style="font-family: GTFont; color: #5f04b4">
                      매물 인근 대학교, 편의시설 Find
                    </h4>
                    <span style="font-family: GTFont"
                      >매물 인근 대학교, 도서관, 편의점, 도서관 등 <br />
                      편의시설을 조회하고 해당 정보, <br />
                      총거리 및 도보/자전거별 예상 소요시간 확인 !</span
                    >
                  </b-col>
                  <b-col>
                    <b-img :src="require('@/assets/calcul.png')" style="width: 50%"></b-img>
                    <hr />
                    <h4 style="font-family: GTFont; color: #5f04b4">매물 맞춤 전/월세 변환</h4>
                    <span style="font-family: GTFont"
                      >전/월세 중 지불하고싶은 비용을 적으면 <br />
                      전/월세 비율을 계산 후 확인!</span
                    ><br />
                  </b-col>
                </b-row>
              </div>

              <div v-if="userInfo">
                <b-row>
                  <b-col cols="8">
                    <div class="py-7">
                      <recent-list class="text-center"></recent-list>
                    </div>
                  </b-col>
                  <b-col cols="4">
                    <div><naver-news></naver-news></div>
                  </b-col>
                </b-row>
              </div>
            </div>
          </b-card>
          <div style="height: 50px"></div>
          <div style="height: 100px; background-image: '../assets/house.jpg'"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NaverNews from "@/components/news/NaverNews.vue";
// const btnProgressElem = document.querySelector(".btn-progress");
// const progressBarElem = document.querySelector(".progress-bar__bar");
import { mapState } from "vuex";
import RecentList from "../components/recent/RecentList.vue";
import "@/assets/css/fonts.css";

const memberStore = "memberStore";

export default {
  name: "AppMain",
  components: {
    RecentList,
    NaverNews,
  },
  data() {
    return {
      typeValue: "",
      typeStatus: false,
      displayTextArray: ["dHou", "eHouse"],
      typingSpeed: 100,
      erasingSpeed: 100,
      newTextDelay: 2000,
      displayTextArrayIndex: 0,
      charIndex: 0,
    };
  },
  computed: {
    //처음부터 가져오는 로그인 정보
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {
    setTimeout(this.typeText, this.newTextDelay + 20);
  },
  methods: {
    typeText() {
      if (this.charIndex < this.displayTextArray[this.displayTextArrayIndex].length) {
        if (!this.typeStatus) this.typeStatus = true;
        this.typeValue += this.displayTextArray[this.displayTextArrayIndex].charAt(this.charIndex);
        this.charIndex += 1;
        setTimeout(this.typeText, this.typingSpeed);
      } else {
        this.typeStatus = false;
        setTimeout(this.eraseText, this.newTextDelay);
      }
    },
    eraseText() {
      if (this.charIndex > 0) {
        if (!this.typeStatus) this.typeStatus = true;
        this.typeValue = this.displayTextArray[this.displayTextArrayIndex].substring(
          0,
          this.charIndex - 1
        );
        this.charIndex -= 1;
        setTimeout(this.eraseText, this.erasingSpeed);
      } else {
        this.typeStatus = false;
        this.displayTextArrayIndex += 1;
        if (this.displayTextArrayIndex >= this.displayTextArray.length)
          this.displayTextArrayIndex = 0;
        setTimeout(this.typeText, this.typingSpeed + 1000);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

h1 {
  font-size: 6rem;
  font-weight: normal;
  font-family: GTFont;
  span.typed-text {
    color: white;
  }
}

// Cursor blinking CSS Starts...
.blinking-cursor {
  font-size: 5rem;
  // color: #2c3e50;
  color: yellow;
  -webkit-animation: 1s blink step-end infinite;
  -moz-animation: 1s blink step-end infinite;
  -ms-animation: 1s blink step-end infinite;
  -o-animation: 1s blink step-end infinite;
  animation: 1s blink step-end infinite;
}
@keyframes blink {
  from,
  to {
    color: transparent;
  }
  50% {
    color: #2c3e50;
  }
}
@-moz-keyframes blink {
  from,
  to {
    color: transparent;
  }
  50% {
    color: #2c3e50;
  }
}
@-webkit-keyframes blink {
  from,
  to {
    color: transparent;
  }
  50% {
    color: #2c3e50;
  }
}
@-ms-keyframes blink {
  from,
  to {
    color: transparent;
  }
  50% {
    color: #2c3e50;
  }
}
@-o-keyframes blink {
  from,
  to {
    color: transparent;
  }
  50% {
    color: #2c3e50;
  }
}
#house {
  background: url("../assets/house.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center center;
  width: 100%;
  height: 450px;
}
.progress-bar {
  background-color: #09b864;
  border-radius: 4px;
  box-shadow: inset 0 0.5em 0.5em rgba(0, 0, 0, 0.05);
  height: 5px;
  margin: 2rem 0 2rem 0;
  overflow: hidden;
  position: relative;
  transform: translateZ(0);
  width: 100%;
}

.progress-bar__bar {
  background-color: #ececec;
  box-shadow: inset 0 0.5em 0.5em rgba(94, 49, 49, 0.05);
  bottom: 0;
  left: 0;
  position: absolute;
  right: 0;
  top: 0;
}

.progress-bar__bar.active {
  transition: all 5000ms ease-out;
  transform: translateX(100%);
  -webkit-transform: translateX(100%);
}
</style>
