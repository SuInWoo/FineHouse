import axios from "axios";
const API_BASE_URL = "http://localhost:9999/pjt";
const YOUTH_DEAL_URL = "https://www.youthcenter.go.kr/opi/empList.do";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function youthInstance() {
  const instance = axios.create({
    baseURL: YOUTH_DEAL_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance, youthInstance };

