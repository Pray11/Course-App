// pages/favourite/favourite.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    favourites: [],
    weekdays:['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var globalData = getApp().globalData;
    var liked = globalData.liked;
    var globalCourses = globalData.courses;
    var favourites = [];
    for (var i = 0; i < liked.length; i++) {
      favourites.push(globalCourses[Number(liked[i]) - 1]);
    }
    this.setData({
      favourites: favourites
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // draw the course tables
    // step 1.1 define number of columns
    // var numberOfTuesdays = 0, numberOfThursdays = 0;
    // var favourites = this.data.favourites;
    // for (var i = 0; i < favourites.length; i++) {
    //   var timeString = favourites[i].time;
    //   if (timeString.indexOf(',') === -1) {
    //     // there is only one time slot
    //     // TODO call it a day
    //   }
    // }
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

  }
})