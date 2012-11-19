package org.uengine.visualmashup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 페이지 전환을 담당하는 스프링 MVC 컨트롤러
 *
 * @author 이승백
 */
@Controller
public class ControllerFacade extends MultiActionController {
    /**
     * 메인 Index
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     */
    @RequestMapping("index.do")
    public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("index");
    }

    /**
     * 타겟 페이지로 이동한다.
     *
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("forward.do")
    public ModelAndView forward(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("param", request);
        HttpSession session = request.getSession(false);

        return new ModelAndView(request.getParameter("nextTarget"));
    }
}