
Page({
  data:{
    motto:"新兆电科技有限公司",
    use:"打卡程式",
    msg:"other",
    time:'',
    unicode:'',
    area:'',
    tips:'',
    hiddenmodalput:true,
    employeeId:'',
    employeeName:''
  },
  //开始扫码按钮
  getCode: function (e) {
    //this的作用域指向
    var _this = this;
    //调用登录接口，获取code
    wx.login({
      success: function (res) {
        _this.setData({ unicode: res.code });
      },
      fail: function (res) {
        _this.setData({ msg: 'fail' });
        _this.setData({ msg: '登录失败' });
      }
    })
    
  }, 
  openScanCode:function(e) {
      this.getCode();
      this.setData({ msg: '' });
      this.setData({ tips: '' });
      this.setData({ tips1: '' });

    //this的作用域指向
    var _this = this;
    wx.scanCode({
      onlyFromCamera: true,
      scanType: 'qrCode',
      success: (res) => {
        _this.setData({ area: res.result.split(",")[0] });
        _this.setData({ time: res.result.split(",")[1] });
        wx.request({

          url: 'http://113.105.134.218:3333/wechat/bivector.do',
          method: 'POST',
          header: {
            "content-type": "application/x-www-form-urlencoded"
          },
          data: {
            code: this.data.unicode,
            area: this.data.area,
            time1: this.data.time,
          },
          //服务端回调
          success: function (result) {
            var data = result.data;

            //如果获取到openid
            if (data.state == 'success') {
              _this.setData({ msg: 'success' });
              _this.setData({ employeeName: data.employeeName });

            } else if (data.state == 'fail') {
              _this.setData({ msg: 'fail' });
            } else if (data.state == 'overtime'){
              _this.setData({ tips: '二维码信息过期，请重新扫描！' });
            }
            else if (data.state == 'address') {
              _this.setData({ tips: '无效二维码,请扫描打卡二维码！' });
            }
          },
          fail: function (result) {
            _this.setData({ msg: 'fail' });
            _this.setData({ tips: '网络连接失败' })

          }
        })
      }
    })
  },
  confirm2: function () {
    this.getCode();
    this.setData({ msg:  '' });
    this.setData({ tips: '' });
    this.setData({ tips1: '' });
    this.setData({ hiddenmodalput: false });
  },
  
  
  //输入工号提交
  confirm:function(){
    this.setData({hiddenmodalput:true});
    var _this=this;
    //发起网络请求
    wx.request({
      url: 'http://113.105.134.218:3333/wechat/binding.do',
      method: 'POST',
      header: {
        "content-type": "application/x-www-form-urlencoded"
      },
      data:{
          code:this.data.unicode,
          employeeId: this.data.employeeId
      },
      //服务端回调
      success: function (result) {
        var data = result.data;
        //如果获取到openid
        if (data.state == 'success') {
          _this.setData({ msg: 'successb' });
          _this.setData({ employeeName: data.employeeName });
        } else if (data.state == 'fail') {
          _this.setData({ msg: 'failb' });
          switch (data.msg) {
            
            case 'getOpenIdFail':
              _this.setData({ tips: '获取用户标志码失败' });
              break;
            case 'cannotGetCode':
              _this.setData({ tips: '获取用户凭证失败' });
              break;
            case 'noEmployee':
              _this.setData({tips:'员工不存在'});
              _this.setData({ unicode: data.openId });
              break;
          }
        } else if (data.state =='openIdnull'){
            _this.setData({ tips: 'openId获取失败。请重启小程序' });
        } else if (data.state =='fail1'){
          _this.setData({ msg: 'fail1' });
        } else if (data.state =='noWei'){
          _this.setData({ tips: '该微信已被绑定，请联系资讯部!'});
          _this.setData({ tips1: data.oid });
        } else if (data.state == 'notId'){
          _this.setData({ msg: 'fail1' });
        }
      },
      fail:function(result){
        _this.setData({ msg: 'fail' });
        _this.setData({ tips: '网络连接失败' })
      }
    })
  },
  
  //工号输入监听
  employeeIdinput:function(e){
    this.setData({employeeId:e.detail.value});
  },
  //页面加载完毕
  onReady:function(e){
    
  }
  
})
