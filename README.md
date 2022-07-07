# JDK 11.0.2
다운로드: https://jdk.java.net/archive/

# Jasypt 암호화
VM option 인자 값 넣기
-Djasypt.encryptor.password=암호화키

암호화 사이트 : https://www.devglan.com/online-tools/jasypt-online-encryption-decryption

# 서버 Jar 구동시
java -jar -Djasypt.encryptor.password=암호화키 fileName.jar --spring.profiles.active=production
