package com.hpugs.easyes.web;

import com.hpugs.easyes.mapper.IDocumentMapper;
import com.hpugs.easyes.model.Document;
import com.hpugs.easyes.model.req.DocumentReq;
import org.apache.http.util.Asserts;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.dromara.easyes.core.conditions.update.LambdaEsUpdateWrapper;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author：xinge
 * @Date：2024/11/14 10:18
 * @Description：
 */
@RestController
@RequestMapping("/document")
public class TestController {

    @Resource
    private IDocumentMapper documentMapper;

    @GetMapping("/deleteIndex")
    public String deleteIndex() {
        // 1.初始化-> 创建索引(相当于mysql中的表)
        Boolean exist = documentMapper.existsIndex();
        if (exist) {
            Boolean flag = documentMapper.deleteIndex();
            if (flag) {
                return "delete index success";
            } else {
                return "delete index fail";
            }
        }
        return "success";
    }

    @GetMapping("/createIndex")
    public String createIndex() {
        // 1.初始化-> 创建索引(相当于mysql中的表)
        Boolean exist = documentMapper.existsIndex();
        if (!exist) {
            Boolean flag = documentMapper.createIndex();
            if (flag) {
                return "create index success";
            } else {
                return "create index fail";
            }
        }
        return "success";
    }

    @PostMapping("/insert")
    public String insert(@RequestBody DocumentReq documentReq) {
        if (documentReq.getId() == null) {
            return "errorMsg：id not null";
        }

        Document document = new Document(documentReq.getId(),
                documentReq.getDbId(), documentReq.getName(), documentReq.getDesc());
        Integer n = documentMapper.insert(document);
        if (n > 0) {
            return "save success";
        }else if (n == 0) {
            return "update success";
        }
        return "errorMsg：data save fail";
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(required = false) String name) {
        // 3.查询出所有标题为老汉的文档列表
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.index(documentMapper.getIndexName())
                .eq(Optional.ofNullable(name).isPresent(), Document::getName, name)
                .orderByDesc(Document::getDbId)
                .limit(10);
        return documentMapper.selectList(wrapper);
    }

    @PostMapping("/update")
    public String update(@RequestBody DocumentReq documentReq) {
        if (documentReq.getId() == null) {
            return "errorMsg：id not null";
        }

        Document document = new Document(documentReq.getId(), documentReq.getDbId(), documentReq.getName(), documentReq.getDesc());
        Integer n = documentMapper.updateById(document);
        if (n > 0) {
            return "update success";
        }else if (n == 0) {
            return "no need update";
        }
        return "errorMsg：data not find";
    }

    @PostMapping("/updateDescByName")
    public String updateDescByName(@RequestBody DocumentReq documentReq) {
        if (documentReq.getName() == null) {
            return "errorMsg：name not null";
        }

        // 批量更新
        LambdaEsUpdateWrapper<Document> wrapper = new LambdaEsUpdateWrapper<>();
        wrapper.eq(Document::getName, documentReq.getName());
        wrapper.set(Document::getDesc, documentReq.getDesc());

        Integer n = documentMapper.update(null, wrapper);
        if (n > 0) {
            return "update success";
        }else if (n == 0) {
            return "no need update";
        }
        return "errorMsg：data not find";
    }
}
