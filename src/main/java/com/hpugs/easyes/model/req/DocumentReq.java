package com.hpugs.easyes.model.req;

/**
 * @Author：xinge
 * @Date：2024/11/14 14:15
 * @Description：
 */
public class DocumentReq {

    private String id;

    private Long dbId;

    private String name;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
