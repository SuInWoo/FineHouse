<template>
  <b-container class="bv-example-row mt-5">
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <!-- <b-form @submit="onSubmit" @reset="onReset"> -->
        <b-form @submit="onSubmit">
          공지사항 작성 ( admin 용 )<br />
          <!-- <b-form-group
            id="userid-group"
            label="작성자:"
            label-for="writer"
            description="작성자를 입력하세요."
          >
            <b-form-input
              id="writer"
              disabled
              v-model="article.writer"
              type="text"
              required
              placeholder="article.writer"
            ></b-form-input>
          </b-form-group> -->

          <b-form-group
            id="subject-group"
            label="제목:"
            label-for="title"
            description="제목을 입력하세요."
          >
            <b-form-input
              id="title"
              v-model="article.title"
              type="text"
              required
              placeholder="제목 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="내용:" label-for="content">
            <b-form-textarea
              id="content"
              v-model="article.content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>

          <b-button type="submit" variant="outline-primary" class="m-1">글작성</b-button>
          <b-button type="reset" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { writeArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardWrite",
  components: {},
  data() {
    return {
      article: {
        bno: 0,
        writer: "",
        title: "",
        content: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.article.writer = "admin";
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.registArticle();
    },
    registArticle() {
      writeArticle(
        {
          writer: "admin",
          title: this.article.title,
          content: this.article.content,
        },
        (response) => {
          console.log(response);
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (response.status === 200) {
            msg = "등록이 완료되었습니다.";
            console.log("성공");
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "board" });
    },
  },
};
</script>

<style scoped></style>
