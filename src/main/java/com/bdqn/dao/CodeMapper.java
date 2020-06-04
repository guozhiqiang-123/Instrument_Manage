package com.bdqn.dao;

import com.bdqn.entity.Code;
import com.bdqn.entity.SmdProductionPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by john on 2019/6/19.
 */
@Mapper
public interface CodeMapper {
    /**
     *
     * @return
     * 按24小时分组计数(白班)并分页显示
     */
    public List<Code> getAllLightCode(@Param("currentPage") int currentPage,@Param("num") int num);

    /**
     *
     * 按24小时分组计数(白班)获取总记录数
     */
    public int getAllLightCodeCount();

    /**
     *
     * @return
     * 按24小时分组计数(前半夜夜班--当天21-23点)并分页显示
     */
    public List<Code> getAllNightCode(@Param("currentPage")int currentPage,@Param("num")int num);

    /**
     * 按24小时分组计数(前半夜夜班--当天21-23点)获取总记录数
     */
    public int getAllNightCodeCount();

    /**
     *
     * @return
     * 按24小时分组计数(后半夜夜班--前一天21-当天凌晨7点)并分页显示
     */
    public List<Code> getAllNextNightCode(@Param("currentPage")int currentPage,@Param("num")int num);

    /**
     *
     * 按24小时分组计数(前半夜夜班--当天21-23点)获取总记录数
     */
    public int getAllNextNightCodeCount();

    /**
     *
     * 给smdProductionPlan表存入一条记录
     * **/
    public int addNewTestCode(SmdProductionPlan smdProductionPlan);
    /**
     *
     * 根据ID对smdProductionPlan表进行更新操作
     */
    public int updateTestCode(@Param("id") int id,@Param("planNum") String planNum);
    /**
     *
     * 根据ID对smdProductionPlan表查出对应的记录
     */
    public SmdProductionPlan getTestCodeById(int id);
    /**
     *
     *查询所有smdProductionPlan记录
     */
    public List<SmdProductionPlan> getAllTestCode();
    /**
     *
     * 根据ID删除对应的smdProductionPlan记录
     */
    public int deleteTestCodeById(@Param("id") int id);
    /**
     *
     * 删除相对于当前日期大于两天的数据记录,以达到数据库不会因数据量多而出现问题
     */
    public int deleteRaspCode();
}
