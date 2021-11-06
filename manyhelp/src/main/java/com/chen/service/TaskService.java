package com.chen.service;

import com.chen.vo.TaskVo;
import com.chen.vo.Result;
import com.chen.vo.params.TaskParam;
import com.chen.vo.params.PageParams;

//设置获取任务列表数据接口
public interface TaskService {
//    通过AriticleVo给的属性参数创建返回给前端的列表listTasksPage方法数据,获取展示任务信息
   Result listTasksPage(PageParams pageParams);
//   找到最热任务
   Result hotTask(int limit);
   //   找到最新任务
   Result newTasks(int limit);
//   任务归档
   Result listArchives();

//   读取任务，点击进入任务内容查看详情
   TaskVo findTaskById(Long id);
// 发布任务
    Result publish(TaskParam taskParam);
//    读取某用户发布的任务
   Result getUserTask(Long authorid);
   /**
    * 读取某用户接受任务
    * @return
    */
   Result getUserATask(Integer stuid);
   /**
    * 更新任务
    * @param taskVo
    * @return
    */
    Result updateTask(TaskVo taskVo);
   /**
    * selectTaskByKeys
    * @param
    * @return
    */
   Result getTaskByKeys(String words,Long schoolid);

   /**
    * selectTaskByKeysNoState
    * @param
    * @return
    */
   Result getTaskByKeysNoState(String words,Long schoolid);

}
