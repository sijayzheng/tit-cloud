@echo off


ECHO.ѡ�����:
ECHO.
	ECHO.  [1] ���������汾
	ECHO.  [2] �������԰汾
	ECHO.  [3] ���������汾
ECHO.

set /p ID=
	IF "%id%"=="1" GOTO build
	IF "%id%"=="2" GOTO test_build
	IF "%id%"=="3" GOTO prod_build
PAUSE

:build
  echo ��ʼ����
  mvn clean package
  echo �������
goto:eof

:test_build
  echo ��ʼ����
  mvn clean package -Ptest
  echo �������
goto:eof

:prod_build
  echo ��ʼ����
  mvn clean package -Pprod
  echo �������
goto:eof
