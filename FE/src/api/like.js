import { apiInstance } from "./index.js";

const api = apiInstance();

function addLike(like, success, fail) {
  api.get(`/like/add`, JSON.stringify(like)).then(success).catch(fail);
}

function listLike(id, success, fail) {
  api.get(`/like/list/${id}`).then(success).catch(fail);
}

function getLike(id, success, fail) {
  api.post(`/like`, JSON.stringify(id)).then(success).catch(fail);
}

// function deleteLike(bno, success, fail) {
//   api.get(`/like/delete`).then(success).catch(fail);
// }

export { addLike, listLike, getLike };
