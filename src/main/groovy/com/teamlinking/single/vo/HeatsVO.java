package com.teamlinking.single.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by rex on 16/4/12.
 */
public class HeatsVO {
    private long edition;
    private List<HeatVO> list = Lists.newArrayList();
    private List<Long> invalids = Lists.newArrayList();

    public void add(long uid,long version){
        list.add(new HeatVO(uid,version));
    }
    public void invalid(long id) {
        invalids.add(id);
    }

    class HeatVO{
        private long uid;
        private long version;

        HeatVO(){}

        HeatVO(long uid,long version){
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

    public List<HeatVO> getList() {
        return list;
    }

    public void setList(List<HeatVO> list) {
        this.list = list;
    }

    public List<Long> getInvalids() {
        return invalids;
    }

    public void setInvalids(List<Long> invalids) {
        this.invalids = invalids;
    }
}
