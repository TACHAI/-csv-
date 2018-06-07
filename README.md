# updateCsv
修改csv文件
##### csv文件存放在服务器的 /usr/java/modifyCsv_jar/file目录下

##### 新文件要放到上面的目录下同时要使用使用接口
```
    http://139.219.141.192:8089/mc/addPath  #接口地址
    {
        mp4Name:'视频名字'，
        csvName:'csv名字'，
        mp4Path:'www.baidu.com',
        csvPath:'/usr/java/modifyCsv_jar/file'
    }
```
##### csv文件名关系到是否能修改下载该文件，后期可能要根据该地址来读写下载文件

