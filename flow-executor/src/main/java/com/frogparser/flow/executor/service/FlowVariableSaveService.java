package com.frogparser.flow.executor.service;

import com.frogparser.flow.domain.FlowExecutionId;
import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import com.frogparser.flow.exception.CommandExecutionException;

public interface FlowVariableSaveService {
    void saveVariable(FlowExecutionId flowExecutionId, String runTimeVariableName, AbstractRunTimeVariable<?> runTimeVariable) throws CommandExecutionException;
}
