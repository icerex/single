package com.teamlinking.single.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by rex on 16/4/10.
 */
public class TagsVO {
    private long edition;
    private long uid;
    private List<TagVO> tagVOs = Lists.newArrayList();
    private List<Long> invalids = Lists.newArrayList();

    public void add(long id, Byte type, long uid, String content, long operatorUid) {
        tagVOs.add(new TagVO(id, type, uid, content, operatorUid));
    }

    public void invalid(long id) {
        invalids.add(id);
    }

    class TagVO {
        private long id;
        //标签类型
        private Byte type;
        //标签所与人
        private long uid;
        //标签内容
        private String content;
        //打标人
        private long operatorUid;

        TagVO() {
        }

        TagVO(long id, Byte type, long uid, String content, long operatorUid) {
            this.id = id;
            this.type = type;
            this.uid = uid;
            this.content = content;
            this.operatorUid = operatorUid;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Byte getType() {
            return type;
        }

        public void setType(Byte type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getOperatorUid() {
            return operatorUid;
        }

        public void setOperatorUid(long operatorUid) {
            this.operatorUid = operatorUid;
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

    public List<TagVO> getTagVOs() {
        return tagVOs;
    }

    public void setTagVOs(List<TagVO> tagVOs) {
        this.tagVOs = tagVOs;
    }

    public List<Long> getInvalids() {
        return invalids;
    }

    public void setInvalids(List<Long> invalids) {
        this.invalids = invalids;
    }
}
