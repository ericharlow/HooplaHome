package com.ericharlow.hooplahome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericharlow on 4/14/16.
 */

public class HooplaMedia {
    public MediaType kind;
    public String label;
    public List<Title> titles = new ArrayList<>();

    @Override
    public String toString() {
        return kind.toString() + " label: " + label + titles.toString();
    }
}
