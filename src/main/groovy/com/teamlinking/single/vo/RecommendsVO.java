package com.teamlinking.single.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by rex on 16/4/12.
 */
public class RecommendsVO {
    private long edition;
    private List<RecommendVO> list = Lists.newArrayList();

    public void add(long uid,long version){
        list.add(new RecommendVO(uid,version));
    }

    class RecommendVO{
        private long uid;
        private long version;

        RecommendVO(){}

        RecommendVO(long uid,long version){
            this.uid = uid;
            this.version = version;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public long getVersion() {
            return version;
        }

        public void setVersion(long version) {
            this.version = version;
        }
    }

    public long getEdition() {
        return edition;
    }

    public void setEdition(long edition) {
        this.edition = edition;
    }

    public List<RecommendVO> getList() {
        return list;
    }

    public void setList(List<RecommendVO> list) {
        this.list = list;
    }
}
