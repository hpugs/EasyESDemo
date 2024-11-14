package com.hpugs.easyes.mapper;

import com.hpugs.easyes.model.Document;
import org.dromara.easyes.core.biz.CreateIndexParam;
import org.dromara.easyes.core.biz.EntityInfo;
import org.dromara.easyes.core.kernel.BaseEsMapper;
import org.dromara.easyes.core.toolkit.EntityInfoHelper;
import org.dromara.easyes.core.toolkit.IndexUtils;

/**
 * @Author：xinge
 * @Date：2024/11/14 10:59
 * @Description：
 */
public interface IDocumentMapper extends BaseEsMapper<Document> {

    default String getIndexName() {
        EntityInfo entityInfo = EntityInfoHelper.getEntityInfo(getEntityClass());
        CreateIndexParam createIndexParam = IndexUtils.getCreateIndexParam(entityInfo, getEntityClass());
        return createIndexParam.getIndexName();
    }

    default Boolean existsIndex() {
        EntityInfo entityInfo = EntityInfoHelper.getEntityInfo(getEntityClass());
        CreateIndexParam createIndexParam = IndexUtils.getCreateIndexParam(entityInfo, getEntityClass());
        return existsIndex(createIndexParam.getIndexName());
    }

    default Boolean deleteIndex() {
        EntityInfo entityInfo = EntityInfoHelper.getEntityInfo(getEntityClass());
        CreateIndexParam createIndexParam = IndexUtils.getCreateIndexParam(entityInfo, getEntityClass());
        return deleteIndex(createIndexParam.getIndexName());
    }

}
