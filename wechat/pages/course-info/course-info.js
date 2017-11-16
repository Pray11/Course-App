// pages/course-info/course-info.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentCourse: {},
    liked: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var courseId = Number(options.courseId);
    console.log('course-info page is loading ' + courseId);
    var globalData = getApp().globalData;
    var courseInfo = globalData.courses;
    this.setData({
      currentCourse: courseInfo[courseId - 1]
    })

    var liked = globalData.liked;
    for (var i = 0; i < liked.length; i++) {
      if (Number(liked[i]) === courseId)
        this.setData({
          liked: true
        })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.login({
      success: function(res) {
        console.log(res);
      }
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  like: function() {
    var liked = getApp().globalData.liked;
    liked.push(this.data.currentCourse.id);
    this.setData({
      liked: true
    })
  }
})