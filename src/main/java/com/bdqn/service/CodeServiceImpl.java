package com.bdqn.service;

import com.bdqn.dao.CodeMapper;
import com.bdqn.entity.Code;
import com.bdqn.entity.SmdProductionPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by john on 2019/6/19.
 */


@Service("CodeService")
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<Code> getAllLightCode(int currentPage, int num) {
        return codeMapper.getAllLightCode(currentPage, num);
    }

    @Override
    public int getAllLightCodeCount() {
        return codeMapper.getAllLightCodeCount();
    }

    @Override
    public List<Code> getAllNightCode(int currentPage, int num) {
        return codeMapper.getAllNightCode(currentPage, num);
    }

    @Override
    public int getAllNightCodeCount() {
        return codeMapper.getAllNightCodeCount();
    }

    @Override
    public List<Code> getAllNextNightCode(int currentPage, int num) {
        return codeMapper.getAllNextNightCode(currentPage, num);
    }

    @Override
    public int getAllNextNightCodeCount() {
        return codeMapper.getAllNextNightCodeCount();
    }

    @Override
    public int addNewTestCode(SmdProductionPlan smdProductionPlan) {
        int inputPlan = Integer.valueOf(smdProductionPlan.getPlanNum());
        int multiple = Integer.valueOf(smdProductionPlan.getMultiple());
        Integer newPlan = inputPlan * multiple;//这里将板数乘以单板倍数作为显示的计划产量
        smdProductionPlan.setPlanNum(newPlan.toString());
        return codeMapper.addNewTestCode(smdProductionPlan);
    }

    @Override
    public int updateTestCode(int id, String planNum) {
        int multiple = codeMapper.getTestCodeById(id).getMultiple();
        Integer newPlan = Integer.valueOf(Integer.valueOf(planNum) * multiple);//这里对修改的计划产量乘以该型号单板倍数作为显示的计划产量
        return codeMapper.updateTestCode(id, newPlan.toString());
    }

    @Override
    public List<SmdProductionPlan> getAllTestCode() {
        return codeMapper.getAllTestCode();
    }

    @Override
    public SmdProductionPlan getTestCodeById(int id) {
        return codeMapper.getTestCodeById(id);
    }

    @Override
    public int deleteTestCodeById(int id) {
        return codeMapper.deleteTestCodeById(id);
    }

    @Override
    public int deleteRaspCode() {
        return codeMapper.deleteRaspCode();
    }
}
