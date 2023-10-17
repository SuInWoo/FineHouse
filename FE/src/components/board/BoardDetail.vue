<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <h3>글보기</h3>
        </b-col>
      </b-row>

      <b-row class="mb-1">
        <b-col class="text-left">
          <b-button variant="outline-dark" @click="listArticle">목록</b-button>
        </b-col>
      </b-row>

      <b-row class="mb-1">
        <b-col>
          <b-card
            :header-html="`<h4>${article.title} </h4>
            <div class='text-right'> 
              <h6> 작성자 : ${article.writer}
            </div>`"
            class="mb-2"
            no-body
          >
            <b-card-body class="text-left">
              <!-- {{ registerTime | dateFormat }} -->
              <div v-html="`${article.content}`"></div>
              <br />
              <br />
              <br />

              <div class="text-right">
                <div v-html="` ${article.registerTime}`"></div>
                <b-button v-if="userInfo.admin == true" @click="deleteArticle">삭제</b-button>
              </div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { getArticle, deleteArticle } from "@/api/board";
// import moment from "moment";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  components: {},
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    getArticle(
      this.$route.params.bno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("글 못불러옴", error);
      }
    );
  },
  methods: {
    //목록으로 이동
    listArticle() {
      this.$router.push({ name: "board" });
    },
    deleteArticle() {
      if (confirm("해당 공지사항을 삭제하시겠습니까?")) {
        deleteArticle(this.article.bno, () => {
          this.$router.push({ name: "board" });
        });
      }
    },
  },
  filters: {
    // dateFormat(registerTime) {
    //   return moment(new Date(registerTime)).format("YY.MM.DD");
    // },
  },
};
</script>

<style scoped></style>
