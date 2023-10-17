import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board/write`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(bno, success, fail) {
  api.get(`/board/detail/${bno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board/modify/${article.bno}`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(bno, success, fail) {
  api.delete(`/board/delete/${bno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
