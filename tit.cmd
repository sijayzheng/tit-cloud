@echo off


ECHO.选择操作:
ECHO.
	ECHO.  [1] 构建开发版本
	ECHO.  [2] 构建测试版本
	ECHO.  [3] 构建生产版本
ECHO.

set /p ID=
	IF "%id%"=="1" GOTO build
	IF "%id%"=="2" GOTO test_build
	IF "%id%"=="3" GOTO prod_build
PAUSE

:build
  echo 开始编译
  mvn clean package
  echo 编译结束
goto:eof

:test_build
  echo 开始编译
  mvn clean package -Ptest
  echo 编译结束
goto:eof

:prod_build
  echo 开始编译
  mvn clean package -Pprod
  echo 编译结束
goto:eof
