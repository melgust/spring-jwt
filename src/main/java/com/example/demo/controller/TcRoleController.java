package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TcRole;
import com.example.demo.repository.TcRoleRepository;
import com.example.demo.service.ErrorManagerService;
import com.example.demo.utils.ApiResponse;
import com.example.demo.utils.ResponseResult;

@RestController
@RequestMapping("/role")
public class TcRoleController {
	
	private ApiResponse apiResponse;
	
	public TcRoleController() {
		apiResponse = new ApiResponse();
		apiResponse.setData(null);
		apiResponse.setSingleValue(null);
	}
	
	@Autowired
	ErrorManagerService errorManagerService;
	
	@Autowired
	TcRoleRepository tcRoleRepository;
	
	@PutMapping("/add")
	public ApiResponse setRole(@Valid @RequestBody TcRole tcRole) {
		try {
			tcRoleRepository.save(tcRole);
			apiResponse.setStatus(ResponseResult.success.getValue());
			apiResponse.setMessage(ResponseResult.success.getMessage());
		} catch (Exception e) {
			apiResponse.setStatus(ResponseResult.fail.getValue());
			apiResponse.setMessage(errorManagerService.managerException(e));
		}
		return apiResponse;		
	}
	
	@PostMapping("/{roleId}")
	public ApiResponse updRole(@PathVariable(value = "roleId") Long roleId, @Valid @RequestBody TcRole tcRole) {
		try {
			Optional<TcRole> iu = tcRoleRepository.findById(roleId);
			if (iu.isEmpty()) {
				apiResponse.setStatus(ResponseResult.fail.getValue());
				apiResponse.setMessage("No existen datos relacionados con el identificador");
			} else {
				TcRole u = iu.get();
				if (u.getRoleId() == tcRole.getRoleId()) {
					u.setRoleDesc(tcRole.getRoleDesc());
					u.setStatusId(tcRole.getStatusId());
					tcRoleRepository.save(u);
					apiResponse.setStatus(ResponseResult.success.getValue());
					apiResponse.setMessage(ResponseResult.success.getMessage());
				} else {
					apiResponse.setStatus(ResponseResult.fail.getValue());
					apiResponse.setMessage("El identificador indicado no coincide con la informacion proporcionada");
				}
			}
		} catch (Exception e) {
			apiResponse.setStatus(ResponseResult.fail.getValue());
			apiResponse.setMessage(errorManagerService.managerException(e));
		}
		return apiResponse;		
	}
	
	@GetMapping("/{roleId}")
	public ApiResponse getRoleById(@PathVariable(value = "roleId") Long roleId) {
		try {
			Optional<TcRole> iu = tcRoleRepository.findById(roleId);
			if (!iu.isEmpty()) {
				apiResponse.setStatus(ResponseResult.fail.getValue());
				apiResponse.setMessage("No existen datos relacionados con el identificador");
			} else {
				List<TcRole> data = new ArrayList<>();
				data.add(iu.get());
				apiResponse.setData(data);
				apiResponse.setStatus(ResponseResult.success.getValue());
			}
		} catch (Exception e) {
			apiResponse.setStatus(ResponseResult.fail.getValue());
			apiResponse.setMessage(errorManagerService.managerException(e));
		}
		return apiResponse;
	}
	
	@GetMapping("/all")
	public ApiResponse getAll() {
		try {
			List<TcRole> data = tcRoleRepository.findAll();
			apiResponse.setData(data);
			apiResponse.setStatus(ResponseResult.success.getValue());
		} catch (Exception e) {
			apiResponse.setStatus(ResponseResult.fail.getValue());
			apiResponse.setMessage(errorManagerService.managerException(e));
		}
		return apiResponse;
	}

}
