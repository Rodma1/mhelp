package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.dao.mapper.TagMapper;
import com.chen.dao.pojo.Tag;
import com.chen.service.TagService;
import com.chen.vo.Result;
import com.chen.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TagsServiceImpl implements TagService {
    @Autowired(required = false)
    private TagMapper tagMapper;
    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
//        转为String类型
        tagVo.setId(String.valueOf(tag.getId()));
//        tag拷贝到tagVo
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }

//    这个是要返回给前端的数据
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList=new  ArrayList<>();
        for (Tag tag:tagList){
            tagVoList.add(copy(tag));
        }
        return  tagVoList;
    }
//     根据任务id查询标签列表
    @Override
    public List<TagVo> findTagsByTaskId(Long taskId) {
//        根据任务id获取数据库对应的标签信息
        List<Tag> tags=tagMapper.findTagsByTaskId(taskId);
        return copyList(tags);
    }
    //    返回前limit最热的标签名
    @Override
    public List<TagVo> hot(int limit) {
//        从任务数据表中找出前limit最热标签id
        List<Long> hotsTagIds=tagMapper.findHotsTagIds(limit);
//        判断是否为空 list.isEmpty是会报空指针的，而CollectionUtils.isEmpty则不会
        if (CollectionUtils.isEmpty(hotsTagIds)){
            return Collections.emptyList();
        }
//        找到最热标签的名字
        List<Tag> tagList=tagMapper.findTagsByTagIds(hotsTagIds);
        return copyList(tagList);
    }

    /**
     *任务内容 获取所有标签
     * @return
     */
    @Override
    public Result findAll() {
//        select * from mh_tag;
        List<Tag> tags=this.tagMapper.selectList(new LambdaQueryWrapper<>());
        return Result.success(copyList(tags));
    }
    /**
     * 导航：获取所有标签
     * @return
     */
    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    /**
     * 通过标签id寻找任务
     * @param id
     * @return
     */
    @Override
    public Result findDetailById(Long id) {
        Tag tag=tagMapper.selectById(id);
        //这个copy的作用是为了解耦
        TagVo copy=copy(tag);
        return Result.success(copy);
    }

}
