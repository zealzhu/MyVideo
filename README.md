# MyVideo
使用MVP模式仿Bilibili的一款在线视频播放器

播放器使用了Vitamio框架中的VideoView

网络开源库使用OkHttp

# 截图

主界面

播放界面

# 使用接口
各大板块视频信息：
http://www.bilibili.com/index/ding.json

获取推荐视频信息：
http://app.bilibili.com/x/feed/index?access_key=9437975bea519f64b033a1dff2218fab&appkey=1d8b6e7d45233436&build=501000&idx=1489893927&mobi_app=android&network=wifi&platform=android&pull=true&style=2&ts=1490426321000&sign=a5bb120db95b0a03b1ef6c68edabc2ae

获取CID
http://api.bilibili.com/view?type=json&appkey=8e9fc618fbd41e28&id=3497752&page=1

获取视频链接：
http://app.bilibili.com/playurl?quality=2&device=android&buvid=B292E40F-ECAB-4521-8250-A0E2AEAA985128566infoc&cid=15268354&build=501000&access_key=121c589b8dde2e22e48b5f442f75d863&platform=android&otype=json&appkey=iVGUTjsxvpLeuDCf&mid=5136944&sign=8701f69671d081cc110bed72bde799ae

搜索关键字
http://app.bilibili.com/x/v2/search/suggest?appkey=1d8b6e7d45233436&build=504001&keyword=%E4%BA%BA&mobi_app=android&platform=android&ts=1495541675&type=accurate&sign=a68615e9a429f2a3540e951e4e806e14

搜索视频
http://app.bilibili.com/x/v2/search?access_key=9c342000cd7c353a9b5bd27f03e0028d&appkey=1d8b6e7d45233436&build=504001&duration=0&keyword=%E5%91%A8%E6%98%9F%E9%A9%B0&mobi_app=android&platform=android&pn=1&ps=20&ts=1495550339&sign=4640b012ccd74ab1363cb94e756c258f
