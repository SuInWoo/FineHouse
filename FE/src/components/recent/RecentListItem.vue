<template>
  <div>
    <b-card
      :img-src="require('@/assets/' + img + '.jpg')"
      img-alt="Image"
      img-top
      style="width: 15rem; height: 28rem"
      class="mb-3 ml-3"
    >
      <b-card-text>
        <b-button pill disabled size="sm" variant="outline-dark">{{ type }} </b-button>
        <div class="mt-2"></div>
        <h5 style="font-family: GTFont">
          {{ name }}
        </h5>

        <span>
          {{ sidoname }} <br />
          {{ dong }} {{ jibun }} {{ floor }}층 <br />
          보증금 : {{ jeon }}만원 <br />
          <p v-if="wal === 0"></p>
          <p v-else>월세 : {{ wal }}만원</p>
        </span>
      </b-card-text>
    </b-card>
  </div>
</template>

<script>
import "@/assets/css/fonts.css";

export default {
  name: "RecentListItem",
  components: {},
  data() {
    return {
      likeHouse: {
        id: "",
        name: "",
        dong: "",
        jibun: "",
        type: "",
        wal: 0,
        jeon: "",
        floor: "",
        img: 0,
      },
    };
  },
  props: {
    id: String,
    sidoname: String,
    name: String,
    dong: String,
    jibun: String,
    wal: Number,
    jeon: String,
    floor: String,
    img: Number,
    type: String,
  },
  created() {},

  methods: {},
};
</script>

<style scoped></style>
