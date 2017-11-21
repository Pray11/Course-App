//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,

    courses:[{id: '1', name: 'Software Engineering', rating: 4, description: 'this is good course', weekTime: ['2'], classTime: ['6', '7', '8']},
      {id: '2', name: 'Artificial Intelligence', rating: 3, description: 'this is a bad course', weekTime: ['2'], classTime: ['9', '10', '11']},
      {id: '3', name: 'Machine Learning', rating: 1, description: 'barely learnable', weekTime: ['4'], classTime: ['6', '7', '8','9', '10','11']},
      {id: '4', name: 'Discrete Mathematics', rating: 5, description: 'beautiful teacher', weekTime: ['2','4'], classTime: [['6', '7', '8'], ['6', '7', '8', '9', '10', '11']]},
      { id: '5', name: 'Roadster', rating: 5, description: 'car!!', weekTime: ['2', '4'], classTime: [['6', '7', '8', '9', '10', '11'], ['6', '7', '8', ]] }],
    liked: ['1', '4', '5']
  }
})

// data convention
// time for courses:
// 2-9-11 means Tuesday, the 9th until 11th classes,
// some course may have several slots, those will be separated by comma without any whitespace