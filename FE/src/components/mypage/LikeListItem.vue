<template>
  <div>
    <b-card
      :title="name"
      :img-src="require('@/assets/' + img + '.jpg')"
      img-alt="Image"
      img-top
      style="max-width: 15rem"
      class="mb-3 ml-3"
    >
      <b-card-text>
        {{ sidoname }} {{ dong }} {{ jibun }}
        <br />
        <b-button pill disabled size="sm" variant="outline-dark">{{ type }} </b-button>
        <!-- 전/월세 : {{ type }} <br />
        보증금 / 월세 : {{ jeon }} / {{ wal }} -->
      </b-card-text>
      <b-button @click="openModal" variant="info">상세보기 </b-button>

      <!-- <b-button v-b-modal.modal-1 variant="info">상세보기 </b-button> -->
    </b-card>

    <b-modal v-model="modalOpen" hide-footer title="찜 매물 정보">
      <div class="d-block text-center">
        <img :src="require('@/assets/' + img + '.jpg')" alt="사진" />

        <h4>
          <b-button pill disabled size="sm" variant="outline-dark">{{ type }} </b-button> {{ name }}
        </h4>

        주소 : {{ sidoname }} {{ dong }} {{ jibun }} <br />
        층수 : {{ floor }} 층 <br />
        보증금 : {{ jeon }} 만원 <br />
        <div v-if="wal === 0"></div>
        <div v-else>월세 : {{ wal }} 만원 <br /></div>

        <b-button @click="deleteLikeHouse" variant="info">찜 삭제 </b-button>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const http = apiInstance();

export default {
  name: "LikeListItem",
  components: {},
  props: {
    id: String,
    lno: Number,
    sidoname: String,
    name: String,
    dong: String,
    jibun: String,
    type: String,
    wal: Number,
    jeon: String,
    floor: String,
    img: Number,
  },
  data() {
    return {
      modalOpen: false, // 모달 상태를 컴포넌트 내에서 공유
      likeHouse: {
        id: "",
        name: "",
        dong: "",
        jibun: "",
        type: "",
        wal: 0,
        jeon: "",
        floor: "",
        randnum: 0,
        img: 0,
      },
    };
  },

  created() {
    this.randnum = Math.floor(Math.random() * 20) + 1;
  },

  methods: {
    openModal() {
      this.modalOpen = true; // 모달을 열기 위해 상태 변경
    },
    closeModal() {
      this.modalOpen = false;
    },
    deleteLikeHouse() {
      this.likeHouse = {
        id: this.id,
        name: this.name,
        dong: this.dong,
        jibun: this.jibun,
        type: this.jibun,
        wal: this.wal,
        jeon: this.jeon,
        floor: this.floor,
      };
      http
        .post(`/like/delete`, JSON.stringify(this.likeHouse))
        .then((response) => {
          console.log(response);
          alert("삭제되었습니다!");
          this.closeModal();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
