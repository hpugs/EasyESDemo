package com.hpugs.easyes.model;

import org.dromara.easyes.annotation.IndexId;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.Settings;
import org.dromara.easyes.annotation.rely.IdType;

/**
 * @Author：xinge
 * @Date：2024/11/14 11:00
 * @Description：
 */
@Settings(shardsNum = 1, replicasNum = 0)
@IndexName(keepGlobalPrefix = true, value = "document", aliasName = "document_alias")
public class Document {

    /**
     * Es索引对象默认为String类型_id，注意字段冲突
     */
    @IndexId(type = IdType.CUSTOMIZE)
    private String id;

    private Long dbId;

    private String name;

    private String desc;

    public Document() {
    }

    public Document(String id, Long dbId, String name, String desc) {
        this.id = id;
        this.dbId = dbId;
        this.name = name;
        this.desc = desc;
    }

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
