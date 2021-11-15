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
   Result getUserTask(PageParams pageParams);
   /**
    * 读取某用户接受任务
    * @return
    */
   Result getUserATask(PageParams pageParams);
   /**
    * 更新任务
    * @param taskParam
    * @return
    */
   Result updateTask(TaskParam taskParam);
   /**
    * 删除任务里面的标签
    */
   Result delTaskTag(Long id);
   /**
    * 增加已经发布任务里面的标签
    */
   Result insertTaskTag(TaskParam taskParam);
   /**
    * selectTaskByKeys
    * @param:String words,Long schoolid
    * @return
    */
   Result getTaskByKeys(PageParams pageParams);

   /**
    * selectTaskByKeysNoState
    * @param:String words,Long schoolid
    * @return
    */
   Result getTaskByKeysNoState(PageParams pageParams);
   /**
    *
    * 用户接受任务
    */
   Result acceptTask(Long taskId);
   /**
    * 修改任务状态
    */
   Result  alterTask(TaskParam taskParam);

}
