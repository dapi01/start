package org.codenova.start.model.travel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Body {
    private String dataType;
    private Items items;
    private int numOfRows;
    private int pageNo;
    private int totalCount;
}
