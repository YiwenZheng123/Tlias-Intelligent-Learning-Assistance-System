package com.itheima.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // Original pagination query.
//        // 1. Call the mapper to query the total record count.
//        Long total = empMapper.count();
//
//        // 2. Call the mapper to query the result list.
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start,pageSize);
//        // 3. Wrap the result.
//        return new PageResult<Emp>(total, rows);

        /**
         * Use PageHelper for paginated queries.
         */
        // 1. Set pagination parameters.
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        // 2. Execute the query.
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3. Parse and wrap the query result.
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional (rollbackFor = {Exception.class})// Transaction management. By default, rollback occurs only for RuntimeException.
    @Override
    public void save(Emp emp) throws Exception {
        try {
            // 1. Save basic employee information.
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);


            // 2. Save employee work experience records.
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                // Iterate over the collection and set empId.
                exprList.forEach(empExpr->{
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // Record operation log.
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工: " + emp);
            empLogService.insertLog(empLog);

        }
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 1. Batch delete basic employee information.
        empMapper.deleteByIds(ids);

        // 2. Batch delete employee work experience records.
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        // 1. Update basic employee information by ID.
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 2. Update employee work experience records by ID.
        // 2.1 Delete existing work experience records first.
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        // 2.2 Save new work experience records.
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

}
