# KHhospitalSurveyBackEnd
医院问诊系统后端项目


## 启动
mvn clean install

点击main类运行即可，确保在局域网环境下

## 需求集

1. 问卷增加分数设置
> 新建问卷时，对单个问题设置分数(保留项)，每个选项设置子分数(主要分数来源)

2. 设置机构 -> 部门 -> 科室的层级结构
> 新用户应该归属到科室中，并且新建的问卷也要归属在科室下

3. 系统中可以管理病人档案
> 病人档案归属在科室下，填写问卷时，必须选择病人(根据科室，姓名+身份证的形式)
