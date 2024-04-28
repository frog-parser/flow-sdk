package com.frogparser.flow.domain.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.frogparser.flow.domain.command.arithmetic.DecrementVariableCommand;
import com.frogparser.flow.domain.command.arithmetic.IncrementVariableCommand;
import com.frogparser.flow.domain.command.command_group.CommandGroupCommand;
import com.frogparser.flow.domain.command.dataset.CreateDatasetCommand;
import com.frogparser.flow.domain.command.dataset.CreateDatasetRowCommand;
import com.frogparser.flow.domain.command.execution_flow.ifthen.IfThenCommand;
import com.frogparser.flow.domain.command.execution_flow.ifthenelse.IfThenElseCommand;
import com.frogparser.flow.domain.command.execution_flow.loop.ForAllLoopCommand;
import com.frogparser.flow.domain.command.execution_flow.loop.UntilLoopCommand;
import com.frogparser.flow.domain.command.execution_flow.loop.WhileLoopCommand;
import com.frogparser.flow.domain.command.find_element.*;
import com.frogparser.flow.domain.command.function.family.compare.equals.EqualsFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.equals.NotEqualsFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.greater.GreaterFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.greater.NotGreaterFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.greater_or_equals.GreaterOrEqualsFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.greater_or_equals.NotGreaterOrEqualsFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.less.LessFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.less.NotLessFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.less_or_equals.LessOrEqualsFunctionCommand;
import com.frogparser.flow.domain.command.function.family.compare.less_or_equals.NotLessOrEqualsFunctionCommand;
import com.frogparser.flow.domain.command.function.family.logical.AndFunctionCommand;
import com.frogparser.flow.domain.command.function.family.logical.NegateFunctionCommand;
import com.frogparser.flow.domain.command.function.family.logical.OrFunctionCommand;
import com.frogparser.flow.domain.command.function.family.string.*;
import com.frogparser.flow.domain.command.list.*;
import com.frogparser.flow.domain.command.manage.timeouts.SetImplicitlyWaitCommand;
import com.frogparser.flow.domain.command.manage.timeouts.SetPageLoadTimoutCommand;
import com.frogparser.flow.domain.command.manage.timeouts.SetScriptTimeoutCommand;
import com.frogparser.flow.domain.command.manage.timeouts.SleepCommand;
import com.frogparser.flow.domain.command.manage.window.SetWindowSizeCommand;
import com.frogparser.flow.domain.command.read_property.ReadPropertyCommand;
import com.frogparser.flow.domain.command.ui.*;
import com.frogparser.flow.domain.command.variable.log_variable.LogVariableCommand;
import com.frogparser.flow.domain.command.variable.save_variable.SaveVariableCommand;
import com.frogparser.flow.domain.command.variable.set_variable_value.SetConstantValueToVariableCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddListItemCommand.class, name = "AddListItemCommand"),
        @JsonSubTypes.Type(value = AndFunctionCommand.class, name = "AndFunctionCommand"),
        @JsonSubTypes.Type(value = CheckChildElementDoesNotExistCommand.class, name = "CheckChildElementDoesNotExistCommand"),
        @JsonSubTypes.Type(value = CheckChildElementExistsCommand.class, name = "CheckChildElementExistsCommand"),
        @JsonSubTypes.Type(value = CheckElementDoesNotExistCommand.class, name = "CheckElementDoesNotExistCommand"),
        @JsonSubTypes.Type(value = CheckElementExistsCommand.class, name = "CheckElementExistsCommand"),
        @JsonSubTypes.Type(value = ClearListCommand.class, name = "ClearListCommand"),
        @JsonSubTypes.Type(value = ClickCommand.class, name = "ClickCommand"),
        @JsonSubTypes.Type(value = CommandGroupCommand.class, name = "CommandGroupCommand"),
        @JsonSubTypes.Type(value = CreateDatasetCommand.class, name = "CreateDatasetCommand"),
        @JsonSubTypes.Type(value = CreateDatasetRowCommand.class, name = "CreateDatasetRowCommand"),
        @JsonSubTypes.Type(value = DecrementVariableCommand.class, name = "DecrementVariableCommand"),
        @JsonSubTypes.Type(value = DeleteListItemByIndexCommand.class, name = "DeleteListItemByIndexCommand"),
        @JsonSubTypes.Type(value = DeleteListItemByItemCommand.class, name = "DeleteListItemByItemCommand"),
        @JsonSubTypes.Type(value = EqualsFunctionCommand.class, name = "EqualsFunctionCommand"),
        @JsonSubTypes.Type(value = FilterListCommand.class, name = "FilterListCommand"),
        @JsonSubTypes.Type(value = FindChildElementCommand.class, name = "FindChildElementCommand"),
        @JsonSubTypes.Type(value = FindChildElementsCommand.class, name = "FindChildElementsCommand"),
        @JsonSubTypes.Type(value = FindElementCommand.class, name = "FindElementCommand"),
        @JsonSubTypes.Type(value = FindElementsCommand.class, name = "FindElementsCommand"),
        @JsonSubTypes.Type(value = ForAllLoopCommand.class, name = "ForAllLoopCommand"),
        @JsonSubTypes.Type(value = GetFirstListItemCommand.class, name = "GetFirstListItemCommand"),
        @JsonSubTypes.Type(value = GetLastListItemCommand.class, name = "GetLastListItemCommand"),
        @JsonSubTypes.Type(value = GetListItemByIndexCommand.class, name = "GetListItemByIndexCommand"),
        @JsonSubTypes.Type(value = GetListSizeCommand.class, name = "GetListSizeCommand"),
        @JsonSubTypes.Type(value = GoBackCommand.class, name = "GoBackCommand"),
        @JsonSubTypes.Type(value = GoForwardCommand.class, name = "GoForwardCommand"),
        @JsonSubTypes.Type(value = GreaterFunctionCommand.class, name = "GreaterFunctionCommand"),
        @JsonSubTypes.Type(value = GreaterOrEqualsFunctionCommand.class, name = "GreaterOrEqualsFunctionCommand"),
        @JsonSubTypes.Type(value = IfThenCommand.class, name = "IfThenCommand"),
        @JsonSubTypes.Type(value = IfThenElseCommand.class, name = "IfThenElseCommand"),
        @JsonSubTypes.Type(value = IncrementVariableCommand.class, name = "IncrementVariableCommand"),
        @JsonSubTypes.Type(value = IsListEmptyCommand.class, name = "IsListEmptyCommand"),
        @JsonSubTypes.Type(value = LessFunctionCommand.class, name = "LessFunctionCommand"),
        @JsonSubTypes.Type(value = LessOrEqualsFunctionCommand.class, name = "LessOrEqualsFunctionCommand"),
        @JsonSubTypes.Type(value = LogVariableCommand.class, name = "LogVariableCommand"),
        @JsonSubTypes.Type(value = NegateFunctionCommand.class, name = "NegateFunctionCommand"),
        @JsonSubTypes.Type(value = NotEqualsFunctionCommand.class, name = "NotEqualsFunctionCommand"),
        @JsonSubTypes.Type(value = NotGreaterFunctionCommand.class, name = "NotGreaterFunctionCommand"),
        @JsonSubTypes.Type(value = NotGreaterOrEqualsFunctionCommand.class, name = "NotGreaterOrEqualsFunctionCommand"),
        @JsonSubTypes.Type(value = NotLessFunctionCommand.class, name = "NotLessFunctionCommand"),
        @JsonSubTypes.Type(value = NotLessOrEqualsFunctionCommand.class, name = "NotLessOrEqualsFunctionCommand"),
        @JsonSubTypes.Type(value = OpenWebPageCommand.class, name = "OpenWebPageCommand"),
        @JsonSubTypes.Type(value = OrFunctionCommand.class, name = "OrFunctionCommand"),
        @JsonSubTypes.Type(value = ReadPropertyCommand.class, name = "ReadPropertyCommand"),
        @JsonSubTypes.Type(value = RefreshPageCommand.class, name = "RefreshPageCommand"),
        @JsonSubTypes.Type(value = SaveVariableCommand.class, name = "SaveVariableCommand"),
        @JsonSubTypes.Type(value = SendKeysCommand.class, name = "SendKeysCommand"),
        @JsonSubTypes.Type(value = SetConstantValueToVariableCommand.class, name = "SetConstantValueToVariableCommand"),
        @JsonSubTypes.Type(value = SetImplicitlyWaitCommand.class, name = "SetImplicitlyWaitCommand"),
        @JsonSubTypes.Type(value = SetPageLoadTimoutCommand.class, name = "SetPageLoadTimoutCommand"),
        @JsonSubTypes.Type(value = SetScriptTimeoutCommand.class, name = "SetScriptTimeoutCommand"),
        @JsonSubTypes.Type(value = SetWindowSizeCommand.class, name = "SetWindowSizeCommand"),
        @JsonSubTypes.Type(value = SleepCommand.class, name = "SleepCommand"),
        @JsonSubTypes.Type(value = StrConcatFunctionCommand.class, name = "StrConcatFunctionCommand"),
        @JsonSubTypes.Type(value = StrContainsFunctionCommand.class, name = "StrContainsFunctionCommand"),
        @JsonSubTypes.Type(value = StrEndsWithFunctionCommand.class, name = "StrEndsWithFunctionCommand"),
        @JsonSubTypes.Type(value = StrEqualsIgnoreCaseFunctionCommand.class, name = "StrEqualsIgnoreCaseFunctionCommand"),
        @JsonSubTypes.Type(value = StrIsBlankFunctionCommand.class, name = "StrIsBlankFunctionCommand"),
        @JsonSubTypes.Type(value = StrIsEmptyFunctionCommand.class, name = "StrIsEmptyFunctionCommand"),
        @JsonSubTypes.Type(value = StrLenFunctionCommand.class, name = "StrLenFunctionCommand"),
        @JsonSubTypes.Type(value = StrLowerCaseFunctionCommand.class, name = "StrLowerCaseFunctionCommand"),
        @JsonSubTypes.Type(value = StrMatchesFunctionCommand.class, name = "StrMatchesFunctionCommand"),
        @JsonSubTypes.Type(value = StrStartsWithFunctionCommand.class, name = "StrStartsWithFunctionCommand"),
        @JsonSubTypes.Type(value = StrTrimFunctionCommand.class, name = "StrTrimFunctionCommand"),
        @JsonSubTypes.Type(value = StrUpperCaseFunctionCommand.class, name = "StrUpperCaseFunctionCommand"),
        @JsonSubTypes.Type(value = UntilLoopCommand.class, name = "UntilLoopCommand"),
        @JsonSubTypes.Type(value = WhileLoopCommand.class, name = "WhileLoopCommand")
})
public abstract class AbstractCommand {

    private Boolean enabled;
    private String name;
    private String description;
    private Integer retryCount = 0;

    @NotNull
    public Boolean getEnabled() {
        return enabled;
    }

    public AbstractCommand setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public AbstractCommand setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AbstractCommand setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    public Integer getRetryCount() {
        return retryCount;
    }

    public AbstractCommand setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }
}
