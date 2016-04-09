package com.teamlinking.single.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by rex on 16/4/10.
 */
public class AlbumVO {
    private long edition;
    private long uid;
    private List<Pic> pics = Lists.newArrayList();
    private List<Long> invalids = Lists.newArrayList();

    public void add(long id,String url){
        pics.add(new Pic(id,url));
    }

    public void invalid(long id){
        invalids.add(id);
    }

    class Pic{
        private long id;
        private String url;

        Pic(){}

        Pic(long id,String url){
            this.id = id;
            this.url = url;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public long getEdition() {
        return edition;
    }

    public void setEdition(long edition) {
        this.edition = edition;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public List<Pic> getPics() {
        return pics;
    }

    public void setPics(List<Pic> pics) {
        this.pics = pics;
    }

    public List<Long> getInvalids() {
        return invalids;
    }

    public void setInvalids(List<Long> invalids) {
        this.invalids = invalids;
    }
}
