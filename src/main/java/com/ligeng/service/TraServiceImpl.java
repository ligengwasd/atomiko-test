package com.ligeng.service;

import com.ligeng.mapper.income.IncomeMapper;
import com.ligeng.mapper.outcome.OutcomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dev on 16-8-22.
 */
@Service
@Transactional
public class TraServiceImpl implements ITraService {

    @Autowired
    private IncomeMapper incomeMapper;
    @Autowired
    private OutcomeMapper outcomeMapper;

    @Transactional
    public void test(int param1){
        incomeMapper.addIncome();
        if (param1 == 1){
            throw new RuntimeException("1111111");
        }
        outcomeMapper.addOutcome();
    }
}
