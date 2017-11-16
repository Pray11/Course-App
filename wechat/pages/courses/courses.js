// pages/courses/courses.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    courses : [],
    isDialogShow: false,
    starsArr_dialog : [0,0,0,0,0],
    starScore_dialog : 0,
    currentIndex : 0
  },

  showRatingDialog: function (e) {
    var currentStatu = e.currentTarget.dataset.statu;
    console.log('currentStatu:', currentStatu);
    //关闭 
    if (currentStatu == "close") {
      this.data.courses[this.data.currentIndex].starScore = this.data.starScore_dialog;
      this.data.courses[this.data.currentIndex].starsArr = this.data.starsArr_dialog;
    
      this.setData({
        isDialogShow: false,
        courses : this.data.courses
      });
    }
    // 显示  
    if (currentStatu == "open") {
      var currentIndex = parseInt(e.currentTarget.dataset.index);
      //TODO: post rating score to database P.S.异步加载,show after uploaded 

      this.setData({
        isDialogShow: true,
        starsArr_dialog: [0,0,0,0,0],
        starScore_dialog: 0, 
        currentIndex : currentIndex
      });
    }
  },

  ratingCourse: function (e) {
    var index = parseInt(e.currentTarget.dataset.index);

    for (let i = 0; i < 5; i++)
      this.data.starsArr_dialog[i] = 0;
    if (!(index===0 && this.data.starScore_dialog===1)) {
      for (let i = 0; i <= index; i++)
        this.data.starsArr_dialog[i] = 2;

      index += 1
    }

    this.setData({
      starsArr_dialog : this.data.starsArr_dialog,
      starScore_dialog : index
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var courses = [];
    //TODO :load data from database. Now we make some fake data.  P.S.异步加载,show after uploaded 
    for (let i=0; i<5; i++) {
      courses[i] = new Object();
      courses[i].name = "Course" + i;
      courses[i].israted = false;
      courses[i].starsArr = [0,0,0,0,0]
      courses[i].starScore = "尚未评分" 
    }

    this.setData({
      courses : courses
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
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