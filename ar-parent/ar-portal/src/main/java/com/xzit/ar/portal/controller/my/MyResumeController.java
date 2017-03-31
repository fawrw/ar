/**  
 * @Title: ResumeController.java
 * @Package com.xzit.ar.portal.controller.my
 * @Description: TODO
 * @author Mr.Black
 * @date 2016年2月7日 下午7:45:01
 * @version V1.0  
 */
package com.xzit.ar.portal.controller.my;

import java.util.Date;

import javax.annotation.Resource;

import com.xzit.ar.common.init.context.ARContext;
import com.xzit.ar.common.po.user.UserResume;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xzit.ar.common.base.BaseController;
import com.xzit.ar.common.exception.ServiceException;
import com.xzit.ar.common.po.recruit.ResumePost;
import com.xzit.ar.portal.service.my.ResumeService;

/**
 * @ClassName: ResumeController
 * @Description: TODO 简历管理
 * @author Mr.Black
 * @date 2016年2月7日 下午7:45:01
 */
@Controller
@RequestMapping("/my/resume")
public class MyResumeController extends BaseController {

	@Resource
	private ResumeService resumeService;

	@RequestMapping("")
	public String index(Model model){

		return "my/recruit/resume-index";
	}

	@RequestMapping(value = "postResume", method = RequestMethod.POST)
	public String postResume(RedirectAttributes attr, @RequestParam("recruitId") Integer recruitId,
			@RequestParam("resumeId") Integer resumeId) throws ServiceException {

		// 参数设置
		ResumePost resumePost = new ResumePost();
		resumePost.setRecruitId(recruitId);
		resumePost.setResumeId(resumeId);
		resumePost.setCreateTime(new Date());
		resumePost.setState("A");
		resumePost.setStateTime(new Date());
		
		int row = resumeService.postResume(resumePost);
		if(row > 0){
			setMessage(null, "投递成功！");
		}
		
		attr.addAttribute("recruitId", recruitId);
		return "redirect:/recruit/detailRecruit.action";
	}

	@RequestMapping("/addResume")
	public String addResume(Model model){
		// 加载招聘常量
		model.addAttribute("positionSalary", ARContext.positionSalary);
		
		return "my/recruit/resume-add";
	}

	@RequestMapping("/addResumeSubmit")
	public String addResumeSubmit(Model model, RedirectAttributes attr, UserResume resume) throws ServiceException {

		resume.setState("A");
		resume.setStateTime(new Date());
		resume.setUserId(getCurrentUserId());
		resumeService.createResume(resume);

		return "redirect:/my/resume.action";
	}
}
