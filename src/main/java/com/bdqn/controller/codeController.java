package com.bdqn.controller;

import com.bdqn.entity.Code;
import com.bdqn.entity.SmdProductionPlan;
import com.bdqn.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by john on 2019/6/19.
 */
@Controller
public class codeController extends BaseController {
    @Autowired
    private CodeService codeService;

    /**
     *
     * @param request
     * @param currentPages
     * @param num
     * @param currentPages1
     * @param num1
     * @return 跳转至首页
     */
    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request,@RequestParam(value = "currentPage",defaultValue = "1",required = false) String currentPages,@RequestParam(value = "num",defaultValue = "10",required = false) String num,@RequestParam(value = "currentPage1",defaultValue = "1",required = false) String currentPages1,@RequestParam(value = "num1",defaultValue = "10",required = false) String num1,@RequestParam(value = "currentPage2",defaultValue = "1",required = false) String currentPages2,@RequestParam(value = "num2",defaultValue = "10",required = false) String num2) {
        codeService.deleteRaspCode();//删除数据库表中前两天的数据记录
        int currentPage = 0;//初始化日班当前页面号
        int currentPage1 = 0;//初始化夜班当前页面号
        int currentPage2 = 0;//初始化夜班当前页面号
        int pages = Integer.parseInt(currentPages);//将页面传入的日班当前页面号转int类型
        int pages1 = Integer.parseInt(currentPages1);//将页面传入的夜班当前页面号转int类型
        int pages2 = Integer.parseInt(currentPages2);//将页面传入的夜班当前页面号转int类型
        int totalLightCount = codeService.getAllLightCodeCount();//获取所有白天记录数
        int totalNightCount = codeService.getAllNightCodeCount();//获取所有晚上记录数(前半夜21-23)
        int totalNextNightCount = codeService.getAllNextNightCodeCount();//获取所有晚上记录数(后半夜21-第二天凌晨07)
        int totalLightPages = 0;//初始化日班总页数
        int totalNightPages = 0;//初始化前半夜班总页数
        int totalNextNightPages = 0;//初始化后半夜班总页数
        int parseint = Integer.parseInt(num);//将页面传入的日班单个页面显示记录数转为int
        int parseint1 = Integer.parseInt(num1);//将页面传入的夜班单个页面显示记录数转为int
        int parseint2 = Integer.parseInt(num2);//将页面传入的夜班单个页面显示记录数转为int
        if(totalLightCount % parseint == 0){
            totalLightPages = totalLightCount / parseint;//如果总记录数对单个页面显示记录数取余为0，则总页数为它们的商
        }else if(totalLightCount % parseint > 0){
            totalLightPages = totalLightCount / parseint + 1;//如果总记录数对单个页面显示记录数取余不为0，则总页数为它们的商加1
        }
        if(totalNightCount % parseint1 == 0){
            totalNightPages = totalNightCount / parseint1;//同上
        }else if(totalNightCount % parseint > 0){
            totalNightPages = totalNightCount / parseint1 + 1;//同上
        }
        if(totalNextNightCount % parseint1 == 0){
            totalNextNightPages = totalNextNightCount / parseint1;//同上
        }else if(totalNightCount % parseint > 0){
            totalNextNightPages = totalNextNightCount / parseint1 + 1;//同上
        }
        currentPage = (pages-1)*parseint;//白班第一页
        currentPage1 = (pages1-1)*parseint1;//夜班第一页
        currentPage2 = (pages2-1)*parseint2;
        int pageSize = parseint;//白班每页显示数量
        int pageSize1 = parseint1;//夜班每页显示数量
        int pageSize2 = parseint2;
        List<Code> list1 = codeService.getAllLightCode(currentPage, pageSize);
        List<Code> list2 = codeService.getAllNightCode(currentPage1,pageSize1);
        List<Code> list3 = codeService.getAllNextNightCode(currentPage2,pageSize2);
        request.setAttribute("list1",list1);
        request.setAttribute("list2",list2);
        request.setAttribute("list3",list3);
        request.setAttribute("currentPage",pages);
        request.setAttribute("currentPage1",pages1);
        request.setAttribute("currentPage2",pages2);
        request.setAttribute("totalLightPages",totalLightPages);
        request.setAttribute("totalNightPages",totalNightPages);
        request.setAttribute("totalNextNightPages",totalNextNightPages);
        request.setAttribute("num",pageSize);
        request.setAttribute("num1",pageSize1);
        request.setAttribute("num2",pageSize2);
        return "index";
    }

    /**
     *
     * @param smdProductionPlan
     * @return 为smdProductionPlan表增加一条记录，并跳转回index方法
     */
    @RequestMapping("/add")
    public String add(SmdProductionPlan smdProductionPlan){
        codeService.addNewTestCode(smdProductionPlan);
        return "redirect:/index";
    }

    /**
     *
     * @param id
     * @param planNum
     * @return 对testcode表做更新操作，并跳转回index方法
     */
    @RequestMapping("/update")
    public String update(@RequestParam("id")int id,@RequestParam("planNum")String planNum){
        codeService.updateTestCode(id,planNum);
        return "redirect:/index";
    }

    /**
     *
     * @param id
     * @return 对testcode表做删除操作，并跳转回index方法
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("id")int id){
        codeService.deleteTestCodeById(id);
        return "redirect:/index";
    }

    /**
     *
     * @param request
     * @return 跳转至设置页
     */
    @RequestMapping("/toSetting")
    public String toSetting(HttpServletRequest request){
        List<SmdProductionPlan> list = codeService.getAllTestCode();
        request.setAttribute("testCodes",list);
        return "setting";
    }

    /**
     *
     * @param request
     * @return 为添加型号输入框做ajax去重处理
     */
    @RequestMapping("/isExist")
    @ResponseBody
    public String isExist(HttpServletRequest request){
        String produceName = request.getParameter("produceName");
        String result = "no";
        List<SmdProductionPlan> list = codeService.getAllTestCode();
        for (SmdProductionPlan testcode:list) {
            if(testcode.getProduceName().equals(produceName)){
                result = "yes";
                break;
            }
        }
        return result;
    }

}
