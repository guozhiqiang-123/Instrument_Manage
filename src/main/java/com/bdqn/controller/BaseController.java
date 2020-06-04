package com.bdqn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BaseController implements WebBindingInitializer {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 使用@InitBinder解决SpringMVC日期类型无法绑定的问题
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
