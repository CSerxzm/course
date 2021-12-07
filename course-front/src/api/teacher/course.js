import * as ajax from "../../common/ajax";

export const list = () => ajax.pureGet("/teacher/course/list");
export const create = entity => ajax.post("/teacher/course", entity);
export const deleteItem = id => ajax.pureDelete("/teacher/course/" + id);
export const update = entity => ajax.put("/teacher/course", entity);
export const get = id => ajax.get("/teacher/course/" + id);