## 后续开发计划

- [x] 增加yiyan数据存储接口
- [x] 支持Https
- [x] 增加访问记录接口
- [x] 增加java爬虫技术 获取归属地

- [x] 增加swagger ，快捷调试接口
- [ ] 增加数据分析，分析界面。（apexcharts.js）

## 本地测试解决 has been blocked by CORS policy问题



就是浏览器 **`同源策略`** 问题，或者叫 **`跨域问题`** 。
 常见于用Chrome调试脚本的时候，需要加载的脚本和`location`不同源。




静态HTML文件能够发送 POST请求

方法一： 本地安装Tomcat,apache，将文件部署至服务器

方法二：创建一个用于调试的快捷方式，添加 **`启动参数`**

1. 找到浏览器主程序所在位置→右键→发送到→桌面快捷方式
2. 找到桌面快捷方式→右键→属性→快捷方式选项卡→目标
    在后面追加 参数

```bash
--user-data-dir="D:\ChromeDebug" --test-type --disable-web-security 
```

1. 确认保存
2. 前面这3步，具体参考 [这个百度经验](https://links.jianshu.com/go?to=https%3A%2F%2Fjingyan.baidu.com%2Farticle%2F148a1921c9dbf24d71c3b11f.html)
3. 点击快捷方式打开浏览器，然后再安装 `GoRes` 扩展，添加规则。再打开原来链接。发现可以成功替换和运行了。
4. 然后，继续你的折腾就好啦 (〃'▽'〃)



## API接口

- 直接导入到postman中。(json)
- 后续通过swagger生成

```
{"swagger":"2.0","info":{"version":"1.0","title":"Spring Boot中使用Swagger2构建RESTful APIs"},"host":"127.0.0.1:9282","basePath":"/","tags":[{"name":"website-_record-controller","description":"Website _record Controller"},{"name":"yiyan-controller","description":"Yiyan Controller"}],"paths":{"/cloudopen/checkHealth":{"get":{"tags":["website-_record-controller"],"summary":"checkHealth","operationId":"checkHealthUsingGET","produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/cloudopen/detailedRecord":{"post":{"tags":["website-_record-controller"],"summary":"detailedRecord","operationId":"detailedRecordUsingPOST","consumes":["application/json"],"produces":["*/*"],"parameters":[{"in":"body","name":"jsonObject","description":"jsonObject","required":true,"schema":{"type":"object","additionalProperties":{"type":"object"}}}],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/cloudopen/pythonIp":{"get":{"tags":["website-_record-controller"],"summary":"pythonIp","operationId":"pythonIpUsingGET","produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/cloudopen/record":{"get":{"tags":["website-_record-controller"],"summary":"dataRecord","operationId":"dataRecordUsingGET","produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/cloudopen/yiyan/dataRecord":{"post":{"tags":["yiyan-controller"],"summary":"dataRecord","operationId":"dataRecordUsingPOST","consumes":["application/json"],"produces":["*/*"],"parameters":[{"in":"body","name":"jsonObject","description":"jsonObject","required":true,"schema":{"type":"object","additionalProperties":{"type":"object"}}}],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}}}}
```



