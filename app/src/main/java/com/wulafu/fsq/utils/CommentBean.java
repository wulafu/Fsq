package com.wulafu.fsq.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 用户评论的数据封装
 * Created by fengbei on 2016/3/23.
 *
 * Modify by yujin on 2016/5/5.
 * 景点-评论列表-实体Bean
 * {
 * "data": [
 * {
 * "commentId": 123,
 * "nickName": "Randy James",
 * "icon": "http://123.56.148.217:2020/bdfile/file/getFile.do?filename=/20160203/1c3af792971ba0e0c5692ac4eaf8d1c4.jpg",
 * "commentLevel": 4,
 * "commentTime": "今天",
 * "content": "印尼是世界上岛屿最多的一个国家，由于海岸线漫长，岛上的旅游资源颇为丰富，尤其以秀丽的热带风光最让人难忘。",
 * "memberId": 304,
 *  "articleId": 155
 * }
 * ],
 * "msg": "success",
 * "code": 0
 * }
 *
 */
public class CommentBean extends BaseBean<ArrayList<CommentBean>> {
    /**
     * 评论Id
     */
    private int commentId;
    /**
     * 评论昵称
     */
    private String nickName;
    /**
     * 评论头像地址
     */
    private String icon;
    /**
     * 评论星级,就那个五颗星
     */
    private int commentLevel;
    /**
     * 评论时间
     */
    private String commentTime;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 用户Id
     */
    private int memberId;
    /**
     * 文章Id
     */
    private int articleId;

    private ArrayList<CommentBean> replyComment;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    /**
     * get reply comment
     *
     * @return
     */
    public ArrayList<CommentBean> getReplyComment() {
        return replyComment;
    }

    /**
     * set reply comment
     *
     * @param replyComment
     */
    public void setReplyComment(ArrayList<CommentBean> replyComment) {
        this.replyComment = replyComment;
    }


    /**
     * @param in
     */
    private CommentBean(Parcel in) {
        commentId = in.readInt();
        icon = in.readString();
        commentLevel = in.readInt();
        commentTime = in.readString();
        content = in.readString();
        nickName = in.readString();
        Parcelable[] parcelables = in.readParcelableArray(CommentBean.class.getClassLoader());
        if (parcelables != null) {
            replyComment = new ArrayList<>(parcelables.length);
            for (Parcelable parcelable : parcelables) {
                replyComment.add((CommentBean) parcelable);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(commentId);
        dest.writeString(icon);
        dest.writeInt(commentLevel);
        dest.writeString(commentTime);
        dest.writeString(content);
        dest.writeString(nickName);
        if (replyComment != null)
            dest.writeParcelableArray(replyComment.toArray(new CommentBean[replyComment.size()]), flags);
    }

    public static final Parcelable.Creator<CommentBean> CREATOR = new Parcelable.Creator<CommentBean>() {
        @Override
        public CommentBean createFromParcel(Parcel in) {
            return new CommentBean(in);
        }

        @Override
        public CommentBean[] newArray(int size) {
            return new CommentBean[size];
        }
    };
}
