package com.teraenergy.bisolution.board.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.teraenergy.common.service.CommonService;

@Controller
public class BoardController {

    @Resource(name = "commonService")
    private CommonService commonService;
    
    private static final String PROGRAM_ID = "Board";

    
}
