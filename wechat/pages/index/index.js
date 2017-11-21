//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: 'Hello World',
    userInfo: {}
  },
  //事件处理函数
  ToSurvey: function() {
    wx.navigateTo({
      url: '../survey/survey'
    })
  },
  ToSearch:function(){
    wx.navigateTo({
      url:'../search/search'
    })
  },
  ToRate: function () {
    wx.navigateTo({
      url: '../rate/rate'
    })
  },
  onLoad: function () {
    console.log('onLoad')
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      that.setData({
        userInfo:userInfo
      })
    })
  }
})
