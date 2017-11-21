// pages/favourite/favourite.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    favourites: [],
    conflicts: [],
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

  // This function finds all the time conflict of the user's favourite
  // courses and create suitable data for rendering
  // A conflict object looks like this:
  //  {course1Id: '1', course2Id: '2', conflictTime: ['6', '7', '8']}
  findConflicts: function() {
    // STEP 1, find all the slots
    var favourites = this.data.favourites;
    var conflicts = [];
    for (var i = 0; i < favourites.length - 1; i++) {
      var course1 = favourites[i];
      for (var j = i + 1; j < favourites.length; j++) {
        var course2 = favourites[j];
        // check week time conflict first
        var hasConflict = false;
        for (var k = 0; k < course1.weekTime.length; k++) {
          var aWeekTime = course1.weekTime[k];
          // if aWeekTime of course1 is contained in course2's time table
          if (this.arrayContainsElement(course2.weekTime, aWeekTime)) {
            hasConflict = true;
            break;
          }
        }
        if (hasConflict) {
          var course1ClassTime = this.computeClassTime(course1);
          var course2ClassTime = this.computeClassTime(course2);
          var conflictTime = []
          for (var k = 0; k < course1ClassTime.length; k++) {
            var currentTime = course1ClassTime[k];
            if (this.arrayContainsElement(course2ClassTime, currentTime)) {
              conflictTime.push(currentTime);
            }
          }
          if (conflictTime.length > 0) {
            conflicts.push({
              course1: i, course2: j,
              conflictTime: conflictTime
            });
          }
        }
      }
    }
    // STEP 2, find if there is any conflict in this slot, record it
    // STEP 3, render it using wxml
    this.setData({
      conflicts: conflicts
    });
    console.log('logging out conflicts: ');
    console.log(conflicts);
  },

  // The input course object should conform to "favourites" standard
  // This function creates an array representing the class time for 
  // one course. The elements are like "1-6".
  computeClassTime: function(course) {
    var classTime = [];
    if (course.weekTime.length === 1) {
      for (var i = 0; i < course.classTime.length; i++) {
        classTime.push(course.weekTime[0] + '-' + course.classTime[i]);
      }
    } else {
      for (var i = 0; i < course.weekTime.length; i++) {
        var weekNumber = course.weekTime[i];
        for (var j = 0; j < course.classTime[i].length; j++) {
          classTime.push(weekNumber + '-' + course.classTime[i][j]);
        }
      }
    }
    return classTime;
  },

  // This function can be widely applied, check if an element 
  // is contained in an array.
  arrayContainsElement: function(array, element) {
    var contain = false;
    for (var i = 0; i < array.length; i++) {
      if (array[i] === element) {
        contain = true;
        break;
      }
    }
    return contain;
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