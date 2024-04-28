package com.frogparser.flow.executor.service;

import com.frogparser.flow.domain.runtime_variable.*;
import com.frogparser.flow.domain.runtime_variable.list.*;

import java.util.Set;

public abstract class AbstractFlowVariableSaveService implements FlowVariableSaveService {

    protected final Set<Class<?>> supportedClasses = Set.of(
            BigDecimalListRunTimeVariable.class,
            BigDecimalRunTimeVariable.class,
            BooleanListRunTimeVariable.class,
            BooleanRunTimeVariable.class,
            CustomWebElementListRunTimeVariable.class,
            CustomWebElementRunTimeVariable.class,
            DatasetRunTimeVariable.class,
            DimensionListRunTimeVariable.class,
            DimensionRunTimeVariable.class,
            IntegerListRunTimeVariable.class,
            IntegerRunTimeVariable.class,
            LocalDateListRunTimeVariable.class,
            LocalDateRunTimeVariable.class,
            LocalDateTimeListRunTimeVariable.class,
            LocalDateTimeRunTimeVariable.class,
            LocationListRunTimeVariable.class,
            LocationRunTimeVariable.class,
            LongListRunTimeVariable.class,
            LongRunTimeVariable.class,
            RectangleListRunTimeVariable.class,
            RectangleRunTimeVariable.class,
            StringListRunTimeVariable.class,
            StringRunTimeVariable.class,
            UrlListRunTimeVariable.class,
            UrlRunTimeVariable.class);

    @SuppressWarnings("unchecked")
    protected static Class<? extends AbstractRunTimeVariable<?>> getClazz(AbstractRunTimeVariable<?> runTimeVariable) {
        return (Class<? extends AbstractRunTimeVariable<?>>) runTimeVariable.getClass();
    }

}
