/**
 * Created by jingchun.zhang on 2018/11/7.
 */
package com.jc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/11/7
 */
@Slf4j
public class BatchRequestHandler {

    public static void main(String[] args) {
        BatchRequestHandler batchRequestHandler = new BatchRequestHandler();
        batchRequestHandler.handler(null);
    }

    //大数据量分批请求

    //单次处理数据最大数量
    private static final Integer SINGLE_HANDLE_DATA_MAX_SIZE = 100;

    private List handler(List data){
        List results = new ArrayList<>();

        Result ret = reqData( 1, SINGLE_HANDLE_DATA_MAX_SIZE);
        results.addAll(ret.getList());

        if (results.isEmpty()){
            log.info("data is empty!");
            return results;
        }
        //分批请求
        int total = (int)ret.getTotal();
        if (total > SINGLE_HANDLE_DATA_MAX_SIZE ){
            int num = (int) Math.ceil(total/ (double)SINGLE_HANDLE_DATA_MAX_SIZE);
            for (int i=1; i<num; i++){
                ret = reqData( i+1, SINGLE_HANDLE_DATA_MAX_SIZE);
                results.addAll(ret.getList());
            }
        }
        log.info("data:",results);
        return results;
    }

    //请求数据
    private Result reqData(int page, int size){
        return new Result(page, size, 200, Collections.EMPTY_LIST);
    }

    @Data
    class Result<T>{
        private int page;
        private int size;
        private long total;
        private List<T> list;

        public Result(int page, int size, long total, List<T> list) {
            this.page = page;
            this.size = size;
            this.total = total;
            this.list = list;
        }
    }

}
