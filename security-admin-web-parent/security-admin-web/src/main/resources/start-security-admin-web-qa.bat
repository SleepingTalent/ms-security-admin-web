MODE 1000,1000

start "security-admin-web-1" java -jar @executable.server.jar.name@.jar --server.port=2322 --spring.application.name=security-admin-web --spring.profiles.active=qa
