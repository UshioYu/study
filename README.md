##疑问区：
#接口yaml实战疑问提问

#已解决：
#ApiActionModel类：
#1.formalParam为什么声明成List<String>，看格式应该是String[](两种均可，只是为了赋值而已)
#2.GlobalVariables全局变量设置的含义？(token值获取后可能变化，可能用于token值的存取)
#StepModel类：
#1.run()方法的入参传timestamp，感觉有点无用，先去掉(后续发现timestamp有用,yaml里声明)

ApiActionModel类：
1.actionVariables变量的声明可以提到方法run()内部，避免造成歧义，它应该算run方法临时
声明的变量，不算model这个类对象该有的属性。run()方法可简化成我写的。
StepResult类：
1.stepVariables参数声明感觉是冗余的，没有处理的地方
2.BaseResult父类声明也没意义，Response参数可放在StepResult里处理，没有两个共用无需用父类吧






