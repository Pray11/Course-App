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
    // setting the time table
    var favourites = this.data.favourites;
    for (var i = 0; i < favourites.length; i++) {
      // configure time table only for classes that have slots in more than two days, inclusively
      if (this.data.favourites[i].weekTime.length >= 2) {
        // index of the weekday that has more slots, by default it is 0
        var biggerIndex = 0;
        if (this.data.favourites[i].classTime[0].length
            < this.data.favourites[i].classTime[1].length) {
          biggerIndex = 1;
        }
        var timeTable = [];
        // iterate through the classTime, 2-d array
        for (var j = 0; j < favourites[i].classTime[biggerIndex].length; j++) {
          // fill in the zeros
          var index = Number(favourites[i].classTime[biggerIndex][j]);
          var aRow =  [index, 0, 0];
          // compute the first place
          var hasIndex = false;
          for (var k = 0; k < favourites[i].classTime[0].length; k++) {
            if (Number(favourites[i].classTime[0][k]) === index) {
              hasIndex = true;
            }
          }
          if (hasIndex) {
            aRow[1] = 1;
          }
          // compute the second place
          hasIndex = false;
          for (var k = 0; k < favourites[i].classTime[1].length; k++) {
            if (Number(favourites[i].classTime[1][k]) === index) {
              hasIndex = true;
            }
          }
          if (hasIndex) {
            aRow[2] = 1;
          }
          timeTable.push(aRow);
        }
        favourites[i].timeTable = timeTable;
      }
    }
    this.setData({
      favourites: favourites
    })
    console.log(favourites);
    this.findConflicts();
  },

  findConflicts: function() {
    // STEP 1, find all the slots
    var maxColumns = 0;
    var favourites = this.data.favourites;
    for (var i = 0; i < favourites.length; i++) {
      numberOfColumns = favourites[i].weekTime.length;
      // TODO 
    }
    // STEP 2, find if there is any conflict in this slot, record it
    // STEP 3, render it using wxml
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