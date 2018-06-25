define(function (require, exports, module) {
    var template = require('./video.html');
    template = doT.template(template);

    exports.back = function () {
        return '';
    };

    exports.show = function (opt) {

        var funHomePage = {
            init: function (opt) {

                this.obj = opt.body;
                this.img = opt.img;
                this.bottom = opt.bottom;
                this.obj.html(template(''));
                this.personnelInfo = common.getLocalStorage("personnelInfo", true) || '';
                this.renderBottom();
                this.getPersonnel();

                //下一步按钮
                this.bottom.off('tap', '.bot-button .videoGoNext');
                this.bottom.on('tap', '.bot-button .videoGoNext', {fun: this}, this.videoGoNext);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already', this.tapReturn);
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);

                //图片缩放
                this.obj.off("tap", ".viewImage img");
                this.obj.on("tap", ".viewImage img",{fun: this}, this.allImgScale);

   
                this.img.off("tap", ".popup-largeImg img");
                this.img.on("tap", ".popup-largeImg img",{fun: this}, this.closeBox);
            },

            getOneImg: function (personnelId) {
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: 1
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    $(".faceImage").children('img').attr('src',  response.data[0] );
                }, function (error) {

                });
            },

            getTwoImgBack: function (personnelId) {
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: 2
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    $(".backImage").children('img').attr('src',  response.data[0] );
                }, function (error) {

                });
            },

            getThreeImgBack: function (personnelId) {
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: 4
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    $(".eduProve").children('img').attr('src',  response.data[0] );
                }, function (error) {

                });
            },

            getFourImgBack: function (personnelId) {
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: 5
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    $(".verify").children('img').attr('src',  response.data[0] );
                }, function (error) {

                });
            },

            getFiveImgBack: function (personnelId) {
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: 13
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    $(".prove").children('img').attr('src',  response.data[0] );
                }, function (error) {

                });
            },

            getPersonnel: function () {
                var _this = this;
                var personnelParam = {
                    type: 'POST',
                    url: '/personnel/customer/personnel/getPersonnel',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelInfo.personnelId
                    }
                };

                Interface.getAsynData(personnelParam, function (response) {
                    if(response.code === '1' && response.data) {

                        _this.obj.html(template(response.data));
                        _this.getOneImg(_this.personnelInfo.personnelId)
                        _this.getTwoImgBack(_this.personnelInfo.personnelId)
                        _this.getThreeImgBack(_this.personnelInfo.personnelId)
                        _this.getFourImgBack(_this.personnelInfo.personnelId)
                        _this.getFiveImgBack(_this.personnelInfo.personnelId)
                    } else {
                        _this.obj.html(template(_this.defaultForm));
                    }
                }, function (error) {
                    _this.obj.html(template(_this.defaultForm));
                });
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button"></button>\
                    <button type="button" class="goNext videoGoNext">下一步</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },

            // 下一步
            videoGoNext: function () {
                return window.location.href = '#page=personBackground';
            },

            //tap返回上一页
            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
            },
            //图片放大
            allImgScale:function(){ 
                var imgSrc=$(this).attr("src");        
                $(".popup-largeImg").find("img").attr("src",imgSrc);
                common.popup('.popup-largeImg');         
            },
            //关闭图片
            closeBox:function(){           
                console.log(1);
                common.closePopup('.popup-largeImg');
            }
        };
        
        funHomePage.init(opt);
    }
});