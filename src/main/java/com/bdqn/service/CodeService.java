package com.bdqn.service;

import com.bdqn.entity.Code;
import com.bdqn.entity.SmdProductionPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by john on 2019/6/19.
 */
public interface CodeService {
    public List<Code> getAllLightCode(@Param("currentPage") int currentPage, @Param("num") int num);

    public int getAllLightCodeCount();

    public List<Code> getAllNightCode(@Param("currentPage") int currentPage, @Param("num") int num);

    public int getAllNightCodeCount();

    public List<Code> getAllNextNightCode(@Param("currentPage") int currentPage, @Param("num") int num);

    public int getAllNextNightCodeCount();

    public int addNewTestCode(SmdProductionPlan smdProductionPlan);

    public int updateTestCode(@Param("id") int id, @Param("planNum") String planNum);

    public List<SmdProductionPlan> getAllTestCode();

    public SmdProductionPlan getTestCodeById(int id);

    public int deleteTestCodeById(@Param("id") int id);

    public int deleteRaspCode();
}
