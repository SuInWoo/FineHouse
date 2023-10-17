<template>
  <div>
    <div class="mt-2"></div>
    <h3 style="font-family: GTFont; float: left">부동산 뉴스</h3>
    <h3 style="margin-right: 150px; color: white">News</h3>
    <div class="mt-4"></div>
    <b-list-group style="font-family: GTFont">
      <b-list-group-item
        v-for="article in articles"
        :key="article.link"
        :href="article.link"
        target="_blank"
      >
        <p v-html="article.title"></p>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "NaverNews",
  data() {
    return {
      query: "",
      articles: [],
    };
  },
  mounted() {
    this.searchNews();
  },
  methods: {
    searchNews() {
      this.loading = true;

      // 네이버 뉴스 API 요청을 보낼 URL
      const apiUrl = "v1/search/news.json";

      // 네이버 뉴스 API 요청 헤더에 필요한 인증 정보
      const headers = {
        Accept: "application/json",
        "X-Naver-Client-Id": "ydGMNO48LtcuV7AejrU3",
        "X-Naver-Client-Secret": "xWQWxc2Xwj",
      };

      // 네이버 뉴스 API 요청에 필요한 쿼리 파라미터
      const params = {
        query: "대학생 원룸",
        display: 7, // 검색 결과 수 (최대 100개)
      };

      axios
        .get(apiUrl, { headers, params })
        .then((response) => {
          console.log(response);
          this.articles = response.data.items;
        })
        .catch((error) => {
          console.error(error);
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
};
</script>

<style scoped></style>
