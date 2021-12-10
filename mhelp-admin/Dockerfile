FROM java:8
EXPOSE 8888
# 这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
volume /tmp
COPY target/*.jar app.jar
#拷贝本地文件到镜像中
#给app.sh赋予可执行权限
RUN bash -c "touch /app.jar"
ENTRYPOINT ["java", "-jar" , "app.jar" , "--spring.profiles.active=pro" ]
#CMD java -jar /app.jar --spring.profiles.active=pro