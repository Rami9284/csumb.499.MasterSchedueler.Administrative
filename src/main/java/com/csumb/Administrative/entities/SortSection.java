package com.csumb.Administrative.entities;

import java.util.Comparator;

class SortSection implements Comparator<Section>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Section a, Section b)
    {
        return a.getPeriodNum() - b.getPeriodNum();
    }
}