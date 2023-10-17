<template>
  <div id="map"></div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const mapStore = "mapStore";

var addr = "";
var image = "https://cdn.icon-icons.com/icons2/317/PNG/512/map-marker-icon_34392.png";
let univImg = require("@/assets/univIconImg.png");

export default {
  name: "OfficeMap",
  components: {},
  props: {
    addr: String,
    flag: Boolean,
  },
  data() {
    return {
      map: null,
      markers: [],
      aroundMarkers: [],
      apiKey: "HjArpPfDKp73BQACFKjAJ8j0iR6W0r2B4JbruiI2",
    };
  },
  computed: {
    ...mapState(mapStore, [
      "officetels",
      "sidos",
      "house",
      "keyword",
      "selectSido",
      "houseLatLng",
      "univs",
    ]),
  },
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap(37.5642135, 127.0016985);
    } else {
      this.loadScript();
    }
  },
  methods: {
    ...mapActions(mapStore, ["setHouseLatLng"]),

    // api 불러오기
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=2c45a57d65518ab4eebc76e4b1a15772&autoload=false&libraries=services";
      script.onload = () => window.kakao.maps.load(() => this.loadMap(37.5642135, 127.0016985));

      document.head.appendChild(script);
    },

    // 맵 출력하기
    loadMap(lat, lng) {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(lat, lng),
        level: 5,
      };
      this.map = new window.kakao.maps.Map(container, options);
      this.loadMaker(lat, lng);
    },

    // 마커 불러오기
    loadMaker(lat, lng) {
      const markerPosition = new window.kakao.maps.LatLng(lat, lng);
      const marker = new window.kakao.maps.Marker({
        position: markerPosition,
      });
      this.markers.push(marker);
      marker.setMap(this.map);
    },

    // 장소 좌표로 변경
    convertLatLng(address) {
      return new Promise((resolve, reject) => {
        // 주소-좌표 변환 객체 생성
        const geocoder = new window.kakao.maps.services.Geocoder();

        // 주소로 좌표 변환 요청
        geocoder.addressSearch(address, (result, status) => {
          if (status === window.kakao.maps.services.Status.OK) {
            // 좌표 변환 성공
            const latitude = result[0].y; // 위도
            const longitude = result[0].x; // 경도

            resolve({ lat: latitude, lng: longitude });
          } else {
            // 좌표 변환 실패 또는 유효하지 않은 주소
            console.error("Failed to convert address to coordinates:", status);
            reject(status);
          }
        });
      });
    },

    // 중심좌표로 움직이는 메서드
    async moveCenter(addr) {
      try {
        const center = await this.convertLatLng(addr);
        const position = new window.kakao.maps.LatLng(center.lat, center.lng);
        this.map.setCenter(position);
      } catch (error) {
        console.error(error);
      }
    },
    async moveCenterOnly(addr) {
      try {
        const center = await this.convertLatLng(addr);

        this.loadMap(center.lat, center.lng);
      } catch (error) {
        console.error(error);
      }
    },

    // 마커들 제거
    clearMarkers() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
      this.aroundMarkers.forEach((aroundM) => {
        aroundM.setMap(null);
      });
      this.aroundMarkers = [];
    },

    // 매물들을 마커로 표시하기
    async drawMarkers() {
      // 기존의 마커들을 모두 삭제
      this.clearMarkers();

      // 새로운 마커들 추가
      for (const offi of this.officetels) {
        let sidoname = "";
        // 지역 코드 찾는 for문
        for (let i = 0; i < 18; i++) {
          if (String(offi.지역코드).substr(0, 2) == this.sidos[i].value) {
            sidoname = this.sidos[i].text;
            break;
          }
        }

        addr = sidoname + " " + offi.법정동 + " " + offi.지번;
        try {
          const latlng = await this.convertLatLng(addr);
          const marker = new window.kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: new window.kakao.maps.LatLng(latlng.lat, latlng.lng),
          });
          this.markers.push(marker);
        } catch (error) {
          console.error("Failed to add marker:", error);
        }
      }

      if (this.markers.length > 0) {
        const firstMarker = this.markers[0];
        const position = firstMarker.getPosition();
        this.map.setCenter(position);
      }
    },
    drawUnivMarkers() {
      this.aroundMarkers.forEach((aroundM) => {
        aroundM.setMap(null);
      });
      this.aroundMarkers = [];

      var imageSize = new window.kakao.maps.Size(50, 50);
      var imageOptions = {};
      var markerImage = this.createMarkerImage(univImg, imageSize, imageOptions);

      this.univs.forEach((univ) => {
        const marker = new window.kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: new window.kakao.maps.LatLng(univ.lat, univ.lng),
          image: markerImage,
        });

        let content = this.univCustomOverlay(univ);
        // 인포 윈도우
        var infowindow = new window.kakao.maps.InfoWindow({
          content: content,
          position: marker.getPosition(),
          removable: true,
        });
        var self = this;

        // 클릭 이벤트 추가
        window.kakao.maps.event.addListener(marker, "click", () => {
          infowindow.open(self.map, marker);
        });

        this.aroundMarkers.push(marker);
      });
    },

    placesSearchCB(data, status) {
      if (status === window.kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new window.kakao.maps.LatLngBounds();

        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
          bounds.extend(new window.kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다 (집 위치 기준이여서 필요없음)
        // this.map.setBounds(bounds);
      }
    },
    createMarkerImage(src, size, options) {
      var markerImage = new window.kakao.maps.MarkerImage(src, size, options);
      return markerImage;
    },

    // 지도에 마커를 표시하는 함수입니다
    displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다
      var imageSize = new window.kakao.maps.Size(30, 30);
      var imageOptions = {};

      // 마커이미지와 마커를 생성합니다
      var markerImage = this.createMarkerImage(image, imageSize, imageOptions);

      var marker = new window.kakao.maps.Marker({
        map: this.map,
        position: new window.kakao.maps.LatLng(place.y, place.x),
        image: markerImage,
      });

      this.aroundMarkers.push(marker);

      // 마커를 지도에 표시
      marker.setMap(this.map);

      var infowindow = new window.kakao.maps.InfoWindow({
        content: "",
        position: new window.kakao.maps.LatLng(place.y, place.x),
        removable: true,
      });
      var self = this;

      //window.kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(this.map, marker, infowindow));
      window.kakao.maps.event.addListener(marker, "mouseout", function () {
        infowindow.close();
      });

      // 마커에 클릭이벤트를 등록합니다
      window.kakao.maps.event.addListener(marker, "mouseover", async function () {
        console.log("여기", place.place_name);

        var start = await self.convertLatLng(self.house.dong + " " + self.house.jibun);
        //var end = await self.convertLatLng(self.house.dong + " " +  +" "+ place.place_name);

        var EARTH_RADIUS = 6371;
        var radLat1 = (start.lat * Math.PI) / 180; // 라디안으로 변환
        var radLon1 = (start.lng * Math.PI) / 180; // 라디안으로 변환

        var radLat2 = (place.y * Math.PI) / 180; // 라디안으로 변환
        var radLon2 = (place.x * Math.PI) / 180; // 라디안으로 변환

        // 두 지점의 위도 및 경도 차이 계산
        var deltaLat = radLat2 - radLat1;
        var deltaLon = radLon2 - radLon1;

        // Haversine 공식을 사용하여 거리 계산
        var a =
          Math.pow(Math.sin(deltaLat / 2), 2) +
          Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLon / 2), 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        var distance = Math.round(EARTH_RADIUS * c * 1000) * 1.5;

        var walkkTime = (distance / 67) | 0;
        var walkHour = "",
          walkMin = "";

        // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
        if (walkkTime > 60) {
          walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + "</span>시간 ";
        }
        walkMin = '<span class="number">' + (walkkTime % 60) + "</span>분";

        // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
        var bycicleTime = (distance / 227) | 0;
        var bycicleHour = "",
          bycicleMin = "";

        // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
        if (bycicleTime > 60) {
          bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + "</span>시간 ";
        }
        bycicleMin = '<span class="number">' + (bycicleTime % 60) + "</span>분";
        if (distance >= 1000) {
          distance = Math.round(distance / 1000);
        }

        //var start = house. jibun
        //var end = place.place_name

        //var distance = keyword + place.place_name;
        console.log("자전거 :", distance);
        console.log("자전거 :", walkkTime, walkHour, walkMin);

        infowindow.setContent(
          '<div class="pr-2 pl-2 pt-3" style="height:130px">' +
            place.place_name +
            "<br/>" +
            '        <span class="label">총거리</span><span class="number">' +
            distance +
            " m" +
            '        <br/> <span class="label">도보</span> ' +
            walkHour +
            walkMin +
            '       <br/>  <span class="label">자전거</span>' +
            bycicleHour +
            bycicleMin +
            "       <br/> " +
            "</div>"
        );
        //console.log(infowindow.content);
        infowindow.open(self.map, marker);
      });
    },
    univCustomOverlay(univ) {
      var content =
        '<div class="wrap">' +
        '    <div class="info">' +
        '        <div class="title">' +
        univ.name +
        '            <div class="close" @click="closeOverlay()" title="닫기"></div>' +
        "        </div>" +
        '        <div class="body">' +
        '            <div class="desc">' +
        "                <div >" +
        univ.addr +
        "</div>" +
        '                <div><a href="' +
        univ.homepage +
        '" target="_blank" class="link">홈페이지</a></div>' +
        "            </div>" +
        "        </div>" +
        "    </div>" +
        "</div>";

      return content;
    },

    //   getTrafficInfo(lat, lng) {
    //     const options = {
    //       method: "POST",
    //       url: "https://apis.openapi.sk.com/transit/routes/sub",
    //       headers: {
    //         accept: "application/json",
    //         "content-type": "application/json",
    //         appKey: "e8wHh2tya84M88aReEpXCa5XTQf3xgo01aZG39k5",
    //       },
    //       data: {
    //         startX: this.houseLatLng.lng,
    //         startY: this.houseLatLng.lat,
    //         endX: lng,
    //         endY: lat,
    //         lang: 0,
    //         format: "json",
    //         count: 1,
    //         searchDttm: "202301011200",
    //       },
    //     };

    //     axios
    //       .request(options)
    //       .then((response) => {
    //         this.trafficInfo = response.data.metaData.plan.itineraries[0];
    //         console.log(this.trafficInfo);

    //         this.totalFare = this.trafficInfo.fare.regular.totalFare;
    //         this.totalDistance = this.trafficInfo.fare.totalDistance;
    //         this.totalTime = this.trafficInfo.fare.totalTime;
    //         this.totalWalkDistance = this.trafficInfo.fare.totalWalkDistance;
    //         this.transferCount = this.trafficInfo.fare.transferCount;
    //       })
    //       .catch((error) => {
    //         console.error(error);
    //       });
    //   },
  },
  watch: {
    officetels() {
      this.drawMarkers();
    },
    async house() {
      let sidoname = "";
      for (let i = 0; i < 18; i++) {
        if (String(this.selectSido) == this.sidos[i].value) {
          sidoname = this.sidos[i].text;
          break;
        }
      }

      let latlng = await this.convertLatLng(
        sidoname + " " + this.house.dong + " " + this.house.jibun
      );
      console.log("Map house watch", latlng);
      this.setHouseLatLng(latlng);
    },
    addr() {
      // console.log("convert?");
      if (this.flag) {
        this.moveCenter(this.addr);
      } else {
        this.moveCenterOnly(this.addr);
      }
    },
    async keyword() {
      console.log("바뀌었다: ", this.keyword);
      try {
        const center = await this.convertLatLng(this.addr);
        var x = center.lat;
        var y = center.lng;
        this.loadMap(x, y);

        var ps = new window.kakao.maps.services.Places();
        ps.keywordSearch(this.keyword, this.placesSearchCB, {
          radius: 1000,
          location: new window.kakao.maps.LatLng(x, y),
        });
      } catch (error) {
        console.error(error);
      }
    },
    univs() {
      console.log("대학교 조회 바뀜");
      this.drawUnivMarkers();
    },
  },
};
</script>
<style scoped>
template {
  margin: 0;
}
.container {
  width: 100vw;
  height: 80vh;
}
.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
}
.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.number {
  font-weight: bold;
  color: #ee6152;
}
.dotOverlay:after {
  content: "";
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png");
}
.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  margin: 0;
}
.distanceInfo .label {
  display: inline-block;
  width: 50px;
}
.distanceInfo:after {
  content: none;
}
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>
