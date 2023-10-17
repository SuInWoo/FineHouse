<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col sm="4">
      <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
    </b-col>
    <b-col sm="4">
      <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
    </b-col>
    <b-col sm="4">
      <b-form-select v-model="dongCode" :options="dongs" @change="officeList"></b-form-select>
    </b-col>
  </b-row>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";

const mapStore = "mapStore";

export default {
  name: "OfficeSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(mapStore, ["sidos", "guguns", "dongs", "officetels"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(mapStore, ["getSido", "getGugun", "getDong", "getOfficetelList"]),
    ...mapMutations(mapStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_OFFICETEL_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    officeList() {
      const selectedDongs = this.dongs.filter((dong) => dong.value === this.dongCode);
      const selectedDongTexts = selectedDongs.map((dong) => dong.text);
      const dongText = selectedDongTexts.length > 0 ? selectedDongTexts[0] : null;

      const payload = {
        gugunCode: this.gugunCode.toString().substr(0, 5),
        dongText: dongText,
      };

      if (this.gugunCode) {
        this.getOfficetelList(payload);
      }

      this.$emit("changeToggle2", true);
    },
  },
};
</script>
<style></style>
