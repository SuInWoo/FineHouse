import { apiInstance } from "./index.js";

const api = apiInstance();

function listRecent(id, success, fail) {
  api.get(`/recent/list/${id}`).then(success).catch(fail);
}
function writeRecent(item, success, fail) {
  api.post(`/recent/write`, JSON.stringify(item)).then(success).catch(fail);
}
export { listRecent, writeRecent };
