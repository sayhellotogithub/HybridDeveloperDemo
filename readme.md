##混合开发
###jQueryMobile
* H5框架，国外、开源
* H5:标签+js+css
* H5能做的，Android不一定能做，但Android能做的，H5一定能做
###phonegap
* 安卓和js通信的桥梁
###H5开发工具
* WebStorm：快捷键和AS一样
* Hbuilder：非常强大，国人开发的
* sublime

###目前H5开发现状

* 比较复杂的页面使用H5，提高开发效率
* H5即将取代App:在3年内绝对不可以取代，微信里面的公众号都是H5,但是体验比较差，没有缓存
* 不能所有页面都使用H5开发

###H5框架
* jQueryMobile

###
* 在线模板 测试阶段使用
* 本地模板，发布阶段使用加载内部网页，该网页不是在服务器，命名用本地模板动态

##H5编程--jQueryMobile

* 开发中，测试尽量不要用alert,使用console.log("efef");

##PhoneGap
* 可以获取Android系统的信息，但是相当的简单
* 中小型公司会使用，大公司不会使用

##js 和安卓通信
* js调用安卓方法 

  * 安卓端:对象.方法名（参数)
	
		     JavaScriptMethods javaScriptMethods=new JavaScriptMethods(MainActivity.this,mWebView);
		        //第一个参数是对象，第二个参数是对象的映射字符串，js是通过映射字符串来调用java中的方法的
		     mWebView.addJavascriptInterface(javaScriptMethods,"javaInterface");
 
   * js端: window.映射字符串.方法名(参数)

	           /**
				 * 显示信息
				 * @param {Object} msg
				 */
				function showMsg(msg) {
					console.log("showMsg");
					window.javaInterface.showMsg(JSON.stringify(msg));
				}

* 安卓调用js方法
  webview.loadUrl("javascript:方法名(参数)");

		mWebView.loadUrl("javascript:showMsg("+ jsonObject.toString()+")");

* js callback安卓方法
  * js端
  
		     $("#jscallandroidCallBack").on("click", function() {
							console.log("jscallandroidCallBack");
							
							window.javaInterface.getDetail("{'name':'evil','msg':'go to hell','callback':'callBackMethod'}");
						});
		
  * android端	
			
			   /**
			     * js callback sample
			     * @param msg
			     */
			    @JavascriptInterface
			    public void getDetail(final String msg){
			        try {
			            JSONObject   jsonObject =new JSONObject(msg);
			            final String method     =jsonObject.optString("callback");
			            showMsg(msg);
			            //回调,记住mWebView.loadUrl必须放在主线程中调用
			            mHandler.post(new Runnable() {
			                @Override
			                public void run() {
			                    mWebView.loadUrl("javascript:"+method+"("+msg+")");
			                }
			            });
			        } catch (JSONException e) {
			            e.printStackTrace();
			        }
			
			    }



底层可以通过反射实现

学会封装自已框架




   





