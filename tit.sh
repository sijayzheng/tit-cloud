#!/bin/bash
build() {
  echo 开始编译
  mvn clean package
  echo 编译结束
}

test_build() {
  echo 开始编译
  mvn clean package -Ptest
  echo 编译结束
}

prod_build() {
  echo 开始编译
  mvn clean package -Pprod
  echo 编译结束
}

echo 选择操作
echo '1.构建开发版本'
echo '2.构建测试版本'
echo '3.构建生产版本'
read a
if [ $a = "1" ]; then
  build
elif [ $a = "2" ]; then
  test_build
elif [ $a = "3" ]; then
  prod_build
else
  echo "输入错误"
fi
