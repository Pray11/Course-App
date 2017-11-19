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

    courses:[{id: '1', name: 'course1', rating: 4, description: 'this is good course', time: '2-6-8'},
      {id: '2', name: 'course2', rating: 3, description: 'this is a bad course', time: '2-9-11'},
      {id: '3', name: 'course3', rating: 1, description: 'barely learnable', time: '4-6-8,4-9-11'}],

    liked: ['1', '2']
  }
})

// data convention
// time for courses:
// 2-9-11 means Tuesday, the 9th until 11th classes,
// some course may have several slots, those will be separated by comma without any whitespace