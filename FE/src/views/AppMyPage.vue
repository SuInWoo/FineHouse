<template>
  <my-page></my-page>
</template>

<script>
import MyPage from "../components/mypage/MyPage.vue";

export default {
  name: "AppMyPage",
  components: { MyPage },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
