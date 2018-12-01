package com.jc.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 查询类的基类
 * Created by shisheng.wang on 17/8/1.
 */
public class BaseQuery implements Serializable {
    private Integer page;
    private Integer size;

    // private List<QueryField> fields;

    private String sort;
    private String orderBy;
    private Map<String, Object> params;

    /*
        public static class QueryField {
            private String field;
            private String compare;
            private String value;
            private String value2;
            private String dataType;

            public String getField() {
                return field;
            }

            public void setField(String field) {
                this.field = field;
            }

            public String getCompare() {
                return compare;
            }

            public void setCompare(String compare) {
                this.compare = compare;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getValue2() {
                return value2;
            }

            public void setValue2(String value2) {
                this.value2 = value2;
            }

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }
        }
    */
    public int getPage() {
        return page == null ? 1 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size == null ? 10 : size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 返回 orderBy sort的拼接，如果要得到sort,请调用{@getPureSort()}
     *
     * @return
     */
    public String getSort() {
        if (this.orderBy != null && this.orderBy.length() > 0) {
            return this.orderBy + " " + this.sort;
        } else {
            return this.sort;
        }
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * 设置pageNo,如果不能正确转为正型，则使用defaultValue
     *
     * @param pageNo       页码
     * @param defaultValue
     */
    public void setPage(String pageNo, int defaultValue) {
        try {
            this.page = Integer.valueOf(pageNo);
        } catch (NumberFormatException e) {
            this.page = defaultValue;
        }
    }

    public int getPage(int defaultValue) {
        if (this.page != null) {
            return this.page.intValue();
        } else {
            return defaultValue;
        }
    }

    /**
     * 设置pageSize,如果不能正确转为正型，则使用defaultValue
     *
     * @param pageSize     页面容量
     * @param defaultValue
     */
    public void setSize(String pageSize, int defaultValue) {
        try {
            this.size = Integer.valueOf(pageSize);
        } catch (NumberFormatException e) {
            this.size = defaultValue;
        }
    }

    public int getSize(int defaultValue) {
        if (this.size != null) {
            return this.size.intValue();
        } else {
            return defaultValue;
        }
    }


    public void setSort(String sort, String defaultValue) {
        if (sort != null && sort.length() > 0) {
            this.sort = sort;
        } else {
            this.sort = defaultValue;
        }
    }

    public String getSort(String defaultValue) {
        if (sort != null && sort.length() > 0) {
            return this.sort;
        } else {
            return defaultValue;
        }
    }

    public void setOrderBy(String orderBy, String defaultValue) {
        if (orderBy != null && orderBy.length() > 0) {
            this.orderBy = orderBy;
        } else {
            this.orderBy = defaultValue;
        }
    }

    public String getOrderBy(String defaultValue) {
        if (orderBy != null && orderBy.length() > 0) {
            return this.orderBy;
        } else {
            return defaultValue;
        }
    }

    /**
     * 仅仅返回sort
     *
     * @return
     */
    public String getPureSort() {
        return this.sort;
    }

    /**
     * 仅仅返回sort
     *
     * @return
     */
    public String getPureSort(String defaultValue) {
        if (sort != null && sort.length() > 0) {
            return this.sort;
        } else {
            return defaultValue;
        }
    }

    public String getOrderClause() {
        StringBuilder strBuilder = new StringBuilder();
        if (this.orderBy != null && this.orderBy.length() > 0) {
            strBuilder.append(this.orderBy);

            //有order时才会添加sort
            if (this.sort != null && this.sort.length() > 0) {
                strBuilder.append(" ").append(this.sort);
            }
        }
        return strBuilder.toString();
    }

    public int getOffset() {
        int pageNo = getPage(1);
        int pageSize = getSize(10);

        return (pageNo - 1) * pageSize;
    }

    public Object getParamValue(String paramName) {
        return this.params == null ? null : this.params.get(paramName);
    }
}
