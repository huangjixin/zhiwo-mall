define(function(require, exports, module){
  var template = require('./video.html');
  template = doT.template(template);

  exports.back = function() {
    return '';
  };

  exports.show = function(opt) {

    var funHomePage = {
      init : function(obj){

        this.obj = obj;// 此时obj指代$('.main')dom元素

        this.obj.html(template(''));

      }
    };
    funHomePage.init(opt.body);// 把$('.main')这个dom元素传过来

  }
});