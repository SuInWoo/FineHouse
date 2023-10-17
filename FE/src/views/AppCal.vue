<template>
  <div>
    <div id="root">
      <div>
        <br />
        <h2>전월세 계산기</h2>

        <b-card>
          전세 ↔ 월세 전환 시 또는 보증금과 월세 조정 시 적정 금액을 계산합니다.<br />
          정해진 전월세전환율에 맞게 계산해 볼 수 있습니다.<br />
          한국은행의 주택 전월세전환율은 현행 3.5% 입니다.<br />
          정부의 주택 전월세전환율은 주택을 전세에서 월세로 전환할 때 적용하도록 하고 있습니다.
          <br />
        </b-card>
      </div>
      <div class="mt-3">
        <!-- <span>전월세 전환율 = </span> <input type="text" placeholder="2.5" /> % -->
      </div>

      <div>
        <b-row>
          <b-col cols="3"></b-col>
          <b-col cols="6">
            <div>
              <b-row>
                <b-col cols="3"> </b-col>
                <b-col cols="6">
                  <b-input-group prepend="전월세 전환율" class="mt-3">
                    <b-input type="number" v-model="percent" placeholder="3.5" />
                    <b-input-group-append>
                      <span class="input-group-text"> % </span>
                    </b-input-group-append>
                  </b-input-group>
                </b-col>
                <b-col cols="3"> </b-col>
              </b-row>

              <div class="mt-3 text-center">
                <h4 class="text-left">조정 전</h4>
                <b-row>
                  <b-col>
                    <div>
                      <b-input-group prepend="보증금" class="mt-1">
                        <b-input type="number" v-model="before_jeon" style="width: 10px" />
                        <b-input-group-append>
                          <span class="input-group-text"> 만원 </span>
                        </b-input-group-append>
                      </b-input-group>
                    </div>
                  </b-col>
                  <b-col>
                    <div>
                      <b-input-group prepend="월세" class="mt-1">
                        <b-input type="number" v-model="before_wal" />
                        <b-input-group-append>
                          <span class="input-group-text"> 만원 </span>
                        </b-input-group-append>
                      </b-input-group>
                    </div>
                  </b-col>
                </b-row>
              </div>
              <div class="mt-4">
                <h4 class="text-left">조정 후</h4>
                <b-row>
                  <b-col>
                    <div>
                      <b-input-group prepend="보증금" class="mt-1">
                        <b-input type="number" v-model="after_jeon" style="width: 10px" />
                        <b-input-group-append>
                          <span class="input-group-text"> 만원 </span>
                        </b-input-group-append>
                      </b-input-group>
                    </div>
                  </b-col>
                  <b-col>
                    <div>
                      <b-input-group prepend="월세" class="mt-1">
                        <b-input type="number" v-model="after_wal" value="resultWal" />
                        <b-input-group-append>
                          <span class="input-group-text"> 만원 </span>
                        </b-input-group-append>
                      </b-input-group>
                    </div>
                  </b-col>
                </b-row>
              </div>

              <!-- <div class="mt-3">
                적정 전세 : <input type="text" :placeholder="resultJeon" /> 만원
              </div> -->
              <div class="mt-3">
                <b-row>
                  <b-col cols="3"> </b-col>
                  <b-col cols="6">
                    <b-input-group prepend="적정 전세" class="mt-3">
                      <b-input type="number" :placeholder="resultJeon" />
                      <b-input-group-append>
                        <span class="input-group-text"> 만원 </span>
                      </b-input-group-append>
                    </b-input-group>
                  </b-col>
                  <b-col cols="3"> </b-col>
                </b-row>
              </div>
              <div>
                <b-row>
                  <b-col cols="3"> </b-col>
                  <b-col cols="6">
                    <b-input-group prepend="적정 월세" class="mt-3">
                      <b-input type="number" :placeholder="resultWal" />
                      <b-input-group-append>
                        <span class="input-group-text"> 만원 </span>
                      </b-input-group-append>
                    </b-input-group>
                  </b-col>
                  <b-col cols="3"> </b-col>
                </b-row>
              </div>

              <b-button class="mt-3 mr-3" variant="info" v-on:click="onClickToJeon"
                >전세 변환</b-button
              >
              <b-button class="mt-3" variant="info" v-on:click="onClickToWal">월세 변환</b-button>
            </div>
          </b-col>
          <b-col cols="3"></b-col>
        </b-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppCal",
  components: {},
  data() {
    return {
      before_jeon: 0,
      before_wal: 0,
      after_jeon: 0,
      after_wal: 0,
      resultJeon: 0,
      resultWal: 0,
    };
  },
  created() {},
  methods: {
    onClickToJeon() {
      this.resultJeon = Math.round(((this.before_wal - this.after_wal) * 12) / 0.035);
    },
    onClickToWal() {
      this.resultWal = Math.round(
        parseInt(this.before_wal) + ((this.before_jeon - this.after_jeon) / 12) * 0.055
      );
    },
  },
};
</script>

<style scoped></style>
