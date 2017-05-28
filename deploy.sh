mvn package -DskipTests=true
scp target/cityguide.jar root@s1.nbakaev.ru:/root/hse
ssh root@s1.nbakaev.ru "systemctl restart java.cityguide"
