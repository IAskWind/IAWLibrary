package com.iaskwind.iawlibrarydemo;

import java.util.List;

/**
 * Created by Dandy on 2016/10/27.
 */

public class News {


    /**
     * error : false
     * results : [{"_id":"5850cea7421aa9723d29b94a","createdAt":"2016-12-14T12:46:31.332Z","desc":"显示富文本的TextView","images":["http://img.gank.io/945ec385-c69b-4445-8faf-b657a27e3e60"],"publishedAt":"2016-12-15T11:54:38.900Z","source":"web","type":"Android","url":"https://github.com/limedroid/XRichText","used":true,"who":null},{"_id":"585111e4421aa9723d29b94e","createdAt":"2016-12-14T17:33:24.978Z","desc":"Android 密钥保护和 C/S 网络传输安全理论指南","images":["http://img.gank.io/fd3bd333-ed2b-45e9-9645-7407a188b490"],"publishedAt":"2016-12-15T11:54:38.900Z","source":"web","type":"Android","url":"https://drakeet.me/android-security-guide","used":true,"who":"drakeet"},{"_id":"5851ef24421aa9723d29b951","createdAt":"2016-12-15T09:17:24.370Z","desc":"DragVideo，一种在播放视频时，可以任意拖拽视频的方案","images":["http://img.gank.io/45985b26-8230-40d8-9337-80b5b5e56103"],"publishedAt":"2016-12-15T11:54:38.900Z","source":"web","type":"Android","url":"https://github.com/hejunlin2013/DragVideo","used":true,"who":"hejunlin"},{"_id":"5851fddf421aa97240ef9ed4","createdAt":"2016-12-15T10:20:15.934Z","desc":"MD 风格的 EditText，精致。","images":["http://img.gank.io/da9efb85-8678-4823-928b-e53071ed5007"],"publishedAt":"2016-12-15T11:54:38.900Z","source":"chrome","type":"Android","url":"https://github.com/bufferapp/BufferTextInputLayout","used":true,"who":"代码家"},{"_id":"5851fe0c421aa9723d29b953","createdAt":"2016-12-15T10:21:00.66Z","desc":"Kickstarter Android 源码开源啦！","images":["http://img.gank.io/45b63f42-de46-4761-b35e-b6f3ec9f983f"],"publishedAt":"2016-12-15T11:54:38.900Z","source":"chrome","type":"Android","url":"https://github.com/kickstarter/android-oss","used":true,"who":"代码家"},{"_id":"5851ff1e421aa9723d29b954","createdAt":"2016-12-15T10:25:34.692Z","desc":"一个仿探探上传相片的widget，基于xmuSistone的demo, 提供gradle import，添加上传照片功能以及各种回调，api，方便使用","images":["http://img.gank.io/fc777bcc-dba6-4e48-a00e-5028f0ea36b6"],"publishedAt":"2016-12-15T11:54:38.900Z","source":"web","type":"Android","url":"https://github.com/SwiftyWang/android-drag-square","used":true,"who":null},{"_id":"584fbd41421aa93437406724","createdAt":"2016-12-13T17:20:01.704Z","desc":"简单的Android社会化分享登录库，一行代码搞定","images":["http://img.gank.io/f0e08927-9797-4728-821e-ebe36c3b95fc","http://img.gank.io/72fc2885-b61a-42fc-8a3a-cf8d4706fa8d"],"publishedAt":"2016-12-14T11:39:22.686Z","source":"web","type":"Android","url":"https://github.com/shaohui10086/ShareUtil","used":true,"who":"邵辉Vista"},{"_id":"5850b313421aa97237bca8a3","createdAt":"2016-12-14T10:48:51.805Z","desc":"Webview 伸缩 Toolbar 效果","images":["http://img.gank.io/3add8949-b19b-454e-9ace-cc9f7dd39cd9","http://img.gank.io/faa3362a-b7af-44a6-8941-27d2a49bba21"],"publishedAt":"2016-12-14T11:39:22.686Z","source":"chrome","type":"Android","url":"https://github.com/RameshBhupathi/CollapsingToolbar-With-Webview","used":true,"who":"代码家"},{"_id":"584172ca421aa939befafafe","createdAt":"2016-12-02T21:10:34.47Z","desc":"Android路由动态配置方案\u2014\u2014可能是最简单的热更新实现","publishedAt":"2016-12-13T11:42:38.536Z","source":"web","type":"Android","url":"http://www.sixwolf.net/blog/2016/12/02/%E7%83%AD%E6%9B%B4%E6%96%B0%E6%96%B9%E6%A1%88%E4%B9%8B%E8%B7%AF%E7%94%B1%E5%8A%A8%E6%80%81%E9%85%8D%E7%BD%AE/","used":true,"who":"Tang Likang"},{"_id":"584a5e3f421aa963eaaee153","createdAt":"2016-12-09T15:33:19.136Z","desc":"类似微博标签的文本控件","images":["http://img.gank.io/da4abdc8-c25d-4559-9f63-b5327e89a3b8"],"publishedAt":"2016-12-13T11:42:38.536Z","source":"web","type":"Android","url":"https://github.com/limedroid/TagEditText","used":true,"who":null}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5850cea7421aa9723d29b94a
         * createdAt : 2016-12-14T12:46:31.332Z
         * desc : 显示富文本的TextView
         * images : ["http://img.gank.io/945ec385-c69b-4445-8faf-b657a27e3e60"]
         * publishedAt : 2016-12-15T11:54:38.900Z
         * source : web
         * type : Android
         * url : https://github.com/limedroid/XRichText
         * used : true
         * who : null
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private Object who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public Object getWho() {
            return who;
        }

        public void setWho(Object who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
