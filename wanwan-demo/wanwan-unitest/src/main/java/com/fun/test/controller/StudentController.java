package com.fun.test.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.common.api.R;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.fun.test.entity.Student;
import com.fun.test.service.StudentService;
import com.fun.test.vo.StudentVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 学生表-测试(student)表控制层
 *
 * @author zz
 * @since 2022-03-23 16:46:08
 */
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Api(value = "xxx", tags = "xxx接口")
public class StudentController {

	private final StudentService studentService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入student")
	public R<Student> detail(String id) {
		Student detail = studentService.getById(id);
		return R.data(detail);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "自定义分页", notes = "传入student")
	public R<IPage<StudentVO>> page(StudentVO student) {
		IPage<StudentVO> pages = studentService.selectStudentPage(new Page<>(),student);
		return R.data(pages);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入student")
	public R<?> submit(@Valid @RequestBody Student student) {
		return R.status(studentService.saveOrUpdate(student));
	}
}
