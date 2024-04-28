package com.frogparser.flow.domain.runtime_variable.list;

import org.openqa.selenium.Point;

import java.util.List;

public class LocationListRunTimeVariable extends AbstractListRunTimeVariable<Point> {

    public LocationListRunTimeVariable() {
        super();
    }

    public LocationListRunTimeVariable(List<Point> value) {
        super(value);
    }

}
